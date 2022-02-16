
package executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

import elements.BuyingOrder;
import elements.SellingOrder;
import elements.Market;
import elements.Trader;
import elements.Wallet;

/**
 * Main class.
 * @author Aras Güngöre
 *
 */
public class Main {
	
	/**
	 * Static variable which holds the seed for the random number generator.
	 */
	public static Random myRandom;
	
	/**
	 * Main method.
	 * @param args Command line arguments where args[0] is the input filename/directory and args[1] is the output filename/directory.
	 * @throws FileNotFoundException Exception thrown if the directories given in args don't exist.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		// Create a new Scanner object to read data from the input file with directory "args[0]".
		Scanner in = new Scanner(new File(args[0]));
		
		final long seed = in.nextLong();
		
		myRandom = new Random(seed);		// Create a random number generator with given seed.
		
		final int initial_fee = in.nextInt();
		final int no_of_users = in.nextInt();
		final int no_of_queries = in.nextInt();
		
		Market market = new Market(initial_fee);
		ArrayList<Trader> traders = new ArrayList<Trader>(no_of_users);		// An ArrayList which holds the traders in the market.
		
		for(int id = 0; id < no_of_users; id++) {
			final double dollar_amount = in.nextDouble();
			final double PQoin_amount = in.nextDouble();
			traders.add(new Trader(dollar_amount, PQoin_amount));
		}
		
		int no_of_invalid_queries = 0;
		market.setTraders(traders);
		
		// Create a new PrintStream object to read data from the input file with directory "args[1]".
		PrintStream out = new PrintStream(new File(args[1]));
		
		for(int q = 0; q < no_of_queries; q++) {	// Iterate over queries.
			final int query = in.nextInt();
			switch(query) {
			
				// Trader specific queries.
			
				case 10: {		// Give buying order of specific price.
					final int trader_ID = in.nextInt();
					final double price = in.nextDouble();
					final double amount = in.nextDouble();
					final double dollars = amount * price;
					Wallet wallet = traders.get(trader_ID).getWallet();
					
					if(trader_ID == 0)
						market.giveBuyOrder(new BuyingOrder(trader_ID, amount, price));
					else if(wallet.checkWithdraw(dollars)) {
						market.giveBuyOrder(new BuyingOrder(trader_ID, amount, price));
						wallet.blockDollars(dollars);
					}
					else
						no_of_invalid_queries++;
					break; }
				
				case 11: {		// Give buying order of market price.
					final int trader_ID = in.nextInt();
					final double amount = in.nextDouble();
					final double price = market.sellingOrderPrice();
					final double dollars = amount * price;
					Wallet wallet = traders.get(trader_ID).getWallet();
					
					if(market.getSellingOrders().isEmpty())
						no_of_invalid_queries++;
					else if(trader_ID == 0)
						market.giveBuyOrder(new BuyingOrder(trader_ID, amount, price));
					else if(wallet.checkWithdraw(dollars)) {
						market.giveBuyOrder(new BuyingOrder(trader_ID, amount, price));
						wallet.blockDollars(dollars);
					}
					else
						no_of_invalid_queries++;
					break; }
				
				case 20: {		// Give selling order of specific price.
					final int trader_ID = in.nextInt();
					final double price = in.nextDouble();
					final double amount = in.nextDouble();
					Wallet wallet = traders.get(trader_ID).getWallet();
					
					if(trader_ID == 0)
						market.giveSellOrder(new SellingOrder(trader_ID, amount, price));
					else if(wallet.checkSelling(amount)) {
						market.giveSellOrder(new SellingOrder(trader_ID, amount, price));
						wallet.blockCoins(amount);
					}
					else
						no_of_invalid_queries++;
					break; }
				
				case 21: {		// Give selling order of market price.
					final int trader_ID = in.nextInt();
					final double amount = in.nextDouble();
					final double price = market.buyingOrderPrice();
					Wallet wallet = traders.get(trader_ID).getWallet();
					
					if(market.getBuyingOrders().isEmpty())
						no_of_invalid_queries++;
					else if(trader_ID == 0)
						market.giveSellOrder(new SellingOrder(trader_ID, amount, price));
					else if(wallet.checkSelling(amount)) {
						market.giveSellOrder(new SellingOrder(trader_ID, amount, price));
						wallet.blockCoins(amount);
					}
					else
						no_of_invalid_queries++;
					break; }
				
				case 3: {		// Deposit a certain amount of dollars to wallet.
					final int trader_ID = in.nextInt();
					final double dollars = in.nextDouble();
					traders.get(trader_ID).getWallet().depositDollars(dollars);
					break; }
				
				case 4: {		// Withdraw a certain amount of dollars from wallet.
					final int trader_ID = in.nextInt();
					final double dollars = in.nextDouble();
					Wallet wallet = traders.get(trader_ID).getWallet();

					if(wallet.checkWithdraw(dollars))
						wallet.withdrawDollars(dollars);
					else
						no_of_invalid_queries++;
					break; }
				
				case 5: {		// Print wallet status.
					final int trader_ID = in.nextInt();
					out.println(traders.get(trader_ID));
					break; }
				
				
				// System queries.
				
				case 777: {		// Give rewards to all traders.
					for(Trader t : traders)
						t.getWallet().depositCoins(myRandom.nextDouble() * 10.0);
					break; }
				
				case 666: {		// Make open market operation.
					final double price = in.nextDouble();
					market.makeOpenMarketOperation(price);
					break; }
				
				case 500: {		// Print the current market size.
					out.println(market.marketSizeInfo());
					break; }
				
				case 501: {		// Print number of successful transactions.
					out.println(market.transactionNumberInfo());
					break; }
				
				case 502: {		// Print the number of invalid queries.
					out.println("Number of invalid queries: " + no_of_invalid_queries);
					break; }
				
				case 505: {		// Print the current prices.
					out.println(market.currentPriceInfo());
					break; }
				
				case 555: {		// Print all traders’ wallet status.
					for(Trader t : traders)
						out.println(t);
					break; }
				
				default:
					System.out.println("Invalid query number : " + query);
			}
			market.checkTransactions(traders);
		}
		
		in.close();		// Closes the Scanner object.
		out.close();	// Closes the PrintStream object.
		
	}
	
}
