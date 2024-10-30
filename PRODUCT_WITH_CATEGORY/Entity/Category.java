package com.basic.ProductsWithCategory.Entity;
import java.util.*;
public class Category {
    private final int category_id;
    private final int capacity;
    private final String category_name;
    private int soldCount;
    private int soldCountByPrice;
    //private List<Products> productsList;
    public Category(int category_id,String category_name,int capacity)
    {
        this.category_id=category_id;
        this.category_name=category_name;
        this.capacity=capacity;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCountByPrice(int soldCountByPrice) {
        this.soldCountByPrice = soldCountByPrice;
    }

    public int getSoldCountByPrice() {
        return soldCountByPrice;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }


    public int getCapacity() {
        return capacity;
    }
}
