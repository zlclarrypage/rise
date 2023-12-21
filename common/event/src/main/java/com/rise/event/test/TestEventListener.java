package com.rise.event.test;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * @author 张牧之
 * @date 2023-04-07 04:10:19
 * @Email zhanglichang99@gmail.com
 */


@Component
public class TestEventListener {

    @EventListener(TestEvent.class)
    public void listener(TestEvent event) {
        System.out.println("监听器：        " + "TestEventListener");
        System.out.println("监听器所在线程：" + Thread.currentThread().getName());
        System.out.println("事件：          " + event);
        System.out.println("事件的数据：    " + event.getSource());
    }

}
