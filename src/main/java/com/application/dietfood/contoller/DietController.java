package com.application.dietfood.contoller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.dietfood.Dto.FoodItemDto;
import com.application.dietfood.Dto.ResponseDietDetails;
import com.application.dietfood.entityClass.FoodItem;
import com.application.dietfood.service.DietService;

//@RestController
//@RequestMapping("/diet")
public class DietController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private DietService dietService;

    @PostMapping("/getDietEE")
    public ResponseEntity<List<FoodItem>> getDiet(@RequestBody FoodItem foodItem) {
        List<FoodItem> equivalentDiet = dietService.getEquivalentDiet(
            foodItem.getType(), foodItem.getProtein(), foodItem.getFat(), 
            foodItem.getCarbohydrates(), foodItem.getVitamins(), 
            foodItem.getFiber(), foodItem.getSugar(), foodItem.getSalt()
        );
        return ResponseEntity.ok(equivalentDiet);
    }

    @PostMapping("/getDiet")
    public ResponseDietDetails getDietFood(@RequestBody FoodItemDto foodItem) {
    	logger.info("GetDiet Hit");
    	ResponseDietDetails response = new ResponseDietDetails();
    	response = dietService.getFoodDetails(foodItem);
        return response;
    }
}

