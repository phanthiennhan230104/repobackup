package com.myRestaurant.manager.Dto;

import java.math.BigDecimal;

public class DishOrderDto {
    private int stt; // Số thứ tự
    private String dishName;  // Tên món ăn
    private int quantity;  // Số lượng món ăn
    private BigDecimal price;

    public DishOrderDto(int stt, String dishName, int quantity, BigDecimal price) {
        this.stt = stt;
        this.dishName = dishName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
