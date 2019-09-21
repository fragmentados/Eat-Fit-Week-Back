package com.eliasfb.efw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.eliasfb.efw.model.UserConfiguration;

public interface UserConfigurationRepository extends Repository<UserConfiguration, Integer> {

	void delete(UserConfiguration userConfiguration);

	List<UserConfiguration> findAll();

	@Query("SELECT uc FROM UserConfiguration uc JOIN uc.id.user u WHERE u.id = :userId AND uc.id.name = :name")
	UserConfiguration findByNameAndUser(@Param("name") String name, @Param("userId") Integer userId);

	UserConfiguration findOne(int id);

	UserConfiguration save(UserConfiguration userConfiguration);
}
