package com.eliasfb.efw.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.eliasfb.efw.model.User;

public interface UserRepository extends Repository<User, Integer> {

	void delete(User user);

	List<User> findAll();

	User findOne(int id);

	User findByEmail(String email);

	User save(User user);
}
