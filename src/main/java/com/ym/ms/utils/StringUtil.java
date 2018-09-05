package com.ym.ms.utils;

/**
 * com.wcy.springboot.common.utils.StringUtil
 *
 * @author 王春宇
 * @version 1.0.0
 * @date 2018/8/16 16:40
 */
public class StringUtil {

    /**
     * 字符串解析，替换参数
     * @param message 取值范围为{0}~{1}
     * @param param 100,200
     * @return 字符串后解析
     */
    public static String parseMessage(String message, Object ...param) {
        try{
            String msg = message;
            int startIndex = message.indexOf("{");
            int endIndex = message.indexOf("}");
            int index = 0;
            while(startIndex >= 0 && endIndex > 0) {
                if(param[index] == null) {param[index] = "空指针异常";}
                msg = msg.replace("{"+ index +"}", param[index].toString());
                index++;
                startIndex = msg.indexOf("{");
                endIndex = msg.indexOf("}");
            }
            return msg;
        } catch (Exception e){
            return message;
        }
    }

    public static void main(String ss[]){
        String s = parseMessage("取值范围为{0}~{1}", 100, 200);
        System.out.println(s);
    }
}
