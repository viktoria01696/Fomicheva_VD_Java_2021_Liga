package model;

import model.products.Product;
import model.service.OnlineShop;
import model.service.Shop;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Shop shop = new OnlineShop(5);

        User user = new User("Lewis", "Carroll", "Daresbury",
                "LK@yandex.ru", "password", shop);
        Basket basket = user.getBasket();

        for (Map.Entry<String, Product> product : shop.getProductList().entrySet()) {
            basket.addProduct(product.getKey(), 1);
        }

        Order order = user.makeOrder();
        if (order != null) {
            order.print();
        }
    }
}
