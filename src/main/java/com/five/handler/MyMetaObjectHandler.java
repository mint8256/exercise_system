package com.five.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author fly
 * @since 2023/5/8 14:52
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
//        log.info("start insert fill ....");
//        if (metaObject.hasGetter("applyTime")) {
//            this.strictInsertFill(metaObject, "applyTime", Date::new, Date.class);
//        }
//        if (metaObject.hasGetter("checkInTime")) {
//            this.strictInsertFill(metaObject, "checkInTime", Date::new, Date.class);
//        }
//        if (metaObject.hasGetter("activeTime")) {
//            this.strictInsertFill(metaObject, "activeTime", Date::new, Date.class);
//        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }

}
