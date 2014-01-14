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
    private Country[] countries{Austria, Prussia, Britian, Russia, Spain, Portugal,Denmark, Sweden, Sicily, Ottoman}; 
  
   

    public static String printMain(){
	String ret="\t 1: Stat \n\t 2:Foreign Affairs \n\t 3:Domestic Affairs \n\t 4:End Turn \n\t 5:End Game";
	return ret;
    }
    public static String printStats(){
	String ret=empire+"\n"+Austria+"\n"+England+"\n"+Piedmont+"\n"+Portugal+"\n"+Prussia+"\n"+Russia+"\n"+Spain+"\n"+Venice;
	return ret;
    }
    public static String domesticAction(){return "";}



    public Game(){
	System.out.println("You are France in 1799. The great military general Napoleon has just staged a coup. This new leader has one goal: to conquer Europe. Use your powers of diplomacy and your military to manipulate those around you");
	System.out.prinln("Where do you want to start?");
	boolean EndGame=false;
	while(!EndGame){
	    int turns=3;
	    while(turns>0){
		String results="";
		printMain();
		int select=Keyboard.readInt();
		if (select==1)
		    printStats();
		else if(select==2)
		    France.foreign(countries);
		else if (select==3)
		    France.domesticOptions();
		else if(select=5)
		    EndGame=true;
		turns-=1
		    }
		}
    }
}
