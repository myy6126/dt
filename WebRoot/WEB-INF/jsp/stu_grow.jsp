<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>学生信息</title>
    <!-- Bootstrap core CSS -->
    <link href="../Content/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="../Content/bootstrap/css/bootstrap-table.css" rel="stylesheet">
    <!-- JS -->
    <script src="../Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../Content/bootstrap/js/bootstrap.min.js"></script>
	<script src="../Content/layer.js"></script>
	<script src="../Content/bootstrap/js/bootstrap-table.js"></script>
	<script src="../Content/bootstrap/js/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript">
    
	$(function(){
		getAllGrow()
	})
	
	function getAllGrow(){
		
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
             url: "getAllGrow.action",
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
       	    	   visible : false,
                   field: 'id',
                   title: 'ID',
                   align: 'center'
               },
                {
                   field: 'name',
                   title: '年级',
                   align: 'center'
               },
               {
                   field: 'value',
                   title: '新编号',
                   align: 'center'
               }
       	]
 	    });
		
	}
	
	 function checkToEdit(){
     	if($(".selected").length>1){
     		alert("只能选择一个")
     		return false
     	}else if($(".selected").length<1){
     		alert("请选择一个")
     		return false
     	}
     	return true
     }
	 
	 function toEditGrow(){
     	if(checkToEdit()){
     		var name = $(".selected > td").eq(1).text();
     		//alert(id)
         	window.location.href="toEditGrow.action?dtConfig.name="+name;
     	}
     }
	 
	 function doDelGrow(){
		 
		 if(checkToEdit()){
			 var name = $(".selected > td").eq(1).text();
				$.ajax({
					url:"doDelGrow.action",
					type:"post",
					contentType:"application/x-www-form-urlencoded; charset=utf-8",
					data:{
						"dtConfig.name":name,
					},
					async: false,
					beforeSend:function(){
						return true;
					},
					success:function(jsonObject){
						if(jsonObject.success){
								alert("删除成功！")
								window.location.href="index.action";
						}else{
							if(jsonObject.msg!=null&&jsonObject.msg!=""){
								alert(jsonObject.msg);
							}else{
								alert("删除失败!");
							}
						} 
					}
				}); 
		 }
		}
	
		 
	    function toBack(){
	    	  window.location.href="index.action"
	    }

	
    	</script>
</head>
<body>
              <h1 class="sub-header">当前学生编号
          </h1>
          <div class="table-responsive">
  	    	
          	<div id="toolbar" class="btn-group">
				    <button id="btn_edit" type="button" class="btn btn-primary" onclick="toEditGrow()">
				        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
				    </button>
				    <button id="btn_del" type="button" class="btn btn-primary" onclick="doDelGrow()">
				        <span class="glyphicon glyphicon-delete" aria-hidden="true"></span>删除
				    </button>
			</div>
            <table class="table table-striped" id="table">
              <!-- <thead>
                <tr>
                  <th data-checkbox="true"></th>
                  <th data-field="id">记录编号</th>
                  <th data-field="name">姓名</th>
                  <th data-field="age">年龄</th>
                  <th data-field="sex">性别</th>
                  <th data-field="trainingDate">入院时间</th>
                  <th data-field="trainingType">身份职称</th>
                  <th data-field="pic">个人照片</th>
                  <th data-field="hospital">所属医院</th>
                  <th data-field="teacher">导师</th>
                  <th data-field="sciTeacher">科研导师</th>
                  <th data-field="residentClass">住院医级别</th>
                  <th data-field="stauts">规培状态</th>
                  <th data-field="score">考试成绩</th>
                </tr>
              </thead> -->
              
            </table>
            
          </div>

</body>
</html>