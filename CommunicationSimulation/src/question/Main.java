
package question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {


	public static void main(String args[]) {

		Customer[] customers;
		Operator[] operators;

		int C, O, N;

		File inFile = new File(args[0]);  // args[0] is the input file
		File outFile = new File(args[1]);  // args[1] is the output file
		try {
			PrintStream outstream = new PrintStream(outFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
		}

		C = reader.nextInt();
		O = reader.nextInt();
		N = reader.nextInt();

		customers = new Customer[C];
		operators = new Operator[O];

		//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

		int cust_cnt = 0, oper_cnt = 0;		// Counters for customers and operators respectively.
		
		// Read inputs and operate accordingly.
		try {
			for(int line_no = 1; line_no <= N; line_no++) {
				final int operation_type = reader.nextInt();
				switch(operation_type) {
					case 1: {		// Input 1 : Creating a new Customer.
						final String name = reader.next();
						final int age = reader.nextInt();
						final int oper_ID = reader.nextInt();
						final double limitingAmount = reader.nextDouble();
						
						if(oper_ID >= oper_cnt)
							throw new NullPointerException("Invalid operator ID in operation " + operation_type + ", line " + line_no + ".");
						if(cust_cnt >= C)
							throw new ArrayIndexOutOfBoundsException("Number of customers exceeds C.");
						
						customers[cust_cnt] = new Customer(cust_cnt, name, age, operators[oper_ID], limitingAmount);
						cust_cnt++;
						break; }
					case 2: {		// Input 2 : Creating a new Operator.
						final double talkingCharge = reader.nextDouble();
						final double messageCost = reader.nextDouble();
						final double networkCharge = reader.nextDouble();
						final int discountRate = reader.nextInt();
						
						if(oper_cnt >= O)
							throw new ArrayIndexOutOfBoundsException("Number of operators exceeds O.");
						
						operators[oper_cnt] = new Operator(oper_cnt, talkingCharge, messageCost, networkCharge, discountRate);
						oper_cnt++;
						break; }
					case 3: {		// Input 3 : A customer talks to another customer.
						final int first_cust_ID = reader.nextInt();
						final int second_cust_ID = reader.nextInt();
						final int time = reader.nextInt();
						
						if(first_cust_ID >= cust_cnt)
							throw new NullPointerException("Invalid first customer ID in operation " + operation_type + ", line " + line_no + ".");
						if(second_cust_ID >= cust_cnt)
							throw new NullPointerException("Invalid second customer ID in operation " + operation_type + ", line " + line_no + ".");
						
						customers[first_cust_ID].talk(time, customers[second_cust_ID]);
						break; }
					case 4: {		// Input 4 : A customer sends message to another customer.
						final int first_cust_ID = reader.nextInt();
						final int second_cust_ID = reader.nextInt();
						final int no_of_msg = reader.nextInt();
						
						if(first_cust_ID >= cust_cnt)
							throw new NullPointerException("Invalid first customer ID in operation " + operation_type + ", line " + line_no + ".");
						if(second_cust_ID >= cust_cnt)
							throw new NullPointerException("Invalid second customer ID in operation " + operation_type + ", line " + line_no + ".");
						
						customers[first_cust_ID].message(no_of_msg, customers[second_cust_ID]);
						break; }
					case 5: {		// Input 5 : A customer connects to the Internet.
						final int cust_ID = reader.nextInt();
						final double amount = reader.nextDouble();
						
						if(cust_ID >= cust_cnt)
							throw new NullPointerException("Invalid customer ID in operation " + operation_type + ", line " + line_no + ".");
						
						customers[cust_ID].connection(amount);
						break; }
					case 6: {		// Input 6 : A customer pays his/her bills.
						final int cust_ID = reader.nextInt();
						final double amount = reader.nextDouble();
						
						if(cust_ID >= cust_cnt)
							throw new NullPointerException("Invalid customer ID in operation " + operation_type + ", line " + line_no + ".");
						
						customers[cust_ID].getBill().pay(amount);
						break; }
					case 7: {		// Input 7 : A customer changes his/her operator.
						final int cust_ID = reader.nextInt();
						final int oper_ID = reader.nextInt();
						
						if(cust_ID >= cust_cnt)
							throw new NullPointerException("Invalid customer ID in operation " + operation_type + ", line " + line_no + ".");
						if(oper_ID >= oper_cnt)
							throw new NullPointerException("Invalid operator ID in operation " + operation_type + ", line " + line_no + ".");
						
						customers[cust_ID].setOperator(operators[oper_ID]);
						break; }
					case 8: {		// Input 8 : A customer changes his/her Bill limit.
						final int cust_ID = reader.nextInt();
						final double limitingAmount = reader.nextDouble();
						
						if(cust_ID >= cust_cnt)
							throw new NullPointerException("Invalid customer ID in operation " + operation_type + ", line " + line_no + ".");
						
						customers[cust_ID].getBill().changeTheLimit(limitingAmount);
						break; }
					default:		// Invalid input.
						throw new IllegalStateException("Invalid operation. Operation type must be between 1-8.");
				}
			}
		} catch(final RuntimeException e) {		// Print the runtime error exception message.
			System.out.println(e.getMessage());
		}
		
		reader.close();
		
		PrintStream outstream1;		// Create another PrintStream object in the main class for printing out results.
		try {
			outstream1 = new PrintStream(outFile);
		} catch(FileNotFoundException e2) {
			e2.printStackTrace();
			return;
		}
		
		// Print results.
		
		/*
		 * 1) For each operator, print out, amount of time that they serviced for talking,
		 *    number of messages sent via that operator, amount of Internet usage in terms of MB that operator provided.
		 *    
		 *    Operator <ID of the Operator> : <Talking Time> <Number of Messages> <MBs of Internet Usage>
		 */
		for(final Operator oper : operators)
			outstream1.println(oper.toString());
		
		Customer mostTalkingCust = customers[0];		// The customer that talks the most.
		Customer mostMessagingCust = customers[0];		// The customer that sends messages the most.
		Customer mostConnectingCust = customers[0];		// The customer that connects to the Internet the most.
		
		/*
		 * 2) For each Customer, print out, how much money that they spend for paying their bills
		 *    and the current debt at the end of the simulation in their bills.
		 * 
		 *    Customer <ID of the Customer> : <Total Money Spent> <Current Debt>
		 */
		for(final Customer cust : customers) {
			
			if(cust.getTotalSpentTalkingTime() > mostTalkingCust.getTotalSpentTalkingTime())
				mostTalkingCust = cust;			// Find the customer that talks the most.
			
			if(cust.getTotalSentMessages() > mostMessagingCust.getTotalSentMessages())
				mostMessagingCust = cust;		// Find the customer that sends messages the most.
			
			if(cust.getTotalInternetUsage() > mostConnectingCust.getTotalInternetUsage())
				mostConnectingCust = cust;		// Find the customer that connects to the Internet the most.
			
			// Print out each customer with their relevant information.
			outstream1.println(cust.toString());
		}
		
		/*
		 * 3) Print out name of the Customer that talks the most and the amount of time in terms of minutes.
		 * 
		 *    <Name of the Customer> : <Talking Time>
		 *    (If 2 Customers are equal, then print out the one that has smaller ID.)
		 */
		outstream1.println(mostTalkingCust.getName() + " : " + mostTalkingCust.getTotalSpentTalkingTime());
		
		/*
		 * 4) Print out name of the Customer that sends messages the most and the number of messages.
		 * 
		 *    <Name of the Customer> : <Number of Messages>
		 *    (If 2 Customers are equal, then print out the one that has smaller ID.)
		 */
		outstream1.println(mostMessagingCust.getName() + " : " + mostMessagingCust.getTotalSentMessages());
		
		/*
		 * 5) Print out name of the Customer that connects the Internet the most and the amount in terms of MBs.
		 * 
		 *    <Name of the Customer> : <MBs of Internet Usage>
		 *    (If 2 Customers are equal, then print out the one that has smaller ID.)
		 */
		outstream1.printf("%s : %.2f\n", mostConnectingCust.getName(), mostConnectingCust.getTotalInternetUsage());
		
		outstream1.close();
		
		//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
	} 
}

