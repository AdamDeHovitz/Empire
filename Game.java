import java.util.*;
import java.io.*;
import cs1.Keyboard;

public class Game{ 
    //Creating every country with it's starting statistics
    private France Empire = new France();
    /*String newName,String newAdj, double newLand, int newMax, int newcount, int prest, int op, int agg,int nconflicts*/
    private static Country Austria = new Country("The Austrian Empire", "Austrian",400000,200000,125000,30,50,20,0);
    private static Country Prussia = new Country("Prussia", "Prussian",250000,300000,200000,65,30,50,0);
    private static Country Britain = new Country("Great Britain", "British",300000,230000,150000,65,10,70,0);
    private static Country Russia = new Country("The Russian Empire", "Russian",600000,300000,150000,60,50,20,0);
    private static Country Spain = new Country("Spain", "Spanish",500000,150000,75000,10,60,0,0);
    private static Country Portugal =new Country("Portugal", "Portugese",100000,100000,80000,20,30,10,0);
    private static Country Denmark = new Country("Denmark", "Danish",200000,150000,80000,50,40,20,0);
    private static Country Sweden = new Country("Sweden", "Swedish",200000,180000,90000,40,40,20,0);
    private static Country Sicily = new Country("The Kingdom of two Sicilies", "Sicilian",100000,50000,50000,20,40,0,0);
    private static Country Ottoman = new Country("The Ottoman Empire", "Ottoman",400000,200000,100000,10,50,0,0);
    private static Country[] countries = new Country[] {Austria, Prussia, Britain, Russia, Spain, Portugal, Denmark, Sweden, Sicily, Ottoman};
    private int year = 1799;
    private int month = 1;

     //////////////
    ///HELPERS+///
    /////////////
    public void printMain(){ //Prints out the main options
	String ret="\t 1: Show Country Statistics \n\t 2: Foreign Affairs \n\t 3: Domestic Affairs \n\t 4: View Current War \n\t 5: End Turn\n\t 6: End Game\n\t 7: Save Game";
	System.out.println( ret);
    }
    public void printStats(){ //Prints out each countries statistics
	String ret=Empire+"\n"+Austria+"\n"+ Prussia +"\n"+Britain +"\n"+Russia +"\n"+Spain+"\n"+ Portugal+"\n"+Denmark+"\n"+ Sweden+"\n"+Sicily+"\n"+ Ottoman;
	System.out.println( ret);
    }

    public String randomEvents(){//Chooses a random event and a random country. If the random event is one that would only effect counries that aren't France, I.E. changing their opinion of France, France gets its own event. Additionally, events are more likely to effect France.
	String ret="";
	int event=(int)(Math.random()*100);
	if (event==50){
	    ret="Your spies report a new weapon appearing on british ships. It is some sort of cannon, only instead od the typical fire, they shoot strange beams of light, capable of mass destruction. Some reported that during attacks they could hear the canons shreiking EXTERMINATE.\n Britain's troop count doubles";
	    Britain.changeTroopCount(Britain.getTroopCount()*2);}
	else{
	    int normal = (int) (Math.random()*12);
	    Country subject;
	    
	    if (normal > 9){
		normal = 10;
		subject = Empire;
	    }
	    else {
		subject = countries[normal];
	    }
	    int chance = (int) (Math.random()*7);
	    if (chance == 0){
		ret=subject.getName()+ " has experienced an unprecedented wave of volunteers!";
		subject.changeTroopCount(subject.getTroopCount()+10000);
	    }
	    else if (chance == 1){
		ret=subject.getName()+ " has lost an entire division after a strange disease went rampant!";
		subject.changeTroopCount(subject.getTroopCount()-10000);
	    }
	    else if (chance == 2){
		ret=subject.getName()+ "'s diplomats have made a fool of themselves in a foriegn court, their prestige drops";
		subject.setPrestige(subject.getPrestige()-10);
	    }
	    else if (chance == 3){
		ret=subject.getName()+ " has commissioned a great work of art, lending it great prestige";
		subject.setPrestige(subject.getPrestige()+10);
	    }
	    else if (chance == 4){
		if (normal == 10){   //France gets it's own unique event because aggressivness doesn't matter in it's case
		    ret = Empire.getName()+ " has experienced a bountiful harvest, and has earned an extra 100 gold this month";
		    Empire.changeTreasury(Empire.getTreasury() + 100);}
		else{
		    ret=subject.getName()+ " has recently become increasingly militarized and anti-French due to effective propoganda efforsts";}
		subject.changeAggressive(20);
	    }
	    else if (chance == 5){
		if (normal == 10){  //same as above
		    ret = Empire.getName()+ " has experienced a poor harvest, and has lost 50 gold this month alone";
		    Empire.changeTreasury(Empire.getTreasury() - 50);}
		else{
		    ret="The people of " +subject.getName()+ " have recently grown infatuated with peace, and are requesting demilitarization";
		    subject.changeAggressive(-20);}
	    }
	    else if (chance == 6){
		if (normal == 10){ //same as above
		    ret = Empire.getName()+ "'s manpower base has recently expanded, raising it's max troop count considerably";
		    Empire.setTroopMax(Empire.getTroopMax()+50000);
		}
		else {
		    ret=Empire.getAdj()+" diplomats have recently convinced "+subject.getName()+ " that your country means only the best";
		    subject.changeAggressive(-10);
		    subject.setOpinion(subject.getOpinion()+20);
		}
	    }
	    else if (chance == 7){
		if (normal == 10){  //same as above
		    ret = Empire.getName()+ " has experienced a surge in national popularity, granting it prestige";
		    Empire.setPrestige(Empire.getPrestige() + 20);}
		else{
		    ret=Empire.getAdj()+" diplomats have recently insulted "+subject.getName()+ " with their poor manners";
		    subject.changeAggressive(+10);
		    subject.setOpinion(subject.getOpinion()-20);
		}
	    }


	
	}
	return  ret;
    }

    
      //////////////
    /////PLAY/////
    /////////////
    public void play(){  //Plays the game, as long as this method loops the game continues.

	System.out.println("You are France in 1799. The great military general Napoleon has just staged a coup. This new leader has one goal: to conquer Europe. Use your powers of diplomacy and your military to manipulate those around you");

	boolean EndGame=false;
	while(!EndGame){
	    Object[] results= new Object[]{"A recount of what you did:\n",3};
	    System.out.println("The date is " + year+"-"+month+"-1");
	    System.out.println("You have "+Empire.getTreasury()+ " gold");
	    if (Empire.getCurrent().getWarScore() < -100){//If you're losing the war real bad you gotta give in
		
		while (Empire.getCurrent().getWarScore() < -100){
		    System.out.println("Your war score is less then -100 and you MUST make peace before continuing");
		    System.out.println("Choose a country to negogiate with:");
		    int number = 1;
		    for(Country A:Empire.getCurrent().getAxis()){
			System.out.println("\t"+number+":"+ A.getName());
			number++;}
		    System.out.println("\t"+number+":"+Empire.getCurrent().getHead().getName());
		    System.out.print("Choose wisely:");
		    int choice = Keyboard.readInt();
		    if (choice == number){
			Empire.getCurrent().options(Empire.getCurrent().getHead(),Empire);}
		    else if (choice < number && choice >0){
			Empire.getCurrent().options(Empire.getCurrent().getAxis().get(choice - 1),Empire);}
		    else{
			System.out.println("Please enter a valid number.");
		     }
		}
	    }
	    else if (Empire.getCurrent().getWarScore() > 100){ //If you're winning by a lot you have to make demands
		
		while (Empire.getCurrent().getWarScore() > 100){
		    System.out.println("Your war score is more than 100 and you must make peace with at least one nation before continuing");
		    System.out.println("Choose a country to negogiate with:");
		    int number = 1;
		    for(Country A:Empire.getCurrent().getAxis()){
			System.out.println("\t"+number+":"+ A.getName());
			number++;}
		    System.out.println("\t"+number+":"+Empire.getCurrent().getHead().getName());
		    System.out.print("Choose wisely:");
		    int choice = Keyboard.readInt();
		    if (choice == number){
			Empire.getCurrent().options(Empire.getCurrent().getHead(),Empire);}
		    else if (choice < number && choice >0){
			Empire.getCurrent().options(Empire.getCurrent().getAxis().get(choice - 1),Empire);}
		    else{
			System.out.println("Please enter a valid number.");
		     }
		}
	    }
	    else{
		while(((Integer)results[1])>0){
		    System.out.println("You have " + results[1]+ " moves left");
		    System.out.println("What do you want to do?");
		    printMain();
		    System.out.print("Choose wisely:");
		    int select=Keyboard.readInt();
		    if (select == 1)
			printStats();
		    /* results is transfered in since we need to write the results there
		       foreign() & domesticOptions() returns number of turns used*/
		    else if(select == 2)
			results = Empire.foreign(countries,(Object[])results);
		    else if (select == 3)
			results = Empire.domesticOptions(results, countries);
		    else if(select == 5){
			break;}
		    else if(select == 4){
			System.out.println(Empire.getCurrent().toString());}
		    else if(select == 6){
			EndGame=true; break;}
		    else if(select == 7){ //saving
			System.out.print("What name would you like to save this game under?");

			try{
			    String name= Keyboard.readString()+".txt";
			    BufferedWriter writer = new BufferedWriter(new FileWriter(name));
			    writer.write(Empire.getName()+","+Empire.getAdj()+","+Empire.getLand()+","+Empire.getTroopMax()+","+
					 Empire.getTroopCount()+","+Empire.getPrestige()+","+Empire.getOpinion()+","+Empire.getAggresive()+","+
					 Empire.getConflict()+","+Empire.getTreasury()+","+Empire. getMilitarySchools()+","+Empire.getLeg()+","+Empire.getEmp()+",");
			    War war=Empire.getCurrent();
			    writer.write("\n"+war.getWarScore()+","+war.getActive()+","+war.getAllies(1)+","+war.getAxis(1)+","+
					 war.getName()+","+war.getDate()+","+war.getHead().getName()+",");
			    for (Country x: countries){
				writer.write("\n"+x.getName()+","+x.getAdj()+","+x.getLand()+","+x.getTroopMax()+","+
					     x.getTroopCount()+","+x.getPrestige()+","+x.getOpinion()+","+x.getAggresive()+","+x.getConflict()+",");}
			    writer.close();
			}
			catch(IOException e){System.err.println("File not found try again");}
	       	
		    }
	
		    else{
			System.out.println("Please enter a valid number."); //wrong input check
		    }
		}
		//replenishing troops
		if (Empire.getTreasury()>0){
		    Empire.changeTroopCount(Empire.getTroopCount()+ 500 + (Empire.getMilitarySchools()*100));} 
		for (Country a: countries){
		    a.changeTroopCount(a.getTroopCount() + 750);}
	    
		String retStr=(String)results[0];
		retStr+=randomEvents()+"\n";   //random eventing
		if (Empire.getCurrent().getActive()){
		    retStr+=Empire.getCurrent().battle(Empire);  //battling
		}
		results[0]=retStr;	
		System.out.println(results[0]);  
		month++;     //incrementing the dat
		if (month>12){
		    System.out.println("\nHappy New Year\n");
		    year++;
		    month = 1;
		}
		Empire.changeTreasury(Empire.getTreasury()+ (Empire.getLand()/12000) - Empire.getMilitarySchools()); //income!
	    
		if (Empire.getCurrent().getActive()){  //incrementing the war date
		    Empire.getCurrent().incDate();}
	    }
	}
    }
    public static void saveGame(Game me){
	try{
	String name=Keyboard.readString()+".txt";
		File f=new File(name);
		Scanner S= new Scanner(f);
		ArrayList<ArrayList<Object>> data=new ArrayList<ArrayList<Object>>();
		int x=0;
		ArrayList<Object> temp;
		while(S.hasNextLine()){
		    temp=new ArrayList<Object>();
		    String n=S.nextLine();
		    while(n.indexOf(",")!=-1){
			temp.add(n.substring(0,n.indexOf(",")));
			n=n.substring(n.indexOf(",")+1);
		    }
		    data.add(temp);
		}
       
		for(int c=2;c<data.size(); c++){
		    data.get(c).set(2,Double.parseDouble((String)data.get(c).get(2)));
		    for(int y=3;y<data.get(c).size();y++)
			data.get(c).set(y,Integer.parseInt((String)data.get(c).get(y)));
		}
		
		for(int e=2;e<data.get(0).size()-2;e++){
		    if (e!=9 && e!=2)

			data.get(0).set(e,Integer.parseInt((String)data.get(0).get(e)));
		    else
			data.get(0).set(e,Double.parseDouble((String)data.get(0).get(e)));
	    

		}

		


		data.get(1).set(0,Integer.parseInt((String)data.get(1).get(0)));
		data.get(1).set(5,Integer.parseInt((String)data.get(1).get(5)));
		ArrayList<Country> _tempAllies=new  ArrayList<Country>();
		String temp2=(String)data.get(1).get(3);
		while(temp2.indexOf(":")!=-1){
		    String country=temp2.substring(0,temp2.indexOf(":"));
		    for (Country b: countries){
			if(b.getName().equals(country))
			    _tempAllies.add(b);
		    }
		    temp2=temp2.substring(temp2.indexOf(":")+1);
		}
		ArrayList<Country> _tempAxis=new  ArrayList<Country>();
		temp2=(String)data.get(1).get(4);
		while(temp2.indexOf(":")!=-1){
		    String country=temp2.substring(0,temp2.indexOf(":"));
		    for (Country a: countries){
			if(a.getName().equals(country))
			    _tempAxis.add(a);
		    }
		    temp2=temp2.substring(temp2.indexOf(":"+1));
		}
		Country tempHead=countries[0];
		temp2=(String)data.get(1).get(6);
		for (Country a: countries){
			if(a.getName().equals(temp2))
			    tempHead=a;
}

		me.Empire=new France((String)data.get(0).get(0),(String)data.get(0).get(1),((Double)data.get(0).get(2)).doubleValue(),((Integer)data.get(0).get(3)).intValue(),((Integer)data.get(0).get(4)).intValue(),((Integer)data.get(0).get(5)).intValue(),((Integer)data.get(0).get(6)).intValue(),((Integer)data.get(0).get(7)).intValue(),((Integer)data.get(0).get(8)).intValue(),((Double)data.get(0).get(9)).doubleValue(),((Integer)data.get(0).get(10)).intValue(),((String)data.get(0).get(11)).equals("true"),((String)data.get(0).get(12)).equals("true"));
 
		War newWar= new War(((Integer)data.get(1).get(0)).intValue(),((String)data.get(1).get(1)).equals("true"),_tempAllies,_tempAxis,((String)data.get(1).get(4)),((Integer)data.get(1).get(5)).intValue(),tempHead);
		me.Empire.setCurrent(newWar);
	    
		for(int w=0; w < countries.length ; w++){
		    int y=w+2;
		    countries[w]=new Country((String)data.get(y).get(0),(String)data.get(y).get(1),((Double)data.get(y).get(2)).doubleValue(),((Integer)data.get(y).get(3)).intValue(),((Integer)data.get(y).get(4)).intValue(),((Integer)data.get(y).get(5)).intValue(),((Integer)data.get(y).get(6)).intValue(),((Integer)data.get(y).get(7)).intValue(),((Integer)data.get(y).get(8)).intValue());
		}
	}
		
	catch(IOException e){System.err.println("File not found try again");}    
    }
	
    public static void main(String [] args){
	System.out.println("Welcome to Empire Builder 1799!");
	System.out.println("\t1: New Game \n\t2: Load Game");
	System.out.print("Choose Wisely:");
	int start=Keyboard.readInt();
	Game me=new Game();
	if (start==2){
	    System.out.print("Enter Game Name:");
	    saveGame(me);
	}
	    
	
				     
	    
	me.play();
	}
    
}
