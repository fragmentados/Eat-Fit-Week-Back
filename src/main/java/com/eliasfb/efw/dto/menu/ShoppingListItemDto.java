package com.eliasfb.efw.dto.menu;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShoppingListItemDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ingredientName;
	private Long units;
	private Double quantity;
}
