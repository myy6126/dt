<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
   <!--解决abslute标签消失的问题-->
   <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <title>异常日志查询引擎  Find Exception Log Engine</title>
<!-- 日期css -->    
<link href="css/jsp/index.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="jquery/form/jquery.form.js"></script>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="js/chargebrowser.js" ></script>
<script type="text/javascript">

var pageId ="";
var nowTime="";

	function randomString() {
			len =32;
			var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    /**** 默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1 ****/
			var maxPos = $chars.length;
			var pwd = '';
		for (i = 0; i < len; i++) {
			pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
		}
			return pwd;
	}

$(function(){
	
	pageId = randomString();
	$("#pageId").val(pageId);
	
	
});
	// 启动dwr引擎
	dwr.engine.setActiveReverseAjax(true);
	dwr.engine.setNotifyServerOnPageUnload(true);
	
	function showSysMsg(Id,info){
		if(pageId==Id){
			$('#sysinfo').prepend(""+info+"<br>");
		}
	}
	
	 function sel(){ 
		if(!check()){
			return;
		};
		$.ajax({
			url:"findinfo/find.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{
				"info.pageId":$("#pageId").val(),
				"info.appname":$("#appname").val(),
				"info.id":$("#onlyid").val()
			},
			beforeSend:function(){
				$("#cleanid").click(function(){
					$("#ibemsg").html("");
					$("#hasmsg").html("");
					$("#sysinfo").html("");
				})
				$("#msg").text("正在查询请稍后...");
				$("#cleanid").attr("disabled","disabled");
				$("#cleanid").addClass("submitButtonStart")
				return true;
			},
			error:function(){
				$("#msg").text("查询失败！");
				$("#cleanid").removeAttr("disabled");
				$("#cleanid").removeClass("submitButtonStart")
			},
			success:function(jsonObject){
				if(jsonObject.success){
					$("#msg").text("查询成功!");
					$("#cleanid").removeAttr("disabled");
					$("#cleanid").removeClass("submitButtonStart");
				}else{
					if(jsonObject.info!=null){
						$("#msg").text(jsonObject.info);							
					}else{
						$("#msg").text("查询日志失败!");							
					}
					$("#cleanid").removeAttr("disabled");
					$("#cleanid").removeClass("submitButtonStart")
				} 
				if(jsonObject.ishis){
					$("#hasmsg").html(jsonObject.datahas);
					$("#ibemsg").html(jsonObject.dataibe);
				}else{
					var hasStr ="";
					var ibeStr ="";
					$.each(jsonObject.dataibe,function(i,n){
						ibeStr+=n+"\n";
					});
					$("#ibemsg").text(ibeStr);
					$.each(jsonObject.datahas,function(i,n){
						hasStr+=n+"\n";
					});
					$("#hasmsg").text(hasStr);
					
				}
			}
		});
	} 
	 
	 //TODO 校验
	function check(){
		
		var year =""; //2016-04-07
		var month ="";
		var day ="";
		year = nowTime.substring(0,4)-1;
		month = nowTime.substring(5,7);
		day = nowTime.substring(8,10);
		var pastTime = year+"-"+month+"-"+day
		//alert(pastTime)
		
		// 时间范围校验
		if(pastTime>$("#logTime").val()){
			alert("日期太久超过一年期限，请重新调整日期!");
			return false;
		}
		// 非空校验
		if($("#appname").val()==""){
			alert("appname不能为空！")
			return false;
		}
		// 非空校验
		if($("#onlyid").val()==""){
			alert("IDcode不能为空！")
			return false;
		}else if(($("#onlyid").val().length)!=32){
			alert("IDcode位数不正确!")
			return false;
		}
		
		
		return true;
	}	


</script>
</head>
<body> 
 <form action="findinfo/find.action" method="post" id="userForm">
 	<input id="pageId" type="hidden" name="info.pageId">
	<div class="tbody">
    	<div class="bigleft">
    	  <div class="maintitle">
              	异常日志查询
          </div>
            <div class="tleft">
                <div class="ibeinfo">
                <div class="inputinfo">
                    <div class="infotitle">输入信息</div>
                    <div class="inputdiv">
                        <span class="sleft">AppName</span>
                        <span class="sright"><input type="text" name="info.appname" value="" id="appname"/></span>
                  </div>
                    <div class="inputdiv">
                        <span class="sleft">ID code</span>
                        <span class="sright"><input class="idcodeinput" type="text" name="info.id" value="" id="onlyid"/></span>
                  </div>
                </div> 
                </div>              
                            
                <div class="infotitle" >
                    <input onclick="sel()" class="submitButton" type="button" value="查询" id="cleanid" />
                </div>
          </div>
                 
          <div class="bigsysteminfo">
          <div class="systitle">系统信息</div>
                    <div class="systeminfo" id="sysinfo">
                    </div>
          </div>
      </div>
          <div class="bigright">
        	  <a class="a_config" href="config.jsp">后台配置</a>
                <div class="tright">
                	<div class="bttitle">HAS 异常信息</div>
                    <textarea readonly="readonly"  class="einfo" id="hasmsg"></textarea >
                    <div class="bttitle">IBE 异常信息</div>
                    <textarea readonly="readonly"  class="ibeinfomsg" id="ibemsg"></textarea > 
                    <div class="msg" id="msg"></div>
            	</div>
      		</div>
     </div>
    </form> 
</body>
</html>
