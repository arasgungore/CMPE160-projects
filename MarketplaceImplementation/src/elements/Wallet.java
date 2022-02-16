
package elements;

/**
 * Wallet class.
 * @author Aras Güngöre
 *
 */
public class Wallet {
	
	/**
	 * Amount of dollars in the wallet.
	 */
	private double dollars;
	/**
	 * Amount of PQoins in the wallet.
	 */
	private double coins;
	/**
	 * Amount of blocked dollars in the wallet.
	 */
	private double blockedDollars;
	/**
	 * Amount of blocked coins in the wallet.
	 */
	private double blockedCoins;
	
	
	
	/**
	 * A constructor with 2 parameters, namely dollars and coins.
	 * @param dollars Amount of dollars in the wallet.
	 * @param coins Amount of PQoins in the wallet.
	 */
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
		blockedDollars = 0.0;
		blockedCoins = 0.0;
	}
	
	
	
	// *** Extra Methods ***
	
	/**
	 * Deposits the given amount of dollars to the available dollars.
	 * @param dollars The given amount of dollars.
	 */
	public void depositDollars(double dollars) {
		this.dollars += dollars;
	}
	
	/**
	 * Deposits the given amount of coins to the available coins.
	 * @param coins The given amount of coins.
	 */
	public void depositCoins(double coins) {
		this.coins += coins;
	}
	
	/**
	 * Withdraws the given amount of dollars from the available dollars.
	 * @param dollars The given amount of dollars.
	 */
	public void withdrawDollars(double dollars) {
		this.dollars -= dollars;
	}
	
	/**
	 * Blocks the given amount of dollars in the wallet.
	 * @param dollars The given amount of dollars.
	 */
	public void blockDollars(double dollars) {
		this.dollars -= dollars;
		blockedDollars += dollars;
	}
	
	/**
	 * Blocks the given amount of coins in the wallet.
	 * @param coins The given amount of coins.
	 */
	public void blockCoins(double coins) {
		this.coins -= coins;
		blockedCoins += coins;
	}
	
	/**
	 * Returns the given amount of dollars from the blocked dollars to the available dollars.
	 * @param dollars The given amount of dollars.
	 */
	public void returnDollars(double dollars) {
		this.dollars += dollars;
		blockedDollars -= dollars;
	}
	
	/**
	 * Pays the given amount of dollars from the blocked dollars.
	 * @param dollars The given amount of dollars.
	 */
	public void payFromBlockedDollars(double dollars) {
		blockedDollars -= dollars;
	}
	
	/**
	 * Pays the given amount of coins from the blocked coins.
	 * @param coins The given amount of coins.
	 */
	public void payFromBlockedCoins(double coins) {
		blockedCoins -= coins;
	}
	
	/**
	 * Checks if the given amount of dollars can be withdrawn from the available dollars.
	 * @param dollars The given amount of dollars.
	 * @return TRUE if the given amount of dollars is affordable, FALSE otherwise.
	 */
	public boolean checkWithdraw(double dollars) {
		return dollars <= this.dollars;
	}
	
	/**
	 * Checks if the given amount of coins can be withdrawn from the available coins.
	 * @param coins The given amount of coins.
	 * @return TRUE if the given amount of coins is affordable, FALSE otherwise.
	 */
	public boolean checkSelling(double coins) {
		return coins <= this.coins;
	}
	
	/**
	 * Checks if the given amount of dollars can be withdrawn from the blocked dollars.
	 * @param dollars The given amount of dollars.
	 * @return TRUE if the given amount of dollars is affordable, FALSE otherwise.
	 */
	public boolean checkBlockedDollars(double dollars) {
		return dollars <= blockedDollars;
	}
	
	/**
	 * Checks if the given amount of coins can be withdrawn from the blocked coins.
	 * @param coins The given amount of coins.
	 * @return TRUE if the given amount of coins is affordable, FALSE otherwise.
	 */
	public boolean checkBlockedCoins(double coins) {
		return coins <= blockedCoins;
	}
	
	/**
	 * Returns a String which includes the wallet's information.
	 * @return “-trader's_dollars-$ -trader's_PQoins-PQ”
	 */
	@Override
	public String toString() {
		return String.format("%.5f$ %.5fPQ", dollars + blockedDollars, coins + blockedCoins);
	}
	
}
