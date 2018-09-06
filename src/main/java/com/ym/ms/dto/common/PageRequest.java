package com.ym.ms.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 分页
 *
 * @author sys
 * @date 9.5
 */
@ApiModel(value = "分页请求")
public class PageRequest {

    @NotNull
    @ApiModelProperty(value = "页数",required = true, example = "0")
    private Integer page = 0;
    @NotNull
    @ApiModelProperty(value = "条数",required = true, example = "10")
    private Integer pageSize = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
