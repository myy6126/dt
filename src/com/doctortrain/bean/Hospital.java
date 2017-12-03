package com.doctortrain.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Hospital_table")
public class Hospital {
	
	//	ID	VARCHAR2(255)	否	记录编号
	@Id
	@GenericGenerator(name="xxx",strategy="uuid")
	@GeneratedValue(generator="xxx")
	@Column(nullable=false,name="ID")
	private String ID;//否	记录编号
	//	Name	VARCHAR2(255)	否	医院名称（中文）
	@Column(nullable=false)
	private String Name;
	//	Short	VARCHAR2(255)	否	英文缩写如PUMC
	@Column(nullable=false)
	private String Short;
	//	Classe	VARCHAR2(255)	否 	等级（三甲/二甲）
	@Column(nullable=false)
	private String Classes;
	//	Location	VARCHAR2(255)	否	地点（北京）
	@Column(nullable=false)
	private String Location;
	//	Remark	VARCHAR2(255)	是	 备注
	private String Remark;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getShort() {
		return Short;
	}
	public void setShort(String s) {
		Short = s;
	}
	public String getClasses() {
		return Classes;
	}
	public void setClasses(String classes) {
		Classes = classes;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	
	
}
