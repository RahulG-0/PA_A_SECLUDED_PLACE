// Program Name: TitleModel
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Does the background work for the title screen

public class TitleModel {

    // Creates instance variables
    private TitleView view; // Instance of TitleView
    public String userSelection = ""; // Stores which button the user selected
    public String gameDifficulty = ""; // Stores difficulty chosen if new game was selected

    // Stores whether the user can load a game or not
    public boolean canLoad = false;

    // Stores whether the GUI should switch from TitleView to GameView or not
    public boolean startGame = false;

    // Constructor
    public TitleModel() {
        super();
    }

    // Sets the current layout of the GUI
    public void setGUI(TitleView currentView) {
        this.view = currentView;
    }

    // Interprets which button was selected on the title screen
    public void getSelected(String option) {
        // Checks to see if the save file game can be loaded to continue
        canLoad = view.canFileLoad();

        if (option.equals("New Game")) {

            userSelection = "new";

        } else if (option.equals("Continue Game")) {

            userSelection = "load";
            if (canLoad) {
                startGame = true;
            }

        } else if (option.equals("Options")) {

            userSelection = "settings";

        } else if (option.equals("Quit")) {

            userSelection = "exit";

        } else if (option.equals("Easy")){

            gameDifficulty = "Easy";
            startGame = true;

        } else if (option.equals("Medium")){

            gameDifficulty = "Medium";
            startGame = true;

        } else if (option.equals("Hard")){

            gameDifficulty = "Hard";
            startGame = true;

        } else if(option.equals("Exit")){
            userSelection = "Quit";
        }

        this.update();
    }

    // Updates the GUI
    public void update() {
        view.update();

    }

    /*
    public boolean getStartGame(){
        return startGame;
    } */
    
} // Closes class