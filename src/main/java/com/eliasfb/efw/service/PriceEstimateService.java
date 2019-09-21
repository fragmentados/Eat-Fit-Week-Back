package com.eliasfb.efw.service;

import java.util.List;

import com.eliasfb.efw.dto.PriceEstimateDto;
import com.eliasfb.efw.dto.menu.ShoppingListItemDto;

public interface PriceEstimateService {
	PriceEstimateDto estimateShoppingList(List<ShoppingListItemDto> shoppingItems);
}
