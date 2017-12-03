package com.doctortrain.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="announcement_table")
public class Announcement {
	
	/**
	 * 序号
	 */
    @Id  
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    
    /**
     * 发布用户
     */
    @Column(nullable=false)
    private String username;
    
    /**
     * 标题
     */
    @Column(nullable=false)
    private String title;
    
    /**
     * 文本内容
     */
    @Column(columnDefinition="TEXT") 
    private String text;
    
    /**
     * 公告状态
     */
    @Column(nullable=false)
    private String status;
    
    /**
     * 创建时间
     */
    private String createTime;
    
    /**
     * 发布时间
     */
    private String publishTime;
    
    /**
     * 更新时间
     */
    private String updateTime;

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStatus() {
		return status;
	}

	/**
	 * 1是发布，0是未发布
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
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
