package com.application.dietfood.service;

import com.application.dietfood.Dto.FoodItemDto;
import com.application.dietfood.Dto.NutritionalInfoDTO;
import com.application.dietfood.Dto.ResponseDietDetails;

public interface NutritionService {
	ResponseDietDetails getNutritionalInfo(FoodItemDto foodItemDto);

}
