/*
package com.mall.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "dateCreated", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "dateUpdated", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updatedBy", String.class, "admin");
        this.strictInsertFill(metaObject, "createdBy", String.class, "admin");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "dateUpdated", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createdBy", String.class, "admin");

    }
}
*/
