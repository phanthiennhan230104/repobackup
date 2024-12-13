package com.myRestaurant.manager.Repository;


import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myRestaurant.manager.Entities.InvoiceEntities;
import com.myRestaurant.manager.Entities.InvoiceMenuEntities;
import com.myRestaurant.manager.Entities.InvoiceMenuKey;
import com.myRestaurant.manager.Entities.MenuEntities;

@Repository
public interface InvoiceMenuRepository extends JpaRepository<InvoiceMenuEntities, InvoiceMenuKey> {
	 Optional<InvoiceMenuEntities> findByInvoiceEntitiesAndMenuEntities(InvoiceEntities invoiceEntities, MenuEntities menuEntities);
	 
	 @Query("SELECT SUM(im.totalPrice) FROM InvoiceMenuEntities im WHERE im.invoiceEntities.invoiceId = :invoiceId")
	    BigDecimal sumTotalPriceByInvoiceId(@Param("invoiceId") int invoiceId);
}


