import java.util.ArrayList;
import cs1.Keyboard;

public class War{//Class for each war
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

    /////////////
    //ACCESSORS//
    /////////////
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

    public int getWarScore(){return warScore;}

    //////////////
    ///MUTATORS///
    /////////////

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
 
    //////////////
    ///DO STUFF//
    /////////////
    public String battle(France Empire){   //Method for simulating battles
	String retStr="";

	int axisTroops=head.getTroopCount()+head.getPrestige()*100;
	for (Country x:axis)
	    axisTroops+=x.getTroopCount()+x.getPrestige()*100;
	
	int allyTroops=Empire.getTroopCount()+Empire.getPrestige()*100;
	for (Country x:allies)
	    allyTroops+=x.getTroopCount()+x.getPrestige()*100;
	int battle=(int)(Math.random()*(allyTroops+axisTroops));
	while((battle/((allyTroops+axisTroops)*1.0)) <= (3/10.0) || (battle/(allyTroops+axisTroops*1.0)) >= (7/10.0))
	    battle=(int)(Math.random()*(allyTroops+axisTroops));
     
	warScore+= (int)(((allyTroops-battle)/(10000)));


	int TroopLossWin=0;
	while(TroopLossWin<3000 || TroopLossWin>8000)
	    TroopLossWin=(int)(Math.random()*10000);
	int battleScale=(int)(Math.random()*5)+1;
	TroopLossWin*=battleScale;
	int TroopLossLose=(10000*battleScale)-TroopLossWin;
	if (TroopLossWin>TroopLossLose){
	    int temp=TroopLossWin;
	    TroopLossWin=TroopLossLose; 
	    TroopLossLose=temp;
	}

	if (battle < allyTroops){
	    Empire.setPrestige(Empire.getPrestige()+1);
	    Empire.changeTroopCount(Empire.getTroopCount()-TroopLossWin/(allies.size()+1));
	    for(Country x: allies){
		x.setOpinion(x.getOpinion()+5);
		x.changeTroopCount(x.getTroopCount()-TroopLossWin/(allies.size()+1));}
	    head.setPrestige(head.getPrestige()-1);
	    head.changeTroopCount(head.getTroopCount()-TroopLossLose/(axis.size()+1));
	    for (Country x: axis){
		x.setPrestige(x.getPrestige()-1);
		x.changeTroopCount(x.getTroopCount()-(TroopLossLose/(axis.size()+1)));
	    }
	    
	    retStr+="You beat the enemy in a glorious battle! You lost "+TroopLossWin+" troops while your enemy lost "+ TroopLossLose +" troops. You gained "+(int)(((allyTroops-battle)/(10000))) +" war score. God is clearly with you.";
	}

	if (battle> allyTroops){

	    head.setPrestige(head.getPrestige()+1);
	    head.changeTroopCount(head.getTroopCount()-TroopLossWin/(axis.size()+1));
	    for (Country x: axis){
		x.setOpinion(x.getOpinion()+5);
		x.changeTroopCount(x.getTroopCount()-TroopLossWin/(axis.size()+1));}
	    
	    Empire.setPrestige(Empire.getPrestige()-1);
	    Empire.changeTroopCount(Empire.getTroopCount()-TroopLossWin/(allies.size()+1));
	    for (Country x: allies){
		x.setPrestige(x.getPrestige()-1);
		x.changeTroopCount(x.getTroopCount()-(TroopLossLose/(allies.size()+1)));}
	    retStr+="The enemy crushed you on the battle field. You lost "+TroopLossLose+" troops while your enemy lost "+TroopLossWin +" troops. You lost "+(int)(((allyTroops-battle)/10000)) +" war score. You should be ashamed. We are French.";
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
			    this.endWar(); //end war does everything to discontinue the conflict
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
			System.out.println("You must have positive war score to make demands!");} //limiter
		    else{
				
			System.out.println("You have this much war score: " + this.getWarScore()+".\nHow much land do you want? (They have "+ select.getLand()+" km and 1000 km = 1 war score)");
			int selection;
			while (true){  //repeats in case of error
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
			System.out.println("You are winning! You cannot surrender now and defile " + empire.getName()+"!");
		    }
		 
		    int value;
		    if (axis.size() == 0){
			
			System.out.println("Since "+ head.getName() + " is the only nation left in the opposition, they demand their own terms");  //This is important because if there is one nation left you should be forced to make a complete peace
			value = warScore * -1;
			System.out.println(head.getName()+ " offers you what they call \"favorable\" terms, "+ empire.getTreasury()*(value/100.0)+" gold as well as " + (int)value*250+" square kilometers of your land\n Do you accept?\n1: yes, 2: no");
			int accept = Keyboard.readInt();
			if (accept == 1){
			    empire.changeTreasury(-1 * (int)(empire.getTreasury()*(value/-100.0)));
			    empire.subLand(value*250);
			    select.addLand(value+250);
			    empire.setPrestige(empire.getPrestige()-2);
			    select.setPrestige(select.getPrestige()+2);
			
			    System.out.println("With no more enemy combatants, the "+ name+" has come to an end");
			    this.endWar();
			
			
			    loop = false;
			    end = false;
			    break;
			}
		    }
		    else {
			System.out.println("How many concessions worth of war score are you willing to surrender? (current war score "+warScore+")");
			while (true){
			    value = Keyboard.readInt();
			    if (value < 0)
				{System.out.println("You must give a positive value");}
			    //since war score can now go below 100 based on a decisive battle, this case was removed
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
			    select.setPrestige(select.getPrestige()+2);   //prestige adjustments for victories/defeats
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

		    
		}
		else if (call == 4){
		    end = false;
		    break;}
		else{
		    System.out.println("Please enter a valid number.");
		}
				
			    
				
	    }
		    
	}
	return -1; //We originally had options return a number in case the turn count changed, but we decided to make military moves not require turn counts because then you couldn't do everything you might need to. The return -1 is just a vestigal statement
    }
}