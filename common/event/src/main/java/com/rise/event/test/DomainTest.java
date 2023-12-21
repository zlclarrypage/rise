package com.rise.event.test;

import com.rise.event.domain.Entity;

/**
 * @author 张牧之
 * @date 2023-04-07 01:39:53
 * @Email zhanglichang99@gmail.com
 */
public class DomainTest extends Entity {
    public void whenTest () {
        this.publishEvent(new TestEvent(this));
    }
}
