package shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StandardItemTest {
    StandardItem item;

    public StandardItemTest() {
    }

    @BeforeEach
    public void setup() {
        this.item = new StandardItem(0, "siberia", 130, "tobacco", 1);
    }

    @Test
    public void constructorTest() {
        Assertions.assertEquals(0, this.item.getID());
        Assertions.assertEquals("siberia", this.item.getName());
        Assertions.assertEquals(130, this.item.getPrice());
        Assertions.assertEquals("tobacco", this.item.getCategory());
        Assertions.assertEquals(1, this.item.getLoyaltyPoints());
    }

    @Test
    public void copyTest() {
        StandardItem copy = this.item.copy();
        Assertions.assertEquals(copy.getID(), this.item.getID());
        Assertions.assertEquals(copy.getName(), this.item.getName());
        Assertions.assertEquals(copy.getPrice(), this.item.getPrice());
        Assertions.assertEquals(copy.getCategory(), this.item.getCategory());
        Assertions.assertEquals(copy.getLoyaltyPoints(), this.item.getLoyaltyPoints());
    }

    @ParameterizedTest
    @CsvSource({"0, siberia, 130, tobacco, 1, 1, bread, 25, groceries, 2"})
    public void equalObjects(int id, String name, float price, String category, int loyaltyPoints,
                             int id3, String name3, float price3, String category3, int loyaltyPoints3) {
        StandardItem item1 = new StandardItem(id, name, price, category, loyaltyPoints);
        StandardItem item2 = new StandardItem(id, name, price, category, loyaltyPoints);
        StandardItem item3 = new StandardItem(id3, name3, price3, category3, loyaltyPoints3);
        Assertions.assertEquals(item1, item2);
        Assertions.assertNotEquals(item1, item3);
    }
}
