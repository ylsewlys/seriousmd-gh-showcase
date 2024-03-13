import java.util.*;

public class FarmLot{

    private ArrayList<ArrayList<Tile>> farmTiles;


    // Constructor

	/**
		creates a farm lot with 5x10 tiles (in arraylist format)
	 
	 */
    public FarmLot()
    {
        generateFarmLot();
    }

    // Getters

    /**
		returns a farm lot with 5x10 tiles

        @return 5x10 tiles of a farm lot object
	 */
    public ArrayList<ArrayList<Tile>> getFarmLot()
    {
        return farmTiles;
    }

    // Methods

    /**
		generates the tiles of a farm lot with 5 rows (5 arraylists) and 10 columns
	 
	 */
    public void generateFarmLot()
    {
        int i;


        this.farmTiles = new ArrayList<ArrayList<Tile>>();
        ArrayList<Tile> row = new ArrayList<Tile>();
        ArrayList<Tile> row2 = new ArrayList<Tile>();
        ArrayList<Tile> row3 = new ArrayList<Tile>();
        ArrayList<Tile> row4 = new ArrayList<Tile>();
        ArrayList<Tile> row5 = new ArrayList<Tile>();

        for (i = 0; i < 10; i++)
        {
            row.add(new Tile());
        }

        for (i = 0; i < 10; i++)
        {
            row2.add(new Tile());
        }

        for (i = 0; i < 10; i++)
        {
            row3.add(new Tile());
        }

        for (i = 0; i < 10; i++)
        {
            row4.add(new Tile());
        }

        for (i = 0; i < 10; i++)
        {
            row5.add(new Tile());
        }       

        this.farmTiles.add(row);
        this.farmTiles.add(row2);
        this.farmTiles.add(row3);  
        this.farmTiles.add(row4);
        this.farmTiles.add(row5);
    }


    /**
		increments the day duration of each crop in the farm lot by 1
	 
	 */
    public void incrementDaysOfGrowingCrops()
    {   
        int i, j;

        for (i = 0; i < 5; i++)
        {

            for (j = 0; j < 10; j++)
            {
                if (this.farmTiles.get(i).get(j).isCropGrowing() == true)
                {
                    this.farmTiles.get(i).get(j).getCrop().getCropStats().incrementCropDuration();
                }

            }

        }


    }

    /**
		checks whether each crop in the 5x10 farm lot is beyond the crop's harvest time with the checkTileCropWitherStatus method from Tile class.  
	 
	 */
    public void checkCropWitherStatus()
    {
        int i, j;

        for (i = 0; i < 5; i++)
        {

            for (j = 0; j < 10; j++)
            {
                if (this.farmTiles.get(i).get(j).isCropGrowing() == true)
                {
                    this.farmTiles.get(i).get(j).checkTileCropWitherStatus(i, j, this.farmTiles);
                }

            }

        }
    }




    /**
		counts all withered crops in the 5x10 farm lot  

        @return number of withered crops in the farm lot
	 */
    public int countWitheredCrops()
    {
        int i, j;
        int result = 0;

        for (i = 0; i < 5; i++)
        {

            for (j = 0; j < 10; j++)
            {
                if (this.farmTiles.get(i).get(j).isCropWithered() == true)
                {
                    result++;
                }

            }

        }

        return result;
    }

    /**
		counts all growing crops in the 5x10 farm lot  

        @return number of growing crops in the farm lot
	 */
    public int countGrowingCrops()
    {
        int i, j;
        int result = 0;

        for (i = 0; i < 5; i++)
        {

            for (j = 0; j < 10; j++)
            {
                if (this.farmTiles.get(i).get(j).isCropGrowing() == true)
                {
                    result++;
                }

            }

        }

        return result;
    }











}