package com.doctortrain.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Score_table")
public class Score {
	@Id
	private String ID;
	// 放疗科成绩
	@Column(nullable=false)
	private String RadScore;
	// 肿瘤内科成绩
	@Column(nullable=false)
	private String MedOncScore;
	// 肿瘤外科成绩
	@Column(nullable=false)
	private String SurOncScore;
	// 病理科成绩
	@Column(nullable=false)
	private String PhthonScore;
	// 影像科成绩
	@Column(nullable=false)
	private String MedImaScore;
	//	MiddleExam	VARCHAR2(255)	否	中期考试
	@Column(nullable=false)
	private String MiddleExam;
	//	stageExamFirst	VARCHAR2(255)	否	阶段考试1
	@Column(nullable=false)
	private String stageExamFirst;
	//	stageExamSecond	VARCHAR2(255)	否	阶段考试2
	@Column(nullable=false)
	private String stageExamSecond;
	//	FinalExam	VARCHAR2(255)	否	结业考试
	@Column(nullable=false)
	private String FinalExam;
	//	Remark	VARCHAR2(255)	是	备注
	private String Remark;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getRadScore() {
		return RadScore;
	}
	public void setRadScore(String radScore) {
		RadScore = radScore;
	}
	public String getMedOncScore() {
		return MedOncScore;
	}
	public void setMedOncScore(String medOncScore) {
		MedOncScore = medOncScore;
	}
	public String getSurOncScore() {
		return SurOncScore;
	}
	public void setSurOncScore(String surOncScore) {
		SurOncScore = surOncScore;
	}
	public String getPhthonScore() {
		return PhthonScore;
	}
	public void setPhthonScore(String phthonScore) {
		PhthonScore = phthonScore;
	}
	public String getMedImaScore() {
		return MedImaScore;
	}
	public void setMedImaScore(String medImaScore) {
		MedImaScore = medImaScore;
	}
	public String getMiddleExam() {
		return MiddleExam;
	}
	public void setMiddleExam(String middleExam) {
		MiddleExam = middleExam;
	}
	public String getStageExamFirst() {
		return stageExamFirst;
	}
	public void setStageExamFirst(String stageExamFirst) {
		this.stageExamFirst = stageExamFirst;
	}
	public String getStageExamSecond() {
		return stageExamSecond;
	}
	public void setStageExamSecond(String stageExamSecond) {
		this.stageExamSecond = stageExamSecond;
	}
	public String getFinalExam() {
		return FinalExam;
	}
	public void setFinalExam(String finalExam) {
		FinalExam = finalExam;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	
	
	
}
