package com.ym.ms.utils;

/**
 * 数字处理工具类
 * @author all
 */
public class NumberUtil {
    public static boolean isNumber(String str){
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }
}
