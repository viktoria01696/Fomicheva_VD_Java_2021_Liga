package model.service;

import model.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.reflect.Whitebox;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OnlineShopTest {

    private HashMap<String, Integer> orderList;
    private final Integer varietyOfProducts = 5;
    @Mock
    private OnlineShop onlineShop;

    @BeforeEach
    public void setUp() {
        onlineShop = new OnlineShop(varietyOfProducts);
        Shop testShop = new OnlineShop(varietyOfProducts);
        orderList = new HashMap<>();
        for (Map.Entry<String, Product> product: testShop.getProductList().entrySet()) {
            orderList.put(product.getKey(), product.getValue().getAmountInStore());
        }
    }

    @Test
    void addNewProductToStoreTest() {
        assertFalse(onlineShop.getProductList().values().stream()
                .anyMatch(product -> product.getProductName().contains("CompletelyUniqueCane")));
        onlineShop.addNewProduct("CompletelyUniqueCane", 10D, 10);
        assertEquals(varietyOfProducts + 1, onlineShop.getProductList().size());
        assertEquals(1, onlineShop.getProductList().values().stream()
                .filter(product -> product.getProductName().contains("CompletelyUniqueCane")).count());
    }

    @Test
    void removeCorrectProductFromStoreTest(){
        String deletedID = orderList.keySet().iterator().next();
        assertTrue(onlineShop.getProductList().keySet().stream()
                .anyMatch(ID -> ID.equals(deletedID)));
        assertTrue(onlineShop.removeProduct(deletedID));
        assertNotEquals(varietyOfProducts, onlineShop.getProductList().size());
        assertTrue(onlineShop.getProductList().keySet().stream()
                .noneMatch(ID -> ID.equals(deletedID)));
    }

    @Test
    void removeWrongProductFromStoreTest(){
        assertTrue(onlineShop.getProductList().keySet().stream()
                .noneMatch(ID -> ID.equals("A1111")));
        assertFalse(onlineShop.removeProduct("A1111"));
        assertEquals(varietyOfProducts, onlineShop.getProductList().size());
    }

    @Test
    void getCorrectProductTest() {
        String existingID = orderList.keySet().iterator().next();
        assertTrue(onlineShop.getProductList().keySet().stream()
                .anyMatch(ID -> ID.equals(existingID)));
        assertNotEquals(null,onlineShop.getProduct(existingID));
    }

    @Test
    void getWrongProductTest() {
        assertTrue(onlineShop.getProductList().keySet().stream()
                .noneMatch(ID -> ID.equals("A1111")));
        assertNull(onlineShop.getProduct("A1111"));
    }

    @Test
    void registerTheOrderWithWrongProductNumberTest() throws Exception {
        HashMap<String, Integer> orderListTest = new HashMap<>(orderList);
        orderListTest.put("A1111", 1);
        //сравнение количества уникальных ключей в магазине и в списке покупок
        assertNotEquals(orderListTest.keySet().size(), onlineShop.getProductList().keySet().size());
        //результат работы приватного метода
        assertFalse((boolean) Whitebox.invokeMethod(onlineShop, "checkOrderContents", orderListTest));
        //результат работы основного метода
        assertFalse(onlineShop.registerTheOrder(orderListTest));
        //проверка неизменности количества товаров в наличии после попытки оформления заказа
        assertEquals(orderList.values().stream().collect(Collectors.toSet()),
                onlineShop.getProductList().values().stream().map(Product::getAmountInStore).collect(Collectors.toSet()));
    }

    @Test
    void registerTheOrderWithWrongProductAmountTest() throws Exception {
        HashMap<String, Integer> orderListTest = new HashMap<>(orderList);
        orderListTest.put(orderListTest.keySet().iterator().next(), 1000);
        //сравнение количества уникальных ключей в магазине и в списке покупок
        assertEquals(orderListTest.keySet().size(), onlineShop.getProductList().keySet().size());
        //сравнение самих множеств уникальных ключей
        assertEquals(orderListTest.keySet(),
                onlineShop.getProductList().keySet().stream().filter(orderListTest.keySet()::contains).collect(Collectors.toSet()));
        //результат работы приватного метода
        assertFalse((boolean) Whitebox.invokeMethod(onlineShop, "checkOrderContents", orderListTest));
        //результат работы основного метода
        assertFalse(onlineShop.registerTheOrder(orderListTest));
        //проверка неизменности количества товаров в наличии после попытки оформления заказа
        assertEquals(orderList.values().stream().collect(Collectors.toSet()),
                onlineShop.getProductList().values().stream().map(Product::getAmountInStore).collect(Collectors.toSet()));
    }

    @Test
    void registerCorrectOrder() throws Exception {
        HashMap<String, Integer> orderListTest = new HashMap<>(orderList);
        //сравнение количества уникальных ключей в магазине и в списке покупок
        assertEquals(orderListTest.keySet().size(), onlineShop.getProductList().keySet().size());
        //сравнение самих множеств уникальных ключей
        assertEquals(orderListTest.keySet(),
                onlineShop.getProductList().keySet().stream().filter(orderListTest.keySet()::contains).collect(Collectors.toSet()));
        //результат работы приватного метода
        assertTrue((boolean) Whitebox.invokeMethod(onlineShop, "checkOrderContents", orderListTest));
        //результат работы основного метода
        assertTrue(onlineShop.registerTheOrder(orderListTest));
        //проверка изменения количества товаров в наличии после оформления заказа
        assertEquals(onlineShop.getProductList().size(), onlineShop.getProductList().values().stream()
                .filter(product -> product.getAmountInStore() == 0).count());
    }

}