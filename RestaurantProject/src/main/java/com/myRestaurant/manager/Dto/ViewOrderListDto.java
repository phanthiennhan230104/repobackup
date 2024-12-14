package com.myRestaurant.manager.Dto;

public class ViewOrderListDto {

    private String tableId;
    private long minutesElapsed;
    private boolean invoiceStatus;  // Thêm thuộc tính invoiceStatus

    public ViewOrderListDto(String tableId, long minutesElapsed) {
        this.tableId = tableId;
        this.minutesElapsed = minutesElapsed;
    }

    // Getter và Setter
    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public long getMinutesElapsed() {
        return minutesElapsed;
    }

    public void setMinutesElapsed(long minutesElapsed) {
        this.minutesElapsed = minutesElapsed;
    }

    public boolean isInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(boolean invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
}
