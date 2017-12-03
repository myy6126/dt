<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html lang="cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>规培住院医师管理系统</title>
	<link rel="shortcut icon" href="img/dt.ico" type="image/x-icon" />
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/bootstrap/signin.css" rel="stylesheet">

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
	
	/************* title 信息   ************/
	.title_container{padding: 0 15px 0 15px; maigin:0 auto}
	.title_name{border-bottom:1px solid #0379a9;padding-bottom:4px; float: left}
	.title_ele{color:#0379a9;border-bottom:1px solid #d9d9d9; overflow: auto}
	
	
	/********* head title 信息 ************/
	.header{background-color: #fff; margin-bottom:30px;border-bottom:2px solid #0379a9}
	.title_img{ background-image: url(img/logo_title2.png);overflow: auto; width: auto;height:100px; background-repeat:no-repeat; background-position:10px 10px; background-color:#fff; }
	
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
	.footer_info{border-top:2px solid #0379a9;height:200px;margin-top:60px;background-image: url(img/footer_bg.jpg);background-repeat: repeat-x;}
	</style>
	<script src="Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	
		function login(){
			$.ajax({
				url:"login.action",
				type:"post",
				data:{
					"username":$("#username").val(),
					"password":	$("#password").val()
				},
				beforeSend:function(){
					$("#message").text("正在进行身份验证请稍候...");
					return true;
				},
				success:function(jsonObject){
					//{"success":true} {"success":false,"errorMsg":""}
					if(jsonObject.success){
						document.location='index.action';
					}else{
						$("#message").text("用户名或密码错误!");
					}
				}
			});
		}
		
		$(function(){
			if (window != top) {
				top.location.href = location.href; 
			}
			getAnn(1);
		})
		
		function getAnn(pageNo){
			$.ajax({
				url:"getAnn.action",
				type:"post",
				data:{
					"page.pageNo":pageNo,
				},
				beforeSend:function(){
					return true;
				},
				success:function(jsonObject){
					/* <div id="annPage"></div>
							<div class="next_before_box">
        					<span><a id="nextPage" onclick="nextPage()">下一页 </a></span>
        					<span><a id="nowPage"></a>/<a id="countPage"></a></span>
        					<span><a id="beforePage" onclick="beforePage()">上一页 </a></span> 
        				</div>*/
        				$("#AnnText").html("<div id='annPage'></div><div class='next_before_box'><span><a id='nextPage' onclick='nextPage()'>下一页 </a></span><span><a id='nowPage'></a>/<a id='countPage'></a></span><span><a id='beforePage' onclick='beforePage()'>上一页 </a></span>");
					if(jsonObject.success){
						$.each(jsonObject.dataAnn,function(i,n){
							$("#annPage").append("<div class='news_list'><a onclick='inAnn("+n.id+")'>"+n.title+"</a><div>"+n.publishTime+"</div></div>")
						});
						$("#nowPage").html(jsonObject.pageNo);
						$("#countPage").html(jsonObject.pageSize);
					}else{
					}
				}
			});
		}
		
		// 下一页
		function nextPage(){
		    var countPage = parseInt($("#countPage").text());
		    var nowpage = parseInt($("#nowPage").text());
			if(countPage>nowpage){
				getAnn(nowpage+1)
			}
		}
		
		// 上一页
		function beforePage(){
			if($("#nowPage").text()>1){
				getAnn($("#nowPage").text()-1)
			}
		}
		
		function inAnn(id){
			window.location.href="announcement/toAnn.action?announcement.id="+id;
		}
		
		
	
	</script>
	
  </head>

  <body>
	<div class="header">
	    <div class="container">
			 <div class="title_img"></div> <!--	系统名称 -->
		</div>
	</div>
    <div class="container">
   
		<div class="left_gg">
			<div class="title_container">
	        	<h4 class="form-signin-heading title_ele"><div class="title_name">公告信息</div></h4>
	        	<div class="news_list_box" id="AnnText">
	        		<!--<div id="annPage"></div>
	        		 <div class="news_list">
		        		<a>2016年中秋节期间我院门诊安排</a>
		        		<div>2016-12-12</div>
	        		</div>
	        		<div class="news_list">
		        		<a>2016年中秋节期间我院门诊安排</a>
		        		<div>2016-12-12</div>
	        		</div>
	        		<div class="news_list">
		        		<a>2016年中秋节期间我院门诊安排</a>
		        		<div>2016-12-12</div>
	        		</div>
	        		<div class="news_list">
		        		<a>2016年中秋节期间我院门诊安排</a>
		        		<div>2016-12-12</div>
	        		</div>
	        		<div class="news_list">
		        		<a>2016年中秋节期间我院门诊安排</a>
		        		<div>2016-12-12</div>
	        		</div>
	        		<div class="news_list">
		        		<a>2016年中秋节期间我院门诊安排</a>
		        		<div>2016-12-12</div>
	        		</div>
	        		<div class="news_list">
		        		<a>2016年中秋节期间我院门诊安排</a>
		        		<div>2016-12-12</div>
	        		</div>
	        		<div class="news_list">
		        		<a>2016年中秋节期间我院门诊安排</a>
		        		<div>2016-12-12</div>
	        		</div>
	        		<div class="news_list">
		        		<a>2016年中秋节期间我院门诊安排</a>
		        		<div>2016-12-12</div>
	        		</div>
	        		<div class="news_list">
		        		<a>2016年中秋节期间我院门诊安排</a>
		        		<div>2016-12-12</div>
	        		</div> -->
	        		<%-- <c:forEach items="${page.dataList}" var="announcement" varStatus="status">
	        			<a>${announcement.title}</a>
		        		<div>${announcement.publishTime}</div>
	        		</c:forEach> 
	        		<div class="next_before_box">
	        			<span><a id="nextPage" onclick="nextPage()">下一页 </a></span>
	        			<span><a id="nowPage"></a>/<a id="countPage"></a></span>
	        			<span><a id="beforePage" onclick="beforePage()">上一页 </a></span>
	        		</div>--%>
	        	</div>
	        </div>
		</div>
		
		<div class="right_login">
			<div class="form-signin" role="form">
	        <div class="title_container" style="padding:0">
	        	<h4 class="form-signin-heading title_ele"><div class="title_name">用户登录</div></h4>
	        </div>
	        <div class="username">
	        	<span>用户名</span>
	        	<input id="username" class="form-control" placeholder="用户名" value="" style="margin:12px 0;width:240px"></div>
	        <div class="username"><span style="margin:0">密码</span>
	        	<input id="password" type="password" class="form-control" placeholder="密码" value="" style="width:240px" ></div>
	        <div style="color: red;width: 132px;margin-left: 64px;height: 16px;overflow: hidden;" id="message"></div>
	        <div class="checkbox">
	          <label style="margin-left:52px">
	            <input type="checkbox" value="remember-me">记住登录信息
	            <a style="margin-left: 10px" href="stu/stuin.action">学生注册</a>
	          </label>
	        </div>
	        <button class="btn btn-lg btn-primary btn-block" onclick="login()">登录</button>
	      </div>
		</div>
	
    </div> <!-- /container -->


	<div class="footer_info">
	</div>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->
  </body>
		
</html>
