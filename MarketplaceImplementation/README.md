# Project 3: Marketplace Implementation


## Introduction

The economy is an indispensable fact of our lives. In real life, for buying and selling
things, we use some printed or virtual assets, called money. However, your money
does not have any difference with paper in your pocket. It gains its value only in a
marketplace.

In this project, you are going to implement a basic marketplace model. This model
consists of traders, wallets, transactions, currencies, and the market. For simplicity,
we have two currencies in the market: dollar and PQoin(Priority Queue Coin).
Traders can give buying or selling orders to the market if they can afford them.

In our market model, there are two priority queues for storing orders. In a priority
queue, elements having the highest priority serve before. The priority rules are written in
the next sections. With these rules, your market implementation should make
transactions if these two priority queues’ tops overlap.



## Packages and Classes

You are provided with the general structure of the project below. In this context, there
are 2 packages and 8 classes. The details of the classes, functionalities to be
implemented and overall operations of the project with the corresponding format are
presented in the following sections.


### Package: executable

The java files of these classes are currently empty, you need to implement
these classes according to the information provided in this document.


#### Main.java

The Main class should be implemented in a way that it should read
corresponding parameters and events from the input file, and log the
results to the output file. The input and output file paths are given in
arguments of the program.

You should create a random object with the given seed. In order to
access Random object everywhere, you may define it as a static
object:

```
public static Random myRandom;
```


### Package: elements

The java files of these classes are currently empty, you need to implement
these classes according to the information provided in this document.


#### Market.java
There is only one market throughout the program.
The fields and methods to be implemented in Market.java:

```
private PriorityQueue<SellingOrder> sellingOrders;
private PriorityQueue<BuyingOrder> buyingOrders;
private ArrayList<Transaction> transactions;
public void giveSellOrder(SellingOrder order);
public void giveBuyOrder(BuyingOrder order);
public void makeOpenMarketOperation(double price);
public void checkTransactions(ArrayList<Trader> traders);
public Market(int fee);
```

The additional data for implementing these methods are given in the
“Implementation Details” section.


#### Order.java
Order is the parent class of BuyingOrder.java and SellingOrder.java.
Every Order should have:

```
double amount;
double price;
int traderID;
public Order(int traderID, double amount, double price)
```


#### SellingOrder.java

SellingOrder.java should be a child of Order.java.
In order to implement PriorityQueue<SellingOrder>, the SellingOrder
method implements Comparable, and compareTo(SellingOrdere)
should be overridden. The comparison logic of SellingOrder is given in
“Implementation Details” in this document.


### BuyingOrder.java

BuyingOrder.java should be a child of Order.java.
In order to implement PriorityQueue<BuyingOrder>, the BuyingOrder
method implements Comparable, and compareTo(BuyingOrdere)
should be overridden. The comparison logic of BuyingOrder is given in
“Implementation Details” in this document.


#### Trader.java

Each Trader object created in the program has an ID and a wallet. The
IDs of the traders are starting from 0 and are incremented when a trader
object is created. A trader can give buying or selling orders to the
market. However, if the trader cannot afford that order, there is nothing
to do.

The fields and signature of the methods that should be implemented
for the Trader class are as follows:

```
private int id;
private Wallet wallet;
public Trader(double dollars, double coins);
public int sell(double amount, double price, Market market);
public int buy(double amount, double price, Market market);
public static int numberOfUsers = 0;
```

#### Wallet.java

Each Wallet object keeps the amounts of dollars and PQoins.
The necessary fields and signature of the methods to be implemented
for the Wallet class are as follows:

```
private double dollars;
private double coins;
private double blockedDollars;
private double blockedCoins;
public Wallet(double dollars, double coins);
```

#### Transaction.java

```
Each Transaction object includes a buying and a selling order.
private SellingOrder sellingOrder;
private BuyingOrder sellingOrder;
```



## Implementation Details

★ PriorityQueue functionality in the Market:
  ○ There are two distinct PriorityQueue you have. One of them is for
  SellingOrders, the other is for BuyingOrders.
  ○ PriorityQueue<SellingOrder>
■ When you poll() this PQ you should get the SellingOrderthat has the
lowest price. If two orders have the same price then you get the
SellingOrder which has the highest amount. If it is still the same, you
get the SellingOrder that has the lowest tradersID.
  ○ PriorityQueue<BuyingOrder>
■ When you poll() this PQ you should get the BuyingOrderthat has the
highest price. If two orders have the same price then you get the
BuyingOrder which has the highest amount. If it is still the same, you
get the BuyingOrder that has the lowest tradersID.
  ○ At the end of each query, the market checks whether the prices at the PQs
  are overlapping. If there is an overlap, the market should make transactions
  with the top of PQs, until there is no overlapping.
★ Query#666 (make open market operation):
  ○ In this query, Trader#0 (The System) gives buying or selling orders for setting
  the current price of PQoin to the given price.
  ○ The final price can differ from the given price. The market tries to converge as
  much as possible.
  ○ You should create system’s orders until:
    ■ price of buyingOrders.peek() < price given by the system
    ■ price of sellingOrders.peek() > price given by the system
★ Handling Orders:
  ○ After a BuyingOrder is given, dollars reserved for this order (amount*price)
  cannot be used again. Therefore, you should store this amount in the
  blockedDollars of the trader's wallet.
  ○ After a SellingOrder is given, PQoins reserved for this order (amount) cannot
  be used again. Therefore, you should store this amount in the blockedCoins
  of the trader's wallet.
  ○ When making transactions the blocked currencies should disappear.
  ○ When making transactions the amount of BuyingOrder and the amount of
  SellingOrder are possibly different. In that case, you should divide an order
  into two parts. One goes to the transaction, the other returns back to its
  PriorityQueue.
  ○ When making transactions the price of BuyingOrder and the price of Selling
  order might differ. If so, you should take the SellingOrder’s price as the
  Transaction price. However, if this is the case, the amount of dollars blocked
  for the extra price should return to the wallet.
★ Market Fee:
  ○ The market fee is an integer representing how much commission the market
  receives from the transaction per thousand.
  ○ Conducting a transaction, Seller gets (amount*price*(1-fee/1000).


## Input/Output

The first line of the input file specifies the random seed that you have to use to
generate random numbers in the project: A

The second line of the input file specifies the initial transaction fee of the
marketplace, the number of users, and number of queries: B C D

The next C lines represent the initial dollars and PQoins asset in each trader’s wallet.

```
<dollar_amount> <PQoin_amount>
<dollar_amount> <PQoin_amount>
...
<dollar_amount> <PQoin_amount>
<dollar_amount> <PQoin_amount>
```

The next D lines are the queries. There are exactly 14 types of events.

Trader specific queries:

➔ 10: Give buying order of specific price

```
10 <trader_id> <price> <amount>
```

➔ 11: Give buying order of market price

```
11 <trader_id> <amount>
```

Note: This is similar to Query#10, but it takes the current selling price as price.
Note: If there is no current selling price then increment the number of invalid queries.

➔ 20: Give selling order of specific price

```
20 <trader_id> <price> <amount>
```

➔ 21: Give selling order of market price

```
21 <trader_id> <amount>
```

Note: This is similar to Query#20, but it takes the current buying price as price.

➔ 3: Deposit a certain amount of dollars to the wallet

```
3 <trader_id> <amount>
```

➔ 4: Withdraw a certain amount of dollars from the wallet

```
4 <trader_id> <amount>
```

➔ 5: Print wallet status

```
5 <trader_id>
```

Prints “Trader <traderID>: <trader_s_dollars>$ <trader_s_PQoins>PQ” to the output file.

### System Queries

➔ 777: Give rewards to all traders

```
777
```

When this query is read, the system creates and distributes random amounts
of PQoins to all traders.
For each trader add myRandom.nextDouble()*coins to the trader’s wallet.

➔ 666: Make an open market operation

```
666 <price>
```

When this query is read, the system compensates buying or selling orders in
order to set the price of PQoin to the given price.

This query can increase or decrease the total amount of PQoin in the market.

➔ 500: Print the current market size

```
500
```

Prints “Current market size: <total_$_in_buying_pq> <total_PQoin_in_selling_pq>” to the output file.

➔ 501: Print number of successful transactions

```
501
```
Prints “Number of successful transactions: <num_of_successful_transaction>” to the output file.

➔ 502: Print the number of invalid queries

```
502
```

Prints “<number_of_invalid_queries>” to the output file.

Note: If a trader is unable to satisfy the query’s liabilities, then the number of
invalid queries is incremented by one.

➔ 505: Print the current prices

```
505
```
Prints “Current prices: <cp_buying> <cp_selling> <cp_average>” to the output file.
Note: If one of the PriorityQueues is empty then the price related to it, is not
included in the average current price.

➔ 555: Print all traders’ wallet status

```
555
```

Prints “<trader_s_dollars>$ <trader_s_PQoins>PQ” of all traders.
