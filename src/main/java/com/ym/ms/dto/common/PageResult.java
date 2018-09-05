package com.ym.ms.dto.common;

import com.ym.ms.utils.StringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分面响应结果封装
 *
 * @author sys
 * @date 9.5
 */
@ApiModel(value = "统一API响应结果")
public class PageResult<T> implements Serializable{
    private static final long serialVersionUID = 6004532974070436597L;
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    @ApiModelProperty(value = "响应结果编码 000000成功，其他失败")
    private String code;

    @ApiModelProperty(value = "提示信息")
    private String message;

    @ApiModelProperty(value = "结果状态")
    private String status;

    @ApiModelProperty(value = "总记录数")
    private long total;

    @ApiModelProperty(value = "返回数据")
    private List<T> rows;

    public PageResult(){
    }

    public long getTotal() {
        return total;
    }

    public PageResult<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public List<T> getRows() {
        return rows;
    }

    public PageResult<T> setRows(List<T> rows) {
        this.rows = rows;
        return this;
    }

    public String getCode() {
        return code;
    }

    public PageResult<T> setCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public PageResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * @Description: 成功的返回结果
     */
    public static <T> PageResult<T> ok() {
        return new PageResult<T>().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE).setTotal(0).setRows(new ArrayList<T>());
    }

    /**
     * @Description: 成功的返回结果
     */
    public static <T> PageResult<T> ok(long total, List<T> rows) {
        return new PageResult<T>().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE).setTotal(total).setRows(rows);
    }

    /**
     * 失败
     */
    public static <T> PageResult<T> err(ResultCode resultCode) {
        String msg = StringUtil.parseMessage(resultCode.getMessage(), "", "", "", "");
        return new PageResult<T>().setCode(resultCode).setMessage(msg);
    }

    /**
     * 失败
     */
    public static <T> PageResult<T> err(ResultCode resultCode, Object ...param) {
        String msg = StringUtil.parseMessage(resultCode.getMessage(), param);
        return new PageResult<T>().setCode(resultCode).setMessage(msg);
    }
}
