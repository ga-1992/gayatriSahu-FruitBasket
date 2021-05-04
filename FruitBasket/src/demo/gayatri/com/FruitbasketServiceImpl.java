package demo.gayatri.com;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;

public class FruitbasketServiceImpl implements FruitBasketService 
{

	@SuppressWarnings("deprecation")
	@Override
	public BigDecimal totalCost(List<List<Item>> baskets, HashSet<Class> allowedItems)
	{
		
		BigDecimal totalCost = new BigDecimal(0);
        if (baskets != null) {
            for (List<Item> basket : baskets) {
                for (Item item : basket) {
                    if (allowedItems != null && allowedItems.contains(item.getClass())){
                        totalCost = totalCost.add(item.getPrice());
                    } else
                        throw new IllegalArgumentException("Basket can't have the items of type :" + item.getClass().getSimpleName());
                }
            }
        }

		return totalCost.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

	}

