package com.eliasfb.efw.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "dish")
@Data
@ToString(exclude = { "ingredients", "users", "meals", "menus" })
@EqualsAndHashCode(exclude = { "ingredients", "users", "meals", "menus" })
@NoArgsConstructor
public class Dish {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column
	private String recipe;

	@OneToMany(mappedBy = "id.dish", cascade = { CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST })
	private List<DisMealRel> meals;

	@OneToMany(mappedBy = "id.dish", cascade = { CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE,
			CascadeType.PERSIST })
	private List<IngDisRel> ingredients;

	@OneToMany(mappedBy = "id.dish", cascade = CascadeType.REMOVE)
	private List<MenuDisRel> menus;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "disuserel", joinColumns = @JoinColumn(name = "dish_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonIgnore
	private Set<User> users;

	public Double getCalories() {
		return this.ingredients.stream()
				.mapToDouble(ig -> ig.getId().getIngredient().getCalories() * ig.getQuantity() / 100).sum();
	}

	public Double getProteins() {
		return this.ingredients.stream()
				.mapToDouble(ig -> ig.getId().getIngredient().getProteins() * ig.getQuantity() / 100).sum();
	}

	public Double getFats() {
		return this.ingredients.stream()
				.mapToDouble(ig -> ig.getId().getIngredient().getFats() * ig.getQuantity() / 100).sum();
	}

	public Double getCarbohydrates() {
		return this.ingredients.stream()
				.mapToDouble(ig -> ig.getId().getIngredient().getCarbohydrates() * ig.getQuantity() / 100).sum();
	}
}
