package com.basic.ProductsWithCategory.Services;

import com.basic.ProductsWithCategory.Entity.Category;
import com.basic.ProductsWithCategory.Entity.Products;
import com.basic.ProductsWithCategory.Repositery.ProductsDetails;

import java.util.*;

public class ProductService extends ProductsDetails {
    private static final HashMap<Category,HashMap<Integer, Products>> hm=new HashMap<>();
    private static final List<Category> categoryList=new ArrayList<>();

    private HashMap<Integer,HashMap<String ,String>> billHashMap;
    Scanner sc=new Scanner(System.in);
    public void addCategory()
    {
        do {
            showCategory();
            System.out.println("Enter Category Name and their Capacity");
            String name = sc.next();
            int capacity = sc.nextInt();
            Category category=new Category(categoryList.size() + 1, name, capacity);
            categoryList.add(category);
            hm.put(category, new HashMap<>());
            System.out.println("Successfully Added");
            System.out.println("Do U want to Continue ... press 1");
        }while (sc.nextInt()==1);
        makeFinalReport();
    }
    public void addProduct()
    {
        Optional<Category> opt;
        int id=0,choice=-1;
        do {
            if(choice==-1 || choice==2) {
                showCategory();
                System.out.println("Enter Category Id...");
                id = sc.nextInt();
            }
            opt=getCategory(id);
            if(!opt.isPresent())
            {
                System.out.println("This Category is not found..");
                choice=2;
                continue;
            }
            Category c =opt.get();
            if (c.getCapacity() == hm.get(c).size()) {
                System.out.println("Could not  Capacity for Category " + c.getCategory_name() + " Exceeded");
                return;
            }
            System.out.println(c.getCategory_id() + "\t" + c.getCategory_name());
            System.out.println("Enter Product Name and Their Price and Their Stock...");
            String product_name = sc.next();
            int product_price = sc.nextInt();
            int stock=sc.nextInt();
            int size = hm.get(c).size();
            Products p=isAvailable(c,product_name,product_price);
            if(p!=null)
                p.setStock(stock+p.getStock());
            else
                hm.get(c).put(size + 1, new Products(size + 1, product_price, product_name,stock));
            System.out.println("Successfully Added");
            System.out.println("To add Product in Same Category "+c.getCategory_name()+" press 1..");
            System.out.println("TO change the Category Press 2...");
            System.out.println("OtherWise Press 3..");
            choice=sc.nextInt();
        }
        while (choice==1|| choice==2);
        makeFinalReport();

    }
    private Products isAvailable(Category c,String product_name,int product_price)
    {
        Products p;
        for(Map.Entry<Integer,Products> map:hm.get(c).entrySet())
        {
            p=map.getValue();
            if(p.getProduct_name().equals(product_name) && p.getProduct_price()==product_price)
                return p;
        }
        return null;
    }
    private Optional<Category> getCategory(int id)
    {
        return categoryList.stream().filter(i->i.getCategory_id()==id).findFirst();
    }
    public void showCategory()
    {
        System.out.println("Category_Id\tCategory_Name\tCategory_Capacity");
        for(Category c:categoryList)
            System.out.println(c.getCategory_id()+"\t"+c.getCategory_name()+"\t"+c.getCapacity());
    }
    public void showProducts(int id)
    {
        Category c;
        Optional<Category> opt=getCategory(id);
        if(!opt.isPresent()) {
            System.out.println("This Category Id  does not Exist..");
            return;
        }
        c=opt.get();
        Comparator<Products> sortThis= (o1, o2) -> {
            if(o1.getProduct_price()<o2.getProduct_price())
                return 1;
            else if(o1.getProduct_price()>o2.getProduct_price())
                return -1;
            return 0;
        };


        HashMap<Integer,Products> hashMap=hm.get(c);
        if(hashMap==null)
            return;
        if(hashMap.size()==0)
        {
            System.out.println("No Product Available in this Category!!");
            return;
        }
        System.out.println("Product Id\tProduct Name\tProduct Price\tStock");

        List<Products> list=new ArrayList<>();
        for(Map.Entry<Integer,Products> m: hashMap.entrySet())
            list.add(m.getValue());
        PriorityQueue<Products> pq = new PriorityQueue<>(sortThis);
        pq.addAll(list);
        Products p;
        while (!pq.isEmpty()) {
            p= pq.poll();
            System.out.println(p.getProduct_id() + "\t" + p.getProduct_name() + "\t" + p.getProduct_price()
            +"\t"+p.getStock());
        }
    }
    private Products getProduct(Category category,int id)
    {
        HashMap<Integer,Products> hashMap=hm.get(category);
        for(Map.Entry<Integer,Products> map:hashMap.entrySet())
        {
            
            if(map.getKey()==id)
                return map.getValue();
        }
        return null;
    }
    public void buyProduct() {
        int choice ;
        Optional<Category> opt;
        int categoryId;
        billHashMap=new HashMap<>();
        HashMap<String,String> tempHm=new HashMap<>();
        do {
            showCategory();
            System.out.println("Enter Category Id ..");
            categoryId=sc.nextInt();
            opt=getCategory(categoryId);
            if(!opt.isPresent())
            {
                System.out.println("Its not available ...");
                choice=3;
                continue;
            }
            Category c = opt.get();
            do {
                showProducts(categoryId);
                System.out.println("Enter Product Id..");
                int id = sc.nextInt();
                Products p = getProduct(c, id);
                if(p==null)
                {
                    System.out.println("No Products available...");
                    choice=2;
                    continue;
                }
                int quantity;
                int stock = p.getStock();
                if (stock == 0) {
                    System.out.println("Its not available..");
                    return;
                }
                System.out.println(p.getProduct_id() + "\t" + p.getProduct_name()
                        + "\t" + p.getProduct_price());

                do {
                    do {
                        System.out.println("Enter  Quantity ...");
                        quantity = sc.nextInt();
                        if (quantity > stock)
                            System.out.println("OOPS...Stock is.." + stock);
                        else
                            break;
                    }
                    while (true);
                    p.setStock(p.getStock() - quantity);
                    p.setSoldCount(quantity+p.getSoldCount());

                    int currentPrice=quantity * p.getProduct_price();

                    p.setSoldCountByPrice(currentPrice+p.getSoldCountByPrice());

                    c.setSoldCount(quantity+c.getSoldCount());
                    c.setSoldCountByPrice(currentPrice+c.getSoldCountByPrice());

                    if(billHashMap.get(p.getProduct_id())!=null)
                    {
                        tempHm=billHashMap.get(p.getProduct_id());
                        tempHm.put("priceForProduct", (Integer.parseInt(tempHm.get("priceForProduct"))+currentPrice)+"");
                        tempHm.put("quantity",(quantity+Integer.parseInt(tempHm.get("quantity")))+"");
                    }
                    else
                    {
                        tempHm=new HashMap<>();
                        tempHm.put("productName",p.getProduct_name());
                        tempHm.put("rate",p.getProduct_price()+"");
                        tempHm.put("priceForProduct", currentPrice+"");
                        tempHm.put("quantity",quantity+"");
                        billHashMap.put(p.getProduct_id(),tempHm);
                    }
                    System.out.println("Successfully Good Job..");
                    System.out.println("If U want to Continue to Buy Same Product Press 1" +
                            "\n If U want to continue in same Category Press 2" +
                            "\n If U want to continue in another Category Press 3" +
                            "\n If U want to Exit Welcome ... Press any Number...\n");
                    choice = sc.nextInt();
                } while (choice == 1);
            }
            while (choice == 2);
        }while(choice==3);
        billGenerator();
        makeFinalReport();
    }
    private void makeFinalReport()
    {
        setHm(hm);
        setCategoryList(categoryList);
    }
    private void billGenerator()
    {
        HashMap<String,String> temp;
        System.out.println("Product Id\t\tProduct Name\t\t" +
                "Product Rate\t\tQuantity\t\tProduct Price");
        int total=0;
        for(Map.Entry<Integer,HashMap<String,String>> map:billHashMap.entrySet())
        {
            temp=map.getValue();
            System.out.println(map.getKey()+"\t\t"+temp.get("productName")+"\t\t"+
                    temp.get("rate")+"\t\t"+temp.get("quantity")+"\t\t"+
                    temp.get("priceForProduct"));
            total+=Integer.parseInt(temp.get("priceForProduct"));
        }
        System.out.println("TOTAL PRICE ===>"+total);
    }
}