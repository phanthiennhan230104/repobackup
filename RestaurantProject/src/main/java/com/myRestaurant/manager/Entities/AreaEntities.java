package com.myRestaurant.manager.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity(name = "areas")
public class AreaEntities {
    @Id
    @Column(name = "area_id")
    private String areaId;

    @Column(name = "area_name")
    private String areaName;

    @OneToMany(mappedBy = "areaEntities")
    private Set<TableEntities> tables;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Set<TableEntities> getTables() {
        return tables;
    }

    public void setTables(Set<TableEntities> tables) {
        this.tables = tables;
    }
}

