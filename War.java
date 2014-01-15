import java.util.ArrayList;

public class War{
    private Country head;
    private ArrayList<Country> allies;
    private ArrayList<Country> axis;
    private int warScore;
    private boolean active;
    private String name;
    
    public War(){
	warScore = 0;
	active = false;
    }
    
    public boolean getActive(){return active;}
    public ArrayList getAllies(){return allies;} 
    public ArrayList getAxis(){return axis;} 
    public Country getHead(){return head;}
    public int addWarScore(int n){
	warScore +=n; return warScore - n;}
    public void setActive(boolean input, Country select){
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

}
    