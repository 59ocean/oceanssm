package com.ocean.query;

/**
 * @author chenhy
 * @date @time 2019/6/27 11:39
 */
public class PageQuery {
	private int page = 1;
	private int limit = 10;

	public int getPage () {
		return page;
	}

	public void setPage (int page) {
		this.page = page;
	}

	public int getLimit () {
		return limit;
	}

	public void setLimit (int limit) {
		this.limit = limit;
	}
}
