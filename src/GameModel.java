// Program Name: GameModel
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Handles the background processing for the game

// Imports
import java.awt.*;

public class GameModel {

    // Creates instance variables
    private GameView view; // Instance of GameView
    public String userSelection = ""; // Registers which button a user presses
    public String gameMode; // Information for the game
    public int numOfKeys = 0;
    public int health = 100;
    public int smokeBombs = 0;
    private final int monsterInitHealth = 100;
    public int monsterHealth = monsterInitHealth;

    private String whichDirection; // What keyboard input the player gives

    public boolean gameOver;

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
    public String outputDirections = "You can move ";

    // Keybinds
    private String forwardKeyBind = "W";
    private String rightKeyBind = "D";
    private String backwardsKeyBind = "S";
    private String leftKeyBind = "A";

    // Flag to activate the quick time buttons
    public boolean quickTimeState = false;
    public int numOfButtons = 0; // Number of buttons for the quick time generation

    // Flags to check if the player or the monster have died
    public boolean playerDied = false;
    public boolean monsterDied = false;

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

    // Checks if the game is over
    public boolean isGameOver() {
        if (gameMode == "EASY" && numOfKeys != 3) {
            gameOver = true;
        } else if (gameMode == "MEDIUM" && numOfKeys != 4) {
            gameOver = true;
        } else if (gameMode == "HARD" && numOfKeys != 5) {
            gameOver = true;
        } else if (health == 0) {
            gameOver = true;
            playerDied = true;
        }

        return(gameOver);
    }

    // Method to run the game
    public void game(){
        // UPDATE KEYBINDS HERE
        // Keeps playing as long as the game is not over
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
    }

    // Message for which directions the user can move in
    public String whichDirections() {
        if (canMoveForward == true) {
            this.outputDirections.concat("forward, ");
        }

        if (canMoveLeft == true) {
            this.outputDirections.concat("left, ");
        }

        if (canMoveRight == true) {
            this.outputDirections.concat("right, ");
        }

        this.outputDirections.substring(0, (this.outputDirections.length()-2));
    
        return(outputDirections);
    }

    // Code to handle walking
    public void walking() {
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

        whichDirections();

        // Updates the view to display available directions
        update();

        // GET USER SELECTION (whichDirection)

        // TODO Consider deleting this if statement cuz it doesn't matter
        // Just play the audio clip since the user's direction doesn't matter

        // Compares which directions the user can move and what they selected
        if (canMoveForward == true && whichDirection == forwardKeyBind) {
            // move forward
            // play sound clip of walking
        } else if (canMoveRight == true && whichDirection == rightKeyBind) {
            // move right
            // play sound clip of walking
        } else if (canMoveLeft == true && whichDirection == leftKeyBind) {
            // move left
            // play sound clip of walking
        }

        if (doesMonsterSpawn()) {
            monsterAttack();
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

        if (defendButton) {
            if (monstAttackDirection == "FORWARD" && whichDirection != forwardKeyBind) {
                defendSuccessful = false;
            } else if (monstAttackDirection == "RIGHT" && whichDirection != rightKeyBind) {
                defendSuccessful = false;
            } else if (monstAttackDirection == "BACKWARD" && whichDirection != backwardsKeyBind) {
                defendSuccessful = false;
            } else if (monstAttackDirection == "LEFT" && whichDirection != leftKeyBind) {
                defendSuccessful = false;
            } else {
                quickTimeState = true;
                update();
            }
        }

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
        }

        if (monsterHealth == 0) {
            monsterDied = true;
            // Go into next floor and do whatever stuff
        }

        update(); // To update GameView with whatever is required
    }

    public int getRandomHeight() {
        int height = 0;

        height = (int)((Math.random()*1100) + 200);

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

        width = (int)screenSize.getWidth()/numOfButtons;

        return(width);

        // returnDimensions[0] = width;
        // returnDimensions[1] = height;

        // return(returnDimensions);
    }

    // Updates the GUI
    public void update() {
        view.update();
    }
    
} // Closes class