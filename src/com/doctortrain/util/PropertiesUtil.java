package com.doctortrain.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;

public class PropertiesUtil {
	
	public static String getCR(String str) throws Exception{
		InputStream inStream = null ;
		String result = "";
		inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("crconfig.properties") ;
		Properties pp = new Properties();
		pp.load(inStream);
		result = pp.getProperty(str);
		inStream.close();
		return result;
	}
	
	public synchronized static void write(String key,String value) throws Exception{
		  Properties prop = new Properties();
		  prop.setProperty(key, value);
		  PrintStream ps = new PrintStream("D:/workspance/doctor-train-prj-login-01/src/crindex.properties");
		  prop.list(ps);
		  ps.close();
	}
	
	public static String getCrIndex(String key) throws Exception{
		InputStream inStream = null ;
		String result = "";
		inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("crindex.properties") ;
		Properties pp = new Properties();
		pp.load(inStream);
		result = pp.getProperty(key);
		inStream.close();
		return result;
	}
	

	
}
