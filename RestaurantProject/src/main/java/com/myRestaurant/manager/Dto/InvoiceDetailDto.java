package com.myRestaurant.manager.Dto;

import java.math.BigDecimal;

public class InvoiceDetailDto {
    private String dishName;
    private int quantity;
    private BigDecimal totalPrice;

    public InvoiceDetailDto(String dishName, int quantity, BigDecimal totalPrice) {
        this.dishName = dishName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getters and setters
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
