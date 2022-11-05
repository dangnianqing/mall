package com.mall.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.util.Collections;


@Slf4j
public class RedisLock {

    private RedisTemplate redisTemplate;

    /**
     * 锁的失效事件  避免线程获得锁喉进行无限期的等待
     */
    private int expireTime = 60 * 1000;

    /**
     * 锁的超时时间
     */
    private int timeOut = 10 * 1000;

    /**
     * 锁的对象
     */
    private String lockKey;

    private String requestId = "0";

    // 加锁脚本
    private static final String SCRIPT_LOCK = "if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then redis.call('expire', KEYS[1], ARGV[2]) return 1 else return 0 end";
    // 解锁脚本
    private static final String SCRIPT_UNLOCK = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    private static final String SCRIPT_SET_EXPIRE = "if redis.call('expire', KEYS[1], ARGV[2]) return 1 else return 0 end";

    private volatile boolean locked = false;

    public RedisLock(RedisTemplate redisTemplate, String lockKey, String requestId, int timeOut, int expireTime) {
        this(redisTemplate, lockKey);
        this.requestId = requestId;
        this.timeOut = timeOut;
        this.expireTime = expireTime;

    }

    public RedisLock(RedisTemplate redisTemplate, String lockKey, String requestId, int timeOut) {
        this(redisTemplate, lockKey);
        this.requestId = requestId;
        this.timeOut = timeOut;

    }

    public RedisLock(RedisTemplate redisTemplate, String lockKey, String requestId) {
        this(redisTemplate, lockKey);
        this.requestId = requestId;

    }

    public RedisLock(RedisTemplate redisTemplate, String lockKey) {
        this.lockKey = lockKey;
        this.redisTemplate = redisTemplate;

    }


    //private JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();

    private boolean setNx(final String lockKey, String requestId, String timeOut) {
        try {
            DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>(SCRIPT_LOCK, Long.class);
            Object object = redisTemplate.execute(defaultRedisScript, Collections.singletonList(lockKey), requestId, timeOut);
            Long result = (Long) object;
            if (result != null && result == 1)
                return true;
        } catch (Exception e) {
            log.info("setNx lua redis error; key:{}", lockKey);
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private Long getExpireTime(String lockKey) {
        return redisTemplate.opsForValue().getOperations().getExpire(lockKey);
    }

    private Long getSetExpireTime(String lockKey, String timeOut) {
        try {
            DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>(SCRIPT_SET_EXPIRE, Long.class);
            Object object = redisTemplate.execute(defaultRedisScript, Collections.singletonList(lockKey), timeOut);
            Long result = (Long) object;
            if (result != null && result == 1)
                return getExpireTime(lockKey);
        } catch (Exception e) {
            log.info("setNx lua redis error; key:{}", lockKey);
            return null;
        }
        return null;
    }

    public synchronized boolean lock() throws InterruptedException {
        int out = timeOut;
        while (out > 0) {
            long expires = System.currentTimeMillis() + expireTime + 1;
            if (this.setNx(lockKey, requestId, String.valueOf(expires))) {
                locked = true;
                return true;
            }
            Long currentExpireTime = getExpireTime(lockKey);
            if (currentExpireTime != null && currentExpireTime < System.currentTimeMillis()) {
                //给Key续期
                Long oldExpireTime = getSetExpireTime(lockKey, String.valueOf(expires));
                if (oldExpireTime != null && currentExpireTime == oldExpireTime) {
                    locked = true;
                    return true;
                }
                out -= 5;
                wait(5);
            }

        }
        return false;
    }

    public synchronized void unLock() {
        if (locked) {
            DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>(SCRIPT_UNLOCK, Long.class);
            Object object = redisTemplate.execute(defaultRedisScript, Collections.singletonList(lockKey), requestId);
            Long result = (Long) object;
            if (result != null && result == 1) {
                log.info("释放锁成功    lockKey:{}, requestId:{}", lockKey, requestId);
            } else {
                log.info("释放锁失败    lockKey:{}, requestId:{}", lockKey, requestId);
            }

        }
    }


}
