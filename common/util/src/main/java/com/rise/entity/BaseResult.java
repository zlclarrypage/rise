package com.rise.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author 张牧之
 * @date 2022-12-05 20:19:14
 * @Email zhanglichang99@gmail.com
 */

@Data
@NoArgsConstructor
public class BaseResult<T> {
    private int code;
    private String message;
    private T data;

    public BaseResult success () {
        this.code = 200;
        this.message = "请求成功";
        return this;
    }

    public BaseResult<T> success (T data) {
        this.code = 200;
        this.message = "请求成功";
        this.data = data;
        return this;
    }


    public BaseResult error () {
        this.code = 500;
        this.message = "请求失败";
        return this;
    }
}
