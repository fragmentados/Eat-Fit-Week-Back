package com.eliasfb.efw.model;

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
@Table(name = "dismealrel")
@AssociationOverrides({ @AssociationOverride(name = "id.meal", joinColumns = @JoinColumn(name = "meal_id")),
		@AssociationOverride(name = "id.dish", joinColumns = @JoinColumn(name = "dish_id")) })
public class DisMealRel {
	@EmbeddedId
	private DisMealRelId id;
}
