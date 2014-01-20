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
    public war getCurrent(){
	return currentWar;}
    
    public double changeTreasury(double changeTres){double old=treasury;treasury=changeTres;return old;}
    public int changeDiss(int changeDis){int old=dissent;dissent-=changeDis;return old;}

    //public String surrender(){
    //	return "Germany";}
    public void foreign(Country[] countries, Object[] results){
	int turns=0;
	while (turns < 3){
	    System.out.println("Select a country to interact with");
	    for(int x=1;x<=countries.length;x++)
		System.out.println("\t"+x+": "+countries[x].getName());
	    Country select=countries[Keyboard.readInt()];
	    if (currentWar.getAxis().contains(select)){
		currentWar.options(select,empire);}
	    
	    else{
		System.out.println("What would you like to do?");
		System.out.println("\t1: Send gift \n\t2: Offer alliance \n\t3:Declare war\n\t4:Go back");
		int choice=Keyboard.readInt();
		if (choice==1){
		    select.setOpinon(select.getOpinion()+10);
		    this.treasury-=100;
		    results[1]+=1;
		}
		else if(choice==2){
		    if(currentWar.getAllies().contains(select)){
			System.out.println(select.getName() + " is already your ally, and is confused by your offer");
		    }
		    else if(select.getOpinion() <= 70 + (select.getAggressive() / 5)){
			results[0]+="\n " + select.getName()+ " has rejected to even consider your offer of an alliance";
			results[1]+=1;}
		    else {
			currentWar.addAlly(select);
		    }
		}
		else if (choice ==3){
		    if (currentWar.getActive()){
			currentWar.addAxis(select);
			results[0]+="\n"+select.getName()+ " has joined the "+ currentWar.getName();}
		    else{
			currentWar.setActive(true, select);

			for (country a:countries){
			    if ((a.getOpinion() < 5 + (a.getAggressive()/2) + select.getPrestige()/4 + (int)(Math.random()*20)) && (double)a.getTroopCount()/ a.getTroopMax() > .25)
				currentWar.addAxis(a);}
			results[0] += "\n you have declared the " + currentWar.getName()+" which features the nations of "+
			    currentWar.printAllies() + "and their glorious leader France versus the damnable nations of " + currentWar.printAxis()+ "and their treacherous leader " + currentWar.getHead();}
		    // Declaring war shouldn't take a turn, to allow for the character to declare war on multiple enemies
		}
	
		else if (choice == 4){
		    break;}
	    }
	}
    }
			
		    
	    
	
    public  String domesticOptions(Country select, String result){
	String retStr= "1: Establish Legion of Honor \n\t Increases prestige";
	return retStr;
    }
}