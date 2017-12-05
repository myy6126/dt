<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加用户信息</title>
    <!-- Bootstrap core CSS -->
    <link href="../Content/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- JS -->
    <script src="../Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../Content/bootstrap/js/bootstrap.min.js"></script>
	<script src="../Content/layer.js"></script>
	<script type="text/javascript">
    
	$(function(){
		getAllRole()
	})
	
    function doSub(){
		
		if($("#password").val()!=$("#password2").val()){
			alert("确认密码不一致！")
			return 
		}
		
    	$.ajax({
			url:"doEdit.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{
				"user.username":$("#username").val(),
				"user.password":$("#password").val(),
				"user.status":$("#userstatus").val(),
				"user.tel":$("#tel").val(),
				"user.email":$("#email").val(),
				"user.privilege":$("#privilege").val(),
				"user.createTime":$("#userCreateTime").val()
			},
			beforeSend:function(){
				return true;
			},
			success:function(jsonObject){
				if(jsonObject.success){
					alert("修改用户成功!")
					toUser();
				}else{
					alert("修改用户失败!")
				} 
				
			}
		});
    }
	
    function getAllRole(){
    	$.ajax({
			url:"getAllRole.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			beforeSend:function(){
				return true;
			},
			success:function(jsonObject){
				if(jsonObject.success){
					$.each(jsonObject.listResult,function(i,n){
						if(n.id==$("#pri").val()){
							$("#privilege").append("<option selected='selected' value='"+n.id+"'>"+n.name+"</option>");
						}else{
							$("#privilege").append("<option value='"+n.id+"'>"+n.name+"</option>");
						}
						
					});
				}else{
					alert("加载角色失败!")
				} 
				
			}
		});
    }
    
    function toUser(){
    	  window.location.href="index.action"
    }
    
    
    	</script>
</head>
<body>
                <legend>&nbsp;&nbsp;修改用户信息</legend>
                <div class="form-horizontal" role="form">
                <div style = "width:86%">
				   
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">用户名</label>
					    <div class="col-sm-6">
						    <input type="text" id="username" class="form-control" value="${user.username}" placeholder="请输入用户名">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">密码</label>
					    <div class="col-sm-6">
						    <input type="password" id="password" value="${user.password}"  class="form-control" placeholder="请输入密码">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">确认密码</label>
					    <div class="col-sm-6">
						    <input type="password" id="password2" value="${user.password}" class="form-control" placeholder="请输入密码">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">邮箱</label>
					    <div class="col-sm-6">
						    <input type="text" id="email" class="form-control" value="${user.email}" placeholder="请输入邮箱">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">手机号</label>
					    <div class="col-sm-6">
						    <input type="text" id="tel" class="form-control" value="${user.tel}" placeholder="请输入手机号">
					    </div>
				    </div>
				    <input id="userstatus" type="hidden" value="${user.status}">
				    
				    <input id="userCreateTime" type="hidden" value="${user.createTime}">
				    <input id="pri" type="hidden" value="${user.privilege}">
				    
				   <c:if test="${operation_type!=0}">
					    <div class="form-group">
						    <label class="col-sm-2 control-label" for="name">分配角色</label>
						    <div class="col-sm-6">
							    <select class="form-control" id="privilege">
							    </select>
						    </div>
					    </div>
				    </c:if>
				   <c:if test="${operation_type==0}">
				   		<input id="privilege" type="hidden" value="${user.privilege}">
				    </c:if>
				    
				   </div>
				     <center>
					     <button type="submit" class="btn btn-default" onclick="doSub()">提交</button>
					     <button type="submit" class="btn btn-default" onclick="toUser()">返回</button>
				     </center>
			    </div>


</body>
</html>