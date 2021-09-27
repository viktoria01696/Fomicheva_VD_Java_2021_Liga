package model.products;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EpicProductTest {

    @Mock
    EpicProduct epicProduct;

    @BeforeEach
    public void setUp() {
        epicProduct = new EpicProduct("", "", 10D, 10);
    }

    @Test
    void checkWrongAmountForBuying() {
        Integer realAmount = epicProduct.getAmountInStore();
        Integer amountForOrder = realAmount + 1;
        assertEquals(false,epicProduct.checkAmountForBuying(amountForOrder));
    }

    @Test
    void checkCorrectAmountForBuying() {
        Integer realAmount = epicProduct.getAmountInStore();
        Integer amountForOrder = realAmount - 1;
        assertEquals(true,epicProduct.checkAmountForBuying(amountForOrder));
    }

    @Test
    void reduceWrongAmountInStore() {
        Integer realAmount = epicProduct.getAmountInStore();
        Integer amountForOrder = realAmount + 1;
        assertFalse(epicProduct.reduceAmountInStore(amountForOrder));
        assertEquals(realAmount, epicProduct.getAmountInStore());
    }

    @Test
    void reduceCorrectAmountInStore() {
        assertEquals(10, epicProduct.getAmountInStore());
        Integer amountForOrder = epicProduct.getAmountInStore() - 1;
        assertTrue(epicProduct.reduceAmountInStore(amountForOrder));
        assertEquals(1, epicProduct.getAmountInStore());
    }
}