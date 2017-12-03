<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>操作日志信息</title>
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
	               undefinedText:' - ',
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
	         	     },{
	         	    	visible : true,
	                     field: 'id',
	                     title: '序号',
	                     align: 'center'
	                 },
	                  {
	                     field: 'title',
	                     title: '标题',
	                     align: 'center'
	                 },
	                 {
	                     field: 'username',
	                     title: '发布用户',
	                     align: 'center'
	                 },
	                 {
	                     field: 'createTime',
	                     title: '创建时间',
	                     align: 'center'
	                 },
	                 {
	                     field: 'publishTime',
	                     title: '发布时间',
	                     align: 'center'
	                 },
	                 {
	                     field: 'status',
	                     title: '状态',
	                     align: 'center'
	                 }
	         	]
	   	    });
	     });
	
     // 添加页面 
     function toAdd(){
     	  window.location.href="toAdd.action"
     }
     
     // 跳转编辑页面
     function toEdit(){
     	if(checkToEdit()){
     		var id = $(".selected > td").eq(1).text();
     		//alert(id)
         	window.location.href="toEdit.action?announcement.id="+id;
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
      
      function publish(){
    	  
    	  if($(".selected").length>1){
    		  alert("一次只能发布一个公告")
    	  }else{
	    	  var id = $(".selected > td").eq(1).text();
	    	  $.ajax({
	    			url:"publish.action",
	    			type:"post",
	    			data:{
	    				"id":id
	    			},
	    			contentType:"application/x-www-form-urlencoded; charset=utf-8",
	    			async: false,
	    			beforeSend:function(){
	    				// 进行校验操作
	    				return true;
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
	            
    	</script>
</head>
<body>
	


		  <h1 class="sub-header">公告管理</h1>
   <!--  <div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			操作日志查询
		</h3>
	</div>
		<div class="panel-body">
			  <div class="form-group">
					    <label class="col-sm-2 control-label" for="userid">标题</label>
					    <div class="col-sm-4">
						    <input id="userid" type="text" class="form-control" placeholder="根据用户编号查询">
					    </div>
					    <label class="col-sm-2 control-label" for="username">用户名</label>
					    <div class="col-sm-4">
						    <input id="username" type="text" class="form-control" placeholder="根据用户名查询">
					    </div>
			 </div>
			  <div class="form-group">
					    <label class="col-sm-2 control-label" for="usertel">开始时间</label>
					    <div class="col-sm-4">
						    <input id="usertel" type="text" class="form-control" placeholder="根据电话查询">
					    </div>
					    <label class="col-sm-2 control-label" for="useremail">结束时间</label>
					    <div class="col-sm-4">
						    <input id="useremail" type="text" class="form-control" placeholder="根据邮箱查询">
					    </div>
			 </div>
			<div style="text-align: right">
			    <button id="btn_search" type="button" class="btn btn-primary" data-toggle="modal" onclick="publish()" data-target="#updateInfo">
			        <span class="glyphicon" aria-hidden="true"></span>发布
			    </button>
			    <button id="btn_search" type="button" class="btn btn-primary" data-toggle="modal" onclick="toAdd()" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>新增公告
			    </button>
			    <button id="btn_search" type="button" class="btn btn-primary" data-toggle="modal" onclick="toEdit()" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>详细信息
			    </button>
			    <button id="btn_search" type="button" class="btn btn-primary" data-toggle="modal" onclick="doDel()" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-remave" aria-hidden="true"></span>删除
			    </button>
			    <button id="btn_search" type="button" class="btn btn-success" data-toggle="modal" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
			    </button>
			</div>
		</div>
	</div> -->
    
          	<div id="toolbar" class="btn-group">
				  <button id="btn_search" type="button" class="btn btn-primary" data-toggle="modal" onclick="publish()" data-target="#updateInfo">
			        <span class="glyphicon" aria-hidden="true"></span>发布
			    </button>
			    <button id="btn_search" type="button" class="btn btn-primary" data-toggle="modal" onclick="toAdd()" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>新增公告
			    </button>
			    <button id="btn_search" type="button" class="btn btn-primary" data-toggle="modal" onclick="toEdit()" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>详细信息
			    </button>
			    <button id="btn_search" type="button" class="btn btn-primary" data-toggle="modal" onclick="doDel()" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-remave" aria-hidden="true"></span>删除
			    </button>
			</div>
          <div class="table-responsive">
            <table class="table table-striped" id="table">
              <!-- <thead>
                <tr>
                  <th data-checkbox="true"></th>
                  <th data-field="accno">序号</th>
                  <th data-field="acctitle">标题</th>
                  <th data-field="accper">发布人</th>
                  <th data-field="acctime">时间</th>
                  <th data-field="accstatus">发布状态</th>
                </tr>
              </thead> -->
            </table>
          </div>
                
</body>
</html>