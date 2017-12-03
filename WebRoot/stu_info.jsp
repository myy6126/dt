<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>学生管理页面</title>
	
    <!-- Bootstrap core CSS -->
    <link href="../../css/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="../../css/bootstrap/dashboard.css" rel="stylesheet">
	<link href="../../css/bootstrap/bootstrap-table.css" rel="stylesheet">
    <!-- JS -->
    <script src="../../js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../../js/bootstrap/bootstrap.js"></script>
	<script src="../../js/bootstrap/bootstrap-table.js"></script>
	<script src="../../js/bootstrap/bootstrap-table-zh-CN.js"></script>

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!-- <script src="../../assets/js/ie-emulation-modes-warning.js"></script> -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <style>
    	/** top logo a **/
    	.logimg{
    				background-image: url("img/logo_common.jpg");
    				background-repeat: no-repeat;
    				background-position: center;
    				width: 362px; 
    				padding-left: 380px; 
    				margin-top: 4px
    			}
    	
    	/*****body***/
    	
    	body{ background-color: #f5f5f5}
    	/*****右下标题*****/
    	.head_info_btn{ float: right;}
    </style>
    
    
    <script type="text/javascript">
    
    var data = [];
    function getAllInfo(){ 
		$.ajax({
			url:"stu/getAll.action",
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
	 
    
	
	            $(function () {
	            	getAllInfo();
	                $('#table').bootstrapTable({
	                	toolbar: "#tbar",
	                	pagination: true,                    //是否显示分页（*）
	                    sortable: false,                     //是否启用排序
	                    sortOrder: "asc",                   //排序方式
	                    queryParams: "",//传递参数（*）
	                    sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
	                    pageNumber:1,                       //初始化加载第一页，默认第一页
	                    pageSize: 50,                       //每页的记录行数（*）
	                    pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
	                    strictSearch: true,
	                    height: 460,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	                    clickToSelect: true,                //是否启用点击选中行
	                    uniqueId: "id",                     //每一行的唯一标识，一般为主键列
	                    data: data
	                });
	            });
    	</script>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand logimg"></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="#">Help</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">学生管理</a></li>
            <li><a href="#">导师管理</a></li>
            <li><a href="#">成绩管理</a></li>
            <li><a href="#">医院管理</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="">权限管理</a></li>
            <li><a href="">公告管理</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
            <li><a href="">More navigation</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="">Nav item again</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="sub-header">学生信息
          		<div class="head_info_btn">
		          	<button type="button" class="btn btn-sm btn-default">添加</button>
		          	<button type="button" class="btn btn-sm btn-default">修改</button>
		          	<button type="button" class="btn btn-sm btn-default">删除</button>
          		</div>
          </h1>
          <div class="table-responsive">
          	<div id="tbar"></div>
            <table class="table table-striped" id="table">
              <thead>
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
              </thead>
            </table>
          </div>
          
        </div>
      </div>
    </div>

  </body>
</html>
