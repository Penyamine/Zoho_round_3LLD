package com.basic.ProductsWithCategory;

import com.basic.ProductsWithCategory.Services.ProductService;
import com.basic.ProductsWithCategory.Services.ReportService;

import java.util.Scanner;

public class RunSystem {
    public static void main(String[] args) {
        ProductService productService=new ProductService();
        int choice;
        Scanner sc=new Scanner(System.in);
        do {
            System.out.println("1.Add Category\n2.Add product\n3.show Category " +
                    "\n4.show products \n5.Buy Product \n6.Report \n7.Exit");
            choice=sc.nextInt();
            switch (choice) {
                case 1:
                    productService.addCategory();
                    break;
                case 2:
                    productService.addProduct();
                    break;
                case 3:
                    productService.showCategory();
                    break;
                case 4:
                    System.out.println("Enter Category Id...");
                    productService.showProducts(sc.nextInt());
                    break;
                case 5:
                    productService.buyProduct();
                    break;
                case 6:
                    System.out.println("1.By Product Name\n2.By Category\n");
                    int take,ch=sc.nextInt();
                    ReportService reportService=new ReportService();
                    switch (ch)
                    {
                        case 1:
                            System.out.println("1.By Quantity Count\n2.Price Count\n");
                            take=sc.nextInt();
                            if(take==1)
                                reportService.reportByCountInProducts();
                            else
                                reportService.reportByPriceInProducts();
                            break;
                        case 2:
                            System.out.println("1.By Quantity Count\n2.Price Count\n");
                            take=sc.nextInt();
                            if(take==1)
                                reportService.reportByCountInCategory();
                            else
                                reportService.reportByPriceInCategory();
                            break;

                    }
                    break;
            }
        }
        while (true);
    }
}