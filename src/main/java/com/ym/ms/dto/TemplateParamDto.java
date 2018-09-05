package com.ym.ms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 查询参数
 *
 * @author zlc
 * @date 9.5
 */
@ApiModel(description = "查询参数")
public class TemplateParamDto implements Serializable {
    private static final long serialVersionUID = -4436946424036216502L;

    @ApiModelProperty(required = true, value = "系统标识，最大长度20")
    @NotBlank(message = "系统标识不能为空")
    @Length(max = 20, message = "系统标识最大长度20")
    private String appId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

}
