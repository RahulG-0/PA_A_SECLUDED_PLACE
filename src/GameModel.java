// Program Name: GameModel
// Last Modified: January 22, 2023
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Handles the background processing for the game

// Imports
import java.awt.*;
import java.io.*;

public class GameModel {

    // Creates instance variables
    private GameView view; // Instance of GameView
    private TotalModel totalModel; // Instance of TotalModel
    public MusicPlayer mPlayer = new MusicPlayer(); // Instance of MusicPlayer
    public String userSelection = ""; // Registers which button a user presses
    public String gameMode; // Information for the game
    public int numOfKeys = 0;
    public double health = 100;
    public int smokeBombs = 0;
    private final int monsterInitHealth = 100;
    public double monsterHealth = monsterInitHealth;

    private String whichDirection; // What keyboard input the player gives

    public String monstAttackDirection = ""; // Gets the direction the monster attacks from
    public boolean defendSuccessful = true; // If the player was able to defend or not
    public boolean wantToUseSmokeBomb = false; // Flag to display smoke bomb option
    public boolean useSmokeBomb = false; // Boolean for if the user wants to use a smoke bomb

    public boolean displayDefendButton = false; // Displays the defend button if true
    public boolean defendButton = false; // If the player clicked the defend button

    // Booleans for which directions the user can travel
    private boolean canMoveForward = false;
    private boolean canMoveRight = false;
    private boolean canMoveLeft = false;
    public String outputDirections = "";
    public boolean displayDirections = false;

    // Keybinds
    public String forwardKeyBind = "W";
    public String rightKeyBind = "D";
    public String backwardsKeyBind = "S";
    public String leftKeyBind = "A";

    // Flag to activate the quick time buttons
    public boolean quickTimeState = false;
    public int numOfButtons = 0; // Number of buttons for the quick time generation

    // Flags to check if the player or the monster have died
    public boolean playerDied = false;
    public boolean monsterDied = false;

    // Switching between going a direction and getting direction
    private boolean  flip = false;
    private boolean flipV2 = false;

    // Displays the options panel
    public boolean displayOptionsPanel = false;

    // These are for the qte
    public boolean randomize = false;
    public int amountClicked = 0; // Counts the amount of buttons clicked
    public boolean[] buttonVisible = new boolean[18];

    // A boolean to check if the player can click escape key
    public boolean canEscape = true;

    // Checks if the game is over or if the user has left the game
    public boolean gameOver = false;
    public boolean leftGame = false;

    public String directory = System.getProperty("user.dir"); // Gets the user's directory
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Gets screensize

    public boolean once = true;

    Thread thread;

    // Constructor
    public GameModel() {
        super();
    }

    // Sets instance variables to information passed in via the file or by starting a new game
    public void setInfo(String gameMode, int numOfKeys, double health, int smokeBombs, double monsterHealth) {
        this.gameMode = gameMode;
        this.numOfKeys = numOfKeys;
        this.health = health;
        this.smokeBombs = smokeBombs;
        this.monsterHealth = monsterHealth;
    }

    public void addTotalModel(TotalModel totalModel){
        this.totalModel = totalModel;
    }

    // Getter methods for the information
    public String getGameMode() {
        return (this.gameMode);
    }

    public int getNumOfKeys() {
        return(this.numOfKeys);
    }

    public double getHealth() {
        return(this.health);
    }

    public int getSmokeBombs() {
        return(this.smokeBombs);
    }

    // Sets the current layout of the GUI
    public void setGUI(GameView currentView) {
        this.view = currentView;
    }

    // Sets keybinds
    public void setKeyBind(String keyBindName, char keyBind) {
        if (keyBindName.equals("FORWARD")) {
            this.forwardKeyBind = Character.toString(keyBind).toUpperCase();
        } else if (keyBindName.equals("BACKWARDS")) {
            this.backwardsKeyBind = Character.toString(keyBind).toUpperCase();
        } else if (keyBindName.equals("RIGHT")) {
            this.rightKeyBind = Character.toString(keyBind).toUpperCase();
        } else if (keyBindName.equals("LEFT")) {
            this.leftKeyBind = Character.toString(keyBind).toUpperCase();
        }
    }

    // Sets the user's direction
    public void setUserDirection(String direction){
        this.whichDirection = direction;
    }

    // Returns a PrintWriter used to write in the two text files
    public PrintWriter getPrintWriter(File file) {
        PrintWriter output = null;
        try {
            output = new PrintWriter(file);
        } catch (FileNotFoundException e) {}

        return(output);
    }

    // Checks if the user can move in a direction
    public boolean canMoveInDirection() {
        double x = Math.random();
        if (x > 0.5) {
            return(true);
        } else {
            return(false);
        }
    }
 
    // Spawns the monster at random
    public boolean doesMonsterSpawn() {
        double x = Math.random();
        if (x > 0.4) {
            return(true);
        } else {
            return(false);
        }
    }

    // Sets the monster's attack direction
    public void monsterAttackDirection() {
        long monstDirection = Math.round((Math.random() * 3) + 1);

        if (monstDirection == 1) {
            monstAttackDirection = "FORWARD";
        } else if (monstDirection == 2) {
            monstAttackDirection = "RIGHT";
        } else if (monstDirection == 3) {
            monstAttackDirection = "BACKWARD";
        } else if (monstDirection == 4) {
            monstAttackDirection = "LEFT";
        }
    }

    // Checks if the game is over
    public void isGameOver() {
        if (gameMode == "DEMO" && numOfKeys == 1) {
            gameOver = true;
        } else if (gameMode == "EASY" && numOfKeys == 3) {
            gameOver = true;
        } else if (gameMode == "MEDIUM" && numOfKeys == 4) {
            gameOver = true;
        } else if (gameMode == "HARD" && numOfKeys == 5) {
            gameOver = true;
        } else if (health <= 0) {
            gameOver = true;
            playerDied = true;
        } else{
            gameOver = false;
        }
    }

    // Method to run the game
    public void game(){
        Runnable myRunnable = new Runnable() {
            public void run(){
                startGame();
                // Keeps the game going
                while (!gameOver) {
                    // Checks to make sure that the game is not over
                    isGameOver();

                    // Calls the walking method
                    walking();
                    
                    // Checks if the monster on that floor has died
                    if (monsterDied) {
                        numOfKeys++;
                        startFloor();
                    }

                    // Checks if the game is over
                    if(gameOver == true){
                        break;
                    }
                }

                // Calls update to go to title screen
                update();

            }
        };

        // Thread to run game
        thread = new Thread(myRunnable);

        thread.start();
    }

    // Method to initialize information at the beginning of the game
    private void startGame(){
        monsterDied = false;
        playerDied = false;
        monsterHealth = monsterInitHealth + (getNumOfKeys() * 25);
        if (health + (health /4) <=100){
            health = health + (health/4);
        }
        amountClicked = 0;
        flip = false;
        flipV2 = false;
        quickTimeState = false;
        defendButton = false;
        this.whichDirection = null;
    }

    // Starts the next floor
    public void startFloor() {
        // Updates health for monster and player
        monsterDied = false;
        monsterHealth = monsterInitHealth + (getNumOfKeys() * 25);
        if (health + (health /4) <=100){
            health = health + (health/4);
        }
        amountClicked = 0;
        flip = false;
        flipV2 = false;

        // This outputs the information to the files
        File saveFile = new File(directory + "\\src\\TextFiles\\SaveFile.txt"); // Version for VS
        // File saveFile = new File(directory + "\\TextFiles\\SaveFile.txt");
        File outputFile = new File(directory + "\\src\\TextFiles\\OutputFile.txt"); // Version for VS
        // File outputFile = new File(directory + "\\TextFiles\\OutputFile.txt");

        // Output to save file and output file
        PrintWriter output = getPrintWriter(saveFile);
        output.println(gameMode + "\n" + numOfKeys + "\n" + health + "\n" + smokeBombs + "\n" + monsterHealth);
        output.close();

        output = getPrintWriter(outputFile);
        output.print("Game Mode: " + gameMode + "\nNumber of Keys: " + numOfKeys + "\nYour Health: " + health);
        output.print("\nNumber of Smoke Bombs: " + smokeBombs);
        output.close();
    }

    // Message for which directions the user can move in
    public String whichDirections() {
        // Initializes values
        this.outputDirections = "";

        // Checks which directions the user can move in and sends a related message
        if (canMoveForward && !canMoveLeft && !canMoveRight) {
            outputDirections = "You can move forward.";
        } else if (!canMoveForward && canMoveLeft && !canMoveRight) {
            outputDirections = "You can move left.";
        } else if (!canMoveForward && !canMoveLeft && canMoveRight) {
            outputDirections = "You can move right.";
        } else if (canMoveForward && canMoveLeft && !canMoveRight) {
            outputDirections = "You can move forward or left.";
        } else if (canMoveForward && !canMoveLeft && canMoveRight) {
            outputDirections = "You can move forward or right.";
        } else if (!canMoveForward && canMoveLeft && canMoveRight) {
            outputDirections = "You can move left or right.";
        } else if (canMoveForward && canMoveLeft && canMoveRight) {
            outputDirections = "You can move forward, left and right.";
        }

        // Returns the final String
        return(outputDirections);
    }

    // Code to simulate walking
    public void walking() {
        
        // Creates the directions the user can move in
        if(!flip){
            // Gets which directions the user can move in
            canMoveForward = canMoveInDirection();
            canMoveRight = canMoveInDirection();
            canMoveLeft = canMoveInDirection();

            // In case all directions are unavailable, the program chooses a direction
            if (canMoveForward == false && canMoveRight == false && canMoveLeft == false) {
                double x = Math.random();

                if (x < 0.3) {
                    canMoveLeft = true;
                } else if (x < 0.6) {
                    canMoveRight = true;
                } else if (x > 0.6) {
                    canMoveForward = true;
                }
            }

            whichDirection = null;
            outputDirections = "";
            whichDirections();
            displayDirections = true;

            // Updates the view to display available directions
            update();
            flip = true;
        }

        // Gets the user's selection of which direction to go in
        if (whichDirection != null){
            // Compares which directions the user can move and what they selected
            if (canMoveForward == true && whichDirection == forwardKeyBind) {
                mPlayer.walkingSounds();
                long time = java.lang.System.currentTimeMillis() + (mPlayer.walkClip.getMicrosecondLength()/1000);
                while(java.lang.System.currentTimeMillis()<= time){}
                flip = false;
                flipV2 = true;
                
            } else if (canMoveRight == true && whichDirection == rightKeyBind) {
                mPlayer.walkingSounds();
                long time = java.lang.System.currentTimeMillis() + (mPlayer.walkClip.getMicrosecondLength()/1000);
                while(java.lang.System.currentTimeMillis()<= time){}
                flip = false;
                flipV2 = true;
                
            } else if (canMoveLeft == true && whichDirection == leftKeyBind) {
                mPlayer.walkingSounds();
                long time = java.lang.System.currentTimeMillis() + (mPlayer.walkClip.getMicrosecondLength()/1000);
                while(java.lang.System.currentTimeMillis()<= time){}
                flip = false;
                flipV2 = true;
            }
        }

        if(!flip && flipV2){
            if (doesMonsterSpawn()) {
                flip = false;
                this.outputDirections = "You are being attacked!";
                monsterAttack();
            } else{
                displayDirections = false;
                flip = false;
                update();
            }
            flipV2 = false;
            update();
        }
    
    }

    // Method for simulating the monster attack
    public void monsterAttack() {
        // Gets the direction for the monster attack and plays the corresponding audio
        this.monsterAttackDirection();

        mPlayer.monstAttackPrepSounds(this.monstAttackDirection);

        // Creates delay while the sound is playing
        long time = java.lang.System.currentTimeMillis() + (mPlayer.monstPrepClip.getMicrosecondLength()/1000);
        
        while(java.lang.System.currentTimeMillis()<= time){}
  
        // Displays defend button
        displayDefendButton = true;
        update();
        
        // Waits 5 secs for the user to face a direction and click defend button
        long targetTime =  java.lang.System.currentTimeMillis() + 5000;

        while (java.lang.System.currentTimeMillis() <= targetTime){
            // Does not allow user to hit escape key
            canEscape = false;

            // Adds ten millisecond delay so defendButton can be passed in
            try {
                thread.sleep((long)10);
            } catch (InterruptedException ex) {}

            // Checks if the user met the requirements to defend successfully
            if (defendButton) {
                if (monstAttackDirection == "FORWARD" && whichDirection != forwardKeyBind) {
                    defendSuccessful = false;
                    break;
                } else if (monstAttackDirection == "RIGHT" && whichDirection != rightKeyBind) {
                    defendSuccessful = false;
                    break;
                } else if (monstAttackDirection == "BACKWARD" && whichDirection != backwardsKeyBind) {
                    defendSuccessful = false;
                    break;
                } else if (monstAttackDirection == "LEFT" && whichDirection != leftKeyBind) {
                    defendSuccessful = false;
                    break;
                } else {
                    quickTimeState = true;
                    update();
                    break;
                }
            }
        }

        // Allows the user to enter the escape key
        canEscape = true;

        // If the defend button was not pressed
        if (!defendButton){
            defendSuccessful = false;
        }

        // Gets rid of the defend button
        displayDefendButton = false;
        defendButton = false;
        update();

        // If the qte state needs to be created
        if (quickTimeState) {
            displayDefendButton = false;
            this.randomize = true;
            update();
            
            this.randomize = false;
            update();

            // Waits a certain amount of time for the user to click the buttons
            targetTime =  java.lang.System.currentTimeMillis() + getAddTime();

            amountClicked = 0;

            while(java.lang.System.currentTimeMillis() <= targetTime){
                // Does not allow user to exit
                canEscape = false;
                
                try {
                    thread.sleep(10);
                } catch (InterruptedException ex) {}

                // Checks to see if the user clicked all the buttons
                if (amountClicked == numOfButtons){
                    defendSuccessful = true;
                    update();
                    break;
                }

                update();

            }

            // Allows the user to press escape and turns off the qte
            canEscape = true;
            quickTimeState = false;
            update();

            // Case for if the player did not click all the buttons
            if (amountClicked != numOfButtons){
                defendSuccessful = false;
            }
        }
        
        update();

        // If the defence was successful, it takes off monster health
        if (defendSuccessful) {
            monsterHealth = monsterHealth - 25;
            update();
        } else {
            // Checks to use a smoke bomb
            if (smokeBombs > 0) {
                this.outputDirections = "Would you like to use a smoke bomb?";
                long waitTime = System.currentTimeMillis() + 5000;
                wantToUseSmokeBomb = true;
                update();

                // Gives the user time to decide if they want to use a smoke bomb
                while (System.currentTimeMillis() <= waitTime) {
                    // Adds ten millisecond delay so values can be passed in
                    try {
                        thread.sleep((long)10);
                    } catch (InterruptedException ex) {}

                    // Does not allow the user to escape
                    canEscape = false;

                    // If the player used a smoke bomb
                    if(useSmokeBomb){
                        smokeBombs--;
                        break;
                    }
                }

                // Sets the boolean to false ofr using a smoke bomb and allows the user to escape
                wantToUseSmokeBomb = false;
                canEscape = true;

                update();

            }

            // Reduces the player's health since the defence was unsuccessful
            health = health - 10;

            // Adds health for the monster if the defense was unsuccessful
            if(!useSmokeBomb){
                if (monsterHealth <= 90) {
                    monsterHealth = monsterHealth + 10;

                } else {
                    monsterHealth = monsterHealth + (100 - monsterHealth);
                }
            }

            update();
        }

        // Checks to see if the mosnter ahs died
        if (monsterHealth == 0) {
            mPlayer.monsterDeath();
            long monstDeathTime = System.currentTimeMillis() + (mPlayer.monstClip.getMicrosecondLength()/1000);

            while (System.currentTimeMillis() <= monstDeathTime) {}

            monsterDied = true;
        }
        
        useSmokeBomb = false;

        update(); // To update GameView with whatever is required
    }

    // Gets the amount of time the user has to do the qte based on difficulty and floor level
    private int getAddTime() {
        int addTime = 0;

        if (gameMode.equals("EASY")) {
            if (numOfKeys == 0) {
                addTime = 7000;
            } else if (numOfKeys == 1) {
                addTime = 6000;
            } else if (numOfKeys == 2) {
                addTime = 6000;
            }
        } else if (gameMode.equals("MEDIUM")) {
            if (numOfKeys == 0) {
                addTime = 6000;
            } else if (numOfKeys == 1) {
                addTime = 7000;
            } else if (numOfKeys == 2) {
                addTime = 8000;
            } else if (numOfKeys == 3) {
                addTime = 12000;
            }
        } else if (gameMode.equals("HARD")) {
            if (numOfKeys == 0) {
                addTime = 7000;
            } else if (numOfKeys == 1) {
                addTime = 8000;
            } else if (numOfKeys == 2) {
                addTime = 10000;
            } else if (numOfKeys == 3) {
                addTime= 10000;
            } else if (numOfKeys == 4) {
                addTime= 8000;
            }
        } else if (gameMode.equals("DEMO")) {
            addTime = 10000;
        }

        return addTime;
    }

    // Creates a random height for the qte buttons
    public int getRandomHeight() {
        int height = (int)this.screenSize.getHeight();

        height = (int)((Math.random()*(height*0.629)) + (height*0.185));

        return(height);
    }

    // Gets the width of the buttons for the quick time event based on difficulty and floor level
    public int quickTimeGeneration() {
        int width = 0;
        
        if (getGameMode().equals("EASY")) {
            if (getNumOfKeys() == 0) {
                numOfButtons = 4;
            } else if (getNumOfKeys() == 1) {
                numOfButtons = 5;
            } else if (getNumOfKeys() == 2) {
                numOfButtons = 6;
            }
        } else if (getGameMode().equals("MEDIUM")) {
            if (getNumOfKeys() == 0) {
                numOfButtons = 5;
            } else if (getNumOfKeys() == 1) {
                numOfButtons = 7;
            } else if (getNumOfKeys() == 2) {
                numOfButtons = 9;
            } else if (getNumOfKeys() == 3) {
                numOfButtons = 13;
            }
        } else if (getGameMode().equals("HARD")) {
            if (getNumOfKeys() == 0) {
                numOfButtons = 7;
            } else if (getNumOfKeys() == 1) {
                numOfButtons = 9;
            } else if (getNumOfKeys() == 2) {
                numOfButtons = 12;
            } else if (getNumOfKeys() == 3) {
                numOfButtons = 15;
            } else if (getNumOfKeys() == 4) {
                numOfButtons = 18;
            }
        } else if (getGameMode().equals("DEMO")) {
            numOfButtons = 5;
        }

        setAmountVisible();

        // Divides the screen width by the number of buttons to be generated
        width = (int)screenSize.getWidth()/numOfButtons;

        return(width);
    }

    // Sets the buttons for the qte to not visible

    // Sets the buttons for the qte to visible
    public void setAmountVisible(){
        for (int i = 0; i<numOfButtons;i++){
            buttonVisible[i] = true;
        }
    }

    // Updates the GUI
    public void update() {
        view.update();
    }
    
} // Closes class