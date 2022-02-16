
package elements;

/**
 * Order class.
 * @author Aras Güngöre
 *
 */
public class Order {
	
	/**
	 * PQoins reserved for the order.
	 */
	protected double amount;
	/**
	 * Price of the order set by the trader.
	 */
	protected double price;
	/**
	 * ID of the trader who is giving the order.
	 */
	protected int traderID;
	
	
	
	/**
	 * Constructor with 3 parameters.
	 * @param traderID ID of the trader who is giving the order.
	 * @param amount PQoins reserved for the order.
	 * @param price Price of the order set by the trader.
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}
	
	
	
	// *** Extra Methods ***
	
	/**
	 * Getter method for amount.
	 * @return PQoins reserved for the order.
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Getter method for price.
	 * @return Price of the order set by the trader.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Getter method for traderID.
	 * @return ID of the trader who is giving the order.
	 */
	public int getTraderID() {
		return traderID;
	}
	
	/**
	 * Returns the dollars reserved for the order.
	 * @return amount * price
	 */
	public double getDollars() {
		return amount * price;
	}
	
}
