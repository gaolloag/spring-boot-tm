package com.ym.ms.rest.api.v1;

import com.ym.ms.dto.TemplateParamDto;
import com.ym.ms.dto.TemplateResultDto;
import com.ym.ms.dto.common.Result;
import com.ym.ms.service.ITemplateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微服务APIS
 *
 * @author zlc
 * @date 9.5
 */
@RestController
@RequestMapping("/api/biz/t/v1")
public class TemplateResource {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ITemplateService templateService;

    @ApiOperation(value = "模板查询", tags = {"模板"}, notes = "模板")
    @PostMapping("/query")
    public Result<TemplateResultDto> query(
            @ApiParam(required = true, name = "accessToken", value = "鉴权token") @RequestHeader("accessToken") String accessToken,
            @ApiParam(name = "paramDto", value = "查询参数") @RequestBody TemplateParamDto param) {
        return templateService.query(param);
    }

}
