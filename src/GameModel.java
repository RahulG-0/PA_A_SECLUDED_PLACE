// Program Name:
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Handles the background processing for the game

// Imports
// import java.util.*;

public class GameModel {

    // Creates instance variables
    private GameView view;
    private TitleModel titleModel;
    public String userSelection = "";
    public String gameMode = "";
    public int numOfKeys = 0;
    public int health = 100;
    public int inventory = 0;
    // public ArrayList<String> inventory = new ArrayList<String>();
    // private double time = 0.00; TODO Add this later

    // Constructor
    public GameModel() {
        super();

    }

    public void getInfo(String gameMode, int numOfKeys, int health, int inventory){
        this.gameMode = gameMode;
        this.numOfKeys = numOfKeys;
        this.health = health;
        this.inventory = inventory;
    }

    // Sets the current layout of the GUI
    public void setGUI(GameView currentView) {
        this.view = currentView;
    }

    public void getSelected(String option) {
        if (option.equals("New Game")) {
            userSelection = "new";
        } else if (option.equals("Continue Game")) {
            userSelection = "load";
        } else if (option.equals("Options")) {
            userSelection = "settings";
        } else if (option.equals("Quit")) {
            userSelection = "exit";
        }

        this.update();
    }

    // Updates the GUI
    public void update() {
        view.update();
    }
    
} // Closes class