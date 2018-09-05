package com.ym.ms.dto.common;

import com.ym.ms.utils.StringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 *
 * @author sys
 * @date 9.5
 */
@ApiModel(value = "统一API响应结果")
public class Result<T> implements Serializable {
	private static final long serialVersionUID = -8598958602264325171L;
	private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

	@ApiModelProperty(value = "响应结果编码 000000成功，其他失败")
	private String code;
	@ApiModelProperty(value = "提示信息")
	private String message;
	@ApiModelProperty(value = "返回数据")
	private T data;

	public Result<T> setCode(ResultCode resultCode) {
		this.code = resultCode.getCode();
		return this;
	}

	private Result(T data) {
		this.data = data;
	}
	private Result() {
		
	}
	
	/**
	 * @Description: 成功的返回结果
	 */
	public static <T> Result<T> ok() {
		return new Result<T>().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE);
	}

	/**
	 * @Description: 成功的返回结果
	 */
	public static <T> Result<T> ok(T data) {
		return new Result<T>(data).setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE);
	}
	
	/**
	 * 失败
	 */
	public static <T> Result<T> err(ResultCode resultCode) {
		String msg = StringUtil.parseMessage(resultCode.getMessage(), "", "", "", "");
		return new Result<T>().setCode(resultCode).setMessage(msg);
	}

	/**
	 * 失败
	 */
	public static <T> Result<T> err(ResultCode resultCode, Object ...param) {
		String msg = StringUtil.parseMessage(resultCode.getMessage(), param);
		return new Result<T>().setCode(resultCode).setMessage(msg);
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public Result<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public T getData() {
		return data;
	}

	public Result<T> setData(T data) {
		this.data = data;
		return this;
	}

}