package com.eliasfb.efw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eliasfb.efw.dto.LoginDto;
import com.eliasfb.efw.dto.MealDto;
import com.eliasfb.efw.dto.ResponseDto;
import com.eliasfb.efw.dto.UserConfigurationsDto;
import com.eliasfb.efw.model.Meal;
import com.eliasfb.efw.model.User;
import com.eliasfb.efw.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({ "/users" })
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public User create(@RequestBody User user) {
		return service.create(user);
	}

	@GetMapping(path = { "/{id}" })
	public User findOne(@PathVariable("id") int id) {
		return service.findById(id);
	}

	@GetMapping(path = { "/{id}/conf" })
	public UserConfigurationsDto findUserConfigurations(@PathVariable("id") int id) {
		return service.findConfigurations(id);
	}

	@GetMapping(path = { "/{id}/meals" })
	public List<MealDto> getUserMeals(@PathVariable("id") int id) {
		return service.findUserMeals(id);
	}

	@PostMapping(path = { "/{id}/conf" })
	public ResponseDto updateUserConfigurations(@PathVariable("id") int id,
			@RequestBody UserConfigurationsDto userConfs) {
		return service.updateUserConfigurations(id, userConfs);
	}

	@PostMapping(path = { "/login" })
	public ResponseDto login(@RequestBody LoginDto loginDto) {
		return service.login(loginDto);
	}

	@PutMapping(path = { "/{id}" })
	public User update(@PathVariable("id") int id, @RequestBody User user) {
		user.setId(id);
		return service.update(user);
	}

	@DeleteMapping(path = { "/{id}" })
	public User delete(@PathVariable("id") int id) {
		return service.delete(id);
	}

	@DeleteMapping(path = { "/meals/{id}" })
	public Meal deleteMeal(@PathVariable("id") int id) {
		return service.deleteMeal(id);
	}

	@GetMapping
	public List<User> findAll() {
		return service.findAll();
	}
}
