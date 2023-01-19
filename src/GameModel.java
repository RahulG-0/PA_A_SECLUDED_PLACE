// Program Name: GameModel
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Handles the background processing for the game

// Imports
import java.awt.*;
import java.util.Timer;

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
    public int monsterHealth = monsterInitHealth;

    private String whichDirection; // What keyboard input the player gives

    public boolean gameOver = false;

    public String monstAttackDirection = "";
    public boolean defendSuccessful = true; // If the player was able to defend or not
    public boolean wantToUseSmokeBomb = false; // Flag to display smoke bomb option

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
    private boolean flipV3 = false;

    public int amountClicked = 0;
    
    public boolean[] buttonVisible = new boolean[18];

    // Constructor
    public GameModel() {
        super();
    }

    // Sets instance variables to information passed in via the file or by starting a new game
    public void setInfo(String gameMode, int numOfKeys, int health, int smokeBombs) {
        this.gameMode = gameMode;
        this.numOfKeys = numOfKeys;
        this.health = health;
        this.smokeBombs = smokeBombs;
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

    // This gets which direction the monster is attacking from
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

        Runnable myRunnable =
    new Runnable(){
        public void run(){
            while (!isGameOver()) {
                // Keep the game going
                walking();
    
                if (monsterDied) {
                    numOfKeys++;
                    startFloor();
                    // Play the elevator music
                }
            }
        }
    };
        // UPDATE KEYBINDS HERE
        // Keeps playing as long as the game is not over
        Thread thread  = new Thread(myRunnable);

        thread.start();
    }

    public void setUserDirection(String direction){
        this.whichDirection = direction;
    }

    // TODO We may need this to initialize each floor level
    // Start the floor
    public void startFloor() {
        // Updates health for monster and player
        monsterDied = false;
        monsterHealth = monsterInitHealth + (getNumOfKeys() * 25);
        health = health + (health/2);
        amountClicked = 0;
    }

    // Message for which directions the user can move in
    public String whichDirections() {
        this.outputDirections = "You can move ";

        if (canMoveForward == true) {
            // this.outputDirections.concat("forward, ");
            this.outputDirections = outputDirections + "forward, ";
        }

        if (canMoveLeft == true) {
            this.outputDirections.concat("left, ");
            this.outputDirections = outputDirections + "left, ";
        }

        if (canMoveRight == true) {
            this.outputDirections.concat("right, ");
            this.outputDirections = outputDirections + "right, ";
            
        }

        this.outputDirections.substring(0, (this.outputDirections.length()-2));
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

        // TODO Consider deleting this if statement cuz it doesn't matter
        // Just play the audio clip since the user's direction doesn't matter
        if(whichDirection != null){
            // Compares which directions the user can move and what they selected
            if (canMoveForward == true && whichDirection == forwardKeyBind) {
                // move forward
                // play sound clip of walking
                flip = false;
                flipV2 = true;
                
            } else if (canMoveRight == true && whichDirection == rightKeyBind) {
                // move right
                // play sound clip of walking
                flip = false;
                flipV2 = true;
                
            } else if (canMoveLeft == true && whichDirection == leftKeyBind) {
                // move left
                // play sound clip of walking
                flip = false;
                flipV2 = true;
            }
        }

        if(!flip && flipV2){
            if (doesMonsterSpawn()) {
                monsterAttack();
            } else{
                displayDirections = false;
                flip = false;
                update();
            }
            flipV2 = false;
        }
    
    }

    public void monsterAttack() {
        // If the monster attack was unsuccessful, do defendSuccessful
        this.monsterAttackDirection();
        // Play audio clip
        // Start timer

        // Compare which direction the user faces and which direction the monster is attacking from
        // If the user is facing the right direction, enable the defend buttons
        // If they clicked the button in time, do something
        // Stop timer when button is pushed
        // If they don't meet those requirements, defendSuccessful = false

        // Checks if they met the requirements for the quick time event
        // if () {
        //     quickTimeGeneration();
        // } else {
        //     defendSuccessful = false;
        // }
        
        // TODO Implement timer here (timer should be combination of facing direction and clickng button)
        // All situations where the defense was unsuccessful

        // If timer runs out, defend unsuccesful

        displayDefendButton = true;
        update();
        
        long targetTime =  java.lang.System.currentTimeMillis() + 5000;
        
        while (java.lang.System.currentTimeMillis() <= targetTime){
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

        if (!defendButton){
            defendSuccessful = false;
            displayDefendButton = false;
            update();
        }
        update();

        if(quickTimeState){

            targetTime =  java.lang.System.currentTimeMillis() + getAddTime();
            while(java.lang.System.currentTimeMillis() <= targetTime){
                if (amountClicked == numOfButtons){
                    defendSuccessful = true;
                    break;
                }
                update();

            }
            quickTimeState = false;

            if (amountClicked != numOfButtons){
                defendSuccessful = false;
            }
        }
        
        update();


        // If the user clicks all the buttons, defendSuccessful

        // If the defence was successful, it takes off monster health
        if (defendSuccessful) {
            monsterHealth = monsterHealth - 25;
        } else {
            // Checks to use a smoke bomb
            if (smokeBombs > 0) {
                wantToUseSmokeBomb = true;
                update();
            }

            health = health - 10;

            // Adds health for the monster if the defense was unsuccessful
            if (monsterHealth < 90) {
                monsterHealth = monsterHealth + 10;
            } else {
                monsterHealth = monsterHealth + (100 - monsterHealth);
            }
        }

        if (monsterHealth == 0) {
            monsterDied = true;
            // Go into next floor and do whatever stuff
        }

        update(); // To update GameView with whatever is required
    }

    private int getAddTime() {
        int addTime = 0;

        if (getGameMode() == "EASY") {
            if (getNumOfKeys() == 0) {
                addTime = 7;
            } else if (getNumOfKeys() == 1) {
                addTime = 6;
            } else if (getNumOfKeys() == 2) {
                addTime = 6;
            }
        } else if (getGameMode() == "MEDIUM") {
            if (getNumOfKeys() == 0) {
                addTime = 6;
            } else if (getNumOfKeys() == 1) {
                addTime = 7;
            } else if (getNumOfKeys() == 2) {
                addTime = 8;
            } else if (getNumOfKeys() == 3) {
                addTime = 12;
            }
        } else if (getGameMode() == "HARD") {
            if (getNumOfKeys() == 0) {
                addTime = 7;
            } else if (getNumOfKeys() == 1) {
                addTime = 8;
            } else if (getNumOfKeys() == 2) {
                addTime = 10;
            } else if (getNumOfKeys() == 3) {
                addTime= 10;
            } else if (getNumOfKeys() == 4) {
                addTime= 8;
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