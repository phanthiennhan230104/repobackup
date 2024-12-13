package com.myRestaurant.manager.Dto;

public class BookDto {
	private int id;
    private String tableId;
    private int numberOfPeople;
    private String bookDate;
    private String bookTime;

    // Getters and setters
    
    public String getTableId() {
        return tableId;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }
}