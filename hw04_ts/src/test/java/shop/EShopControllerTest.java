package shop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.NoItemInStorage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class EShopControllerTest {
    EShopController controller;
    int[] itemCount;
    Item[] storageItems;

    private final ByteArrayOutputStream newOut = new ByteArrayOutputStream();
    private final ByteArrayOutputStream newErr = new ByteArrayOutputStream();
    private final PrintStream ogOut;
    private final PrintStream ogErr;

    public EShopControllerTest() {
        this.ogOut = System.out;
        this.ogErr = System.err;
    }

    @BeforeEach
    void setUp() {
        controller = new EShopController();
        controller.startEShop();

        /* make up an artificial data */

        itemCount = new int[]{10, 10, 4, 5, 10, 0};


         storageItems = new Item[]{
                 new StandardItem(1, "Dancing Panda v.2", 5000, "GADGETS", 5),
                 new StandardItem(2, "Dancing Panda v.3 with USB port", 6000, "GADGETS", 10),
                 new StandardItem(3, "Screwdriver", 200, "TOOLS", 5),
                 new DiscountedItem(4, "Star Wars Jedi buzzer", 500, "GADGETS", 30, "1.8.2013", "1.12.2013"),
                 new DiscountedItem(5, "Angry bird cup", 300, "GADGETS", 20, "1.9.2013", "1.12.2013"),
                 new DiscountedItem(6, "Soft toy Angry bird (size 40cm)", 800, "GADGETS", 10, "1.8.2013", "1.12.2013")
         };

        // insert data to the storage
        for (int i = 0; i < storageItems.length; i++) {
            controller.getStorage().insertItems(storageItems[i], itemCount[i]);
        }

        System.setOut(new PrintStream(this.newOut));
        System.setErr(new PrintStream(this.newErr));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(this.ogOut);
        System.setErr(this.ogErr);
    }

    @Test
    void validPurchase() throws NoItemInStorage {
        ShoppingCart newCart = new ShoppingCart();
        newCart.addItem(storageItems[0]);
        newCart.addItem(storageItems[1]);
        newCart.addItem(storageItems[2]);
        newCart.addItem(storageItems[4]);
        controller.purchaseShoppingCart(newCart, "Libuse Novakova","Kosmonautu 25, Praha 8");

        Assertions.assertEquals("Item with ID 1 added to the shopping cart.\n" +
                "Item with ID 2 added to the shopping cart.\n" +
                "Item with ID 3 added to the shopping cart.\n" +
                "Item with ID 5 added to the shopping cart.\n", this.newOut.toString());
    }

    @Test
    void addInvalidItemToCart() {
        ShoppingCart newCart = new ShoppingCart();
        newCart.addItem(new StandardItem(7, "Invalid item", 100, "GADGETS", 5));
        Assertions.assertThrows(NoItemInStorage.class, () -> controller.purchaseShoppingCart(newCart,
                "Libuse Novakova","Kosmonautu 25, Praha 8"));

    }

    @Test
    void purchaseInvalidName() {
        ShoppingCart newCart = new ShoppingCart();
        newCart.addItem(storageItems[0]);
        newCart.addItem(storageItems[1]);
        newCart.addItem(storageItems[2]);
        newCart.addItem(storageItems[4]);
        Assertions.assertThrows(IllegalArgumentException.class, () -> controller.purchaseShoppingCart(newCart, null,
                "Kosmonautu 25, Praha 8"));
    }

    @Test
    void addOutOfStockItemToCart() {
        ShoppingCart newCart = new ShoppingCart();
        newCart.addItem(storageItems[5]);
        Assertions.assertThrows(NoItemInStorage.class, () -> controller.purchaseShoppingCart(newCart,
                "Libuse Novakova","Kosmonautu 25, Praha 8"));
    }

    @Test
    void purchaseInvalidAddress() {
        ShoppingCart newCart = new ShoppingCart();
        newCart.addItem(storageItems[0]);
        newCart.addItem(storageItems[1]);
        newCart.addItem(storageItems[2]);
        newCart.addItem(storageItems[4]);
        Assertions.assertThrows(IllegalArgumentException.class, () -> controller.purchaseShoppingCart(newCart,
                "Libuse Novakova", null));
    }

    @Test
    void purchaseEmptyCart() throws NoItemInStorage {
        ShoppingCart newCart = new ShoppingCart();
        controller.purchaseShoppingCart(newCart, "Libuse Novakova","Kosmonautu 25, Praha 8");
        Assertions.assertEquals("Error: shopping cart is empty\n", this.newOut.toString());
    }
}