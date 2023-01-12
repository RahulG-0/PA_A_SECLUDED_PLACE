// Program Name: KeyBindController
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: This is the controller for the buttons on the title screen

// Imports
import java.awt.event.*;

public class KeyBindController implements KeyListener {

    // Creates an instance of TitleModel
    private TitleModel titleModel;
    private GameModel gameModel;

    // Constructor
    public KeyBindController(TitleModel titleModel, GameModel gameModel) {
        this.titleModel = titleModel;
        this.gameModel = gameModel;
    }

    // Tracks the keys pressed by the user
    public void keyPressed(KeyEvent e) {
        if (this.titleModel.startGame) {
            // listen to gameModel
        } else {
            // listen to titleModel
        }
    }

    // These two are required for KeyBindController to implement KeyListener
    public void keyTyped(KeyEvent e) {
        // Just here to not cause error
    }

    public void keyReleased(KeyEvent e) {
        // Just here to not cause error
    }

} // Closes class