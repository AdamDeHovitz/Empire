import java.util.ArrayList;
import cs1.Keyboard;

public class War{
    private France Empire;
    private Country head;
    private ArrayList<Country> allies;
    private ArrayList<Country> axis;
    private int warScore;
    private boolean active;
    private String name;
    private int date;
    
    public War(){
	warScore = 0;
	active = false;
	allies = new ArrayList<Country>();
	axis = new ArrayList<Country>();
    }
    public String getName(){return name;}
    public boolean getActive(){return active;}
    public ArrayList<Country> getAllies(){return allies;} 
    public ArrayList<Country> getAxis(){return axis;} 
    public Country getHead(){return head;}
    public int getWarScore(){
	return warScore;}
    public int incDate(){
	date++;return date-1;
    }
    public int addWarScore(int n){
	warScore +=n; 
	if (warScore > 100){
	    warScore = 100;}
	if (warScore < -100){
	    warScore = -100;}

	return warScore - n;}
    public void setActive(boolean input, Country select){
	date = 0;
	active = input;
	head = select;
	String numb;
	//addAxis(select);
	select.conflictIncrement();
	if (select.getConflict() == 1){
	    numb = "1st";}
	else if (select.getConflict() == 2){
	    numb = "2nd";}
	else if (select.getConflict() == 3){
	    numb = "3rd";}
	else {numb = select.getConflict()+"th";}
	name = numb+ " Franco-" + select.getAdj()+ " war";
	warScore = 0;


    }

    public void addAlly(Country A){
	
	allies.add(A);
    }
     public void addAxis(Country A){
	 if (! A.equals(head)){
	     axis.add(A);}
    }
    public void addAlly(ArrayList<Country> A){
	for( Country c :A){
	    allies.add(c);}
    }
     public void addAxis(ArrayList<Country> A){
	 for (Country c:A){
	     axis.add(c);}
     }
    public String printAxis(){
	String retStr = "";
	for (Country c:axis){
	    retStr += c.getName() + ", ";}
	return retStr;} 
    public String printAllies(){
	String retStr = "";
	for (Country c:allies){
	    retStr += c.getName()+ ", ";}
	return retStr;} 
    public void endWar(){
	active = false;
	axis.removeAll(axis);}
    public String toString(){
	String ret;
	if(active){
	     ret=("Name:"+name+
			"\nAllies:"+printAllies()+
			"\nHead:"+head.getName()+
			"\nAxis:"+printAxis()+
			"\nWarScore:"+warScore);}
	else
	    ret="You are currently at peace! Fix that!";
	    
	return ret;
    }
 
    public String battle(France Empire){
	String retStr="";

	int axisTroops=head.getTroopCount()*head.getPrestige();
	for (Country x:axis)
	    axisTroops+=x.getTroopCount()*x.getPrestige();
	
	int allyTroops=Empire.getTroopCount()*Empire.getPrestige();
	for (Country x:allies)
	    allyTroops+=x.getTroopCount()*x.getPrestige();

	int battle=(int)(Math.random()*(allyTroops+axisTroops));
	warScore+= (int)((allyTroops-battle-1500)/100000.0);


	int TroopsLostAlly;
	if (allies.size()>0)
	    TroopsLostAlly= (int)((battle/10000)/(allies.size()+1));
	else
	    TroopsLostAlly=(int)((battle/10000)/(allies.size()+1));

	for (Country x:allies)
	    x.changeTroopCount(x.getTroopCount()-TroopsLostAlly);
	Empire.changeTroopCount((Empire.getTroopCount()-TroopsLostAlly));
	int TroopsLostAxis;


	if (axis.size()>0)
	    TroopsLostAxis=(int)((((axisTroops+allyTroops)-battle)/10000)/axis.size()+1);
	else
	     TroopsLostAxis=(int)((((axisTroops+allyTroops)-battle)/10000));
	head.changeTroopCount((head.getTroopCount()-TroopsLostAxis));
	for (Country x:axis)
	    x.changeTroopCount(x.getTroopCount()-TroopsLostAxis);

	if (battle < allyTroops){
	    Empire.setPrestige(Empire.getPrestige()+10);
	    for(Country x: allies){
		x.setOpinion(x.getOpinion()+10);
		x.changeTroopCount(Empire.getTroopCount()+ 100);}
	    for (Country x: axis){
		x.setPrestige(x.getPrestige()-10);
		x.changeTroopCount(Empire.getTroopCount()+ 1000);}
	    retStr+="You beat the enemy in a glorious battle! You lost "+TroopsLostAlly+" troops while your enemy lost "+ TroopsLostAxis +" troops. You gained "+(int)((allyTroops-battle)/10000) +" war score. God is clearly with you.";
	}

	if (battle> allyTroops){
	    Empire.setPrestige(Empire.getPrestige()-10);
	    for (Country x: axis){
		x.setPrestige(x.getPrestige()+10);
		x.changeTroopCount(Empire.getTroopCount()+ 100);}
	     for (Country x: allies){
		x.changeTroopCount(Empire.getTroopCount()+ 1000);}
	    retStr+="The enemy crushed you on the battle feild. You lost "+TroopsLostAlly+" troops while your enemy lost "+ TroopsLostAxis +" troops. You lost "+(int)((allyTroops-battle)/10000) +" war score. You should be ashamed. We are French.";
	}
	return retStr;
    }
	    
			 
	
	
	
    public int options (Country select, France empire){
	boolean end = true;
	Empire=empire;
	while (end){
	System.out.println("Choose an action wisely, remembering that you have a warscore value of "+ getWarScore());
	//System.out.println("\t1: Negotiate with the entire alliance \n\t2: Negotiate with " +select.getName()+" \n\t3:Go back");
	/*	int choice=Keyboard.readInt();
		if (choice == 1){*/
		    boolean loop = true;
		    while (loop){
			System.out.println("\t1: Make Demands \n\t2: Offer White Peace \n\t3: Offer surrender\n\t4:Go back");
			System.out.print("Choose wisely:");
			int call=Keyboard.readInt();
			if (call == 2){
			    if (warScore > 0){
				if (date <5){
				    System.out.println("The duration of this war has been too short, wait at least 5 monthes");}
			    }
			    else {
				System.out.println("Your enemies accept your call for peace and tranquility");
				this.endWar();
				break;
			    }
			}
				
			    
			if (call == 1){
			    System.out.println("You have this much warscore: " + this.getWarScore()+".\nHow much land do you want? (They have"+ select.getLand()+" km and 1000 km = 1 warscore)");
			    int selection;
			    while (true){
			    selection=Keyboard.readInt();
			    if (selection > 1000 * warScore){
				System.out.println("you don't have enough warscore, 1 warscore can be used for 1000 km of useful land\nHow land do you want?");
			    }
			    else{
				break;}
			    }
			    System.out.println("You have this much warscore: " + (this.getWarScore() - selection /1000) +".\nHow much gold do you want? ( 1 warscore = 10 gold)");
			    int gold=0;
			    while (true){
			    gold=Keyboard.readInt();
			    if (gold > 10 * (warScore - gold/10)){
				System.out.println("you don't have enough warscore, 1 warscore can be used for 10 gold\nHow much gold do you want?");
			    }
			    else{
				break;}
			    }
			    System.out.println("Your current deal with "+ select.getName()+" is for " + selection+ "square km of useful land and " + gold+ " gold costing you " + (selection/1000 + gold/10)+ " warscore\n 1: yes, 2: no");
			    int decision = Keyboard.readInt();
			    if (decision == 1){
				warScore -= (selection/1000 + gold/10);
				select.subLand(selection);
				empire.addLand(selection);
				empire.changeTreasury(gold);
				if (select.equals(head)){
				    head = axis.remove((int)(Math.random()*axis.size()));
				    System.out.println("The new head of the axis is " + head.getName());}				
				else {axis.remove(select);}
				empire.setPrestige(empire.getPrestige()+2);
				select.setPrestige(select.getPrestige()-2);
				break;
			    }
			}
			else if (call == 3){
			    if (warScore > 0){
				System.out.println("You are winning! You cannot surrender now and defile the " + empire.getName()+"!");
			    }
			    System.out.println("How many concessions worth of warscore are you willing to me? (current warscore "+warScore);
			    int value;
			    while (true){
				value = Keyboard.readInt();
				if (value < 0)
				    {System.out.println("You must give a positive value");}
				else if(value > 100){
				    System.out.println("You're offer is too high for the "+empire.getName()+ " to accept!");
				}
				else if (value < 10){
				    System.out.println("You must offer at least 10 warscore");
				}
				else{break;}
			    }
			    
					
			    System.out.println("Your enemy offers you what they call \"favorable\" terms, "+ empire.getTreasury()*(value/100.0)+" gold as well as " + value*250+" square kilometers of your land\n Do you accept?\n1: yes, 2: no");
			    int accept = Keyboard.readInt();
			    if (accept == 1){
				empire.changeTreasury(-1 * (empire.getTreasury()*(value/100.0)));
				empire.subLand(value*250);
				select.addLand(value+250);
				empire.setPrestige(empire.getPrestige()-2);
				select.setPrestige(select.getPrestige()+2);
				if (select.equals(head)){
				    head = axis.remove((int)(Math.random()*axis.size()));
				    System.out.println("The new head of the axis is " + head.getName());}				
				else {axis.remove(select);}
				warScore+=value;
				System.out.println("The new warscore value is "+ warScore+" and your enemies are "+ printAxis()+ " and their leader "+ head.getName());}
			}
			else if (call == 4){
			    end = false;
			    break;}
				
			    
				
		    }
		    
	}
	return -1;
    }
}
