package com.myRestaurant.manager.Entities;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "tables")
public class TableEntities {
    @Id
    @Column(name = "table_id")
    private String tableId;

    @Column(name = "seats")
    private int seats;

    @Column(name = "table_status")
    private boolean tableStatus;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private AreaEntities areaEntities;
    
    @OneToMany(mappedBy = "table")
    private Set<InvoiceEntities> invoice;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(boolean tableStatus) {
        this.tableStatus = tableStatus;
    }

    public AreaEntities getAreaEntities() {
        return areaEntities;
    }

    public void setAreaEntities(AreaEntities areaEntities) {
        this.areaEntities = areaEntities;
    }

	public Set<InvoiceEntities> getInvoice() {
		return invoice;
	}

	public void setInvoice(Set<InvoiceEntities> invoice) {
		this.invoice = invoice;
	}
}

