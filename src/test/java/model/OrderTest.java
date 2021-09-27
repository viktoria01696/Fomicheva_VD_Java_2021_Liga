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
class OrderTest {

    @Mock
    User user;
    @Mock
    OnlineShop onlineShop;
    Order order;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Shop testShop = new OnlineShop(5);
        HashMap<String, Integer> productList = new HashMap<>();
        for (Map.Entry<String, Product> product: testShop.getProductList().entrySet()) {
            productList.put(product.getKey(), product.getValue().getAmountInStore());
        }
        order= new Order(user, onlineShop, productList);
    }

    @Test
    void printOrderTest() {
        Mockito.when(onlineShop.getProduct(any())).thenReturn(new EpicProduct("", "Product", 10D, 2));
        order.print();
        Mockito.verify(onlineShop, Mockito.times(order.getOrderList().size())).getProduct(any());
        assertNotNull(outputStreamCaptor.toString().trim());
    }

}