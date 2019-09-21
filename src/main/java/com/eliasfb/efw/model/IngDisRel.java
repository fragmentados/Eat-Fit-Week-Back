package com.eliasfb.efw.model;

import java.util.Objects;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ingdisrel")
@AssociationOverrides({ @AssociationOverride(name = "id.dish", joinColumns = @JoinColumn(name = "dish_id")),
		@AssociationOverride(name = "id.ingredient", joinColumns = @JoinColumn(name = "ingredient_id")) })
public class IngDisRel {

	@EmbeddedId
	private IngDisRelId id;

	private Double quantity;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		IngDisRel that = (IngDisRel) o;
		return id != null ? Objects.equals(id, that.id) && Objects.equals(quantity, that.quantity) : false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, quantity);
	}
}
