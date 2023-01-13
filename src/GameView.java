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

    private JLabel backGround = new JLabel();

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
        this.setLayout(null);
        int width = (int)this.screenSize.getWidth();
        int height = (int)this.screenSize.getHeight();

        System.out.println(height);

        this.setBounds(0,0,width,height);

        // setting the colour of the backround
        backGround.setBounds(0,0,width,height);
        // backGround.setBorder(getBorder());
        backGround.setBackground(Color.BLACK);
        backGround.setOpaque(true);

        playerHealth.setBounds(10,1020,600,50);
        playerHealth.setBackground(Color.RED);
        playerHealth.setOpaque(true);

        MonsterHealth.setBounds(1310,1020,600,50);
        MonsterHealth.setBackground(new Color(170, 34, 34));
        MonsterHealth.setOpaque(true);

        
        // backGround.setVisible(true);
        playerHealth.setVisible(true);
        this.add(playerHealth);
        this.add(MonsterHealth);
        this.add(backGround);
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