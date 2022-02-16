
package question;

public class Bill {

	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

	/**
	 * Limiting amount of the bill.
	 * If a customer would exceed the limit after the actions, then no actions occur.
	 */
	private double limitingAmount;
	/**
	 * Current debt of the bill.
	 */
	private double currentDebt;
	
	// *** Extra Fields ***
	
	/**
	 * Total amount of money the customer has spent for paying their bills over the course of simulation.
	 */
	private double totalMoneySpent;
	
	
	
	/**
	 * Constructor with 1 parameter.
	 * Throws an exception if the given limiting amount is negative.
	 * @param limitingAmount Limiting amount of the bill.
	 */
	public Bill(double limitingAmount) {
		try {
			if(limitingAmount < 0.0)
				throw new IllegalArgumentException("Limiting amount must be non-negative.");
			
			this.limitingAmount = limitingAmount;
			currentDebt = 0.0;
			
			totalMoneySpent = 0.0;
			
		} catch(final IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Checks whether the limitingAmount is exceeded or not when a certain amount is about to be added to bill's current debt.
	 * @param amount Amount to check.
	 * @return TRUE if the limitingAmount is not exceeded, FALSE otherwise.
	 */
	public boolean check(double amount) {
		return currentDebt + amount <= limitingAmount;
	}
	
	/**
	 * Adds debt to the bill.
	 * This method has to be called with the "public boolean check(double amount)" method.
	 * @param amount Amount to be added to the current debt.
	 */
	public void add(double amount) {
		currentDebt += amount;
	}
	
	/**
	 * Pays the bills with the given amount.
	 * If someone tries to pay more than her debt, then she must pay the current debt she has.
	 * @param amount Amount to be payed from the current debt.
	 */
	public void pay(double amount) {
		if(amount < currentDebt) {
			currentDebt -= amount;
			totalMoneySpent += amount;
		}
		else {
			totalMoneySpent += currentDebt;
			currentDebt = 0.0;		// There is no negative debt.
		}
	}
	
	/**
	 * Changes the limitingAmount, i.e. setter method for limitingAmount.
	 * If the current debt would exceed the limit after setting a new limit, then the limit remains unchanged.
	 * @param amount New limiting amount of the bill.
	 */
	public void changeTheLimit(double amount) {
		if(amount >= currentDebt)
			limitingAmount = amount;
	}
	
	/**
	 * Getter method for limitingAmount.
	 * @return Limiting amount of the bill.
	 */
	public double getLimitingAmount() {
		return limitingAmount;
	}

	/**
	 * Getter method for currentDebt.
	 * @return Current debt of the bill.
	 */
	public double getCurrentDebt() {
		return currentDebt;
	}
	
	
	
	// *** Extra Methods ***
	
	/**
	 * Getter method for totalMoneySpent.
	 * @return Total amount of money the customer has spent for paying their bills over the course of simulation.
	 */
	public double getTotalMoneySpent() {
		return totalMoneySpent;
	}

	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

