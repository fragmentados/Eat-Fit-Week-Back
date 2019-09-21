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
@Table(name = "menudisrel")
@AssociationOverrides({ @AssociationOverride(name = "id.menu", joinColumns = @JoinColumn(name = "menu_id")),
		@AssociationOverride(name = "id.dish", joinColumns = @JoinColumn(name = "dish_id")) })
public class MenuDisRel {

	@EmbeddedId
	private MenuDisRelId id;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		MenuDisRel that = (MenuDisRel) o;
		return id != null ? Objects.equals(id, that.id) : false;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

}
