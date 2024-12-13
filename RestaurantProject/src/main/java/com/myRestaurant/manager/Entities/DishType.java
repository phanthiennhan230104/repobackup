package com.myRestaurant.manager.Entities;

public enum DishType {
    Dish,
    Drink,
    Combo;
	
    public static DishType fromString(String type) {
        for (DishType dishType : DishType.values()) {
            if (dishType.name().equalsIgnoreCase(type)) {
                return dishType;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + type);
    }
}