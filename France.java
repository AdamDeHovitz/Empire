import java.util.ArrayList;
import cs1.Keyboard;


public class France extends Country{
    private double treasury;
    private int militarySchoolCount;
    //private double monthlyIncome;
    private int soldiersPerMonth;
    private War currentWar;
    private boolean legion;
    private boolean emperor;
    private static String[] insults = {"If I stood close enough to you, I could hear the ocean", "I can hear the wails of your distressed people from here, it's shocking they haven't revolted yet", "With an army as weak as yours, it's surprise you haven't succumbed to barbarians yet", "I have always said \"Impossible is a word to be found only in the dictionary of fools.\" I'm shocked that your vocabulary contains anything more than just Impossible"}; 

 

    public France(){
	super("The French Republic","French", 600000, 450000,400000,80,100,100,0);
	/*String newName,String newAdj, double newLand, int newMax, int newcount, int prest, int op, int agg*/
	treasury=100;
	currentWar = new War();
	legion = false;
	emperor = false;
	militarySchoolCount = 0;

    }
    public France (String newName,String newAdj, double newLand, int newAt, int newDf, int prest, int op, int agg,int nconflicts,int _treasury,int schoolCount, boolean leg, boolean emp){
	super(newName,newAdj,newLand,newAt,newDf,prest,op,agg,nconflicts);
	treasury=_treasury;
	
	currentWar = new War();
	legion = leg;
	emperor = emp;
	militarySchoolCount=schoolCount;
	
    }

    public double getTreasury(){return treasury;}
    public War getCurrent(){
	return currentWar;}
    public int getMilitarySchools(){return militarySchoolCount;}
    
    public double changeTreasury(double changeTres){double old=treasury;treasury=changeTres;return changeTres;}
    //public int changeDiss(int changeDis){int old=dissent;dissent-=changeDis;return old;}

    public boolean getLeg(){return legion;}
    public boolean getEmp(){return emperor;}
    //public String surrender(){
    //	return "Germany";}
    public int lowerOp(int val,Country[] countries){
	for (Country a:countries){
	    a.setOpinion(a.getOpinion() - val);}
	return val;
    }
	
    public void setCurrent(War cur){currentWar=cur;}
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
		System.out.println("\t1: Send gift (100 gold) \n\t2: Offer alliance \n\t3: Declare war\n\t4: Show country's stats \n\t5: Send Insult\n\t6: Go back");
		System.out.print("Choose wisely:");
		int choice=Keyboard.readInt();
		if (choice==1){
		    if (this.getTreasury()>=100){
			select.setOpinion(select.getOpinion()+10);
			this.treasury-=100;
			tr-=1;
			System.out.println("\n"+"You have sent "+select.getName()+" a gift. Their opinion of you has increased by 10.");
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
			System.out.println("\n"+select.getName()+ " has rejected to even consider your offer of an alliance");
			retStr+= "\n"+select.getName()+ " has rejected to even consider your offer of an alliance";
			
			tr-=1;}
		    else {
			currentWar.addAlly(select);
			System.out.println("\n"+select.getName()+" has accepted your offer for an alliance and wishes you prosperity");
			retStr+="\n"+select.getName()+" has accepted your offer for an alliance and wishes you prosperity";
			tr-=1;
		    }
		}
		else if (choice ==3){
		    cont = false;
		    if (currentWar.getActive()){
			currentWar.addAxis(select);
			System.out.println("\n"+select.getName()+ " has joined the "+ currentWar.getName());
			retStr+="\n"+select.getName()+ " has joined the "+ currentWar.getName();}
		    else{
			currentWar.setActive(true, select);

			for (Country a:countries){
			    if ((a.getOpinion() < 5 + (a.getAggresive()/2) + select.getPrestige()/4 + (int)(Math.random()*20)) && (double)a.getTroopCount()/ a.getTroopMax() > .25){
				currentWar.addAxis(a);}}

			String ret= "";
			if(currentWar.getAllies().size() == 0){
			    ret ="\nYou have declared the " + currentWar.getName()+" which features the glorious nation of "+ name +" versus the damnable nations of " + currentWar.printAxis()+ "and their treacherous leader " + currentWar.getHead().getName();}
			else {
			    ret = "\nYou have declared the " + currentWar.getName()+" which features the nations of "+
				currentWar.printAllies() + "and their glorious leader France versus the damnable nations of " + currentWar.printAxis()+ "and their treacherous leader " + currentWar.getHead().getName();}
			System.out.println(ret);
			retStr += ret; }
		    // Declaring war shouldn't take a turn, to allow for the character to declare war on multiple enemies
		}
		else if (choice == 4){
		    System.out.println(select);}
		else if (choice == 5){
		    int insult = (int)(Math.random()*insults.length);
		    System.out.println("You send "+select.getName()+" an insult, writing \""+insults[insult]+"\"");
		    System.out.println(select.getName()+" has issued no response");
		    retStr+="\nYou have insulted "+select.getName()+", but I guess that's what you do when you're the foremost military genius in all of Europe";
		    select.setOpinion(select.getOpinion()-20);}
		else if (choice == 6){
		    cont = false;}
		else{
		     System.out.println("Please enter a valid number.");
		 }
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
	    System.out.println("\n\t1: Establish Legion of Honor \n\t Increases prestige\n\t(costs 75 gold)\n2:Establish a Military School:\n\t Each school increases Troop Count by an extra 1000 every month\n\t(costs 50 gold to create and 1 gold each month)");
	    System.out.print("Choose wisely:");
	    int choice=Keyboard.readInt();
	    if (choice==1){
		if (this.treasury < 75){
		    System.out.println("You need moar monies");}
		else{
		    System.out.println("\n"+"You formed the legion of honor, increasing your prestige by 10");
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
		    System.out.println("\n"+"You have established a military school.");
		    retStr+="\n"+"You have established a military school.";
		    militarySchoolCount+=1;
		    this.treasury-=50;
		    tr-=1;}
		
	    }
	    else{
		 System.out.println("Please enter a valid number.");
	     }
	}
	else if (! emperor){
	    System.out.println("\n\t1: Crown yourself emperor \n\t Increases prestige by 50, angers your neighbors, creates the empire, and raises your troop count \n\t(costs 300 gold)\n2:Establish a Military School:\n\t Each school increases Troop Count by an extra 1000 every month\n\t(costs 50 gold to create and 1 gold each month)");
	    System.out.print("Choose wisely:");
	    int choice=Keyboard.readInt();
	    if (choice==1){
		if (this.treasury <300){
		    System.out.println("You need moar monies");}
		else {
		    System.out.println("\n"+"You have summoned the pope to coronate you as the one and only Emeperor of the French\n this day will live in on in glory");
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
		    System.out.println("\n"+"You formed the legion of honor, increasing your prestige by 10");
		    retStr+="\n"+"You have established a military school.";
		    militarySchoolCount+=1;
		    this.treasury-=50;
		    tr-=1;}
	    }
	    else{
		 System.out.println("Please enter a valid number.");
	     }
	}
	else {
	    System.out.println("\n\t1:Establish a Military School:\n\t Each school increases Troop Count by an extra 1000 every month\n\t(costs 50 gold to create and 1 gold each month)");
	    System.out.print("Choose wisely:");
	    int choice=Keyboard.readInt();
	    
	    if (choice==1){
		if (this.treasury < 50){
		    System.out.println("You need moar monies");}
		else{
		    retStr+="\n"+"You have established a military school.";
		    militarySchoolCount+=1;
		    this.treasury-=50;
		    tr-=1;}
	    }
	    else{
		 System.out.println("Please enter a valid number.");
	     }



	}


	results[0]=retStr+"\n";
	results[1]=tr;
	return results;
	    
	
    }
    //France specific toString()
    public String toString(){
	String retStr=("\nName:"+getName()+
		       "\n\tLand:"+getLand()+
		       "\n\tTroop Count:"+getTroopCount()+
		       "\n\tPrestige:"+getPrestige()+
		       "\n\tTreasury:"+ getTreasury()+
		       "\n\tNumber of Military Schools:"+getMilitarySchools()
		       //"\n\tConflict Count:"+getConflict()
		       );
	return retStr;
    }
}