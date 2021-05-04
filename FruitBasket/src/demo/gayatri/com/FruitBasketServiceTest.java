package demo.gayatri.com;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FruitBasketServiceTest 
{

    FruitBasketService service;
    HashSet<Class> allowedTypes;

    @Before
    public void setUp(){
        service = new FruitbasketServiceImpl();
        allowedTypes = new HashSet<Class>();
        allowedTypes.add(Orange.class);
        allowedTypes.add(Apple.class);
        allowedTypes.add(Banana.class);
        allowedTypes.add(Lemon.class);
        allowedTypes.add(Peach.class);
    }

    @Test
    public void testTotalCostForSingleBasket() {
        List<Item> basket1 = new ArrayList<Item>();
        basket1.add(new Orange(new BigDecimal("2.5")));
        basket1.add(new Banana(new BigDecimal("1.5")));
        basket1.add(new Banana(new BigDecimal("1.55")));
        basket1.add(new Apple(new BigDecimal("4.9")));
        basket1.add(new Apple(new BigDecimal("5.11")));

        ArrayList<List<Item>> baskets = new ArrayList<List<Item>>();
        baskets.add(basket1);

        BigDecimal totalCost = service.totalCost(baskets,allowedTypes);

        assertEquals(new BigDecimal(15.56).setScale(2, RoundingMode.HALF_EVEN), totalCost);

    }

    @Test
    public void testTotalCostForMultipleBaskets() {
        List<Item> basket1 = new ArrayList<Item>();
        basket1.add(new Orange(new BigDecimal("2.5")));
        basket1.add(new Banana(new BigDecimal("1.5")));
        basket1.add(new Banana(new BigDecimal("1.55")));
        basket1.add(new Apple(new BigDecimal("4.9")));
        basket1.add(new Apple(new BigDecimal("5.11")));

        ArrayList<List<Item>> baskets = new ArrayList<List<Item>>();
        baskets.add(basket1);
        baskets.add(basket1);
        baskets.add(basket1);

        BigDecimal totalCost = service.totalCost(baskets,allowedTypes);

        assertEquals(new BigDecimal(30.56*3).setScale(2, RoundingMode.HALF_EVEN), totalCost);

    }
    @Test
    (expected=IllegalArgumentException.class)
    public void testTotalCostForBasketWithWrongItems()
    {
        List<Item> basket1 = new ArrayList<Item>();
        basket1.add(new Apple(new BigDecimal("5.11")));

        ArrayList<List<Item>> baskets = new ArrayList<List<Item>>();
        baskets.add(basket1);
        baskets.add(basket1);
        baskets.add(basket1);

        BigDecimal totalCost = service.totalCost(baskets,allowedTypes);

    }
    @Test
    public void testTotalCostForEmptyBasket() {
        assertEquals(BigDecimal.ZERO.setScale(2), service.totalCost(null,null));
        assertEquals(BigDecimal.ZERO.setScale(2), service.totalCost(new ArrayList<List<Item>>(),null));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBasketItemPriceWithManyDecimals() {
        new Orange(new BigDecimal("2.52"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBasketItemNegativePrice() {
        new Orange(new BigDecimal("-2.524"));
    }


}
