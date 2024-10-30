package com.basic.ProductsWithCategory.Entity;

public class Products {
    private final int product_id,product_price;
    private final String product_name;
    private int buyCount,stock;
    private int buyCountByPrice;
    public Products(int product_id,int product_price,String product_name,int stock)
    {
        this.product_id=product_id;
        this.product_name=product_name;
        this.product_price=product_price;
        this.buyCount=0;
        this.buyCountByPrice=0;
        this.stock=stock;
    }

    public void setSoldCountByPrice(int buyCountByPrice) {
        this.buyCountByPrice = buyCountByPrice;
    }

    public int getSoldCountByPrice() {
        return buyCountByPrice;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    public int getStock() {
        return stock;
    }

    public void setSoldCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public int getSoldCount() {
        return buyCount;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getProduct_price() {
        return product_price;
    }

    public String getProduct_name() {
        return product_name;
    }
}