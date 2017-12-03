package com.doctortrain.listener;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.doctortrain.bean.StudentInfo;
import com.doctortrain.service.StudentInfoService;
import com.doctortrain.util.DateUtil;


public class GrowResidentClassListener implements ServletContextListener {

	private Logger log = Logger.getLogger(this.getClass());
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	public void contextInitialized(final ServletContextEvent arg0) {
		String[] a = DateUtil.getHMSTime().split(":");
		int b = ((23-Integer.parseInt(a[0]))*60*60*1000)+
				((60-Integer.parseInt(a[1]))*60*1000)+
				((60-Integer.parseInt(a[2]))*1000);
		log.info("住院医级别增长监听器：还有约"+(b/1000/60/60)+"小时进行询问" );
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
            	growRC(arg0);
            }
        },
        b,// 延迟到0点
        24*60*60*1000);// 隔24小时删除一次日志
	}

	public void growRC(ServletContextEvent context) {
		WebApplicationContext ctx= WebApplicationContextUtils.getRequiredWebApplicationContext(context.getServletContext());
		StudentInfoService service = (StudentInfoService) ctx.getBean("studentInfoService");
		try {
			List<StudentInfo> studentInfo = service.queryAllStudentInfo();
			for (StudentInfo studentInfo2 : studentInfo) {
				service.growRC(studentInfo2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
