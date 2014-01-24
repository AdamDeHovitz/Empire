Welcome to Empire Builder 1799

Introduction:

Empire builder is set in France with it’s First Consul, the recently come to power Napoleon Bonaparte. The game’s objective is effectively whatever you want it to be, but generally you want to conquer as much land as you can through skillful diplomacy and war. 



The Players:

In each game there exist the same set of 10 countries. Each country has it’s own set of values.
These values are:
name: The name of the country, stays constant except in France’s case
adjective: used primarily for naming wars but also allows us to say things like “russian agents”
land: simply the amount of valuable land. We specify valuable because Russia’s great expanses of unpopulated siberian wasteland are not to be included, and also only the European holdings of colonial nations are involved.
troopMax: a cap for any countries troop numbers, to keep things from going crazy
troopCount: The current number of troops, subject to frequent fluctuations
opiniongFrance, basically a countries opinion of you, important for making allies and also a low opinion increases probability that they will join a war against you.
prestige: Important in the fighting ability of troops, also involved in each country’s willingness to join a war. Attacking high prestige nations means more nations will get involved
aggressiveness: similarly affects how likely each country is to join a war versus France, and makes a nation less likely to be an ally
conflicts: used exclusively in the naming process for wars.



France:

France is an extension of the country class, with a few significant modifications.

New variables;
treasury: France has a treasury which affects whether or not you can send gifts, and also if you’re in debt you will not resupply with troops.
militarySchoolCount: the number of military schools you create will affect your troop replenishment and also cost monthly income
currentWar: A war class is kept at all times in the France class
legion/emperor booleans: used exclusively for domestic options



How to play (the most important part):

Upon compiling and running the Game file, you will be faced with the option to start a new game or load an old one. You have no old ones so hit new game.

Next you will be faced with the main menu which will tell you the date and how much gold you have. You will have several options in front of you, which are pretty self descriptive

foreign affairs:
The most important menu option, selecting foreign affairs will give you a prompt to choose a country and then afterwards will request that you choose an action.

	1: Send gift (100 gold)          
This will raise your targets opinion by 10
	2: Offer alliance 	
If their opinion of you is over 70 + (their aggressiveness) / 5 they will accept your alliance offer, and thus fight with you from then on.
	3: Declare war
If no war is being fought, declare war will create a new war with a new name and will also automatically add any other countries who hate you enough, basically. If there is already a war, then the country you are attacking will join it.
	4: Open espionage options 
presents you with a few options for intrigue, with a 50% chance of success or else you are caught. If you are caught you lose 10 prestige and the country that caught you loses 20 opinion of you
	5: Send Insult
for the lols, makes countries dislike you
	6: Show country's statistics
	7: Go back
both 6 and 7 are self explanatory

Domestic options:
Domestic choices are more limited, and change depending on what you’ve already done. Their helper text makes them intuitive. 

Turn mechanics. Each turn, you can make 3 moves. A move is something such as sending a gift, sabotaging, any domestic action, or offering an alliance. Declaring war and making peace does not count as a move, nor does displaying statistics.

At the end of each turn income is calculated (based on land and military schools), troops are replenished for all nations, the date increments, and all of your previous actions are displayed. Finally, one random event will also occur to a random country, changing things like troop count or prestige.



War
After a war is declared, a battle takes place at the end of every turn. In each battle each side looses men and depending on if you win or lose you can gain war score. Postive war score means you’re winning, negative means you’re losing, and 0 is neutral. 
There are two ways to end a war. The first way is to white peace. White peace can only be made if it has been at least 5 months and you have positive warscore. The other way is if your opponent is the last one. 

In most situations, the way you’ll end war is through white peace. For example, if you have 40 war score and are at war with Britain and Prussia, what you can do is demand from Britain 30 war score worth of land and gold, which they will accept in return for leaving the war. 
Now your warscore is at 10, so you can white peace the entire war assuming it’s been at least 5 months.

Alternatively, if you have negative war score then what you can do is surrender land to players to get your war score positive. Let’s say you have -40 war score, and are again fighting Britain and Prussia. Now what you do is give up 50 war score worth of land to Britain, so they will leave the war. Now you can make a white peace, ending the entire conflict.

Important to note: if the war score is greater than 100 or less than -100 you are forced to make peace.




