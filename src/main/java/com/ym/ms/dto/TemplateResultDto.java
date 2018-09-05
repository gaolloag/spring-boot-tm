package com.ym.ms.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 结果参数
 *
 * @author zlc
 * @date 9.5
 */
public class TemplateResultDto implements Serializable {
    private static final long serialVersionUID = -663824153556319008L;

    @ApiModelProperty(value = "id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
