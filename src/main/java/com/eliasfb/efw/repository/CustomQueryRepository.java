package com.eliasfb.efw.repository;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eliasfb.efw.model.MachineLearningInstance;

@Repository
public class CustomQueryRepository {

	//@formatter:off
	private static final String GET_MACHINE_LEARNING_INSTANCES = "SELECT UPPER(DAYNAME(MDREL.DISH_DATE)) AS DAYNAME, "
			+ "ME.NAME AS MEALNAME, D.NAME AS DISHNAME "
			+ "FROM MENU M " 
			+ "JOIN menudisrel MDREL ON M.ID = MDREL.MENU_ID " 
			+ "JOIN DISH D ON MDREL.DISH_ID = D.ID "
			+ "JOIN MEAL ME ON ME.USER = M.USER_ID AND ME.HOUR = HOUR(MDREL.DISH_DATE) " 
			+ "WHERE M.USER_ID = ?";
	//@formatter:on

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<MachineLearningInstance> findUserInstances(Integer userId) {
		return this.jdbcTemplate.query(CustomQueryRepository.GET_MACHINE_LEARNING_INSTANCES, new Object[] { userId },
				(ResultSet rs, int rowNum) -> {
					return new MachineLearningInstance(rs.getString("DAYNAME"), rs.getString("MEALNAME"),
							rs.getString("DISHNAME"));
				});
	}
}
