<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>许可信息</title>
    <!-- Bootstrap core CSS -->
    <link href="../Content/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../Content/bootstrap/css/datetimepicker/bootstrap-datetimepicker.css" rel="stylesheet">
	<link href="../Content/bootstrap/css/fileinput/fileinput.css" rel="stylesheet">
    <!-- JS -->
    <script src="../Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../Content/bootstrap/js/bootstrap.min.js"></script>
	<script src="../Content/layer.js"></script>
	<script src="../Content/bootstrap/js/datetimepicker/bootstrap-datetimepicker.js"></script>
	<script src="../Content/bootstrap/js/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="../Content/bootstrap/js/fileinput/fileinput.js"></script>
	<script src="../Content/bootstrap/js/fileinput/zh.js"></script>
	
	<script type="text/javascript">
    
	$(function(){
		
		
	})
	
	
	function the_submit(){
		//提交数据
		doEdit()
	}
	
	function doEdit(){
		
		
		$.ajax({
			url:"doEdit.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{
				"permission.id":$("#perid").val(),
				"permission.name":$("#pername").val(),
				"permission.icon":$("#the_icon").val(),
				"permission.moduleUrl":$("#moduleUrl").val(),
				"permission.operationUrl":$("#operationUrl").val(),
				"permission.pid":$("#perpid").val(),
			},
			beforeSend:function(){
				return true;
			},
			success:function(jsonObject){
				if(jsonObject.success){
					alert("修改成功!")
					toBack();
				}else{
					alert("修改失败!")
				} 
				
			}
		});
	}
	
	function toBack(){
  	  window.location.href="index.action"
  }
    	</script>
</head>
<body>
                <legend>&nbsp;&nbsp;修改学生信息</legend>
                <div class="form-horizontal" role="form">
                <div style = "width:86%">
                
				  <div class="form-group">
				    <label class="col-sm-2 control-label" for="perid">许可编号</label>
					    <div class="col-sm-6">
						    <input id="perid" type="text" class="form-control" value="${permission.id}" placeholder="许可编号">
					    </div>
				  </div>
				  
				  <div class="form-group">
				    <label class="col-sm-2 control-label" for="pername">许可名称</label>
					    <div class="col-sm-6">
						    <input id="pername" type="text" class="form-control" value="${permission.name}" placeholder="许可名称">
					    </div>
				  </div>
				  
				  <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_trainStutas">模块图标</label>
					    <div class="col-sm-6">
					    	<select class="form-control" id="the_icon">
					    	<c:if test="${permission.icon=='icon-cog'}">
						      <option value=""></option>
						      <option id="icon-cog" value="icon-cog" selected="selected">齿轮</option>
						      <option id="icon-leaf" value="icon-leaf">叶子</option>
						      <option id="icon-user" value="icon-user">用户</option>
						      <option id="icon-apple" value="icon-apple">苹果</option>
						      <option id="icon-envelope" value="icon-envelope">信封</option>
						      <option id="icon-list" value="icon-list">列表</option>
					    	</c:if>
					    	<c:if test="${permission.icon=='icon-envelope'}">
						      <option value=""></option>
						      <option id="icon-cog" value="icon-cog" >齿轮</option>
						      <option id="icon-leaf" value="icon-leaf">叶子</option>
						      <option id="icon-user" value="icon-user">用户</option>
						      <option id="icon-apple" value="icon-apple">苹果</option>
						      <option id="icon-envelope" value="icon-envelope" selected="selected">信封</option>
						      <option id="icon-list" value="icon-list">列表</option>
					    	</c:if>
					    	<c:if test="${permission.icon=='icon-leaf'}">
						      <option value=""></option>
						      <option id="icon-cog" value="icon-cog" >齿轮</option>
						      <option id="icon-leaf" value="icon-leaf" selected="selected">叶子</option>
						      <option id="icon-user" value="icon-user">用户</option>
						      <option id="icon-apple" value="icon-apple">苹果</option>
						      <option id="icon-envelope" value="icon-envelope">信封</option>
						      <option id="icon-list" value="icon-list">列表</option>
					    	</c:if>
					    	<c:if test="${permission.icon=='icon-user'}">
						      <option value=""></option>
						      <option id="icon-cog" value="icon-cog">齿轮</option>
						      <option id="icon-leaf" value="icon-leaf">叶子</option>
						      <option id="icon-user" value="icon-user" selected="selected">用户</option>
						      <option id="icon-apple" value="icon-apple">苹果</option>
						      <option id="icon-envelope" value="icon-envelope">信封</option>
						      <option id="icon-list" value="icon-list">列表</option>
					    	</c:if>
					    	<c:if test="${permission.icon=='icon-apple'}">
						      <option value=""></option>
						      <option id="icon-cog" value="icon-cog">齿轮</option>
						      <option id="icon-leaf" value="icon-leaf">叶子</option>
						      <option id="icon-user" value="icon-user">用户</option>
						      <option id="icon-apple" value="icon-apple" selected="selected">苹果</option>
						      <option id="icon-envelope" value="icon-envelope">信封</option>
						      <option id="icon-list" value="icon-list">列表</option>
					    	</c:if>
					    	<c:if test="${permission.icon=='icon-list'}">
						      <option value="" selected="selected"></option>
						      <option id="icon-cog" value="icon-cog">齿轮</option>
						      <option id="icon-leaf" value="icon-leaf">叶子</option>
						      <option id="icon-user" value="icon-user">用户</option>
						      <option id="icon-apple" value="icon-apple">苹果</option>
						      <option id="icon-envelope" value="icon-envelope">信封</option>
						      <option id="icon-list" value="icon-list" selected="selected">列表</option>
					    	</c:if>
					    	<c:if test="${permission.icon==''}">
						      <option value="" selected="selected"></option>
						      <option id="icon-cog" value="icon-cog">齿轮</option>
						      <option id="icon-leaf" value="icon-leaf">叶子</option>
						      <option id="icon-user" value="icon-user">用户</option>
						      <option id="icon-apple" value="icon-apple">苹果</option>
						      <option id="icon-envelope" value="icon-envelope">信封</option>
						      <option id="icon-list" value="icon-list">列表</option>
					    	</c:if>
						      
						    </select>
					    </div>
				    </div>
				  
				  <div class="form-group">
				    <label class="col-sm-2 control-label" for="moduleUrl">模块Url</label>
					    <div class="col-sm-6">
						    <input id="moduleUrl" type="text" class="form-control" value="${permission.moduleUrl}" placeholder="模块Url">
					    </div>
				  </div>
				  
				  <div class="form-group">
				    <label class="col-sm-2 control-label" for="operationUrl">操作Url</label>
					    <div class="col-sm-6">
						    <input id="operationUrl" type="text" class="form-control" value="${permission.operationUrl}" placeholder="操作Url">
					    </div>
				  </div>
				  
				  <div class="form-group">
				    <label class="col-sm-2 control-label" for="perpid">上级编号</label>
					    <div class="col-sm-6">
						    <input id="perpid" type="text" class="form-control" value="${permission.pid}" placeholder="上级编号">
					    </div>
				  </div>
				    
				   </div>
				     <center>
					     <button onclick="the_submit()" class="btn btn-default">提交</button>
					     <button onclick="toBack()" class="btn btn-default">返回</button>
				     </center>
			    </div>


</body>
</html>