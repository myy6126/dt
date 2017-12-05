
--插入权限信息
insert into Permission_table(id,createTime,icon,moduleUrl,name,operationUrl,pid,updateTime) values('1','init','icon-leaf','','基础模块','','0','');
insert into Permission_table(id,createTime,icon,moduleUrl,name,operationUrl,pid,updateTime) values('11','init','','/stu/index.action','学生信息','stu/add.action,stu/toEdit.action,stu/doEdit.action,stu/update.action','1','');
insert into Permission_table(id,createTime,icon,moduleUrl,name,operationUrl,pid,updateTime) values('12','init','','/clinicalRotation/index.action','轮转信息','clinicalRotation/add.action,clinicalRotation/toEdit.action,clinicalRotation/doEdit.action,clinicalRotation/update.action','1','');
insert into Permission_table(id,createTime,icon,moduleUrl,name,operationUrl,pid,updateTime) values('13','init','','/score/index.action','学生成绩','score/add.action,score/toEdit.action,score/doEdit.action,score/update.action','1','');
insert into Permission_table(id,createTime,icon,moduleUrl,name,operationUrl,pid,updateTime) values('2','init','icon-user','','权限模块','','0','');
insert into Permission_table(id,createTime,icon,moduleUrl,name,operationUrl,pid,updateTime) values('21','init','','/user/index.action','用户管理','user/add.action,user/toEdit.action,user/doEdit.action,user/update.action','2','');
insert into Permission_table(id,createTime,icon,moduleUrl,name,operationUrl,pid,updateTime) values('22','init','','/role/index.action','角色管理','role/add.action,role/toEdit.action,role/doEdit.action,role/update.action','2','');
insert into Permission_table(id,createTime,icon,moduleUrl,name,operationUrl,pid,updateTime) values('23','init','','/permission/index.action','权限管理','permission/add.action,permission/toEdit.action,permission/doEdit.action,permission/update.action','2','');
insert into Permission_table(id,createTime,icon,moduleUrl,name,operationUrl,pid,updateTime) values('3','init','icon-list','','日志模块','','0','');
insert into Permission_table(id,createTime,icon,moduleUrl,name,operationUrl,pid,updateTime) values('31','init','','/operationlog/index.action','日志管理','operationlog/add.action,operationlog/toEdit.action,operationlog/doEdit.action,operationlog/update.action','3','');
insert into Permission_table(id,createTime,icon,moduleUrl,name,operationUrl,pid,updateTime) values('4','init','icon-envelope','','公告模块','','0','');
insert into Permission_table(id,createTime,icon,moduleUrl,name,operationUrl,pid,updateTime) values('41','init','','/announcement/index.action','公告管理','announcement/add.action,announcement/toEdit.action,announcement/doEdit.action,announcement/update.action','4','');

insert into role_table(id,createTime,name,remark,type,updateTime) values('001','init','超级管理员','init','1','');

insert into user_table(username,createTime,email,password,privilege,status,tel,updateTime) values('admin','init','admin@163.com','admin','001','1','123','');

insert into role_permission_relation_table(id,permission_id,role_id) values('2c9b8d955a273ac8015a273d921c0002','1','001');
insert into role_permission_relation_table(id,permission_id,role_id) values('2c9b8d955a273ac8015a273d92200003','11','001');
insert into role_permission_relation_table(id,permission_id,role_id) values('2c9b8d955a273ac8015a273d92230004','12','001');
insert into role_permission_relation_table(id,permission_id,role_id) values('2c9b8d955a273ac8015a273d92250005','13','001');
insert into role_permission_relation_table(id,permission_id,role_id) values('2c9b8d955a273ac8015a273d92270006','2','001');
insert into role_permission_relation_table(id,permission_id,role_id) values('2c9b8d955a273ac8015a273d92290007','21','001');
insert into role_permission_relation_table(id,permission_id,role_id) values('2c9b8d955a273ac8015a273d922a0008','22','001');
insert into role_permission_relation_table(id,permission_id,role_id) values('2c9b8d955a273ac8015a273d922c0009','23','001');
insert into role_permission_relation_table(id,permission_id,role_id) values('2c9b8d955a273ac8015a273d922e0e0a','3','001');
insert into role_permission_relation_table(id,permission_id,role_id) values('2c9b8d955a273ac8015a273d923s006b','31','001');
insert into role_permission_relation_table(id,permission_id,role_id) values('2c9b8d955a273ac8015a273d9231000c','4','001');
insert into role_permission_relation_table(id,permission_id,role_id) values('2c9b8d955a273ac8015a273d9233000d','41','001');

insert into user_role_relation_table(id,role_id,user_username) values('2c9b8d955a273ac8015a273d9233000a','001','admin');

insert into dt_config_table(id,name,value) values('2c9b8d9a6a273ac8015a273d9233000a','crindex','1');