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

    private JLabel backGround = new JLabel(); // sets the backround of the game

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // getting the screen sice of the users device

    private JLabel playerHealth = new JLabel();  // bar that diplays player health
    private JLabel MonsterHealth = new JLabel(); // bar that displays monster health

    private JButton buttons[] = new JButton[18]; // array that contains all the wuicktinme buttons
    private JPanel quicktimeButtonPannel = new JPanel(); // contains all the quicktime buttons

    private JLabel floorLevel = new JLabel(); // siplays the floor you are on 

    private JButton defend = new JButton("Defend"); // button that allows you to defend

    // Constructor
    public GameView(GameModel gameModel,TitleModel titleModel) {
        this.gameModel = gameModel;
        this.titleModel = titleModel;
        this.mPlayer = new MusicPlayer();
        this.gameModel.setGUI(this);
        this.layoutView();
        this.registerControllers();
        this.update();
        this.mPlayer.gameMusic(); 
    }

    // Creates the initial layout of the GUI
    public void layoutView() {

        this.setLayout(null); // settoing the layout to null

        // gettoing the width and height of user diplay
        int width = (int)this.screenSize.getWidth(); 
        // Plays the background ambiance
        // if (titleModel.startGame) {
        //     System.out.println("Making it here"); // WAS NOT MAKING IT HERE :(
        //     this.mPlayer.gameMusic();
        // }

        this.setLayout(null);
        // int width = (int)this.screenSize.getWidth();
        int height = (int)this.screenSize.getHeight();

        this.setBounds(0,0,width,height); // setting the size of the game

        this.defend.setBounds(800, 1020, 350, 50); // settng the size for the defend buttom

        // setting the colour of the backround
        backGround.setBounds(0,0,width,height);
        backGround.setBackground(Color.BLACK);
        backGround.setOpaque(true);

        
        playerHealth.setBounds(10,1020,600,50); // setting the location of player health
        playerHealth.setBackground(Color.RED); // setting colour of player health
        playerHealth.setOpaque(true);

        MonsterHealth.setBounds(1310,1020,600,50); // setting the location of monster health
        MonsterHealth.setBackground(new Color(170, 34, 34)); // setting colour of monster health
        MonsterHealth.setOpaque(true);

        addButtons(); // gneratting and adding quicktime buttons
        quicktimeButtonPannel.setVisible(false);

        // adding objects to the game
        this.add(playerHealth);
        this.add(defend);
        this.add(MonsterHealth);
        this.add(quicktimeButtonPannel);
        // this.add(backGround);
    }// end of game view

    // generates buttons in an array
    public void generateButtons(){
        for(int i = 0; i<18;i++ ){
            JButton button = new JButton();
            button.setText(Integer.toString(i+1));
            buttons[i] = button;

        }
    }

    // setting the quicktime location for each button
    public void randomLocations(){
        int numOfButtons = gameModel.quickTimeGeneration()[0];
        numOfButtons = gameModel.numOfButtons;
        for(int i = 0;i<4;i++){
            buttons[i].setBounds(gameModel.quickTimeGeneration()[0]*i,gameModel.quickTimeGeneration()[1],gameModel.quickTimeGeneration()[0],100);
            System.out.println(Arrays.toString(gameModel.quickTimeGeneration()));
        }
        // System.out.println(gameModel.numOfButtons);
    }

    // add
    public void addButtons(){
        generateButtons();
        quicktimeButtonPannel.setLayout(null);
        for(int i = 0; i<18;i++ ){
            quicktimeButtonPannel.add(buttons[i]);
        }

    }

    // Registers controllers
    private void registerControllers() {
        //
        buttonGameController bc = new buttonGameController(this.gameModel);
        defend.addActionListener(bc);

    }

    // Updates the GUI based on what happens in the game
    public void update() {
        if (gameModel.wantToUseSmokeBomb){
            quicktimeButtonPannel.setVisible(false);
        }
        // else if(gameModel.isMonsterAttack){
        //     quicktimeButtonPannel.setVisible(false);
        //     defend.setVisible(true);
        // }

        else if (gameModel.defendButton){
            randomLocations();
            quicktimeButtonPannel.setVisible(true);
            // System.out.println("hello");
        }
    }
    
} // Closes class