
package elements;

/**
 * Transaction class.
 * @author Aras Güngöre
 *
 */
public class Transaction {
	
	/**
	 * Selling order of the transaction.
	 */
	private SellingOrder sellingOrder;
	/**
	 * Buying order of the transaction.
	 */
	private BuyingOrder buyingOrder;
	
	
	
	/**
	 * Constructor with 2 parameters, namely SellingOrder and buyingOrder.
	 * @param sellingOrder Selling order of the transaction.
	 * @param buyingOrder Buying order of the transaction.
	 */
	public Transaction(SellingOrder sellingOrder, BuyingOrder buyingOrder) {
		this.sellingOrder = sellingOrder;
		this.buyingOrder = buyingOrder;
	}
	
	/**
	 * Getter method for sellingOrder.
	 * @return Selling order of the transaction.
	 */
	public SellingOrder getSellingOrder() {
		return sellingOrder;
	}
	
	/**
	 * Getter method for buyingOrder.
	 * @return Buying order of the transaction.
	 */
	public BuyingOrder getBuyingOrder() {
		return buyingOrder;
	}
	
}
