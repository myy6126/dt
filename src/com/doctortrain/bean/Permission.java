package com.doctortrain.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Permission_table")
public class Permission {
	
	/**
	 * id
	 */
    @Id  
	@Column(nullable=false)
	private String id;
	/**
	 * 名称
	 */
    @Column(nullable=false)
	private String name;
	/**
	 * 模块URL
	 */
    @Column(nullable=true)
	private String moduleUrl;
    
    /**
     * 图标
     */
    @Column(nullable=true)
	private String icon;
    /**
     * 操作URL
     */
    @Column(nullable=true)
    private String operationUrl;
	/**
	 * 上级ID
	 */
    @Column(nullable=true)
	private String pid;
	
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 修改时间
	 */
	private String updateTime;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public String getOperationUrl() {
		return operationUrl;
	}

	public void setOperationUrl(String operationUrl) {
		this.operationUrl = operationUrl;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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
