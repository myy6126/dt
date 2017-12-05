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
	<style type="text/css">
	td{
    	vertical-align: middle !important;
	}
	</style>
    <!-- JS -->
    <script src="../Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../Content/bootstrap/js/bootstrap.min.js"></script>
	<script src="../Content/bootstrap/js/bootstrap-table.js"></script>
	<script src="../Content/bootstrap/js/bootstrap-table-zh-CN.js"></script>
	<script src="../Content/layer.js"></script>
	<script type="text/javascript">
    
  			    var data = [];
	
	            $(function () {
	            	
	            	var id = "<%=session.getAttribute("operation_type")%>"; 
	            	if(id=="0"){
	            		getStudentInfoByUsername();
	            	}else{
		            	getAllStudentInfo();
	            	}
	            });
	            
	            function getAllStudentInfo(){
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
	            	     },{
	            	    	visible : true,
                            field: 'id',
                            title: '记录编号',
                            align: 'center'
                        },
                         {
                            field: 'name',
                            title: '姓名',
                            align: 'center'
                        },
                         {
                            field: 'birth',
                            title: '出生年月',
                            align: 'center'
                        },
                         {
                            field: 'sex',
                            title: '性别',
                            align: 'center'
                        },
                         {
                            field: 'trainingDate',
                            title: '入院时间',
                            align: 'center'
                        },
                         {
                            field: 'trainingType',
                            title: '身份职称',
                            align: 'center'
                        },
                        {
                            field: 'pic',
                            title: '个人照片',
                            align: 'center',
                            formatter: function(value,row,index){
                               // return '<img  src="//www.baidu.com/img/baidu_jgylogo3.gif" class="img-rounded" >';
                            	//return '<img src="'+getRealPath()+'/stu_img/'+value+'.jpg" class="img-rounded" >';
                           	return '<img height="159" width="123"  src="'+getRealPath()+'/stu_img/'+value+'.jpg" class="img-rounded" >';
                            }
                        },
                        {
                            field: 'hospital',
                            title: '所属医院',
                            align: 'center'
                        },
                        {
                            field: 'teacher',
                            title: '导师',
                            align: 'center'
                        },
                        {
                            field: 'residentClass',
                            title: '住院医级别',
                            align: 'center'
                        },
                        {
                            field: 'stauts',
                            title: '规培状态',
                            align: 'center'
                        },
                        {
                            field: 'inClass',
                            title: '轮转科室',
                            align: 'center'
                        }
	            	]
	      	    });
	            }
	            
	            function getStudentInfoByUsername(){
	            	$('#table').bootstrapTable({
	            	  clickToSelect:false,
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
		            	    	visible : true,
	                            field: 'id',
	                            title: '记录编号',
	                            align: 'center'
	                        },
	                         {
	                            field: 'name',
	                            title: '姓名',
	                            align: 'center'
	                        },
	                         {
	                            field: 'birth',
	                            title: '出生年月',
	                            align: 'center'
	                        },
	                         {
	                            field: 'sex',
	                            title: '性别',
	                            align: 'center'
	                        },
	                         {
	                            field: 'trainingDate',
	                            title: '入院时间',
	                            align: 'center'
	                        },
	                         {
	                            field: 'trainingType',
	                            title: '身份职称',
	                            align: 'center'
	                        },
	                        {
	                            field: 'pic',
	                            title: '个人照片',
	                            align: 'center',
	                            formatter: function(value,row,index){
	                               // return '<img  src="//www.baidu.com/img/baidu_jgylogo3.gif" class="img-rounded" >';
	                            	//return '<img src="'+getRealPath()+'/stu_img/'+value+'.jpg" class="img-rounded" >';
	                           	return '<img height="159" width="123"  src="'+getRealPath()+'/stu_img/'+value+'.jpg" class="img-rounded" >';
	                            }
	                        },
	                        {
	                            field: 'hospital',
	                            title: '所属医院',
	                            align: 'center'
	                        },
	                        {
	                            field: 'teacher',
	                            title: '导师',
	                            align: 'center'
	                        },
	                        {
	                            field: 'residentClass',
	                            title: '住院医级别',
	                            align: 'center'
	                        },
	                        {
	                            field: 'stauts',
	                            title: '规培状态',
	                            align: 'center'
	                        },
	                        {
	                            field: 'inClass',
	                            title: '轮转科室',
	                            align: 'center'
	                        }
		            	]
	      	    });
	            }
	            
	            
	            
	         // 跳转添加页面
	            function toAdd(){
	            	window.location.href="toAdd.action";
	            }
	         
	            function toGrow(){
	            	window.location.href="idGrow.action";
	            }
	         
	            function toCrh(){
	            	
	            	if(checkToCr()){
		            	var ids = $(".selected > td").eq(1).text();
		         		var i = 0;
		         		//所有.selected选择下的第二个子元素的值
		         		$(".selected td:nth-child(2)").each(function(){
		         			if(i==0){
		         				i++;
		         			}else{
								ids+="-"+$(this).text();
		         			}
		         		})
			            	window.location.href="toCrh.action?ids="+ids;
	            	}
	            }
	            
	            function toCr(){
	            	//$(".selected").eq(1).text();
	         		var ids = $(".selected > td").eq(1).text();
	         		var i = 0;
	         		//所有.selected选择下的第二个子元素的值
	         		$(".selected td:nth-child(2)").each(function(){
	         			if(i==0){
	         				i++;
	         			}else{
							ids+="-"+$(this).text();
	         			}
	         		})
	            	$.ajax({
	        			url:"stucr.action",
	        			type:"post",
	        			data:{
	        				"ids":ids
	        			},
	        			contentType:"application/x-www-form-urlencoded; charset=utf-8",
	        			async: false,
	        			beforeSend:function(){
	        				// 进行校验操作
	        				return checkToCr();
	        			},
	        			success:function(jsonObject){
	        				if(jsonObject.success){
	        					window.location.reload();
	        				}else{
	        				} 
	        			}
	        		});
	            }
	         
	         
	         // 跳转编辑页面
	            function toEdit(){
	            	if(checkToEdit()){
	            		var id = $(".selected > td").eq(1).text();
	            		//alert(id)
		            	window.location.href="toEdit.action?studentInfo.id="+id;
	            	}
	            }
	         
	         function theEdit(){
	            		var id = $("tr > td").eq(0).text();
		            	window.location.href="toEdit.action?studentInfo.id="+id;
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
							ids+="-"+$(this).text();
	         			}
	         		})
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
	           
	         //获取 路径
	         function getRealPath(){
				  //获取当前网址，如： http://localhost:8083/myproj/view/my.jsp
				  var curWwwPath=window.document.location.href;
				  //获取主机地址之后的目录，如： myproj/view/my.jsp
				  var pathName=window.document.location.pathname;
				  var pos=curWwwPath.indexOf(pathName);
				  //获取主机地址，如： http://localhost:8083
				  var localhostPaht=curWwwPath.substring(0,pos);
				  //获取带"/"的项目名，如：/myproj
				  var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
				 
				  //得到了 http://localhost:8083/myproj
				  var realPath=localhostPaht+projectName;
				 
				 return realPath;
				 
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
	        
	        // 删除校验
	        function checkToDel(){
	        	if($(".selected").length<1){
	        		alert("请选择记录！")
	        		return false
	        	}
	        	if(confirm("您确定要删除吗？")){
		        	return true
	        	}
	        }
	        // 轮转校验
	        function checkToCr(){
	        	if($(".selected").length<1){
	        		alert("请选择记录！")
	        		return false
	        	}
	        	if(confirm("确认轮转吗？")){
		        	return true
	        	}
	        	return false
	        }
	        
	        // 表格导出
	        function outExcel(){
	        	var form=$("<form>");//定义一个form表单
	        	form.attr("style","display:none");
	        	form.attr("target","");
	        	form.attr("method","post");
	        	form.attr("action","outExcel.action");
	        	var input1=$("<input>");
	        	input1.attr("type","hidden");
	        	input1.attr("name","exportData");
	        	input1.attr("value",(new Date()).getMilliseconds());
	        	$("body").append(form);//将表单放置在web中
	        	form.append(input1);

	        	form.submit();//表单提交 
	        }
	         
    	</script>
</head>
<body>
		  <h1 class="sub-header">学生信息
          </h1>
          <div class="table-responsive">
  	    	
	          	<div id="toolbar" class="btn-group">
	          	<c:if test="${operation_type!=0}">
					    <button id="btn_add" type="button" class="btn btn-primary" onclick="toAdd()">
					        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
					    </button>
					    <button id="btn_delete" type="button" class="btn btn-primary" onclick="doDel()">
					        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
					    </button>
					    <button id="btn_edit" type="button" class="btn btn-primary" onclick="toEdit()">
					        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
					    </button>
					    <button id="btn_delete" type="button" class="btn btn-primary" onclick="toCrh()">
					        <span class="glyphicon" aria-hidden="true"></span>外院轮转
					    </button>
					    <button id="btn_delete" type="button" class="btn btn-primary" onclick="toCr()">
					        <span class="glyphicon" aria-hidden="true"></span>默认轮转
					    </button>
					    <button id="btn_delete" type="button" class="btn btn-primary" onclick="toGrow()">
					        <span class="glyphicon" aria-hidden="true"></span>新增长编号
					    </button>
					    <button id="btn-export" type="button" class="btn btn-success" onclick="outExcel()">
					        <span class="glyphicon glyphicon-out" aria-hidden="true"></span>Excel导出
					    </button>
			    </c:if>
			    <c:if test="${operation_type==0}">
			    		<button id="btn_edit" type="button" class="btn btn-primary" onclick="theEdit()">
					        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
					    </button>
			    </c:if>
					    
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