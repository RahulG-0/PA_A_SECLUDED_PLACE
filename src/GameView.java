// Program Name: GameView
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Creates the GUI for the game

// Imports
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JPanel {

    // Creates instance variables
    private GameModel gameModel; // Instance of model
    private TitleModel titleModel;
    private MusicPlayer mPlayer;
    private VolumeController volumeModel;

    private JSlider volume = new JSlider(JSlider.HORIZONTAL,-20, 6,0);
    private JTextField cFowardKeybind = new JTextField("w");
    private JTextField cBackwardsKeybind = new JTextField("s");
    private JTextField cRightKeybind = new JTextField("d");
    private JTextField cLeftKeybind = new JTextField("a");

    private JLabel fowardKeybind = new JLabel("Foward Keybind");
    private JLabel backwardsKeybind = new JLabel("Backward Keybing");
    private JLabel rightKeybind = new JLabel("Right Keybind");
    private JLabel leftKeybind = new JLabel("Left Keybind"); 
    private JLabel volumeLable = new JLabel("Volume"); 
    private JButton quitGame = new JButton("Quit_Game");

    private JButton exitOptions = new JButton("Exit");

    private JLabel optionsTitle = new JLabel("Options");
    private JPanel optionsPanel = new JPanel();


    private JLabel backGround = new JLabel(); // sets the backround of the game

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // getting the screen sice of the users device

    private JLabel playerHealth = new JLabel();  // bar that diplays player health
    private JLabel MonsterHealth = new JLabel(); // bar that displays monster health

    private JButton buttons[] = new JButton[18]; // array that contains all the wuicktinme buttons
    private JPanel quicktimeButtonPannel = new JPanel(); // contains all the quicktime buttons

    private JLabel floorLevel = new JLabel(); // siplays the floor you are on 

    private JButton defend = new JButton("Defend"); // button that allows you to defend

    private String directory = System.getProperty("user.dir");

    private JLabel options = new JLabel("",SwingConstants.CENTER);

    private boolean once = true;

    // Constructor
    public GameView(GameModel gameModel,TitleModel titleModel) {
        this.gameModel = gameModel;
        this.titleModel = titleModel;
        this.mPlayer = new MusicPlayer();
        this.gameModel.setGUI(this);
        this.layoutView();
        this.registerControllers();
        this.update();
    }

    // Creates the initial layout of the GUI
    public void layoutView() {

        this.setLayout(null); // settoing the layout to null

        File fontFile = new File(directory + "\\src\\res\\HelpMe.ttf");

        // gettoing the width and height of user diplay
        int width = (int)this.screenSize.getWidth(); 
        int height = (int)this.screenSize.getHeight();

        System.out.println((int)Math.round(width*0.005)+" "+ (int)Math.round(height*0.944));

        this.setBounds(0,0,width,height); // setting the size of the game

        this.defend.setBounds((int)Math.round(width*0.416), (int)Math.round(height*0.944), (int)Math.round(width*0.182), (int)Math.round(height*0.046)); // settng the size for the defend buttom

        // setting the colour of the backround
        backGround.setBounds(0,0,width,height);
        backGround.setBackground(Color.BLACK);
        backGround.setOpaque(true);

        
        playerHealth.setBounds((int)Math.round(width*0.005),(int)Math.round(height*0.944),(int)Math.round(width*0.312),(int)Math.round(height*0.046)); // setting the location of player health
        playerHealth.setBackground(Color.RED); // setting colour of player health
        playerHealth.setOpaque(true);

        MonsterHealth.setBounds((int)Math.round(width*0.682),(int)Math.round(height*0.944),(int)Math.round(width*0.312),(int)Math.round(height*0.046)); // setting the location of monster health
        MonsterHealth.setBackground(new Color(170, 34, 34)); // setting colour of monster health
        MonsterHealth.setOpaque(true);

        quicktimeButtonPannel.setBounds(0,0,width,height);
        addButtons(); // gneratting and adding quicktime buttons
        quicktimeButtonPannel.setVisible(false);
        quicktimeButtonPannel.setOpaque(false);

        floorLevel.setText("9");
        floorLevel.setBounds((int)Math.round(width*0.947),(int)Math.round(height*0.009),(int)Math.round(width*0.052),(int)Math.round(height*0.083));
        floorLevel.setForeground(new Color(255,255,255));

        try {
            // setting font and scaling the font
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font sizedFont = font.deriveFont(width*0.052f); // original size 100
            floorLevel.setFont(sizedFont);
        } 
        catch (Exception e) {}

        defend.setVisible(false);

        options.setBounds(660,10,700,200);
        options.setForeground(Color.white);

        optionsPanel.setLayout(null);
        optionsPanel.setBounds(0,0,width,height);
        optionsPanel.setBackground(new Color(20,20,20,20));

        optionsTitle.setBounds((int)Math.round(width*0.390),(int)Math.round(height*0.092),(int)Math.round(width*0.625),(int)Math.round(height*0.092)); // setting scaling for Options title
        optionsTitle.setForeground(new Color(139, 0, 0));

        try {
            // setting font and scaling the font
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font sizedFont = font.deriveFont(width*0.052f); // original size 100
            optionsTitle.setFont(sizedFont);

            Font font1 = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font LableSizedFont = font1.deriveFont(width*0.015f); // original size 30

            fowardKeybind.setFont(LableSizedFont);
            backwardsKeybind.setFont(LableSizedFont);
            leftKeybind.setFont(LableSizedFont);
            rightKeybind.setFont(LableSizedFont);
            volumeLable.setFont(LableSizedFont);
            exitOptions.setFont(LableSizedFont);
            quitGame.setFont(LableSizedFont);

            Font font2 = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font volumeFont = font2.deriveFont(width*0.007f);

            volume.setFont(volumeFont);
        } 
        catch (Exception e) {}

        cFowardKeybind.setBounds((int)Math.round(width*0.677), (int) Math.round(height*0.277), (int)Math.round(width*0.052), (int) Math.round(height*0.046));
        fowardKeybind.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.259), (int)Math.round(width*0.208), (int) Math.round(height*0.092));
        fowardKeybind.setForeground(new Color(139, 0, 0));
        cFowardKeybind.setText(gameModel.forwardKeyBind);

        cBackwardsKeybind.setBounds((int)Math.round(width*0.677), (int) Math.round(height*0.370), (int)Math.round(width*0.052), (int) Math.round(height*0.046));
        backwardsKeybind.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.351), (int)Math.round(width*0.208), (int) Math.round(height*0.092));
        backwardsKeybind.setForeground(new Color(139, 0, 0));
        cBackwardsKeybind.setText(gameModel.backwardsKeyBind);

        cLeftKeybind.setBounds((int)Math.round(width*0.677), (int) Math.round(height*0.462), (int)Math.round(width*0.052), (int) Math.round(height*0.046));
        leftKeybind.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.444), (int)Math.round(width*0.208), (int) Math.round(height*0.092));
        leftKeybind.setForeground(new Color(139, 0, 0));
        cLeftKeybind.setText(gameModel.leftKeyBind);

        cRightKeybind.setBounds((int)Math.round(width*0.677), (int) Math.round(height*0.555), (int)Math.round(width*0.052), (int) Math.round(height*0.046));
        rightKeybind.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.537), (int)Math.round(width*0.208), (int) Math.round(height*0.092));
        rightKeybind.setForeground(new Color(139, 0, 0));
        cRightKeybind.setText(gameModel.rightKeyBind);

        volumeLable.setBounds((int)Math.round(width*0.463), (int) Math.round(height*0.629), (int)Math.round(width*0.208), (int) Math.round(height*0.046));
        volumeLable.setForeground(new Color(139, 0, 0));
        volume.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.722), (int)Math.round(width*0.468), (int) Math.round(height*0.046));
        volume.setMajorTickSpacing(2);
        volume.setMinorTickSpacing(1);
        volume.setPaintTicks(true);
        volume.setPaintLabels(true);
        volume.setForeground(new Color(139, 0, 0));

        exitOptions.setBounds((int)Math.round(width*0.005),(int) Math.round(height*0.009),(int)Math.round(width*0.078),(int) Math.round(height*0.084));
        exitOptions.setForeground(new Color(139, 0, 0));

        quitGame.setBounds((int)Math.round(width*0.005),(int) Math.round(height*0.101),(int)Math.round(width*0.156),(int) Math.round(height*0.084));
        quitGame.setForeground(new Color(139, 0, 0));

        fowardKeybind.setText("Foward Keybind");
        backwardsKeybind.setText("Backward Keybind");

        optionsPanel.add(optionsTitle);
        optionsPanel.add(cFowardKeybind);
        optionsPanel.add(fowardKeybind);
        optionsPanel.add(cBackwardsKeybind);
        optionsPanel.add(backwardsKeybind);
        optionsPanel.add(cLeftKeybind);
        optionsPanel.add(leftKeybind);
        optionsPanel.add(cRightKeybind);
        optionsPanel.add(rightKeybind);
        optionsPanel.add(volume);
        optionsPanel.add(volumeLable);
        optionsPanel.add(exitOptions);
        optionsPanel.add(quitGame);

        optionsPanel.setVisible(false);


        // adding objects to the game
        this.add(playerHealth);
        this.add(defend);
        this.add(MonsterHealth);
        this.add(quicktimeButtonPannel);
        this.add(floorLevel);
        this.add(options);
        this.add(optionsPanel);
        this.add(backGround);
    }// end of game view

    // generates buttons in an array
    public void generateButtons(){
        for(int i = 0; i<18;i++ ){
            buttons[i] = new JButton(String.valueOf(i));
            buttons[i].setVisible(false);
        }
    }

    public void defaultLocations(){
        for(int i = 0;i<18;i++){
            buttons[i].setBounds(0,0,100,100);
        }
    }

    // setting the quicktime location for each button
    public void randomLocations(){
        int width  = gameModel.quickTimeGeneration();
        for(int i = 0;i<gameModel.numOfButtons;i++){
            buttons[i].setBounds(width*i,gameModel.getRandomHeight(),width,100);
            buttons[i].setVisible(true);

        }
    }

    // add
    public void addButtons(){
        generateButtons();
        defaultLocations();
        quicktimeButtonPannel.setLayout(null);
        for(int i = 0; i<18;i++ ){
            quicktimeButtonPannel.add(buttons[i]);
        }

    }

    public void addActionForArray(){
        for(int i = 0;i<18;i++){
            buttons[i].addActionListener(new buttonGameController(gameModel,titleModel, buttons[i]));
        }
    }



    // Registers controllers
    private void registerControllers() {
        //
        buttonGameController bc = new buttonGameController(this.gameModel, titleModel,defend);
        defend.addActionListener(bc);

        keyboardInput keyboardInput = new keyboardInput(this.gameModel);
        this.addKeyListener(keyboardInput);
        this.setFocusable(true);

        buttonGameController exitButtonGameController = new buttonGameController(this.gameModel,titleModel,exitOptions);
        exitOptions.addActionListener(exitButtonGameController);

        buttonGameController quiButtonGameController = new buttonGameController(this.gameModel,titleModel,quitGame);
        quitGame.addActionListener(quiButtonGameController);
        
        addActionForArray();

        cFowardKeybind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModel.forwardKeyBind = cFowardKeybind.getText().toUpperCase();
            }
          });
        cBackwardsKeybind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModel.backwardsKeyBind = cBackwardsKeybind.getText().toUpperCase();
            }
          });
        cRightKeybind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModel.rightKeyBind = cRightKeybind.getText().toUpperCase();
            }
          });
        cLeftKeybind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModel.leftKeyBind = cLeftKeybind.getText().toUpperCase();
            }
          });

        volumeModel = new VolumeController(mPlayer, volume);
        volume.addChangeListener(volumeModel);

    }

    // Updates the GUI based on what happens in the game
    public void update() {

        int width = (int)this.screenSize.getWidth(); 
        int height = (int)this.screenSize.getHeight();


        if (titleModel.startGame && once == true){
            once = false;
            gameModel.game();
        }



        if (gameModel.randomise){
            randomLocations();
        }

        quicktimeButtonPannel.setVisible(gameModel.quickTimeState);
        defend.setVisible(gameModel.displayDefendButton);

        if (gameModel.displayDirections){
            options.setText(gameModel.outputDirections);
            options.setVisible(true);
        }

        if (gameModel.displayOptionsPannel){
            optionsPanel.setVisible(true);
            defend.setVisible(false);
            quicktimeButtonPannel.setVisible(false);
            playerHealth.setVisible(false);
            MonsterHealth.setVisible(false);
            options.setVisible(false);
        }
        else{
            playerHealth.setVisible(true);
            MonsterHealth.setVisible(true);
            options.setVisible(true);
            optionsPanel.setVisible(false);

        }

        

        floorLevel.setText(Integer.toString(gameModel.numOfKeys + 1));

        // MonsterHealth.setBounds((int)Math.round(1310+(1310*((100-gameModel.monsterHealth)/100))),1020,(int)Math.round(600*(gameModel.monsterHealth/100)),50);

        // TODO The monster health is not always 100
        // 610 in
        // Changes the health bar width for the monster depending on the total health
        int healthBarWidth = 0;
        if (gameModel.numOfKeys == 0) {
            healthBarWidth = (int)Math.round((width*0.312)*(gameModel.monsterHealth/100));
        } else if (gameModel.numOfKeys == 1) {
            healthBarWidth = (int)Math.round((width*0.312)*(gameModel.monsterHealth/125));
        } else if (gameModel.numOfKeys == 2) {
            healthBarWidth = (int)Math.round((width*0.312)*(gameModel.monsterHealth/150));
        } else if (gameModel.numOfKeys == 3) {
            healthBarWidth = (int)Math.round((width*0.312)*(gameModel.monsterHealth/175));
        } else if (gameModel.numOfKeys == 4) {
            healthBarWidth = (int)Math.round((width*0.312)*(gameModel.monsterHealth/200));
        }

        MonsterHealth.setBounds((int)Math.round(width*0.682)-healthBarWidth, (int)Math.round(height*0.944), healthBarWidth, 50);

        playerHealth.setBounds((int)Math.round(width*0.005),(int)Math.round(height*0.944),(int)Math.round((width*0.312)*(gameModel.health/100)),50);

        cFowardKeybind.setText(gameModel.forwardKeyBind);
        cBackwardsKeybind.setText(gameModel.backwardsKeyBind);
        cLeftKeybind.setText(gameModel.leftKeyBind);
        cRightKeybind.setText(gameModel.rightKeyBind);

    }
    
} // Closes class