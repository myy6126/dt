<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>外院轮转界面</title>
    <!-- Bootstrap core CSS -->
    <link href="../Content/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- JS -->
    <script src="../Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../Content/bootstrap/js/bootstrap.min.js"></script>
	<script src="../Content/layer.js"></script>
	<script type="text/javascript">
    
	function getYear(){
		var d = new Date();
        return d.getFullYear();
	}
	
	function setYear(){
		$(".year1").html(getYear()+"年");
		$(".year2").html(getYear()+1+"年");
		$(".year3").html(getYear()+2+"年");
		$(".year4").html(getYear()+3+"年");
	}
	
	$(function(){
		setYear();
	})
	
	function addCrc(){
		$.ajax({
			url:"stucrh.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{
				"ids":$("#stuid").val(),
				"clinicalRotationConfig.startTime":$("#startTime_year").val()+"-"+$("#startTime_month").val(),
				"clinicalRotationConfig.endTime":$("#endTime_year").val()+"-"+$("#endTime_month").val()
			},
			async: false,
			beforeSend:function(){
				return true;
			},
			success:function(jsonObject){
				if(jsonObject.success){
					alert("轮转成功！");
					toBack();
				}else{
					alert("轮转失败!");
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
                <legend>&nbsp;&nbsp;添加规则配置</legend>
                <div class="form-horizontal" role="form">
                <input id="stuid" type="hidden" value="${ids}"/>
                <div style = "width:86%">
				  <div class="form-group">
				    <label class="col-sm-2 control-label" for="subject">科目名称</label>
					    <div class="col-sm-10">
						    <input id="subject" type="text" class="form-control" value="外院" readonly="readonly" placeholder="请输入科目名称">
					    </div>
				  </div>
				   
				   <div class="form-group">
					    <label class="col-sm-2 control-label" for=startTime_year>开始时间</label>
					    <div class="col-sm-6" class="form-control">
						    <select id="startTime_year" >
						      <option class="year1" value="1"></option>
						      <option class="year2" value="2"></option>
						      <option class="year3" value="3"></option>
						      <option class="year4" value="4"></option>
						    </select>
						    <select id="startTime_month">
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
						      <option value="12">12月</option>
						    </select>
					    </div>
				    </div>
				    
   				   <div class="form-group">
					    <label class="col-sm-2 control-label" for="endTime_year">结束时间</label>
					   	<div class="col-sm-6" class="form-control">
						    <select id="endTime_year">
						      <option class="year1" value="1"></option>
						      <option class="year2" value="2"></option>
						      <option class="year3" value="3"></option>
						      <option class="year4" value="4"></option>
						    </select>
						    <select id="endTime_month">
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
						      <option value="12">12月</option>
						    </select>
					   	 </div>
				    </div>
				    
				   </div>
				     <center>
					     <button type="submit" class="btn btn-default" onclick="addCrc()">开始轮转</button>
					     <button type="button" class="btn btn-default" onclick="toBack()">返回</button>
				     </center>
			    </div>


</body>
</html>