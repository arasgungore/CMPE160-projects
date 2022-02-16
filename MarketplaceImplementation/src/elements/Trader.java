
package elements;

/**
 * Trader class.
 * @author Aras Güngöre
 *
 */
public class Trader {
	
	/**
	 * ID of the trader.
	 */
	private int id;
	/**
	 * Wallet of the trader.
	 */
	private Wallet wallet;
	/**
	 * Total number of traders in the market.
	 */
	public static int numberOfUsers = 0;
	
	
	
	/**
	 * A constructor with 2 parameters, namely dollars and coins.
	 * @param dollars Initial amount of dollars in the trader's wallet.
	 * @param coins Initial amount of PQoins in the trader's wallet.
	 */
	public Trader(double dollars, double coins) {
		id = numberOfUsers;
		numberOfUsers++;
		wallet = new Wallet(dollars, coins);
	}
	
	/**
	 * Processes the selling part of the transaction.
	 * @param amount PQoins reserved for the selling order.
	 * @param price Price of the selling order set by the selling trader.
	 * @param market The market object.
	 * @return 1 if the selling part of the transaction is successful, 0 otherwise.
	 */
	public int sell(double amount, double price, Market market) {
		final boolean flag = wallet.checkBlockedCoins(amount) || id == 0;
		
		if(flag) {
			wallet.payFromBlockedCoins(amount);
			wallet.depositDollars(amount * price * (1.0 - (double)market.getFee() / 1000.0));
		}
		
		return flag ? 1 : 0;
	}
	
	/**
	 * Processes the buying part of the transaction.
	 * @param amount PQoins reserved for the buying order.
	 * @param price Price of the buying order set by the buying trader.
	 * @param market The market object.
	 * @return 1 if the buying part of the transaction is successful, 0 otherwise.
	 */
	public int buy(double amount, double price, Market market) {
		final double dollars = amount * price;
		final boolean flag = wallet.checkBlockedDollars(dollars) || id == 0;
		
		if(flag) {
			wallet.payFromBlockedDollars(dollars);
			wallet.depositCoins(amount);
		}
		
		return flag ? 1 : 0;
	}
	
	
	
	// *** Extra Methods ***
	
	/**
	 * Getter method for wallet.
	 * @return Wallet of the trader.
	 */
	public Wallet getWallet() {
		return wallet;
	}
	
	/**
	 * Returns a String which includes the trader's ID and wallet information.
	 * @return “Trader -trader_ID-: -trader's_dollars-$ -trader's_PQoins-PQ”
	 */
	@Override
	public String toString() {
		return String.format("Trader %d: %s", id, wallet.toString());
	}
	
}
