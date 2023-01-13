import java.util.Timer;

// Program Name: GameModel
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Handles the background processing for the game

// Imports
// import java.util.*;

public class GameModel {

    // Creates instance variables
    private GameView view; // Instance of GameView
    // private GameController controller;
    public String userSelection = ""; // Registers which button a user presses
    public String gameMode; // Information for the game
    public int numOfKeys = 0;
    public int health = 100;
    public int smokeBombs = 0;
    private final int monsterInitHealth = 100;
    public int monsterHealth = monsterInitHealth;

    private String whichDirection; // What keyboard input the player gives

    public boolean isAttacked;

    public boolean gameOver;

    public Timer timer;

    public String monstAttackDirection = "";
    public boolean defendSuccessful = true; // If the player was able to defend or not
    public boolean wantToUseSmokeBomb = false; // Flag to display smoke bomb option



    // private double time = 0.00; TODO Add this later

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

    // Getter methods for the information
    public String getGameMode() {
        return (this.gameMode);
    }

    public int getNumOfKeys() {
        return(this.numOfKeys);
    }

    public int getHealth() {
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

    public void game(){
        //
    }

    public void setUserDirection(String direction){
        this.whichDirection = direction;
    }

    // TODO We may need this to initialize each floor level
    // Start the floor
    public void startFloor() {
        monsterHealth = monsterInitHealth + (getNumOfKeys() * 25);
    }

    public void monsterAttack() {
        // If the monster attack was unsuccessful, do defendSuccessful
        this.monsterAttackDirection();

        // Play audio clip
        // Start timer

        // Compare which direction the user faces and which direction the monster is attacking from
        // If the user is facing the right direction, check if they clicked the button in time
        // Stop timer when button is pushed
        // If they don't meet those requirements, defendSuccessful = false

        // Checks if they met the requirements for the quick time event
        // if () {
        //     quickTimeGeneration();
        // } else {
        //     defendSuccessful = false;
        // }

        if (defendSuccessful) {
            monsterHealth = monsterHealth - 25;
        } else {
            if (smokeBombs > 0) {
                wantToUseSmokeBomb = true;
                this.view.update();
            }

            health = health - 10;
        }

        if (monsterHealth == 0) {
            numOfKeys++;
            // Go into next floor and do whatever stuff
        }

        this.view.update(); // To update GameView with whatever is required
    }

    public void quickTimeGeneration() {
        //
    }

    // Rough estimates: Height * 0.277
    // Bottom: Height * 0.629
    // public int generateY() {
    //     //
    // }

    // Updates the GUI
    public void update() {
        view.update();
    }
    
} // Closes class