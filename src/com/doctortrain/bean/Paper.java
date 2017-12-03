package com.doctortrain.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Paper_table")
public class Paper{
	//	ID	VARCHAR2(255)	否	记录编号
	@Id
	@GenericGenerator(name="xxx",strategy="uuid")
	@GeneratedValue(generator="xxx")
	@Column(nullable=false,name="ID")
	private String ID;
	//	StudentID	VARCHAR2(255)	否	论文相关的规培生
	@Column(nullable=false)
	private String Short;
	//	PapaerName	VARCHAR2(255)	否	论文或会议名称
	@Column(nullable=false)
	private String PapaerName;
	//	PapaerDate	VARCHAR2(255)	否	论文或会议时间
	@Column(nullable=false)
	private String PapaerDate;
	//	PaperValue	VARCHAR2(255)	是	论文或会议价值
	private String PaperValue;
	//	PaperContribution	VARCHAR2(255)	是	规培生贡献
	private String PaperContribution;
	//	corresponding	VARCHAR2(255)	是	通讯作者（导师）
	private String corresponding;
	//	Remark	VARCHAR2(255)	是	备注
	private String Remark;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getShort() {
		return Short;
	}
	public void setShort(String s) {
		Short = s;
	}
	public String getPapaerName() {
		return PapaerName;
	}
	public void setPapaerName(String papaerName) {
		PapaerName = papaerName;
	}
	public String getPapaerDate() {
		return PapaerDate;
	}
	public void setPapaerDate(String papaerDate) {
		PapaerDate = papaerDate;
	}
	public String getPaperValue() {
		return PaperValue;
	}
	public void setPaperValue(String paperValue) {
		PaperValue = paperValue;
	}
	public String getPaperContribution() {
		return PaperContribution;
	}
	public void setPaperContribution(String paperContribution) {
		PaperContribution = paperContribution;
	}
	public String getCorresponding() {
		return corresponding;
	}
	public void setCorresponding(String corresponding) {
		this.corresponding = corresponding;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	
	
}
