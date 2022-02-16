
package question;

public class Customer {
	
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

	/**
	 * ID of the customer.
	 */
	private int ID;
	/**
	 * Name of the customer.
	 */
	private String name;
	/**
	 * Age of the customer.
	 */
	private int age;
	/**
	 * Operator of the customer.
	 */
	private Operator operator;
	/**
	 * Bill of the customer.
	 */
	private Bill bill;
	
	// *** Extra Fields ***
	
	/**
	 * Total amount of time the customer has spent for talking over the course of simulation.
	 */
	private int totalSpentTalkingTime;
	/**
	 * Total number of messages the customer has sent over the course of simulation.
	 */
	private int totalSentMessages;
	/**
	 * Total amount of Internet usage of the customer in terms of MB over the course of simulation.
	 */
	private double totalInternetUsage;
	
	
	
	/**
	 * A constructor with five parameters.
	 * @param ID ID of the customer.
	 * @param name Name of the customer.
	 * @param age Age of the customer.
	 * @param operator Operator of the customer.
	 * @param limitingAmount Limiting amount of the bill of the customer.
	 */
	public Customer(int ID, String name, int age, Operator operator, double limitingAmount) {
		this.ID = ID;
		this.name = name;
		setAge(age);
		this.operator = operator;
		bill = new Bill(limitingAmount);
		
		totalSpentTalkingTime = 0;
		totalSentMessages = 0;
		totalInternetUsage = 0.0;
	}
	
	/**
	 * For customers to talk via the operator.
	 * @param minute Duration of the call in minutes.
	 * @param other Another customer, mainly the second customer.
	 */
	public void talk(int minute, Customer other) {
		try {
			if(minute < 0)
				throw new IllegalArgumentException("Duration of the call must be non-negative.");
			
			if(ID != other.ID) {		// A customer cannot talk with him/herself.
				final double cost = operator.calculateTalkingCost(minute, this);
				
				if(bill.check(cost)) {
					bill.add(cost); 	// Add the talking cost to the bill.
					operator.addTalkingTime(minute);
					other.operator.addTalkingTime(minute);
					totalSpentTalkingTime += minute;
					other.totalSpentTalkingTime += minute;
				}
			}
		} catch(final IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * For customers to send message.
	 * @param quantity Number of messages to be sent.
	 * @param other Another customer, mainly the second customer.
	 */
	public void message(int quantity, Customer other) {
		try {
			if(quantity < 0)
				throw new IllegalArgumentException("Number of messages to be sent must be non-negative.");
			
			if(ID != other.ID) {		// A customer cannot message him/herself.
				final double cost = operator.calculateMessageCost(quantity, this, other);
				
				if(bill.check(cost)) {
					bill.add(cost); 	// Add the messaging cost to the bill.
					operator.addSentMessages(quantity);
					totalSentMessages += quantity;
				}
			}
		} catch(final IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * For customers to connect the Internet.
	 * @param amount Number of data as MB.
	 */
	public void connection(double amount) {
		try {
			if(amount < 0.0)
				throw new IllegalArgumentException("Number of data must be non-negative.");
			
			final double cost = operator.calculateNetworkCost(amount);
			
			if(bill.check(cost)) {
				bill.add(cost);		// Add the network cost to the bill.
				operator.addInternetUsage(amount);
				totalInternetUsage += amount;
			}
		} catch(final IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Getter method for age.
	 * @return Age of the customer.
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Setter method for age.
	 * Throws an exception if the given age is negative.
	 * @param age New age of the customer.
	 */
	public void setAge(int age) {
		try {
			if(age < 0)
				throw new IllegalArgumentException("Age must be non-negative.");
		
			this.age = age;
			
		} catch(final IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Getter method for operator.
	 * @return Operator of the customer.
	 */
	public Operator getOperator() {
		return operator;
	}
	
	/**
	 * Setter method for operator.
	 * @param operator New operator of the customer.
	 */
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	/**
	 * Getter method for bill.
	 * @return Bill of the customer.
	 */
	public Bill getBill() {
		return bill;
	}
	
	/**
	 * Setter method for bill.
	 * @param bill New bill of the customer.
	 */
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	
	
	// *** Extra Methods ***
	
	/**
	  * Getter method for ID.
	  * @return ID of the customer.
	  */
	public int getID() {
		return ID;
	}
	
	/**
	  * Getter method for name.
	  * @return Name of the customer.
	  */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter method for totalSpentTalkingTime.
	 * @return Total amount of time the customer has spent for talking over the course of simulation.
	 */
	public int getTotalSpentTalkingTime() {
		return totalSpentTalkingTime;
	}

	/**
	 * Getter method for totalSentMessages.
	 * @return Total number of messages the customer has sent over the course of simulation.
	 */
	public int getTotalSentMessages() {
		return totalSentMessages;
	}

	/**
	 * Getter method for totalInternetUsage.
	 * @return Total amount of Internet usage of the customer in terms of MB over the course of simulation.
	 */
	public double getTotalInternetUsage() {
		return totalInternetUsage;
	}
	
	/**
	 * Returns a string which tells us the ID of the customer, how much money has s/he spent for paying the bills, and his/her current debt.
	 * @return "Customer -ID of the Customer- : -Total Money Spent- -Current Debt-"
	 */
	@Override
	public String toString() {
		return String.format("Customer %d : %.2f %.2f", ID, bill.getTotalMoneySpent(), bill.getCurrentDebt());
	}

	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

