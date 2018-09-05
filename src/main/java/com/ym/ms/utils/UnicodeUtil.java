package com.ym.ms.utils;

public class UnicodeUtil {

    public static String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        String[] strs = unicode.split("%u");
        StringBuffer returnStr = new StringBuffer();
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr.append((char) Integer.valueOf(strs[i], 16).intValue());
        }
        return returnStr.toString();
    }

    public static String cnToUnicode(String cn) {
        char[] chars = cn.toCharArray();
        StringBuffer returnStr = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            returnStr.append("%u" + Integer.toString(chars[i], 16));
        }
        return returnStr.toString();
    }

    public static void main(String[] args) {
        System.out.println(UnicodeUtil.unicodeToCn("%u8D85%u7EA7%u7BA1%u7406%u5458"));

    }
}
