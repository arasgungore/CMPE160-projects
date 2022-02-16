
package question;

public class Operator {
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

	/**
	 * ID of the operator.
	 */
	private int ID;
	/**
	 * Talking charge of the operator per minute.
	 */
	private double talkingCharge;
	/**
	 * Message cost of the operator per message.
	 */
	private double messageCost;
	/**
	 * Network charge of the operator per MB.
	 */
	private double networkCharge;
	/**
	 * Discount rate of the operator as percentage.
	 */
	private int discountRate;
	
	// *** Extra Fields ***
	
	/**
	 * Total amount of time the operator has serviced for talking over the course of simulation.
	 */
	private int totalSpentTalkingTime;
	/**
	 * Total number of messages sent via the operator over the course of simulation.
	 */
	private int totalSentMessages;
	/**
	 * Total amount of Internet usage in terms of MB the operator has provided over the course of simulation.
	 */
	private double totalInternetUsage;
	
	
	
	/**
	 * A constructor with 5 parameters.
	 * @param ID ID of the operator.
	 * @param talkingCharge Talking charge of the operator per minute.
	 * @param messageCost Message cost of the operator per message.
	 * @param networkCharge Network charge of the operator per MB.
	 * @param discountRate Discount rate of the operator as percentage.
	 */
	public Operator(int ID, double talkingCharge, double messageCost, double networkCharge, int discountRate) {
		this.ID = ID;
		setTalkingCharge(talkingCharge);
		setMessageCost(messageCost);
		setNetworkCharge(networkCharge);
		setDiscountRate(discountRate);
		
		totalSpentTalkingTime = 0;
		totalSentMessages = 0;
		totalInternetUsage = 0.0;
	}

	/**
	 * For calculating the total amount to pay for talking.
	 * @param minute Duration of the call in minutes.
	 * @param customer Customer who is making the call.
	 * @return Talking cost.
	 */
	public double calculateTalkingCost(int minute, Customer customer) {
		// Talking cost must be the amount of minutes times the operator’s talking charge per minute.
		double cost = minute * talkingCharge;
		
		// Apply discount if the customer’s age is below age 18 (18 is excluded) or higher than 65 (65 is excluded).
		if(customer.getAge() < 18 || customer.getAge() > 65)
			cost *= (double)(100 - discountRate) / 100.0;
		
		return cost;
	}
	
	/**
	 * For calculating the total amount to pay for messaging.
	 * @param quantity Number of messages to be sent.
	 * @param customer Customer who is sending the message.
	 * @param other Customer who is receiving the message.
	 * @return Messaging cost.
	 */
	public double calculateMessageCost(int quantity, Customer customer, Customer other) {
		// Messaging cost must be the quantity of the messages times the operator’s message cost per message.
		double cost = quantity * messageCost;
		
		// Apply discount if the 2 customers are using the same operator.
		if(customer.getOperator().getID() == other.getOperator().getID())
			cost *= (double)(100 - discountRate) / 100.0;
		
		return cost;
	}

	/**
	 * For calculating the total amount to pay for connecting to the Internet.
	 * @param amount Number of data as MB.
	 * @return Network cost.
	 */
	public double calculateNetworkCost(double amount) {
		// Connection cost must be the amount of MBs times the network cost per MB.
		double cost = amount * networkCharge;
		
		return cost;
	}

	/**
	 * Getter method for talkingCharge.
	 * @return Talking charge of the operator per minute.
	 */
	public double getTalkingCharge() {
		return talkingCharge;
	}

	/**
	 * Setter method for talkingCharge.
	 * Throws an exception if the given talking charge is negative.
	 * @param talkingCharge New talking charge of the operator per minute.
	 */
	public void setTalkingCharge(double talkingCharge) {
		try {
			if(talkingCharge < 0.0)
				throw new IllegalArgumentException("Talking charge must be non-negative.");
			
			this.talkingCharge = talkingCharge;
			
		} catch(final IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Getter method for MessageCost.
	 * @return Message cost of the operator per message.
	 */
	public double getMessageCost() {
		return messageCost;
	}

	/**
	 * Setter method for MessageCost.
	 * Throws an exception if the given message cost is negative.
	 * @param messageCost New message cost of the operator per message.
	 */
	public void setMessageCost(double messageCost) {
		try {
			if(messageCost < 0.0)
				throw new IllegalArgumentException("Message cost must be non-negative.");
			
			this.messageCost = messageCost;
			
		} catch(final IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Getter method for networkCharge.
	 * @return Network charge of the operator per MB.
	 */
	public double getNetworkCharge() {
		return networkCharge;
	}

	/**
	 * Setter method for networkCharge.
	 * Throws an exception if the given network charge is negative.
	 * @param networkCharge New network charge of the operator per MB.
	 */
	public void setNetworkCharge(double networkCharge) {
		try {
			if(networkCharge < 0.0)
				throw new IllegalArgumentException("Network charge must be non-negative.");
			
			this.networkCharge = networkCharge;
			
		} catch(final IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Getter method for discountRate.
	 * @return Discount rate of the operator as percentage.
	 */
	public int getDiscountRate() {
		return discountRate;
	}

	/**
	 * Setter method for discountRate.
	 * Throws an exception if the given discount rate is higher than 100 or lower than 0.
	 * @param discountRate New discount rate of the operator as percentage.
	 */
	public void setDiscountRate(int discountRate) {
		try {
			if(discountRate < 0 || discountRate > 100)
				throw new IllegalArgumentException("Discount rate must be between 0-100.");
			
			this.discountRate = discountRate;
			
		} catch(final IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	// *** Extra Methods ***
	
	/**
	 * Getter method for ID.
	 * @return ID of the operator.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Getter method for totalSpentTalkingTime.
	 * @return Total amount of time the operator has serviced for talking over the course of simulation.
	 */
	public int getTotalSpentTalkingTime() {
		return totalSpentTalkingTime;
	}

	/**
	 * Getter method for totalSentMessages.
	 * @return Total number of messages sent via the operator over the course of simulation.
	 */
	public int getTotalSentMessages() {
		return totalSentMessages;
	}

	/**
	 * Getter method for totalInternetUsage.
	 * @return Total amount of Internet usage in terms of MB the operator has provided over the course of simulation.
	 */
	public double getTotalInternetUsage() {
		return totalInternetUsage;
	}
	
	/**
	 * Adds to the total spent talking time.
	 * @param minute Duration of the call in minutes.
	 */
	public void addTalkingTime(int minute) {
		final int newTotalSpentTalkingTime = totalSpentTalkingTime + minute;
		if(newTotalSpentTalkingTime >= 0)
			totalSpentTalkingTime = newTotalSpentTalkingTime;	// Result has to be non-negative.
		// Result can be negative if:
		// 1) Value of "minute" is negative with a larger absolute value than totalSpentTalkingTime.
		// 2) Overflow occurs.
	}

	/**
	 * Adds to the total number of sent messages.
	 * @param quantity Number of messages to be sent.
	 */
	public void addSentMessages(int quantity) {
		final int newTotalSentMessages = totalSentMessages + quantity;
		if(newTotalSentMessages >= 0)
			totalSentMessages = newTotalSentMessages;			// Result has to be non-negative.
		// Result can be negative if:
		// 1) Value of "quantity" is negative with a larger absolute value than totalSentMessages.
		// 2) Overflow occurs.
	}

	/**
	 * Adds to the total Internet usage.
	 * @param amount Number of data as MB.
	 */
	public void addInternetUsage(double amount) {
		final double newTotalInternetUsage = totalInternetUsage + amount;
		if(newTotalInternetUsage >= 0.0)
			totalInternetUsage = newTotalInternetUsage;			// Result has to be non-negative.
		// Result can be negative if:
		// 1) Value of "amount" is negative with a larger absolute value than totalInternetUsage.
		// 2) Overflow occurs.
	}
	
	/**
	 * Returns a string which tells us the ID of the operator, how many minutes the operator has serviced for talking,
	 * how many messages sent via the operator, and how many MBs the operator has provided for Internet usage.
	 * @return "Operator -ID of the Operator- : -Talking Time- -Number of Messages- -MBs of Internet Usage-"
	 */
	@Override
	public String toString() {
		return String.format("Operator %d : %d %d %.2f", ID, totalSpentTalkingTime, totalSentMessages, totalInternetUsage);
	}
	
	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

