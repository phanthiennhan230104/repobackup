package com.myRestaurant.manager.Dto;

import java.util.List;
import java.util.Set;

public class AreaDto {
    private String area_id;
    private String area_name;
    private List<TableDto> tables;

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

	public List<TableDto> getTables() {
		return tables;
	}

	public void setTables(List<TableDto> tables) {
		this.tables = tables;
	}
}
