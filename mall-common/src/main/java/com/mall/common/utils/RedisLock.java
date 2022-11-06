package com.mall.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;


@Slf4j
public class RedisLock {
    private static final int DEFAULT_ACQUIRT_RESOLUTION_MILLIS = 5;
    private StringRedisTemplate redisTemplate;

    /**
     * 锁的失效事件  避免线程获得锁喉进行无限期的等待
     */
    private int expireTime = 2 * 1000;
    private int waitExpireTime = 5 * 1000;

    /**
     * 锁的等待时间
     */
    private int timeOut = 1 * 1000;

    /**
     * 锁的对象
     */
    private String lockKey;

    private String requestId = "0";


    public RedisLock(StringRedisTemplate redisTemplate, String lockKey, String requestId, int timeOut, int expireTime) {
        this(redisTemplate, lockKey);
        this.requestId = requestId;
        this.timeOut = timeOut;
        this.expireTime = expireTime;

    }

    public RedisLock(StringRedisTemplate redisTemplate, String lockKey, String requestId, int timeOut) {
        this(redisTemplate, lockKey);
        this.requestId = requestId;
        this.timeOut = timeOut;

    }

    public RedisLock(StringRedisTemplate redisTemplate, String lockKey, String requestId) {
        this(redisTemplate, lockKey);
        this.requestId = requestId;

    }

    public RedisLock(StringRedisTemplate redisTemplate, String lockKey) {
        this.lockKey = lockKey;
        this.redisTemplate = redisTemplate;

    }

    private static final String SCRIPT_LOCK = "if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then redis.call('expire', KEYS[1], ARGV[2]) return 1 else return 0 end";

    public boolean lock() {
        while (timeOut > 0) {
            DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>(SCRIPT_LOCK, Long.class);
            Long object = redisTemplate.execute(defaultRedisScript, Collections.singletonList(lockKey), requestId, String.valueOf(expireTime));
            log.info("获取锁的结果:{}", object);
            if (object != null && object == 1) {
                return true;
            }
            timeOut -= DEFAULT_ACQUIRT_RESOLUTION_MILLIS;
        }
        return false;
    }

    private static final String SCRIPT_SET_EXPIRE = "if redis.call('get', KEYS[1] ) == ARGV[1] then return redis.call('expire', KEYS[1], ARGV[2]) else return 0 end";

    public Thread setExpireTime() {
        return new Thread(() -> {
            while (waitExpireTime >= 0) {
                if (redisTemplate.opsForValue().get(lockKey) != null && redisTemplate.opsForValue().get(lockKey).equals(requestId)) {
                    log.info(lockKey + "续期中——————————————————————————————————————————");
                    DefaultRedisScript<Long> script = new DefaultRedisScript<>(SCRIPT_SET_EXPIRE, Long.class);
                    Long aLong = redisTemplate.execute(script, Collections.singletonList(lockKey), requestId, String.valueOf(expireTime));
                    log.info(lockKey + "续期完成——————————————————————————————————————————" + aLong);
                }
                waitExpireTime -= DEFAULT_ACQUIRT_RESOLUTION_MILLIS;
            }
        });

    }


    private static final String SCRIPT_UNLOCK = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";


    public synchronized void unLock() {
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>(SCRIPT_UNLOCK, Long.class);
        Long object = redisTemplate.execute(defaultRedisScript, Collections.singletonList(lockKey), requestId);
        log.info(lockKey + "锁释放——————————————————————————————————————————" + object);
    }


}
