package com.eliasfb.efw.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.eliasfb.efw.model.Menu;

public interface MenuRepository extends Repository<Menu, Integer> {

	void delete(Menu menu);

	List<Menu> findAll();

	Menu findByUserIdAndStartDate(int userId, String startDate);

	Menu findOne(int id);

	Menu save(Menu menu);
}
