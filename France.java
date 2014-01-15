import java.util.ArrayList;


public class France extends Country{
    private double treasury;
    private int dissent; 
    private int militarySchoolCount;
    private double monthlyIncome;
    private int soldiersPerMonth;
    private War currentWar;
    
 

    public France(){
	super("The French Republic","French", 1230, 300000,100000,80,100);
	treasury=100;
	dissent=0;
	currentWar = new War();
    }

    public double getTreasury(){return treasury;}
    public int getDissent(){return dissent;}
    
    public double changeTreasury(double changeTres){double old=treaury;treasury=+newTres;return old;}
    public int changeDiss(int changeDis){int old=dissent;dissent-=changeDis;return old;}

    //public String surrender(){
    //	return "Germany";}
    public int foreign(Country[] countries, String results){
	int turns=0;
	while (turns < 3){
	    System.out.println("Select a country to interact with");
	    for(x=1;x<=countries.length;x++)
		System.out.println("\t"+x+": "+country[x].getName());
	    Country select=countries[Keyboard.readInt()];
	    if (currentWar.getAxis().contains(select)){
		currentWar.options(select);}
	    
	    else{
		System.out.println("What would you like to do?");
		System.out.println("\t1: Send gift \n\t2: Offer alliance \n\t3:Declare war\n\t4:Go back");
		int choice=Keyboard.readInt();
		if (choice==1){
		    select.setOpinon(select.getOpinion()+10);
		    this.treasury-=100;
		    turns+=1;
		}
		else if(choice==2){
		    if(currentWar.getAllies().contains(select)){
			System.out.println(select.getName() + " is already your ally, and is confused by your offer");
		    }
		    else if(select.getOpinion() <= 70 + (select.getAggressive() / 5)){
			results+="\n " + select.getName()+ " has rejected to even consider your offer of an alliance";
			turns+=1;}
		    else {
			currentWar.addAlly(select);
		    }
		}
		else if (choice ==3){
		    if (currentWar.getActive()){
			currentWar.addAxis(select);
			results+="\n"select.getName()+ " has joined the "+ currentWar.getName();}
		    else{
			currentWar.setActive(true, select);

			for (country a:countries){
			    if (a.getOpinion() < 30 + (a.getAggressive()/2) && (double)a.getTroopCount()/ a.getTroopMac() > .25)
				currentWar.addAxis(a);}
			results += "\n you have declared the " + currentWar.getName()+" which features the nations of "+
			    currentWar.printAllies() + "and their glorious leader France versus the damnable nations of " + currentWar.printAxis()+ "and their treacherous leader " + currentWar.getHead();}
		    // Declaring war shouldn't take a turn, to allow for the character to declare war on multiple enemies
		}
			
		    
	    
	
    public  String domesticOptions(){
	String retStr= "1: Establish Legion of Honor \n\t Increases prestige";
	return retStr;
    }
}