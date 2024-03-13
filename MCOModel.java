public class MCOModel {



    private Player player;
    private FarmLot farmLot;



    
    public MCOModel(){
        this.player = new Player();
        this.farmLot = new FarmLot();
    }



    public Player getPlayer(){
        return player;
    }

    public FarmLot getFarmLot(){
        return farmLot;
    }

    public void setNewFarmLot(){
        this.farmLot = new FarmLot();
    }

    public void setDefaultPlayer(){
        this.player = new Player();
    }

}
