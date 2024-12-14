package com.myRestaurant.manager.Repository;

import com.myRestaurant.manager.Dto.DishOrderDto;
import com.myRestaurant.manager.Entities.InvoiceMenuEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DishOrderRepository extends JpaRepository<InvoiceMenuEntities, Integer> {
    List<InvoiceMenuEntities> findByInvoiceEntities_table_tableId(String tableId); // Fetch dishes by table ID
}
