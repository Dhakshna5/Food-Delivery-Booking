# Food-Delivery-Booking

### There are 3 classes used :

DeliveryExecutives

FoodDeliveryCompany and

Main class

------

### DeliveryExecutives class

Each executive contains 6 unique values

Executive name 

previousDeliveryDestination

previousDeliveryTime

deliveryChargedEarned 

allowance

ordersCompleted

They all have both getters and setters

------

### FoodDeliveryCompany class

They have 2 global lists

List<DeliveryExecutives> DeliveryExecutive

List<String> tripHistory

In the constructor all the Executives names are initialized as DEn

METHOD 1 : handlebooking() this method calls assignDe

METHOD 2 : assignDe() 

the goal of this method is to choose which DeliveryExecutive among the 5 to be allotted for this current trip and this process is done by 
traversing all the Executives from the list

1. Find what is the maximum delivery charge among all the Executives
2. The Executive is chosen based on 3 conditions

   (i) his current delivery charge < maximum delivery charge (so that everyone can get opportunity)

   (ii) executives previous destination must be the current destination to be reached and

   (iii) previous delivery time and the current time difference must be within 15 minutes
4. Condition 2 and 3 has to be satisfied simultaneously so that he can take current order also with him to reach that location so it is
   ( i || (ii && iii) )
5. Executives who satisfies Cond 1 alone gets delivery charge of 50 but the Executives who satisfies Cond 2 gets delivery charge 5 for 
   each trip
6. Once the executive is chosen the values like deliverycharge earned , allowance , previousDeliveryDestination and previousDeliveryTime 
   are entered.

METHOD 3 : calculateDeliveryTime()

1. this method adds 15 mins to the pickup time when passed and returns the new time
2. also adds 30 mins from pickup time and decides the delivery time since each deivery takes 30 minutes.

METHOD 4 : displayDeliveryHistory()

simply traverses all the values of tripHistory() list and displays

METHOD 5 : displayDeliveryExecutiveActivity

1. displays executive name
2. allowance (10 for each trip)
3. delivery charge
4. total (allowance + delivery charge)

METHOD 6 : isWithin15Minutes

1. gets 2 parameters lastTime and currentTime
2. lastTime = Executives previousDeliveryTime (calculated from assignDe)
3. currentTime = user enter time

------

### Main class

1. create an object comapny for the class FoodDeliveryCompany by passing the argument 5 (works for n input)
2. invoke the method handleBooking() by passing the arguments
   (i)   customer id
   (ii)  restaurant
   (iii) destination
   (iv)  time
3. finally invoke method displayDeliveryExecutiveActivity() to display all the necessary output.






