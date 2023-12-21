package com.rise.event.test;

import com.rise.event.domain.Event;


/**
 * @author 张牧之
 * @date 2023-04-07 01:45:59
 * @Email zhanglichang99@gmail.com
 */
public class TestEvent implements Event {
    private String eventKey = "eventKey:test";
    private transient DomainTest source;

    public TestEvent(DomainTest domainTest) {
        source = domainTest;
    }

    public DomainTest getSource() {
        return source;
    }


    @Override
    public String getEventKey() {
        return eventKey;
    }
}
