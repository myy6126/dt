package com.doctortrain.util;

public class CalculateUtil {
	
	/**
	 * 计算开始年月结束年月之间有几个月
	 * 例子：1-1 （一年一月）
	 * @param startTime 年-月 
	 * @param endTime 年-月
	 * @return
	 * @throws Exception
	 */
	public static int countMonth(String startTime,String endTime) throws Exception{
		int startYear = Integer.parseInt(startTime.split("-")[0]);
		int startMonth = Integer.parseInt(startTime.split("-")[1]);
		int endYear = Integer.parseInt(endTime.split("-")[0]);
		int endMonth = Integer.parseInt(endTime.split("-")[1]);
		
		int Yesrs = endYear-startYear;
		if(Yesrs==0){
			int months = endMonth - startMonth;
			if(months==0){
				return 1;
			}else{
				return months + 1;
			}
		}else if(Yesrs==1){
			return 12 - startMonth + endMonth + 1;
		}
		
		return -1;
	}
}
