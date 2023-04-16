package shop;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {
    Order order1;
    Order order2;
    ShoppingCart cart = new ShoppingCart();

    public OrderTest() {
    }

    @BeforeEach
    public void setup() {
        Item item = new StandardItem(0, "siberia", 130, "tobacco", 1);
        this.cart.addItem(item);
        this.order1 = new Order(this.cart, "Radko", "Praha", 2);
        this.order2 = new Order(this.cart, "Radko", "Praha");
    }

    @Test
    public void constructorTest() {
        Assertions.assertEquals(this.cart.getCartItems(), this.order1.getItems());
        Assertions.assertEquals("Radko", this.order1.getCustomerName());
        Assertions.assertEquals("Praha", this.order1.getCustomerAddress());
        Assertions.assertEquals(2, this.order1.getState());

        Assertions.assertEquals(this.cart.getCartItems(), this.order2.getItems());
        Assertions.assertEquals("Radko", this.order2.getCustomerName());
        Assertions.assertEquals("Praha", this.order2.getCustomerAddress());
        Assertions.assertEquals(0, this.order2.getState());

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Order(this.cart, null, "Praha", 2));
    }
}
