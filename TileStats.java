public class TileStats{

    private int nTimesTileWasWatered;
    private int nTimesTileAddedFertilizer;



	/**
		creates an object for the tile's water and fertilizer counter
	 
	 */
    public TileStats ()
    {
        this.nTimesTileWasWatered = 0;
        this.nTimesTileAddedFertilizer = 0;
    }









    // Getters



     /**
        returns number of times a tile has been watered
	 
		@return number of times a tile has been watered
	 */
    public int getCropWaterTimes ()
    {
        return nTimesTileWasWatered;
    }



    /**
        returns number of times a tile has been fertilized
	 
		@return number of times a tile has been fertilized
	 */
    public int getTotalFertilizerCount ()
    {
        return nTimesTileAddedFertilizer;
    }





    // Setters




    /**
        increments the number of times a crop was watered by 1	
	 */
    public void addCropWaterTimes()
    {
        this.nTimesTileWasWatered += 1;
    }
    /**
        increments the number of times a crop was fertilized by 1	
	 */
    public void addFertilizerTimes()
    {
        this.nTimesTileAddedFertilizer += 1;
    }

    /**
        resets the number of times a tile was watered into 0
	 */
    public void resetTileWaterTimes()
    {
        this.nTimesTileWasWatered = 0;
    }
    /**
        resets the number of times a tile was fertilized into 0
	 */
    public void resetTileFertilizerTimes()
    {
        this.nTimesTileAddedFertilizer = 0;
    }



    
}