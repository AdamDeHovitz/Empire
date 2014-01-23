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
    public War(int _warScore,boolean _active, ArrayList _allies,ArrayList _axis,String _name,int _date){
	warScore = _warScore;
	active = _active;
	allies =  _allies;
	axis =  _axis;
	name=_name;
	date=_date;
    }

    //Getters with some variation for the purpose of saving
    public String getName(){return name;}
    public boolean getActive(){return active;}
    public String getAllies(int i){
	String ret="[";
	for(Country x: allies)
	    ret+=x.getName()+":";
	ret+="]";
	return ret;
    }
    public String getAxis(int i){
	String ret="[";
	for(Country x: axis)
	    ret+=x.getName()+":";
	ret+="]";
	return ret;
    } 
    public ArrayList<Country> getAllies(){return allies;}
    public ArrayList<Country> getAxis(){return axis;}
    public Country getHead(){return head;}
    public int getDate(){return date;}
    public int getWarScore(){
	return warScore;}

    // the date, and it's incrementation, is used to prevent a white peace within the first 5 months of a conflict
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
    public void endWar(){  //Ends the conflict
	active = false;
	axis.removeAll(axis);
	warScore = 0;
	head = null;}
    public String toString(){  //War toString() method, 
	String ret;
	if(active){
	    ret=("Name: "+name+
		 "\nAllies: "+printAllies()+
		 "\nHead of enemy coalition: "+head.getName()+
		 "\nEnemy coalition: "+printAxis()+
		 "\nWar score: "+warScore +
		 "\nDuration: "+date);}
	else
	    ret="You are currently at peace! Fix that!";
	    
	return ret;
    }
 
    public String battle(France Empire){   //Method for simulating battles
	String retStr="";

	int axisTroops=head.getTroopCount()+head.getPrestige();
	for (Country x:axis)
	    axisTroops+=x.getTroopCount()+x.getPrestige();
	
	int allyTroops=Empire.getTroopCount()+Empire.getPrestige();
	for (Country x:allies)
	    allyTroops+=x.getTroopCount()+x.getPrestige();
	int battle=(int)(Math.random()*(allyTroops+axisTroops));
	while((battle/((allyTroops+axisTroops)*1.0)) <= (2/10.0) || (battle/(allyTroops+axisTroops*1.0)) >= (7/10.0)){
	    battle=(int)(Math.random()*(allyTroops+axisTroops));
	    System.out.println(battle);}
	System.out.println(battle);
	warScore+= (int)(((allyTroops-battle)/(10000)));


	int TroopsLostAlly;
	if (allies.size()>0)
	    TroopsLostAlly= (int)((battle/100)/(allies.size()+1));
	else
	    TroopsLostAlly=(int)((battle/100)/(allies.size()+1));

	for (Country x:allies)
	    x.changeTroopCount(x.getTroopCount()-TroopsLostAlly);
	Empire.changeTroopCount((Empire.getTroopCount()-TroopsLostAlly));
	int TroopsLostAxis;


	if (axis.size()>0)
	    TroopsLostAxis=(int)((((axisTroops+allyTroops)-battle)/100)/axis.size()+1);
	else 

	    TroopsLostAxis=(int)((((axisTroops+allyTroops)-battle)/100));

	TroopsLostAxis=(int)((((axisTroops+allyTroops)-battle)/100));

	head.changeTroopCount((head.getTroopCount()-TroopsLostAxis));
	for (Country x:axis)
	    x.changeTroopCount(x.getTroopCount()-TroopsLostAxis);

	if (battle < allyTroops){
	    Empire.setPrestige(Empire.getPrestige()+1);
	    for(Country x: allies){
		x.setOpinion(x.getOpinion()+5);
		x.changeTroopCount(x.getTroopCount()+ 100);}
	    for (Country x: axis){
		x.setPrestige(x.getPrestige()-1);
		x.changeTroopCount(x.getTroopCount()+ 100);}
	    retStr+="You beat the enemy in a glorious battle! You lost "+(TroopsLostAlly*(allies.size()+1))+" troops while your enemy lost "+ (TroopsLostAxis*(axis.size()+1)) +" troops. You gained "+(int)(((allyTroops-battle)/(10000))) +" war score. God is clearly with you.";
	}

	if (battle> allyTroops){
	    Empire.setPrestige(Empire.getPrestige()-1);
	    for (Country x: axis){
		x.setPrestige(x.getPrestige()+1);
		x.changeTroopCount(Empire.getTroopCount()+ 100);
		x.setOpinion(x.getOpinion()-5);}
	    for (Country x: allies){
		x.changeTroopCount(Empire.getTroopCount()+ 100);}
	    retStr+="The enemy crushed you on the battle feild. You lost "+(TroopsLostAlly*(allies.size()+1))+" troops while your enemy lost "+ (TroopsLostAxis*(axis.size()+1)) +" troops. You lost "+(int)(((allyTroops-battle)/10000)) +" war score. You should be ashamed. We are French.";
	}
	return retStr;
    }
	    
			 
	
	
	
    public int options (Country select, France empire){
	boolean end = true;
	Empire=empire;
	while (end){
	    System.out.println("Choose an action wisely, remembering that you have a war score value of "+ getWarScore());
	    //System.out.println("\t1: Negotiate with the entire alliance \n\t2: Negotiate with " +select.getName()+" \n\t3:Go back");
	    /*	int choice=Keyboard.readInt();
		if (choice == 1){*/
	    boolean loop = true;
	    while (loop){
		System.out.println("\t1: Make Demands from " + select.getName()+" \n\t2: Offer White Peace to the Entire Coalition \n\t3: Offer Concessions for "+ select.getName()+" to Leave\n\t4: Go back");
		System.out.print("Choose wisely:");
		int call=Keyboard.readInt();
		if (call == 2){
		    if (warScore > 0){
			if (date <5){
			    System.out.println("The duration of this war has been too short, wait at least 5 monthes");}
			else{
		    
			    System.out.println("Your enemies accept your call for peace and tranquility");
			    this.endWar();
			    loop = false;
			    end = false;
			    break;}
			
		    }
		    else {
			System.out.println("Your enemies reject your offer of white peace on the grounds that they are winning!");
			    }		
		}
		else if (call == 1){
		    if (warScore < 1){
			System.out.println("You must have positive war score to make demands!");}
		    else{
				
			System.out.println("You have this much war score: " + this.getWarScore()+".\nHow much land do you want? (They have "+ select.getLand()+" km and 1000 km = 1 war score)");
			int selection;
			while (true){
			    selection=Keyboard.readInt();
			    if (selection > 1000 * warScore){
				System.out.println("you don't have enough war score, 1 war score can be used for 1000 km of useful land\nHow land do you want?");
			    }
			    else{
				break;}
			}
			System.out.println("You have this much war score: " + (this.getWarScore() - selection /1000) +".\nHow much gold do you want? ( 1 war score = 10 gold)");
			int gold=0;
			while (true){
			    gold=Keyboard.readInt();
			    if (gold > 10 * (warScore - gold/10)){
				System.out.println("you don't have enough war score, 1 war score can be used for 10 gold\nHow much gold do you want?");
			    }
			    else{
				break;}
			}
			System.out.println("Your current deal with "+ select.getName()+" is for " + selection+ " square km of useful land and " + gold+ " gold costing you " + (selection/1000 + gold/10)+ " war score\n 1: yes, 2: no");
			int decision = Keyboard.readInt();
			if (decision == 1){
			    warScore -= (selection/1000 + gold/10);
			    select.subLand(selection);
			    empire.addLand(selection);
			    empire.changeTreasury(gold);
			    System.out.println(select.getName()+" has accepted your generous offer");
			    if (select.equals(head)){
				if (axis.size() == 0){
				    System.out.println("With no more enemy combatants, the "+ name+" has come to an end");
				    this.endWar();
				    loop = false;
				    end = false;
				    break;}
				else {
				head = axis.remove((int)(Math.random()*axis.size()));
				System.out.println("The new head of the axis is " + head.getName());}
			    }
			    else {axis.remove(select);}
			    
			    empire.setPrestige(empire.getPrestige()+2);
			    select.setPrestige(select.getPrestige()-2);
			    loop = false;
			    end = false;
			    break;
			}
		    }
		}
		else if (call == 3){
		    if (warScore > 0){
			System.out.println("You are winning! You cannot surrender now and defile the " + empire.getName()+"!");
		    }
		    System.out.println("How many concessions worth of war score are you willing to surrender? (current war score "+warScore+")");
		    int value;
		    while (true){
			value = Keyboard.readInt();
			if (value < 0)
			    {System.out.println("You must give a positive value");}
			/*else if(value > 100){
			    System.out.println("You're offer is too high for the "+empire.getName()+ " to accept!");
			    }*/
			else if (value < 10){
			    System.out.println("You must offer at least 10 war score");
			}
			else{
			    
			    break;}
		    }
			    
					
		    System.out.println("Your enemy offers you what they call \"favorable\" terms, "+ empire.getTreasury()*(value/100.0)+" gold as well as " + (int)value*250+" square kilometers of your land\n Do you accept?\n1: yes, 2: no");
		    int accept = Keyboard.readInt();
		    if (accept == 1){
			empire.changeTreasury(-1 * (int)(empire.getTreasury()*(value/100.0)));
			empire.subLand(value*250);
			select.addLand(value+250);
			empire.setPrestige(empire.getPrestige()-2);
			select.setPrestige(select.getPrestige()+2);
			if (select.equals(head)){
			    if (axis.size() == 0){
				    System.out.println("With no more enemy combatants, the "+ name+" has come to an end");
				    this.endWar();
				    loop = false;
				    end = false;
				    break;}
				else {
				    head = axis.remove((int)(Math.random()*axis.size()));
				System.out.println("The new head of the axis is " + head.getName());}
			}				
			else {axis.remove(select);}
			warScore+=value;
			loop = false;
			end = false;
			System.out.println("The new war score value is "+ warScore+" and your enemies are "+ printAxis()+ " and their leader "+ head.getName());}
		    
		}
		else if (call == 4){
		    end = false;
		    break;}
		else{
		     System.out.println("Please enter a valid number.");
		 }
				
			    
				
	    }
		    
	}
	return -1;
    }
}