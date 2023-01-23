// Program Name: GameView
// Last Modified: January 22, 2023
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Creates the GUI for the game

// Imports
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameView extends JPanel {

    // Creates instance variables
    private GameModel gameModel; // Instance of model
    private TitleModel titleModel; // Instance of title model
    private MusicPlayer mPlayer; // Instance of music player
    private VolumeController volumeController; // Instance of volume controller

    // Creates the JSlider for sound volume and keybind text fields
    private JSlider volume = new JSlider(JSlider.HORIZONTAL,-20, 6,0);
    private JTextField cFowardKeybind = new JTextField("W");
    private JTextField cBackwardsKeybind = new JTextField("S");
    private JTextField cRightKeybind = new JTextField("D");
    private JTextField cLeftKeybind = new JTextField("A");

    // JLabels and JButtons displayed on options
    private JLabel fowardKeybind = new JLabel("Foward Keybind");
    private JLabel backwardsKeybind = new JLabel("Backward Keybind");
    private JLabel rightKeybind = new JLabel("Right Keybind");
    private JLabel leftKeybind = new JLabel("Left Keybind"); 
    private JLabel volumeLabel = new JLabel("Volume"); 
    private JButton quitGame = new JButton("Quit Game");
    private JButton exitOptions = new JButton("Exit");

    // Label and panel for the options menu
    private JLabel optionsTitle = new JLabel("Options");
    private JPanel optionsPanel = new JPanel();

    private JLabel backGround = new JLabel(); // Sets the background of the game

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // getting the screen sice of the users device

    private JLabel playerHealth = new JLabel();  // bar that diplays player health
    private JLabel monsterHealth = new JLabel(); // bar that displays monster health

    private JButton buttons[] = new JButton[18]; // array that contains all the wuicktinme buttons
    private JPanel quicktimeButtonPanel = new JPanel(); // contains all the quicktime buttons

    private JLabel floorLevel = new JLabel(); // displays the floor you are on 

    private JButton defend = new JButton("Defend"); // Button that allows you to defend

    private String directory = System.getProperty("user.dir"); // Gets directory

    private JLabel options = new JLabel("",SwingConstants.CENTER); // Tells user what they can do

    // Game over screen
    private JPanel gameOverPanel = new JPanel();
    private JLabel wonOrNot = new JLabel();
    private JLabel gameModeStat = new JLabel();
    private JLabel numOfKeysStat = new JLabel();
    private JLabel healthStat = new JLabel();
    private JLabel smokeBombStat = new JLabel();
    private JButton exitGameOver = new JButton("Quit to Title Screen");
    private JLabel gameOverBG = new JLabel();

    // Label and JButton for displaying and using smoke bombs
    private JLabel smokeBombs = new JLabel("");
    private JButton useButton = new JButton("Use");

    // Constructor
    public GameView(GameModel gameModel,TitleModel titleModel) {
        this.gameModel = gameModel;
        this.titleModel = titleModel;
        this.mPlayer = new MusicPlayer();
        this.gameModel.setGUI(this);
        this.layoutView();
        this.registerControllers();
        this.update();
    }

    // Creates the initial layout of the GUI
    public void layoutView() {

        this.setLayout(null); // settoing the layout to null

        // Gets a font
        File fontFile = new File(directory + "\\src\\res\\HelpMe.ttf"); // Version for VS
        // File fontFile = new File(directory + "\\res\\HelpMe.ttf");

        // getting the width and height of user diplay
        int width = (int)this.screenSize.getWidth(); 
        int height = (int)this.screenSize.getHeight();

        this.setBounds(0,0,width,height); // setting the size of the game

        // Sets up defend button
        defend.setBounds((int)Math.round(width*0.416), (int)Math.round(height*0.944), (int)Math.round(width*0.182), (int)Math.round(height*0.046)); // settng the size for the defend buttom
        defend.setBackground(new Color(204, 204, 204));
        defend.setVisible(false);

        // setting the colour of the backround
        backGround.setBounds(0,0,width,height);
        backGround.setBackground(Color.BLACK);
        backGround.setOpaque(true);

        // Sets up the player's and monster's health bar        
        playerHealth.setBounds((int)Math.round(width*0.005),(int)Math.round(height*0.944),(int)Math.round(width*0.312),(int)Math.round(height*0.046)); // setting the location of player health
        playerHealth.setBackground(Color.RED); // setting colour of player health
        playerHealth.setOpaque(true);

        monsterHealth.setBounds((int)Math.round(width*0.682),(int)Math.round(height*0.944),(int)Math.round(width*0.312),(int)Math.round(height*0.046)); // setting the location of monster health
        monsterHealth.setBackground(new Color(170, 34, 34)); // setting colour of monster health
        monsterHealth.setOpaque(true);

        // Sets up the qte buttons
        quicktimeButtonPanel.setBounds(0,0,width,height);
        addButtons(); // gneratting and adding quicktime buttons
        quicktimeButtonPanel.setVisible(false);
        quicktimeButtonPanel.setOpaque(false);

        // The text to display the floor level
        floorLevel.setText("");
        floorLevel.setBounds((int)Math.round(width*0.9),(int)Math.round(height*0.006),(int)Math.round(width*0.2),(int)Math.round(height*0.083));
        floorLevel.setForeground(new Color(255,255,255));

        try {
            // setting font and scaling the font
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            // Font sizedFont = font.deriveFont(width*0.052f); // original size 100
            Font sizedFont = font.deriveFont(width*0.018f); // original size 100
            floorLevel.setFont(sizedFont);
        } 
        catch (Exception e) {}

        // Displays the number of smoke Bombs
        smokeBombs.setText("");
        smokeBombs.setBounds((int)Math.round(width*0.82),(int)Math.round(height*0.06),(int)Math.round(width*0.27),(int)Math.round(height*0.083));
        smokeBombs.setForeground(new Color(255,255,255));

        // Button to use a smoke bomb
        useButton.setBounds((int)Math.round(width*0.43),(int)Math.round(height*0.2),(int)Math.round(width*0.182), (int)Math.round(height*0.046));
        useButton.setBackground(new Color(204, 204, 204));
        useButton.setVisible(false);

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font sizedFont = font.deriveFont(width*0.018f);
            smokeBombs.setFont(sizedFont);
        } catch (Exception e) {}

        // Text for user instructrions
        options.setBounds((int)Math.round(width*0.343),(int)Math.round(height*0.009),(int)Math.round(width*0.364),(int)Math.round(height*0.185));
        options.setFont(new Font("Helvetica", Font.PLAIN, 25));
        options.setForeground(Color.white);

        // Settings menu
        optionsPanel.setLayout(null);
        optionsPanel.setBounds(0,0,width,height);
        optionsPanel.setBackground(new Color(20,20,20,20));

        optionsTitle.setBounds((int)Math.round(width*0.390),(int)Math.round(height*0.092),(int)Math.round(width*0.625),(int)Math.round(height*0.092)); // setting scaling for Options title
        optionsTitle.setForeground(new Color(139, 0, 0));

        try {
            // setting font and scaling the font
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font sizedFont = font.deriveFont(width*0.052f); // original size 100
            optionsTitle.setFont(sizedFont);

            Font font1 = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font LableSizedFont = font1.deriveFont(width*0.015f); // original size 30

            fowardKeybind.setFont(LableSizedFont);
            backwardsKeybind.setFont(LableSizedFont);
            leftKeybind.setFont(LableSizedFont);
            rightKeybind.setFont(LableSizedFont);
            volumeLabel.setFont(LableSizedFont);
            exitOptions.setFont(LableSizedFont);
            quitGame.setFont(LableSizedFont);

            Font font2 = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font volumeFont = font2.deriveFont(width*0.007f);

            volume.setFont(volumeFont);
        } 
        catch (Exception e) {}

        // Sets up the key binds
        cFowardKeybind.setBounds((int)Math.round(width*0.677), (int) Math.round(height*0.277), (int)Math.round(width*0.052), (int) Math.round(height*0.046));
        fowardKeybind.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.259), (int)Math.round(width*0.208), (int) Math.round(height*0.092));
        fowardKeybind.setForeground(new Color(139, 0, 0));
        cFowardKeybind.setText(gameModel.forwardKeyBind);

        cBackwardsKeybind.setBounds((int)Math.round(width*0.677), (int) Math.round(height*0.370), (int)Math.round(width*0.052), (int) Math.round(height*0.046));
        backwardsKeybind.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.351), (int)Math.round(width*0.208), (int) Math.round(height*0.092));
        backwardsKeybind.setForeground(new Color(139, 0, 0));
        cBackwardsKeybind.setText(gameModel.backwardsKeyBind);

        cLeftKeybind.setBounds((int)Math.round(width*0.677), (int) Math.round(height*0.462), (int)Math.round(width*0.052), (int) Math.round(height*0.046));
        leftKeybind.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.444), (int)Math.round(width*0.208), (int) Math.round(height*0.092));
        leftKeybind.setForeground(new Color(139, 0, 0));
        cLeftKeybind.setText(gameModel.leftKeyBind);

        cRightKeybind.setBounds((int)Math.round(width*0.677), (int) Math.round(height*0.555), (int)Math.round(width*0.052), (int) Math.round(height*0.046));
        rightKeybind.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.537), (int)Math.round(width*0.208), (int) Math.round(height*0.092));
        rightKeybind.setForeground(new Color(139, 0, 0));
        cRightKeybind.setText(gameModel.rightKeyBind);

        // Sets up the volume JSlider
        volumeLabel.setBounds((int)Math.round(width*0.463), (int) Math.round(height*0.629), (int)Math.round(width*0.208), (int) Math.round(height*0.046));
        volumeLabel.setForeground(new Color(139, 0, 0));
        volume.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.722), (int)Math.round(width*0.468), (int) Math.round(height*0.046));
        volume.setMajorTickSpacing(2);
        volume.setMinorTickSpacing(1);
        volume.setPaintTicks(true);
        volume.setPaintLabels(true);
        volume.setForeground(new Color(139, 0, 0));

        // Sets up exit and quit game buttons
        exitOptions.setBounds((int)Math.round(width*0.005),(int) Math.round(height*0.009),(int)Math.round(width*0.078),(int) Math.round(height*0.084));
        exitOptions.setForeground(new Color(139, 0, 0));

        quitGame.setBounds((int)Math.round(width*0.005),(int) Math.round(height*0.101),(int)Math.round(width*0.156),(int) Math.round(height*0.084));
        quitGame.setForeground(new Color(139, 0, 0));

        fowardKeybind.setText("Foward Keybind");
        backwardsKeybind.setText("Backward Keybind");

        // Adds everything to the optionsPanel and makes it not visible
        optionsPanel.add(optionsTitle);
        optionsPanel.add(cFowardKeybind);
        optionsPanel.add(fowardKeybind);
        optionsPanel.add(cBackwardsKeybind);
        optionsPanel.add(backwardsKeybind);
        optionsPanel.add(cLeftKeybind);
        optionsPanel.add(leftKeybind);
        optionsPanel.add(cRightKeybind);
        optionsPanel.add(rightKeybind);
        optionsPanel.add(volume);
        optionsPanel.add(volumeLabel);
        optionsPanel.add(exitOptions);
        optionsPanel.add(quitGame);

        optionsPanel.setVisible(false);

        // Game over screen
        wonOrNot.setBounds(660,0,900,500);
        wonOrNot.setForeground(new Color(139, 0, 0));
        wonOrNot.setSize(screenSize);
        try {
            // setting font and scaling the font
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            // Font sizedFont = font.deriveFont(width*0.052f); // original size 100
            Font sizedFont = font.deriveFont(width*0.06f); // original size 100
            wonOrNot.setFont(sizedFont);
        } 
        catch (Exception e) {}

        // The different statistics from the game
        gameModeStat.setFont(new Font("Helvetica", Font.PLAIN, 20));
        gameModeStat.setBounds((int)Math.round(width*0.234),(int)Math.round(height*0.4),(int)Math.round(width*0.625),(int)Math.round(height*0.092));
        gameModeStat.setText("Game Mode: " + gameModel.gameMode);
        
        numOfKeysStat.setFont(new Font("Helvetica", Font.PLAIN, 20));
        numOfKeysStat.setBounds((int)Math.round(width*0.234),(int)Math.round(height*0.44),(int)Math.round(width*0.625),(int)Math.round(height*0.092));
        numOfKeysStat.setText("Number of Keys: " + gameModel.numOfKeys);

        healthStat.setFont(new Font("Helvetica", Font.PLAIN, 20));
        healthStat.setBounds((int)Math.round(width*0.234),(int)Math.round(height*0.48),(int)Math.round(width*0.625),(int)Math.round(height*0.092));
        healthStat.setText("Your Health: " + gameModel.health);

        smokeBombStat.setFont(new Font("Helvetica", Font.PLAIN, 20));
        smokeBombStat.setBounds((int)Math.round(width*0.234),(int)Math.round(height*0.52),(int)Math.round(width*0.625),(int)Math.round(height*0.092));
        smokeBombStat.setText("Number of Smoke Bombs: " + gameModel.smokeBombs);

        gameModeStat.setForeground(new Color(255, 255, 255));
        numOfKeysStat.setForeground(new Color(255, 255, 255));
        healthStat.setForeground(new Color(255, 255, 255));
        smokeBombStat.setForeground(new Color(255, 255, 255));

        // Sets up the button to exit back to title screen
        exitGameOver.setBounds((int)Math.round(width*0.234),(int)Math.round(height*0.8),(int)Math.round(width*0.625),(int)Math.round(height*0.092));

        gameOverBG.setBounds(0,0,width,height);
        gameOverBG.setBackground(Color.BLACK);
        gameOverBG.setOpaque(true);

        // Adds everything to the gameOverPanel and sets it to not visible
        gameOverPanel.setLayout(null);
        gameOverPanel.setBounds(0, 0, width, height);
        gameOverPanel.add(wonOrNot);
        gameOverPanel.add(gameModeStat);
        gameOverPanel.add(numOfKeysStat);
        gameOverPanel.add(healthStat);
        gameOverPanel.add(smokeBombStat);
        gameOverPanel.add(exitGameOver);
        gameOverPanel.add(gameOverBG);
        gameOverPanel.setVisible(false);

        // adding objects to the game
        this.add(playerHealth);
        this.add(defend);
        this.add(monsterHealth);
        this.add(quicktimeButtonPanel);
        this.add(floorLevel);
        this.add(smokeBombs);
        this.add(options);
        this.add(optionsPanel);
        this.add(useButton);
        this.add(gameOverPanel);
        this.add(backGround);

        update();
    }// end of game view

    // generates buttons in an array
    public void generateButtons(){
        for(int i = 0; i<18;i++ ){
            buttons[i] = new JButton(String.valueOf(i));
            buttons[i].setBackground(new Color(230, 230, 230));
            buttons[i].setVisible(false);
        }
    }

    public void defaultLocations(){
        for(int i = 0;i<18;i++){
            buttons[i].setBounds(0,0,100,100);
        }
    }

    // setting the quicktime location for each button
    public void randomLocations(){
        int height = (int)this.screenSize.getHeight();
        int width  = gameModel.quickTimeGeneration();
        for(int i = 0;i<gameModel.numOfButtons;i++){
            buttons[i].setBounds(width*i,gameModel.getRandomHeight(),width,(int) Math.round(height*0.092));
            buttons[i].setVisible(true);
        }
    }

    // adds buttons to the quicktime panel
    public void addButtons(){
        generateButtons();
        defaultLocations();
        quicktimeButtonPanel.setLayout(null);
        for(int i = 0; i<18;i++ ){
            quicktimeButtonPanel.add(buttons[i]);
        }
    }

    // Adds action listener for the array of buttons
    public void addActionForArray(){
        for(int i = 0;i<18;i++){
            buttons[i].addActionListener(new ButtonGameController(gameModel,titleModel, buttons[i]));
        }
    }

    // Registers controllers
    private void registerControllers() {
        ButtonGameController bc = new ButtonGameController(this.gameModel, titleModel,defend);
        defend.addActionListener(bc);

        KeyboardInput keyboardInput = new KeyboardInput(this.gameModel);
        this.addKeyListener(keyboardInput);
        this.setFocusable(true);

        ButtonGameController exitButtonGameController = new ButtonGameController(this.gameModel,titleModel,exitOptions);
        exitOptions.addActionListener(exitButtonGameController);

        ButtonGameController quitButtonGameController = new ButtonGameController(this.gameModel,titleModel,quitGame);
        quitGame.addActionListener(quitButtonGameController);

        ButtonGameController useGameController = new ButtonGameController(gameModel, titleModel, useButton);
        useButton.addActionListener(useGameController);
        
        addActionForArray();

        TextFieldController textFieldController = new TextFieldController(gameModel, cFowardKeybind, cBackwardsKeybind, cLeftKeybind, cRightKeybind);
        cFowardKeybind.addActionListener(textFieldController);
        cBackwardsKeybind.addActionListener(textFieldController);
        cLeftKeybind.addActionListener(textFieldController);
        cRightKeybind.addActionListener(textFieldController);

        volumeController = new VolumeController(mPlayer, volume);
        volume.addChangeListener(volumeController);

        ButtonGameController exitGameOverCont = new ButtonGameController(gameModel, titleModel, exitGameOver);
        exitGameOver.addActionListener(exitGameOverCont);

    }

    // Updates the GUI based on what happens in the game
    public void update() {

        // Gets screen dimensions
        int width = (int)this.screenSize.getWidth(); 
        int height = (int)this.screenSize.getHeight();

        // If the game should start
        if (titleModel.startGame && gameModel.once == true) {
            gameModel.once = false;
            gameModel.game();
        }

        // Check to see if the user wants to use a smoke bomb
        if (gameModel.wantToUseSmokeBomb) {
            useButton.setVisible(true);
        } else {
            useButton.setVisible(false);
        }

        // Generates the random locations for the buttons
        if (gameModel.randomize){
            randomLocations();
        }

        // If the quick time state is activated, it sets the panel to visible
        quicktimeButtonPanel.setVisible(gameModel.quickTimeState);

        // If the quick time state is activated, sets the array of buttons to visible
        if(gameModel.quickTimeState){
            for(int i = 0; i<gameModel.numOfButtons;i++){
                buttons[i].setVisible(gameModel.buttonVisible[i]);   
            }
        }

        // Displays the defend button
        defend.setVisible(gameModel.displayDefendButton);

        // Displays the directions the user can move in
        if (gameModel.displayDirections){
            options.setText(gameModel.outputDirections);
            options.setVisible(true);
        }

        // Displays the options panel
        if (gameModel.displayOptionsPanel){
            optionsPanel.setVisible(true);
            defend.setVisible(false);
            quicktimeButtonPanel.setVisible(false);
            playerHealth.setVisible(false);
            monsterHealth.setVisible(false);
            options.setVisible(false);
        } else {
            playerHealth.setVisible(true);
            monsterHealth.setVisible(true);
            options.setVisible(true);
            optionsPanel.setVisible(false);

        }        

        // Sets the text for the floor level and the number of smoke bombs
        floorLevel.setText("Floor: " + Integer.toString(gameModel.numOfKeys + 1));
        smokeBombs.setText("Smoke Bombs: " + Integer.toString(gameModel.smokeBombs));

        // Changes the health bar width for the monster depending on the total health
        int healthBarWidth = 0;
        if (gameModel.numOfKeys == 0) {
            healthBarWidth = (int)Math.round((width*0.312)*(gameModel.monsterHealth/100));
        } else if (gameModel.numOfKeys == 1) {
            healthBarWidth = (int)Math.round((width*0.312)*(gameModel.monsterHealth/125));
        } else if (gameModel.numOfKeys == 2) {
            healthBarWidth = (int)Math.round((width*0.312)*(gameModel.monsterHealth/150));
        } else if (gameModel.numOfKeys == 3) {
            healthBarWidth = (int)Math.round((width*0.312)*(gameModel.monsterHealth/175));
        } else if (gameModel.numOfKeys == 4) {
            healthBarWidth = (int)Math.round((width*0.312)*(gameModel.monsterHealth/200));
        }

        // Updates the monster's and player's health bars
        monsterHealth.setBounds((int)Math.round(width-(width*0.007))-healthBarWidth, (int)Math.round(height*0.944), healthBarWidth, (int)Math.round(height*0.046));

        playerHealth.setBounds((int)Math.round(width*0.005),(int)Math.round(height*0.944),(int)Math.round((width*0.312)*(gameModel.health/100)),(int)Math.round(height*0.046));

        // Sets the text for the keybind JTextFields
        cFowardKeybind.setText(gameModel.forwardKeyBind);
        cBackwardsKeybind.setText(gameModel.backwardsKeyBind);
        cLeftKeybind.setText(gameModel.leftKeyBind);
        cRightKeybind.setText(gameModel.rightKeyBind);

        // Game over screen
        if (gameModel.gameOver) {
            if (!gameModel.gameMode.equals(null)) {

                gameModel.canEscape = false;

                if (gameModel.playerDied) {
                    wonOrNot.setText("You Died");
                } else {
                    wonOrNot.setText("You Escaped");
                }

                playerHealth.setVisible(false);
                monsterHealth.setVisible(false);
                options.setVisible(false);
                floorLevel.setVisible(false);
                smokeBombs.setVisible(false);

                gameOverPanel.setVisible(true);
            }
        }
        else{
            gameOverPanel.setVisible(false);
        }

    } // Closes update
    
} // Closes class