package com.eliasfb.efw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eliasfb.efw.dto.CreateMenuTemplateDto;
import com.eliasfb.efw.dto.MenuTemplateDto;
import com.eliasfb.efw.dto.ResponseDto;
import com.eliasfb.efw.service.MenuTemplateService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({ "/menutemplates" })
public class MenuTemplateController {
	@Autowired
	private MenuTemplateService service;

	@GetMapping
	public List<MenuTemplateDto> getUserMenuTemplates(@RequestParam("userId") int userId) {
		return service.findUserMenuTemplates(userId);
	}

	@PostMapping
	public ResponseDto saveMenuAsTemplate(@RequestBody CreateMenuTemplateDto dto) {
		return service.saveMenuAsTemplate(dto);
	}
}
