import java.security.spec.EncodedKeySpec;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

public class Player{

    private String playerName;
    private FarmerType farmerType;
    private double objectCoins;
    private int level;
    private int day;
    private double experienceCount;
    private double tempExpCount; 
    private int[] seedInventory;





    // Constructor




    /**
		creates a player object along with its attributes with the given player's given name as parameter. 
	 
		@param name name of the player
	 */
    public Player ()
    {
        this.playerName = "";
        this.farmerType = new FarmerType("Default Farmer");
        this.objectCoins = 10000;
        this.level = 65;
        this.day = 0;
        this.experienceCount = 0;
        this.tempExpCount = 0;
        this.seedInventory = new int[8];
    }


    // Getters

   /**
		returns a player's farmer type.

        @return player's farmer type. 
	 */
    public String getPlayerName()
    {
        return playerName;
    }



    /**
		returns a player's farmer type.

        @return player's farmer type. 
	 */
    public FarmerType getFarmerType()
    {
        return farmerType;
    }


    /**
		returns a player's current object coins. 

        @return player's current object coins. 
	 */
    public double getObjectCoins()
    {
        return objectCoins;
    }


    /**
		returns a player's current level. 

        @return player's current level. 
	 */
    public int getLevel()
    {
        return level;
    }

    /**
		returns a player's current experience count. 

        @return player's current experience count. 
	 */
    public double getExpCount()
    {
        return experienceCount;
    }

    public int[] getSeedInventory(){
        return seedInventory;
    }



    /**
		returns a player's current day count. 

        @return player's current day count. 
	 */
    public int getDay(){
        return day;
    }


    // Setters



    /**
		sets a player's name.

        @param name  player's name in string format.
	 */
    public void setPlayerName (String name){
        this.playerName = name;
    }


    /**
		sets a player's farmer type.

        @param farmerType farmer type in string format.
	 */
    public void setFarmerType (String farmerType)
    {
        this.farmerType = new FarmerType(farmerType);
    }


    /**
		sets a player's level.

        @param level player level.
	 */
    public void setLevel (int level)
    {
        this.level = level;
    }


    public void setInventory(int[] tempInventory){
        this.seedInventory = tempInventory;
    }

    /**
		adds a specific amount of experience points to the player's overall experience count. This method also levels up the player every time he accumulates a total of 100 experience points.

        @param level player level.
	 */
    public void addPlayerExperience (double expAmount)
    {
        this.experienceCount = this.experienceCount + expAmount;
        this.tempExpCount += expAmount;

        if (this.tempExpCount >= 100)
        {
            this.level += 1;
            this.tempExpCount = this.tempExpCount % 100;

            System.out.printf("\nCongratulations, you have leveled up! Your current level is " + this.level);
        }




    }

    /**
		increments a player's total days in the game by 1.

	 */
    public void incrementDay()
    {
        this.day += 1;
    }

    public void incrementObjectCoins(double objectCoins){
        this.objectCoins += objectCoins;
    }




    // Other actions


    /**
		returns the total number of seeds in the player's inventory.

        @return total seed count in a player's inventory.
	 */
    public int getTotalInventorySeedCount()
    {
        int nTemp = 0;
        int result = 0;

        while (nTemp < this.seedInventory.length)
        {
            result += this.seedInventory[nTemp];
            nTemp++;
        }

        return result;

    }




    // For reporting stats


    /**
		converts the player object's attributes and information into a string format.

        @return string representation of a player object.
	*/
    public String toString()
    {
        return playerName + " <" + farmerType.getFarmerType() + ">: Objectcoins: " + objectCoins + " | Player Level: " + level + " | Experience: " + experienceCount + " | Day: " + day;
    }








}