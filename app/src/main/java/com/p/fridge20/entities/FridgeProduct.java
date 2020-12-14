package com.p.fridge20.entities;

public class FridgeProduct {
    private long id;
    private String fridgeProductName;
    private String fridgeProductAmount;

    public FridgeProduct(long id, String fridgeProductName, String fridgeProductAmount) {
        this.id = id;
        this.fridgeProductName = fridgeProductName;
        this.fridgeProductAmount = fridgeProductAmount;
    }

    public FridgeProduct() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFridgeProductName() {
        return fridgeProductName;
    }

    public void setFridgeProductName(String fridgeProductName) {
        this.fridgeProductName = fridgeProductName;
    }

    public String getFridgeProductAmount() {
        return fridgeProductAmount;
    }

    public void setFridgeProductAmount(String fridgeProductAmount) {
        this.fridgeProductAmount = fridgeProductAmount;
    }
}
