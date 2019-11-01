package com.eliasfb.efw.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisMealRelId implements Serializable {

	@ManyToOne
	private Dish dish;

	@ManyToOne
	private Meal meal;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		DisMealRelId that = (DisMealRelId) o;
		return Objects.equals(dish, that.dish) && Objects.equals(meal, that.meal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dish, meal);
	}
}
