package com.ym.ms.service;

import com.ym.ms.dto.TemplateParamDto;
import com.ym.ms.dto.TemplateResultDto;
import com.ym.ms.dto.common.Result;
import com.ym.ms.entity.Template;
import com.baomidou.mybatisplus.service.IService;

/**
 * 服务类
 *
 * @author zlc
 * @date 9.5
 */
public interface ITemplateService extends IService<Template> {
    /**
     * 查询省
     *
     * @param param
     * @return
     */
    Result<TemplateResultDto> query(TemplateParamDto param);
	
}
