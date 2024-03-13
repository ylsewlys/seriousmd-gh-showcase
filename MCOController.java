import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MCOController{

    private MCOView view;
    private MCOModel model;

    private String chosenItem;
    private String chosenCrop = "Turnip";
    private int cropIndex;


    public MCOController(MCOView view, MCOModel model){
        this.view = view;
        this.model = model;

        this.view.setButtonListener(0, new ActionHandler()); // Start Button
        this.view.setButtonListener(1, new ActionHandler()); // Shop Button
        this.view.setButtonListener(2, new ActionHandler()); // Exit Shop Button

        for (int i = 3; i < 11; i++){
            this.view.setButtonListener(i, new ActionHandler());
        }


        
        this.view.setButtonListener(11, new ActionHandler()); // Buy Crop Button
        this.view.setButtonListener(12, new ActionHandler()); // Go Register Button
        this.view.setButtonListener(13, new ActionHandler()); // Exit Register UI

        // Register Buttons
        this.view.setButtonListener(14, new ActionHandler()); // Registered Farmer Button
        this.view.setButtonListener(15, new ActionHandler()); // Legendary Farmer Button
        this.view.setButtonListener(16, new ActionHandler()); // Distinguished Farmer Button

        this.view.setButtonListener(17, new ActionHandler()); // Advance Day Button


        for (int i = 18; i < 26; i++){
            this.view.setButtonListener(i, new ActionHandler());        // Main Screen Inventory Buttons
        }

        this.view.setButtonListener(26, new ActionHandler());

        
        for (int i = 0; i < 5; i++){

            for (int j = 0; j < 10; j++){
                this.view.setupPopActionHandlerListener(new ActionHandler(), i, j, 7);
            }
        }
        
    }


    public class ActionHandler implements ActionListener{

     
        public void actionPerformed(ActionEvent e){

            String playerChoice = e.getActionCommand();
            Crop tempCrop;
            int row = view.getRow();
            int column = view.getColumn();


            switch(playerChoice){



                case "startGame": startGame();
                                    break;
                case "goShop":    showShop();
                                    break;
                case "exitShop":  exitShop();
                                    break;     
                                    
                case "goRegister": showRegisterUI();
                                    break;    
                case "exitRegisterUI": exitRegisterUI();
                                    break;     
                                    
                case "advanceDay": checkEndGameConditions(model.getFarmLot()); 
                                   model.getFarmLot().incrementDaysOfGrowingCrops();
                                   model.getPlayer().incrementDay();
                                   view.setMainTextArea("You are now at day " + model.getPlayer().getDay() + "!");

                                   for (int i = 0; i < 5; i++){
                                        for (int j = 0; j < 10; j++){
                                            checkTileCropWitherStatus(i, j, model.getFarmLot().getFarmLot());
                                        }
                                   }

                                   checkCropsReadyToHarvest();


                                     break;                                        
                // DISPLAY CROP INFO

                case "displayTurnip": displayTurnip();
                                      chosenItem = "Turnip";
                                      cropIndex = 0;
                                    break;
                case "displayPotato": displayPotato();
                                      chosenItem = "Potato";     
                                      cropIndex = 2;           
                                    break;
                case "displayTulips": displayTulips();
                                      chosenItem = "Tulips"; 
                                      cropIndex = 4;                 
                                    break;    
                case "displayMango": displayMango();
                                      chosenItem = "Mango";    
                                      cropIndex = 6;              
                                    break;
                case "displayCarrot": displayCarrot();
                                      chosenItem = "Carrot";    
                                      cropIndex = 1;              
                                    break;
                case "displayRose": displayRose();
                                      chosenItem = "Rose";     
                                      cropIndex = 3;             
                                    break;    
                case "displaySunflower": displaySunflower();
                                      chosenItem = "Sunflower";   
                                      cropIndex = 5;               
                                    break;
                case "displayApple": displayApple();
                                      chosenItem = "Apple";
                                      cropIndex = 7;                 
                                    break;  

                // BUY CROP
                
                case "buyCrop": buyCrop(chosenItem, model.getPlayer().getFarmerType());
                break;  

                
                 // REGISTER FARMER
                case "registerRF": registerFarmer(playerChoice, model.getPlayer());
                break;  
                case "registerLF": registerFarmer(playerChoice, model.getPlayer());
                break;  
                case "registerDF": registerFarmer(playerChoice, model.getPlayer());
                break;     
                
                
                // PLAYER OPTIONS
                case "plowTile": plowTile(row, column, model.getFarmLot().getFarmLot().get(row).get(column));
                                break; 
                case "plantCrop": plantCrop(model.getFarmLot().getFarmLot().get(row).get(column), tempCrop = new Crop(chosenCrop, model.getPlayer().getFarmerType()), model.getFarmLot(), row, column);
                                break; 
                case "water": waterTile(row, column, model.getFarmLot().getFarmLot().get(row).get(column));
                                break; 
                case "fertilize": fertilizeTile(row, column, model.getFarmLot().getFarmLot().get(row).get(column));
                                break; 
                case "harvestCrop": harvestCrop(model.getFarmLot().getFarmLot().get(row).get(column), model.getFarmLot(), row, column);
                                break; 
                case "useShovel": useShovel(model.getFarmLot(), row, column);
                                break; 
                case "usePickaxe": usePickaxe(model.getFarmLot().getFarmLot().get(row).get(column), row, column);
                                break; 


                // MAIN SCREEN INVENTORY ACTIONS
                case "chooseTurnip": chosenCrop = "Turnip";
                                     break;
                case "chooseCarrot": chosenCrop = "Carrot";
                                     break;
                case "choosePotato": chosenCrop = "Potato";
                                     break;
                case "chooseRose": chosenCrop = "Rose";
                                     break;
                case "chooseTulips": chosenCrop = "Tulips";
                                     break;
                 case "chooseSunflower": chosenCrop = "Sunflower";
                                     break;               
                case "chooseMango": chosenCrop = "Mango";
                                     break;
                case "chooseApple": chosenCrop = "Apple";
                                     break;


                // GAME OVER UI

                case "restartGame": view.showMainMenu();
                                     break;                


            }

            updatePlayerStatsInMain();
        }

    }


    public void startGame(){
        try {
            if (view.getTfText(0).length() > 15){
                throw new InvalidUsernameException("Please enter a username less than 16 characters");
            }
            else if (view.getTfText(0).length() < 1){
                throw new InvalidUsernameException("Please enter a username.");
            }
            else{



                setNewGame();
                this.model.getPlayer().setPlayerName(view.getTfText(0));
                setStartingGameLabels();
                view.showMainGameScreen();
                updateFarmLotStatus();
                updatePlayerStatsInMain();
            }
        }
        catch (InvalidUsernameException e){
            view.displayErrorMessage(e.getMessage());
        }


    }



    public void visibilityItemInfoUI(boolean param){
        view.setTextLabelVisibility(7, param);
        view.setTextLabelVisibility(8, param);
        view.setButtonVisibility(11, param);
        view.setTextFieldVisibility(1, param);
        view.setItemInfoTextAreaVisiblity(param);
        view.setImageLabelVisibility(4, param);
        view.setImageLabelVisibility(5, param);
    }

    // BUY CROP

    public void buyCrop(String cropName, FarmerType farmerType){
        int nQuantity = 0;
        int price;
        CropStats tempStats = new CropStats(cropName, farmerType);
        int[] tempInventoryArray = model.getPlayer().getSeedInventory();



        try{
            nQuantity = view.getQuantity(1);
            price = (nQuantity * tempStats.getSeedCost());

            if (price > this.model.getPlayer().getObjectCoins()){
                view.setShopTextArea("Huhu you do not have enough objectcoins! You need " + (price - model.getPlayer().getObjectCoins()) + " more objectcoins to purchase " + nQuantity + " " + cropName + " seeds.");
            }
            else{
                view.setShopTextArea("You have successfully purchased " + nQuantity + " " + cropName + " seeds for " + price + " object coins! Happy Farming!");
                tempInventoryArray[cropIndex] += nQuantity;
                model.getPlayer().incrementObjectCoins(-1*(price));
                updatePlayerStatsInShop();
                updatePlayerStatsInMain();
            }


            
        }
        catch(NumberFormatException e){
            view.displayErrorMessage("Error! Please enter a valid quantity input.");

        }



    }

    // PLAYER ACTIONS

    public void plowTile(int row, int column, Tile tile){

        if (tile.isTilePlowed() == false && tile.tileHasRock() == false && tile.isCropWithered() == false && tile.isTileOccupied() == false)
        {
            tile.setPlowStatus(true);
            model.getPlayer().addPlayerExperience(0.5);
            view.setMainTextArea("Tile has successfully been plowed! You can now start planting crops.");
            view.setFarmTileImage(row, column, 48, 48, "/GameAssets/Tilesets/plowed.png");
        }
        else
        {
           if (tile.isTileOccupied() == true && tile.isCropGrowing() == true)
           {
                view.setMainTextArea("Plow tool use failed. Tile currently has a crop.");
           }

           else if (tile.tileHasRock() == true)
           {
               view.setMainTextArea("Plow tool use failed. Tile has rocks.");
           }

           else if (tile.isCropWithered() == true)
           {
                view.setMainTextArea("Plow tool use failed. Tile's crop is withered.");         
           }

           else if (tile.isTilePlowed() == true)
           {
                view.setMainTextArea("Plow tool use failed. Tile is already plowed.");     
           }

           else if (tile.isTileOccupied() == true)
           {
                view.setMainTextArea("Plow tool use failed. Tile is occupied.");     
           }

        }

    }

    public void waterTile(int row, int column, Tile tile){

        if (tile.isTilePlowed() == true)
        {
            tile.getTileStats().addCropWaterTimes();
            model.getPlayer().addPlayerExperience(80);
            view.setMainTextArea("Tile has been watered.");
            view.setFarmTileImage(row, column, 48, 48, "/GameAssets/Tilesets/plowedwatered.png");
               
        }
        else
        {
            if (tile.tileHasRock() == true){
                view.setMainTextArea("Failed to water tile. Tile has rocks");
            }
            else{
                view.setMainTextArea("Failed to water tile. Tile is not plowed");               
            }

        }

    }

    public void fertilizeTile(int row, int column, Tile tile){
        if (tile.isTilePlowed() == true)
        {
            if (model.getPlayer().getObjectCoins() >= 10)
            {
                tile.getTileStats().addFertilizerTimes();
                model.getPlayer().addPlayerExperience(4);
                model.getPlayer().incrementObjectCoins(-1*(10));

                view.setMainTextArea("Tile has been fertilized.");
                updatePlayerStatsInMain();
            }
            else
            {
                view.setMainTextArea("Not enough objectcoins to fertilize tile");
            }
        }
        else
        {
            view.setMainTextArea("Failed to fertilize tile. Tile is not plowed.");
        }  


    }

    public void plantCrop(Tile tile, Crop crop, FarmLot farmlot, int row, int column){

        int[] tempInventory = model.getPlayer().getSeedInventory();

        if (farmlot.getFarmLot().get(row).get(column).isTileAvailable() == true)
        {


            if (tempInventory[crop.getCropId()] > 0)
            {
                if (crop.getType().compareTo("Fruit Tree") == 0)
                {          
                    int i, j;
  
                    if (crop.isTreeAvailForPlanting(farmlot, row, column) == 0)
                    {


                        farmlot.getFarmLot().get(row).get(column).setTileCrop(crop.getName(), model.getPlayer().getFarmerType());
                        farmlot.getFarmLot().get(row).get(column).setTileOccupancy(true);
                        view.setMainTextArea("Planted " + crop.getName() +  " crop successfully...");
                        view.setCropImage(row, column, 48, 48, "/GameAssets/Crops/" + crop.getName() + ".png");

                        tempInventory[crop.getCropId()] = tempInventory[crop.getCropId()] - 1;


                        for (i = row - 1; i <= row + 1; i++)
                        {

                            for (j = column - 1; j <= column + 1; j++)
                            {
                                if (!(i == row && j == column))
                                {
                                    farmlot.getFarmLot().get(i).get(j).setTileOccupancy(true);
                                    view.setCropImage(i, j, 48, 48, "/GameAssets/Crops/roots.png");
                                }



                            }

                        }


                    }
                    else if (crop.isTreeAvailForPlanting(farmlot, row, column) == 1)
                    {
                        view.setMainTextArea("Unable to plant tree in the farm lot's borders. Please choose another tile.");
                    }
                    else if (crop.isTreeAvailForPlanting(farmlot, row, column) == 2)
                    {
                        view.setMainTextArea("Unable to plant tree. The following tile/s are occupied: ");
                        for (i  = row - 1; i <= row + 1; i++)
                        {
                            for (j = column - 1; j <= column + 1; j++)
                            {
                                if (farmlot.getFarmLot().get(i).get(j).isTileOccupied() == true)
                                {
                                    view.getMainTextArea().append("\nTile in row " + i + " and column " + j + " is occupied");   
                                }
                            }
        
                        }


                    }
                }                
                else
                {
                    farmlot.getFarmLot().get(row).get(column).setTileCrop(crop.getName(), model.getPlayer().getFarmerType());
                    farmlot.getFarmLot().get(row).get(column).setTileOccupancy(true);
                    view.setMainTextArea("Planted " + crop.getName() +  " crop successfully...");
                    view.setCropImage(row, column, 48, 48, "/GameAssets/Crops/" + crop.getName() + ".png"); // Set image of tile's crop

                    tempInventory[crop.getCropId()] = tempInventory[crop.getCropId()] - 1;
                }



                
            }
            else
            {
                view.setMainTextArea("You don't have enough seeds to plant " + crop.getName() + " crop.");   
            }
        }
        else
        {
            view.setMainTextArea("Tile is not available for planting.");
            if ( farmlot.getFarmLot().get(row).get(column).isCropWithered() == true)
            {
                view.setMainTextArea("Tile currently has a withered crop.");
            }
        }

        model.getPlayer().setInventory(tempInventory);
        updatePlayerStatsInMain();

    }


    public void harvestCrop(Tile tile, FarmLot farmLot, int row, int column){


        CropStats tempStats = tile.getCrop().getCropStats();
        
        if (tile.getCrop().isCropReadyForHarvest(tile) == true && tile.isCropWithered() == false)
        {
            int harvestTotal = tempStats.getProductsProduced() * (tempStats.getBaseSellingPrice() + model.getPlayer().getFarmerType().getBonusEarnings());
            double waterBonus = harvestTotal * 0.2 * (tempStats.getWaterRequirement() - 1);
            double fertilizerBonus = harvestTotal * 0.5 * (tempStats.getFertilizerReq());
            double finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;

            if (tile.getCrop().getType().compareTo("Flower") == 0)
            {
                finalHarvestPrice = finalHarvestPrice * 1.1;
            }


            view.setMainTextArea("Successfuly harvested " + tile.getCrop().getName() + " crop for " + finalHarvestPrice + " objectcoins. Player has earned " + tempStats.getExperienceYield() + " experience. Tile has been reverted to unplowed status.");
        

            model.getPlayer().incrementObjectCoins(finalHarvestPrice);
            model.getPlayer().addPlayerExperience(tempStats.getExperienceYield());

            tile.removeCrop();
            tile.setCropGrowthStatus(false);
            tile.setPlowStatus(false);
            tile.setTileOccupancy(false);
            tile.getTileStats().resetTileWaterTimes();
            tile.getTileStats().resetTileFertilizerTimes();
   

            view.setFarmTileImage(row, column, 48, 48, "/GameAssets/Tilesets/unplowed.png");
            view.setCropImage(row, column, 48, 48, "/GameAssets/Crops/blank.png");







        }



    }


    /**
		enables a player to use a shovel on a tile with varying results

        @param farmLot 5x10 farm lot.
        @param row tile's row coordinate.
        @param column tile's column coordinate
	 */
    public void useShovel (FarmLot farmLot, int row, int column)
    {

        Tile tempTile = farmLot.getFarmLot().get(row).get(column);
        int i, j;

        if (model.getPlayer().getObjectCoins() >= 7)
        {
            if (tempTile.isCropWithered() == true) // If shovel is used on a tile with a withered crop
            {
                farmLot.getFarmLot().get(row).get(column).setWitherStatus(false);
                farmLot.getFarmLot().get(row).get(column).removeCrop();
                farmLot.getFarmLot().get(row).get(column).setPlowStatus(false);
                farmLot.getFarmLot().get(row).get(column).setTileOccupancy(false);
                model.getPlayer().incrementObjectCoins(-1*(7));
                model.getPlayer().addPlayerExperience(2);
                view.setMainTextArea("Withered crop has been removed with a shovel. Tile has been reverted to unplowed status.");
                view.setCropImage(row, column, 48, 48, "/GameAssets/Crops/blank.png");
                view.setFarmTileImage(row, column, 48, 48, "/GameAssets/Tilesets/unplowed.png");
            }
            else if (tempTile.tileHasRock() == true || tempTile.isTilePlowed() == false) // If shovel is used on a tile with rocks/unplowed tile
            {
                model.getPlayer().incrementObjectCoins(-1*(7));
                view.setMainTextArea("Shovel used. No changes have been made to the tile.");


            }
            else if (tempTile.isTileOccupied() == true && tempTile.isCropGrowing() == true) // If shovel is used on a tile with a crop 
            {



                view.setMainTextArea("" + farmLot.getFarmLot().get(row).get(column).getCrop().getName() + " crop has been removed with a shovel. Tile has been reverted to unplowed status.");
                view.setCropImage(row, column, 48, 48, "/GameAssets/Crops/blank.png");
                view.setFarmTileImage(row, column, 48, 48, "/GameAssets/Tilesets/unplowed.png");

                if (tempTile.getCrop().getType().compareTo("Fruit Tree") == 0)
                {
                    for (i = row - 1; i <= row + 1; i++)
                    {
                        for (j = column - 1; j <= column + 1; j++)
                        {
                            if (!(i == row && j == column))
                            {
                                farmLot.getFarmLot().get(i).get(j).resetTile();
                                view.setCropImage(i, j, 48, 48, "/GameAssets/Crops/blank.png");
                                view.setFarmTileImage(i, j, 48, 48, "/GameAssets/Tilesets/unplowed.png");
                            }
                        }
                    }

                }
                farmLot.getFarmLot().get(row).get(column).removeCrop();
                farmLot.getFarmLot().get(row).get(column).getTileStats().resetTileFertilizerTimes();
                farmLot.getFarmLot().get(row).get(column).getTileStats().resetTileWaterTimes();
                farmLot.getFarmLot().get(row).get(column).setTileOccupancy(false);
                farmLot.getFarmLot().get(row).get(column).setPlowStatus(false);
                farmLot.getFarmLot().get(row).get(column).setCropGrowthStatus(false);
                model.getPlayer().incrementObjectCoins(-7);
                model.getPlayer().addPlayerExperience(2);
            }
            else if (tempTile.isTileOccupied() == true){
                model.getPlayer().incrementObjectCoins(-1*(7));
                view.setMainTextArea("Shovel used. No changes have been made to the tile.");


            }
            else if (tempTile.isTilePlowed() == true) // If shovel is used on a plowed and unoccupied tile
            {
                model.getPlayer().incrementObjectCoins(-7);
                farmLot.getFarmLot().get(row).get(column).getTileStats().resetTileFertilizerTimes();
                farmLot.getFarmLot().get(row).get(column).getTileStats().resetTileWaterTimes();
                farmLot.getFarmLot().get(row).get(column).setPlowStatus(false);
                view.setMainTextArea("Shovel used on a plowed and unoccupied tile. Tile has been reverted to an unplowed status");
                view.setFarmTileImage(row, column, 48, 48, "/GameAssets/Tilesets/unplowed.png");
            }
        }
        else if (model.getPlayer().getObjectCoins() < 7)
        {
            view.setMainTextArea("Unable to use shovel. You need " + (7 - model.getPlayer().getObjectCoins()) + " more objectcoins to use the shovel.");
        }





    }

    /**
		enables a player to remove rocks in a specific tile.

        @param tile specific tile in a 5x10 farmlot.
	 */
    public void usePickaxe(Tile tile, int row, int column){

        if (tile.tileHasRock() == true)
        {
            if (model.getPlayer().getObjectCoins() >= 50)
            {
                tile.setTileRockStatus(false);
                tile.setTileOccupancy(false);
                model.getPlayer().incrementObjectCoins(-50);
                model.getPlayer().addPlayerExperience(15);
                view.setMainTextArea("Rocks on tile have been removed with pickaxe.");
                view.setFarmTileImage(row, column, 48, 48, "/GameAssets/Tilesets/unplowed.png");
            }
            else if (model.getPlayer().getObjectCoins() < 50)
            {
                view.setMainTextArea("Not enough objectcoins to use pickaxe.");
            }
        }
        else
        {
            view.setMainTextArea("Unable to pickaxe tile. Tile has no rock presence.");
        }

    }




    // DISPLAY

    public void displayTurnip(){
        view.setItemInfoTextArea("Harvest Time: 2 days\nWater Needs: 1(2)\nFertilizer Needs: 0(1)\nBase Selling Price: 6\nExperience Yield: 5\nProducts Produced: 1-2\n\n\nEnter Quantity: ");
        view.setTextLabelString(7, "Turnip");
        view.setTextLabelString(8, "Root Crop");
        visibilityItemInfoUI(true);
    }

    public void displayCarrot(){
        view.setItemInfoTextArea("Harvest Time: 3 days\nWater Needs: 1(2)\nFertilizer Needs: 0(1)\nBase Selling Price: 9\nExperience Yield: 7.5\nProducts Produced: 1-2\n\n\nEnter Quantity: ");
        view.setTextLabelString(7, "Carrot");
        view.setTextLabelString(8, "Root Crop");
        visibilityItemInfoUI(true);
    }

    public void displayPotato(){
        view.setItemInfoTextArea("Harvest Time: 5 days\nWater Needs: 3(4)\nFertilizer Needs: 1(2)\nBase Selling Price: 3\nExperience Yield: 12.5\nProducts Produced: 1-10\n\n\nEnter Quantity: ");
        view.setTextLabelString(7, "Potato");
        view.setTextLabelString(8, "Root Crop");
        visibilityItemInfoUI(true);
    }

    public void displayRose(){
        view.setItemInfoTextArea("Harvest Time: 1 day\nWater Needs: 1(2)\nFertilizer Needs: 0(1)\nBase Selling Price: 5\nExperience Yield: 2.5\nProducts Produced: 1\n\n\nEnter Quantity: ");
        view.setTextLabelString(7, "Rose");
        view.setTextLabelString(8, "Flower");
        visibilityItemInfoUI(true);
    }

    public void displayTulips(){
        view.setItemInfoTextArea("Harvest Time: 2 days\nWater Needs: 2(3)\nFertilizer Needs: 0(1)\nBase Selling Price: 9\nExperience Yield: 5\nProducts Produced: 1\n\n\nEnter Quantity: ");
        view.setTextLabelString(7, "Tulips");
        view.setTextLabelString(8, "Flower");
        visibilityItemInfoUI(true);
    }

    public void displaySunflower(){
        view.setItemInfoTextArea("Harvest Time: 3 days\nWater Needs: 2(3)\nFertilizer Needs: 1(2)\nBase Selling Price: 19\nExperience Yield: 7.5\nProducts Produced: 1\n\n\nEnter Quantity: ");
        view.setTextLabelString(7, "Sunflower");
        view.setTextLabelString(8, "Flower");
        visibilityItemInfoUI(true);
    }

    public void displayMango(){
        view.setItemInfoTextArea("Harvest Time: 10 days\nWater Needs: 7(7)\nFertilizer Needs: 4(4)\nBase Selling Price: 8\nExperience Yield: 25\nProducts Produced: 5-15\n\n\nEnter Quantity: ");
        view.setTextLabelString(7, "Mango");
        view.setTextLabelString(8, "Fruit Tree");
        visibilityItemInfoUI(true);
    }

    public void displayApple(){
        view.setItemInfoTextArea("Harvest Time: 10 days\nWater Needs: 7(7)\nFertilizer Needs: 5(5)\nBase Selling Price: 5\nExperience Yield: 25\nProducts Produced: 10-15\n\n\nEnter Quantity: ");
        view.setTextLabelString(7, "Apple");
        view.setTextLabelString(8, "Fruit Tree");
        visibilityItemInfoUI(true);
    }

    public void showShop(){

        updatePlayerStatsInShop();
        view.showShopScreen();
        view.setClickLabelArrVisibility(false);
        view.setShopTextArea("Henloo human. This is the start of your farming journey in this farm lot!. Since you've had quite a handy experience in farming, I guess I don't need to show you around the basics. Good luck on your farming endeavor!");
        cropIndex = -1;
        chosenItem = "";
        visibilityItemInfoUI(false);
    }

    public void showRegisterUI(){

        view.showRegisterUI();
        view.setClickLabelArrVisibility(false);
        updatePlayerStatsInRegistration();
    }

    public void exitRegisterUI(){

        view.exitRegisterUI();
        view.setClickLabelArrVisibility(true);
        updatePlayerStatsInMain();
    }

    public void exitShop(){
        view.exitShopScreen();
        visibilityItemInfoUI(false);
        view.setClickLabelArrVisibility(true);
        updatePlayerStatsInMain();
    }






    // UPDATE STATUS

    public void updatePlayerStatsInMain(){

        view.setTextLabelString(1, "" + this.model.getPlayer().getFarmerType().getFarmerType());
        view.setTextLabelString(2, "" + (int)this.model.getPlayer().getObjectCoins());
        view.setTextLabelString(3, "" + this.model.getPlayer().getLevel());
        int j = 0;
        for (int i = 8; i < 16; i++){

            int[] nArray = this.model.getPlayer().getSeedInventory();

            view.setSlotCountLabel(i, "" + nArray[j]);
            j++;
            
        }


    }

    public void updatePlayerStatsInShop(){

        view.setTextLabelString(4, "" + this.model.getPlayer().getFarmerType().getFarmerType());
        view.setTextLabelString(5, "" + (int)this.model.getPlayer().getObjectCoins());
        view.setTextLabelString(6, "" + this.model.getPlayer().getLevel());

        for (int i = 0; i < 8; i++){

            int[] nArray = this.model.getPlayer().getSeedInventory();

            view.setSlotCountLabel(i, "" + nArray[i]);
            
        }
    }

    public void updatePlayerStatsInRegistration(){

        view.setTextLabelString(9, "" + this.model.getPlayer().getFarmerType().getFarmerType());
        view.setTextLabelString(10, "" + (int)this.model.getPlayer().getObjectCoins());
        view.setTextLabelString(11, "" + this.model.getPlayer().getLevel());

    }

    public void updateFarmLotStatus(){
        Tile tempTile;
        

        for (int i = 0; i < 5; i++)
        {

            for (int j = 0; j < 10; j++)
            {
                tempTile = model.getFarmLot().getFarmLot().get(i).get(j);

                if (tempTile.tileHasRock() == true)
                {
                    view.setFarmTileImage(i, j, 48, 48, "/GameAssets/Tilesets/rocks.png");

                }
                else if (tempTile.isTilePlowed() == false && tempTile.isTileOccupied() == false)
                {
                    view.setFarmTileImage(i, j, 48, 48, "/GameAssets/Tilesets/unplowed.png");
                }
                else if (tempTile.isTilePlowed() == true && tempTile.isTileOccupied() == false)
                {
                    view.setFarmTileImage(i, j, 48, 48, "/GameAssets/Tilesets/plowed.png");
                }
                else if (tempTile.isTileOccupied() == true)
                {
                    if (tempTile.isCropGrowing() == true)
                    {
                        switch (tempTile.getCrop().getName()) {
                            case "Turnip":
                                view.setCropImage(i, j, 48, 48, "/GameAssets/Crops/Turnip.png");
                                if (tempTile.getTileStats().getCropWaterTimes() > 0){
                                    view.setFarmTileImage(i, j, 48, 48, "/GameAssets/Tilesets/plowedwatered.png");
                                }
                                break;
                            case "Carrot":
                                view.setCropImage(i, j, 48, 48, "/GameAssets/Crops/Carrot.png");
                                if (tempTile.getTileStats().getCropWaterTimes() > 0){
                                    view.setFarmTileImage(i, j, 48, 48, "/GameAssets/Tilesets/plowedwatered.png");
                                }
                                break;
                            case "Potato":
                                view.setCropImage(i, j, 48, 48, "/GameAssets/Crops/Potato.png");
                                if (tempTile.getTileStats().getCropWaterTimes() > 0){
                                    view.setFarmTileImage(i, j, 48, 48, "/GameAssets/Tilesets/plowedwatered.png");
                                }
                                break;
                            case "Rose":
                                view.setCropImage(i, j, 48, 48, "/GameAssets/Crops/Rose.png");
                                if (tempTile.getTileStats().getCropWaterTimes() > 0){
                                    view.setFarmTileImage(i, j, 48, 48, "/GameAssets/Tilesets/plowedwatered.png");
                                }
                                break;
                            case "Tulips":
                                view.setCropImage(i, j, 48, 48, "/GameAssets/Crops/Tulips.png");
                                if (tempTile.getTileStats().getCropWaterTimes() > 0){
                                    view.setFarmTileImage(i, j, 48, 48, "/GameAssets/Tilesets/plowedwatered.png");
                                }
                                break;                
                            case "Sunflower":
                                view.setCropImage(i, j, 48, 48, "/GameAssets/Crops/Sunflower.png");
                                if (tempTile.getTileStats().getCropWaterTimes() > 0){
                                    view.setFarmTileImage(i, j, 48, 48, "/GameAssets/Tilesets/plowedwatered.png");
                                }
                                break;
                            case "Mango":
                                view.setCropImage(i, j, 48, 48, "/GameAssets/Crops/MangoTree.png");
                                if (tempTile.getTileStats().getCropWaterTimes() > 0){
                                    view.setFarmTileImage(i, j, 48, 48, "/GameAssets/Tilesets/plowedwatered.png");
                                }
                                break;
                            case "Apple":
                                view.setCropImage(i, j, 48, 48, "/GameAssets/Crops/AppleTree.png");
                                if (tempTile.getTileStats().getCropWaterTimes() > 0){
                                    view.setFarmTileImage(i, j, 48, 48, "/GameAssets/Tilesets/plowedwatered.png");
                                }
                                break;
                
                
                            default:
                                view.setCropImage(i, j, 48, 48, "/GameAssets/Crops/blank.png");

                        }
                    }
                    else if (tempTile.isCropWithered() == true)
                    {
                                view.setCropImage(i, j, 48, 48, "/GameAssets/Crops/Withered.png");
                    }
                    else if (tempTile.isTileOccupied() == true)
                    {
                        view.setCropImage(i, j, 48, 48, "/GameAssets/Crops/roots.png");
                    }




                }

 

            }
            



        }


    }



    // REGISTER

    public void registerFarmer(String action, Player player){

        //Player tempPlayer = player;

        String farmerType = player.getFarmerType().getFarmerType();

        if (action.compareTo("registerRF") == 0){
            
            if ((farmerType.compareTo("Default Farmer") == 0) && player.getObjectCoins() >= 200 && player.getLevel() >= 5){

                view.setRegisterTextArea("You are now successfully registered as a Registered Farmer! You may now enjoy new benefits!");
                view.setButtonVisibility(14, false);
                view.setButtonVisibility(16, true);

                view.setObjectImage(9, 64, 20, "/GameAssets/Buttons/registeredButton.png");
                view.setObjectImage(7, 64, 20, "/GameAssets/Buttons/registerButton.png");
                player.incrementObjectCoins(-1*(200));
                player.setFarmerType("Registered Farmer");
            }
            else{


                    if (player.getObjectCoins() >= 200 && player.getLevel() < 5){
                        view.setRegisterTextArea("You do not meet the level requirement of this status to register! You need " + (5 - player.getLevel()) + " more levels to be able to register. Work harder!");                        
                    }
                    else if (player.getObjectCoins() < 200 && player.getLevel() >= 5){
                        view.setRegisterTextArea("You have insufficient objectcoins to register to this status! You need " + (200 - player.getObjectCoins()) + " more objectcoins to register. Work harder!");  
                    }
                    else if (player.getObjectCoins() < 200 && player.getLevel() < 5){
                        view.setRegisterTextArea("You do not meet the level requirement and you have insufficient objectcoins to register to this status! You need " + (200 - player.getObjectCoins()) + " more objectcoins and " + (5 - player.getLevel()) + " more levels to register. Work harder!");  
                    }
                
            }
        }
        else if (action.compareTo("registerDF") == 0){

            if ((farmerType.compareTo("Registered Farmer") == 0) && player.getObjectCoins() >= 300 && player.getLevel() >= 10){

                view.setRegisterTextArea("You are now successfully registered as a Distinguished Farmer! You may now enjoy new benefits!");
                view.setButtonVisibility(16, false);
                view.setButtonVisibility(15, true);

                view.setObjectImage(7, 64, 20, "/GameAssets/Buttons/registeredButton.png");
                view.setObjectImage(8, 64, 20, "/GameAssets/Buttons/registerButton.png");
                player.incrementObjectCoins(-1*(300));
                player.setFarmerType("Distinguished Farmer");
            }
            else{


                    if (player.getObjectCoins() >= 300 && player.getLevel() < 10){
                        view.setRegisterTextArea("You do not meet the level requirement to register as Distinguished Farmer Status! You need " + (10 - player.getLevel()) + " more levels to be able to register. Work harder!");                        
                    }
                    else if (player.getObjectCoins() < 300 && player.getLevel() >= 10){
                        view.setRegisterTextArea("You have insufficient objectcoins to register as Distinguished Farmer Status! You need " + (300 - player.getObjectCoins()) + " more objectcoins to register. Work harder!");  
                    }
                    else if (player.getObjectCoins() < 300 && player.getLevel() < 10){
                        view.setRegisterTextArea("You do not meet the level requirement and you have insufficient objectcoins to register as Distinguished Farmer Status! You need " + (300 - player.getObjectCoins()) + " more objectcoins and " + (10 - player.getLevel()) + " more levels to register. Work harder!");  
                    }
                }
            }



        
        else if (action.compareTo("registerLF") == 0){

            if ((farmerType.compareTo("Distinguished Farmer") == 0) && player.getObjectCoins() >= 400 && player.getLevel() >= 15){

                view.setRegisterTextArea("You are now successfully registered as a Legendary Farmer! You may now enjoy all the benefits!");
                view.setButtonVisibility(15, false);

                view.setObjectImage(8, 64, 20, "/GameAssets/Buttons/registeredButton.png");

                player.incrementObjectCoins(-1*(400));
                player.setFarmerType("Legendary Farmer");
            }
            else{

                    if (player.getObjectCoins() >= 400 && player.getLevel() < 15){
                        view.setRegisterTextArea("You do not meet the level requirement to register as Distinguished Farmer Status! You need " + (15 - player.getLevel()) + " more levels to be able to register. Work harder!");                        
                    }
                    else if (player.getObjectCoins() < 400 && player.getLevel() >= 15){
                        view.setRegisterTextArea("You have insufficient objectcoins to register as Distinguished Farmer Status! You need " + (400 - player.getObjectCoins()) + " more objectcoins to register. Work harder!");  
                    }
                    else if (player.getObjectCoins() < 400 && player.getLevel() < 15){
                        view.setRegisterTextArea("You do not meet the level requirement and you have insufficient objectcoins to register as Distinguished Farmer Status! You need " + (400 - player.getObjectCoins()) + " more objectcoins and " + (15 - player.getLevel()) + " more levels to register. Work harder!");  
                    }
                
            }


        }

        updatePlayerStatsInRegistration();
        updatePlayerStatsInMain();

    }



    // CHECKERS
    public void checkTileCropWitherStatus(int row, int column, ArrayList<ArrayList<Tile>> farmTiles)
    {
        Tile tempTile = farmTiles.get(row).get(column);
        
        if (tempTile.isTileOccupied() == true && tempTile.isCropWithered() == false && tempTile.isCropGrowing() == true)
        {
            if (tempTile.getCrop().getCropStats().getDuration() > tempTile.getCrop().getCropStats().getHarvestTime())
            {
                int i, j;


                if (tempTile.getCrop().getType().compareTo("Fruit Tree") == 0)
                {
                    for (i = row - 1; i <= row + 1; i++) // Sets surrounding adjacent tiles to withered
                    {
                        for (j = column - 1; j <= column + 1; j++)
                        {
                            if (!(i == row && j == column))
                            {
                                farmTiles.get(i).get(j).setWitherStatus(true);
                                view.setCropImage(i, j, 48, 48, "/GameAssets/Crops/withered.png");
                            }
                        }
                    }
                }

                view.getMainTextArea().append(" " + tempTile.getCrop().getName() + " crop at tile with coordinates at row " + row + " and column " + column + " has withered..."); // determines which tile coordinate has a withered crop
                farmTiles.get(row).get(column).setWitherStatus(true);
                view.setCropImage(row, column, 48, 48, "/GameAssets/Crops/withered.png");
                farmTiles.get(row).get(column).removeCrop();
                farmTiles.get(row).get(column).setCropGrowthStatus(false);



            }
        }

           
    }

    public void checkCropsReadyToHarvest()
    {

        int i, j;

        //
        
        //view.setMainTextArea("");
        for (i = 0; i < 5; i++)
        {

            for (j = 0; j < 10; j++)
            {
                if (model.getFarmLot().getFarmLot().get(i).get(j).isCropGrowing() == true)
                {
                    if (model.getFarmLot().getFarmLot().get(i).get(j).getCrop().isCropReadyForHarvest(model.getFarmLot().getFarmLot().get(i).get(j)) == true)
                    {
                       view.getMainTextArea().append("\n" + model.getFarmLot().getFarmLot().get(i).get(j).getCrop().getName() + " crop in tile at row " + i + " and column " + j + " is ready for harvest");
                    }
                }


            }

        }


    }






    // NEW GAME FUNCTIONS


    public void setStartingGameLabels(){
        view.setTextLabelString(0, this.model.getPlayer().getPlayerName());
        view.setTextLabelString(1, "" + this.model.getPlayer().getFarmerType().getFarmerType());
        view.setTextLabelString(2, "" + (int)this.model.getPlayer().getObjectCoins());
        view.setTextLabelString(3, "" + this.model.getPlayer().getLevel());

        for (int i = 0; i < 8; i++){

            int[] nArray = this.model.getPlayer().getSeedInventory();

            view.setSlotCountLabel(i, "" + nArray[i]);
            
        }
    }
    public void setNewGame(){
        model.setNewFarmLot();
        model.setDefaultPlayer();
    }


    // END GAME FUNCTIONS

    
    /**
		checks whether a player meets the end game (losing) conditions. If a player loses the game, he is prompted back to the main menu.


        @param farmLot 5x10 farm lot.
	*/
    public void checkEndGameConditions (FarmLot farmLot)
    {

        if ((farmLot.countGrowingCrops() == 0 && (model.getPlayer().getObjectCoins() < 5 - model.getPlayer().getFarmerType().getSeedCostReduction()) && model.getPlayer().getTotalInventorySeedCount() == 0))
        {
            view.showGameOverUI();
            view.setGameOverTextArea("You don't have any more seeds and you don't have enough objectcoins to buy any seed. Farm lot also has no more growing crops left.");


        }
        else if (farmLot.countWitheredCrops() == 50)
        {
            view.setGameOverTextArea("All of your tiles and crops have withered.\nGame over... Going back to menu.");

        }

    }





    public class InvalidUsernameException extends Exception{
        public InvalidUsernameException (String str){

            super(str);
        }
    }


    public class BuyErrorException extends Exception{
        public BuyErrorException (String str){

            super(str);
        }
    }


}