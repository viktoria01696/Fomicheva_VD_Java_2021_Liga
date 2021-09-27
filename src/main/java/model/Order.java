package model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import model.service.Shop;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter(AccessLevel.PRIVATE)
public class Order {

    private String ID;
    private User user;
    private Shop shop;
    private HashMap<String, Integer> orderList;
    private Date momentOfCreation;
    private static Integer orderCounter = 0;

    public Order(User user, Shop shop, HashMap<String, Integer> orderList){
        this.ID = String.valueOf(orderCounter++);
        this.momentOfCreation = new Date();
        this.user = user;
        this.shop = shop;
        this.orderList = orderList;
    }

    public void print(){
        System.out.println("Your basket consists of:");
        for (Map.Entry<String, Integer> product : this.orderList.entrySet()){
            System.out.println(String.format("Product with name %s in quantity: %d ",
                    shop.getProduct(product.getKey()).getProductName(), product.getValue()));
        }
    }

}
