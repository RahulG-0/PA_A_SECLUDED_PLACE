// Program Name: GameView
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Creates the GUI for the game

// Imports
import javax.swing.*;

public class GameView extends JPanel {

    // Creates instance variables
    private GameModel gameModel; // Instance of model

    // Constructor
    public GameView(GameModel gameModel) {
        this.gameModel = gameModel;
        this.gameModel.setGUI(this);
        this.layoutView();
        this.registerControllers();
        this.update();
    }

    // Creates the initial layout of the GUI
    public void layoutView() {
        //
    }

    // Registers controllers
    private void registerControllers() {
        //
    }

    // Updates the GUI based on what happens in the game
    public void update() {
        //
    }
    
} // Closes class