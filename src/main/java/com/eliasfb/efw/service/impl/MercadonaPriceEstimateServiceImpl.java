package com.eliasfb.efw.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.eliasfb.efw.dto.PriceEstimateDto;
import com.eliasfb.efw.dto.menu.ShoppingListItemDto;
import com.eliasfb.efw.service.PriceEstimateService;

@Service
public class MercadonaPriceEstimateServiceImpl implements PriceEstimateService {

	@Override
	public PriceEstimateDto estimateShoppingList(List<ShoppingListItemDto> shoppingItems) {
		PriceEstimateDto priceEstimate = new PriceEstimateDto();
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
			priceEstimate = processResults(pricesByName, shoppingItems);

		} catch (IOException exception) {
			exception.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priceEstimate;
	}

	private PriceEstimateDto processResults(Map<String, Double> pricesByName, List<ShoppingListItemDto> shoppingItems) {
		// We get the total price summed of all the ingredients
		Double priceSummed = shoppingItems.stream()
				.mapToDouble(it -> findFirstPrice(pricesByName, it.getIngredientName()) * it.getUnits()).sum();
		// We round that amount to two decimals
		priceSummed = Math.round(priceSummed * 100.0) / 100.0;
		return new PriceEstimateDto(priceSummed);
	}

	private Double findFirstPrice(Map<String, Double> pricesByName, String name) {
		Entry<String, Double> priceByNameEntry = pricesByName.entrySet().stream()
				.filter(entry -> entry.getKey().toLowerCase().contains(name.toLowerCase())).findFirst().orElse(null);
		return priceByNameEntry != null ? priceByNameEntry.getValue() : 0d;
	}

}
