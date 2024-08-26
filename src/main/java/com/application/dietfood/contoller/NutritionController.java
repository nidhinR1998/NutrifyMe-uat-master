package com.application.dietfood.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.dietfood.Dto.FoodItemDto;
import com.application.dietfood.Dto.ResponseDietDetails;
import com.application.dietfood.service.NutritionService;


@RestController
@RequestMapping("/diet")
public class NutritionController {
	
	private static final Logger logger = LoggerFactory.getLogger(NutritionController.class);

    @Autowired
    private NutritionService nutritionService;

    @PostMapping("/getDiet")
    public ResponseDietDetails getNutritionalInfo(@RequestBody FoodItemDto foodItemDto) {    
    	ResponseDietDetails dto = nutritionService.getNutritionalInfo(foodItemDto);
        return dto;
    }

}