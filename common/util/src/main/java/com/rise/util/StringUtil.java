package com.rise.util;

import org.springframework.util.StringUtils;

/**
 * @author 张牧之
 * @date 2022-12-05 23:53:56
 * @Email zhanglichang99@gmail.com
 */


public class StringUtil extends StringUtils{
    public static boolean isNotEmpty (Object str) {
        return !StringUtils.isEmpty(str);
    }
}
