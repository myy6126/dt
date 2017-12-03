<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>用户信息</title>
    <!-- Bootstrap core CSS -->
    <link href="../Content/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="../Content/bootstrap/css/bootstrap-table.css" rel="stylesheet">
    <!-- JS -->
    <script src="../Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../Content/bootstrap/js/bootstrap.min.js"></script>
	<script src="../Content/bootstrap/js/bootstrap-table.js"></script>
	<script src="../Content/bootstrap/js/bootstrap-table-zh-CN.js"></script>
	<script src="../Content/layer.js"></script>
	<!-- <style type="text/css">
		 .control-label{text-align: left;}
		.form-group label{margin-top: 6px}
		.form-group div{margin-top: 4px}
		button{margin-top: 12px}
		.search_btn{ margin-right:14px}
	</style> -->
	<style type="text/css">
		.form-group{text-align: right}
	</style>
	<script type="text/javascript">
	
	 $(function () {
     	
     	var userId = "<%=session.getAttribute("username")%>"; 
     	var id = "<%=session.getAttribute("operation_type")%>"; 
     	if(id=="0"){
     		getByUsername();
     	}else{
     		getAll();
     	}
     });
	
	
	            function getByUsername(){
	            	$('#table').bootstrapTable({
		            	  toolbar: '#toolbar',
		      	    	  //请求方法
		                  method: 'post',
		                   //是否显示行间隔色
		                  striped: false,
		                  //uniqueId:'id',
		                  //可供选择的每页的行数（*）    
		                  //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
		                  url: "getByUsername.action",
		                  //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
		                  ////查询参数,每次调用是会带上这个参数，可自定义          
		                  undefinedText:'暂无数据',
		                  //分页方式：client客户端分页，server服务端分页（*）
		                  //onlyInfoPagination:true,
		                  sidePagination: "client",
		                  search:true,
		                  pagination:true,
		                  //client 要设置 responseHandler
		                  responseHandler: function(data){
		                	     return data.rows;
		                	},
		            	columns: [
	                         {
	                            field: 'username',
	                            title: '用户名',
	                            align: 'center'
	                        },
	                         {
	                            field: 'password',
	                            title: '用户密码',
	                            align: 'center'
	                        },
	                         {
	                            field: 'email',
	                            title: '电子邮件',
	                            align: 'center'
	                        },
	                         {
	                            field: 'tel',
	                            title: '电话',
	                            align: 'center'
	                        }
		            	]
		      	    });
	            }
	            
	            function getAll(){
	            	$('#table').bootstrapTable({
		            	  clickToSelect:true,
		            	  toolbar: '#toolbar',
		      	    	  //请求方法
		                  method: 'post',
		                   //是否显示行间隔色
		                  striped: false,
		                  //uniqueId:'id',
		                  //可供选择的每页的行数（*）    
		                  //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
		                  url: "getAll.action",
		                  //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
		                  ////查询参数,每次调用是会带上这个参数，可自定义          
		                  undefinedText:'暂无数据',
		                  //分页方式：client客户端分页，server服务端分页（*）
		                  //onlyInfoPagination:true,
		                  sidePagination: "client",
		                  search:true,
		                  pagination:true,
		                  //client 要设置 responseHandler
		                  responseHandler: function(data){
		                	     return data.rows;
		                	},
		            	columns: [
	            	          {
	            	        	  checkbox:true  
		            	     },
	                         {
	                            field: 'username',
	                            title: '用户名',
	                            align: 'center'
	                        },
	                         {
	                            field: 'password',
	                            title: '用户密码',
	                            align: 'center'
	                        },
	                         {
	                            field: 'email',
	                            title: '电子邮件',
	                            align: 'center'
	                        },
	                         {
	                            field: 'tel',
	                            title: '电话',
	                            align: 'center'
	                        },
	                         {
	                            field: 'privilege',
	                            title: '用户角色',
	                            align: 'center'
	                        },
	                        {
	                            field: 'createTime',
	                            title: '创建时间',
	                            align: 'center'
	                        },
	                        {
	                            field: 'updateTime',
	                            title: '更新时间',
	                            align: 'center'
	                        }
	                      
		            	]
		      	    });
	            }
	            
	            function toAdd(){
	            	  window.location.href="toAdd.action"
	            }
	            function toEdit(){
	            	if(checkToEdit()){
		            	var id = $(".selected > td").eq(1).text();
		            	window.location.href="toEdit.action?user.username="+id;
	            	}
	            }
	            
	            function doDel(){
	             	
	          		//$(".selected").eq(1).text();
	          		var ids = $(".selected > td").eq(1).text();
	          		var i = 0;
	          		//所有.selected选择下的第二个子元素的值
	          		$(".selected td:nth-child(2)").each(function(){
	          			if(i==0){
	          				i++;
	          			}else{
	          				i++;
	        					ids+="-"+$(this).text();
	          			}
	          		})
	          		
	          		
	             	if(confirm("您确定要删除"+i+"记录吗？")){
	              		$.ajax({
	             			url:"delete.action",
	             			type:"post",
	             			data:{
	             				"ids":ids
	             			},
	             			contentType:"application/x-www-form-urlencoded; charset=utf-8",
	             			async: false,
	             			beforeSend:function(){
	             				// 进行校验操作
	             				return checkToDel();
	             			},
	             			success:function(jsonObject){
	             				if(jsonObject.success){
	             					window.location.reload();
	             				}else{
	             				} 
	             			}
	             		});
	             	}
	          		
	          	}
	             
	             // 修改校验
	             function checkToEdit(){
	             	if($(".selected").length>1){
	             		alert("只能选择一个修改")
	             		return false
	             	}else if($(".selected").length<1){
	             		alert("请选择一个修改")
	             		return false
	             	}
	             	return true
	             }
	             
	             // 删除校验
	             function checkToDel(){
	             	if($(".selected").length<1){
	             		alert("请选择记录！")
	             		return false
	             	}
	             	return true
	             }
	             
	             function toUserEdit(){
	            	 var id = $("td").eq(0).text();
	            	 window.location.href="toEdit.action?user.username="+id;
	             }
	            
    	</script>
</head>
<body>
	


		  <h1 class="sub-header">用户管理</h1>
   <!-- <div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			用户查询
		</h3>
	</div>
		 <div class="panel-body">
			  <div class="form-group">
					    <label class="col-sm-2 control-label" for="userid">用户编号</label>
					    <div class="col-sm-4">
						    <input id="userid" type="text" class="form-control" placeholder="根据用户编号查询">
					    </div>
					    <label class="col-sm-2 control-label" for="username">用户名</label>
					    <div class="col-sm-4">
						    <input id="username" type="text" class="form-control" placeholder="根据用户名查询">
					    </div>
			 </div>
			  <div class="form-group">
					    <label class="col-sm-2 control-label" for="usertel">电话</label>
					    <div class="col-sm-4">
						    <input id="usertel" type="text" class="form-control" placeholder="根据电话查询">
					    </div>
					    <label class="col-sm-2 control-label" for="useremail">邮箱E-mail</label>
					    <div class="col-sm-4">
						    <input id="useremail" type="text" class="form-control" placeholder="根据邮箱查询">
					    </div>
			 </div>
			<div style="text-align: right">
			    <button id="btn_add" type="button" class="btn btn-primary" data-toggle="modal" onclick="toAdd()" data-target="#addInfo">
			        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			    </button>
			    <button id="btn_remove" type="button" class="btn btn-primary" data-toggle="modal" onclick="toEdit()" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>修改
			    </button>
			    <button id="btn_remove" type="button" class="btn btn-primary" data-toggle="modal" onclick="doDel()" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			    </button>
			    <button id="btn_search" type="button" class="btn btn-success search_btn" data-toggle="modal" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
			    </button>
			</div>
		</div>
	</div> -->
    
          <div class="table-responsive">
          	<div id="toolbar" class="btn-group">
          	<c:if test="${operation_type!=0}">
				<button id="btn_add" type="button" class="btn btn-primary" data-toggle="modal" onclick="toAdd()" data-target="#addInfo">
			        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			    </button>
			    <button id="btn_remove" type="button" class="btn btn-primary" data-toggle="modal" onclick="toEdit()" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>修改
			    </button>
			    <button id="btn_remove" type="button" class="btn btn-primary" data-toggle="modal" onclick="doDel()" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			    </button>
          	</c:if>
          	<c:if test="${operation_type==0}">
			    <button id="btn_remove" type="button" class="btn btn-primary" data-toggle="modal" onclick="toUserEdit()" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>修改
			    </button>
          	</c:if>
			</div>
            <table class="table table-striped" id="table">
              <!-- <thead>
                <tr>
                  <th data-checkbox="true"></th>
                  <th data-field="usernum">用户编号</th>
                  <th data-field="username">用户名</th>
                  <th data-field="password">用户密码</th>
                  <th data-field="email">邮箱</th>
                  <th data-field="tel">电话号</th>
                  <th data-field="createtime">注册时间</th>
                  <th data-field="status">是否激活</th>
                  <th data-field="edit">操作</th>
                </tr>
              </thead> -->
            </table>
          </div>
                
</body>
</html>