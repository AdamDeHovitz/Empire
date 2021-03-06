import java.util.ArrayList;

public class Country{
    
    protected String name;
    protected String adjective;  //Used primarily for the naming of wars
    protected double land;
    protected int troopMax;
    protected int troopCount;
    protected int opinionFrance;
    protected int prestige;
    protected int aggressiveness; 
    protected int conflicts;
    
    
    public Country(String newName,String newAdj, double newLand, int newAt, int newDf, int prest, int op, int agg,int nconflicts){
	name = newName;
	adjective = newAdj;
	land = newLand;
	troopMax = newAt;
	troopCount = newDf;
	prestige = prest;
	opinionFrance = op;
	aggressiveness = agg;
	conflicts = nconflicts;
	  
    }
    
     /////////////
    //ACCESSORS//
    /////////////
    
    public String getName(){return name;}

    public String getAdj(){return adjective;}

    public double getLand(){return land;}

    public int getTroopMax(){return troopMax;}

    public int getTroopCount(){return troopCount;}

    public int getPrestige(){return prestige;}
   

    public int getOpinion(){return opinionFrance;}
    public int getAggresive(){return aggressiveness;}
    public int getConflict(){return conflicts;};
    

    //////////////
    ///MUTATORS///
    /////////////

    public double addLand(double newLand){land+=newLand; return land-newLand;}
    public double subLand(double lostLand){land-=lostLand; return land + lostLand; }

    public double setLand(double newLand){double old = land; land=newLand; return old;}
    public int setTroopMax(int newVal){int old = troopMax; troopMax=newVal; return old;}

    //Mutators with caps
    public int changeTroopCount(int newVal){int old = troopCount; troopCount=newVal; 
	if (troopCount > troopMax){
	    troopCount = troopMax;}
	else if (troopCount < 0){
	    troopCount = 0;}
    
	return old;}
    public void setName(String newVal){
	name = newVal;}
    public int setPrestige(int newVal){int old = prestige; prestige= newVal; 
	if (prestige > 100){
	    prestige = 100;}
	else if( prestige < 0){prestige = 0;}
	return old;}
    public int setOpinion(int newVal){int old = opinionFrance; opinionFrance= newVal; 
	if (opinionFrance > 100){
	    opinionFrance = 100;}
	else if(opinionFrance < -50){opinionFrance = -50;}
	return old;}
    
    public int changeAggressive(int add){int old = aggressiveness; aggressiveness += add; 
	if (aggressiveness > 100){
	    aggressiveness = 100;}
	if (aggressiveness < 0){
	    aggressiveness = 0;}
	return old;}

    //Used in the naming of wars
    public void conflictIncrement(){conflicts+=1;}
    

    //Default Country toString()
    public String toString(){
	String retStr=("\nName: "+getName()+
		       "\n\tLand: "+getLand()+
		       "\n\tTroop Count: "+getTroopCount()+
		       "\n\tPrestige: "+getPrestige()+
		       "\n\tOpinion of France: "+ getOpinion()+
		       "\n\tAgressiveness: "+getAggresive()
		       //"\n\tConflict Count:"+getConflict()
		       );
	return retStr;
    }

}