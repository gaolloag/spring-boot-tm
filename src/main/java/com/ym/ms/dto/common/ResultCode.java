package com.ym.ms.dto.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * 响应码枚举，参考HTTP状态码的语义
 *
 * @author sys
 * @date 9.5
 */
public enum ResultCode {
    /*
     * 系统异常
     */
    SUCCESS("000000", "success"),
    RE_SUCCESS("100000", "resuccess"),
    PARAM_TYPE_ERROR("S00001", "{0}参数类型错误"),
    PARAM_ISNULL("S00002", "{0}参数不能为空"),
    PARAM_OUTBOUND("S00003", "{0}参数范围越界"),
    PARAM_ERROR("S00004", "{0}参数错误"),
    PARAM_MISSING("S00005", "缺少参数"),
    METHOD_NOTSUPPORT("S00006", "请求方法错误"),
    MEDIATYPE_NOTSUPPORT("S00007", "MediaType不支持"),
    NULL_ERROR("S00008", "空指针异常"),
    /*
     * 通用异常
     */
    C0000001("C0000001", "租户不存在"),
    C0000002("C0000002", "密文验证失败"),
    C0000003("C0000003", "系统文件配置错误"),
    C0000004("C0000004", "{0}"),
    C0000005("C0000005", "字节转换异常"),
    C0000006("C0000005", "字符编码异常"),
    C9999999("C9999999", "系统内部异常"),
    /*
     * 业务异常
     */
    OSS00001("BIZ00001", "业务异常");

    ResultCode(String code, String msg) {
        this.code = code;
        this.message = msg;
    }

    @ApiModelProperty(value = "000000/10000成功，其他失败")
    private String code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String msg) {
        this.message = msg;
    }
}