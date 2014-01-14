public class France extends Country{
    private double treasury;
    private int dissent; 
    private int militarySchoolCount;
    private double monthlyIncome;
    private int soldiersPerMonth;
 

    public France(){
	super("The French Republic","French", 1230, 300000,100000,80,100);
	treasury=100;
	dissent=0;
    }

    public double getTreasury(){return treasury;}
    public int getDissent(){return dissent;}
    
    public double changeTreasury(double changeTres){double old=treaury;treasury=+newTres;return old;}
    public int changeDiss(int changeDis){int old=dissent;dissent-=changeDis;return old;}

    //public String surrender(){
    //	return "Germany";}
    public static String domesticOptions(){
	String retStr= "1: Establish Legion of Honor \n\t Increases prestige";
	return retStr;
    }
}