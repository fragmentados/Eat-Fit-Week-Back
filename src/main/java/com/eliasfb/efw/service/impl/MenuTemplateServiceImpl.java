package com.eliasfb.efw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eliasfb.efw.dto.CreateMenuTemplateDto;
import com.eliasfb.efw.dto.MenuTemplateDto;
import com.eliasfb.efw.dto.ResponseDto;
import com.eliasfb.efw.dto.mapper.MenuToDtoMapper;
import com.eliasfb.efw.model.Menu;
import com.eliasfb.efw.model.MenuTemplate;
import com.eliasfb.efw.model.User;
import com.eliasfb.efw.repository.MenuRepository;
import com.eliasfb.efw.repository.MenuTemplateRepository;
import com.eliasfb.efw.repository.UserRepository;
import com.eliasfb.efw.service.MenuTemplateService;

@Service
public class MenuTemplateServiceImpl implements MenuTemplateService {

	@Autowired
	private MenuTemplateRepository repository;

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MenuToDtoMapper mapper;

	@Override
	public List<MenuTemplateDto> findUserMenuTemplates(Integer userId) {
		return this.mapper.menuTemplateListToMenuTemplateDtoList(this.repository.findByUserId(userId));
	}

	@Override
	public ResponseDto saveMenuAsTemplate(CreateMenuTemplateDto dto) {
		// Find the corresponding entities : menu and user
		Menu menu = this.menuRepository.findOne(dto.getMenuId());
		User user = this.userRepository.findOne(dto.getUserId());
		// Form the menu template with the entities retrieved
		MenuTemplate menuTemplate = new MenuTemplate(dto.getName(), user, menu);
		// Persist the changes
		this.repository.save(menuTemplate);

		return new ResponseDto(ResponseDto.OK_CODE, "Menu Templated recorded correctly");
	}

}
