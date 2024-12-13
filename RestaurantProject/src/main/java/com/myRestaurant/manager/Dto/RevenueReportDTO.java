package com.myRestaurant.manager.Dto;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class RevenueReportDTO {
    private String dishName;
    private BigDecimal price;
    private int quantity;
    private BigDecimal totalPrice;

    // Constructor
    public RevenueReportDTO(String dishName, BigDecimal price, int quantity, BigDecimal totalPrice) {
        this.dishName = dishName;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    // Helper for formatted currency
    public String getFormattedPrice() {
        return formatCurrency(price);
    }
	public String getFormattedTotalPrice() {
        return formatCurrency(totalPrice);
    }
	private String formatCurrency(BigDecimal amount) {
        if (amount == null) {
            return ""; // Trả về chuỗi rỗng nếu amount là null
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return formatter.format(amount);
    }
}