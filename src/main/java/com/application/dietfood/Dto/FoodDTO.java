package com.application.dietfood.Dto;

import java.util.List;

public class FoodDTO {
	
	
	private String food_name;
    private String brand_name;
    private int serving_qty;
    private String serving_unit;
    private double serving_weight_grams;
    private double nf_calories;
    private double nf_total_fat;
    private double nf_saturated_fat;
    private double nf_cholesterol;
    private double nf_sodium;
    private double nf_total_carbohydrate;
    private double nf_dietary_fiber;
    private double nf_sugars;
    private double nf_protein;
    private double nf_potassium;
    private double nf_p;
    private List<FullNutrientDTO> full_nutrients;
    private String nix_brand_name;
    private String nix_brand_id;
    private String nix_item_name;
    private String nix_item_id;
    private String upc;
    private String consumed_at;
    private MetadataDTO metadata;
    private int source;
    private int ndb_no;
    private TagsDTO tags;
    private List<AltMeasureDTO> alt_measures;
    private String lat;
    private String lng;
    private int meal_type;
    private PhotoDTO photo;
    private String sub_recipe;
    private String class_code;
    private String brick_code;
    private String tag_id;
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public int getServing_qty() {
		return serving_qty;
	}
	public void setServing_qty(int serving_qty) {
		this.serving_qty = serving_qty;
	}
	public String getServing_unit() {
		return serving_unit;
	}
	public void setServing_unit(String serving_unit) {
		this.serving_unit = serving_unit;
	}
	public double getServing_weight_grams() {
		return serving_weight_grams;
	}
	public void setServing_weight_grams(double serving_weight_grams) {
		this.serving_weight_grams = serving_weight_grams;
	}
	public double getNf_calories() {
		return nf_calories;
	}
	public void setNf_calories(double nf_calories) {
		this.nf_calories = nf_calories;
	}
	public double getNf_total_fat() {
		return nf_total_fat;
	}
	public void setNf_total_fat(double nf_total_fat) {
		this.nf_total_fat = nf_total_fat;
	}
	public double getNf_saturated_fat() {
		return nf_saturated_fat;
	}
	public void setNf_saturated_fat(double nf_saturated_fat) {
		this.nf_saturated_fat = nf_saturated_fat;
	}
	public double getNf_cholesterol() {
		return nf_cholesterol;
	}
	public void setNf_cholesterol(double nf_cholesterol) {
		this.nf_cholesterol = nf_cholesterol;
	}
	public double getNf_sodium() {
		return nf_sodium;
	}
	public void setNf_sodium(double nf_sodium) {
		this.nf_sodium = nf_sodium;
	}
	public double getNf_total_carbohydrate() {
		return nf_total_carbohydrate;
	}
	public void setNf_total_carbohydrate(double nf_total_carbohydrate) {
		this.nf_total_carbohydrate = nf_total_carbohydrate;
	}
	public double getNf_dietary_fiber() {
		return nf_dietary_fiber;
	}
	public void setNf_dietary_fiber(double nf_dietary_fiber) {
		this.nf_dietary_fiber = nf_dietary_fiber;
	}
	public double getNf_sugars() {
		return nf_sugars;
	}
	public void setNf_sugars(double nf_sugars) {
		this.nf_sugars = nf_sugars;
	}
	public double getNf_protein() {
		return nf_protein;
	}
	public void setNf_protein(double nf_protein) {
		this.nf_protein = nf_protein;
	}
	public double getNf_potassium() {
		return nf_potassium;
	}
	public void setNf_potassium(double nf_potassium) {
		this.nf_potassium = nf_potassium;
	}
	public double getNf_p() {
		return nf_p;
	}
	public void setNf_p(double nf_p) {
		this.nf_p = nf_p;
	}
	public List<FullNutrientDTO> getFull_nutrients() {
		return full_nutrients;
	}
	public void setFull_nutrients(List<FullNutrientDTO> full_nutrients) {
		this.full_nutrients = full_nutrients;
	}
	public String getNix_brand_name() {
		return nix_brand_name;
	}
	public void setNix_brand_name(String nix_brand_name) {
		this.nix_brand_name = nix_brand_name;
	}
	public String getNix_brand_id() {
		return nix_brand_id;
	}
	public void setNix_brand_id(String nix_brand_id) {
		this.nix_brand_id = nix_brand_id;
	}
	public String getNix_item_name() {
		return nix_item_name;
	}
	public void setNix_item_name(String nix_item_name) {
		this.nix_item_name = nix_item_name;
	}
	public String getNix_item_id() {
		return nix_item_id;
	}
	public void setNix_item_id(String nix_item_id) {
		this.nix_item_id = nix_item_id;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public String getConsumed_at() {
		return consumed_at;
	}
	public void setConsumed_at(String consumed_at) {
		this.consumed_at = consumed_at;
	}
	public MetadataDTO getMetadata() {
		return metadata;
	}
	public void setMetadata(MetadataDTO metadata) {
		this.metadata = metadata;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getNdb_no() {
		return ndb_no;
	}
	public void setNdb_no(int ndb_no) {
		this.ndb_no = ndb_no;
	}
	public TagsDTO getTags() {
		return tags;
	}
	public void setTags(TagsDTO tags) {
		this.tags = tags;
	}
	public List<AltMeasureDTO> getAlt_measures() {
		return alt_measures;
	}
	public void setAlt_measures(List<AltMeasureDTO> alt_measures) {
		this.alt_measures = alt_measures;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public int getMeal_type() {
		return meal_type;
	}
	public void setMeal_type(int meal_type) {
		this.meal_type = meal_type;
	}
	public PhotoDTO getPhoto() {
		return photo;
	}
	public void setPhoto(PhotoDTO photo) {
		this.photo = photo;
	}
	public String getSub_recipe() {
		return sub_recipe;
	}
	public void setSub_recipe(String sub_recipe) {
		this.sub_recipe = sub_recipe;
	}
	public String getClass_code() {
		return class_code;
	}
	public void setClass_code(String class_code) {
		this.class_code = class_code;
	}
	public String getBrick_code() {
		return brick_code;
	}
	public void setBrick_code(String brick_code) {
		this.brick_code = brick_code;
	}
	public String getTag_id() {
		return tag_id;
	}
	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}
    
    

}
