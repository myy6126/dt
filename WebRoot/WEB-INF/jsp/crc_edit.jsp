<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加轮转配置信息</title>
    <!-- Bootstrap core CSS -->
    <link href="../Content/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- JS -->
    <script src="../Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../Content/bootstrap/js/bootstrap.min.js"></script>
	<script src="../Content/layer.js"></script>
	<script type="text/javascript">
    
	
	function addCrc(){
		$.ajax({
			url:"doConfigEdit.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{
				"clinicalRotationConfig.id":$("#crcid").val(),
				"clinicalRotationConfig.subject":$("#subject").val(),
				"clinicalRotationConfig.startTime":$("#startTime_year").val()+"-"+$("#startTime_month").val(),
				"clinicalRotationConfig.endTime":$("#endTime_year").val()+"-"+$("#endTime_month").val(),
				"clinicalRotationConfig.priority":$("#priority").val(),
				"clinicalRotationConfig.remark":$("#remark").val()
			},
			async: false,
			beforeSend:function(){
				toBack()
				return true;
			},
			success:function(jsonObject){
				if(jsonObject.success){
				}else{
					alert("false");
				} 
			}
		}); 
	}
	
     
    function toBack(){
    	  window.location.href="toConfig.action"
    }
    
	
    	</script>
</head>
<body>
                <legend>&nbsp;&nbsp;编辑规则配置</legend>
                <div class="form-horizontal" role="form">
                <input id="crcid" type="hidden" value="${clinicalRotationConfig.id}">
                <div style = "width:86%">
				  <div class="form-group">
				    <label class="col-sm-2 control-label" for="subject">科目名称</label>
					    <div class="col-sm-10">
						    <input id="subject" type="text" class="form-control" value="${clinicalRotationConfig.subject}" placeholder="请输入科目名称">
					    </div>
				  </div>
				   
				   <div class="form-group">
					    <label class="col-sm-2 control-label" for=startTime_year>开始时间</label>
					    <div class="col-sm-6" class="form-control">
						    <select id="startTime_year" >
						    <c:if test="${fn:contains(clinicalRotationConfig.startTime,'1-')}">
							      <option selected="selected" value="1">1年</option>
							      <option value="2">2年</option>
							      <option value="3">3年</option>
							      <option value="4">4年</option>
					    	 </c:if>
						    <c:if test="${fn:contains(clinicalRotationConfig.startTime,'2-')}">
							      <option value="1">1年</option>
							      <option selected="selected" value="2">2年</option>
							      <option value="3">3年</option>
							      <option value="4">4年</option>
					    	 </c:if>
						    <c:if test="${fn:contains(clinicalRotationConfig.startTime,'3-')}">
							      <option value="1">1年</option>
							      <option value="2">2年</option>
							      <option selected="selected" value="3">3年</option>
							      <option value="4">4年</option>
					    	 </c:if>
						    <c:if test="${fn:contains(clinicalRotationConfig.startTime,'4-')}">
							      <option value="1">1年</option>
							      <option value="2">2年</option>
							      <option value="3">3年</option>
							      <option selected="selected" value="4">4年</option>
					    	 </c:if>
						    </select>
						    <!-- 开始时间 -->
						    <select id="startTime_month">
						      <c:if test="${fn:contains(clinicalRotationConfig.startTime,'-1')}">
							      <option selected="selected" value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.startTime,'-2')}">
							      <option value="1">1月</option>
							      <option selected="selected" value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.startTime,'-3')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option selected="selected" value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.startTime,'-4')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option selected="selected" value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.startTime,'-5')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option selected="selected" value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.startTime,'-6')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option selected="selected" value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.startTime,'-7')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option selected="selected" value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.startTime,'-8')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option selected="selected" value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.startTime,'-9')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option selected="selected" value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.startTime,'-10')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option selected="selected" value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.startTime,'-11')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option selected="selected" value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.startTime,'-12')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option selected="selected" value="12">12月</option>
					    	 </c:if>
						    </select>
					    </div>
				    </div>
				    
				    
				    
				    
				    <!-- 结束时间 -->
				    
				    
   				   <div class="form-group">
					    <label class="col-sm-2 control-label" for="endTime_year">结束时间</label>
					   	<div class="col-sm-6" class="form-control">
						     <select id="endTime_year" >
						    <c:if test="${fn:contains(clinicalRotationConfig.endTime,'1-')}">
							      <option selected="selected" value="1">1年</option>
							      <option value="2">2年</option>
							      <option value="3">3年</option>
							      <option value="4">4年</option>
					    	 </c:if>
						    <c:if test="${fn:contains(clinicalRotationConfig.endTime,'2-')}">
							      <option value="1">1年</option>
							      <option selected="selected" value="2">2年</option>
							      <option value="3">3年</option>
							      <option value="4">4年</option>
					    	 </c:if>
						    <c:if test="${fn:contains(clinicalRotationConfig.endTime,'3-')}">
							      <option value="1">1年</option>
							      <option value="2">2年</option>
							      <option selected="selected" value="3">3年</option>
							      <option value="4">4年</option>
					    	 </c:if>
						    <c:if test="${fn:contains(clinicalRotationConfig.endTime,'4-')}">
							      <option value="1">1年</option>
							      <option value="2">2年</option>
							      <option value="3">3年</option>
							      <option selected="selected" value="4">4年</option>
					    	 </c:if>
						    </select>
						    <!-- 开始时间 -->
						    <select id="endTime_month">
						      <c:if test="${fn:contains(clinicalRotationConfig.endTime,'-1')}">
							      <option selected="selected" value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.endTime,'-2')}">
							      <option value="1">1月</option>
							      <option selected="selected" value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.endTime,'-3')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option selected="selected" value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.endTime,'-4')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option selected="selected" value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.endTime,'-5')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option selected="selected" value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.endTime,'-6')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option selected="selected" value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.endTime,'-7')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option selected="selected" value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.endTime,'-8')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option selected="selected" value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.endTime,'-9')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option selected="selected" value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.endTime,'-10')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option selected="selected" value="10">10月</option>
							      <option value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.endTime,'-11')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option selected="selected" value="11">11月</option>
							      <option value="12">12月</option>
					    	 </c:if>
					    	 <c:if test="${fn:contains(clinicalRotationConfig.endTime,'-12')}">
							      <option value="1">1月</option>
							      <option value="2">2月</option>
							      <option value="3">3月</option>
							      <option value="4">4月</option>
							      <option value="5">5月</option>
							      <option value="6">6月</option>
							      <option value="7">7月</option>
							      <option value="8">8月</option>
							      <option value="9">9月</option>
							      <option value="10">10月</option>
							      <option value="11">11月</option>
							      <option selected="selected" value="12">12月</option>
					    	 </c:if>
						    </select>
					   	 </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="priority">优先级</label>
					   	<div class="col-sm-6" class="form-control">
						    <select id="priority">
						    	<c:if test="${clinicalRotationConfig.priority==0}">
						    		  <option selected="selected" value="0">手动填写</option>
								      <option value="1">自动平衡1</option>
								      <option value="2">自动平衡2</option>
						    	</c:if>
						    	<c:if test="${clinicalRotationConfig.priority==1}">
						    		  <option value="0">手动填写</option>
								      <option selected="selected" value="1">自动平衡1</option>
								      <option value="2">自动平衡2</option>
						    	</c:if>
						    	<c:if test="${clinicalRotationConfig.priority==2}">
						    		  <option value="0">手动填写</option>
								      <option value="1">自动平衡1</option>
								      <option selected="selected" value="2">自动平衡2</option>
						    	</c:if>
						    </select>
					   	 </div>
				    </div>
				    
				     <div class="form-group">
					    <label class="col-sm-2 control-label" for="remark">描述</label>
						    <div class="col-sm-10">
							    <input id="remark" type="text" class="form-control" value="${clinicalRotationConfig.remark}" placeholder="">
						    </div>
					  </div>
				    
				   </div>
				     <center>
					     <button type="submit" class="btn btn-default" onclick="addCrc()">提交</button>
					     <button type="submit" class="btn btn-default" onclick="toBack()">返回</button>
				     </center>
			    </div>


</body>
</html>