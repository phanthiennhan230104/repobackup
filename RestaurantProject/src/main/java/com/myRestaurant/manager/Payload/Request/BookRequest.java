package com.myRestaurant.manager.Payload.Request;

public class BookRequest {
	private int bookId;
    private String tableId;
    private int numberOfPeople;
    private String bookDate;
    private String bookTime;

    // Getters and setters
    
    
    public String getTableId() {
        return tableId;
    }

    public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
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
