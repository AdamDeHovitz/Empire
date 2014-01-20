import java.util.ArrayList;


public class Game{
    private France Empire = new France();
    	/*String newName,String newAdj, double newLand, int newMax, int newcount, int prest, int op, int agg*/
    private Country Austria = new Country("The Austrian Empire", "Austrian",400000,200000,125000,30,50,20);
    private Country Prussia = new Country("Prussia", "Prussian",250000,300000,200000,75,30,50);
    private Country Britain = new Country("Great Britain", "British",300000,200000,100000,70,10,70);
    private Country Russia = new Country("The Russian Empire", "Russian",600000,300000,150000,60,50,20);
    private Country Spain = new Country("Spain", "Spanish",500000,150000,75000,10,60,0);
    private Country Portugal =new Country("Portugal", "Portugese",100000,100000,80000,20,30,10);
    private Country Denmark = new Country("Denmark", "Danish",200000,150000,80000,50,40,20);
    private Country Sweden = new Country("Sweden", "Swedish",200000,180000,90000,40,40,20);
    private Country Sicily = new Country("Kingdom of two Sicilies", "Sicilian",100000,50000,50000,20,40,0);
    private Country Ottoman = new Country("The Ottoman Empire", "Ottoman",400000,200000,150000,10,50,0);
    private Country[] countries={Austria, Prussia, Britian, Russia, Spain, Portugal,Denmark, Sweden, Sicily, Ottoman}; 
    private int year = 1799;
    private int month = 1;
  
   

    public static String printMain(){
	String ret="\t 1: Stat \n\t 2:Foreign Affairs \n\t 3:Domestic Affairs \n\t 4:End Turn \n\t5: View Current War\n\t 6:End Game";
	return ret;
    }
    public static String printStats(){
	String ret=empire+"/n"+Austria+"/n"+ Prussia +"/n"+Britian +"/n"+Russia +"/n"+Spain+"/n"+ Portugal+"/n"+Denmark+"/n"+ Sweden+"/n"+Sicily+"/n"+ Ottoman;
	return ret;
    }



    public Game(){
	System.out.println("You are France in 1799. The great military general Napoleon has just staged a coup. This new leader has one goal: to conquer Europe. Use your powers of diplomacy and your military to manipulate those around you");
	System.out.println("Where do you want to start?");
	boolean EndGame=false;
	while(!EndGame){
	    Object[] results={"",3};
	    while(results[1]>0){
		
		printMain();
		int select=Keyboard.readInt();
		if (select == 1)
		    printStats();
		/* results is transfered in since we need to write the results there
		   foreign() & domesticOptions() returns number of turns used*/
		else if(select == 2)
		    results =  Empire.foreign(countries,results);
		else if (select == 3)
		    results = Empire.domesticOptions(results);
		else if(select == 4){
		    break;}
		else if(select == 5){
		    System.out.println(empire.getCurrent().toString());}
		else if(select == 6){
		    EndGame=true; break;}
		else{
		    System.out.println("Please enter a valid number.");
		}
	    }
	    System.out.println(results);
	    month++;
	    if (month>12){
		year++;
		month = 1;
		}
	    System.out.println("The date is " + year+"-"+month+"-1");
	    if (empire.getCurrent().getActive){
		empire.getCurrent().incDate();}
	}
    }
}
