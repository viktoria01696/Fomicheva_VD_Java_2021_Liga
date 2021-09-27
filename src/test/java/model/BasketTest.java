package model;

import model.products.EpicProduct;
import model.products.Product;
import model.service.OnlineShop;
import model.service.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BasketTest {

    private String testID;
    private String newID;
    private int orderListSizeAtTheBeginning;
    @Mock
    private User user;
    @Mock
    private OnlineShop shop;
    @InjectMocks
    private Basket basket;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Integer varietyOfProducts = 5;
        Shop testShop = new OnlineShop(varietyOfProducts);
        HashMap<String, Integer> productList = new HashMap<>();
        for (Map.Entry<String, Product> product: testShop.getProductList().entrySet()) {
            productList.put(product.getKey(), product.getValue().getAmountInStore());
        }
        testID = productList.keySet().iterator().next();
        newID = (String) productList.keySet().toArray()[1];
        basket.getBasket().put(testID, productList.get(testID));
        orderListSizeAtTheBeginning = basket.getBasket().size();
    }

    @Test
     void addNewProductTest() {
        assertFalse(basket.getBasket().containsKey(newID));
        assertTrue(basket.addProduct(newID, 5));
        assertTrue(basket.getBasket().containsKey(newID));
        assertEquals(orderListSizeAtTheBeginning + 1, basket.getBasket().size());
    }

    @Test
    void addNewButNotUniqueProductTest() {
        assertTrue(basket.getBasket().containsKey(testID));
        Integer lastAmount = basket.getBasket().get(testID);
        assertTrue(basket.addProduct(testID, 5));
        assertEquals(lastAmount + 5, basket.getBasket().get(testID));
        assertEquals(orderListSizeAtTheBeginning, basket.getBasket().size());
    }

    @Test
    void addNewProductAfterOrderCreationTest() {
        basket.setBecameOrder(true);
        assertTrue(basket.getBasket().containsKey(testID));
        Integer lastAmount = basket.getBasket().get(testID);
        assertFalse(basket.addProduct(testID, 5));
        assertEquals(lastAmount, basket.getBasket().get(testID));
        assertEquals(orderListSizeAtTheBeginning, basket.getBasket().size());
    }

    @Test
    void removeCorrectProductTest() {
        assertTrue(basket.getBasket().containsKey(testID));
        assertTrue(basket.removeProduct(testID));
        assertFalse(basket.getBasket().containsKey(testID));
        assertEquals(orderListSizeAtTheBeginning- 1, basket.getBasket().size());
    }

    @Test
    void removeWrongProductTest() {
        assertFalse(basket.getBasket().containsKey(newID));
        assertFalse(basket.removeProduct(newID));
        assertEquals(orderListSizeAtTheBeginning, basket.getBasket().size());
    }
    @Test
    void removeProductAfterOrderCreationTest() {
        basket.setBecameOrder(true);
        assertTrue(basket.getBasket().containsKey(testID));
        assertFalse(basket.removeProduct(testID));
        assertEquals(orderListSizeAtTheBeginning, basket.getBasket().size());
    }

    @Test
    void changeCorrectProductAmountTest() {
        assertTrue(basket.getBasket().containsKey(testID));
        assertTrue(basket.changeProductAmount(testID, 2));
        assertEquals(2, basket.getBasket().get(testID));
        assertEquals(orderListSizeAtTheBeginning, basket.getBasket().size());
    }

    @Test
    void changeWrongProductAmountTest() {
        assertFalse(basket.getBasket().containsKey(newID));
        assertFalse(basket.changeProductAmount(newID, 2));
    }

    @Test
    void changeProductAmountAfterOrderCreationTest() {
        basket.setBecameOrder(true);
        Integer lastAmount = basket.getBasket().get(testID);
        assertTrue(basket.getBasket().containsKey(testID));
        assertFalse(basket.changeProductAmount(testID, 2));
        assertEquals(lastAmount, basket.getBasket().get(testID));
    }

    @Test
    void deleteAllProductsAndStartOverTest() {
        assertTrue(basket.deleteAllProductsAndStartOver());
        assertEquals(new HashMap<String, Integer>(), basket.getBasket());
        assertEquals(0, basket.getBasket().size());
    }

    @Test
    void deleteAllProductsAndStartOverAfterOrderCreationTest() {
        basket.setBecameOrder(true);
        HashMap<String, Integer> productList = basket.getBasket();
        assertFalse(basket.deleteAllProductsAndStartOver());
        assertEquals(productList, basket.getBasket());
    }

    @Test
    void becomeAnOrderWithCorrectBasketTest() {
        Mockito.when(shop.registerTheOrder(any())).thenReturn(true);
        Order order = basket.becomeAnOrder();
        assertNotNull(order);
        Mockito.verify(shop, Mockito.times(1)).registerTheOrder(basket.getBasket());
        assertEquals(order.getShop(), shop);
        assertEquals(order.getUser(), user);
    }
    @Test
    void becomeAnOrderWithWrongBasketTest() {
        String errorMessage = "During your absence, the quantity of some goods in the warehouse has decreased.\n" +
                "Order creation will be available after changing your shopping cart " +
                "in accordance with the current information on the site.";
        Mockito.when(shop.registerTheOrder(any())).thenReturn(false);
        Order order = basket.becomeAnOrder();
        assertNull(order);
        Mockito.verify(shop, Mockito.times(1)).registerTheOrder(basket.getBasket());
        assertEquals(errorMessage, outputStreamCaptor.toString()
                .trim());
    }
    @Test
    void becomeAnOrderWithNullBasketTest() {
        String errorMessage = "Before creating an order, you must add something to the basket!";
        basket.getBasket().clear();
        assertEquals(0, basket.getBasket().size());
        Order order = basket.becomeAnOrder();
        assertNull(order);
        Mockito.verify(shop, Mockito.times(0)).registerTheOrder(basket.getBasket());
        assertEquals(errorMessage, outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void checkBasketPrice() {
        Double sum = 0D;
        Mockito.when(shop.getProduct(any(String.class))).thenReturn(
                new EpicProduct("", "", 10D, 2) {
        });
        for (Map.Entry<String, Integer> product : basket.getBasket().entrySet()){
            sum +=product.getValue() * 10D;
        }
        assertEquals(sum, basket.checkBasketPrice());
        Mockito.verify(shop, Mockito.times(basket.getBasket().size())).getProduct(any());
    }
}