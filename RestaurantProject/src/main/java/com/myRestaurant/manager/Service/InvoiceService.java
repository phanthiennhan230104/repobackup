package com.myRestaurant.manager.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myRestaurant.manager.Dto.InvoiceDetailDto;
import com.myRestaurant.manager.Dto.InvoiceDto;
import com.myRestaurant.manager.Entities.InvoiceEntities;
import com.myRestaurant.manager.Repository.InvoiceRepository;
import com.myRestaurant.manager.Service.Impl.InvoiceServiceImpl;

@Service
public class InvoiceService implements InvoiceServiceImpl{

	@Autowired
    private InvoiceRepository invoiceRepository;
	
	@Override
	public List<InvoiceDto> getAllInvoices() {
	    List<InvoiceEntities> invoiceEntitiesList = invoiceRepository.findByInvoiceStatus(false); // Chỉ lấy hóa đơn chưa thanh toán
	    return invoiceEntitiesList.stream().map(invoice -> {
	        InvoiceDto invoiceDto = new InvoiceDto();
	        invoiceDto.setInvoiceId(invoice.getInvoiceId());
	        invoiceDto.setUserId(invoice.getUser().getId());
	        invoiceDto.setTableId(invoice.getTable().getTableId());
	        invoiceDto.setSum(invoice.getSum());
	        invoiceDto.setPoint(invoice.getPoint());
	        invoiceDto.setInvoiceStatus(invoice.isInvoiceStatus());
	        return invoiceDto;
	    }).collect(Collectors.toList());
	}

	@Override
	public List<InvoiceDto> searchInvoicesByTableId(String tableId) {
	    List<InvoiceEntities> invoiceEntitiesList = invoiceRepository.findByTable_TableIdContainingIgnoreCaseAndInvoiceStatus(tableId, false);
	    return invoiceEntitiesList.stream().map(invoice -> {
	        InvoiceDto invoiceDto = new InvoiceDto();
	        invoiceDto.setInvoiceId(invoice.getInvoiceId());
	        invoiceDto.setUserId(invoice.getUser().getId());
	        invoiceDto.setTableId(invoice.getTable().getTableId());
	        invoiceDto.setSum(invoice.getSum());
	        invoiceDto.setPoint(invoice.getPoint());
	        invoiceDto.setInvoiceStatus(invoice.isInvoiceStatus());
	        return invoiceDto;
	    }).collect(Collectors.toList());
	}


	@Override
	public boolean processPayment(int invoiceId) {
	    InvoiceEntities invoice = invoiceRepository.findById(invoiceId).orElse(null);
	    if (invoice == null || invoice.isInvoiceStatus()) { // Kiểm tra trạng thái hóa đơn
	        return false;
	    }

	    // Cập nhật trạng thái hóa đơn
	    invoice.setInvoiceStatus(true);
	    invoiceRepository.save(invoice); // Lưu thay đổi

	    return true;
	}

	@Override
	public List<InvoiceDetailDto> getInvoiceDetails(int invoiceId) {
		return invoiceRepository.findInvoiceDetailsByInvoiceId(invoiceId);
	}
}
