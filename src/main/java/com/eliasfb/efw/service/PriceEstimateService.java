package com.eliasfb.efw.service;

import java.util.List;

import com.eliasfb.efw.dto.menu.ShoppingListItemDto;

public interface PriceEstimateService {
	List<ShoppingListItemDto> estimateShoppingList(List<ShoppingListItemDto> shoppingItems);
}
