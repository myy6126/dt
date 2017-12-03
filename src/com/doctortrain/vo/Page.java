package com.doctortrain.vo;

import java.util.List;

public class Page {
	
	/**
	 * 总记录
	 */
	private int totalSize;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 每页记录数
	 */
	private int pageSize = 10;
	
	/**
	 * 第几页
	 */
	private int pageNo = 1;
	
	/**
	 * 记录信息
	 */
	private List dataList;

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
		int temp = totalSize % pageSize;
		if(temp==0){
			totalPage = totalSize / pageSize;
		}else{
			totalPage = totalSize / pageSize + 1;
		}
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if(pageNo<=0){
			this.pageNo=1;
		}else{
			this.pageNo = pageNo;
		}
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	
	public int getStartIndex() {
		return (pageNo - 1) * pageSize;
	}

}
