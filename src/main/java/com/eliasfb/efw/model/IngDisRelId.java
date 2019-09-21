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
public class IngDisRelId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Dish dish;

	@ManyToOne
	private Ingredient ingredient;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		IngDisRelId that = (IngDisRelId) o;
		return Objects.equals(ingredient, that.ingredient) && Objects.equals(dish, that.dish);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ingredient, dish);
	}
}
