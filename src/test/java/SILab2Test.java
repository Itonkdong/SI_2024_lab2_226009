import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test
{
    //Направените анализи за секој од критериумите, користењето на приложените табели и дополнителното објаснување од документацијата,
    // беа искористени за да се напишат следните unit тестови:

    @Test
    void everyBranchTest()
    {
//        allItems = null, payment = X
        RuntimeException exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 0));
        assertTrue(exception.getMessage().contains("allItems list can't be null!"));


//        allItems = [{null, 03, 1000, 10},{DVD, 23, 100, 0},{CD, 2a, 1000, 10}],payment = X
        exception = assertThrows(RuntimeException.class, () ->
        {
            SILab2.checkCart(List.of(
                    new Item(null, "03", 1000, 10),
                    new Item("DVD", "23", 100, 0),
                    new Item("CD", "2a", 1000, 10)
            ), 0);
        });
        assertTrue(exception.getMessage().contains("Invalid character in item barcode!"));


//        allItems = [{CD, null, 1000, 10}], payment = X
        exception = assertThrows(RuntimeException.class, () ->
        {
            SILab2.checkCart(List.of(
                    new Item("CD", null, 1000, 10)
            ), 0);
        });
        assertTrue(exception.getMessage().contains("No barcode!"));


//        allItems = [{CD, 12, 1000, 10}], payment = 0
        assertFalse(SILab2.checkCart(List.of(
                new Item("CD", "12", 1000, 10)), 0));


//        allItems = [{DVD, 12, 100, 10}], payment = 10000
        assertTrue(SILab2.checkCart(List.of(
                new Item("DVD", "12", 100, 10)), 10000));

    }

    @Test
    void multipleConditionTest()
    {
/*        Multiple-Condition Coverage For:
        item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'*/


//        FXX:	allItems = [{DVD, 9, 100, 0}], payment = Y
        assertFalse(SILab2.checkCart(List.of(
                new Item("DVD", "9", 100, 0)), 0));


//        TFX:	allItems = [{DVD, 9, 1000, 0}], payment = Y
        assertFalse(SILab2.checkCart(List.of(
                new Item("DVD", "9", 1000, 0)), 0));


//        TTF:	allItems = [{DVD, 9, 1000, 10}], payment = Y
        assertFalse(SILab2.checkCart(List.of(
                new Item("DVD", "9", 1000, 10)), 0));


//        TTT:	allItems = [{DVD, 09, 1000, 10}], payment = Y
        assertFalse(SILab2.checkCart(List.of(
                new Item("DVD", "09", 1000, 10)), 0));
    }
}