public class War{

    private ArrayList<Country> allies;
    private ArrayList<Country> axis;
    public int warScore;
    
    public War(){
	warScore = 0;}

    public int addWarScore(int n){
	warScore +=n; return warScore - n;}

    public void addAlly(Country A){
	allies.add(a);
    }
     public void addAxis(Country A){
	axis.add(a);
    }
    public void addAlly(ArrayList A){
	for( Country c:A){
	    allies.add(c);}
    }
     public void addAxis(ArrayList A){
	 for (Country c:A){
	     axis.add(c);}
     }

}
    