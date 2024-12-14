package com.myRestaurant.manager.Service;

import com.myRestaurant.manager.Dto.ViewOrderListDto;
import com.myRestaurant.manager.Entities.InvoiceEntities;
import com.myRestaurant.manager.Repository.ViewOrderListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

@Service
public class ViewOrderListService {

    @Autowired
    private ViewOrderListRepository invoiceRepository;

    public List<ViewOrderListDto> getInvoicesWithTables() {
        List<InvoiceEntities> invoices = invoiceRepository.findAllWithTables();
        List<ViewOrderListDto> invoiceDTOs = new ArrayList<>();

        for (InvoiceEntities invoice : invoices) {
            long minutesElapsed = (System.currentTimeMillis() - invoice.getCreateDate().getTime()) / 1000 / 60;
            ViewOrderListDto dto = new ViewOrderListDto(invoice.getTable().getTableId(), minutesElapsed);
            dto.setInvoiceStatus(invoice.isInvoiceStatus());  // Thêm invoiceStatus vào DTO
            invoiceDTOs.add(dto);
        }

        invoiceDTOs.sort(Comparator.comparingLong(ViewOrderListDto::getMinutesElapsed).reversed());

        return invoiceDTOs;
    }
}
