package com.doctortrain.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取系统时间的工具类
 * @author Administrator
 *
 */
public class DateUtil {

	/**
	 * 毫秒
	 */
	private static SimpleDateFormat ssSS = new SimpleDateFormat("ssSS");
	
	/**
	 * 时分秒
	 */
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	/**
	 * 年月日
	 */
	private static SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 年月日 时分秒
	 */
	private static SimpleDateFormat sdfall = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 一般工具类的构造方法都是私用化的
	 */
	private DateUtil() {
	}
	
	public static int getSSSStime(){
		return Integer.parseInt(ssSS.format(new Date()));
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getSystemTime(){
		return sdfall.format(new Date());
	}
	
	/**
	 * HH:mm:ss
	 * @return
	 */
	public static String getHMSTime(){
		return sdf.format(new Date());
	}
	
	/**
	 * yyyy-MM-dd
	 * @return
	 */
	public static String getYmd(){
		return ymd.format(new Date());
	}
}
