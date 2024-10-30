package com.basic.ProductsWithCategory.Repositery;

import com.basic.ProductsWithCategory.Entity.Category;
import com.basic.ProductsWithCategory.Entity.Products;
import java.util.HashMap;
import java.util.List;

public class ProductsDetails  {
    private   static HashMap<Category, HashMap<Integer, Products>> hm;
    private   static List<Category> categoryList;

    public  HashMap<Category, HashMap<Integer, Products>> getHm() {
        return hm;
    }

    public  List<Category> getCategoryList() {
        return categoryList;
    }

    public void setHm(HashMap<Category, HashMap<Integer, Products>> hm)
    {
        ProductsDetails.hm =hm;
    }

    public void setCategoryList(List<Category> categoryList)
    {
        ProductsDetails.categoryList =categoryList;
    }
}