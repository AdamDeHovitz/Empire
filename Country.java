public class Country{
    
    private String name;
    private String adjective;
    private double landOwned;
    private int attack;
    private int defense;
    private int prestige;
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

    public int getAttack(){return attack;}

    public int getDefense(){return defense;}

    public int getPrestige(){return prestige;}

    public int getOpinion(){return opinionFrance;}


    //MUTATORS//

    public double addLand(double newLand){land+=newLand; return land-newLand;}
    public double subLand(double lostLand){land-=lostLand; return land + lostland; }

    public double setLand(double newLand){double old = land; land=newLand; return old;}
    public int setAttack(int newVal){int old = attack; attack=newVal; return old;}
    public int setDefense(int newVal){int old = defense; defense=newVal; return old;}
    public int setPrestige(int newVal){int old = prestige; prestige= newVal; 
	if (prestige > 100){
	    prestige = 100;}return old;}
     public int setOpinion(int newVal){int old = opinionFrance; opinionFrance= newVal; 
	if (opinionFrance > 100){
	    opinionFrance = 100;}return old;}
    
}