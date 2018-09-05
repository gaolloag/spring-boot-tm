package com.ym.ms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ym.ms.dto.TemplateParamDto;
import com.ym.ms.dto.TemplateResultDto;
import com.ym.ms.dto.common.Result;
import com.ym.ms.entity.Template;
import com.ym.ms.mapper.ITemplateMapper;
import com.ym.ms.service.ITemplateService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author zlc
 * @date 9.5
 */
@Service
public class TemplateImpl extends ServiceImpl<ITemplateMapper, Template> implements ITemplateService {

    @Override
    public Result<TemplateResultDto> query(TemplateParamDto param) {
        return Result.ok();
    }
}
