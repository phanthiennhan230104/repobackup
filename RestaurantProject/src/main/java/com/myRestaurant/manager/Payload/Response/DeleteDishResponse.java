package com.myRestaurant.manager.Payload.Response;

public class DeleteDishResponse {

    private String successMessage;
    private String errorMessage;

    public DeleteDishResponse(String successMessage, String errorMessage) {
        this.successMessage = successMessage;
        this.errorMessage = errorMessage;
    }

    // Getters and Setters
    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
