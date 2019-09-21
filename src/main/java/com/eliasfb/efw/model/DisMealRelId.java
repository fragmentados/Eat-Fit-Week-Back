package com.eliasfb.efw.model;

import java.io.Serializable;

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
}
