import java.util.ArrayList;
import cs1.Keyboard;


public class France extends Country{
    private double treasury;
    private int dissent; 
    private int militarySchoolCount;
    private double monthlyIncome;
    private int soldiersPerMonth;
    private War currentWar;
    
 

    public France(){
	super("The French Republic","French", 600000, 300000,200000,80,100,100);
	/*String newName,String newAdj, double newLand, int newMax, int newcount, int prest, int op, int agg*/
	treasury=100;
	dissent=0;
	currentWar = new War();
    }

    public double getTreasury(){return treasury;}
    public int getDissent(){return dissent;}
    public War getCurrent(){
	return currentWar;}
    public int getMilitarySchools(){return militarySchoolCount;}
    public double changeTreasury(double changeTres){double old=treasury;treasury=changeTres;return changeTres;}
    public int changeDiss(int changeDis){int old=dissent;dissent-=changeDis;return old;}

    //public String surrender(){
    //	return "Germany";}
    public Object[] foreign(Country[]countries,Object[]results){
	Integer tr= (Integer)results[1];
	String retStr=(String)results[0];
	    System.out.println("Select a country to interact with");
	    for(int x=1;x<=countries.length;x++)
		System.out.println("\t"+x+": "+countries[x-1].getName());
	    System.out.print("Choose wisely:");
	    Country select=countries[Keyboard.readInt()-1];
	    if (currentWar.getActive() && (currentWar.getAxis().contains(select) || currentWar.getHead().equals(select))){
		currentWar.options(select,this);}
	    
	    else{
		System.out.println("What would you like to do?");
		System.out.println("\t1: Send gift \n\t2: Offer alliance \n\t3:Declare war\n\t4:Go back");
		System.out.print("Choose wisely:");
		int choice=Keyboard.readInt();
		if (choice==1){
		    select.setOpinion(select.getOpinion()+10);
		    this.treasury-=100;
		    tr-=1;
		    retStr+="You have sent"+select.getName()+"a gift. Their oppinion of you has increased, but it wasn't cheap.";
		    retStr+="But hey, I guess you can buy friends.";
		    
		}
		else if(choice==2){
		    if(currentWar.getAllies().contains(select)){
			System.out.println(select.getName() + " is already your ally, and is confused by your offer");
		    }
		    else if(select.getOpinion() <= 70 + (select.getAggresive() / 5)){
			retStr+=select.getName()+ " has rejected to even consider your offer of an alliance";
			tr-=1;}
		    else {
			currentWar.addAlly(select);
			retStr+=select.getName()+"has accepted your offer for an alliance and wishes you prosperity";
		    }
		}
		else if (choice ==3){
		    if (currentWar.getActive()){
			currentWar.addAxis(select);
			retStr+="\n"+select.getName()+ " has joined the "+ currentWar.getName();}
		    else{
			currentWar.setActive(true, select);

			for (Country a:countries){
			    if ((a.getOpinion() < 5 + (a.getAggresive()/2) + select.getPrestige()/4 + (int)(Math.random()*20)) && (double)a.getTroopCount()/ a.getTroopMax() > .25){
				currentWar.addAxis(a);}}
			retStr += "\n You have declared the " + currentWar.getName()+" which features the nations of "+
			    currentWar.printAllies() + "and their glorious leader France versus the damnable nations of " + currentWar.printAxis()+ "and their treacherous leader " + currentWar.getHead().getName();}
		    // Declaring war shouldn't take a turn, to allow for the character to declare war on multiple enemies
		}
	
 
	    }
	
	results[0]=retStr+"\n";
	results[1]=tr;
	return results;
    }
			
		    
	    
	
    public  Object[] domesticOptions (Object[] results){
	Integer tr= (Integer)results[1];
	String retStr=(String)results[0];
	System.out.println("What would you like to do?");
	System.out.println("1: Establish Legion of Honor \n\t Increases prestige\n2:Establish a Military School:\n\t Each school increases Troop Count by an extra 1000 every month");
	System.out.print("Choose wisely:");
	int choice=Keyboard.readInt();
	if (choice==1){
	    retStr+="You formed a legion of honor, increasing your prestige by 10";
	    tr-=1;
	    this.setPrestige(this.getPrestige()+10);
	}
	if (choice==2){
	    retStr+="You have established military schools.";
	    militarySchoolCount+=1;
	}
	results[0]=retStr+"\n";
	results[1]=tr;
	return results;
    }
}