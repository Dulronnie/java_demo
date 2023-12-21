package org.example.entity;

/**
 * @author hongchuzhen
 * @date 12/21/2023
 */
public class Product {
    private String name;
    private String sku;
    private double price;

    public Product() {
    }

    public Product(String name, String sku, double price) {
        this.name = name;
        this.sku = sku;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
