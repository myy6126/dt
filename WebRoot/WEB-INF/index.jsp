<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   <title>规培住院医师管理系统</title>
   <link rel="shortcut icon" href="img/dt.ico" type="image/x-icon" />
    <!--Bootstrap-->
    <link href="Content/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <!-- Font Awesome -->
    <link href="Content/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
    <!--[if IE 7]>
    <link href="/Content/font-awesome/css/font-awesome-ie7.min.css" rel="stylesheet" />
    <![endif]-->
    <link href="Content/sidebar-menu/sidebar-menu.css" rel="stylesheet" />
    <link href="Content/ace/css/ace-rtl.min.css" rel="stylesheet" />
    <link href="Content/ace/css/ace-skins.min.css" rel="stylesheet" />
    <link href="Content/toastr/toastr.min.css" rel="stylesheet" />

    <script src="Content/jquery-1.9.1.min.js"></script>
    <script src="Content/bootstrap/js/bootstrap.min.js"></script>
    <script src="Content/sidebar-menu/sidebar-menu.js"></script>
    <script src="Content/bootstrap/js/bootstrap-tab.js"></script>
    
    <!--[if lt IE 9]>
    <script src="/Scripts/html5shiv.js"></script>
    <script src="/Scripts/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        body {
            font-size: 12px;
        }

        .nav > li > a {
            padding: 5px 10px;
        }

        .tab-content {
            padding-top: 3px;
        }
        
        /********* logo  **************/
        .theNav {background-color:#0379a9; height: 80px}
        .theNavLogo{width:560px;height:80px;background-image: url("img/logo_blue_index_CN_EN2.png");background-repeat: no-repeat;background-position: center;}
        /********* user msg *************/
        .userInfo {background-color:#0379a9;color: #fff; height:80px; line-height:80px; margin-right: 10px;}
   
   	.news_list_box{margin-top:15px;margin-bottom:15px}
	.news_list{border-bottom:1px dotted #BFBFBF; width:100%;height:37px}
	.news_list a{float: left;margin-top:10px; font-family: Microsoft Yahei;font-size: 13px;color: #333;cursor: pointer}
	.news_list div{float:right;margin-top:10px; font-family: Microsoft Yahei;font-size: 13px;color: #888}
	#nextPage{cursor: pointer}
	#beforePage{cursor: pointer}
	.next_before_box{width:100%; height:47px;text-align: center;}
	.next_before_box span{margin-top:4px;width:80px;height:37px; line-height: 37px;text-align: center;}
    </style>
</head>
<body>
   <!-- 隐藏导航  -->
   <div class="navbar navbar-default theNav" id="navbar">
        <div class="navbar-container" id="navbar-container">
            <div class="navbar-header pull-left">
                <a href="#" class="navbar-brand">
						  <div class="theNavLogo"></div> 
                    <small>
                        
                    </small>
                </a>
            </div>

            <div class="navbar-header pull-right" role="navigation">
		    <div class="userInfo">
			  欢迎光临,${session_user.username}
			    <a href="logout.action" style="color: #fff;">
                                    <i class="icon-off"></i>
                                    退出
                                </a>
		    </div>
            </div>
        </div>
	    
    </div> 
    
    <div class="main-container" id="main-container">
        <div class="main-container-inner">
            <a class="menu-toggler" id="menu-toggler" href="#">
                <span class="menu-text"></span>
            </a>
            <div class="sidebar" id="sidebar">
                <ul class="nav nav-list" id="menu"></ul>
                <div class="sidebar-collapse" id="sidebar-collapse">
                    <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
                </div>
            </div>
            <div class="main-content">
                <div class="page-content">
                    <div class="row">
                        <div class="col-xs-12" style="padding-left:5px;">
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="active"><a href="#Index" role="tab" data-toggle="tab">首页</a></li>
                            </ul>
                            <div class="tab-content" style="height:865px">
                                <div role="tabpanel" class="tab-pane active" id="Index" style="height:100%">
					<h2>欢迎进入后台管理系统</h2>
					<div class="panel panel-default">
										<div class="panel-heading">
											<h3 class="panel-title">
												公告信息
											</h3>
										</div>
										<div class="panel-body" style="padding-top:0px;">
											<div class="news_list_box" id="AnnText"></div>
										</div>
									</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
        <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
            <i class="icon-double-angle-up icon-only bigger-110"></i>
        </a>
    </div>
    <script type="text/javascript">
        //toastr.options.positionClass = 'toast-bottom-right';
           
        
        
            $(function () {
            	getMenu();
            });
           <%--  <%=session.getAttribute("code") %> --%>
            function getMenu(){ 
				$.ajax({
					url:"getMenu.action",
					type:"post",
					contentType:"application/x-www-form-urlencoded; charset=utf-8",
					async: false,
					beforeSend:function(){
						return true;
					},
					success:function(jsonObject){
						if(jsonObject.success){
							theMenu(jsonObject.menu)
						}else{
							alert("false");
						} 
					}
				});
			} 
            
            function theMenu(theData){
            	$('#menu').sidebarMenu({
                    data: theData
                });
            	getAnn(1);
            }
            
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
            				$("#AnnText").html("<div id='annPage'></div><div class='next_before_box'><span><a id='beforePage' onclick='beforePage()'>上一页 </a></span><span><a id='nowPage'></a>/<a id='countPage'></a></span><span><a id='nextPage' onclick='nextPage()'>下一页 </a></span>");
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
    			
    			$.ajax({
    				url:"inAnn.action",
    				type:"post",
    				async: true,
    				data:{
    					"annId":id,
    				},
    				beforeSend:function(){
    					return true;
    				},
    				success:function(jsonObject){
    					if(jsonObject.success){
    						$("#AnnText").html("<a style='float:right;cursor: pointer;' onclick='getAnn(1)'>返回</a><div style='text-align: center;font-weight: bold;font-size:21px'>"+jsonObject.title+"</div><div style='text-align: center;color:#888;font-size:12px'>"+jsonObject.time+"</div><div>"+jsonObject.text+"</div>");
    					}else{
    					}
    				}
    			});
    		}
            
            
            
            
            /* $(function () {
                $('#menu').sidebarMenu({
                    data: [
                         /*   {
                        id: '1',
                        text: '系统设置',
                        icon: 'icon-cog',
                        menus: [{
                            id: '11',
                            text: '编码管理',
                            url: '/CodeType/Index'
                        }]
                    },  
                    {
                        id: '1',
                        text: '基础数据',
                        icon: 'icon-leaf',
                        menus: [{
                            id: '11',
                            text: '学生管理',
                            url: '/stu/index.action'
                        }]
                    }, {
                        id: '2',
                        text: '权限管理',
                        icon: 'icon-user',
                        menus: [{
                            id: '21',
                            text: '用户管理',
                          //  icon: 'icon-user',
                            url: '/user/index.action'
                        }, {
                            id: '22',
                            text: '角色管理',
                          //  icon: 'icon-apple',
                            url: '/role/index.action'
                        }
                         ,{
                            id: '23',
                            text: '权限管理',
                           // icon: 'icon-list',
                            url: '/permission/index.action'
                        }
                        ]
                    }
                    , 
                    {
                        id: '3',
                        text: '公告管理',
                        icon: 'icon-envelope',
                        menus: [{
                            id: '31',
                            text: '公告管理',
                            url: '/announcement/index.action'
                        }]
                    },
                    {
                        id: '4',
                        text: '日志管理',
                        icon: 'icon-list',
                        menus: [{
                            id: '41',
                            text: '日志管理',
                            url: '/operationlog/index.action'
                        }]
                    } 
                    ]
                });
            }); */
    </script>
    <script src="Content/ace/js/ace-extra.min.js"></script>
    <script src="Content/ace/js/ace.min.js"></script>
</body>
</html>