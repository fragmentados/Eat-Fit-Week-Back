package com.eliasfb.efw.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
// @ToString(exclude = { "menu", "dish", "dishDate" })
public class MenuDisRelId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Menu menu;

	@ManyToOne
	private Dish dish;

	@Column(name = "dish_date")
	private String dishDate;

	@Override
	public String toString() {
		return "MenuDisRelId : menuId = " + this.menu.getId() + ", dishId = " + this.dish.getId() + ", dishDate = "
				+ this.dishDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		MenuDisRelId that = (MenuDisRelId) o;
		return Objects.equals(menu, that.menu) && Objects.equals(dish, that.dish)
				&& Objects.equals(dishDate, that.dishDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(menu, dish, dishDate);
	}
}
