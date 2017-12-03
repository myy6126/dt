<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		getAllRole();
	})
	
    function adduser(){
    	/* 
    	var status = "1";
		if(typeof($("#turnon").attr("checked"))!="undefined"){
			status = "0";
		} */
		if($("#password").val()!=$("#password2").val()){
			alert("确认密码不一致！")
			return 
		}
    	
    	$.ajax({
			url:"add.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{
				"user.username":$("#username").val(),
				"user.password":$("#password").val(),
				"user.status":$(":selected").val(),
				"user.tel":$("#tel").val(),
				"user.email":$("#email").val(),
				"user.privilege":$("#privilege").val()
			},
			beforeSend:function(){
				return true;
			},
			success:function(jsonObject){
				if(jsonObject.success){
					alert("添加成功!")
					window.location.href="index.action";
				}else{
					alert("添加失败!")
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
						$("#privilege").append("<option value='"+n.id+"'>"+n.name+"</option>");
					});
				}else{
					alert("加载角色失败!")
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
                <legend>&nbsp;&nbsp;添加用户信息</legend>
                <div class="form-horizontal" role="form">
                <div style = "width:86%">
				   
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">用户名</label>
					    <div class="col-sm-6">
						    <input type="text" id="username" class="form-control" placeholder="请输入用户名">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">密码</label>
					    <div class="col-sm-6">
						    <input type="password" id="password" class="form-control" placeholder="请输入密码">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">确认密码</label>
					    <div class="col-sm-6">
						    <input type="password" id="password2" class="form-control" placeholder="请输入密码">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">邮箱</label>
					    <div class="col-sm-6">
						    <input type="text" id="email" class="form-control" placeholder="请输入邮箱">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">手机号</label>
					    <div class="col-sm-6">
						    <input type="text" id="tel" class="form-control" placeholder="请输入手机号">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">激活状态</label>
					    <div class="col-sm-6">
						    <div class="radio">
								<label>
									<input type="radio" name="optionsRadios" id="turnon" value="1" checked>激活
								</label>
							</div>
							<div class="radio">
								<label>
									<input type="radio" name="optionsRadios" id="turnoff" value="0">未激活
								</label>
							</div>
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">分配角色</label>
					    <div class="col-sm-6">
						    <select class="form-control" id="privilege">
						    </select>
					    </div>
				    </div>
				    
				   </div>
				     <center>
					     <button type="submit" class="btn btn-default" onclick="adduser()">提交</button>
					     <button type="submit" class="btn btn-default" onclick="toBack()">返回</button>
				     </center>
			    </div>


</body>
</html>