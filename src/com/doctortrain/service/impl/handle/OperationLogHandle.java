package com.doctortrain.service.impl.handle;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.doctortrain.bean.OperationLog;
import com.doctortrain.bean.User;
import com.doctortrain.service.OperationLogService;
import com.doctortrain.util.DateUtil;


@Aspect //抽取了一个独立的服务(记录操作日志是一个独立的服务，可复用，和具体的业务流程没有直接关系，不管什么业务都是这样记录日志的)
@Component//纳入spring的IOC容器管理，该对象的创建交给spring的IOC容器
public class OperationLogHandle {
	
	private static Map<String,String> moduleMap = new HashMap<String,String>();
	//类加载的时候解析OperationMuduleMapping.properties配置文件，将解析的结果放到Map集合中
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("OperationMuduleMapping");
		moduleMap.put("/stu", bundle.getString("/stu"));
		moduleMap.put("/user", bundle.getString("/user"));
		moduleMap.put("/role", bundle.getString("/role"));
		moduleMap.put("/permission", bundle.getString("/permission"));
		moduleMap.put("/operationlog", bundle.getString("/operationlog"));
		moduleMap.put("/announcement", bundle.getString("/announcement"));
	}
	@Resource(name="operationLogService")
	private OperationLogService operationLogService;
	
	//指定切入点,可以以表达式的方式指定  这个注解必须要加载方法上，才能编译通过
	@Pointcut("execution(* com.doctortrain.service.impl.*.add*(..))"
			//+ "|| execution(* com.doctortrain.service.impl.*.save*(..))"
			+ "|| execution(* com.doctortrain.service.impl.*.update*(..))"
			+ "|| execution(* com.doctortrain.service.impl.*.delete*(..))")
	private void serviceMethod(){}//这个方法是一个空实现，存在的价值就是为了让@point注解编译通过
	
	//Advice是Aspect的具体实现 是一段具体存在的代码
	//指定该通知是什么类型    记录日志应该在service上的方法执行结束之后记录，所以是后置通知
	@After("com.doctortrain.service.impl.handle.OperationLogHandle.serviceMethod()")
	public void writeLog(JoinPoint joinPoint){//连接点：是具体切入的方法，这个方法就是目标对象上的方法
		//System.out.println("---write log---");
		OperationLog ol = new OperationLog();
		//ip地址
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		ol.setIp(ip);
		//操作员
		User operator = (User)ServletActionContext.getRequest().getSession().getAttribute("session_user");
		String name = null;
		if(operator!=null){
			name = operator.getUsername();
		}
		ol.setOperator(name);
		//操作时间
		ol.setTime(DateUtil.getSystemTime());
		//操作模块
		String nameSpace = ServletActionContext.getActionMapping().getNamespace(); 
		ol.setModule(moduleMap.get(nameSpace));
		//操作节点 com.bjpowernode.egov.service.impl.orgServiceImpl.save()
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		ol.setNode(className+"."+methodName+"()");
		
		//操作类型
		if(methodName.startsWith("save")||methodName.startsWith("add")){
			ol.setType("新增");			
		}
		if(methodName.startsWith("delete")){
			ol.setType("删除");			
		}
		if(methodName.startsWith("update")){
			ol.setType("修改");			
		}
		//保存操作日志
		operationLogService.save(ol);//注意：这个方法名不能以save开始，否则也会记录日志
		
	}
}