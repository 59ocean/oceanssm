package com.ocean.query;

/**
 * @author chenhy
 * @date @time 2019/6/27 11:39
 */
public class PageQuery {
	private int pageNo = 1;
	private int pageSize = 10;

	public int getPageNo () {
		return pageNo;
	}

	public void setPageNo (int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize () {
		return pageSize;
	}

	public void setPageSize (int pageSize) {
		this.pageSize = pageSize;
	}
}
