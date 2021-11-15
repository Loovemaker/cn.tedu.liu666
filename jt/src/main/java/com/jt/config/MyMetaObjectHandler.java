package com.jt.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jt.misc.TimeUtils;
import lombok.val;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;


@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        val now = TimeUtils.now();
        fillStrategy(
                metaObject,
                "created",
                now
        );
        fillStrategy(
                metaObject,
                "updated",
                now
        );
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        fillStrategy(
                metaObject,
                "updated",
                TimeUtils.now()
        );
    }
}
