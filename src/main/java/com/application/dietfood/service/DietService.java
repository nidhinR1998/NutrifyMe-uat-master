package com.application.dietfood.service;

import java.util.List;

import com.application.dietfood.Dto.FoodItemDto;
import com.application.dietfood.Dto.ResponseDietDetails;
import com.application.dietfood.entityClass.FoodItem;


public interface DietService {

	List<FoodItem> getEquivalentDiet(String type, double protein, double fat, double carbohydrates, double vitamins,
			double fiber, double sugar, double salt);

	ResponseDietDetails getFoodDetails(FoodItemDto foodItem);

}
