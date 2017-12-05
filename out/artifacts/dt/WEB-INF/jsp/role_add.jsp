<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加角色信息</title>
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
    
	var sendData = "";
	
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true,
					 /* idKey: "id",
	                    //当前节点的父节点id属性 
	                    pIdKey: "pId",
	                    //用于修正根节点父节点数据，即pIdKey指定的属性值
	                    rootPId: 0 */
				}
			},
			callback:{
                onCheck:onCheck
            }
		};

		var zNodes =[
			/* 
			{ id:1, pId:0, name:"随意勾选 1", open:true},
			{ id:11, pId:1, name:"随意勾选 1-1", open:true},
			{ id:111, pId:11, name:"随意勾选 1-1-1"},
			{ id:112, pId:11, name:"随意勾选 1-1-2"},
			{ id:12, pId:1, name:"随意勾选 1-2", open:true},
			{ id:121, pId:12, name:"随意勾选 1-2-1"},
			{ id:122, pId:12, name:"随意勾选 1-2-2"},
			{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
			{ id:21, pId:2, name:"随意勾选 2-1"},
			{ id:22, pId:2, name:"随意勾选 2-2", open:true},
			{ id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
			{ id:222, pId:22, name:"随意勾选 2-2-2"},
			{ id:23, pId:2, name:"随意勾选 2-3"} 
			*/
		];
		
		
	function onCheck(e,treeId,treeNode){
			sendData = "roleId="+$("#roleid").val()
            var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            nodes=treeObj.getCheckedNodes(true),
            v="";
            for(var i=0;i<nodes.length;i++){
            v+=nodes[i].name + ",";
            //alert(nodes[i].id); //获取选中节点的值
            sendData += "&permissionIds="+nodes[i].id;
            }
            
    }
		
		
	function getAuth(){
			$.ajax({
				url:"getAuth.action",
				type:"post",
				contentType:"application/x-www-form-urlencoded; charset=utf-8",
				async: false,
				beforeSend:function(){
					return true;
				},
				success:function(jsonObject){
					if(jsonObject.success){
						zNodes = jsonObject.resultList;
					}else{
						alert("false");
					} 
				}
			});
	}
	
	function addAll(){
		addRole();
		assign();
	}
	
	function addRole(){
		var theType = $("input[name='roleRadio']:checked").val();
		$.ajax({
			url:"add.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:{
				"role.id":$("#roleid").val(),
				"role.name":$("#rolename").val(),
				"role.type":theType,
				"role.remark":$("#roleremark").val()
			},
			async: false,
			beforeSend:function(){
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
	
	function assign(){
		$.ajax({
			url:"assign.action",
			type:"post",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			data:sendData,
			async: false,
			beforeSend:function(){
				return true;
			},
			success:function(jsonObject){
				if(jsonObject.success){
					alert("添加成功！");
					window.location.href="index.action";
				}else{
					alert("false");
				} 
			}
		});
	}
	
    $(function(){
    	getAuth()
    	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
    })
        
     
    function toBack(){
    	  window.location.href="index.action"
    }

    
    
    
	
    	</script>
</head>
<body>
                <legend>&nbsp;&nbsp;添加角色信息</legend>
                <div class="form-horizontal" role="form">
                <div style = "width:86%">
				  <div class="form-group">
				    <label class="col-sm-2 control-label" for="roleid">角色编号</label>
					    <div class="col-sm-10">
						    <input id="roleid" type="text" class="form-control" placeholder="请输入角色编号">
					    </div>
				  </div>
				   
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="rolename">角色名称</label>
					    <div class="col-sm-10">
						    <input id="rolename" type="text" class="form-control" placeholder="请输入角色名称">
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="stu_sex_boy">角色类型</label>
					    <div class="col-sm-6">
						    	<label class="checkbox-inline">
									<input type="radio" name="roleRadio" value="0" checked="checked">普通用户
								</label>
								<label class="checkbox-inline">
									<input type="radio" name="roleRadio" value="1" >管理员
								</label>
					    </div>
				    </div>
				    
				    <div class="form-group">
					    <label class="col-sm-2 control-label" for="permission">访问权限</label>
					    <div class="col-sm-10">
					    	<div class="zTreeDemoBackground left">
						    	<ul id="treeDemo" class="ztree"></ul>
					    	</div>
								
					    </div>
					</div>
					    
					    
				    </div>
				    
				    
				    
				   <div class="form-group">
					    <label class="col-sm-2 control-label" for="name">角色备注</label>
					    <div class="col-sm-10">
						    <input type="text" class="form-control" placeholder="" id="roleremark">
					    </div>
				    </div>
				    
				   </div>
				     <center>
					     <button type="submit" class="btn btn-default" onclick="addAll()">提交</button>
					     <button type="submit" class="btn btn-default" onclick="toBack()">返回</button>
				     </center>
			    </div>


</body>
</html>