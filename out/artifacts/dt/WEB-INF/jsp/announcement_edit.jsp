<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>编辑公告信息</title>
    <!-- Bootstrap core CSS -->
    <link href="../Content/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../Content/edit/css/summernote.css" rel="stylesheet">
    <!-- JS -->
    <script src="../Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../Content/bootstrap/js/bootstrap.min.js"></script>
	<script src="../Content/layer.js"></script>
	<script src="../Content/edit/js/summernote.js"></script>
	<script src="../Content/edit/js/summernote-zh-CN.js"></script>
	<script type="text/javascript">
    
	$(function(){
		$('.summernote').summernote({
			  height: 150,   //set editable area's height
			  codemirror: { // codemirror options
			    theme: 'monokai'
			  }
			});
	})
	
    function doEdit(){ 
    	
    	var theType = $("input[name='annRadio']:checked").val();
    	var username = "<%=session.getAttribute("username")%>";
    	$.ajax({
			url:"doEdit.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{
				"announcement.id":$("#ann_id").val(),
				"announcement.title":$("#ann_title").val(),
				"announcement.text":$("#ann_text").val(),
				"announcement.createTime":$("#ann_createTime").val(),
				"announcement.publishTime":$("#ann_publishTime").val(),
				"announcement.username":username,
				"announcement.status":theType
			},
			beforeSend:function(){
				return true;
			},
			/* error: function(XMLHttpRequest, textStatus, errorThrown) {
				 alert(XMLHttpRequest.status);
				 alert(XMLHttpRequest.readyState);
				 alert(textStatus);
				   }, */
			success:function(jsonObject){
				if(jsonObject.success){
					alert("修改成功!")
					window.location.href="index.action";
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
                <legend>&nbsp;&nbsp;编辑公告信息</legend>
                <div class="form-horizontal" role="form">
                <input id="ann_id" type="hidden" value="${announcement.id}">
                <input id="ann_createTime" type="hidden" value="${announcement.createTime}">
                <input id="ann_publishTime" type="hidden" value="${announcement.publishTime}">
                <div style = "width:86%">
				  <div class="form-group">
				    <label class="col-sm-2 control-label" for="ann_title">标题</label>
					    <div class="col-sm-10">
						    <input id="ann_title" type="text" class="form-control" value="${announcement.title}" placeholder="公告标题">
					    </div>
				  </div>
				   
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="ann_text">内容</label>
					    <div class="col-sm-10">
						    <textarea class="form-control summernote" rows="6" id="ann_text" placeholder="公告内容">${announcement.text}</textarea>
					    </div>
				    </div>
				     <div class="form-group">
					    <label class="col-sm-2 control-label" for="annRadio">公告状态</label>
					    <div class="col-sm-6">
					    	<c:if test="${announcement.status=='1'}">
						    	<label class="checkbox-inline">
									<input type="radio" name="annRadio" value="1" checked="checked">发布
								</label>
								<label class="checkbox-inline">
									<input type="radio" name="annRadio" value="0" >未发布
								</label>
							</c:if>
					    	<c:if test="${announcement.status=='0'}">
						    	<label class="checkbox-inline">
									<input type="radio" name="annRadio" value="1" >发布
								</label>
								<label class="checkbox-inline">
									<input type="radio" name="annRadio" value="0" checked="checked">未发布
								</label>
							</c:if>
					    </div>
				    </div>
				 
				    
				   </div>
				     <center>
					     <button  class="btn btn-default" onclick="doEdit()">提交</button>
					     <button  class="btn btn-default" onclick="toBack()">返回</button>
				     </center>
			    </div>


</body>
</html>