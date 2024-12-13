package com.myRestaurant.manager.Dto;

import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.myRestaurant.manager.Entities.DishType;

@Data
public class MenuDto {
    private int dishId;
    private String dishName;
    private DishType dishType;
    private BigDecimal price;
    private String imagePath;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
 
	public int getDishId() {
		return dishId;
	}
	public void setDishId(int dishId) {
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
	public void setDishType(DishType dishType) {
		this.dishType = dishType;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		// TODO Auto-generated method stub
		
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		// TODO Auto-generated method stub
		
	}
	
	
    
}