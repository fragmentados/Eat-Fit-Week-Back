package com.eliasfb.efw.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.eliasfb.efw.model.MenuTemplate;

public interface MenuTemplateRepository extends Repository<MenuTemplate, Integer> {

	void delete(MenuTemplate menuTemplate);

	List<MenuTemplate> findAll();

	List<MenuTemplate> findByUserId(int userId);

	MenuTemplate findOne(int id);

	MenuTemplate save(MenuTemplate menuTemplate);
}
