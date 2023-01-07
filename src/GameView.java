// Program Name: GameView
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Creates the GUI for the game

// Imports
import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    // Creates instance variables
    private GameModel gameModel; // Instance of model

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

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
        int width = (int)this.screenSize.getWidth();
        int height = (int)this.screenSize.getHeight();


        this.setBounds(0,0,width,height);
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