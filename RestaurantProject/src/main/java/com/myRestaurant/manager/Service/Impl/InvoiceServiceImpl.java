package com.myRestaurant.manager.Service.Impl;

import java.util.List;

import com.myRestaurant.manager.Dto.InvoiceDetailDto;
import com.myRestaurant.manager.Dto.InvoiceDto;

public interface InvoiceServiceImpl {
	List<InvoiceDto> getAllInvoices();
	List<InvoiceDto> searchInvoicesByTableId(String tableId);
	boolean processPayment(int invoiceId);
	List<InvoiceDetailDto> getInvoiceDetails(int invoiceId);
}
