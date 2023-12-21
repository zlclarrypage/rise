package com.rise.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张牧之
 * @date 2022-12-05 21:28:37
 * @Email zhanglichang99@gmail.com
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPage<T> {
    private int current;
    private int size;
    private T param;
    private String[] orderBy;

    public int getCurrent () {
        if (current == 0) {
            current = 1;
        }
        return current;
    }

    public int getSize () {
        if (size == 0) {
            size = 10;
        }
        return size;
    }
}
