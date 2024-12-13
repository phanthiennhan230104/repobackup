package com.myRestaurant.manager.Payload.Response;

public class AddDishToInvoiceResponse {
    private boolean success;
    private String message;

    // Constructor
    public AddDishToInvoiceResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    // Getters and Setters...
}
