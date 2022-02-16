
package elements;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Market class.
 * @author Aras Güngöre
 *
 */
public class Market {
	
	/**
	 * A priority queue which holds the selling orders.
	 */
	private PriorityQueue<SellingOrder> sellingOrders;
	/**
	 * A priority queue which holds the buying orders.
	 */
	private PriorityQueue<BuyingOrder> buyingOrders;
	/**
	 * An ArrayList which holds the transactions.
	 */
	private ArrayList<Transaction> transactions;
	
	// *** Extra Fields ***
	
	/**
	 * Market fee.
	 */
	private int fee;
	/**
	 * Total number of successful transactions.
	 */
	private int no_of_successful_transactions;
	/**
	 * An ArrayList which holds the traders in the market.
	 */
	private ArrayList<Trader> traders;
	
	
	
	/**
	 * Constructor with 1 parameter, namely fee.
	 * @param fee Market fee.
	 */
	public Market(int fee) {
		sellingOrders = new PriorityQueue<SellingOrder>();
		buyingOrders = new PriorityQueue<BuyingOrder>();
		transactions = new ArrayList<Transaction>();
		
		this.fee = fee;
		no_of_successful_transactions = 0;
		traders = new ArrayList<Trader>();
	}
	
	/**
	 * Adds the given selling order to the selling priority queue.
	 * @param order Given selling order.
	 */
	public void giveSellOrder(SellingOrder order) {
		sellingOrders.add(order);
	}
	
	/**
	 * Adds the given buying order to the buying priority queue.
	 * @param order Given buying order.
	 */
	public void giveBuyOrder(BuyingOrder order) {
		buyingOrders.add(order);
	}
	
	/**
	 * The system makes an open market operation and compensates buying or selling orders
	 * in order to set the price of PQoin to the given price.
	 * @param price The given price.
	 */
	public void makeOpenMarketOperation(double price) {
		while(buyingOrderPrice() >= price && !buyingOrders.isEmpty()) {
			BuyingOrder b_order = buyingOrders.peek();
			giveSellOrder(new SellingOrder(0, b_order.getAmount(), b_order.getPrice()));
			checkTransactions(traders);
		}
		while(sellingOrderPrice() <= price && !sellingOrders.isEmpty()) {
			SellingOrder s_order = sellingOrders.peek();
			giveBuyOrder(new BuyingOrder(0, s_order.getAmount(), s_order.getPrice()));
			checkTransactions(traders);
		}
	}
	
	/**
	 * Checks whether the prices at the PQs are overlapping.
	 * If there is an overlap, the market should make transactions with the top of PQs, until there is no overlapping.
	 * After handling all of the overlaps, then the method handles all the transactions.
	 * @param traders An ArrayList which holds the traders in the market.
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
		while(buyingOrderPrice() >= sellingOrderPrice() && !buyingOrders.isEmpty() && !sellingOrders.isEmpty()) {
			SellingOrder s_order = sellingOrders.poll();
			BuyingOrder b_order = buyingOrders.poll();
			
			double traded_amount;
			if(s_order.amount > b_order.amount) {
				traded_amount = b_order.amount;
				sellingOrders.add(new SellingOrder(s_order.traderID, s_order.price, s_order.amount - traded_amount));
			}
			else if(s_order.amount < b_order.amount) {
				traded_amount = s_order.amount;
				buyingOrders.add(new BuyingOrder(b_order.traderID, b_order.price, b_order.amount - traded_amount));
			}
			else
				traded_amount = b_order.amount;
			
			int sell_flag = traders.get(s_order.traderID).sell(traded_amount, s_order.price, this);
			int buy_flag = traders.get(b_order.traderID).buy(traded_amount, s_order.price, this);
			
			if(sell_flag == 1 && buy_flag == 1)
				no_of_successful_transactions++;
			
			if(b_order.price > s_order.price)
				traders.get(b_order.traderID).getWallet().returnDollars((b_order.price - s_order.price) * traded_amount);
			
			transactions.add(new Transaction(s_order, b_order));
		}
		
	}
	
	
	
	// *** Extra Methods ***

	/**
	 * Getter method for sellingOrders.
	 * @return A priority queue which holds the selling orders.
	 */
	public PriorityQueue<SellingOrder> getSellingOrders() {
		return sellingOrders;
	}

	/**
	 * Getter method for buyingOrders.
	 * @return A priority queue which holds the buying orders.
	 */
	public PriorityQueue<BuyingOrder> getBuyingOrders() {
		return buyingOrders;
	}
	
	/**
	 * Getter method for fee.
	 * @return Market fee.
	 */
	public int getFee() {
		return fee;
	}
	
	/**
	 * Setter method for traders.
	 * @param traders An ArrayList which holds the traders in the market.
	 */
	public void setTraders(ArrayList<Trader> traders) {
		this.traders = traders;
	}
	
	/**
	 * Returns the price of the top of buying priority queue.
	 * @return The price of the top of buying priority queue.
	 */
	public double buyingOrderPrice() {
		return buyingOrders.isEmpty() ? 0.0 : buyingOrders.peek().getPrice();
	}
	
	/**
	 * Returns the price of the top of selling priority queue.
	 * @return The price of the top of selling priority queue.
	 */
	public double sellingOrderPrice() {
		return sellingOrders.isEmpty() ? 0.0 : sellingOrders.peek().getPrice();
	}
	
	/**
	 * Returns a String which includes the current market size information.
	 * @return “Current market size: -total_$_in_buying_pq- -total_PQoin_in_selling_pq-”
	 */
	public String marketSizeInfo() {
		double dollars_sum = 0.0, coins_sum = 0.0;
		for(BuyingOrder b_order : buyingOrders)
			dollars_sum += b_order.getDollars();
		for(SellingOrder s_order : sellingOrders)
			coins_sum += s_order.getAmount();
		return String.format("Current market size: %.5f %.5f", dollars_sum, coins_sum);
	}
	
	/**
	 * Returns a String which includes the current price information.
	 * Note: If one of the priority queues is empty then the price related to it,
	 * then it is not included in the average current price.
	 * @return “Current prices: -cp_buying- -cp_selling- -cp_average-”
	 */
	public String currentPriceInfo() {
		final double buying_order_price = buyingOrderPrice();
		final double selling_order_price = sellingOrderPrice();
		double average_price;
		
		boolean flag1 = buyingOrders.isEmpty();
		boolean flag2 = sellingOrders.isEmpty();
		
		if(flag1 && flag2)
			average_price = 0.0;
		else if(!flag1 && flag2)
			average_price = buying_order_price;
		else if(flag1 && !flag2)
			average_price = selling_order_price;
		else
			average_price = (buying_order_price + selling_order_price) / 2.0;
		
		return String.format("Current prices: %.5f %.5f %.5f", buying_order_price, selling_order_price, average_price);
	}
	
	/**
	 * Returns a String which includes the total number of successful transactions.
	 * @return “Number of successful transactions: -num_of_successful_transaction-”
	 */
	public String transactionNumberInfo() {
		return "Number of successful transactions: " + no_of_successful_transactions;
	}
	
}
