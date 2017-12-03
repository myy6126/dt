<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加用户信息</title>
    <!-- Bootstrap core CSS -->
    <link href="../Content/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- JS -->
    <script src="../Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../Content/bootstrap/js/bootstrap.min.js"></script>
	<script src="../Content/layer.js"></script>
	<script type="text/javascript">
    
	
    function doEdit(){
    	$.ajax({
			url:"doEdit.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{
				"score.RadScore":$("#radScore").val(),
				"score.MedOncScore":$("#medOncScore").val(),
				"score.SurOncScore":$("#surOncScore").val(),
				"score.PhthonScore":$("#phthonScore").val(),
				"score.MedImaScore":$("#medImaScore").val(),
				"score.stageExamSecond":$("#secondScore").val(),
				"score.middleExam":$("#middleScore").val(),
				"score.stageExamFirst":$("#firstScore").val(),
				"score.finalExam":$("#finalExam").val(),
				"score.remark":$("#remark").val(),
				"score.ID":$("#scoreId").val()
			},
			beforeSend:function(){
				return true;
			},
			success:function(jsonObject){
				if(jsonObject.success){
					alert("修改成功!")
					toBack();
				}else{
					alert("修改失败!")
				} 
				
			}
		});
    }
	
    
    function toBack(){
    	  window.location.href="index.action"
    }
    
    
    	</script>
</head>
<body>
                <legend>&nbsp;&nbsp;修改用户信息</legend>
                <div class="form-horizontal" role="form">
                <div style = "width:86%">
				   
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="scoreId">学生编号</label>
					    <div class="col-sm-6">
						    <input type="text" id="scoreId" class="form-control" value="${score.ID}" readonly="readonly" placeholder="请输入用户名">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">学生姓名</label>
					    <div class="col-sm-6">
						    <input type="text" id="name" value="${studentInfo.name}" readonly="readonly"  class="form-control" placeholder="请输入密码">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="radScore">放疗科考试成绩</label>
					    <div class="col-sm-6">
						    <input type="text" id="radScore" value="${score.radScore}" class="form-control" placeholder="出科考试成绩">
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="medOncScore">肿瘤内科考试成绩</label>
					    <div class="col-sm-6">
						    <input type="text" id="medOncScore" value="${score.medOncScore}" class="form-control" placeholder="出科考试成绩">
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="surOncScore">肿瘤外科考试成绩</label>
					    <div class="col-sm-6">
						    <input type="text" id="surOncScore" value="${score.surOncScore}" class="form-control" placeholder="出科考试成绩">
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="phthonScore">病理科考试成绩</label>
					    <div class="col-sm-6">
						    <input type="text" id="phthonScore" value="${score.phthonScore}" class="form-control" placeholder="出科考试成绩">
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="medImaScore">影像科考试成绩</label>
					    <div class="col-sm-6">
						    <input type="text" id="medImaScore" value="${score.medImaScore}" class="form-control" placeholder="出科考试成绩">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="firstScore">第一阶段考试</label>
					    <div class="col-sm-6">
						    <input type="text" id="firstScore" class="form-control" value="${score.stageExamFirst}" placeholder="第一阶段考试">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">第二阶段考试</label>
					    <div class="col-sm-6">
						    <input type="text" id="secondScore" class="form-control" value="${score.stageExamSecond}" placeholder="第二阶段考试">
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">期中考试</label>
					    <div class="col-sm-6">
						    <input type="text" id="middleScore" class="form-control" value="${score.middleExam}" placeholder="第二阶段考试">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">结业考试</label>
					    <div class="col-sm-6">
						    <input type="text" id="finalExam" class="form-control" value="${score.finalExam}" placeholder="结业考试">
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">备注</label>
					    <div class="col-sm-6">
						    <input type="text" id="remark" class="form-control" value="${score.remark}" placeholder="备注">
					    </div>
				    </div>
				    
				    
				   </div>
				     <center>
					     <button type="submit" class="btn btn-default" onclick="doEdit()">提交</button>
					     <button type="submit" class="btn btn-default" onclick="toBack()">返回</button>
				     </center>
			    </div>


</body>
</html>