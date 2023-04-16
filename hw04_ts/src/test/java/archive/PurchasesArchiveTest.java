package archive;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import shop.Item;
import shop.Order;
import shop.ShoppingCart;
import shop.StandardItem;

public class PurchasesArchiveTest {
    private final ByteArrayOutputStream newOut = new ByteArrayOutputStream();
    private final ByteArrayOutputStream newErr = new ByteArrayOutputStream();
    private final PrintStream ogOut;
    private final PrintStream ogErr;

    Item item;
    ShoppingCart cart;
    Order order;
    private PurchasesArchive purchasesArchive;
    private ItemPurchaseArchiveEntry itemPurchaseArchiveEntry;

    public PurchasesArchiveTest() {
        this.ogOut = System.out;
        this.ogErr = System.err;
    }

    @BeforeEach
    public void setup() {
        this.item = new StandardItem(0, "siberia", 130, "tobacco", 1);
        this.cart = new ShoppingCart();
        this.cart.addItem(this.item);
        this.order = new Order(this.cart, "Sabina", "Praha", 1);
        this.purchasesArchive = new PurchasesArchive();
        this.purchasesArchive.putOrderToPurchasesArchive(this.order);
        this.itemPurchaseArchiveEntry = this.purchasesArchive.getItemPurchaseArchive().get(0);
        System.setOut(new PrintStream(this.newOut));
        System.setErr(new PrintStream(this.newErr));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(this.ogOut);
        System.setErr(this.ogErr);
    }

    @Test
    public void printItemPurchaseStatisticsTest() {
        this.purchasesArchive.printItemPurchaseStatistics();
        Assertions.assertEquals("ITEM PURCHASE STATISTICS:\nITEM  " + this.itemPurchaseArchiveEntry.getRefItem()
                + "   HAS BEEN SOLD " + this.itemPurchaseArchiveEntry.getCountHowManyTimesHasBeenSold() + " TIMES\n",
                this.newOut.toString());
    }

    @Test
    public void getHowManyTimesHasBeenItemSoldTest() {
        Assertions.assertEquals(1, this.purchasesArchive.getHowManyTimesHasBeenItemSold(this.item));
        this.purchasesArchive.putOrderToPurchasesArchive(this.order);
        Assertions.assertEquals(2, this.purchasesArchive.getHowManyTimesHasBeenItemSold(this.item));
    }

    @Test
    public void mockPurchaseArchiveTest() {
        Mockito.mock(Order.class);
        Mockito.mock(PurchasesArchive.class);
        Mockito.mock(ItemPurchaseArchiveEntry.class);
    }
}
