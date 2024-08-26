package com.application.dietfood.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.dietfood.Dto.FoodItemDto;
import com.application.dietfood.Dto.ResponseDietDetails;
import com.application.dietfood.entityClass.FoodItem;
import com.application.dietfood.repository.FoodItemRepository;
import com.application.dietfood.service.DietService;
@Service
public class DietServiceImpl implements DietService{
	
	@Autowired
    private FoodItemRepository foodItemRepository;

	@Override
	public List<FoodItem> getEquivalentDiet(String type, double protein, double fat, double carbs, double vitamins, double fiber, double sugar, double salt) {
        String targetType = type.equals("veg") ? "non-veg" : "veg";
        List<FoodItem> targetFoods = foodItemRepository.findByType(targetType);

        // Apply filtering logic to match the nutritional content
        return targetFoods.stream()
                .filter(item -> Math.abs(item.getProtein() - protein) < 2 &&
                                Math.abs(item.getFat() - fat) < 2 &&
                                Math.abs(item.getCarbohydrates() - carbs) < 2 &&
                                Math.abs(item.getVitamins() - vitamins) < 2 &&
                                Math.abs(item.getFiber() - fiber) < 2 &&
                                Math.abs(item.getSugar() - sugar) < 2 &&
                                Math.abs(item.getSalt() - salt) < 2)
                .collect(Collectors.toList());
    }

	@Override
	public ResponseDietDetails getFoodDetails(FoodItemDto foodItem) {
		
		return null;
	}
 

}
