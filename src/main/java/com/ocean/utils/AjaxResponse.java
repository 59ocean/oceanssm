package com.ocean.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author chenhy
 * @date @time 2019/5/23 9:47
 *  ajaxJson result
 */
public class AjaxResponse {
	private String code;
	private String msg;
	private Object data;

	public AjaxResponse () {
	}
	public static AjaxResponse error(){
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setMsg("操作失败！");
		ajaxResponse.setCode("-1");
		return ajaxResponse;
	}
	public static AjaxResponse errorMsg(String msg){
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setMsg(msg);
		ajaxResponse.setCode("-1");
		return ajaxResponse;
	}
	public static AjaxResponse ok(){
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setMsg("操作成功！");
		ajaxResponse.setCode("0");
		return ajaxResponse;
	}
	public static AjaxResponse okMsg(String msg){
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setMsg(msg);
		ajaxResponse.code="0";
		return ajaxResponse;
	}
	public static AjaxResponse ok(Object data) {
		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setMsg("操作成功！");
		ajaxResponse.code = "0";
		ajaxResponse.setData(data);
		return ajaxResponse;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg () {
		return msg;
	}

	public void setMsg (String msg) {
		this.msg = msg;
	}

	public Object getData () {
		return data;
	}

	public void setData (Object data) {
		this.data = data;
	}
}
