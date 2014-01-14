public class Country{
    
    private String name;
    private String adjective;
    private double landOwned;
    private int attack;
    private int troopMax;
    private int troopCount;
    private int opinionFrance;
    
    public Country(String newName,String newAdj, double newLand, int newAt, int newDf, int prest, int op){
	name = newName;
	adjective = newAdj;
	landOwned = newLand;
	attack = newAt;
	defense = newDf;
	prestige = prest;
	opinionFrance = op;
    }
    
    //ACSESSOR//
    
    public String getName(){return name;}

    public String getAdj(){return adjetive;}

    public double getLand(){return landOwned;}

    public int getTroopMax(){return troopMax;}

    public int getTroopCount(){return troopCount;}

    public int getPrestige(){return prestige;}

    public int getOpinion(){return opinionFrance;}


    //MUTATORS//

    public double addLand(double newLand){land+=newLand; return land-newLand;}
    public double subLand(double lostLand){land-=lostLand; return land + lostland; }

    public double setLand(double newLand){double old = land; land=newLand; return old;}
    public int setTroopMax(int newVal){int old = TroopMax; TroopMax=newVal; return old;}
    public int changeTroopCount(int newVal){int old = troopCount; troopCount=newVal; return old;}
    public int setPrestige(int newVal){int old = prestige; prestige= newVal; 
	if (prestige > 100){
	    prestige = 100;}
	else if( prestige > 0){prestige = 0;}
		return old};
     public int setOpinion(int newVal){int old = opinionFrance; opinionFrance= newVal; 
	if (opinionFrance > 100){
	    opinionFrance = 100;}return old;}
    
}