package com.dj.ssm.common;

public class ResultModel<T> {

	private Integer code = 200;
	private String msg = "请求成功！";
	private T data;

	
	public ResultModel<T> success(String msg){
		this.msg = msg;
		return this;
	}
	public ResultModel<T> success(){
		return this;
	}
	
	public ResultModel<T> success(T data){
		this.data = data;
		return this;
	}
	public ResultModel<T> success( String msg, T data){
		this.msg = msg;
		this.data = data;
		return this;
	}
	
	public ResultModel<T> error(String msg){
		this.code = -1;
		this.msg = msg;
		return this;
	}
	
	public ResultModel<T> error(Integer code, String msg){
		this.code = code;
		this.msg = msg;
		return this;
	}
	public ResultModel<T> error(Integer code, String msg, T data){
		this.code = code;
		this.msg = msg;
		this.data = data;
		return this;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
