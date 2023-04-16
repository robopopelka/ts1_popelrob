package storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import shop.Item;
import shop.StandardItem;

public class ItemStockTest {
    ItemStock itemStock;

    public ItemStockTest() {
    }

    @BeforeEach
    public void setup() {
        this.itemStock = new ItemStock(new StandardItem(0, "siberia", 130, "tobacco", 1));
    }

    @Test
    public void constructorTest() {
        Item item = new StandardItem(0, "siberia", 130, "tobacco", 1);
        Assertions.assertEquals(0, this.itemStock.getCount());
        Assertions.assertEquals(item, this.itemStock.getItem());
    }

    @ParameterizedTest(
            name = "0 plus {0} should return {1}"
    )
    @CsvSource({"1, 1", "2, 2"})
    public void IncreaseItemCount_addPositiveValueToZero_positiveValue(int x, int expectedCount) {
        this.itemStock.IncreaseItemCount(x);
        Assertions.assertEquals(expectedCount, this.itemStock.getCount());
    }

    @ParameterizedTest
    @CsvSource({"1, 1, 0, 1, -1, 0"})
    public void IncreaseAndDecreaseItemCount(int x1, int n1, int x2, int n2, int x3, int n3) {
        this.itemStock.IncreaseItemCount(x1);
        Assertions.assertEquals(n1, this.itemStock.getCount());
        this.itemStock.IncreaseItemCount(x2);
        Assertions.assertEquals(n2, this.itemStock.getCount());
        this.itemStock.IncreaseItemCount(x3);
        Assertions.assertEquals(n3, this.itemStock.getCount());
    }
}
