<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html lang="cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>放疗科医师培训管理系统 - 学生入口</title>
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
	var timestamp ="";
	$(function(){
		
		//初始化日历
		initCalendar()
		// 初始化
		initFileInput()
		getAllteacher()
		$("#stu_cometime").val(getNowDate());
		timestamp = Date.parse(new Date());
	})
	
	function getNowDate(){
	    var myDate = new Date(); 
	    myDate.getYear();        //获取当前年份(2位) 
	    myDate.getFullYear();    //获取完整的年份(4位,1970-????) 
	    myDate.getMonth();       //获取当前月份(0-11,0代表1月) 
	    myDate.getDate();        //获取当前日(1-31) 
	    myDate.getDay();         //获取当前星期X(0-6,0代表星期天) 
	    myDate.getTime();        //获取当前时间(从1970.1.1开始的毫秒数) 
	    myDate.getHours();       //获取当前小时数(0-23) 
	    myDate.getMinutes();     //获取当前分钟数(0-59) 
	    myDate.getSeconds();     //获取当前秒数(0-59) 
	    myDate.getMilliseconds();    //获取当前毫秒数(0-999) 
	    myDate.toLocaleDateString();     //获取当前日期 
	    var mytime=myDate.toLocaleTimeString();     //获取当前时间 
	    myDate.toLocaleString( );        //获取日期与时间  
	    
	    var year = myDate.getFullYear();
	    var month
	    var mon = myDate.getMonth() + 1; 
	    if(mon<10){
	    	month = "0"+mon; 
	    }else{
	    	month = mon+"";
	    }
	    
	    var day = myDate.getDate();
	    if(day<10){
	    	day = "0"+day;
	    }
	    
	    return year+"-"+month+"-"+day
	}
	
	function getAllteacher(){
		$.ajax({
			url:"getAllteacher.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			beforeSend:function(){
				return true;
			},
			success:function(jsonObject){
				if(jsonObject.success){
					$("#stu_tutor").append("<option value=''></option>")
					$.each(jsonObject.tList,function(i,n){
						$("#stu_tutor").append("<option value="+n.id+">"+n.name+"</option>")
					});
				}else{
				} 
				
			}
		});
	}
	
	// 日历功能
	function initCalendar(){
		$('.form_date').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  true,
			autoclose: true,
			todayHighlight: true,
			startView: 2,
			minView: 2,
			forceParse: true
	    });
	}
	
	function stu_submit(){
		
		if(check()){
			//提交照片
			$('#pic_upload').fileinput('upload');
			//提交数据
			stuInfo_submit()
		}
	}
	
	function stuInfo_submit(){
		
		var theSex = $("input[name='sexradio']:checked").val();
		
		$.ajax({
			url:"add.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{
				/* "studentInfo.id":$("#stu_no").val(), */
				"studentInfo.name":$("#stu_name").val(),
				"studentInfo.Birth":$("#stu_birth").val(),
				"studentInfo.Sex":theSex,
				"studentInfo.InTraningDate":$("#stu_cometime").val(),
				"studentInfo.trainingType":$("#stu_occupation").val(),
				"studentInfo.HospitalID":$("#stu_hospital").val(),
				"studentInfo.TeacherID":$("#stu_tutor").val(),
				//"studentInfo.ResidentClass":$("#stu_residentClass").val(),
				"studentInfo.studentStatus":$("#stu_trainStutas").val(),
				"studentInfo.personPic":timestamp
			},
			beforeSend:function(){
				return true;
			},
			success:function(jsonObject){
				if(jsonObject.success){
					alert("注册成功!")
					toStuInfo();
				}else{
					alert("注册失败!")
				} 
				
			}
		});
	}
	
	// 上传功能
	function initFileInput(){
		 $("#pic_upload").fileinput({
		        language: 'zh', //设置语言
		        uploadUrl: "uploadImage.action", //上传的地址
		        allowedFileExtensions: ['jpg'],//接收的文件后缀
		        uploadAsync: true, //默认异步上传
		        showUpload: false, //是否显示上传按钮
		        showRemove : false, //显示移除按钮
		        showPreview : true, //是否显示预览
		        showCaption: false,//是否显示标题
		        dropZoneEnabled: false,//是否显示拖拽区域
		        //minImageWidth: 50, //图片的最小宽度
		        //minImageHeight: 50,//图片的最小高度
		        //maxImageWidth: 1000,//图片的最大宽度
		        //maxImageHeight: 1000,//图片的最大高度
		        //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
		        //minFileCount: 0,
		        maxFileCount: 1, //表示允许同时上传的最大文件个数
		        enctype: 'multipart/form-data',
		       // validateInitialCount:true,
		       // msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
		        //传递参数
	             uploadExtraData:function (){
	                 return{"studentImageName": timestamp}
	             }
		 });
	}
	
	// 校验
	function check(){
		// 照片
		if($("#pic_upload").val()==""){
			alert("请上传照片!")
			return false;
		}
		return true;
	}
	
	function toStuInfo(){
  	  window.location.href="index.action"
  }
	</script>
	
  </head>

  <body>
	<div class="header">
	    <div class="container">
			<div class="title_img"></div> <!-- 	系统名称 -->
		</div>
	</div>
    <div class="container"  >
   
                <div class="form-horizontal"  role="form">
                <div style = "width:86%"  style="" >
				  <!-- <div class="form-group">
				    <label class="col-sm-2 control-label" for="stu_no">记录编号</label>
					    <div class="col-sm-6">
						    <input id="stu_no" type="text" class="form-control" placeholder="请输入编号">
					    </div>
				  </div> -->
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_name">姓名</label>
					    <div class="col-sm-6">
						    <input id="stu_name" type="text" class="form-control" placeholder="请输入姓名">
					    </div>
				    </div>
				    <div class="form-group">
					    <label for="stu_birth" class="col-sm-2 control-label">出生年月</label>
					    <div class="col-sm-6">
	                		<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm" data-link-field="stu_birth" data-link-format="yyyy-mm">
		                    	<input id="stu_birth" class="form-control" size="16" type="text" value="" readonly>
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                    	<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    		</div>
                		</div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_sex_boy">性别</label>
					    <div class="col-sm-6">
						    	<label class="checkbox-inline">
									<input type="radio" name="sexradio" id="stu_sex_boy" value="男" checked="checked"> 男
								</label>
								<label class="checkbox-inline">
									<input type="radio" name="sexradio" id="stu_sex_girl"  value="女"> 女
								</label>
					    </div>
				    </div>
				    <div class="form-group">
					    <label for="stu_cometime" class="col-sm-2 control-label">入院时间</label>
					    <div class="col-sm-6">
	                		<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="stu_cometime" data-link-format="yyyy-mm-dd">
		                    	<input id="stu_cometime" class="form-control" size="16" type="text" value="" readonly>
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                    	<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    		</div>
                		</div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_occupation">身份职称</label>
					    <div class="col-sm-6">
						    <select class="form-control" id="stu_occupation">
						      <option>研究生</option>
						      <option>单位规培住院医</option>
						      <option>社会规培住院医</option>
						    </select>
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_hospital">所属医院</label>
					    <div class="col-sm-6">
						    <input id="stu_hospital" type="text" class="form-control" placeholder="所属医院">
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_tutor">导师</label>
					    <div class="col-sm-6">
						    <select class="form-control" id="stu_tutor">
						    </select>
					    </div>
				    </div>
				   <!--  <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_residentClass">住院医级别</label>
					    <div class="col-sm-6">
						     <select class="form-control" id="stu_residentClass">
						      <option>1</option>
						      <option>2</option>
						      <option>3</option>
						    </select>
					    </div>
				    </div> -->
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_trainStutas">规培状态</label>
					    <div class="col-sm-6">
					    	<select class="form-control" id="stu_trainStutas">
						      <option value="1">未完成</option>
						    </select>
					    </div>
				    </div>
				      <div class="form-group">
					    <label class="col-sm-2 control-label" for="pic_upload">个人照片</label>
					    <div class="col-sm-6">
							<input id="pic_upload" type="file" name="uploadImageFile"  class="projectfile">
					    </div>
				     </div>
				   </div>
				     <center>
					     <button onclick="stu_submit()" class="btn btn-default">提交</button>
					     <button onclick="toStuInfo()" class="btn btn-default">返回</button>
				     </center>
			    </div>
	
    </div> <!-- /container -->


	<div class="footer_info">
	</div>

  </body>
		
</html>
