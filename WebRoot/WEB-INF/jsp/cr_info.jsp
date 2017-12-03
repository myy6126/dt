<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>轮转信息</title>
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
	            	
	            	var userId = "<%=session.getAttribute("username")%>"; 
	            	var id = "<%=session.getAttribute("operation_type")%>"; 
	            	var rc = "<%=session.getAttribute("stu_rc")%>"; 
	            	if(id=="0"){
	            		getCrInfoById();
	            	}else{
	            		getAllCrInfo();
	            	}
	            });
	            
	            function getAllCrInfo(){
	            	$('#table').bootstrapTable('destroy')
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
	                  undefinedText:'-',
	                  //分页方式：client客户端分页，server服务端分页（*）
	                  //onlyInfoPagination:true,
	                  sidePagination: "client",
	                  search:true,
	                  ajaxOptions:{
	                  	async:false
	                  },
	                  pageSize:100,
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
	            	    	visible : true,
                            field: 'stuid',
                            title: '学生编号',
                            align: 'center'
                        },
                         {
                            field: 'name',
                            title: '学生姓名',
                            align: 'center'
                        },
                        {
                            field: 'year',
                            title: '年',
                            align: 'center'
                        },
                         {
                            field: 'jan',
                            title: '一月',
                            align: 'center'
                        },
                         {
                            field: 'feb',
                            title: '二月',
                            align: 'center'
                        },
                         {
                            field: 'mar',
                            title: '三月',
                            align: 'center'
                        },
                         {
                            field: 'apr',
                            title: '四月',
                            align: 'center'
                        },
                        {
                            field: 'may',
                            title: '五月',
                            align: 'center',
                        },
                        {
                            field: 'jun',
                            title: '六月',
                            align: 'center'
                        },
                        {
                            field: 'jul',
                            title: '七月',
                            align: 'center'
                        },
                        {
                            field: 'aug',
                            title: '八月',
                            align: 'center'
                        },
                        {
                            field: 'sep',
                            title: '九月',
                            align: 'center'
                        },
                        {
                            field: 'oct',
                            title: '十月',
                            align: 'center'
                        },
                        {
                            field: 'nov',
                            title: '十一月',
                            align: 'center'
                        },
                        {
                            field: 'dec',
                            title: '十二月',
                            align: 'center'
                        }
	            	]
	      	    });
	            	for (var i=0; i<100; i++)
					  {
						if(i%4==0){
			            	$('#table').bootstrapTable('mergeCells', {index:i,field: 'name', colspan: 0,rowspan: 4});
			            	$('#table').bootstrapTable('mergeCells', {index:i,field: 'stuid', colspan: 0,rowspan: 4});
						}
					  }
	            }
	            
	            function rowspan(){
	            	$.ajax({
	        			url:"getCount.action",
	        			type:"post",
	        			contentType:"application/x-www-form-urlencoded; charset=utf-8",
	        			async: false,
	        			beforeSend:function(){
	        				return true;
	        			},
	        			success:function(jsonObject){
	        				if(jsonObject.success){
	        					var count= jsonObject.getCount;
	        					for (var i=0; i<count; i++)
	        					  {
	        						if(i%4==0){
						            	$('#table').bootstrapTable('mergeCells', {index:i,field: 'name', colspan: 0,rowspan: 4});
	        						}
	        					  }
	        				}else{
	        				} 
	        			}
	        		});
	            }
	            
	            
	            function getCrInfoById(){
	            	$('#table').bootstrapTable('destroy')
	            	$('#table').bootstrapTable({
	            	  clickToSelect:false,
	            	  toolbar: '#toolbar',
	      	    	  //请求方法
	                  method: 'post',
	                   //是否显示行间隔色
	                  striped: false,
	                  ajaxOptions:{
		                  	async:false
		                  },
	                  //uniqueId:'id',
	                  //可供选择的每页的行数（*）    
	                  //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
	                  url: "getById.action",
	                  //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
	                  ////查询参数,每次调用是会带上这个参数，可自定义          
	                  undefinedText:'-',
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
	                            field: 'year',
	                            title: '年份',
	                            align: 'center'
	                        },
	                         {
	                            field: 'jan',
	                            title: '一月',
	                            align: 'center'
	                        },
	                         {
	                            field: 'feb',
	                            title: '二月',
	                            align: 'center'
	                        },
	                         {
	                            field: 'mar',
	                            title: '三月',
	                            align: 'center'
	                        },
	                         {
	                            field: 'apr',
	                            title: '四月',
	                            align: 'center'
	                        },
	                        {
	                            field: 'may',
	                            title: '五月',
	                            align: 'center',
	                        },
	                        {
	                            field: 'jun',
	                            title: '六月',
	                            align: 'center'
	                        },
	                        {
	                            field: 'jul',
	                            title: '七月',
	                            align: 'center'
	                        },
	                        {
	                            field: 'aug',
	                            title: '八月',
	                            align: 'center'
	                        },
	                        {
	                            field: 'sep',
	                            title: '九月',
	                            align: 'center'
	                        },
	                        {
	                            field: 'oct',
	                            title: '十月',
	                            align: 'center'
	                        },
	                        {
	                            field: 'nov',
	                            title: '十一月',
	                            align: 'center'
	                        },
	                        {
	                            field: 'dec',
	                            title: '十二月',
	                            align: 'center'
	                        }
		            	]
	      	    });
	            	$('#table').bootstrapTable('mergeCells', {index:0,field: 'name', colspan: 0,rowspan: 4});
	            }
	            
	            /* function getTitle(rcId,type){
	            	$.ajax({
	        			url:"getTitle.action",
	        			type:"post",
	        			contentType:"application/x-www-form-urlencoded; charset=utf-8",
	        			async: false,
	        			data:{
	        				"rcId":rcId
	        			},
	        			beforeSend:function(){
	        				return true;
	        			},
	        			success:function(jsonObject){
	        				if(jsonObject.success){
	        					   getOneAll(jsonObject.listResult,rcId)
	        				}else{
	        					alert("false");
	        				} 
	        			}
	        		});
	            }
	            
	            function getTitleOne(rcId,userId){
	            	$.ajax({
	        			url:"getTitle.action",
	        			type:"post",
	        			contentType:"application/x-www-form-urlencoded; charset=utf-8",
	        			async: false,
	        			data:{
	        				"rcId":rcId
	        			},
	        			beforeSend:function(){
	        				return true;
	        			},
	        			success:function(jsonObject){
	        				if(jsonObject.success){
	        					getOne(jsonObject.listResult,userId)
	        				}else{
	        					alert("false");
	        				} 
	        			}
	        		});
	            } */
	            
	            function getOneAll(theColumns,rcId){
	            	var theId = rcId;
	            	$('#table').bootstrapTable('destroy')
	            	$('#table').bootstrapTable({
	            	  clickToSelect:true,
	            	  toolbar: '#toolbar',
	      	    	  //请求方法
	                  method: 'get',
	                   //是否显示行间隔色
	                  striped: false,
	                  //uniqueId:'id',
	                  //可供选择的每页的行数（*）    
	                  //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
	                  url: "getClass.action",
	                  //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
	                  ////查询参数,每次调用是会带上这个参数，可自定义          
	                  undefinedText:'暂无数据',
	                  //分页方式：client客户端分页，server服务端分页（*）
	                  //onlyInfoPagination:true,
	                  sidePagination: "client",
	                  queryParams : function(params) {
	                     return {
	                   		  		theId: theId
	                         	 };
	                  },	
	                  search:true,
	                  pagination:true,
	                  //client 要设置 responseHandler
	                  responseHandler: function(data){
	                	     return data.rows;
	                	},
	            	  columns:theColumns
	      	    });
	            }
	            
	            function getOne(theColumns,stuId){
	            	$('#table').bootstrapTable('destroy')
	            	$('#table').bootstrapTable({
	            	  clickToSelect:true,
	            	  toolbar: '#toolbar',
	      	    	  //请求方法
	                  method: 'get',
	                   //是否显示行间隔色
	                  striped: false,
	                  //uniqueId:'id',
	                  //可供选择的每页的行数（*）    
	                  //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
	                  url: "getOne.action",
	                  //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
	                  ////查询参数,每次调用是会带上这个参数，可自定义          
	                  undefinedText:'暂无数据',
	                  //分页方式：client客户端分页，server服务端分页（*）
	                  //onlyInfoPagination:true,
	                  sidePagination: "client",
	                  queryParams : function(params) {
	                     return {
	                    	 stuId: stuId
	                         	 };
	                  },	
	                  search:true,
	                  pagination:true,
	                  //client 要设置 responseHandler
	                  responseHandler: function(data){
	                	     return data.rows;
	                	},
	            	  columns:theColumns
	      	    });
	            }
	            
	         // 跳转添加页面
	            function toAdd(){
	            	window.location.href="toAdd.action";
	            }
	         // 跳转添加页面
	            function toConfig(){
	            	window.location.href="toConfig.action";
	            }
	         
	         // 跳转编辑页面
	            function toEdit(){
	            	if(checkToEdit()){
	            		var id = $(".selected > td").eq(1).text();
	            		//alert(id)
		            	window.location.href="toEdit.action?theId="+id;
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
							ids+="-"+$(this).text();
	         			}
	         		})
	         		$.ajax({
	        			url:"delCr.action",
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
				 
				 return localhostPaht;
				 
			} 
	         
	         
	        function checkToEdit(){
	        	if($(".selected").length>1){
	        		alert("只能选择一条记录")
	        		return false
	        	}else if($(".selected").length<1){
	        		alert("请选择一条记录")
	        		return false
	        	}
	        	return true
	        }
	        
	        function checkToDel(){
	        	if($(".selected").length<1){
	        		alert("请选择记录！")
	        		return false
	        	}
	        	if(confirm("确认删除吗？")){
		        	return true
	        	}
	        }
	        
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
		  <h1 class="sub-header">轮转信息
          </h1>
          <div class="table-responsive">
  	    	<c:if test="${operation_type!=0}">
	          	<div id="toolbar" class="btn-group">
					    <button id="btn_edit" type="button" class="btn btn-primary" onclick="toConfig()">
					        <span class="glyphicon" aria-hidden="true"></span>轮转配置
					    </button>
					    <button id="btn_edit" type="button" class="btn btn-primary" onclick="toEdit()">
					        <span class="glyphicon" aria-hidden="true"></span>编辑
					    </button>
					    <button id="btn_edit" type="button" class="btn btn-primary" onclick="doDel()">
					        <span class="glyphicon" aria-hidden="true"></span>删除
					    </button>
					    <button id="btn-export" type="button" class="btn btn-success" onclick="outExcel()">
					        <span class="glyphicon glyphicon-out" aria-hidden="true"></span>Excel导出
					    </button>
					    <!-- <button id="btn_edit" type="button" class="btn btn-primary" onclick="getTitle(1,1)">
					        <span class="glyphicon" aria-hidden="true"></span>一年级
					    </button>
					    <button id="btn_edit" type="button" class="btn btn-primary" onclick="getTitle(2,1)">
					        <span class="glyphicon" aria-hidden="true"></span>二年级
					    </button>
					    <button id="btn_edit" type="button" class="btn btn-primary" onclick="getTitle(3,1)">
					        <span class="glyphicon" aria-hidden="true"></span>三年级
					    </button> -->
				</div>
		    </c:if>
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
                drop table announcement_table;
				drop table clinicalrotation_table;
				drop table dt_config_table;
				drop table hospital_table;
				drop table operation_log_table;
				drop table paper_table;
				drop table permission_table;
				drop table role_permission_relation_table;
				drop table role_table;
				drop table score_table;
				drop table studentinfo_table;
				drop table teacherinfo_table;
				drop table user_role_relation_table;
				drop table user_table;
              </thead> -->
            </table>
          </div>
          
</body>
</html>