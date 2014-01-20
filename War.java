import java.util.ArrayList;

public class War{
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
    }
    
    public boolean getActive(){return active;}
    public ArrayList getAllies(){return allies;} 
    public ArrayList getAxis(){return axis;} 
    public Country getHead(){return head;}
    public int getWarScore(){
	return warScore;}
    public int incDate(){
	date++;}
    public int addWarScore(int n){
	warScore +=n; return warScore - n;}
    public void setActive(boolean input, Country select){
	date = 0;
	active = input;
	head = select;
	String numb;
	addAxis(select);
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
	     allies.add(A);}
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
	
    public int options (Country select, France empire){
	boolean end = true;
	while (end){
	System.out.println("Choose an action wisely, remembering that you have a warscore value of "+ getWarScore());
	System.out.println("\t1: Negotiate with the entire alliance \n\t2: Negotiate with " +select.getName()+" \n\t3:Go back");
		int choice=Keyboard.readInt();
		if (choice = 1){
		    boolean loop = true;
		    while (loop){
			System.out.println("\t1: Make Demands \n\t2: Offer White Peace \n\t3: Offer surrender\n\t4:Go back");
			int call=Keyboard.readInt();
			if (call == 2){
			    if (warScore > 0){
				if (date <5){
				    System.out.println("The duration of this war has been too short, wait at least 5 monthes");}
			    }
			    else {
				System.out.println("Your enemies accept your call for peace and tranquility");
				this.endWar();
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
				break}}
			    System.out.println("You have this much warscore: " + this.getWarScore() - selection /1000+".\nHow much gold do you want? ( 1 warscore = 10 gold)");
			    int gold;
			    while (true){
			    selection=Keyboard.readInt();
			    if (selection > 10 * (warScore - selection/1000)){
				System.out.println("you don't have enough warscore, 1 warscore can be used for 10 gold\nHow much gold do you want?");
			    }
			    else{
				break}}
			    System.out.println("Your current deal with "+ select.getName()+" is for " + selection+ "square km of useful land and " + gold+ " gold costing you " + (selection/1000 + gold/10)+ " warscore\n 1: yes, 2: no");
			    int decision = Keyboard.readInt();
			    if (decision = 1){
				warScore -= (selection/1000 + gold/10);
				select.subLand(selection);
				empire.addLand(selection);
				empire.changeTreasury(gold);
				axis.remove(select);
				empire.setPrestige(empire.getPrestige()+2);
				select.setPrestige(select.getPrestige()-2);
				break;
			    }
			}
			if (call = 3){
			    if (warScore > 0){
				System.out.println("You are winning! You cannot surrender now and defile the " + empire.getName()+"!");
			    }
			    System.out.println("Your enemies offer you what they call \"favorable\" terms, "+ empire.getTreasury()*(warscore/100.0)+" gold as well as " + warScore*250+" square kilometers of your land\n Do you accept?\n1: yes, 2: no");
			    int accept = Keyboard.readInt();
			    if (accept = 1){
				
			    
			    

		    

		    

}
    