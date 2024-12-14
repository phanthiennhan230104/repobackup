package com.myRestaurant.manager.Payload.Response;

public class DishOrderResponse {
    private String message;

    public DishOrderResponse() {
    }

    public DishOrderResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}