package com.myRestaurant.manager.Entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class InvoiceMenuKey implements Serializable {
    @Column(name = "invoice_id")
    private int invoiceId;

    @Column(name = "dish_id")
    private int dishId;

    public InvoiceMenuKey() {}

    public InvoiceMenuKey(int invoiceId, int dishId) {
        this.invoiceId = invoiceId;
        this.dishId = dishId;
    }

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

    // Getter và Setter
    
}
