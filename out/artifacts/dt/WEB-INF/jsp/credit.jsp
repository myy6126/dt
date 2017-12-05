<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>编辑轮转信息</title>
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
				"clinicalRotation.ID":$("#crId").val(),
				"clinicalRotation.stuId":$("#stuId").val(),
				"clinicalRotation.year":$("#year").val(),
				"clinicalRotation.Jan":$("#_jan").val(),
				"clinicalRotation.Feb":$("#_feb").val(),
				"clinicalRotation.Mar":$("#_mar").val(),
				"clinicalRotation.Apr":$("#_apr").val(),
				"clinicalRotation.May":$("#_may").val(),
				"clinicalRotation.Jun":$("#_jun").val(),
				"clinicalRotation.Jul":$("#_jul").val(),
				"clinicalRotation.Aug":$("#_aug").val(),
				"clinicalRotation.Sep":$("#_sep").val(),
				"clinicalRotation.Oct":$("#_oct").val(),
				"clinicalRotation.Nov":$("#_noc").val(),
				"clinicalRotation.Decb":$("#_decb").val(),
				"clinicalRotation.remark":$("#remark").val()
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
                <legend>&nbsp;&nbsp;修改轮转信息</legend>
                <div class="form-horizontal" role="form">
                <div style = "width:86%">
				   
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="crId">轮转编号</label>
					    <div class="col-sm-6">
						    <input type="text" id="crId" class="form-control" value="${clinicalRotation.ID}" readonly="readonly" placeholder="请输入用户名">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stuId">学生编号</label>
					    <div class="col-sm-6">
						    <input type="text" id="stuId" value="${clinicalRotation.stuId}" readonly="readonly"  class="form-control" placeholder="请输入密码">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stuName">学生姓名</label>
					    <div class="col-sm-6">
						    <input type="text" id="stuName" value="${studentInfo.name}" readonly="readonly" class="form-control" placeholder="出科考试成绩">
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="year">年份</label>
					    <div class="col-sm-6">
						    <input type="text" id="year" value="${clinicalRotation.year}" readonly="readonly" class="form-control" placeholder="出科考试成绩">
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="_jan">一月</label>
					    <div class="col-sm-6">
					      <select class="form-control" id="_jan">
			                  <c:forEach var="crc"   items="${crcList}">
									<c:choose>
						  				<c:when test="${clinicalRotation.jan==crc.subject}">
											<option selected="selected">${crc.subject}</option>
						  				</c:when>
						  				<c:otherwise>
											<option>${crc.subject}</option>
						  				</c:otherwise>
						  			</c:choose> 
			                  </c:forEach>
			                  <c:choose>
					  				<c:when test="${empty clinicalRotation.jan}">
										<option selected="selected">${crc.subject}</option>
					  				</c:when>
					  				<c:otherwise>
										<option></option>
					  				</c:otherwise>
				  				</c:choose> 
		                  </select>
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="_feb">二月</label>
					     <div class="col-sm-6">
					    <select class="form-control" id="_feb">
			                  <c:forEach var="crc"   items="${crcList}">
									<c:choose>
						  				<c:when test="${clinicalRotation.feb==crc.subject}">
											<option selected="selected">${crc.subject}</option>
						  				</c:when>
						  				<c:otherwise>
											<option>${crc.subject}</option>
						  				</c:otherwise>
						  			</c:choose> 
			                  </c:forEach>
			                  <c:choose>
					  				<c:when test="${empty clinicalRotation.feb}">
										<option selected="selected">${crc.subject}</option>
					  				</c:when>
					  				<c:otherwise>
										<option></option>
					  				</c:otherwise>
				  				</c:choose> 
		                  </select>
		                  </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="_mar">三月</label>
					    <div class="col-sm-6">
						    <select class="form-control" id="_mar">
			                  <c:forEach var="crc"   items="${crcList}">
									<c:choose>
						  				<c:when test="${clinicalRotation.mar==crc.subject}">
											<option selected="selected">${crc.subject}</option>
						  				</c:when>
						  				<c:otherwise>
											<option>${crc.subject}</option>
						  				</c:otherwise>
						  			</c:choose> 
			                  </c:forEach>
			                   <c:choose>
					  				<c:when test="${empty clinicalRotation.mar}">
										<option selected="selected">${crc.subject}</option>
					  				</c:when>
					  				<c:otherwise>
										<option></option>
					  				</c:otherwise>
				  				</c:choose> 
		                  </select>
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="_apr">四月</label>
					    <div class="col-sm-6">
						    <select class="form-control" id="_apr">
			                  <c:forEach var="crc"   items="${crcList}">
									<c:choose>
						  				<c:when test="${clinicalRotation.apr==crc.subject}">
											<option selected="selected">${crc.subject}</option>
						  				</c:when>
						  				<c:otherwise>
											<option>${crc.subject}</option>
						  				</c:otherwise>
						  			</c:choose> 
			                  </c:forEach>
			                   <c:choose>
					  				<c:when test="${empty clinicalRotation.apr}">
										<option selected="selected">${crc.subject}</option>
					  				</c:when>
					  				<c:otherwise>
										<option></option>
					  				</c:otherwise>
				  				</c:choose> 
		                  </select>
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="_may">五月</label>
					    <div class="col-sm-6">
						  <select class="form-control" id="_may">
			                  <c:forEach var="crc"   items="${crcList}">
									<c:choose>
						  				<c:when test="${clinicalRotation.may==crc.subject}">
											<option selected="selected">${crc.subject}</option>
						  				</c:when>
						  				<c:otherwise>
											<option>${crc.subject}</option>
						  				</c:otherwise>
						  			</c:choose> 
			                  </c:forEach>
			                   <c:choose>
					  				<c:when test="${empty clinicalRotation.mar}">
										<option selected="selected">${crc.subject}</option>
					  				</c:when>
					  				<c:otherwise>
										<option></option>
					  				</c:otherwise>
				  				</c:choose> 
		                  </select>
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="_jun">六月</label>
					    <div class="col-sm-6">
						    <select class="form-control" id="_jun">
			                  <c:forEach var="crc"   items="${crcList}">
									<c:choose>
										<c:when test="${clinicalRotation.jun==crc.subject}">
											<option selected="selected">${crc.subject}</option>
						  				</c:when>
						  				<c:otherwise>
											<option>${crc.subject}</option>
						  				</c:otherwise>
						  			</c:choose> 
			                  </c:forEach>
			                   <c:choose>
					  				<c:when test="${empty clinicalRotation.jun}">
										<option selected="selected">${crc.subject}</option>
					  				</c:when>
					  				<c:otherwise>
										<option></option>
					  				</c:otherwise>
				  				</c:choose> 
		                  </select>
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="_jul">七月</label>
					    <div class="col-sm-6">
						    <select class="form-control" id="_jul">
			                  <c:forEach var="crc"   items="${crcList}">
									<c:choose>
									<c:when test="${clinicalRotation.jul==crc.subject}">
											<option selected="selected">${crc.subject}</option>
						  				</c:when>
						  				<c:otherwise>
											<option>${crc.subject}</option>
						  				</c:otherwise>
						  			</c:choose> 
			                  </c:forEach>
			                   <c:choose>
					  				<c:when test="${empty clinicalRotation.jul}">
										<option selected="selected">${crc.subject}</option>
					  				</c:when>
					  				<c:otherwise>
										<option></option>
					  				</c:otherwise>
				  				</c:choose> 
		                  </select>
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="_aug">八月</label>
					    <div class="col-sm-6">
						    <select class="form-control" id="_aug">
			                  <c:forEach var="crc"   items="${crcList}">
									<c:choose>
						  				<c:when test="${clinicalRotation.aug==crc.subject}">
											<option selected="selected">${crc.subject}</option>
						  				</c:when>
						  				<c:otherwise>
											<option>${crc.subject}</option>
						  				</c:otherwise>
						  			</c:choose> 
			                  </c:forEach>
			                   <c:choose>
					  				<c:when test="${empty clinicalRotation.aug}">
										<option selected="selected">${crc.subject}</option>
					  				</c:when>
					  				<c:otherwise>
										<option></option>
					  				</c:otherwise>
				  				</c:choose> 
		                  </select>
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="_sep">九月</label>
					    <div class="col-sm-6">
						    <select class="form-control" id="_sep">
			                  <c:forEach var="crc"   items="${crcList}">
									<c:choose>
						  				<c:when test="${clinicalRotation.sep==crc.subject}">
											<option selected="selected">${crc.subject}</option>
						  				</c:when>
						  				<c:otherwise>
											<option>${crc.subject}</option>
						  				</c:otherwise>
						  			</c:choose> 
			                  </c:forEach>
			                  <c:choose>
					  				<c:when test="${empty clinicalRotation.sep}">
										<option selected="selected">${crc.subject}</option>
					  				</c:when>
					  				<c:otherwise>
										<option></option>
					  				</c:otherwise>
				  				</c:choose> 
		                  </select>
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="_oct">十月</label>
					    <div class="col-sm-6">
						    <select class="form-control" id="_oct">
			                  <c:forEach var="crc"   items="${crcList}">
									<c:choose>
						  				<c:when test="${clinicalRotation.oct==crc.subject}">
											<option selected="selected">${crc.subject}</option>
						  				</c:when>
						  				<c:otherwise>
											<option>${crc.subject}</option>
						  				</c:otherwise>
						  			</c:choose> 
			                  </c:forEach>
			                  <c:choose>
					  				<c:when test="${empty clinicalRotation.oct}">
										<option selected="selected">${crc.subject}</option>
					  				</c:when>
					  				<c:otherwise>
										<option></option>
					  				</c:otherwise>
				  				</c:choose> 
		                  </select>
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="_nov">十一月</label>
					    <div class="col-sm-6">
						    <select class="form-control" id="_nov">
			                  <c:forEach var="crc"   items="${crcList}">
									<c:choose>
						  				<c:when test="${clinicalRotation.nov==crc.subject}">
											<option selected="selected">${crc.subject}</option>
						  				</c:when>
						  				<c:otherwise>
											<option>${crc.subject}</option>
						  				</c:otherwise>
						  			</c:choose> 
			                  </c:forEach>
			                 <c:choose>
					  				<c:when test="${empty clinicalRotation.nov}">
										<option selected="selected">${crc.subject}</option>
					  				</c:when>
					  				<c:otherwise>
										<option></option>
					  				</c:otherwise>
				  				</c:choose> 
		                  </select>
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="_decb">十二月</label>
					    <div class="col-sm-6">
						     <select class="form-control" id="_decb">
			                  <c:forEach var="crc"   items="${crcList}">
									<c:choose>
						  				<c:when test="${clinicalRotation.decb==crc.subject}">
											<option selected="selected">${crc.subject}</option>
						  				</c:when>
						  				<c:otherwise>
											<option>${crc.subject}</option>
						  				</c:otherwise>
						  			</c:choose> 
			                  </c:forEach>
			                  <c:choose>
					  				<c:when test="${empty clinicalRotation.decb}">
										<option selected="selected">${crc.subject}</option>
					  				</c:when>
					  				<c:otherwise>
										<option></option>
					  				</c:otherwise>
				  				</c:choose> 
		                  </select>
					    </div>
				    </div>
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="remark">备注</label>
					    <div class="col-sm-6">
						    <input type="text" id="remark" class="form-control" value="${clinicalRotation.remark}" placeholder="备注">
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