package com.rise.gateway.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 张牧之
 * @date 2022-12-05 20:31:49
 * @Email zhanglichang99@gmail.com
 */


@AllArgsConstructor
@Getter
public enum StatusEnum {
    SUCCESS(200,"请求成功"),
    UPDATE_VERSION_ERROR(500,"记录不存在或版本不一致"),
    NO_RECORD_ERROR(500,"记录不存在"),
    DEL_ROUTE_ERROR(500,"路由删除失败"),
    SAVE_ROUTE_ERROR(500,"路由保存失败");

    private int code;
    private String message;
}
