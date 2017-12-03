<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>权限管理</title>
    <!-- Bootstrap core CSS -->
    <link href="../../Content/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- JS -->
    <script src="../../Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../../Content/bootstrap/js/bootstrap.min.js"></script>
	<script src="../../Content/layer.js"></script>
	<script type="text/javascript">
    
    var data = [];
    function getAllInfo(){ 
		$.ajax({
			url:"getAll.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			async: false,
			beforeSend:function(){
				return true;
			},
			success:function(jsonObject){
				if(jsonObject.success){
					data = jsonObject.listResult;
				}else{
					alert("false");
				} 
			}
		});
	} 
	
    	</script>
</head>
<body>
                <legend>&nbsp;&nbsp;权限管理</legend>
                <form class="form-horizontal" role="form">
                <div style = "width:86%">
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">设置权限</label>
					    <div class="col-sm-10">
								<label class="checkbox-inline">
									<input type="radio" name="optionsRadiosinline" id="stu_sex_girl"  value="manager" checked> 管理员
								</label>
						    	<label class="checkbox-inline">
									<input type="radio" name="optionsRadiosinline" id="stu_sex_boy" value="user" > 学生
								</label>
								<div>
									  <table class="table table-bordered">
							            <tbody>
							              <tr>
							                <td rowspan="2">基本信息</td>
							                <td>学生信息</td>
							                <td>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">查看
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">增加
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">修改
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">删除
												  </label>
											</td>
											<td>
												<label class="checkbox-inline">
												    <input type="checkbox" value="">全选
												 </label>
											</td>
							              </tr>
							              <tr>
							                <td>医师信息</td>
							                <td>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">查看
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">增加
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">修改
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">删除
												  </label>
											</td>
											<td>
												<label class="checkbox-inline">
												    <input type="checkbox" value="">全选
												 </label>
											</td>
							              </tr>
							              <td rowspan="3">权限管理</td>
							              <tr>
							                <td>用户管理</td>
							                <td>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">查看
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">增加
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">修改
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">删除
												  </label>
											</td>
											<td>
												<label class="checkbox-inline">
												    <input type="checkbox" value="">全选
												 </label>
											</td>
							              </tr>
							              <tr>
							                <td>角色管理</td>
							                <td>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">查看
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">增加
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">修改
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">删除
												  </label>
											</td>
											<td>
												<label class="checkbox-inline">
												    <input type="checkbox" value="">全选
												 </label>
											</td>
							              </tr>
							              <tr>
							                <td>公告管理</td>
							                <td >公告维护</td>
							                <td>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">查看
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">增加
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">修改
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">删除
												  </label>
											</td>
											<td>
												<label class="checkbox-inline">
												    <input type="checkbox" value="">全选
												</label>
											</td>
							              </tr>
							              <tr>
							                <td>日志管理</td>
							                <td >日志维护</td>
							                <td>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">查看
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">增加
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">修改
												  </label>
												  <label class="checkbox-inline">
												    <input type="checkbox" value="">删除
												  </label>
											</td>
											<td>
												<label class="checkbox-inline">
												    <input type="checkbox" value="">全选
												 </label>
											</td>
							              </tr>
							            </tbody>
							          </table>
					    			</div>
					    </div>
				    </div>
				    
				   </div>
				     <center>
					     <button type="submit" class="btn btn-default">提交</button>
					     <button type="submit" class="btn btn-default">返回</button>
				     </center>
			    </form>


</body>
</html>