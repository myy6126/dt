<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加教师信息</title>
    <!-- Bootstrap core CSS -->
    <link href="../Content/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../Content/bootstrap/css/datetimepicker/bootstrap-datetimepicker.css" rel="stylesheet">
    <!-- JS -->
    <script src="../Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../Content/bootstrap/js/bootstrap.min.js"></script>
	<script src="../Content/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script src="../Content/ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<script src="../Content/bootstrap/js/datetimepicker/bootstrap-datetimepicker.js"></script>
	<script src="../Content/bootstrap/js/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="../Content/layer.js"></script>
	<script type="text/javascript">
    
	
	$(function(){
		initCalendar()
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
	
	function add(){
		$.ajax({
			url:"doAdd.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{
				"teacherInfo.name":$("#teacherName").val(),
				"teacherInfo.birth":$("#teacherBirth").val(),
				"teacherInfo.department":$("#department").val(),
				"teacherInfo.remark":$("#remark").val()
			},
			async: false,
			beforeSend:function(){
				return true;
			},
			success:function(jsonObject){
				if(jsonObject.success){
					alert("添加成功!");
					toBack();
				}else{
					alert("添加失败!");
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
                <legend>&nbsp;&nbsp;添加教师信息</legend>
                <div class="form-horizontal" role="form">
                <div style = "width:86%">
				  <div class="form-group">
				    <label class="col-sm-2 control-label" for="teacherName">教师姓名</label>
					    <div class="col-sm-10">
						    <input id="teacherName" type="text" class="form-control" value="" placeholder="请输入教师姓名">
					    </div>
				  </div>
				   
				   <div class="form-group">
					    <label class="col-sm-2 control-label" for="teacherBirth">出生年月</label>
					   <div class="col-sm-6">
	                		<div class="input-group date form_date" data-date="" data-date-format="yyyy-mm" data-link-format="yyyy-mm" data-link-field="teacherBirth">
		                    	<input id="teacherBirth" class="form-control" size="16" type="text" value="" readonly>
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                    	<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    		</div>
                		</div>
				    </div>
				    
				     <div class="form-group">
					    <label class="col-sm-2 control-label" for="department">科室</label>
						    <div class="col-sm-10">
							     <select class="form-control" id="department">
					                  <c:forEach var="crc"   items="${crcList}">
											<option>${crc.subject}</option>
					                  </c:forEach>
											<option></option>
				                  </select>
						    </div>
					  </div>
					  
					  <div class="form-group">
					    <label class="col-sm-2 control-label" for="remark">备注</label>
						    <div class="col-sm-10">
							    <input id="remark" type="text" class="form-control" value="" placeholder="">
						    </div>
					  </div>
				    
				   </div>
				     <center>
					     <button type="submit" class="btn btn-default" onclick="add()">提交</button>
					     <button type="submit" class="btn btn-default" onclick="toBack()">返回</button>
				     </center>
			    </div>


</body>
</html>