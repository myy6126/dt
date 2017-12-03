package com.doctortrain.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="StudentInfo_table")
public class StudentInfo {
	
	//@Id
	//@GenericGenerator(name="xxx",strategy="uuid")
	//@GeneratedValue(generator="xxx")

    @Id  
	@Column(nullable=false,name="ID")
	private String id;//否	记录编号
	@Column(nullable=false,name="NAME")
	private String name;//	否	姓名
	private String Birth;//是	出生年月
	private String Sex;//是	性别
	private String InTraningDate;//是	规培入院时间
	@Column(nullable=false)
	private String trainingType;//否	单位规培住院医/社会规培住院医/研究生
	private String personPic;//	BLOB	是	个人照片
	@Column(nullable=false)
	private String HospitalID;//	否	所属医院（关联医院信息表）
	private String TeacherID;//	否	导师（关联导师信息表）
	private String ResidentClass;//	否	住院医级别（第几年（1/2/3））
	/**
	 * 已完成、正在轮转、未完成
	 */
	@Column(nullable=false)
	private String studentStatus;//	否	已完成、正在轮转、未完成
	
	public String getStudentStatus() {
		return studentStatus;
	}
	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return Birth;
	}
	public void setBirth(String birth) {
		Birth = birth;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getInTraningDate() {
		return InTraningDate;
	}
	public void setInTraningDate(String inTraningDate) {
		InTraningDate = inTraningDate;
	}
	public String getTrainingType() {
		return trainingType;
	}
	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}
	public String getPersonPic() {
		return personPic;
	}
	public void setPersonPic(String personPic) {
		this.personPic = personPic;
	}
	public String getHospitalID() {
		return HospitalID;
	}
	public void setHospitalID(String hospitalID) {
		HospitalID = hospitalID;
	}
	public String getTeacherID() {
		return TeacherID;
	}
	public void setTeacherID(String teacherID) {
		TeacherID = teacherID;
	}
	public String getResidentClass() {
		return ResidentClass;
	}
	public void setResidentClass(String residentClass) {
		ResidentClass = residentClass;
	}
	
}
