import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.DimensionUIResource;  


public class MCOView {


    private JFrame window;
    private JPanel[] bgPanelArr = new JPanel[10]; // Scene Array
    private JLabel[] imageLabelArr = new JLabel[30]; // Image array
    private JLabel[] buttonImageLabelArr = new JLabel[30]; // Button background image array
    private JLabel[] textLabelArr = new JLabel[30];
    private JLabel[] slotCountArr = new JLabel[16];
    private JLabel[][] tileImageArr = new JLabel[5][10];
    private JLabel[][] cropImageArr = new JLabel[5][10];
    private JLabel[][] clickLabelArr = new JLabel[5][10];
    private JButton[] buttonArr = new JButton[30]; // Button array
    private JPopupMenu popMenu[][] = new JPopupMenu[5][10];
    private JMenuItem menuItem[][][] = new JMenuItem[5][10][7]; 
    private JTextField[] tfArray = new JTextField[20];
    private JTextArea messageTextArea;
    private JTextArea shopTextArea;
    private JTextArea itemInfoTextArea;
    private JTextArea registerTextArea;
    private JTextArea gameOverTextArea;

    // FONTS 
    private Font titleFont = new Font("Toriko", Font.PLAIN, 36);
    private Font usernameFont = new Font("Toriko", Font.PLAIN, 30);
    private Font farmerStatusFont = new Font("Toriko", Font.PLAIN, 17);
    private Font objectCoinsFont = new Font("Toriko", Font.PLAIN, 24);
    private Font levelStatusFont = new Font("Toriko", Font.PLAIN, 24);
    private Font slotCountFont = new Font("Toriko", Font.PLAIN, 18);
    private Font textAreaFont = new Font("Toriko", Font.PLAIN, 24);
    private Font shopAreaFont = new Font("Toriko", Font.PLAIN, 14);
    private Font itemInfoAreaFont = new Font("Toriko", Font.PLAIN, 16);
    private Font gameOverFont = new Font("Toriko", Font.PLAIN, 144);

    // TRACK

    private int row = 0;
    private int column = 0;




    // COLOR
    Color gray = new Color(64,64,64, 200);


    private String currentScene;


    public MCOView(){
        createMainMenuScreen();
        generateScreen();

        this.window.setResizable(false);


    }




    // METHODS

    public void createMainMenuScreen(){
        this.window = new JFrame("Awesome Farm Game 2000");
        this.window.setSize(800, 600);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.getContentPane().setBackground(Color.blue);
        this.window.setLayout(null);
        this.window.setVisible(true);



    }

    public void createTextField(int tfArrayIndex, int bgPanelArrIndex, int x, int y, int width, int height){
        this.tfArray[tfArrayIndex] = new JTextField();
        this.tfArray[tfArrayIndex].setBounds(x, y, width, height);
        this.tfArray[tfArrayIndex].setFont(titleFont);
        this.tfArray[tfArrayIndex].setBackground(Color.white);
        this.tfArray[tfArrayIndex].setForeground(Color.black);
        this.tfArray[tfArrayIndex].setOpaque(false);
        this.tfArray[tfArrayIndex].setHorizontalAlignment(JTextField.CENTER);
        this.tfArray[tfArrayIndex].setBorder(javax.swing.BorderFactory.createEmptyBorder());


        this.bgPanelArr[bgPanelArrIndex].add(this.tfArray[tfArrayIndex]);
    }


    public void createBackground(int bgPanelArrIndex, int imageLabelArrNum, String bgFileName){

        this.bgPanelArr[bgPanelArrIndex] = new JPanel();
        this.bgPanelArr[bgPanelArrIndex].setBounds(0, 0, 800, 600);
        this.bgPanelArr[bgPanelArrIndex].setBackground(null);
        this.bgPanelArr[bgPanelArrIndex].setLayout(null);
        this.bgPanelArr[bgPanelArrIndex].setOpaque(false);



        ImageIcon bgIcon = new ImageIcon(getClass().getResource(bgFileName));
        Image bgImage = bgIcon.getImage();
        Image modifiedBgImage = bgImage.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);

        bgIcon = new ImageIcon(modifiedBgImage);

        this.imageLabelArr[imageLabelArrNum] = new JLabel();
        this.imageLabelArr[imageLabelArrNum].setBounds(0, 0, 800, 600);
        this.imageLabelArr[imageLabelArrNum].setIcon(bgIcon);

        this.window.add(bgPanelArr[bgPanelArrIndex]);

    }

    public void createBackground(int bgPanelArrIndex, Color color){

        this.bgPanelArr[bgPanelArrIndex] = new JPanel();
        this.bgPanelArr[bgPanelArrIndex].setBounds(0, 0, 800, 600);
        this.bgPanelArr[bgPanelArrIndex].setBackground(color);
        this.bgPanelArr[bgPanelArrIndex].setLayout(null);


        this.window.add(bgPanelArr[bgPanelArrIndex]);

    }

    public void createObjectImage(int imageArrIndex, int bgPanelArrIndex, int x, int y, int width, int height, String fileName, boolean isOpaque){
        
        ImageIcon objectIcon = new ImageIcon(getClass().getResource(fileName));

        Image objectImage = objectIcon.getImage();
        Image modifiedObjectImage = objectImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

        objectIcon.setImage(modifiedObjectImage);

        this.imageLabelArr[imageArrIndex] = new JLabel();
        this.imageLabelArr[imageArrIndex].setBounds(x, y, width, height);
        this.imageLabelArr[imageArrIndex].setBackground(null);
        this.imageLabelArr[imageArrIndex].setIcon(objectIcon);
        this.imageLabelArr[imageArrIndex].setOpaque(isOpaque);


        this.bgPanelArr[bgPanelArrIndex].add(this.imageLabelArr[imageArrIndex]);

    }

    public void createCropImage(int row, int column, int bgPanelArrIndex, int x, int y, int width, int height, String fileName, boolean isOpaque){
        
        ImageIcon objectIcon = new ImageIcon(getClass().getResource(fileName));

        Image objectImage = objectIcon.getImage();
        Image modifiedObjectImage = objectImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

        objectIcon.setImage(modifiedObjectImage);

        this.cropImageArr[row][column] = new JLabel();
        this.cropImageArr[row][column].setBounds(x, y, width, height);
        this.cropImageArr[row][column].setBackground(null);
        this.cropImageArr[row][column].setIcon(objectIcon);
        this.cropImageArr[row][column].setOpaque(isOpaque);


        this.bgPanelArr[bgPanelArrIndex].add(this.cropImageArr[row][column]);

    }

    public void createClickLabels(int popMenuRow, int popMenuColumn, int menuItemXIndex, int menuItemYIndex, int bgPanelArrIndex,
     int x, int y, int width, int height, boolean isOpaque, 
    String choiceOneName, String choiceTwoName, String choiceThreeName, String choiceFourName, String choiceFiveName, String choiceSixName, String choiceSevenName,
    String choiceOneCommand, String choiceTwoCommand, String choiceThreeCommand, String choiceFourCommand, String choiceFiveCommand, String choiceSixCommand, String choiceSevenCommand){

      
        // CREATE OBJECT
        this.clickLabelArr[popMenuRow][popMenuColumn] = new JLabel();


        this.popMenu[popMenuRow][popMenuColumn] = new JPopupMenu();

        // CREATE POP MENU ITEMS
   
        this.menuItem[menuItemXIndex][menuItemYIndex][0] = new JMenuItem(choiceOneName);
        this.menuItem[menuItemXIndex][menuItemYIndex][0].setActionCommand(choiceOneCommand);
        this.popMenu[popMenuRow][popMenuColumn].add(this.menuItem[menuItemXIndex][menuItemYIndex][0]);
        

        this.menuItem[menuItemXIndex][menuItemYIndex][1] = new JMenuItem(choiceTwoName);
        this.menuItem[menuItemXIndex][menuItemYIndex][1].setActionCommand(choiceTwoCommand);
        this.popMenu[popMenuRow][popMenuColumn].add(this.menuItem[menuItemXIndex][menuItemYIndex][1]);


        this.menuItem[menuItemXIndex][menuItemYIndex][2] = new JMenuItem(choiceThreeName);
        this.menuItem[menuItemXIndex][menuItemYIndex][2].setActionCommand(choiceThreeCommand);
        this.popMenu[popMenuRow][popMenuColumn].add(this.menuItem[menuItemXIndex][menuItemYIndex][2]);


        this.menuItem[menuItemXIndex][menuItemYIndex][3] = new JMenuItem(choiceFourName);
        this.menuItem[menuItemXIndex][menuItemYIndex][3].setActionCommand(choiceFourCommand);
        this.popMenu[popMenuRow][popMenuColumn].add(this.menuItem[menuItemXIndex][menuItemYIndex][3]);


        this.menuItem[menuItemXIndex][menuItemYIndex][4] = new JMenuItem(choiceFiveName);
        this.menuItem[menuItemXIndex][menuItemYIndex][4].setActionCommand(choiceFiveCommand);
        this.popMenu[popMenuRow][popMenuColumn].add(this.menuItem[menuItemXIndex][menuItemYIndex][4]);


        this.menuItem[menuItemXIndex][menuItemYIndex][5] = new JMenuItem(choiceSixName);
        this.menuItem[menuItemXIndex][menuItemYIndex][5].setActionCommand(choiceSixCommand);
        this.popMenu[popMenuRow][popMenuColumn].add(this.menuItem[menuItemXIndex][menuItemYIndex][5]);

        this.menuItem[menuItemXIndex][menuItemYIndex][6] = new JMenuItem(choiceSevenName);
        this.menuItem[menuItemXIndex][menuItemYIndex][6].setActionCommand(choiceSevenCommand);
        this.popMenu[popMenuRow][popMenuColumn].add(this.menuItem[menuItemXIndex][menuItemYIndex][6]);


        this.clickLabelArr[popMenuRow][popMenuColumn].setBounds(x, y, width, height);
        this.clickLabelArr[popMenuRow][popMenuColumn].setBackground(Color.white); // remove later
        this.clickLabelArr[popMenuRow][popMenuColumn].setOpaque(isOpaque); // remove later

        this.clickLabelArr[popMenuRow][popMenuColumn].addMouseListener(new MouseInputAdapter() {
            public void mousePressed(MouseEvent e){

                if (SwingUtilities.isLeftMouseButton(e)){
                    popMenu[popMenuRow][popMenuColumn].show(clickLabelArr[popMenuRow][popMenuColumn], e.getX(), e.getY());
                    row = popMenuRow;
                    column = popMenuColumn;

                }
            }
        });


        bgPanelArr[bgPanelArrIndex].add(this.clickLabelArr[popMenuRow][popMenuColumn]);



    }


    public void createButtonImage(int buttonImageArrIndex, int bgPanelArrIndex, int x, int y, int width, int height, String fileName, boolean isOpaque){

        ImageIcon buttonIcon = new ImageIcon(getClass().getResource(fileName));

        Image buttonImage = buttonIcon.getImage();
        Image modifiedButtonImage = buttonImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

        buttonIcon.setImage(modifiedButtonImage);

        this.buttonImageLabelArr[buttonImageArrIndex] = new JLabel();
        this.buttonImageLabelArr[buttonImageArrIndex].setBounds(x, y, width, height);
        this.buttonImageLabelArr[buttonImageArrIndex].setBackground(null);
        this.buttonImageLabelArr[buttonImageArrIndex].setIcon(buttonIcon);
        this.buttonImageLabelArr[buttonImageArrIndex].setOpaque(isOpaque);


        this.bgPanelArr[bgPanelArrIndex].add(this.buttonImageLabelArr[buttonImageArrIndex]);

    }

    public void createFarmTile(int row, int column, int bgPanelArrIndex, int x, int y, int width, int height, String filename){


        ImageIcon tileIcon = new ImageIcon(getClass().getResource(filename));

        Image tileImage = tileIcon.getImage();
        Image modifiedTileImage = tileImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

        tileIcon.setImage(modifiedTileImage);

        
        this.tileImageArr[row][column] = new JLabel();
        this.tileImageArr[row][column].setBounds(x, y, width, height);
        this.tileImageArr[row][column].setBackground(null);
        this.tileImageArr[row][column].setIcon(tileIcon);

        this.bgPanelArr[bgPanelArrIndex].add(this.tileImageArr[row][column]);


    }

    public void createButton(int buttonArrIndex, int bgPanelArrIndex, int x, int y, int width, int height, String command, String buttonText){

        this.buttonArr[buttonArrIndex] = new JButton();
        this.buttonArr[buttonArrIndex].setBounds(x, y, width, height);
        this.buttonArr[buttonArrIndex].setBackground(null);
        this.buttonArr[buttonArrIndex].setForeground(Color.black); // Font COLOR
        this.buttonArr[buttonArrIndex].setContentAreaFilled(false);
        this.buttonArr[buttonArrIndex].setFocusPainted(false);
        this.buttonArr[buttonArrIndex].setBorderPainted(false);
        this.buttonArr[buttonArrIndex].setFont(titleFont);
        this.buttonArr[buttonArrIndex].setText(buttonText);
        this.buttonArr[buttonArrIndex].setActionCommand(command);
        this.bgPanelArr[bgPanelArrIndex].add(this.buttonArr[buttonArrIndex]);

    }


    public void createTextLabel(int textLabelArrIndex, int bgPanelArrIndex, int x, int y, int width, int height, String text, Font font){
        this.textLabelArr[textLabelArrIndex] = new JLabel();
        this.textLabelArr[textLabelArrIndex].setBounds(x, y, width, height);
        this.textLabelArr[textLabelArrIndex].setBackground(Color.white);
        this.textLabelArr[textLabelArrIndex].setForeground(Color.black);
        this.textLabelArr[textLabelArrIndex].setFont(font);
        this.textLabelArr[textLabelArrIndex].setText(text);
        //this.textLabelArr[textLabelArrIndex].setOpaque(true);


        this.bgPanelArr[bgPanelArrIndex].add(this.textLabelArr[textLabelArrIndex]);
    }

    public void createInventorySlotCount(int slotCountArrIndex, int bgPanelArrIndex, int x, int y, int width, int height, String text, Font font ){

        this.slotCountArr[slotCountArrIndex] = new JLabel();
        this.slotCountArr[slotCountArrIndex].setBounds(x, y, width, height);
        this.slotCountArr[slotCountArrIndex].setBackground(Color.white);
        this.slotCountArr[slotCountArrIndex].setForeground(Color.black);
        this.slotCountArr[slotCountArrIndex].setFont(font);
        this.slotCountArr[slotCountArrIndex].setText(text);
        //this.slotCountArr[slotCountArrIndex].setOpaque(true);

        this.bgPanelArr[bgPanelArrIndex].add(this.slotCountArr[slotCountArrIndex]);
    }

    public void createTextArea(int bgPanelArrIndex, int x, int y, int width, int height, String text, Font font){
        this.messageTextArea = new JTextArea();
        this.messageTextArea.setBounds(x, y, width, height);
        this.messageTextArea.setForeground(Color.black);
        this.messageTextArea.setBackground(Color.pink);
        this.messageTextArea.setOpaque(false);
        this.messageTextArea.setEditable(false);
        this.messageTextArea.setLineWrap(true);
        this.messageTextArea.setWrapStyleWord(true);
        this.messageTextArea.setFont(font);
        this.messageTextArea.setText(text);

        this.bgPanelArr[bgPanelArrIndex].add(this.messageTextArea);
    }

    public void createShopTextArea(int bgPanelArrIndex, int x, int y, int width, int height, String text, Font font){
        this.shopTextArea = new JTextArea();
        this.shopTextArea.setBounds(x, y, width, height);
        this.shopTextArea.setForeground(Color.black);
        this.shopTextArea.setBackground(Color.pink);
        this.shopTextArea.setOpaque(false);
        this.shopTextArea.setEditable(false);
        this.shopTextArea.setLineWrap(true);
        this.shopTextArea.setWrapStyleWord(true);
        this.shopTextArea.setFont(font);
        this.shopTextArea.setText(text);

        this.bgPanelArr[bgPanelArrIndex].add(this.shopTextArea);

    }


    public void createItemInfoTextArea(int bgPanelArrIndex, int x, int y, int width, int height, String text, Font font){
        this.itemInfoTextArea = new JTextArea();
        this.itemInfoTextArea.setBounds(x, y, width, height);
        this.itemInfoTextArea.setForeground(Color.black);
        this.itemInfoTextArea.setBackground(Color.pink);
        this.itemInfoTextArea.setOpaque(false);
        this.itemInfoTextArea.setEditable(false);
        this.itemInfoTextArea.setLineWrap(true);
        this.itemInfoTextArea.setWrapStyleWord(true);
        this.itemInfoTextArea.setFont(font);
        this.itemInfoTextArea.setText(text);

        this.bgPanelArr[bgPanelArrIndex].add(this.itemInfoTextArea);

    }

    public void createRegisterTextArea(int bgPanelArrIndex, int x, int y, int width, int height, String text, Font font){
        this.registerTextArea = new JTextArea();
        this.registerTextArea.setBounds(x, y, width, height);
        this.registerTextArea.setForeground(Color.black);
        this.registerTextArea.setBackground(Color.pink);
        this.registerTextArea.setOpaque(false);
        this.registerTextArea.setEditable(false);
        this.registerTextArea.setLineWrap(true);
        this.registerTextArea.setWrapStyleWord(true);
        this.registerTextArea.setFont(font);
        this.registerTextArea.setText(text);

        this.bgPanelArr[bgPanelArrIndex].add(this.registerTextArea);

    }

    public void createGameOverTextArea(int bgPanelArrIndex, int x, int y, int width, int height, String text, Font font){


        this.gameOverTextArea = new JTextArea();
        this.gameOverTextArea.setBounds(x, y, width, height);
        this.gameOverTextArea.setForeground(Color.black);
        this.gameOverTextArea.setBackground(Color.pink);
        this.gameOverTextArea.setOpaque(false);
        this.gameOverTextArea.setEditable(false);
        this.gameOverTextArea.setLineWrap(true);
        this.gameOverTextArea.setWrapStyleWord(true);
        this.gameOverTextArea.setFont(font);
        this.gameOverTextArea.setText(text);

        this.bgPanelArr[bgPanelArrIndex].add(this.gameOverTextArea);


    }


    public void showMainMenu(){
        this.bgPanelArr[0].setVisible(true);
        this.bgPanelArr[1].setVisible(false);
        this.bgPanelArr[2].setVisible(false);
        this.bgPanelArr[3].setVisible(false);
        this.bgPanelArr[4].setVisible(false);
        this.bgPanelArr[5].setVisible(false);
        this.bgPanelArr[6].setVisible(false);

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 10; j++){
                this.clickLabelArr[i][j].setVisible(false);
            }
        }
    }


    public void showMainGameScreen(){
        this.bgPanelArr[0].setVisible(false);
        this.bgPanelArr[1].setVisible(true);

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 10; j++){
                this.clickLabelArr[i][j].setVisible(true);
            }
        }


        this.bgPanelArr[2].setVisible(false);
        this.bgPanelArr[3].setVisible(false);
        this.bgPanelArr[4].setVisible(false);
        this.bgPanelArr[5].setVisible(false);
        this.bgPanelArr[6].setVisible(false);
        this.bgPanelArr[7].setVisible(false);
        this.currentScene = "MainGameScene";
        

    }

    public void showShopScreen(){
        this.bgPanelArr[0].setVisible(false);
        this.bgPanelArr[1].setVisible(true); // Farm
        this.bgPanelArr[2].setVisible(true); // SHOP UI
        this.bgPanelArr[3].setVisible(true); // Translucent Gray Background
        this.buttonArr[1].setVisible(false);
        this.imageLabelArr[4].setVisible(true); // Hide item info when shop opens

        this.currentScene = "ShopScene";
    }

    public void showGameOverUI(){
        this.bgPanelArr[0].setVisible(false);
        this.bgPanelArr[1].setVisible(true); // Farm
        this.bgPanelArr[2].setVisible(false); // SHOP UI
        this.bgPanelArr[3].setVisible(false); // Translucent Gray Background
        this.bgPanelArr[4].setVisible(false); // REGISTER UI
        this.bgPanelArr[5].setVisible(false); // Translucent Gray Background
        this.bgPanelArr[6].setVisible(true); // GAME OVER UI
        this.bgPanelArr[7].setVisible(true); // GAME OVER UI
    }

    public void exitShopScreen(){
        this.bgPanelArr[0].setVisible(false);
        this.bgPanelArr[1].setVisible(true); // Farm
        this.bgPanelArr[2].setVisible(false); // SHOP UI
        this.bgPanelArr[3].setVisible(false); // Translucent Gray Background
        this.buttonArr[1].setVisible(true);


        this.currentScene = "MainGameScene";
    }

    public void showRegisterUI(){

        this.bgPanelArr[0].setVisible(false);
        this.bgPanelArr[1].setVisible(true); // FARM UI
        this.bgPanelArr[4].setVisible(true);    // REGISTER FARMER UI
        this.bgPanelArr[5].setVisible(true); // Translucent Gray Background
        this.buttonArr[12].setVisible(false);


        this.currentScene = "Register UI Scene";


    }

    public void exitRegisterUI(){
        this.bgPanelArr[0].setVisible(false);
        this.bgPanelArr[1].setVisible(true); // FARM UI
        this.bgPanelArr[4].setVisible(false);    // REGISTER FARMER UI
        this.bgPanelArr[5].setVisible(false); // Translucent Gray Background
        this.buttonArr[12].setVisible(true);




    }







    public void generateScreen(){

        int i, x, y;
        // MAIN MENU SCREEN
        createBackground(0, 0, "/GameAssets/Backgrounds/MainMenu.png");
        createButton(0, 0, 353, 352, 93, 26, "startGame", "");
        createTextField(0, 0, 292, 305, 215, 30);


        this.bgPanelArr[0].add(this.imageLabelArr[0]);

        // GAME OVER UI
                
        createBackground(6, 10, "/GameAssets/UI/gameOverUI.png");
        createBackground(7, gray);
        createTextLabel(12, 6, 115, 78, 570, 170, "GAME OVER", gameOverFont);
        this.textLabelArr[12].setHorizontalAlignment(JTextField.CENTER);
        this.textLabelArr[12].setForeground(Color.white);
        createGameOverTextArea(6, 120, 474, 559, 96, "Testing", textAreaFont);
        createButton(26, 6, 304, 314, 171, 53, "restartGame", "");


        this.bgPanelArr[6].add(this.imageLabelArr[10]);
        this.bgPanelArr[6].setVisible(false);
        this.bgPanelArr[7].setVisible(false);


        // SHOP UI

        createBackground(2, 2, "/GameAssets/Backgrounds/SHOPUI.png");
        createBackground(3, gray);
        createButton(2, 2, 706, 41, 28, 31, "exitShop", "");
        createTextLabel(4, 2, 570, 85, 125, 24, "", farmerStatusFont); // Farmer Status Label
        createTextLabel(5, 2, 558, 128, 63, 23, "", objectCoinsFont); // Objectcoins Label
        createTextLabel(6, 2, 558, 166, 63, 23, "", levelStatusFont); // Level Status Label
        createShopTextArea(2, 205, 121, 265, 51, "Welcome to Catto Farm Shop! Here you can find the 'best' and most affordable seeds in town! Feel free to check each of what we offer.", shopAreaFont);


        createInventorySlotCount(0, 2, 260, 540, 23, 14, "0", slotCountFont);
        createInventorySlotCount(1, 2, 298, 540, 23, 14, "0", slotCountFont);
        createInventorySlotCount(2, 2, 337, 540, 23, 14, "0", slotCountFont);
        createInventorySlotCount(3, 2, 374, 540, 23, 14, "0", slotCountFont);
        createInventorySlotCount(4, 2, 412, 540, 23, 14, "0", slotCountFont);
        createInventorySlotCount(5, 2, 451, 540, 23, 14, "0", slotCountFont);
        createInventorySlotCount(6, 2, 488, 540, 23, 14, "0", slotCountFont);
        createInventorySlotCount(7, 2,527, 540, 23, 14, "0", slotCountFont);

        // ITEM INFO 
        createTextLabel(7, 2, 561, 258, 137, 17, "", levelStatusFont); // Item Title Label
        createTextLabel(8, 2, 561, 276, 137, 17, "", slotCountFont); // Crop Type Label
        this.textLabelArr[8].setForeground(Color.magenta);

        createButton(11, 2,  600, 422, 57, 24, "buyCrop", "");
        createTextField(1, 2, 644, 407, 53, 14);
        this.tfArray[1].setFont(itemInfoAreaFont);
        this.tfArray[1].setHorizontalAlignment(JTextField.LEFT);
        createItemInfoTextArea(2, 558, 311, 143, 110, "", itemInfoAreaFont);
        createObjectImage(5, 2, 600, 422, 57, 24, "/GameAssets/Buttons/itemInfoButton.png", false); 
        createObjectImage(4, 2, 532, 227, 191, 252, "/GameAssets/UI/itemInfo.png", false); 



        this.textLabelArr[7].setVisible(false); 
        this.textLabelArr[8].setVisible(false);
        this.buttonArr[11].setVisible(false);
        this.tfArray[1].setVisible(false);
        this.itemInfoTextArea.setVisible(false);
        this.imageLabelArr[4].setVisible(false);
        this.imageLabelArr[5].setVisible(false);


        createButton(3, 2, 91, 238, 201, 37, "displayTurnip", "");  
        createButton(4, 2, 91, 282, 201, 37, "displayPotato", "");  
        createButton(5, 2, 91, 326, 201, 37, "displayTulips", "");  
        createButton(6, 2, 91, 370, 201, 37, "displayMango", "");  

        createButton(7, 2, 297, 238, 201, 37, "displayCarrot", "");  
        createButton(8, 2, 297, 282, 201, 37, "displayRose", "");  
        createButton(9, 2, 297, 326, 201, 37, "displaySunflower", "");  
        createButton(10, 2, 297, 370, 201, 37, "displayApple", "");  

        




        this.bgPanelArr[2].add(this.imageLabelArr[2]);
        this.bgPanelArr[2].setVisible(false);
        this.bgPanelArr[3].setVisible(false);


        // REGISTER FARMER UI



        createBackground(4, 6, "/GameAssets/UI/registerFarmerUI.png");
        createBackground(5, gray);
        createButton(13, 4, 706, 41, 30, 31, "exitRegisterUI", "");
        createTextLabel(9, 4, 158, 50, 130, 26, "Test", farmerStatusFont); // Farmer Status Label
        createTextLabel(10, 4, 341, 52, 62, 22, "69", objectCoinsFont); // Objectcoins Label
        createTextLabel(11, 4, 455, 53, 62, 22, "5", levelStatusFont); // Level Status Label

        createButton(14, 4, 567, 174, 64, 20, "registerRF", "");
        createButton(15, 4, 567, 268, 65, 20, "registerLF", "");
        this.buttonArr[15].setVisible(false);
        createButton(16, 4, 294, 268, 65, 20, "registerDF", "");
        this.buttonArr[16].setVisible(false);
        
        createObjectImage(7, 4, 293, 268, 64, 20, "/GameAssets/Buttons/lockedButton.png", false); // Image button of distinguished farmer
        createObjectImage(8, 4, 567, 268, 64, 20, "/GameAssets/Buttons/lockedButton.png", false); // Image button of legendary farmer
        createObjectImage(9, 4, 567, 174, 64, 20, "/GameAssets/Buttons/registerButton.png", false);// Image button of registered farmer

        createRegisterTextArea(4, 193, 345, 394, 76, "Welcome to the Catto FarmLands Registration Center! You may choose to register yourself to different statuses to aid you in your farming!", textAreaFont);
        this.bgPanelArr[4].add(this.imageLabelArr[6]);
        this.bgPanelArr[4].setVisible(false);
        this.bgPanelArr[5].setVisible(false);










        // MAIN GAME SCREEN 

        createBackground(1, 1, "/GameAssets/Backgrounds/startingGame2.png");
        createTextLabel(0, 1, 104, 14, 165, 50, "", usernameFont); //Username text Label
        createTextLabel(1, 1, 135, 52, 135, 50, "", farmerStatusFont); //Farmer status text label
        createTextLabel(2, 1, 321, 26, 67, 24, "", objectCoinsFont); //Money status text label
        createTextLabel(3, 1, 447, 26,65 , 26, "", levelStatusFont); //Level status text label

        
        createInventorySlotCount(8, 1, 273, 446, 21, 17, "", slotCountFont);
        createInventorySlotCount(9, 1, 311, 446, 21, 17, "", slotCountFont);
        createInventorySlotCount(10, 1, 350, 446, 21, 17, "", slotCountFont);
        createInventorySlotCount(11, 1, 388, 446, 21, 17, "", slotCountFont);
        createInventorySlotCount(12, 1, 425, 446, 21, 17, "", slotCountFont);
        createInventorySlotCount(13, 1, 462, 446, 21, 17, "", slotCountFont);
        createInventorySlotCount(14, 1, 500, 446, 21, 17, "", slotCountFont);
        createInventorySlotCount(15, 1, 538, 446, 21, 17, "", slotCountFont);

        createButton(1, 1, 18, 100, 32, 32, "goShop", ""); // Shop button
        createButton(12, 1, 58, 100, 32, 32, "goRegister", ""); // Register Farmer Button
        createButton(17, 1, 98, 100, 32, 32, "advanceDay", ""); // Register Farmer Button

        createTextArea(1, 120, 474, 559, 96, "Henloo human. This is the start of your farming journey in this farm lot!. Since you've had quite a handy experience in farming, I guess I don't need to show you around the basics. Good luck on your farming endeavor!", textAreaFont);


        createButton(18, 1, 251, 423, 32, 32, "chooseTurnip", "");
        createButton(19, 1, 289, 423, 32, 32, "chooseCarrot", "");
        createButton(20, 1, 327, 423, 32, 32, "choosePotato", "");
        createButton(21, 1, 365, 423, 32, 32, "chooseRose", "");
        createButton(22, 1, 403, 423, 32, 32, "chooseTulips", "");
        createButton(23, 1, 441, 423, 32, 32, "chooseSunflower", "");
        createButton(24, 1, 479, 423, 32, 32, "chooseMango", "");
        createButton(25, 1, 516, 423, 32, 32, "chooseApple", "");


        createObjectImage(3, 1, 162, 117, 493, 274, "/GameAssets/Fence/fence.png", false);

        

        // INITIATE CLICK LISTENERS


        y = 141;
        for (i = 0; i < 5; i++){

            x = 168;
            for (int j = 0; j < 10; j++){

                createClickLabels(i, j, i, j, 1, x, y, 48, 48, false, "Plow", "Plant", "Water", "Fertilize", "Harvest", "Use Shovel", "Use Pickaxe", "plowTile", "plantCrop", "water", "fertilize", "harvestCrop", "useShovel", "usePickaxe" );
                x += 48;
            }

            y += 48;

        }


        // INITIATE BLANK CROP IMAGES

        y = 141;
        for (i = 0; i < 5; i++){

            x = 168;
            for (int j = 0; j < 10; j++){

                createCropImage(i, j, 1, x, y, 48, 48, "/GameAssets/Crops/blank.png", false);
                x += 48;
            }

            y += 48;

        }




        // INITIATE 5X10 TILES

        y = 141;
        for (i = 0; i < 5; i++){

            x = 168;
            for (int j = 0; j < 10; j++){

                createFarmTile(i, j, 1, x, y, 48, 48, "/GameAssets/Tilesets/unplowed.png");
                x += 48;
            }

            y += 48;

        }




        this.bgPanelArr[1].add(this.imageLabelArr[1]);
        this.bgPanelArr[1].setVisible(false);






    }


    // SETTERS




    public void setTextLabelString(int textLabelArrIndex, String text){
        this.textLabelArr[textLabelArrIndex].setText(text);
    }

    public void setMainTextArea(String text){
        this.messageTextArea.setText(text);
    }

    public void setGameOverTextArea(String text){
        this.gameOverTextArea.setText(text);
    }

    public void setItemInfoTextArea(String text){
        this.itemInfoTextArea.setText(text);
    }

    public void setSlotCountLabel(int slotCountArrIndex, String text){
        this.slotCountArr[slotCountArrIndex].setText(text);
    }

    public void setShopTextArea(String text){
        this.shopTextArea.setText(text);
    }

    public void setRegisterTextArea(String text){
        this.registerTextArea.setText(text);
    }

    // VISIBLITY SETTERS

    public void setTextLabelVisibility(int textLabelArrIndex, boolean param){
        this.textLabelArr[textLabelArrIndex].setVisible(param);
    }

    public void setItemInfoTextAreaVisiblity(boolean param){
        this.itemInfoTextArea.setVisible(param);
    }

    public void setImageLabelVisibility(int imageLabelArrIndex, boolean param){
        this.imageLabelArr[imageLabelArrIndex].setVisible(param);
    }

    public void setButtonVisibility(int buttonArrIndex, boolean param){
        this.buttonArr[buttonArrIndex].setVisible(param);
    }

    public void setTextFieldVisibility(int tfArrayIndex, boolean param){
        this.tfArray[tfArrayIndex].setVisible(param);
    }

    public void setClickLabelArrVisibility(boolean param){

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 10; j++){
                this.clickLabelArr[i][j].setVisible(param);
            }
        }

    }

    // ACTION LISTENER SETTERS

    public void setupPopActionHandlerListener(ActionListener actionListener, int menuXIndex, int menuYIndex, int numChoices){

        for (int i = 0; i < numChoices; i++){
            this.menuItem[menuXIndex][menuYIndex][i].addActionListener(actionListener);
        }
    

    }   

    public void setButtonListener(int buttonArrIndex, ActionListener actionListener){
        this.buttonArr[buttonArrIndex].addActionListener(actionListener);
    }


    // IMAGE SETTERS

    public void setObjectImage(int imageArrIndex, int width, int height, String filename){

        ImageIcon objectIcon = new ImageIcon(getClass().getResource(filename));

        Image objectImage = objectIcon.getImage();
        Image modifiedObjectImage = objectImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

        objectIcon.setImage(modifiedObjectImage);

        this.imageLabelArr[imageArrIndex].setIcon(objectIcon);


    }

    public void setFarmTileImage(int row, int column, int width, int height, String filename){


        ImageIcon objectIcon = new ImageIcon(getClass().getResource(filename));

        Image objectImage = objectIcon.getImage();
        Image modifiedObjectImage = objectImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

        objectIcon.setImage(modifiedObjectImage);

        this.tileImageArr[row][column].setIcon(objectIcon);


    }

    public void setCropImage(int row, int column, int width, int height, String filename){


        ImageIcon objectIcon = new ImageIcon(getClass().getResource(filename));

        Image objectImage = objectIcon.getImage();
        Image modifiedObjectImage = objectImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

        objectIcon.setImage(modifiedObjectImage);

        this.cropImageArr[row][column].setIcon(objectIcon);

    }


    // GETTERS

    public String getTfText(int tfArrayIndex) {
		return this.tfArray[tfArrayIndex].getText();
	}


    public int getQuantity(int tfArrayIndex){
        return Integer.parseInt(this.tfArray[tfArrayIndex].getText());
    }

    public int getRow(){
        return row;
    }
    
    public int getColumn(){
        return column;
    }

    public JTextArea getMainTextArea(){
        return messageTextArea;
    }




    // Display Message Window

    
    public void displayErrorMessage(String errorMessage){

        JOptionPane.showMessageDialog(this.window, errorMessage);


    }



}
