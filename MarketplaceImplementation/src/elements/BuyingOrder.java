
package elements;

/**
 * BuyingOrder class which is a subclass of the Order class.
 * @author Aras Güngöre
 *
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder> {
	
	/**
	 * Constructor with 3 parameters.
	 * @param traderID ID of the trader who is giving the buying order.
	 * @param amount PQoins reserved for the buying order.
	 * @param price Price of the buying order set by the buying trader.
	 */
	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}
	
	/**
	 * Overrides to compareTo method to return the BuyingOrder that has the highest price.
	 * If two orders have the same price then you get the BuyingOrder which has the highest amount.
	 * If it is still the same, you get the BuyingOrder that has the lowest tradersID.
	 * @param e Other BuyingOrder.
	 * @return 1 if this BuyingOrder is of top priority, -1 if the other BuyingOrder is of top priority, 0 if they have the same priority.
	 */
	@Override
	public int compareTo(BuyingOrder e) {
		if(price > e.price)
			return -1;
		if(price < e.price)
			return 1;
		if(amount > e.amount)
			return -1;
		if(amount < e.amount)
			return 1;
		if(traderID < e.traderID)
			return -1;
		if(traderID > e.traderID)
			return 1;
		return 0;
	}
	
}
