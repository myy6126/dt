<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>学生信息</title>
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
	
	<script type="text/javascript">
    
	$(function(){
		
		//初始化日历
		initCalendar()
		// 初始化
		initFileInput()
		getAllteacher()
		
	})
	
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
		//提交照片
		if($("#pic_upload").val()==null||$("#pic_upload").val()==""){
			stuInfo_submit()
		}else{
			//check()
			$('#pic_upload').fileinput('upload');
			stuInfo_submit()
		}	
		//提交数据
	}
	
	function clearpic(){
		$("$rowpic").remove();
	}
	
	function stuInfo_submit(){
		
		var theSex = $("input[name='sexradio']:checked").val();

		$.ajax({
			url:"doEdit.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{
				"studentInfo.id":$("#stu_no").val(),
				"studentInfo.name":$("#stu_name").val(),
				"studentInfo.Birth":$("#stu_birth").val(),
				"studentInfo.Sex":theSex,
				"studentInfo.InTraningDate":$("#stu_cometime").val(),
				"studentInfo.trainingType":$("#stu_occupation").val(),
				"studentInfo.HospitalID":$("#stu_hospital").val(),
				"studentInfo.TeacherID":$("#stu_tutor").val(),
				//"studentInfo.ResidentClass":$("#stu_residentClass").val(),
				"studentInfo.studentStatus":$("#stu_trainStutas").val(),
				"studentInfo.personPic":$("#picId").val()
			},
			beforeSend:function(){
				return true;
			},
			success:function(jsonObject){
				if(jsonObject.success){
					alert("修改成功!")
					toStuInfo();
				}else{
					alert("修改失败!")
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
	                 return{"studentImageName": $("#picId").val()}
	             }
		 });
	}
	
	// 校验
	function check(){
		// 照片
		if($("#pic_upload").val()==""){
			alert("请上传照片!")
			return;
		}
	}
	
	function toStuInfo(){
  	  window.location.href="index.action"
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
					$.each(jsonObject.tList,function(i,n){
						var tid = $("#teachId").val();
						if(tid==n.id){
							$("#stu_tutor").append("<option selected='selected' value="+n.id+">"+n.name+"</option>")
						}else{
							$("#stu_tutor").append("<option value="+n.id+">"+n.name+"</option>")
						}
					});
				}else{
				} 
				
			}
		});
	}
	
	
	
    	</script>
</head>
<body>
<input id="teachId" type="hidden" value="${studentInfo.teacherID}">
                <legend>&nbsp;&nbsp;编辑信息</legend>
                <div class="form-horizontal" role="form">
                <div style = "width:86%">
                <c:if test="${operation_type!=0}">
				  <div class="form-group">
				    <label class="col-sm-2 control-label" for="stu_no">记录编号</label>
					    <div class="col-sm-6">
						    <input id="stu_no" type="text" class="form-control" value="${studentInfo.id}" placeholder="请输入编号">
					    </div>
				  </div>
                </c:if>
                <c:if test="${operation_type==0}">
	                <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_no">记录编号</label>
						    <div class="col-sm-6">
							    <input id="stu_no" type="text" class="form-control" readonly="readonly" value="${studentInfo.id}" placeholder="请输入编号">
						    </div>
					</div>
                </c:if>
				  
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_name">姓名</label>
					    <div class="col-sm-6">
						    <input id="stu_name" type="text" value="${studentInfo.name}" class="form-control" placeholder="请输入姓名">
					    </div>
				    </div>
				    <div class="form-group">
					      <label for="stu_birth" class="col-sm-2 control-label">出生年月</label>
					    <div class="col-sm-6">
	                		<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm" data-link-field="stu_birth" data-link-format="yyyy-mm">
		                    	<input id="stu_birth" class="form-control" size="16" type="text" value="${studentInfo.birth}" readonly>
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                    	<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    		</div>
                		</div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_sex_boy">性别</label>
					    <div class="col-sm-6">
					    		<c:if test="${studentInfo.sex=='男'}">
							    	<label class="checkbox-inline">
										<input type="radio" name="sexradio"  id="stu_sex_boy" value="男" checked> 男
									</label>
					    		</c:if>
					    		<c:if test="${studentInfo.sex!='男'}">
							    	<label class="checkbox-inline">
										<input type="radio" name="sexradio"  id="stu_sex_boy" value="男"> 男
									</label>
					    		</c:if>
					    		<c:if test="${studentInfo.sex=='女'}">
							    	<label class="checkbox-inline">
										<input type="radio" name="sexradio" id="stu_sex_girl"  value="女" checked> 女
									</label>
					    		</c:if>
					    		<c:if test="${studentInfo.sex!='女'}">
							    	<label class="checkbox-inline">
										<input type="radio" name="sexradio" id="stu_sex_girl"  value="女"> 女
									</label>
					    		</c:if>
					    </div>
				    </div>
				    
				    <c:if test="${operation_type!=0}">
					    <div class="form-group">
						    <label for="stu_cometime" class="col-sm-2 control-label">入院时间</label>
						    <div class="col-sm-6">
		                		<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="stu_cometime" data-link-format="yyyy-mm-dd">
			                    	<input id="stu_cometime" class="form-control" size="16" type="text"  value="${studentInfo.inTraningDate}" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			                    	<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
	                    		</div>
	                		</div>
					    </div>
				    </c:if>
				    <c:if test="${operation_type==0}">
					    <div class="form-group">
						    <label for="stu_cometime" class="col-sm-2 control-label">入院时间</label>
						    <div class="col-sm-6">
		                		<div class="input-group date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="stu_cometime" data-link-format="yyyy-mm-dd">
			                    	<input id="stu_cometime" class="form-control" size="16" type="text"  value="${studentInfo.inTraningDate}" readonly>
	                    		</div>
	                		</div>
					    </div>
				    </c:if>
				    
					    <div class="form-group">
						    <label class="col-sm-2 control-label" for="stu_occupation">身份职称</label>
						    <div class="col-sm-6">
							    <select class="form-control" id="stu_occupation">
						    	 <c:if test="${studentInfo.trainingType==研究生}">ResidentClass
								      <option selected="selected">研究生</option>
						    	 </c:if>
						    	 <c:if test="${studentInfo.trainingType==单位规培住院医}">ResidentClass
								      <option selected="selected">单位规培住院医</option>
						    	 </c:if>
						    	 <c:if test="${studentInfo.trainingType==社会规培住院医}">ResidentClass
								      <option selected="selected">社会规培住院医<</option>
						    	 </c:if>
						    	 <c:if test="${studentInfo.trainingType!=研究生}">ResidentClass
								      <option>研究生</option>
						    	 </c:if>
						    	 <c:if test="${studentInfo.trainingType!=单位规培住院医}">ResidentClass
								      <option>单位规培住院医</option>
						    	 </c:if>
						    	 <c:if test="${studentInfo.trainingType!=社会规培住院医}">ResidentClass
								      <option>社会规培住院医</option>
						    	 </c:if>
							    </select>
						    </div>
					    </div>
				     
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_hospital">所属医院</label>
					    <div class="col-sm-6">
						    <input id="stu_hospital" type="text" class="form-control" placeholder="所属医院" value="${studentInfo.hospitalID}">
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_tutor">导师</label>
					    <div class="col-sm-6">
						    <select class="form-control" id="stu_tutor">
						    	<option value=""></option>
						    </select>
					    </div>
				    </div>
				   <%--  <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_residentClass">住院衣级别</label>
					    <div class="col-sm-6">
					    <c:if test="${operation_type!=0}">
						     <select class="form-control" id="stu_residentClass">
						     <c:if test="${studentInfo.residentClass==1}">ResidentClass
							      <option selected="selected">1</option>
					    	 </c:if>
						     <c:if test="${studentInfo.residentClass==2}">ResidentClass
							      <option selected="selected">2</option>
					    	 </c:if>
						     <c:if test="${studentInfo.residentClass==3}">ResidentClass
							      <option selected="selected">3</option>
					    	 </c:if>
						     <c:if test="${studentInfo.residentClass!=1}">ResidentClass
							      <option>1</option>
					    	 </c:if>
						     <c:if test="${studentInfo.residentClass!=2}">ResidentClass
							      <option>2</option>
					    	 </c:if>
						     <c:if test="${studentInfo.residentClass!=3}">ResidentClass
							      <option>3</option>
					    	 </c:if>
						    </select>
					    </c:if>
					    
					    <c:if test="${operation_type==0}">
						     <input id="stu_residentClass" type="text" class="form-control" placeholder="" readonly="readonly" value="${studentInfo.residentClass}">
					    </c:if>
					    </div>
				    </div> --%>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_trainStutas">规培状态</label>
					    <div class="col-sm-6">
					    <c:if test="${operation_type!=0}">
					    	<select class="form-control" id="stu_trainStutas">
					    	<c:if test="${studentInfo.studentStatus=='1'}">ResidentClass
							      <option selected="selected" value="1">未完成</option>
					    	 </c:if>
					    	<c:if test="${studentInfo.studentStatus=='2'}">ResidentClass
							      <option selected="selected" value="2">正在轮转</option>
					    	 </c:if>
					    	<c:if test="${studentInfo.studentStatus=='3'}">ResidentClass
							      <option selected="selected" value="3">已完成</option>
					    	 </c:if>
					    	<c:if test="${studentInfo.studentStatus!='1'}">ResidentClass
							      <option value="1">未完成</option>
					    	 </c:if>
					    	<c:if test="${studentInfo.studentStatus!='2'}">ResidentClass
							      <option value="2">正在轮转</option>
					    	 </c:if>
					    	<c:if test="${studentInfo.studentStatus!='3'}">ResidentClass
							      <option value="3">已完成</option>
					    	 </c:if>
						    </select>
					    </c:if>
					    <c:if test="${operation_type==0}">
					   		<c:if test="${studentInfo.studentStatus=='1'}">
							    	<input  type="text" class="form-control" placeholder="" readonly="readonly" value="未完成">
					    	 		<input type="hidden" id="stu_trainStutas" value="1">
					    	 </c:if>
					    	<c:if test="${studentInfo.studentStatus=='2'}">
							    	<input type="text" class="form-control" placeholder="" readonly="readonly" value="正在轮转">
					    	 		<input type="hidden" id="stu_trainStutas" value="2">
					    	 </c:if>
					    	<c:if test="${studentInfo.studentStatus=='3'}">
							    	<input type="text" class="form-control" placeholder="" readonly="readonly" value="已完成">
					    	 		<input type="hidden" id="stu_trainStutas" value="3">
					    	 </c:if>
					    </c:if>
					    </div>
				    </div>
				    <input id="picId" type="hidden" value="${studentInfo.personPic}">
				     <div class="form-group">
					    <label class="col-sm-2 control-label" for="pic_upload">个人照片</label>
					    <div class="col-sm-6">
							<input id="pic_upload" type="file" name="uploadImageFile" onchange="clearpic()" class="projectfile">
					    </div>
				     </div>
				   </div>
				     <center>
					     <button onclick="stu_submit()" class="btn btn-default">提交</button>
					     <button onclick="toStuInfo()" class="btn btn-default">返回</button>
				     </center>
			    </div>


</body>
</html>