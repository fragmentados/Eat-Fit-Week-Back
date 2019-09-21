package com.eliasfb.efw.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eliasfb.efw.dto.LoginDto;
import com.eliasfb.efw.dto.MealDto;
import com.eliasfb.efw.dto.ResponseDto;
import com.eliasfb.efw.dto.UserConfigurationsDto;
import com.eliasfb.efw.dto.UserDto;
import com.eliasfb.efw.dto.mapper.IngredientToIngredientDtoMapper;
import com.eliasfb.efw.dto.mapper.MealToDtoMapper;
import com.eliasfb.efw.dto.mapper.UserConfigurationToDtoMapper;
import com.eliasfb.efw.enums.UserConfigurationEnum;
import com.eliasfb.efw.model.FoodCategory;
import com.eliasfb.efw.model.Meal;
import com.eliasfb.efw.model.User;
import com.eliasfb.efw.repository.FoodCategoryRepository;
import com.eliasfb.efw.repository.MealRepository;
import com.eliasfb.efw.repository.UserRepository;
import com.eliasfb.efw.service.UserConfigurationService;
import com.eliasfb.efw.service.UserDataLoadService;
import com.eliasfb.efw.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private FoodCategoryRepository categoryRepository;

	@Autowired
	private UserConfigurationService confService;

	@Autowired
	private UserDataLoadService userDataLoadService;

	@Autowired
	private UserConfigurationToDtoMapper userConfMapper;

	@Autowired
	private MealToDtoMapper mealMapper;

	@Autowired
	private IngredientToIngredientDtoMapper ingMapper;

	@Autowired
	private MealRepository mealRepository;

	@Override
	public User create(User user) {
		// Default Meals
		if (user != null) {
			user.setMeals(this.userDataLoadService.getDefaultMeals(user));
		}

		return repository.save(user);
	}

	@Override
	@Transactional
	public User delete(int id) {
		User user = findById(id);
		if (user != null) {
			repository.delete(user);
		}
		return user;
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User findById(int id) {
		return repository.findOne(id);
	}

	@Override
	public User update(User user) {
		return repository.save(user);
	}

	@Override
	@Transactional
	public ResponseDto updateUserConfigurations(int id, UserConfigurationsDto confsDto) {
		User user = repository.findOne(id);
		// User Confs
		user.setConfigurations(userConfMapper.toEntity(confsDto, id));
		// User Meals
		/*
		 * List<Meal> mealsSentByFront =
		 * this.mealMapper.mealDtoListToMealList(confsDto.getMeals()); List<Meal>
		 * userMeals = user.getMeals(); if (mealsSentByFront != userMeals) { List<Meal>
		 * mealsToUpdate = new ArrayList<>(); // We set the hours of the meals ordered
		 * LocalDateTime hour = LocalDate.now().atStartOfDay(); for (Meal m :
		 * mealsSentByFront) {
		 * m.setHour(hour.format(DateTimeFormatter.ofPattern("HH"))); if
		 * (userMeals.stream().anyMatch(userMeal ->
		 * m.getName().equals(userMeal.getName()))) { this.mealRepository.save(m); }
		 * else { m.setUser(user); mealsToUpdate.add(m); } hour = hour.plusHours(1); }
		 * user.setMeals(mealsToUpdate); }
		 */
		// Persist changes
		user = repository.save(user);

		return new ResponseDto(ResponseDto.OK_CODE, "User confs updated correctly");
	}

	@Override
	public UserConfigurationsDto findConfigurations(int id) {
		// Max Nutrition Stats Per Week Confs
		Double maxCaloriesPerWeek = confService.findUserConfigurationByNameOrDefault(id,
				UserConfigurationEnum.MAX_CALORIES_PER_WEEK.getName(), Double.class, 0d);
		Double maxProteinsPerWeek = confService.findUserConfigurationByNameOrDefault(id,
				UserConfigurationEnum.MAX_PROTEINS_PER_WEEK.getName(), Double.class, 0d);
		Double maxFatsPerWeek = confService.findUserConfigurationByNameOrDefault(id,
				UserConfigurationEnum.MAX_FATS_PER_WEEK.getName(), Double.class, 0d);
		Double maxCarbsPerWeek = confService.findUserConfigurationByNameOrDefault(id,
				UserConfigurationEnum.MAX_CARBOHYDRATES_PER_WEEK.getName(), Double.class, 0d);
		// Banned Categories Confs
		List<Integer> bannedCategoriesIds = confService.findUserConfigurationListByNameOrDefault(id,
				UserConfigurationEnum.BANNED_CATEGORIES.getName(), Integer.class, new ArrayList<Integer>());
		List<FoodCategory> bannedCategories = new ArrayList<>();
		if (!bannedCategoriesIds.isEmpty()) {
			bannedCategories = categoryRepository.findByIds(bannedCategoriesIds);
		}

		// Meals
		List<MealDto> meals = this.findUserMeals(id);

		return new UserConfigurationsDto(maxCaloriesPerWeek, maxProteinsPerWeek, maxFatsPerWeek, maxCarbsPerWeek,
				ingMapper.foodCategoryListToDto(bannedCategories), meals);
	}

	@Override
	public ResponseDto login(LoginDto login) {
		ResponseDto response;
		User user = this.repository.findByEmail(login.getEmail());
		if (user == null) {
			response = new ResponseDto(-1, "User not found");
		} else if (user.getPassword().equals(login.getPassword())) {
			response = new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
		} else {
			response = new ResponseDto(-1, "Wrong password");
		}
		return response;
	}

	@Override
	public List<MealDto> findUserMeals(Integer userId) {
		return this.mealMapper.mealListToMealDtoList(this.mealRepository.findByUserIdOrderByHour(userId));
	}

	@Override
	public Meal deleteMeal(int mealId) {
		Meal meal = this.mealRepository.findOne(mealId);
		if (meal != null) {
			this.mealRepository.delete(meal);
		}
		return meal;
	}
}
