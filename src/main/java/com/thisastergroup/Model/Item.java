package com.thisastergroup.Model;

public class Item {

    private String name;
    private String clothingType;
    private int price;
    private double discount;
    private String collection;
    private String path;

    public Item(String name, String clothingType, int price, double discount, String collection, String path) {
        this.name = name;
        this.clothingType = clothingType;
        this.price = price;
        this.discount = discount;
        this.collection = collection;
        this.path =path;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the clothingType
     */
    public String getClothingType() {
        return clothingType;
    }

    /**
     * @param clothingType the clothingType to set
     */
    public void setClothingType(String clothingType) {
        this.clothingType = clothingType;
    }

    /**
     * @return int return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return String return the collection
     */
    public String getCollection() {
        return collection;
    }

    /**
     * @param collection the collection to set
     */
    public void setCollection(String collection) {
        this.collection = collection;
    }

    /**
     * @return double return the discount
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public String getImage_path() {
        return path;
    }
    public void setImage_path(String image_path) {
        this.path = path;
    }
    public String toString() {
        return "Name: " + name + " Clothing Type: " + clothingType + " Price: " + price + " Discount: " + discount
                + " Collection: " + collection + " Image Path: " + path;
    }

}

