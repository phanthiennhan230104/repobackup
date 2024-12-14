package com.myRestaurant.manager.Payload.Request;

public class DishOrderRequest {
    private int dishId;

    public DishOrderRequest() {
    }

    public DishOrderRequest(int dishId) {
        this.dishId = dishId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    
}
