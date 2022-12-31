// Program Name:
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Handles the background processing for the game

public class TitleModel {

    // Creates instance variables
    private TitleView view;
    private TotalView totalView;
    public String userSelection = "";
    public String difficulty = "";
    public String gameDifficulty = "";
    public int gameModeFlag = 0;

    

    public boolean startGame = false;

    // Constructor
    public TitleModel() {
        super();
    }

    // Sets the current layout of the GUI
    public void setGUI(TitleView currentView) {
        this.view = currentView;
    }

    public void getSelected(String option) {
        if (option.equals("New Game")) {
            userSelection = "new";
        } else if (option.equals("Continue Game")) {
            userSelection = "load";
            startGame = true;
        } else if (option.equals("Options")) {
            userSelection = "settings";
        } else if (option.equals("Quit")) {
            userSelection = "exit";
        } else if(option.equals("Easy")){
            gameDifficulty = "Easy";
            startGame = true;
            System.out.println("easy");
        } else if(option.equals("Medium")){
            gameDifficulty = "Medium";
            startGame = true;
        } else if(option.equals("Hard")){
            gameDifficulty = "Hard";
            startGame = true;
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

    public boolean getStartGame(){
        return startGame;
    }
    
} // Closes class