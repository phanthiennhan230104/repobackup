package com.myRestaurant.manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRestaurant.manager.Entities.TableEntities;
import com.myRestaurant.manager.Entities.AreaEntities;
import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableEntities, String> {
    List<TableEntities> findByAreaEntities(AreaEntities areaEntities);
}
