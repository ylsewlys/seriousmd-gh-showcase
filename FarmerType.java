public class FarmerType{

    private String farmerType;
    private int bonusEarnings;
    private int seedCostReduction;
    private int waterBonusLimit; 
    private int fertilizerBonusLimit;

    // Constructor

	/**
		creates a farmer type object given a farmer type string parameter. The privileges of each farmer type depend on the classification of the farmer type (farmerType string parameter). 
	 
        @param farmerType farmer type of the player
	 */
    public FarmerType(String farmerType)
    {
        this.farmerType = farmerType;
        setBonusEarningsProduce(farmerType);
        setSeedCostReduction(farmerType);
        setWaterBonusLimit(farmerType);
        setFertilizerBonusLimit(farmerType);
    }






    // Getters


    /**
		returns the player's farmer type clasification in string format
	 
        @return player's farmer type
	*/
    public String getFarmerType()
    {
        return farmerType;
    }


    /**
		returns the player's bonus earnings per product 
	 
        @return player's bonus earnings per product
	*/ 
    public int getBonusEarnings()
    {
        return bonusEarnings;
    }

    /**
		returns the player's seed cost reduction when purchasing seeds from the seed shop
	 
        @return player's seed cost reduction
	*/
    public int getSeedCostReduction()
    {
        return seedCostReduction;
    }

    /**
		returns the player's water bonus limit when watering specific crops
	 
        @return player's water bonus limit
	*/
    public int getWaterBonusLimit()
    {
        return waterBonusLimit;
    }

    /**
		returns the player's fertilizer bonus limit when fertilizing specific crops
	 
        @return player's fertilizer bonus limit
	*/
    public int getFertilizerBonusLimit()
    {
        return fertilizerBonusLimit;
    }


    // Setters



    /**
		sets the farmer type of a player based on the string farmer type parameter
	 
        @param farmerType player's farmer type
	*/
    public void setFarmerType(String farmerType)
    {
        this.farmerType = farmerType;
    }

    /**
		sets the bonus earnings of a player per product based on his farmer type in string format parameter
	 
        @param farmerType player's farmer type
	*/
    public void setBonusEarningsProduce(String farmerType)
    {
        switch (farmerType){
            case "Default Farmer":
                this.bonusEarnings = 0;
                break;
            case "Registered Farmer":
                this.bonusEarnings = 1;
                break;
            case "Distinguished Farmer":
                this.bonusEarnings = 2;
                break;
            case "Legendary Farmer":
                this.bonusEarnings = 4;
                break;

            default:
                this.bonusEarnings = 0;

        }


    }


    /**
		sets the seed cost reduction of a player based on his farmer type in string format parameter
	 
        @param farmerType player's farmer type
	*/
    public void setSeedCostReduction(String farmerType)
    {
        switch (farmerType){
            case "Default Farmer":
                this.seedCostReduction = 0;
                break;
            case "Registered Farmer":
                this.seedCostReduction = 1;
                break;
            case "Distinguished Farmer":
                this.seedCostReduction = 2;
                break;
            case "Legendary Farmer":
                this.seedCostReduction = 3;
                break;

            default:
                this.seedCostReduction = 0;

        }
    }

    /**
		sets the water bonus limit of a player's crop based on his farmer type in string format parameter
	 
        @param farmerType player's farmer type
	*/
    public void setWaterBonusLimit(String farmerType)
    {
        switch (farmerType){
            case "Default Farmer":
                this.waterBonusLimit = 0;
                break;
            case "Registered Farmer":
                this.waterBonusLimit = 0;
                break;
            case "Distinguished Farmer":
                this.waterBonusLimit = 1;
                break;
            case "Legendary Farmer":
                this.waterBonusLimit = 1;
                break;

            default:
                this.waterBonusLimit = 0;

        }
    }

    /**
		sets the fertilizer bonus limit of a player's crop based on his farmer type in string format parameter

        @param farmerType player's farmer type
	*/
    public void setFertilizerBonusLimit(String farmerType)
    {
        if (farmerType.compareTo("Legendary Farmer") == 0)
        {
            this.fertilizerBonusLimit = 1;
        }
        else
        {
            this.fertilizerBonusLimit = 0;
        }

    }



















    
}