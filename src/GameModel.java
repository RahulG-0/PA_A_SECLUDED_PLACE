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
    public String userSelection = ""; // Registers which button a user presses
    public String gameMode; // Information for the game
    public int numOfKeys = 0;
    public int health = 100;
    public int flashbangs = 0;
    private final int monstrInitHealth = 100;
    public int monsterHealth = monstrInitHealth;

    private String whichDirection;

    public boolean isAttacked;

    public boolean gameOver;

    public Timer timer;



    // private double time = 0.00; TODO Add this later

    // Constructor
    public GameModel() {
        super();
    }

    // Sets instance variables to information passed in via the file or by starting a new game
    public void SetInfo(String gameMode, int numOfKeys, int health, int flashbangs) {
        this.gameMode = gameMode;
        this.numOfKeys = numOfKeys;
        this.health = health;
        this.flashbangs = flashbangs;
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

    public int getFlashbangs() {
        return(this.flashbangs);
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

    // game

    public void game(){

    }

    public void setUserDirection(String direction){
        this.whichDirection = direction;
    }

    public void monsterAttack(){
        
    }

    // Updates the GUI
    public void update() {
        view.update();
    }
    
} // Closes class