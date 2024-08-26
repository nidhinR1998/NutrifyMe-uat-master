package com.application.dietfood.Dto;

import java.util.List;

public class FoodResponseDTO {
	
	private List<FoodDTO> foods;
	
	// Getters and Setters
    public List<FoodDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodDTO> foods) {
        this.foods = foods;
    }

}
