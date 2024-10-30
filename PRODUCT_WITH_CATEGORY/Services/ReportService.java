package com.basic.ProductsWithCategory.Services;

import com.basic.ProductsWithCategory.Entity.Category;
import com.basic.ProductsWithCategory.Entity.Products;
import com.basic.ProductsWithCategory.Repositery.ProductsDetails;

import java.util.*;

public class ReportService extends ProductsDetails {
    private  final HashMap<Category,HashMap<Integer, Products>> hm=getHm();
    private  final List<Category> categoryList=getCategoryList();
    PriorityQueue<Products> pq;
    PriorityQueue<Category> pq2;
    public void reportByCountInProducts()
    {
        Products p;
        pq=new PriorityQueue<>((x,y)->y.getSoldCount()-x.getSoldCount());

        for(Map.Entry<Category,HashMap<Integer, Products>> map:hm.entrySet())
            for(Map.Entry<Integer,Products> map2:map.getValue().entrySet())
                pq.add(map2.getValue());

        System.out.println("Product Id \t Product Name \t Product Sold Count");
        while(!pq.isEmpty())
        {
            p=pq.poll();
            System.out.println(p.getProduct_id()+"\t"+p.getProduct_name()+
                    "\t"+p.getSoldCount());
        }
    }
    public void reportByPriceInProducts()
    {
        Products p;

        pq=new PriorityQueue<>((x,y)->y.getProduct_price()-x.getProduct_price());

        for(Map.Entry<Category,HashMap<Integer, Products>> map:hm.entrySet())
            for(Map.Entry<Integer,Products> map2:map.getValue().entrySet())
                pq.add(map2.getValue());

        System.out.println("Product Id \t Product Name \t Product Sold Price");
        while(!pq.isEmpty())
        {
            p=pq.poll();
            System.out.println(p.getProduct_id()+"\t"+p.getProduct_name()+
                    "\t"+p.getSoldCountByPrice());
        }

    }
    public void reportByCountInCategory()
    {
        pq2=new PriorityQueue<>((x,y)->y.getSoldCount()-x.getSoldCount());
        pq2.addAll(categoryList);
        Category c;
        System.out.println("Category Id\tCategory Name\tSoldCount");
        while(!pq2.isEmpty())
        {
            c=pq2.poll();
            System.out.println(c.getCategory_id()+"\t"+c.getCategory_name()+"\t"+c.getSoldCount());
        }
    }
    public void reportByPriceInCategory()
    {
        pq2=new PriorityQueue<>((x,y)->y.getSoldCountByPrice()-x.getSoldCountByPrice());
        pq2.addAll(categoryList);
        Category c;
        System.out.println("Category Id\tCategory Name\tSoldCountByPrice");
        while(!pq2.isEmpty())
        {
            c=pq2.poll();
            System.out.println(c.getCategory_id()+"\t"+c.getCategory_name()+"\t"+c.getSoldCountByPrice());
        }
    }

}