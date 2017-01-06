Scott Miller
1/5/17
Vending Machine Kata


To Build
--------
Code example has an accompanying gradle build file.  Executing 'gradle build' from the project directory will build
the code and run all tests. 

Additionally the gradle wrapper has been provided as well such that the project can be built if you do not have gradle
already installed.  Only an internet connection is required.

From the root project directory:

On Windows:
	gradlew.bat build
	
On Linux:
	./gradlew build
	*you may need to make gradlew executable 'chmod +x gradlew' should do the trick.



Application Assumptions / Design Decisions
------------------------------------------
With some ambiguity existing in the kata description is was necesary to make a few assumptions about system functionality.
Under normal circumstances I would reach out to the proper stake holder/customer/user to attain clarification.  For the 
purposes of a kata and given the straight forward nature of a vending machine I made these decision on my own.
(Not to mention you probably didn't want half a dozen phone calls/emails)  Were I in a situation where I needed to make 
these decisions uninformed I would later confirm them with the customer at first opportunity.
The following assumptions were made:

Product addition/removal
In order to add or remove (short of purchasing) products. The machine wound be turned off before the product was chanded
and then turned back on and re-initialized. i.e.) No live adding/removing.

Change coins addition/removal
In order to add or remove (short of purchasing) coins used to make change the machine would be turned off before coins 
would be added or removed and then re-initialized. i.e.) No live adding/removing.

Seperation of inserted coins and change coins
As is done on most vending machines, coins inserted for purchase are not recycled/re-used for making change.  They are placed
into a collection tray to be collected by the machine owner/care taker.  This is done for severals reasons: To protect against
the use of fraudulent coins,  It reduces the complexity of the internal mechanisms, and since product will need to be restocked
there exists little need for an eternal supply of change coins.

Change provided
It was assumed that change was to be provided with the fewest number of coins possible from the available change coins.
This is how most vending machines operate and makes sense to eliminate the need to prematurely restock change coins.

Coins that the machine may use
The use of Nickels, Dimes, and Quarters is relatively hard coded.  An ideal system would have these coins defined in an outside
xml file or database for easy modification were a change necessary.  To keep the kata shorter this was not supported.
Additionally the reality is that such a modification is unlikely to be required in real life.

Coin validation
Coins are validated based on their weight and diameter.  The data used was located online and should be relatively accurate.
The coin class and test class detail the decision process behind what minimum and maximum coin size is allowed.  Ideally this
would be driven by real world data sampling.

Prodcuts the machine may sell
The products the machine can sell has been relatively hard coded to 'Cola','Chips', and 'Candy'.  Though not as deeply embedded
as the coins, changing the products available in the machine would require a code change.  As this is a more realistic case 
than a change in allowable coin types my next add-on would be supporting a wider array of products.  However, again, the kata 
surely kept the list short for simplicity reasons.

Adding too many coins
To keep the machine from running out of change coins and from jamming the machine physically, the machine will not allow a user 
to insert more money than is required to purchase the most expensive item.  Any additional coins are sent directly to the coin return.

Machine Mechanisms
It is assumed that the machine has mechanical components that do the following:
	Determine how many of each product are available
	Determine how many of each change coin are available
	Given a list of coins by value, actuates the change coin holder to place the proper coins in the return
	Determine an inserted coin's weight and diameter
	Holds inserted coins in a seperate area until a purchase is made or a coin return is requested
	When a purchase is made deposits inserted coins into a collection tray
	When a coin return is requested inserted coins are sent to the coin return
	A unique selection button for each of the three products

	

Software Architecture/Use Decisions
-----------------------------------
The general idea behind the design is that several sets of machine states need to be represented.  Each set of states would
be represented uniquely in a class with each not having knowledge of the others.  Proper use then requires some high level class or 
software user to properly interact with each of these classes when state changes/actions/events occur.  

The state sets/classes are:

Display - handles changes to the state of the display
InsertedCoins - handles changes to the state of the coin insertion slot (coins inserted)
ProductManager - handles changes to the state of the machine's products (initialization and purchasing) also provides usefull information
ChangeManager - handles changes to the state of the machines change coins (making change) also determines if change can be made at and time

Detailed descriptions of each class can be found in the code comments.  The limitation to this design is that someone using these 
classes could create a vending machine that does not function correctly if they do not update each class correctly when states change.
An acceptable risk in my mind.  The benefit to this more loosely coupled design is taht is allows for more independent testing of each class.

Initially I considerd having each class interact directly with the Display class, but quickly decided against this.  You will 
see this in early commits.

An alternate design would involve each class emitting signals when state changes occur that other classes may need to know.
This would make updating the display a bit more 'automated.'  However, even with this design a high level organizer would still
be required to ensure all of the signals are properly connected between classes.  Additionally any domino effect of signals could
become more difficult to test and maintain.

*ProductManager Class contains a function that is no longer required.  However, why remove a function you made need in the future
if it works.	




Use Example - 'VendingMachine' Class File
-----------------------------------------
To best demonstrate how the code structure I've developed would function I wrote a sample use class, 'VendingMachine'.  
This file isn't meant to be evaluated or used really.  It is some code with functional comments and pseudo code thrown in.
Were a physical machine or even a sample UI put together, it would be driven the way this class is written. Given the 
stated purpose of the kata is to develop the 'brains' of a vending machine I didn't concern myself with developing a fully
executable application.
	
	
	
Potential Add-ons / Features
----------------------------
From a 'real product' perspective a few additional features came to mind that might be very nice for a customer and/or become
a product differentiator for our software were we selling to vending machine companies.

-Detailing acceptable coins from an outside file or DB
-Detailing available products from an outside file or DB
-A log of when each product was purchased. 
	Helpful for data informatics such as: Is there a busy time of day for a product? Are products often purchased in pairs? etc.

Network required activities:
-Notification when a product is low, or sold out
-Notification when change coins are low or out
-Notification if/when coin deposit tray is full



Other Notes
-----
I used a decent number more commits than I normally would to hopefully better illustrate my development process. Tests were commited
before they passed. Minor changes like comment corrections etc. were commited.  You will even see where I changed a design decision.
This is a certainly more 'raw' than I would commit at.  Usually I would wait until a working section of code and test were ready 
and perform a detailed review for these types of changes.  This being said, I did not commit at every test or function added.  
I didn't believe it too necessary to detail my work.