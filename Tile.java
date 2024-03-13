import java.util.*;

public class Tile{

    private boolean isTilePlowed;
    private boolean isTileOccupied;
    private boolean isCropWithered;     
    private boolean isCropGrowing;
    private boolean tileHasRock; 
    private Crop tileCrop;
    private TileStats stats;


    // Constructor


    /**
        creates a tile object

	 */
    public Tile ()
    {
        this.isTilePlowed = false; 
        this.isCropWithered = false;
        this.isCropGrowing = false;
        newTileRockStatus(); // A tile has 1/4 chance to generate rocks
        this.tileCrop = null;
        this.stats = new TileStats(); // Composition
    }



    // Setters

    /**
        sets a tile's crop and its stats based on the crop name and player's farmer type

		@param name name of the crop object
        @param farmerType farmer type of the player
	 */
    public void setTileCrop (String name, FarmerType farmerType)
    {
        if (isTileAvailable() == true)
        {
            this.tileCrop = new Crop(name, farmerType);
            this.isCropGrowing = true;
        }
        else
        {
            System.out.println ("Tile is not available for crop planting!");
        }

    }


    /**
        removes a tile's crop. 

	 */
    public void removeCrop()
    {
        this.tileCrop = null;

        if (this.isCropWithered == true)
        {
            this.isTileOccupied = true;
        }
    }

    /**
        sets a tile's plow status based on the boolean parameter

        @param param boolean parameter (true or false).
	 */
    public void setPlowStatus(boolean param)
    {
        this.isTilePlowed = param;
    }
    /**
        sets a tile's occupancy status based on the boolean parameter

        @param param boolean parameter (true or false).        
	 */
    public void setTileOccupancy (boolean param)
    {
        this.isTileOccupied = param;
    }
    /**
        sets a tile's wither status based on the boolean parameter

         @param param boolean parameter (true or false).       
	 */
    public void setWitherStatus (boolean param)
    {
        this.isCropWithered = param;
    }
    /**
        sets a tile's crop growth status based on the boolean parameter

        @param param boolean parameter (true or false).        
	 */
    public void setCropGrowthStatus (boolean param)
    {
        this.isCropGrowing = param;
    }

    /**
        sets a new tile's rock presence status and occupancy status to true or false based on a 1/4 probability

	 */
    public void newTileRockStatus()
    {
        Random random = new Random();   

        int x = random.nextInt(4);

        if (x == 1) // integer can be between 0 - 3 since each integer has equal 1/4 probability
        {
            this.tileHasRock = true;
            this.isTileOccupied = true;
        }
        else
        {
            this.tileHasRock = false;
            this.isTileOccupied = false;
        }
    }
    /**
        sets a tile's rock presence status based on the boolean parameter

        @param param boolean parameter (true or false).        
	 */
    public void setTileRockStatus (boolean param)
    {
        this.tileHasRock = param;
    }

    // Getters

    /**
        determines if a tile is plowed or not
	 
		@return true if a tile is plowed; false if otherwise
	 */
    public boolean isTilePlowed()
    {
        return isTilePlowed;
    }

    /**
        determines if a tile has a withered crop or not
	 
		@return true if a tile's crop is withered; false if otherwise
	 */
    public boolean isCropWithered()
    {
        return isCropWithered;
    }
    
    /**
        determines if a tile's crop is currently growing (also indicates that a tile has a crop presence)
	 
		@return true if a tile's crop is growing; false if otherwise
	 */
    public boolean isCropGrowing()
    {
        return isCropGrowing;
    }
    
    /**
        determines if a tile has rock presence
	 
		@return true if a tile has rock presence; false if otherwise
	 */
    public boolean tileHasRock()
    {
        return tileHasRock;
    }


    /**
        determines if a tile is occupied or not. A tile is occupied if it has a crop, withered crop, fruit tree roots, or rock presence.
	 
		@return true if a tile is occupied; false if otherwise
	 */
    public boolean isTileOccupied()
    {
        return isTileOccupied;
    }

    /**
        determines if a tile is available for planting
	 
		@return true if a tile is available for planting; false if otherwise
	 */
    public boolean isTileAvailable ()
    {
        if (this.isTileOccupied == true || this.isCropWithered == true || this.tileHasRock == true || this.isTilePlowed == false || this.isCropGrowing == true)
        {
            return false;
        }
        else if (this.isTilePlowed == true && isTileOccupied == false)
        {
            return true;
        }

        return false;

    }

    /**
        returns a tile's crop object
	 
		@return tile's crop object
	 */
    public Crop getCrop ()
    {
        return tileCrop;
    }

    /**
        returns a tile's crop stats
	 
		@return tile's crop stats object
	 */
    public TileStats getTileStats ()
    {
        return stats;
    }

    // Methods



    /**
        checks if each tile's crop's duration is beyond the crop's harvest time. If the duration of a tile's crop is beyond its harvest time, the method sets the crop's wither status to true, making
        the crop withered. In a fruit tree's case, the surrounding adjacent tiles also become withered.

		@param row row coordinate of a tile. 
       	@param column column coordinate of a tile
        @param farmTiles farmer type of the player
	 */
    public void checkTileCropWitherStatus(int row, int column, ArrayList<ArrayList<Tile>> farmTiles)
    {
        if (this.isTileOccupied == true && this.isCropWithered == false)
        {
            if (this.tileCrop.getCropStats().getDuration() > this.tileCrop.getCropStats().getHarvestTime())
            {
                int i, j;


                if (this.tileCrop.getType().compareTo("Fruit Tree") == 0)
                {
                    for (i = row - 1; i <= row + 1; i++) // Sets surrounding adjacent tiles to withered
                    {
                        for (j = column - 1; j <= column + 1; j++)
                        {
                            if (!(i == row && j == column))
                            {
                                farmTiles.get(i).get(j).setWitherStatus(true);
                            }
                        }
                    }
                }

                
                setWitherStatus(true);
                removeCrop();
                this.isCropGrowing = false;



            }
        }

           
    }

    /**
        resets a tile back to its unplowed and unoccupied status.
	 
	 */
    public void resetTile()
    {
        this.isTilePlowed = false;
        this.isTileOccupied = false;
    }







    
}