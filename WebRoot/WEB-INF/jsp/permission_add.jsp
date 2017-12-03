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
    
    function addper(){ 
    	
    	$.ajax({
			url:"add.action",
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
					alert("添加成功!")
					window.location.href="index.action";
				}else{
					alert("添加失败!")
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
                <legend>&nbsp;&nbsp;添加许可信息</legend>
                <div class="form-horizontal" role="form">
                <div style = "width:86%">
				  <div class="form-group">
				    <label class="col-sm-2 control-label" for="perid">许可ID</label>
					    <div class="col-sm-6">
						    <input id="perid" type="text" class="form-control" placeholder="请输入目录编号">
					    </div>
				  </div>
				   
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="pername">许可名称</label>
					    <div class="col-sm-6">
						    <input type="text" id="pername" class="form-control" placeholder="请输入用户名目录名称">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_trainStutas">模块图标</label>
					    <div class="col-sm-6">
					    	<select class="form-control" id="the_icon">
						      <option value=""></option>
						      <option value="icon-cog">齿轮</option>
						      <option value="icon-leaf">叶子</option>
						      <option value="icon-user">用户</option>
						      <option value="icon-apple">苹果</option>
						      <option value="icon-envelope">信封</option>
						      <option value="icon-list">列表</option>
						    </select>
					    </div>
				    </div>
				    
				  <div class="form-group">
				    <label class="col-sm-2 control-label" for="moduleUrl">模块URL</label>
					    <div class="col-sm-6">
						    <input id="moduleUrl" type="text" class="form-control" placeholder="请输入许可编号">
					    </div>
				  </div>
				  
				    <div class="form-group">
				    
				    <label class="col-sm-2 control-label" for="operationUrl">操作URL</label>
					    <div class="col-sm-6">
						    <input id="operationUrl" type="text" class="form-control" placeholder="请输入许可编号">
					    </div>
				  </div>
				   
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="perpid">上级ID</label>
					    <div class="col-sm-6">
						    <input type="text" id="perpid" class="form-control" placeholder="请输入许可名称">
					    </div>
				    </div>
				    
				   </div>
				     <center>
					     <button type="submit" class="btn btn-default" onclick="addper()">提交</button>
					     <button type="submit" class="btn btn-default" onclick="toBack()">返回</button>
				     </center>
			    </div>


</body>
</html>