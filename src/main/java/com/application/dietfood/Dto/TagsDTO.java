package com.application.dietfood.Dto;

public class TagsDTO {
    private String item;
    private String measure;
    private String quantity;
    private int food_group;
    private int tag_id;
    
    // Getters and Setters
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getFood_group() {
        return food_group;
    }

    public void setFood_group(int food_group) {
        this.food_group = food_group;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }
}
