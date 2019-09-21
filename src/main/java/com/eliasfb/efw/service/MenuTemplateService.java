package com.eliasfb.efw.service;

import java.util.List;

import com.eliasfb.efw.dto.CreateMenuTemplateDto;
import com.eliasfb.efw.dto.MenuTemplateDto;
import com.eliasfb.efw.dto.ResponseDto;

public interface MenuTemplateService {

	List<MenuTemplateDto> findUserMenuTemplates(Integer userId);

	ResponseDto saveMenuAsTemplate(CreateMenuTemplateDto dto);
}
