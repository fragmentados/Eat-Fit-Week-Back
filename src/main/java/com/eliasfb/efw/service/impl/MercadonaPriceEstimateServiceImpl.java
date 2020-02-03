package com.eliasfb.efw.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.eliasfb.efw.dto.menu.ShoppingListItemDto;
import com.eliasfb.efw.service.PriceEstimateService;

@Service
public class MercadonaPriceEstimateServiceImpl implements PriceEstimateService {

	@Override
	public List<ShoppingListItemDto> estimateShoppingList(List<ShoppingListItemDto> shoppingItems) {
		List<ShoppingListItemDto> shoppingItemsPriced = new ArrayList<>();
		shoppingItemsPriced.addAll(shoppingItems);
		try {
			// We load the contents of the csv on a hashmap
			Map<String, Double> pricesByName = new HashMap<>();
			String row = null;
			BufferedReader csvReader = new BufferedReader(new FileReader("result.csv"));
			// We ignore the header
			csvReader.readLine();
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				// The second column represents the name of the product
				// The fourth column represents the price of the product
				pricesByName.put(data[1].replaceAll("^\"|\"$", ""), Double.valueOf(data[3]));
			}
			csvReader.close();

			// Once we have the map, we cross it with the shopping list needs
			shoppingItemsPriced.stream()
					.forEach(it -> {
						Double price = findFirstPrice(pricesByName, it.getIngredientName()) * it.getUnits();
						// We round each price to two decimals
						price = Math.round(price * 100.0) / 100.0;
						it.setPrice(price);
					});

		} catch (IOException exception) {
			exception.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shoppingItemsPriced;
	}

	private Double findFirstPrice(Map<String, Double> pricesByName, String name) {
		Entry<String, Double> priceByNameEntry = pricesByName.entrySet().stream()
				.filter(entry -> entry.getKey().toLowerCase().contains(name.toLowerCase())).findFirst().orElse(null);
		return priceByNameEntry != null ? priceByNameEntry.getValue() : 0d;
	}

}
