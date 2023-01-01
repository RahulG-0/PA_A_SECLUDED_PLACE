// Program Name: TitleView
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Creates the GUI for the game

// Imports
import javax.swing.*;

public class GameView extends JPanel {

    // Creates instance variables
    private GameModel gameModel; // Instance of model

    // Gets directory and screen size
    private JLabel info = new JLabel();

    private String info1;

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
        // info1 = this.gameModel.gameMode + " " + this.gameModel.numOfKeys;
        // info1 = gameModel.getGameMode();
        info1 = Integer.toString(gameModel.getFlashbangs());
        // info1 = info1 + " " + this.gameModel.health + " " + this.gameModel.flashbangs;

        this.info.setText(info1);
        this.add(info);
    }

    // Registers the controller to the buttons
    private void registerControllers() {
        //
    }

    // Updates the GUI with the answer
    public void update() {
        System.out.println(info1);
    }
    
} // Closes class