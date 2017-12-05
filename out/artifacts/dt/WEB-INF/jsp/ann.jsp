<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>放疗科医师培训管理系统  - 公告</title>
	<link rel="shortcut icon" href="img/dt.ico" type="image/x-icon" />
    <!-- Bootstrap core CSS -->
    <link href="../Content/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../Content/bootstrap/css/datetimepicker/bootstrap-datetimepicker.css" rel="stylesheet">
	<link href="../Content/bootstrap/css/fileinput/fileinput.css" rel="stylesheet">
    <!-- JS -->
    <script src="../Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../Content/bootstrap/js/bootstrap.min.js"></script>
	<script src="../Content/layer.js"></script>
	<script src="../Content/bootstrap/js/datetimepicker/bootstrap-datetimepicker.js"></script>
	<script src="../Content/bootstrap/js/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="../Content/bootstrap/js/fileinput/fileinput.js"></script>
	<script src="../Content/bootstrap/js/fileinput/zh.js"></script>

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!-- <script src="../../assets/js/ie-emulation-modes-warning.js"></script> -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	
	<style>
	a{text-decoration: none}
	span{display: block;}
	body{background-color: #f5f5f5}
	/************* title 信息   ************/
	.title_container{padding: 0 15px 0 15px; maigin:0 auto}
	.title_name{border-bottom:1px solid #0379a9;padding-bottom:4px; float: left}
	.title_ele{color:#0379a9;border-bottom:1px solid #d9d9d9; overflow: auto}
	
	
	/********* head title 信息 ************/
	.header{background-color: #fff; margin-bottom:30px;border-bottom:2px solid #0379a9}
	.title_img{ background-image: url(../img/logo_title.png);overflow: auto; width: auto;height:100px; background-repeat:no-repeat; background-position:10px 10px; background-color:#fff; }
	
	/********* left 公告  ************/
	.left_gg{ float:left; width:68%;padding:10px; border:1px solid #d9d9d9; margin-right:10px; height:480px}
	
	.news_list_box{margin-top:15px;margin-bottom:15px}
	.news_list{border-bottom:1px dotted #BFBFBF; width:100%;height:37px}
	.news_list a{float: left;margin-top:10px; font-family: Microsoft Yahei;font-size: 13px;color: #333;cursor: pointer}
	.news_list div{float:right;margin-top:10px; font-family: Microsoft Yahei;font-size: 13px;color: #888}
	#nextPage{cursor: pointer}
	#beforePage{cursor: pointer}
	#AnnText span{display: inline;}
	.next_before_box{width:100%; height:47px;}
	.next_before_box span{margin-top:4px;width:80px;height:37px; float:right;line-height: 37px;text-align: center;}
	/********* right 登录  ************/
	.right_login{ float:right; width:30%; border:1px solid #d9d9d9;height:312px;}
	.username {overflow: auto;}
	.username span{height:44px;width:50px;float: left;font-size: 16px;font-family: Microsoft Yahei;margin-top:12px;line-height: 44px;text-align: right;margin-right:8px}
	.username input{float:right}
	/********* foot 脚注信息 *************/
	.footer_info{border-top:2px solid #0379a9;height:200px;margin-top:60px;background-image: url(../img/footer_bg.jpg);background-repeat: repeat-x;}
	</style>
	<script type="text/javascript">
	
	
	</script>
	
  </head>

  <body>
	<div class="header">
	    <div class="container">
			<div class="title_img"></div> <!-- 	系统名称 -->
		</div>
	</div>
    <div class="container"  >
   
                <div class="panel panel-default">
										<div class="panel-heading">
											<h3 class="panel-title">
												公告信息
											</h3>
										</div>
										<div class="panel-body" style="padding-top:0px">
											<div class="news_list_box" id="AnnText">
												<a style='float:right;cursor: pointer;' onclick='javascript:history.back(-1)'>返回</a>
												<div style='text-align: center;font-weight: bold; font-size:21px'>${announcement.title}</div>
												<div style='text-align: center;color:#888;font-size:12px'>${announcement.publishTime}</div>
												<div>${announcement.text}</div>
											</div>
										</div>
									</div>
	
    </div> <!-- /container -->


	<div class="footer_info">
	</div>

  </body>
		
</html>
