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
	
    public int options (Country select){
	boolean end = true;
	while (end){
	System.out.println("Choose an action wisely, remembering that you have a warscore value of "+ getWarScore());
	System.out.println("\t1: Negotiate with the entire alliance \n\t2: Negotiate with " +select.getName()+" \n\t3:Go back");
		int choice=Keyboard.readInt();
		if (choice = 1){
		    boolean loop = true;
		    while (loop){
			System.out.println("\t1: Make Demands \n\t2: Offer White Peace \n\t3: Offer terms of surrender\n\t4:Go back");
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
			    System.out.println("How much of your war score (total " + this.getWarScore()+") do you want to use?");
			    
			    

		    

		    

}
    