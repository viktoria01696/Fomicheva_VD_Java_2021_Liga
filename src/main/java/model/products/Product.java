package model.products;

public interface Product {

    String getProductName();
    Double getPrice();
    Integer getAmountInStore();
    boolean checkAmountForBuying(Integer amount);
    boolean reduceAmountInStore(Integer amount);
}
