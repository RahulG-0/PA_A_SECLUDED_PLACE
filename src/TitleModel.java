// Program Name:
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Handles the background processing for the game

public class TitleModel {

    // Creates instance variables
    private TitleViewV2 view;
    public String userSelection = "";
    public String difficulty = "";
    public String gameDifficulty = "";
    public int gameModeFlag = 0;

    // Constructor
    public TitleModel() {
        super();
    }

    // Sets the current layout of the GUI
    public void setGUI(TitleViewV2 currentView) {
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

    // Returns the difficulty level
    public void getDifficulty(String diff) {
        gameDifficulty = diff;
    }

    // Updates the GUI
    public void update() {
        view.update();
    }
    
} // Closes class