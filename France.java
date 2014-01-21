import java.util.ArrayList;
import cs1.Keyboard;


public class France extends Country{
    private double treasury;
    private int dissent; 
    private int militarySchoolCount;
    private double monthlyIncome;
    private int soldiersPerMonth;
    private War currentWar;
    private boolean legion;
    private boolean emperor;
    
 

    public France(){
	super("The French Republic","French", 600000, 350000,300000,80,100,100);
	/*String newName,String newAdj, double newLand, int newMax, int newcount, int prest, int op, int agg*/
	treasury=100;
	dissent=0;
	currentWar = new War();
	legion = false;
	emperor = false;
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
    public int lowerOp(int val,Country[] countries){
	for (Country a:countries){
	    a.setOpinion(a.getOpinion - val);}
	return val;
    }
	
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
	    boolean cont = true;
	    while (cont && tr > 0){
		System.out.println("What would you like to do?");
		System.out.println("\t1: Send gift (100 gold) \n\t2: Offer alliance \n\t3: Declare war\n\t4: Show country's stats \n\t5: Go back");
		System.out.print("Choose wisely:");
		int choice=Keyboard.readInt();
		if (choice==1){
		    if (this.getTreasury()>=100){
			select.setOpinion(select.getOpinion()+10);
			this.treasury-=100;
			tr-=1;
			retStr+="\n"+"You have sent "+select.getName()+" a gift. Their opinion of you has increased, but it wasn't cheap.";
			retStr+="\n"+"But hey, I guess you can buy friends.";
		    }
		    else{
			System.out.println("You don't have enough gold to do that");}
		}
		else if(choice==2){
		    if(currentWar.getAllies().contains(select)){
			System.out.println(select.getName() + " is already your ally, and is confused by your offer");
		    }
		    else if(select.getOpinion() <= 70 + (select.getAggresive() / 5)){
			retStr+= "\n"+select.getName()+ " has rejected to even consider your offer of an alliance";
			
			tr-=1;}
		    else {
			currentWar.addAlly(select);
			retStr+="\n"+select.getName()+" has accepted your offer for an alliance and wishes you prosperity";
			tr-=1;
		    }
		}
		else if (choice ==3){
		    cont = false;
		    if (currentWar.getActive()){
			currentWar.addAxis(select);
			retStr+="\n"+select.getName()+ " has joined the "+ currentWar.getName();}
		    else{
			currentWar.setActive(true, select);

			for (Country a:countries){
			    if ((a.getOpinion() < 5 + (a.getAggresive()/2) + select.getPrestige()/4 + (int)(Math.random()*20)) && (double)a.getTroopCount()/ a.getTroopMax() > .25){
				currentWar.addAxis(a);}}
			retStr += "\nYou have declared the " + currentWar.getName()+" which features the nations of "+
			    currentWar.printAllies() + "and their glorious leader France versus the damnable nations of " + currentWar.printAxis()+ "and their treacherous leader " + currentWar.getHead().getName();}
		    // Declaring war shouldn't take a turn, to allow for the character to declare war on multiple enemies
		}
		else if (choice == 4){
		    System.out.println(select);}
		else if (choice == 5){
		    cont = false;}
		
	    }
	}
	
	results[0]=retStr+"\n";
	results[1]=tr;
	return results;
    }
			
		    
	    
	
    public  Object[] domesticOptions (Object[] results, Country[] countries){
	Integer tr= (Integer)results[1];
	String retStr=(String)results[0];
	System.out.println("What would you like to do?");
	if (! legion){
	    System.out.println("\n\t1: Establish Legion of Honor \n\t Increases prestige\n(costs 75 gold)\n2:Establish a Military School:\n\t Each school increases Troop Count by an extra 1000 every month\n(costs 50 gold to create and 1 gold each month)");
	    System.out.print("Choose wisely:");
	    int choice=Keyboard.readInt();
	    if (choice==1){
		if (this.treasury < 75){
		    System.out.println("You need moar monies");}
		else{
		    retStr+="\n"+"You formed the legion of honor, increasing your prestige by 10";
		    tr-=1;
		    this.setPrestige(this.getPrestige()+10);
		    this.treasury-=75;
		    legion = true;
		}
	    }
	    if (choice==2){
		if (this.treasury < 50){
		    System.out.println("You need moar monies");}
		else{
		    retStr+="\n"+"You have established a military school.";
		    militarySchoolCount+=1;
		    this.treasury-=50;}
	    }
	}
	else if (! emperor){
	    System.out.println("\n\t1: Crown yourself emperor \n\t Increases prestige by 50, angers your neighbors, creates the empire, and raises your troop count \n(costs 300 gold)\n2:Establish a Military School:\n\t Each school increases Troop Count by an extra 1000 every month\n(costs 50 gold to create and 1 gold each month)");
	    System.out.print("Choose wisely:");
	    int choice=Keyboard.readInt();
	    if (choice==1){
		if (this.treasury <300){
		    System.out.println("You need moar monies");}
		else {
		    retStr+="\n"+"You have summoned the pope to coronate you as the one and only Emeperor of the French\n this day will live in on in glory";
		    tr-=1;
		    this.setPrestige(this.getPrestige()+50);
		    this.setTroopMax(this.getTroopMax()+100000);
		    this.treasury-=300;
		    this.setName("The Empire of the French");
		    this.lowerOp(10,countries);
		    emperor = true;
		}
		
	    }
	    if (choice==2){
		if (this.treasury < 50){
		    System.out.println("You need moar monies");}
		else{
		    retStr+=""\n"+You have established a military school.";
		    militarySchoolCount+=1;
		    this.treasury-=50;}
	    }
	}
	else {
	     System.out.println("\n\t1:Establish a Military School:\n\t Each school increases Troop Count by an extra 1000 every month\n(costs 50 gold to create and 1 gold each month)");
	    System.out.print("Choose wisely:");
	    int choice=Keyboard.readInt();
	    
	    if (choice==1){
		if (this.treasury < 50){
		    System.out.println("You need moar monies");}
		else{
		    retStr+=""\n"+You have established a military school.";
		    militarySchoolCount+=1;
		    this.treasury-=50;}
	    }






	    results[0]=retStr+"\n";
	    results[1]=tr;
	    return results;
	    
	}
    }
}