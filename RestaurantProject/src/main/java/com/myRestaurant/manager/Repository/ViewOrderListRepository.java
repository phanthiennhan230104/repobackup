package com.myRestaurant.manager.Repository;

import com.myRestaurant.manager.Entities.InvoiceEntities;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewOrderListRepository extends CrudRepository<InvoiceEntities, Integer> {

    @Query("SELECT i FROM invoice i WHERE i.table IS NOT NULL")
    List<InvoiceEntities> findAllWithTables();
}