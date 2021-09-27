package model.service;

import model.products.Product;

import java.util.HashMap;

public interface Shop {

    HashMap<String, Product> getProductList();
    Integer getVarietyOfProducts();
    void addNewProduct(String name, Double price, Integer amountInStore);
    boolean removeProduct(String ID);
    Product getProduct(String ID);
    boolean registerTheOrder(HashMap<String, Integer> orderList);
}
