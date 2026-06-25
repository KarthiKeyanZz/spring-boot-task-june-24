package com.stackly.inventory.model;

public class Product {

    private Long productId;
    private String productName;
    private String category;
    private Integer stock;
    private Double price;

    public Product() {
    }

    public Product(Long productId, String productName, String category,
                   Integer stock, Double price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
