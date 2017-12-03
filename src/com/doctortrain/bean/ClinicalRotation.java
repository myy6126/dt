package com.doctortrain.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ClinicalRotation_table")
public class ClinicalRotation {
	
	//	ID	VARCHAR2(255)	否	记录编号
	@Id	
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
	// 学生id
	private String stuId;
	// 年
	private String year;
	// 一月
	private String Jan;
	// 二月	
	private String Feb;
	// 三月
	private String Mar;
	// 四月
	private String Apr;
	// 五月
	private String May;
	// 六月
	private String Jun;
	// 七月
	private String Jul;
	// 八月
	private String Aug;
	// 九月
	private String Sep;
	// 十月
	private String Oct;
	// 十一月
	private String Nov;
	// 十二月
	private String Decb;
	// 备注
	private String remark;
	
	// 创建时间/开始轮转时间
	private String createTime;
	
	// 更新时间
	private String updateTime;
	
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getJan() {
		return Jan;
	}
	public void setJan(String jan) {
		Jan = jan;
	}
	public String getFeb() {
		return Feb;
	}
	public void setFeb(String feb) {
		Feb = feb;
	}
	public String getMar() {
		return Mar;
	}
	public void setMar(String mar) {
		Mar = mar;
	}
	public String getApr() {
		return Apr;
	}
	public void setApr(String apr) {
		Apr = apr;
	}
	public String getMay() {
		return May;
	}
	public void setMay(String may) {
		May = may;
	}
	public String getJun() {
		return Jun;
	}
	public void setJun(String jun) {
		Jun = jun;
	}
	public String getJul() {
		return Jul;
	}
	public void setJul(String jul) {
		Jul = jul;
	}
	public String getAug() {
		return Aug;
	}
	public void setAug(String aug) {
		Aug = aug;
	}
	public String getSep() {
		return Sep;
	}
	public void setSep(String sep) {
		Sep = sep;
	}
	public String getOct() {
		return Oct;
	}
	public void setOct(String oct) {
		Oct = oct;
	}
	public String getNov() {
		return Nov;
	}
	public void setNov(String nov) {
		Nov = nov;
	}
	public String getDecb() {
		return Decb;
	}
	public void setDecb(String decb) {
		Decb = decb;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
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
	
	
	
}
