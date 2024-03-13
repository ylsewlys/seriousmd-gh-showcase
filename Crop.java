import java.util.*;


public class Crop{
    private int cropId;
    private String name;
    private String type;
    private CropStats stats;





    
	/**
		creates a crop object with the given name and player's farmer type. A crop's stats depend on the farmer type of the player; hence, 
        same crops can have different stats depending on when the player registers. 
	 
		@param name name of the crop object.
        @param farmerType farmer type of the player.
	 */
    public Crop (String name, FarmerType farmerType)
    {
        setCropId(name);
        this.name = name;
        setType(name);
        this.stats = new CropStats(name, farmerType);
 
    }


    // Getters


    /**
        returns a crop's unique id (indicates what type of crop a crop object is. 0 - Turnip, 1 - Carrot, etc...).
	 
		@return crop ID of a crop object.
	 */
    public int getCropId()
    {
        return cropId;
    }

    /**
        returns a crop's name (e.g. Turnip, Potato, etc.).
	 
		@return name of the crop object.
	 */
    public String getName()
    {
        return name;
    }


    /**
        returns a crop's type (e.g. Root crop, Flower, Fruit Tree).
	 
		@return crop's type.
	 */
    public String getType()
    {
        return type;
    }



     /**
        returns a crop's stats.
	 
		@return stats of this crop object.
	 */
    public CropStats getCropStats()
    {
        return stats;
    }


    // Setters




     /**
        sets the crop ID of a crop object based on the name parameter.
	 
		@param name name of the crop object.
	 */
    public void setCropId(String name)
    {
        switch (name) {
            case "Turnip":
                this.cropId = 0;
                break;
            case "Carrot":
                this.cropId = 1;
                break;
            case "Potato":
                this.cropId = 2;
                break;
            case "Rose":
                this.cropId = 3;
                break;
            case "Tulips":
                this.cropId = 4;
                break;                
            case "Sunflower":
                this.cropId = 5;
                break;
            case "Mango":
                this.cropId = 6;
                break;
            case "Apple":
                this.cropId = 7;
                break;


            default:
                this.cropId = -1;
        }
    }


     /**
        sets the crop's name given a name parameter.
	 
		@param name name of the crop object.
	 */
    public void setName (String name)
    {
        this.name = name;
    }

     /**
        sets the crop's type based on its name.
	 
		@param name name of the crop object.
	 */
    public void setType (String name)
    {
        switch (name) {
            case "Turnip":
                this.type = "Root crop";
                break;
            case "Carrot":
                this.type = "Root crop";
                break;
            case "Potato":
                this.type = "Root crop";
                break;
            case "Rose":
                this.type = "Flower";
                break;
            case "Tulips":
                this.type = "Flower";
                break;                
            case "Sunflower":
                this.type = "Flower";
                break;
            case "Mango":
                this.type = "Fruit Tree";
                break;
            case "Apple":
                this.type = "Fruit Tree";
                break;
            case "Withered":
                this.type = "Withered";
                break;


            default:
                this.type = null;
    }



    }

    // Methods

     /**
        determines if a crop is ready for harvest.
	 
		@param tile specific tile of a farm lot containing different possible objects such as a crop, rocks, etc.
        @return true if crop is ready for harvest, false if otherwise.
	 */
    public boolean isCropReadyForHarvest(Tile tile)
    {
        if ((this.stats.getDuration() == this.stats.getHarvestTime()) && (tile.getTileStats().getCropWaterTimes() >= this.stats.getWaterRequirement()) && (tile.getTileStats().getTotalFertilizerCount() >= this.stats.getFertilizerReq()))
        {
            return true;
        }
        return false;
    }



     /**
        determines if a tile is available for tree planting. 
	 
		@param farmLot the whole farmLot object containing 5x10 tiles.
		@param row row coordinate of a farm lot's tile.
		@param column column coordinate of a farm lot's tile.
        @return 0 if tile is available for tree planting; 1 if tile is at the farm lot's borders, 2 if the adjacent tiles of a tile has objects.
	 */
    public int isTreeAvailForPlanting (FarmLot farmLot, int row, int column)
    {

            if (row == 0 || row == 4 || column == 0 || column == 9)
            {
                return 1;
            }
            else
            {
                int i, j;
                int result = 0;

                for (i  = row - 1; i <= row + 1; i++)
                {
                    for (j = column - 1; j <= column + 1; j++)
                    {
                        if (farmLot.getFarmLot().get(i).get(j).isTileOccupied() == true)
                        {
                            result++;
                        }
                    }

                }
                
                if (result > 0)
                {
                    return 2;
                }
                else
                {
                    return 0;
                }
            }


    }


}







    
   