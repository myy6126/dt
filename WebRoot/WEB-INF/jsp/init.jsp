<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>init</title>
    <script src="../Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<style type="text/css">
	</style>
	<script type="text/javascript">
    
	            
	            function init(){
	              		$.ajax({
	             			url:"init/doInit.action",
	             			type:"post",
	             			contentType:"application/x-www-form-urlencoded; charset=utf-8",
	             			beforeSend:function(){
	             				// 进行校验操作
	             				return true;
	             			},
	             			success:function(jsonObject){
	             				if(jsonObject.success){
	             					alert("初始化成功")
	             				}else{
	             					alert("初始化失败")
	             				} 
	             			}
	             		});
	          	}
	             
    	</script>
</head>
<body>
	
	<input type="button" value="init" onclick="init()">
 <!-- 
 drop table clinicalrotation_table;
 drop table announcement_table;
 drop table dt_config_table;
 drop table hospital_table;
 drop table operation_log_table;
 drop table paper_table;
 drop table permission_table;
 drop table role_permission_relation_table;
 drop table role_table;
 drop table score_table;
 drop table studentinfo_table;
 drop table teacherinfo_table;
 drop table user_role_relation_table;
 drop table user_table;
  -->

</body>
</html>