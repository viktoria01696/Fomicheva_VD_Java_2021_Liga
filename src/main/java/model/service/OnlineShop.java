package model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import model.products.Product;
import model.products.EpicProduct;

import java.util.*;

@Getter
@NoArgsConstructor
public class OnlineShop implements Shop{

    private HashMap<String, Product> productList;
    private Integer varietyOfProducts;

    public OnlineShop(Integer varietyOfProducts){
        this.varietyOfProducts = varietyOfProducts;
        productList = new HashMap<>(varietyOfProducts);
        for (int i = 1; i < varietyOfProducts + 1; i++) {
            String ID = "A000"+i;
            productList.put(ID, new EpicProduct(ID, "Product" + i, 10.99 + i, i+10));
        }
    }

    @Override
    public void addNewProduct(String name, Double price, Integer amountInStore){
        varietyOfProducts++;
        String ID = "A000"+varietyOfProducts;
        productList.put(ID, new EpicProduct(ID, name, price, amountInStore));
    }

    @Override
    public boolean removeProduct(String ID){
        if (productList.containsKey(ID)){
            productList.remove(ID);
            varietyOfProducts--;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Product getProduct(String ID){
        return productList.getOrDefault(ID, null);
    }

    @Override
    public boolean registerTheOrder(HashMap<String, Integer> orderList){
        if (checkOrderContents(orderList)){
            updateProductAmount(orderList);
            return true;
        }
        return false;
    }

    private boolean checkOrderContents(HashMap<String, Integer> orderList){
        for (Map.Entry<String, Integer> product : orderList.entrySet()){
            if (!productList.containsKey(product.getKey())){
                return false;
            }
            boolean isEnoughAmountForSale = productList.get(product.getKey())
                    .checkAmountForBuying(product.getValue());
            if (!isEnoughAmountForSale){
                return false;
            }
        }
        return true;
    }

    private void updateProductAmount(HashMap<String, Integer> orderList){
        for (Map.Entry<String, Integer> product : orderList.entrySet()){
            productList.get(product.getKey()).reduceAmountInStore(product.getValue());
        }
    }

}
