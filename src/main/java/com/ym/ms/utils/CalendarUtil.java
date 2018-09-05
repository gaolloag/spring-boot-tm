/**
 * <p>文件名: CalendarUtil.java</p>
 * <p>版权: CopyrightTag</p>
 * <p>公司: FIXME</p>
 * @author 王春宇(wangchunyu@FIXME)
*/

package com.ym.ms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>CalendarUtil</p>
 * <p>日期工具类</p>
 * 
 * @author 王春宇
 * @version 0.0.0
 *          <table style="border:1px solid gray;">
 *          <tr>
 *          	<th width="100px">版本号</th>
 *          	<th width="100px">动作</th>
 *          	<th width="100px">修改人</th>
 *          	<th width="100px">修改时间</th>
 *          </tr>
 *          <!-- 以 Table 方式书写修改历史 -->
 *          <tr>
 *          	<td>0.0.0</td>
 *          	<td>创建类</td>
 *          	<td>王春宇</td>
 *          	<td>2010-4-21</td>
 *          </tr>
 *          </table>
 */
public class CalendarUtil {
	
	/** 日期格式 yyyy-MM-dd */
    public static final String DATE_FORMAT_MODE1 = "yyyy-MM-dd";
    /** 日期格式yyyy-MM-dd HH:mm:ss */
    public static final String DATE_FORMAT_MODE2 = "yyyy-MM-dd HH:mm:ss";
    /** 日期格式 yyyy-MM-dd HH:mm */
    public static final String DATE_FORMAT_MODE3 = "yyyy-MM-dd HH:mm";
    /** 日期格式 yyyyMMdd */
    public static final String DATE_FORMAT_MODE4 = "yyyyMMdd";
	 /** 日期格式 yyyy-MM */
    public static final String DATE_FORMAT_MODE5 = "yyyy-MM";
    
    //年：YEAR；月：MONTH；日：DAY_OF_MONTH；小时：HOUR_OF_DAY；分钟：MINUTE；秒：SECOND；毫秒：MILLISECOND
    
    /**
     * 获取当前日期 
     * @param format 日期字符串预转换的格式
     * @return 当前日期
     */
    public static String getCurrentDateStr(String format){
		Calendar calendar = Calendar.getInstance();
		return formatStr(calendar, format);
    }
    
    /**
     * 日期转换为字符串
     * @param date Calendar日期
     * @param format 日期预转换的字符串格式
     * @return format格式的日期字符串
     */
    public static String formatStr(Calendar date, String format) {
    	return formatStr(date.getTime(), format);
    }
    
    /**
     * 日期转换为字符串 
     * @param date Date日期
     * @param format 日期预转换的字符串格式
     * @return format格式的日期字符串
     */
    public static String formatStr(Date date, String format) {
    	return new SimpleDateFormat(format).format(date);
    }    

    /**
     * 字符串格式转换 
     * @param source 日期字符串
     * @param sourceFormat 日期字符串的现有格式
     * @param targetFormat 日期字符串预转换的格式
     * @return targetFormat 格式的字符串
     */
    public static String formatStr(String source, String sourceFormat, String targetFormat){
		Date date = formatDate(source, sourceFormat);
		return date == null ? null : formatStr(date, targetFormat);
    }
    
    /**
     * Date类型转换为Calendar类型 
     * @param sourceDate Date类型日期
     * @return Calendar类型日期
     */
    public static Calendar formatCalendar(Date sourceDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDate);
		return calendar;
    }
    
    /**
     * 字符串转换为Calendar类型日期 
     * @param source 日期字符串
     * @param format 日期字符串的现有格式
     * @return 日期
     */
    public static Calendar formatCalendar(String source, String format){
		Date date = formatDate(source, format);
		return date == null ? null : formatCalendar(date);
    }    

    /**
     * 字符串转换为Date类型日期 
     * @param date 日期字符串
     * @param format 日期字符串的现有格式
     * @return 日期
     */
    public static Date formatDate(String date, String format) {
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (Exception e) {}
		return null;
    }

    /**
     * 克隆Calendar对象 
     * @param sourceDate Date类型日期
     * @return Calendar类型日期
     */
    public static Calendar cloneCalendar(Calendar sourceDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(sourceDate.getTimeInMillis());
		return calendar;
    }

    /**
     * 获取新的日期  
     * @param d 日期
     * @param n 正数或负数
     * @param type 日期类型(日：DAY_OF_MONTH；小时：HOUR_OF_DAY；分钟：MINUTE；秒：SECOND；毫秒：MILLISECOND)
     * @return Date
     */
    public static Calendar addDate(Calendar d, int n, int type){
		Calendar calendar = cloneCalendar(d);
		calendar.add(type, n);
		return calendar;
    }

    /**
     * 获取日期差 
     * @param startDate 开始时间
     * @param startDateFormat 开始时间字符串格式
     * @param endDate 结束时间
     * @param endDateFormat 结束时间字符串格式
     * @param divisor 设置除数，返回对应的时间差（日(24*60*60*1000)、小时(60*60*1000)、分钟(60 * 1000)...）
     * @return 日期差 
     * @throws ParseException 异常
     */
    public static long getDateSubtract(String startDate, String startDateFormat, String endDate, String endDateFormat, int divisor) throws ParseException{
    	Calendar start = formatCalendar(startDate, startDateFormat);
    	Calendar end = formatCalendar(endDate, endDateFormat);
		return getDateSubtract(start, end, divisor);
    }
    
    /**
     * 获取日期差
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param divisor 设置除数，返回对应的时间差（日(24*60*60*1000)、小时(60*60*1000)、分钟(60 * 1000)...）
     * @return 日期差 
     * @throws ParseException 异常
     */
    public static long getDateSubtract(Calendar startDate, Calendar endDate, int divisor) {
    	return (endDate.getTimeInMillis() - startDate.getTimeInMillis()) / divisor;
    }    


    public static void main(String ss[]) throws Exception{
		String f = "yyyy-MM-dd HH:mm:ss";
		String d = "2010-11-12 15:30:59";
        Calendar c = CalendarUtil.formatCalendar(d, f);
        System.out.println(CalendarUtil.formatStr(c, f));
    }
}