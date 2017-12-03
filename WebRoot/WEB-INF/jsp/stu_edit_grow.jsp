<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
    <!-- Bootstrap core CSS -->
    <link href="../Content/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../Content/ztree/css/zTreeStyle.css" rel="stylesheet">
    <link href="../Content/ztree/css/tree.css" rel="stylesheet">
    <!-- JS -->
    <script src="../Content/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../Content/bootstrap/js/bootstrap.min.js"></script>
	<script src="../Content/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script src="../Content/ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<script src="../Content/layer.js"></script>
	<script type="text/javascript">
    
	
	function doEditGrow(){
		var theType = $("input[name='roleRadio']:checked").val();
		$.ajax({
			url:"doEditGrow.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{
				"dtConfig.id":$("#dtId").val(),
				"dtConfig.name":$("#dtName").val(),
				"dtConfig.value":$("#dtValue").val()
			},
			async: false,
			beforeSend:function(){
				return true;
			},
			success:function(jsonObject){
				if(jsonObject.success){
						alert("修改成功！")
						window.location.href="index.action";
				}else{
					alert("false");
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
                <div class="form-horizontal" role="form">
                <div style = "width:86%">
                <input id="dtId" type="hidden" value="${dtConfig.id}">
				  <div class="form-group">
				    <label class="col-sm-2 control-label" for="roleid">年级</label>
					    <div class="col-sm-10">
						    <input id="dtName" type="text" class="form-control" readonly="readonly" value="${dtConfig.name}">
					    </div>
				  </div>
				   
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="rolename">当前ID</label>
					    <div class="col-sm-10">
						    <input id="dtValue" type="text" class="form-control" value="${dtConfig.value}">
					    </div>
				    </div>
				    
				    </div>
				    
				   </div>
				     <center>
					     <button type="submit" class="btn btn-default" onclick="doEditGrow()">提交</button>
					     <button type="submit" class="btn btn-default" onclick="toBack()">返回</button>
				     </center>
			    </div>


</body>
</html>