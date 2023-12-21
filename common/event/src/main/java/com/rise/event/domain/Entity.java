package com.rise.event.domain;



import java.util.ArrayList;
import java.util.List;

/**
 * @author 张牧之
 * @date 2023-04-07 01:05:50
 * @Email zhanglichang99@gmail.com
 */


public class Entity {

    private List<Event> eventList = new ArrayList<>();

    public void publishEvent (Event event) {
        eventList.add(event);
    }

    public List<Event> getEventList () {
        return eventList;
    }
}
