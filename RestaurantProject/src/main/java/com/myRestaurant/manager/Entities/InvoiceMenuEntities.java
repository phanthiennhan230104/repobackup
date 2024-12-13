package com.myRestaurant.manager.Entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EmbeddedId;

@Entity
@Table(name = "invoice_menuu")
public class InvoiceMenuEntities {
    
    @EmbeddedId
    private InvoiceMenuKey id;  // Khóa chính composite
    
    @ManyToOne
    @MapsId("invoiceId")  // Mối quan hệ với hóa đơn
    @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id", insertable = false, updatable = false)
    private InvoiceEntities invoiceEntities;
    
    @ManyToOne
    @MapsId("dishId")  // Mối quan hệ với món ăn
    @JoinColumn(name = "dish_id", referencedColumnName = "dish_id", insertable = false, updatable = false)
    private MenuEntities menuEntities;

    private int quantity;  // Số lượng món ăn
    private BigDecimal totalPrice;  // Tổng giá của món ăn

    // Getter và Setter cho các thuộc tính khác
    public InvoiceMenuKey getId() {
        return id;
    }

    public void setId(InvoiceMenuKey id) {
        this.id = id;
    }

    public InvoiceEntities getInvoiceEntities() {
        return invoiceEntities;
    }

    public void setInvoiceEntities(InvoiceEntities invoiceEntities) {
        this.invoiceEntities = invoiceEntities;
    }

    public MenuEntities getMenuEntities() {
        return menuEntities;
    }

    public void setMenuEntities(MenuEntities menuEntities) {
        this.menuEntities = menuEntities;
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

    // Tính toán tổng giá
    public void calculateTotalPrice() {
        if (this.menuEntities != null && this.menuEntities.getPrice() != null) {
            // Chuyển quantity thành BigDecimal và tính tổng giá
            this.totalPrice = this.menuEntities.getPrice().multiply(BigDecimal.valueOf(this.quantity));
        }
    }

}
