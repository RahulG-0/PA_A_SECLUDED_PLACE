// Program Name: GameModel
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Handles the background processing for the game

// Imports
import java.awt.*;
import java.io.*;

public class GameModel {

    // Creates instance variables
    private GameView view; // Instance of GameView
    private TotalModel totalModel;
    public String userSelection = ""; // Registers which button a user presses
    public String gameMode; // Information for the game
    public int numOfKeys = 0;
    public double health = 100;
    public int smokeBombs = 0;
    private final int monsterInitHealth = 100;
    public double monsterHealth = monsterInitHealth;

    private String whichDirection; // What keyboard input the player gives

    public boolean gameOver = false;

    public String monstAttackDirection = "";
    public boolean defendSuccessful = true; // If the player was able to defend or not
    public boolean wantToUseSmokeBomb = false; // Flag to display smoke bomb option
    public boolean useSmokeBomb = false;

    public boolean displayDefendButton = false;
    public boolean defendButton = false; // If the player clicked the defend button

    // Screensize
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

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

    // SWITCHING BETWEEN going a direction and getting direction
    private boolean  flip = false;
    private boolean flipV2 = false;

    public boolean displayOptionsPannel = false;

    public boolean randomise = false;

    public int amountClicked = 0;
    
    public boolean[] buttonVisible = new boolean[18];

    public MusicPlayer mPlayer = new MusicPlayer();

    // A boolean to check if the player can escape
    public boolean canEscape = true;

    // Gets the user's directory
    public String directory = System.getProperty("user.dir");

    public boolean once = true;

    // Thread for running the game
    // Runnable myRunnable;
    // Thread thread = new Thread(myRunnable);

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

    public PrintWriter getPrintWriter(File file) {
        PrintWriter output = null; ///////////////////////////////// MAY CAUSE NULL POINTER EXCEPTION
        try {
            output = new PrintWriter(file);
        } catch (FileNotFoundException e) {}

        return(output);
    }

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

    // game

    // Checks if the game is over
    public boolean isGameOver() {
        if (gameOver == true) {
            return(gameOver);
        }

        if (gameMode == "EASY" && numOfKeys == 3) {
            gameOver = true;
        } else if (gameMode == "MEDIUM" && numOfKeys == 4) {
            gameOver = true;
        } else if (gameMode == "HARD" && numOfKeys == 5) {
            gameOver = true;
        } else if (health == 0) {
            gameOver = true;
            playerDied = true;
        } else{
            gameOver = false;
        }

        return(gameOver);
    }

    // Method to run the game
    public void game(){
        Runnable myRunnable = new Runnable() {
            public void run(){
                int counter = 0;
                while (!isGameOver()) {
                    // System.out.println("Still doing this: " + counter);
                    // System.nanoTime();
                    // Keep the game going
                    walking();
        
                    if (monsterDied) {
                        numOfKeys++;
                        startFloor();
                        // Play the elevator music
                    }

                    counter++;
                }
            }
        };
        // UPDATE KEYBINDS HERE
        // Keeps playing as long as the game is not over
        Thread thread = new Thread(myRunnable);

        thread.start();
    }

    // public void quitThread() {
    //     // Quit the gameModel thread and call this in quit game
    // }

    public void setUserDirection(String direction){
        this.whichDirection = direction;
    }

    // Start the floor
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

        // This outputs the information to the file
        File saveFile = new File(directory + "\\src\\TextFiles\\SaveFile.txt");
        File outputFile = new File(directory + "\\src\\TextFiles\\OutputFile.txt");

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
        this.outputDirections = "You can move ";
        String tempVal = "";
        int numOfDirections = 0;
        int commaCounter = 0;
        String tempString = "";

        if (canMoveForward == true) {
            this.outputDirections = outputDirections + "forward, ";
            numOfDirections++;
        }

        if (canMoveLeft == true) {
            this.outputDirections = outputDirections + "left, ";
            numOfDirections++;
        }

        if (canMoveRight == true) {
            this.outputDirections = outputDirections + "right, ";
            numOfDirections++;            
        }

        for (int i1 = 0; i1 < outputDirections.length() - 2; i1++) {
            tempVal = tempVal + outputDirections.charAt(i1);
        }

        this.outputDirections = tempVal;
        this.outputDirections = outputDirections + ".";

        return(outputDirections);
    }

    // Code to handle walking
    public void walking() {
        
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
            whichDirections();
            displayDirections = true;

            // Updates the view to display available directions
            update();
            flip = true;
        }

        // GET USER SELECTION (whichDirection)
        if(whichDirection != null){
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
                flip =false;
                update();
            }
            flipV2 = false;
            update();
        }
    
    }

    public void monsterAttack() {
        // If the monster attack was unsuccessful, do defendSuccessful
        this.monsterAttackDirection();

        mPlayer.monstAttackPrepSounds(this.monstAttackDirection);

        long time = java.lang.System.currentTimeMillis() + (mPlayer.monstPrepClip.getMicrosecondLength()/1000);
        
        while(java.lang.System.currentTimeMillis()<= time){}
  
        displayDefendButton = true;
        update();
        
        long targetTime =  java.lang.System.currentTimeMillis() + 5000;
        
        while (java.lang.System.currentTimeMillis() <= targetTime){
            canEscape = false;

            if (defendButton) {
                if (monstAttackDirection == "FORWARD" && whichDirection != forwardKeyBind) {
                    defendSuccessful = false;
                    displayDefendButton = false;
                    break;
                } else if (monstAttackDirection == "RIGHT" && whichDirection != rightKeyBind) {
                    defendSuccessful = false;
                    displayDefendButton = false;
                    break;
                } else if (monstAttackDirection == "BACKWARD" && whichDirection != backwardsKeyBind) {
                    defendSuccessful = false;
                    displayDefendButton = false;
                    break;
                } else if (monstAttackDirection == "LEFT" && whichDirection != leftKeyBind) {
                    defendSuccessful = false;
                    displayDefendButton = false;
                    break;
                } else {
                    quickTimeState = true;
                    update();
                    break;
                }
            }
        }

        canEscape = true;

        if (!defendButton){
            defendSuccessful = false;
        }
        displayDefendButton = false;
        defendButton = false;
        update();

        if(quickTimeState){
            displayDefendButton = false;
            randomise = true;
            update();
            randomise = false;
            update();

            targetTime =  java.lang.System.currentTimeMillis() + getAddTime(); 
            amountClicked = 0;

            while(java.lang.System.currentTimeMillis() <= targetTime){
                canEscape = false;
                
                if (amountClicked == numOfButtons){
                    defendSuccessful = true;
                    update();
                    break;
                }
                update();

            }

            canEscape = true;
            quickTimeState = false;
            update();

            if (amountClicked != numOfButtons){
                defendSuccessful = false;
            }
        }
        
        update();


        // If the user clicks all the buttons, defendSuccessful

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

                while (System.currentTimeMillis() < waitTime) {
                    canEscape = false;
                    // wantToUseSmokeBomb = true;
                    // update(); ////////////// MIGHT NOT BE IDEAL

                    //CHECK WHAT THEY DO
                }

                wantToUseSmokeBomb = false;
                canEscape = true;

            }

            // IF THE USER USED THE SMOKE BOMB
            // if (user used smoke bomb) {
            //     smokeBombs--;
            // }

            health = health - 10;

            // Adds health for the monster if the defense was unsuccessful
            if (monsterHealth <= 90) {
                monsterHealth = monsterHealth + 10;

            } else {
                monsterHealth = monsterHealth + (100 - monsterHealth);
            }
            update();
        }

        if (monsterHealth == 0) {
            mPlayer.monsterDeath();
            long monstDeathTime = System.currentTimeMillis() + (mPlayer.monstClip.getMicrosecondLength()/1000);

            while (System.currentTimeMillis() <= monstDeathTime) {}

            monsterDied = true;
            // Go into next floor and do whatever stuff
        }

        update(); // To update GameView with whatever is required
    }

    private int getAddTime() {
        int addTime = 0;

        if (getGameMode() == "EASY") {
            if (getNumOfKeys() == 0) {
                addTime = 7000;
            } else if (getNumOfKeys() == 1) {
                addTime = 6000;
            } else if (getNumOfKeys() == 2) {
                addTime = 6000;
            }
        } else if (getGameMode() == "MEDIUM") {
            if (getNumOfKeys() == 0) {
                addTime = 6000;
            } else if (getNumOfKeys() == 1) {
                addTime = 7000;
            } else if (getNumOfKeys() == 2) {
                addTime = 8000;
            } else if (getNumOfKeys() == 3) {
                addTime = 12000;
            }
        } else if (getGameMode() == "HARD") {
            if (getNumOfKeys() == 0) {
                addTime = 7000;
            } else if (getNumOfKeys() == 1) {
                addTime = 8000;
            } else if (getNumOfKeys() == 2) {
                addTime = 10000;
            } else if (getNumOfKeys() == 3) {
                addTime= 10000;
            } else if (getNumOfKeys() == 4) {
                addTime= 8000;
            }
        }

        return addTime;
    }

    public int getRandomHeight() {
        int height = 0;

        height = (int)((Math.random()*680) + 200);

        return(height);
    }

    // Gets the height and width of the buttons for the quick time event
    public int quickTimeGeneration() {
        int width = 0;
        // int[] returnDimensions = {0, 0};

        // Based on game mode and floor level, it decides how many buttons to generate
        if (getGameMode() == "EASY") {
            if (getNumOfKeys() == 0) {
                numOfButtons = 4;
            } else if (getNumOfKeys() == 1) {
                numOfButtons = 5;
            } else if (getNumOfKeys() == 2) {
                numOfButtons = 6;
            }
        } else if (getGameMode() == "MEDIUM") {
            if (getNumOfKeys() == 0) {
                numOfButtons = 5;
            } else if (getNumOfKeys() == 1) {
                numOfButtons = 7;
            } else if (getNumOfKeys() == 2) {
                numOfButtons = 9;
            } else if (getNumOfKeys() == 3) {
                numOfButtons = 13;
            }
        } else if (getGameMode() == "HARD") {
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
        }
        setAmountVisible();

        width = (int)screenSize.getWidth()/numOfButtons;

        return(width);

        // returnDimensions[0] = width;
        // returnDimensions[1] = height;

        // return(returnDimensions);
    }

    public void setFalseVisible(){
        for (int i = 0; i<18;i++){
            buttonVisible[i] = false;
        }
    }
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