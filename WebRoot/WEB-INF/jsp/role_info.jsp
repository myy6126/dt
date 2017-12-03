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
                     title: '角色ID',
                     align: 'center'
                 },
                  {
                     field: 'name',
                     title: '角色名称',
                     align: 'center'
                 },
                 {
                     field: 'type',
                     title: '角色类型',
                     align: 'center'
                 },
                 {
                     field: 'remark',
                     title: '备注',
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
         	window.location.href="toEdit.action?role.id="+id;
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
    	</script>
</head>
<body>
	


		  <h1 class="sub-header">角色管理</h1>
		<div id="toolbar" class="btn-group">
				<button id="btn_add" type="button" class="btn btn-primary" data-toggle="modal" onclick="toAdd()" data-target="#addInfo">
			        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			    </button>
			    <button id="btn_remove" type="button" class="btn btn-primary" data-toggle="modal" onclick="toEdit()" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>修改
			    </button>
			    <button id="btn_remove" type="button" class="btn btn-primary" data-toggle="modal" onclick="doDel()" data-target="#updateInfo">
			        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			    </button>
			    
		
		</div>
    
          <div class="table-responsive">
          	
            <table class="table table-striped" id="table">
             <!--  <thead>
                <tr>
                  <th data-checkbox="true"></th>
                  <th data-field="id">角色编号</th>
                  <th data-field="rolename">角色名称</th>
                  <th data-field="roleedit">角色权限</th>
                </tr>
              </thead> -->
            </table>
          </div>
                
</body>
</html>