package com.myRestaurant.manager.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "revenues")
public class RevenueEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "total_revenue", nullable = false)
    private double totalRevenue;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    // Constructors
    public RevenueEntities() {
    }

    public RevenueEntities(String itemName, int quantity, double price, LocalDate date) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.totalRevenue = quantity * price; // Compute total revenue
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        computeTotalRevenue(); // Update total revenue when quantity changes
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        computeTotalRevenue(); // Update total revenue when price changes
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Helper method to recalculate total revenue
    private void computeTotalRevenue() {
        this.totalRevenue = this.quantity * this.price;
    }
}
