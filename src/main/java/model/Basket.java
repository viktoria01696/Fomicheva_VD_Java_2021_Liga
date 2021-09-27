package model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import model.products.Product;
import model.service.Shop;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Basket {

    private final User user;
    private final Shop shop;
    @Setter
    private boolean becameOrder = false;
    @Setter(AccessLevel.PRIVATE)
    private HashMap<String, Integer> basket;
    @Setter(AccessLevel.PRIVATE)
    private int uniqueProductAmount = 0;

    public Basket(User user, Shop shop) {
        this.user = user;
        this.shop = shop;
        this.basket = new HashMap<>();
    }

    public boolean addProduct(String ID, @Min(1) Integer amount) {
        if (!becameOrder) {
            if (basket.containsKey(ID)) {
                basket.put(ID, basket.get(ID) + amount);
                uniqueProductAmount++;
            } else {
                basket.put(ID, amount);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean removeProduct(String ID) {
        if (!becameOrder) {
            if (basket.containsKey(ID)) {
                basket.remove(ID);
                uniqueProductAmount--;
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean changeProductAmount(String ID, @Min(1) Integer newAmount) {
        if (!becameOrder) {
            if (basket.containsKey(ID)) {
                basket.put(ID, newAmount);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean deleteAllProductsAndStartOver() {
        if (!becameOrder) {
            this.basket = new HashMap<>();
            uniqueProductAmount = 0;
            return true;
        } else {
            return false;
        }
    }

    public Order becomeAnOrder() {
        if (basket.size() == 0){
            System.out.println("Before creating an order, you must add something to the basket!");
            return null;
        }
        if (shop.registerTheOrder(this.basket)) {
            this.becameOrder = true;
            deleteAllProductsAndStartOver();
            return new Order(this.user, this.shop, this.basket);
        }
        System.out.println("During your absence, the quantity of some goods in the warehouse has decreased.\n" +
                "Order creation will be available after changing your shopping cart " +
                "in accordance with the current information on the site.");
        return null;
    }

    public Double checkBasketPrice(){
        Double sum = 0D;
        for (Map.Entry<String, Integer> product : this.basket.entrySet()){
            Product productFromStore = this.shop.getProduct(product.getKey());
            if(productFromStore != null){
                sum += (productFromStore.getPrice() * product.getValue());
            }
        }
        return sum;
    }

}
