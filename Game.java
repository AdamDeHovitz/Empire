public class Game{
    private France empire=new France();
    private Country Austria=new Country("Austrian Empire", "Austrian",625400,100,100,100);
    private Country Prussia=new Country("Prussia", "Prussian",0,100,100,100);
    private Country England=new Country("England", "English",0,100,100,100);
    private Country Russia=new Country("Russia", "Russian",0,100,100,100);
    private Country Spain=new Country("Spain", "Spanish",0,100,100,100);
    private Country Portugal=new Country("Portugal", "Portugese",0,100,100,100);
    private Country Venice=new Country("Venice", "Venician ",0,100,100,100);
    private Country Peidmont=new Country("Peidmont", "Peidmontese",0,100,100,100);
    private Country Sicilies=new Country("Sicilies", "Sicilian",0,100,100,100);
   

    public static String printMain(){
	String ret="\t 1: Stat \n\t 2:Foreign Affairs \n\t 3:Domestic Affairs \n\t 4:End Turn \n\t 5:End Game";
	return ret;
    }
    public static String printStats(){
	String ret=empire+"\n"+Austria+"\n"+England+"\n"+Piedmont+"\n"+Portugal+"\n"+Prussia+"\n"+Russia+"\n"+Spain+"\n"+Venice;
	return ret;
    }



    public Game(){
	System.out.println("You are France in 1799. The great military general Napoleon has just staged a coup. This new leader has one goal: to conquer Europe. Use your powers of diplomacy and your military to manipulate those around you");
	System.out.prinln("Where do you want to start?");
	boolean EndGame=false;
	while(!EndGame){
	    String results="";
	    printMain();
	    int select=Keyboard.readInt();
	    if (select==1)
		printStats();
	    else if(select==2)
		
		}
    }
}
