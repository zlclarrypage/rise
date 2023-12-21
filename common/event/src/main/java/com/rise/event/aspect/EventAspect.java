package com.rise.event.aspect;

import com.rise.event.domain.Event;
import com.rise.event.domain.Entity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张牧之
 * @date 2023-04-07 00:46:31
 * @Email zhanglichang99@gmail.com
 */


@Aspect
@Component
public class EventAspect {
    @Autowired
    private ApplicationContext applicationContext;


    @Pointcut("this(com.rise.event.inter.IEventRepository)")
    public void eventSave () {}


    @After("eventSave()")
    public void doAfter (JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (null == args || args.length == 0) {
            return;
        }
        if (!(args[0] instanceof Entity)) {
            return;
        }
        Entity publishers = (Entity)args[0];
        List<Event> eventList = publishers.getEventList();
        if (!CollectionUtils.isEmpty(eventList)) {
            eventList.stream().forEach( e -> applicationContext.publishEvent(e));
        }
    }
}
