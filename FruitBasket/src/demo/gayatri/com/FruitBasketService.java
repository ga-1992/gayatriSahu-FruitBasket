
package demo.gayatri.com;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;

public interface FruitBasketService 
{


    BigDecimal  totalCost(List<List<Item>> baskets, HashSet<Class> allowedItems);
}
   interface Item {

    BigDecimal  getPrice();
}
class BasketItem implements Item 
{
	  private  BigDecimal  price;

	    @SuppressWarnings("deprecation")
		public BasketItem(BigDecimal  price, RoundingMode ROUND_HALF_EVEN)
	    {
	        if (price == null || price.intValue() < 0) throw new IllegalArgumentException("Price can't be negative");
	        if (price.setScale(2,ROUND_HALF_EVEN).compareTo(price) != 0 ) throw new IllegalArgumentException("Price should have only 2 decimals");
	        this.price = price;
	    }

	    public BigDecimal getPrice() {
	        return price;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        BasketItem that = (BasketItem) o;

	        return price != null ? price.equals(that.price) : that.price == null;

	    }

	    @Override
	    public int hashCode() {
	        return price != null ? price.hashCode() : 0;
	    }
	}

	class Banana extends BasketItem{

	    public Banana(BigDecimal price) {
	        super(price,null);
	    }
	}
	class Orange extends BasketItem{
	    public Orange(BigDecimal price) {
	        super(price, null);
	    }
	}
	class Apple extends BasketItem{
	    public Apple(BigDecimal price) {
	        super(price, null);
	    }
	}
	class Lemon extends BasketItem{
	    public Lemon(BigDecimal price) {
	        super(price, null);
	    }
	}
	class Peach extends BasketItem{
	    public Peach(BigDecimal price) {
	        super(price, null);
	    }
	}


