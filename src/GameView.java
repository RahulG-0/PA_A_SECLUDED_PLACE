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
    private TitleModel titleModel;

    private JLabel backround = new JLabel();

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private JLabel playerHealth = new JLabel();
    private JLabel MonsterHealth = new JLabel();

    private JLabel floorLevel = new JLabel();

    private JButton defend = new JButton("Defend");

    


    // Constructor
    public GameView(GameModel gameModel,TitleModel titleModel) {
        this.gameModel = gameModel;
        this.titleModel = titleModel;
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

        // setting the colour of the backround
        backround.setBounds(0,0,width,height);
        backround.setBackground(Color(0,0,0));

        
    }

    private Color Color(int i, int j, int k) {
        return null;
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