package com.ocean.utils;

import java.util.ArrayList;
import java.util.List;

public class PageResult {
    private static final long serialVersionUID = 4137170628914512450L;

    private Long count = 0L;
    private String code = "";
    private String msg = "";
    private List data = new ArrayList();

    public PageResult(Long count, String code, String msg, List data) {
        this.count = count;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static PageResult ok(Long count,List data){
        return new PageResult(count,"0","操作成功",data);
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }


}
