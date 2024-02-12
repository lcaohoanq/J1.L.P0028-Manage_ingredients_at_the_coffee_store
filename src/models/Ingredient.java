package models;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String code;
    private String name;
    private int quantity;
    private String unit;
    private double price; //price per unit

    public Ingredient(){
    }

    public Ingredient(String code, String name, int quantity, String unit, double price) {
        this.code = code.toUpperCase();
        this.name = name.toLowerCase();
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void showIngredient(){
        System.out.printf("| %-5s | %-15s | %10d | %5s | %15.0f |\n",code,name,quantity,unit,price);
    }

    @Override
    public String toString() {
        return String.format("| %-5s | %-15s | %10d | %5s | %15.0f |\n", code, name, quantity, unit, price);
    }
}
