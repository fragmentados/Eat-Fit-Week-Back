package com.eliasfb.efw.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ingredient")
@Data
@ToString(exclude = { "users", "category" })
@EqualsAndHashCode(exclude = { "users", "category" })
@NoArgsConstructor
public class Ingredient {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private double calories;
	@Column
	private double proteins;
	@Column
	private double fats;
	@Column
	private double carbohydrates;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "inguserel", joinColumns = @JoinColumn(name = "ingredient_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonIgnore
	private Set<User> users;

	@ManyToOne(fetch = FetchType.LAZY)
	private FoodCategory category;

	// @OneToMany(mappedBy = "id.ingredient", cascade = CascadeType.REMOVE)
	// private List<IngDisRel> dishes;

	public Ingredient(int id) {
		super();
		this.id = id;
	}

}
