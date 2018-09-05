package com.ym.ms.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期工具类
 * @author all
 */
public class DateUtil {


	public final static String YYYY_MM_DD = "yyyy-MM-dd";

	public final static String YYYY_MM_DD_SS = "yyyy-MM-dd HH:mm:ss";

	public final static String YYYYMMDDSS = "yyyyMMddHHmmss";


	/**
	 * 功能描述：格式化日期
	 * @param dateStr String 字符型日期
	 * @param format  String 格式
	 * @return Date 日期
	 */
	public static Date parseDate(String dateStr, String format) {
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}


	/**
	 * 功能描述：格式化输出日期
	 * @param date Date 日期
	 * @param format String 格式
	 * @return 返回字符型日期
	 */
	public static String format(Date date, String format) {
		String result = "";
		if (date != null) {
			result = new SimpleDateFormat(format).format(date);
		}
		return result;
	}

	public static String formatBeginDate(String dateString, String  format)
			throws ParseException {
		String result = "";
		if (dateString != null) {
			Date date = new SimpleDateFormat(format).parse(dateString.trim());
			if (date != null) {
				result = new SimpleDateFormat(YYYY_MM_DD).format(date)
						+ " 00:00:00";
			}
		}
		return result;
	}

	public static String formatEndDate(String string, String format)
			throws ParseException {
		String result = "";
		Date date = new SimpleDateFormat(format).parse(string);
		if (date != null) {
			result = new SimpleDateFormat(YYYY_MM_DD).format(date)
					+ " 59:59:59";
		}
		return result;
	}


	/**
	 * 功能描述：返回年份
	 * @param date Date 日期
	 * @return 返回年份
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 功能描述：返回月份
	 * @param date Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日份
	 * @param date Date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：由系统时间来构造格式化日期
	 * @return String 日期字符串
	 */
	public static String getSystem_Date() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	/**
	 * 功能描述：由系统时间来构造格式化日期
	 *
	 * @param time
	 *            Date 日期
	 * @param time
	 *            String 格式
	 * @return String 日期字符串
	 */
	public static String getSystem_Date(long time) {
		Date date = new Date(time);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	/**
	 * 获取几天后或几天前的日期，返回格式为  2018-01-18
	 * @param buffer
	 * @return
	 */
	public static String getDateStringByBuffer(long buffer){

		SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd");
			try   {
			Date  d  =  new Date((new Date()).getTime()+buffer*24*3600*1000);
			return  f.format(d);
			}
			catch(Exception ex) {
				return   "输入格式错误";
			}

	}

	public static void main(String[] args) {
		String date  = getDateStringByBuffer(-30);
		System.out.println(date);
	}


}