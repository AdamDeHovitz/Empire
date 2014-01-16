import java.util.ArrayList;


public class Game{
    private France Empire = new France();
    private Country Austria = new Country("The Austrian Empire", "Austrian",625400,100,100,100);
    private Country Prussia = new Country("Prussia", "Prussian",0,100,100,100);
    private Country Britain = new Country("Great Britain", "British",0,100,100,100);
    private Country Russia = new Country("The Russian Empire", "Russian",0,100,100,100);
    private Country Spain = new Country("Spain", "Spanish",0,100,100,100);
    private Country Portugal =new Country("Portugal", "Portugese",0,100,100,100);
    private Country Denmark = new Country("Denmark", "Danish",0,100,100,100);
    private Country Sweden = new Country("Sweden", "Swedish",0,100,100,100);
    private Country Sicily = new Country("Kingdom of two Sicilies", "Sicilian",0,100,100,100);
    private Country Ottoman = new Country("The Ottoman Empire", "Ottoman",0,100,100,100);
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
    public static String domesticAction(){return "";}



    public Game(){
	System.out.println("You are France in 1799. The great military general Napoleon has just staged a coup. This new leader has one goal: to conquer Europe. Use your powers of diplomacy and your military to manipulate those around you");
	System.out.println("Where do you want to start?");
	boolean EndGame=false;
	while(!EndGame){
	    int turns=3;
	    String results="";
	    while(turns>0){
		
		printMain();
		int select=Keyboard.readInt();
		if (select == 1)
		    printStats();
		/* results is transfered in since we need to write the results there
		   foreign() & domesticOptions() returns number of turns used*/
		else if(select == 2)
		    turns = turns - Empire.foreign(countries,results);
		else if (select == 3)
		    Empire.domesticOptions(results),;
		else if(select == 4){
		    break;}
		else if(select == 5){
		    System.out.println(empire.getCurrent().toString());}
		else if(select == 6){
		    EndGame=true; break;}
		else{
		    System.out.println("Please enter a valid number.");
		    turns+=1;
		}
		
		turns-=1;
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
