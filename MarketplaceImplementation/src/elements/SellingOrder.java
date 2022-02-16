
package elements;

/**
 * SellingOrder class which is a subclass of the Order class.
 * @author Aras Güngöre
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder> {
	
	/**
	 * Constructor with 3 parameters.
	 * @param traderID ID of the trader who is giving the selling order.
	 * @param amount PQoins reserved for the selling order.
	 * @param price Price of the selling order set by the selling trader.
	 */
	public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}
	
	/**
	 * Overrides to compareTo method to return the SellingOrder that has the lowest price.
	 * If two orders have the same price then you get the SellingOrder which has the highest amount.
	 * If it is still the same, you get the SellingOrder that has the lowest tradersID.
	 * @param e Other SellingOrder.
	 * @return 1 if this SellingOrder is of top priority, -1 if the other SellingOrder is of top priority, 0 if they have the same priority.
	 */
	@Override
	public int compareTo(SellingOrder e) {
		if(price < e.price)
			return -1;
		if(price > e.price)
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
