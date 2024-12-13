package com.myRestaurant.manager.Dto;

public class TableDto {
    private String table_id;
    private boolean table_status;
    
    private AreaDto areaDto;

    public String getTable_id() {
        return table_id;
    }
    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }
	public boolean isTable_status() {
		return table_status;
	}
	public void setTable_status(boolean table_status) {
		this.table_status = table_status;
	}
	public AreaDto getAreaDto() {
		return areaDto;
	}
	public void setAreaDto(AreaDto areaDto) {
		this.areaDto = areaDto;
	}
}
