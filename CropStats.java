import java.util.*;



public class CropStats{

    private int waterReq;
    private int fertilizerReq;
    private int harvestTime;
    private int cropDuration;
    private int productsProduced;
    private int seedCost;
    private int baseSellingPrice;
    private double experienceYield;



    // Constructor





    /**
		creates an object for the crop's statistics with the given name and player's farmer type. A crop's stats depend on the farmer type of the player; hence, 
        same crops can have different stats depending on when the player registers. 
	 
		@param name name of the crop object.
        @param farmerType farmer type of the player.
	 */
    public CropStats (String name, FarmerType farmerType)
    {
        this.setWaterRequirement(name, farmerType);
        this.setFertilizerRequirement(name, farmerType);
        this.setHarvestTime(name);
        this.cropDuration = 0;
        this.setProductsProduced(name);
        this.setSeedCost(name, farmerType);
        this.setBaseSellingPrice(name, farmerType);
        this.setExperienceYield();
    }



    // Getters

    /**
        returns a crop's minimum water requirement to thrive.
	 
		@return crop's minimum water requirement.
	 */
    public int getWaterRequirement ()
    {
        return waterReq;
    }

    /**
        returns a crop's minimum fertilizer requirement to thrive.
	 
		@return crop's minimum fertilizerb requirement.
	 */
    public int getFertilizerReq ()
    {
        return fertilizerReq;
    }

    /**
        returns the number of days before a specific crop is ready for harvest.
	 
		@return crop's harvest time in days.
	 */
    public int getHarvestTime ()
    {
        return harvestTime;
    }

    /**
        returns a crop's duration in days since a crop was planted.
	 
		@return crop's duration in days.
	 */
    public int getDuration ()
    {
        return cropDuration;
    }

    /**
        returns a crop's number of product/s produced after harvest.
	 
		@return crop's number of product/s produced .
	 */
    public int getProductsProduced ()
    {
        return productsProduced;
    }

    /**
        returns a crop's seed cost.
	 
		@return crop's seed cost.
	 */
    public int getSeedCost ()
    {
        return seedCost;
    }

    /**
        returns a crop's base selling price per product.
	 
		@return crop's base selling price.
	 */
    public int getBaseSellingPrice ()
    {
        return baseSellingPrice;
    }  


    /**
        returns a crop's experience yield when harvested.
	 
		@return crop's experience yield.
	 */
    public double getExperienceYield ()
    {
        return experienceYield;
    }   


    // Setters


    
     /**
        sets a crop's number of water requirement based on the given name parameter and the player's farmer type. A crop's water requirement increases depending on the player's farmer type.
	 
		@param name name of the crop object.
        @param farmerType player's farmer type.
	 */
    public void setWaterRequirement (String name, FarmerType farmerType)
    {
        switch (name) {
            case "Turnip":
                this.waterReq = 1 + farmerType.getWaterBonusLimit();
                break;
            case "Carrot":
                this.waterReq = 1 + farmerType.getWaterBonusLimit();
                break;
            case "Potato":
                this.waterReq = 3 + farmerType.getWaterBonusLimit();
                break;
            case "Rose":
                this.waterReq = 1 + farmerType.getWaterBonusLimit();
                break;
            case "Tulips":
                this.waterReq = 2 + farmerType.getWaterBonusLimit();
                break;                
            case "Sunflower":
                this.waterReq = 2 + farmerType.getWaterBonusLimit();
                break;
            case "Mango":
                this.waterReq = 7;
                break;
            case "Apple":
                this.waterReq = 7;
                break;


            default:
                this.waterReq = 0;

        }

    }



    /**
        sets a crop's number of fertilizer requirement based on the given name parameter and the player's farmer type. A crop's fertilizer requirement increases depending on the player's farmer type.
	 
		@param name name of the crop object.
        @param farmerType player's farmer type.
	 */
    public void setFertilizerRequirement (String name, FarmerType farmerType)
    {
        switch (name) {
            case "Turnip":
                this.fertilizerReq = 0 + farmerType.getFertilizerBonusLimit();
                break;
            case "Carrot":
                this.fertilizerReq = 0 + farmerType.getFertilizerBonusLimit();
                break;
            case "Potato":
                this.fertilizerReq = 1 + farmerType.getFertilizerBonusLimit();
                break;
            case "Rose":
                this.fertilizerReq = 0 + farmerType.getFertilizerBonusLimit();
                break;
            case "Tulips":
                this.fertilizerReq = 0 + farmerType.getFertilizerBonusLimit();
                break;                
            case "Sunflower":
                this.fertilizerReq = 1 + farmerType.getFertilizerBonusLimit();
                break;
            case "Mango":
                this.fertilizerReq= 4;
                break;
            case "Apple":
                this.fertilizerReq = 5;
                break;


            default:
                this.fertilizerReq = 0;

        }

    }


    /**
        sets a crop's harvest time in days based on the given name parameter.
	 
		@param name name of the crop object.
	 */
    public void setHarvestTime (String name){

        switch (name) {
            case "Turnip":
                this.harvestTime = 2;
                break;
            case "Carrot":
                this.harvestTime = 3;
                break;
            case "Potato":
                this.harvestTime = 5;
                break;
            case "Rose":
                this.harvestTime = 1;
                break;
            case "Tulips":
                this.harvestTime = 2;
                break;                
            case "Sunflower":
                this.harvestTime = 3;
                break;
            case "Mango":
                this.harvestTime = 10;
                break;
            case "Apple":
                this.harvestTime = 10;
                break;


            default:
                this.harvestTime = 0;

        }

    }


    /**
        sets a crop's number of products produced during harvest.
	 
		@param name name of the crop object.
	 */
    public void setProductsProduced (String name)
    {

        Random randomObj = new Random();

        switch (name) {
            case "Turnip":
                this.productsProduced = randomObj.nextInt(2-1) + 1;
                break;
            case "Carrot":
                this.productsProduced = randomObj.nextInt(2-1) + 1;
                break;
            case "Potato":
                this.productsProduced = randomObj.nextInt(10-1) + 1;
                break;
            case "Rose":
                this.productsProduced = 1;
                break;
            case "Tulips":
                this.productsProduced = 1;
                break;                
            case "Sunflower":
                this.productsProduced = 1;
                break;
            case "Mango":
                this.productsProduced = randomObj.nextInt(15-5) + 5;
                break;
            case "Apple":
                this.productsProduced = randomObj.nextInt(15-10) + 10;
                break;


            default:
                this.productsProduced = 0;

        }

    }




    /**
        sets a crop's seed cost based on the given name parameter and the player's farmer type. Discounts are applied to the seed cost depending on the player's farmer type.
	 
		@param name name of the crop object.
        @param farmerType player's farmer type.
	 */
    public void setSeedCost (String name, FarmerType farmerType)
    {

        switch (name) {
            case "Turnip":
                this.seedCost = 5 - farmerType.getSeedCostReduction();
                break;
            case "Carrot":
                this.seedCost = 10 - farmerType.getSeedCostReduction();
                break;
            case "Potato":
                this.seedCost = 20 - farmerType.getSeedCostReduction();
                break;
            case "Rose":
                this.seedCost = 5 - farmerType.getSeedCostReduction();
                break;
            case "Tulips":
                this.seedCost = 10 - farmerType.getSeedCostReduction();
                break;                
            case "Sunflower":
                this.seedCost = 20 - farmerType.getSeedCostReduction();
                break;
            case "Mango":
                this.seedCost = 100 - farmerType.getSeedCostReduction();
                break;
            case "Apple":
                this.seedCost = 200 - farmerType.getSeedCostReduction();
                break;


            default:
                this.seedCost = 0;

        }

    }



    /**
        sets a crop's base selling price during harvest based on the given name parameter and the player's farmer type.
        
	 
		@param name name of the crop object.
        @param farmerType player's farmer type.
	 */
    public void setBaseSellingPrice (String name, FarmerType farmerType)
    {

        switch (name) {
            case "Turnip":
                this.baseSellingPrice = 6;
                break;
            case "Carrot":
                this.baseSellingPrice = 9;
                break;
            case "Potato":
                this.baseSellingPrice = 3;
                break;
            case "Rose":
                this.baseSellingPrice = 5;
                break;
            case "Tulips":
                this.baseSellingPrice = 9;
                break;                
            case "Sunflower":
                this.baseSellingPrice = 19;
                break;
            case "Mango":
                this.baseSellingPrice = 8;
                break;
            case "Apple":
                this.baseSellingPrice = 5;
                break;


            default:
                this.baseSellingPrice = 0;

        }

    }

    /**
        computes and sets a crop's experience yield based on a crop's harvest time.

	 */
    public void setExperienceYield (){
        this.experienceYield = this.harvestTime * 2.5;
    }
    /**
        increments a crop's duration (in days) by 1 for every day advancement of a player in the driver class.

	 */
    public void incrementCropDuration()
    {
        this.cropDuration += 1;
    }







    
}