package com.myRestaurant.manager.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myRestaurant.manager.Dto.InvoiceDetailDto;
import com.myRestaurant.manager.Entities.InvoiceEntities;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntities, Integer> {
	 // Tìm hóa đơn chưa thanh toán theo tableId
    List<InvoiceEntities> findByTable_TableIdContainingIgnoreCaseAndInvoiceStatus(String tableId, boolean invoiceStatus);

    // Tìm tất cả hóa đơn chưa thanh toán
    List<InvoiceEntities> findByInvoiceStatus(boolean invoiceStatus);
    
    // Tìm hóa đơn theo invoiceId
    Optional<InvoiceEntities> findById(int invoiceId);
    
    @Query("SELECT new com.myRestaurant.manager.Dto.InvoiceDetailDto(m.dishName, im.quantity, im.totalPrice) " +
    	       "FROM InvoiceMenuEntities im " +
    	       "JOIN im.menuEntities m " +
    	       "JOIN im.invoiceEntities i " +
    	       "WHERE i.invoiceId = :invoiceId")
    List<InvoiceDetailDto> findInvoiceDetailsByInvoiceId(@Param("invoiceId") int invoiceId);
    
    @Query("SELECT MAX(i.invoiceId) FROM invoice i")
    Optional<Integer> findLatestInvoiceId();

}