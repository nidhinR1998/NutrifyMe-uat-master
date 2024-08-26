package com.application.dietfood.Dto;

public class NutritionalInfoDTO {
	private Double calories;
    private Double protein;
    private Double carbohydrates;
    private Double fat;
    private Double fiber;
    private Double sugar;
    private Double salt;
    private Double cholesterol;
    private Double calcium;
    private Double iron;
    private Double potassium;
    private Double vitaminA;
    private Double vitaminC;
    private Double vitaminD;
    private Double vitaminE;
    private Double vitaminK;
    private String allergens;
    private Integer servingSize;
    private String servingUnit;
    private Integer servingsPerContainer;
    
    private String notes;
    
    private String recommendations;
    
	public NutritionalInfoDTO() {
		
	}

	public NutritionalInfoDTO(Double calories, Double protein, Double carbohydrates, Double fat, Double fiber,
			Double sugar, Double salt, Double cholesterol, Double calcium, Double iron, Double potassium,
			Double vitaminA, Double vitaminC, Double vitaminD, Double vitaminE, Double vitaminK, String allergens,
			Integer servingSize, String servingUnit, Integer servingsPerContainer, String notes,
			String recommendations) {
		super();
		this.calories = calories;
		this.protein = protein;
		this.carbohydrates = carbohydrates;
		this.fat = fat;
		this.fiber = fiber;
		this.sugar = sugar;
		this.salt = salt;
		this.cholesterol = cholesterol;
		this.calcium = calcium;
		this.iron = iron;
		this.potassium = potassium;
		this.vitaminA = vitaminA;
		this.vitaminC = vitaminC;
		this.vitaminD = vitaminD;
		this.vitaminE = vitaminE;
		this.vitaminK = vitaminK;
		this.allergens = allergens;
		this.servingSize = servingSize;
		this.servingUnit = servingUnit;
		this.servingsPerContainer = servingsPerContainer;
		this.notes = notes;
		this.recommendations = recommendations;
	}

	public Double getCalories() {
		return calories;
	}

	public void setCalories(Double calories) {
		this.calories = calories;
	}

	public Double getProtein() {
		return protein;
	}

	public void setProtein(Double protein) {
		this.protein = protein;
	}

	public Double getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(Double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public Double getFat() {
		return fat;
	}

	public void setFat(Double fat) {
		this.fat = fat;
	}

	public Double getFiber() {
		return fiber;
	}

	public void setFiber(Double fiber) {
		this.fiber = fiber;
	}

	public Double getSugar() {
		return sugar;
	}

	public void setSugar(Double sugar) {
		this.sugar = sugar;
	}

	public Double getSalt() {
		return salt;
	}

	public void setSalt(Double salt) {
		this.salt = salt;
	}

	public Double getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(Double cholesterol) {
		this.cholesterol = cholesterol;
	}

	public Double getCalcium() {
		return calcium;
	}

	public void setCalcium(Double calcium) {
		this.calcium = calcium;
	}

	public Double getIron() {
		return iron;
	}

	public void setIron(Double iron) {
		this.iron = iron;
	}

	public Double getPotassium() {
		return potassium;
	}

	public void setPotassium(Double potassium) {
		this.potassium = potassium;
	}

	public Double getVitaminA() {
		return vitaminA;
	}

	public void setVitaminA(Double vitaminA) {
		this.vitaminA = vitaminA;
	}

	public Double getVitaminC() {
		return vitaminC;
	}

	public void setVitaminC(Double vitaminC) {
		this.vitaminC = vitaminC;
	}

	public Double getVitaminD() {
		return vitaminD;
	}

	public void setVitaminD(Double vitaminD) {
		this.vitaminD = vitaminD;
	}

	public Double getVitaminE() {
		return vitaminE;
	}

	public void setVitaminE(Double vitaminE) {
		this.vitaminE = vitaminE;
	}

	public Double getVitaminK() {
		return vitaminK;
	}

	public void setVitaminK(Double vitaminK) {
		this.vitaminK = vitaminK;
	}

	public String getAllergens() {
		return allergens;
	}

	public void setAllergens(String allergens) {
		this.allergens = allergens;
	}

	public Integer getServingSize() {
		return servingSize;
	}

	public void setServingSize(Integer servingSize) {
		this.servingSize = servingSize;
	}

	public String getServingUnit() {
		return servingUnit;
	}

	public void setServingUnit(String servingUnit) {
		this.servingUnit = servingUnit;
	}

	public Integer getServingsPerContainer() {
		return servingsPerContainer;
	}

	public void setServingsPerContainer(Integer servingsPerContainer) {
		this.servingsPerContainer = servingsPerContainer;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}

	
}
