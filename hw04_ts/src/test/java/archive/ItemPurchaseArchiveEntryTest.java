package archive;

import org.junit.jupiter.api.Assertions;
import shop.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.StandardItem;

import static org.junit.jupiter.api.Assertions.*;

class ItemPurchaseArchiveEntryTest {

    Item refItem;
    ItemPurchaseArchiveEntry itemPurchaseArchiveEntry;

    @BeforeEach
    void setUp() {
        this.refItem = new StandardItem(0, "siberia", 130, "tobacco", 1);
        this.itemPurchaseArchiveEntry = new ItemPurchaseArchiveEntry(refItem);
    }

    @Test
    void increaseCountHowManyTimesHasBeenSold() {
        Assertions.assertEquals(1, this.itemPurchaseArchiveEntry.getCountHowManyTimesHasBeenSold());
    }

    @Test
    void getCountHowManyTimesHasBeenSold() {
        this.itemPurchaseArchiveEntry.increaseCountHowManyTimesHasBeenSold(2);
        Assertions.assertEquals(3, this.itemPurchaseArchiveEntry.getCountHowManyTimesHasBeenSold());
    }

    @Test
    void getRefItem() {
        Assertions.assertEquals(this.refItem, this.itemPurchaseArchiveEntry.getRefItem());
    }

}