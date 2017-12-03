package com.doctortrain.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TeacherInfo_table")
public class TeacherInfo {
	
	@Id	
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;//否	记录编号
//	Name	VARCHAR2(255)	否	姓名
	@Column(nullable=false)
	private String name;
//	Age	VARCHAR2(255)	是	出生年月
	private String birth;
//	Department	VARCHAR2(255)	否	科室
	@Column(nullable=false)
	private String department;
//	Branch	VARCHAR2(255)	否	 专业组
	//@Column(nullable=false)
	//private String branch;
//	Remark	VARCHAR2(255)	是	 备注
	private String remark;
	
	private String createTime;
	private String updateTime;
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	/*public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}*/
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
