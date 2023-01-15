// Program Name: GameView
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Creates the GUI for the game

// Imports
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GameView extends JPanel {

    // Creates instance variables
    private GameModel gameModel; // Instance of model
    private TitleModel titleModel;
    private MusicPlayer mPlayer;

    private JLabel backGround = new JLabel();

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private JLabel playerHealth = new JLabel();
    private JLabel MonsterHealth = new JLabel();

    private JButton buttons[] = new JButton[18];
    private JPanel quicktimeButtonPannel = new JPanel();

    private JLabel floorLevel = new JLabel();

    private JButton defend = new JButton("Defend");

    // Constructor
    public GameView(GameModel gameModel,TitleModel titleModel) {
        this.gameModel = gameModel;
        this.titleModel = titleModel;
        this.mPlayer = new MusicPlayer();
        this.gameModel.setGUI(this);
        this.layoutView();
        this.registerControllers();
        this.update();
        // this.mPlayer.gameMusic(); TODO Get this to not play over the title screen
    }

    // Creates the initial layout of the GUI
    public void layoutView() {
        // Plays the background ambiance
        // if (titleModel.startGame) {
        //     System.out.println("Making it here"); // WAS NOT MAKING IT HERE :(
        //     this.mPlayer.gameMusic();
        // }

        this.setLayout(null);
        int width = (int)this.screenSize.getWidth();
        int height = (int)this.screenSize.getHeight();

        System.out.println(height);

        this.setBounds(0,0,width,height);

        this.defend.setBounds(100, 400, 30, 40);

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

        quicktimeButtonPannel = addButtons();
        quicktimeButtonPannel.setVisible(false);

        
        // backGround.setVisible(true);
        playerHealth.setVisible(true);
        this.add(playerHealth);
        this.add(MonsterHealth);
        this.add(backGround);
        this.add(quicktimeButtonPannel);
    }

    public void generateButtons(){
        for(int i = 0; i<18;i++ ){
            JButton button = new JButton();
            button.setText(Integer.toString(i));
            buttons[i] = button;
        }
    }

    public void randomLocations(){
        for(int i = 0;i<gameModel.numOfButtons;i++){
            buttons[i].setBounds(gameModel.quickTimeGeneration()[1]*i,gameModel.quickTimeGeneration()[0],gameModel.quickTimeGeneration()[1],100);
        }
    }

    public JPanel addButtons(){
        JPanel panel = new JPanel();
        generateButtons();
        for(int i = 0; i<18;i++ ){
            panel.add(buttons[i]);
        }
        return panel;
    }

    // Registers controllers
    private void registerControllers() {
        //
    }

    // Updates the GUI based on what happens in the game
    public void update() {


        
        if (gameModel.wantToUseSmokeBomb){
            quicktimeButtonPannel.setVisible(false);
        }
        else if(gameModel.isMonsterAttack){
            quicktimeButtonPannel.setVisible(false);
            defend.setVisible(true);
        }

        else if (gameModel.defendeButton){
            quicktimeButtonPannel.setVisible(true);
            randomLocations();
        }
    }
    
} // Closes class