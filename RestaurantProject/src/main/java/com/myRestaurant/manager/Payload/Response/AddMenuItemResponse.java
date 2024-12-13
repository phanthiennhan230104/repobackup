package com.myRestaurant.manager.Payload.Response;

import java.math.BigDecimal;

import com.myRestaurant.manager.Entities.DishType;

public class AddMenuItemResponse {
    private Integer dishId;
    private String dishName;
    private DishType dishType;
    private BigDecimal price;
    private String description;
    private String imagePath;

    // Getters và Setters
    public Integer getdishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public DishType getDishType() {
        return dishType;
    }


    public Integer getDishId() {
		return dishId;
	}

	public void setDishType(DishType dishType) {
		this.dishType = dishType;
	}

	public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}