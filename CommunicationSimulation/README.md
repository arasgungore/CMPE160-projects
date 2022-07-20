# Project 1: Communication Simulation

This project was assigned for the Introduction to Object-Oriented Programming (CMPE 160) course in the Spring 2021 semester.



## Introduction

We are all living in a world where technological revolutions occur almost every
day. One of the most important revolutions was, of course, the Communication
Systems. Today, you’re going to implement a simple version of a communication
system.

There will be Customers, Operators, and Bills that belong to the Customers.
Customers can talk to each other, message each other, or they can connect to the
internet via their mobile phones. Operators have their unique costs for the
corresponding actions that will be given to you in the input. Every customer will have
their unique Bill that stores and do the necessary actions about their spending.

You’re going to take the input as an input file, do the operations, and will print
out the necessary information to the output file. There will be no System.in arguments,
in other words, there will not be any input given with the keyboard while the program
runs.

Note that there will be multiple classes, (Not like in CMPE150) Therefore, think
a little bit about the structure before starting to implement the program.

Field and Method names that are given to you in this Document must be identical
in terms of names and structures in your Project. That doesn’t mean that you can not
add extra Methods or Fields.



## Classes and Implementation Details

There will be 4 classes interacting with each other in this project:
- Main
- Customer
- Operator
- Bill

Note that, it will be better to do the necessary calculations via using corresponding
methods in the Customer, Operator, or Bill class, not in the main Class. Some parts of
the main class are given to you (input-output operations); however, the other 3 classes are given
completely empty. You’re expected to fill these classes with the given directions.


### Main.java

Main Class is for the general input-output operations. You are going to read an
input file that contains directions about the simulation and do several actions,
then you will print out the desired results into the output file. These directions will be
explained in a detailed way, later on in this document. The name of the input and output files
will be given as arguments of the program. For the input-output test cases, we are
going to use your main class.

There will be 2 arrays in the main method as follows:

```
● Customer[] customers
● Operator[] operators
```

You are going to store the corresponding data in these arrays. All of these arrays
must be initialized and, all of the elements in these arrays must be set to “null” as
default. You’re going to edit these arrays whenever a new object is created.


### Customer.java

Customer Class must have the following fields, exactly as named below:

```
● int ID
● String name
● int age
● Operator operator
● Bill bill
```

Customer class must have the following methods with the exact parameters:

```
● A constructor with five parameters: int ID, String name, int age, Operator
operator, double limitingAmount
● void talk(int minute, Customer other) : for customers to talk via the operator.
Other is the other customer, mainly the second customer.
● void message(int quantity, Customer other) : for customers to send a message,
amount is the number of messages to be sent. Other is the other customer,
mainly the second customer.
● void connection(double amount) : for customers to connect to the internet.
Amount is the number of data as MB.
● Getter and setter methods for age, operator, and bill. Ex: getAge(), setAge(int age)
```


### Operator.java

Operator class should have these fields with the exact names:

```
● int ID
● double talkingCharge
● double messageCost
● double networkCharge
● int discountRate
```

Operator class should have the following methods with the given parameters:

```
● A constructor with 5 parameters: ID, talkingCharge, messageCost,
networkCharge, discountRate
● double calculateTalkingCost(int minute, Customer customer) : for calculating
the total amount to pay for talking.
● double calculateMessageCost(int quantity, Customer customer, Customer
other) : for calculating the total amount to pay for talking.
● double calculateNetworkCost(double amount) : for calculating the total amount
to pay for talking.
● Getter and setter methods for talkingCharge, messageCost, networkCharge,
discountRate.
```


### Bill.java

Bill class must have the following fields:

```
● double limitingAmount
● double currentDebt
```

Bill Class must have the following methods with the exact parameters:

```
● Constructor with 1 parameter: limitingAmount. Note that you should initialize
the currentDebt as zero.
● boolean check(double amount) : for checking whether the limitingAmount is
exceeded or not.
● void add(double amount) : for adding debts to the bill.
● void pay(double amount) : for paying the bills with the given amount.
● void changeTheLimit(double amount) : for changing the limitingAmount.
● Getter methods for limitingAmount and currentDebt
```


## Instructions

- Most of the calculations must be in the Customer, Operator, and Bill classes
and their methods.
- For the talking charge, the charge must be the number of minutes times the
operator’s talking charge per minute. If the Customer’s age is below age 18
(18 is excluded) or higher than 65 (65 is excluded), then the operator applies a
discount with the corresponding discount rate that is given.
- For messaging, the cost must be the quantity of the messages times the operator’s
message cost per message. If the 2 customers are using the same operator, then
the operator applies a discount with the corresponding discount rate that
the operator has.
- For internet usage, calculations are straightforward, amount of MBs * the
network cost per MB.
- Before the actions, you should check the limit of the bill, if someone would
exceed the limit after the actions, then no actions must occur.
- We assume that every customer has enough money to pay their bill for the
desired amount.
- The ID of the Customers, Operators, and Bills should start from 0.
- You can declare extra fields or extra methods. But the given ones in this
document must exist in your program and their parameters and names must be
the same as the ones that are given.



## Input/Output

### Input

You’re going to read the input file line by line or token by token. The first 3 lines
will be integers. In the first line, the number “C” represents the number of customers
who will be in this project. The second line has the number “O” which tells you the
number of Operators in the project. In the third line, the number “N” will represent
the number of events that are going to be simulated.

Next N lines are going to be operations with the given rules:

```
1 - Creating a new Customer
2 - Creating a new Operator
3 - A customer can talk to another customer
4 - A customer can send a message to another customer
5 - A customer can connect to the internet
6 - A customer can pay his/her bills.
7 - A customer can change his/her operator
8 - A customer can change his/her Bill limit
```

_Input 1: Creating a new Customer_

This line contains a String name, an int age, an int ID as operator ID, double as
the limiting amount.

```
1 <name> <age> <ID> <limitingAmount>
```

Note that, the ID of the customer should be in the order of creation. To illustrate,
first created customer must have ID 0 and the location in the array of that customer
should be on his/her ID. Also, there is no operation to create a bill object, therefore you
need to create in the Customer class and store the bill object whenever you are going
to create a customer.

_Input 2: Creating a new Operator_

This Input line is followed by 3 doubles and 1 integer respectively, double
talkingCharge, messageCost, networkCharge, int discountRate

```
2 <talkingCharge> <messageCost> <networkCharge> <discountRate>
```

Creation of an Operator object. Again, the ID of the operator must be in the order
of creation.

_Input 3: A customer talks to another customer_

This line is followed by 3 integers that represent respectively, the ID of the first
customer, the ID of the second customer, time as an integer.

```
3 <1stCustomerID> <2ndCustomerID> <time>
```

_Input 4: A customer sends a message to another customer_

This line is followed by 3 integers that represent respectively, the ID of the first
customer, the ID of the second customer, number of messages that Customer 1 has sent.

```
4 <1stCustomerID> <2ndCustomerID> <quantity>
```

_Input 5: A customer connects to the internet_

This line is followed by an integer and a double, the ID of the customer, and amount
of the internet that the customer uses in MB.

```
5 <CustomerID> <amount>
```

_Input 6: A customer pays his/her bills_

This line is followed by an integer and a double, ID of the customer, and the amount
of money that the customer wants to pay for his/her bill.

```
6 <CustomerID> <amount>
```

_Input 7: A customer changes his/her operator_

This line contains 2 integers as the CustomerID and the OperatorID.

```
7 <CustomerID> <OperatorID>
```

_Input 8: A customer changes his/her Bill limit_

This line contains 1 integer and 1 double for, the CustomerID, and the new limit.

```
8 <CustomerID> <amount>
```

### Output

You should calculate the following and print them out to the output file.

1. For each operator, you should print out, the amount of time that they serviced for
talking, number of messages sent via that operator, amount of internet usage in terms
of MB that operator provided.

```
Operator <ID of the Operator> : <talking time> <num of messages> <MBs of usage>
```

2. For each Customer, How much money that they spend for paying their bills
and the current debt at the end of the simulation in their bills.

```
Customer <ID of the Customer> : <total money spent> <current debt>
```

3. ID of the Customer that talks the most and the amount of time in terms of
minutes. (if 2 Customers are equal, then print out the one that has a smaller ID)

```
<name of the Customer> : <talking time>
```

4. ID of the Customer that sends messages the most and the number of
messages. (if 2 Customers are equal, then print out the one that has a smaller ID)

```
<name of the Customer> : <number Of Messages>
```

5. ID of the Customer that connects the internet the most and the amount in
terms of MBs. (if 2 Customers are equal, then print out the one that has a smaller ID)

```
<name of the Customer> : <connection amount> 
```
