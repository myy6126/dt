<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   <!--  <title>放疗科医师管理系统</title> -->
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
        .theNavLogo{width:560px;height:80px;background-image: url("img/logo_blue_index_CN_EN.png");background-repeat: no-repeat;background-position: center;}
        /********* user msg *************/
        .userInfo {background-color:#0379a9;color: #fff; height:80px; line-height:80px; margin-right: 10px;}
    </style>
</head>
<body>
   <!-- 隐藏导航  
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
			  欢迎光临,Admin
			    <a href="/Home/Logout" style="color: #fff;">
                                    <i class="icon-off"></i>
                                    退出
                                </a>
		    </div>
            </div>
        </div>
	    
    </div> -->
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
                },  */
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
        });
    </script>
    <script src="Content/ace/js/ace-extra.min.js"></script>
    <script src="Content/ace/js/ace.min.js"></script>
</body>
</html>