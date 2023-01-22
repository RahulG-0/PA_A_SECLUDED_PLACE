// Program Name: TitleView
// Last Modified: January 22, 2023
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Creates the GUI for the title screen

// Imports
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class TitleView extends JPanel {

    // Creates instance variables
    private TitleModel titleModel; // Instance of model
    public MusicPlayer mPlayer;
    private VolumeController volumeController;

    private JLabel loadingScreenImage = new JLabel(); // Background image
    private JLabel title = new JLabel("A Secluded Place"); // Title

    private JButton newGame = new JButton("New Game"); // Buttons on GUI
    private JButton loadGame = new JButton("Continue Game");
    private JButton settings = new JButton("Options");
    private JButton howTo = new JButton("How To Play");
    private JButton quit = new JButton("Quit");
    private JPanel buttonsPanel = new JPanel(); // JPanel to store buttons

    // Error message for if the save file has not been started or is completed
    private JLabel startNewGame = new JLabel("No game in session. Please start a new game.");
    
    private JLabel howToPlay = new JLabel("Look through the game manual file provided!");

    // Difficulty settings
    private JButton easy = new JButton("Easy"); 
    private JButton medium = new JButton("Medium");
    private JButton hard = new JButton("Hard");
    private JButton demo = new JButton("Demo");
    private JPanel gameModePanel = new JPanel(); // Stores buttons for difficulty

    // JSlider for game music volume
    private JSlider volume = new JSlider(JSlider.HORIZONTAL,-20, 6,0);

    // Sets up keybinds
    private JTextField cFowardKeybind = new JTextField();
    private JTextField cBackwardsKeybind = new JTextField();
    private JTextField cRightKeybind = new JTextField();
    private JTextField cLeftKeybind = new JTextField();

    private JLabel fowardKeybind = new JLabel("Foward Keybind");
    private JLabel backwardsKeybind = new JLabel("Backward Keybind");
    private JLabel rightKeybind = new JLabel("Right Keybind");
    private JLabel leftKeybind = new JLabel("Left Keybind"); 
    private JLabel volumeLabel = new JLabel("Volume"); 

    // Exit button and options panel
    private JButton exitOptions = new JButton("Exit");

    private JLabel optionsTitle = new JLabel("Options");
    private JPanel optionsPanel = new JPanel();

    private GameModel gameModel; // Instance of GameModel

    // Gets directory and screen size
    private String directory = System.getProperty("user.dir");
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Constructor
    public TitleView(TitleModel titleModel, GameModel gameModel) {
        this.mPlayer = new MusicPlayer();
        this.titleModel = titleModel;
        this.gameModel = gameModel;
        this.titleModel.setGUI(this);
        this.layoutView();
        this.registerControllers();
        this.update();
        this.mPlayer.music();
        this.mPlayer.gameMusic();
        this.mPlayer.stop(mPlayer.gameClip);
    }

    // Creates the initial layout of the GUI
    private void layoutView() {
        // Sets the background image
        int width = (int)this.screenSize.getWidth();
        int height = (int)this.screenSize.getHeight();

        loadingScreenImage.setBounds(0, 0, width, height);

        try {
            // setting image and scaling image to fit the icon
            BufferedImage bufferedImage = ImageIO.read(new File(directory + "\\src\\res\\Safeimagekit-resized-img.png")); // Version for VS
            // BufferedImage bufferedImage = ImageIO.read(new File(directory + "\\res\\Safeimagekit-resized-img.png"));
            Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            loadingScreenImage.setIcon(new ImageIcon(image));
        } catch (IOException e1) {}

        // Formatting for title
        title.setBounds((int)Math.round(width*0.234),(int)Math.round(height*0.231),(int)Math.round(width*0.625),(int)Math.round(height*0.092));
        title.setForeground(new Color(139, 0, 0));
        title.setBorder(null);

        File fontFile = new File(directory + "\\src\\res\\HelpMe.ttf"); // Version for VS
        // File fontFile = new File(directory + "\\res\\HelpMe.ttf");
        try {
            // setting font and scaling the font
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font sizedFont = font.deriveFont(width*0.052f); // original size 100
            title.setFont(sizedFont);
        } 
        catch (Exception e) {}

        // Changing the color of the buttons
        newGame.setBackground(new Color(230, 230, 230));
        newGame.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        loadGame.setBackground(new Color(230, 230, 230));
        loadGame.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        howTo.setBackground(new Color(230, 230, 230));
        howTo.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        settings.setBackground(new Color(230, 230, 230));
        settings.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        quit.setBackground(new Color(230, 230, 230));
        quit.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        // Adds the buttons to the buttons panel
        buttonsPanel.setBounds((int)Math.round(width*0.416),(int)Math.round(height*0.462),(int)Math.round(width*0.156),(int)Math.round(height*0.509));
        buttonsPanel.setLayout(new GridLayout(5, 1));
        buttonsPanel.add(newGame);
        buttonsPanel.add(loadGame);
        buttonsPanel.add(howTo);
        buttonsPanel.add(settings);
        buttonsPanel.add(quit);

        // Displays the error message if the user needs to start a new game
        startNewGame.setVisible(false);
        startNewGame.setForeground(new Color(255, 255, 255));
        startNewGame.setBounds((int)Math.round(width*0.625),(int)Math.round(height*0.531),(int)Math.round(width*0.364),(int)Math.round(height*0.185));

        // Displays the instructions on how to play
        howToPlay.setVisible(false);
        howToPlay.setForeground(new Color(255, 255, 255));
        howToPlay.setBounds((int)Math.round(width*0.625),(int)Math.round(height*0.531),(int)Math.round(width*0.364),(int)Math.round(height*0.185));

        // Changes background colors for the game mode buttons
        easy.setBackground(new Color(230, 230, 230));
        easy.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        medium.setBackground(new Color(230, 230, 230));
        medium.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        hard.setBackground(new Color(230, 230, 230));
        hard.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        demo.setBackground(new Color(230, 230, 230));
        demo.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Sets up the panel for displaying the game modes
        gameModePanel.setBounds((int)Math.round(width*0.625),(int)Math.round(height*0.462),(int)Math.round(width*0.104),(int)Math.round(height*0.24));
        gameModePanel.setLayout(new GridLayout(4,1));
        gameModePanel.add(easy);
        gameModePanel.add(medium);
        gameModePanel.add(hard);
        gameModePanel.add(demo);
        gameModePanel.setVisible(false);

        // Sets up the options menu panel
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
            volumeLabel.setFont(LableSizedFont);
            exitOptions.setFont(LableSizedFont);

            Font font2 = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font volumeFont = font2.deriveFont(width*0.007f);

            volume.setFont(volumeFont);
        } 
        catch (Exception e) {}

        // Sets up the keybinds
        cFowardKeybind.setBounds((int)Math.round(width*0.677), (int) Math.round(height*0.277), (int)Math.round(width*0.052), (int) Math.round(height*0.046));
        fowardKeybind.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.259), (int)Math.round(width*0.208), (int) Math.round(height*0.092));
        fowardKeybind.setForeground(new Color(139, 0, 0));

        cBackwardsKeybind.setBounds((int)Math.round(width*0.677), (int) Math.round(height*0.370), (int)Math.round(width*0.052), (int) Math.round(height*0.046));
        backwardsKeybind.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.351), (int)Math.round(width*0.208), (int) Math.round(height*0.092));
        backwardsKeybind.setForeground(new Color(139, 0, 0));

        cLeftKeybind.setBounds((int)Math.round(width*0.677), (int) Math.round(height*0.462), (int)Math.round(width*0.052), (int) Math.round(height*0.046));
        leftKeybind.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.444), (int)Math.round(width*0.208), (int) Math.round(height*0.092));
        leftKeybind.setForeground(new Color(139, 0, 0));

        cRightKeybind.setBounds((int)Math.round(width*0.677), (int) Math.round(height*0.555), (int)Math.round(width*0.052), (int) Math.round(height*0.046));
        rightKeybind.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.537), (int)Math.round(width*0.208), (int) Math.round(height*0.092));
        rightKeybind.setForeground(new Color(139, 0, 0));

        // Sets up the volume slider
        volumeLabel.setBounds((int)Math.round(width*0.463), (int) Math.round(height*0.629), (int)Math.round(width*0.208), (int) Math.round(height*0.046));
        volumeLabel.setForeground(new Color(139, 0, 0));
        volume.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.722), (int)Math.round(width*0.468), (int) Math.round(height*0.046));
        volume.setMajorTickSpacing(2);
        volume.setMinorTickSpacing(1);
        volume.setPaintTicks(true);
        volume.setPaintLabels(true);
        volume.setForeground(new Color(139, 0, 0));

        // The button for exiting the options menu
        exitOptions.setBounds((int)Math.round(width*0.005),(int) Math.round(height*0.009),(int)Math.round(width*0.078),(int) Math.round(height*0.084));
        exitOptions.setForeground(new Color(139, 0, 0));
        exitOptions.setBackground(new Color(230, 230, 230));

        // Keybinds
        fowardKeybind.setText("Foward Keybind");
        backwardsKeybind.setText("Backward Keybind");

        cFowardKeybind.setText(gameModel.forwardKeyBind);
        cBackwardsKeybind.setText(gameModel.backwardsKeyBind);
        cLeftKeybind.setText(gameModel.leftKeyBind);
        cRightKeybind.setText(gameModel.rightKeyBind);

        // Adds everything to the options panel
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
        optionsPanel.add(volumeLabel);
        optionsPanel.add(exitOptions);

        optionsPanel.setVisible(false);

        // Adds everything to the JFrame
        this.setLayout(null);
        this.add(optionsPanel);
        this.add(title);
        this.add(gameModePanel);
        this.add(buttonsPanel);
        this.add(startNewGame);
        this.add(howToPlay);
        this.add(loadingScreenImage);

    }

    // Registers the controller to the buttons
    private void registerControllers() {
        TitleController controller = new TitleController(this.titleModel);
        this.newGame.addActionListener(controller);
        this.loadGame.addActionListener(controller);
        this.settings.addActionListener(controller);
        this.quit.addActionListener(controller);
        this.howTo.addActionListener(controller);

        this.easy.addActionListener(controller);
        this.medium.addActionListener(controller);
        this.hard.addActionListener(controller);
        this.demo.addActionListener(controller);
        this.exitOptions.addActionListener(controller);

        volumeController = new VolumeController(mPlayer, volume);
        volume.addChangeListener(volumeController);

        TextFieldController textFieldController = new TextFieldController(gameModel, cFowardKeybind, cBackwardsKeybind, cLeftKeybind, cRightKeybind);
        cFowardKeybind.addActionListener(textFieldController);
        cBackwardsKeybind.addActionListener(textFieldController);
        cLeftKeybind.addActionListener(textFieldController);
        cRightKeybind.addActionListener(textFieldController);
    }

    // This checks if the save file can be used for game information
    public boolean canFileLoad() {
        File sFile = new File(directory + "\\src\\TextFiles\\SaveFile.txt"); // Version for VS
        // File sFile = new File(directory + "\\TextFiles\\SaveFile.txt");
        Scanner saveFile = null;
        String gameMode = ""; // Information for game
        int numOfKeys = 0;
        double health = 100;
        int smokeBombs = 0;
        double monsterHealth = 100;
        int counter = 0; // Counter to help transfer information from file to variables
        boolean canLoad = false;

        // Accesses the save file
        try {
            saveFile = new Scanner(sFile);
        } catch (FileNotFoundException ex) {}

        // If the file is blank meaning the player hasn't played a game yet
        if (!saveFile.hasNext()) {
            
            gameModePanel.setVisible(false);
            startNewGame.setVisible(true);

        } else {
            // Transfers all information from file to variables
            while (saveFile.hasNext()) {
                if (counter == 0) {
                    gameMode = saveFile.next();
                } else if (counter == 1) {
                    numOfKeys = Integer.parseInt(saveFile.next());
                } else if (counter == 2) {
                    health = Double.parseDouble(saveFile.next());
                    // health = Integer.parseInt(saveFile.next());
                } else if (counter == 3) {
                    smokeBombs = Integer.parseInt(saveFile.next());
                } else if (counter == 4) {
                    monsterHealth = Double.parseDouble(saveFile.next());
                }

                counter++;
            }

            // Makes sure that the save file is not already completed
            if (gameMode.equals("EASY") && numOfKeys == 3) {
                gameModePanel.setVisible(false);
                howToPlay.setVisible(false);
                startNewGame.setVisible(true);
            } else if (gameMode.equals("MEDIUM") && numOfKeys == 4) {
                gameModePanel.setVisible(false);
                howToPlay.setVisible(false);
                startNewGame.setVisible(true);
            } else if (gameMode.equals("HARD") && numOfKeys == 5) {
                gameModePanel.setVisible(false);
                howToPlay.setVisible(false);
                startNewGame.setVisible(true);
            } else if (gameMode.equals("DEMO") && numOfKeys == 1) {
                gameModePanel.setVisible(false);
                howToPlay.setVisible(false);
                startNewGame.setVisible(true);
            } else {
                // Passes the information to the GameModel and sets canLoad to true
                if (this.titleModel.userSelection.equals("load")) {
                    gameModel.once = true;
                    gameModel.gameOver = false;
                    this.mPlayer.stop(this.mPlayer.clip);
                    this.mPlayer.gameMusic();
                    this.gameModel.setInfo(gameMode, numOfKeys, health, smokeBombs, monsterHealth);
                    // buttonsPanel.setVisible(false);
                    this.gameModel.update();
                    gameModel.leftGame = false;

                    canLoad = true;
                } else {
                    canLoad = true;
                }
            }
        }

        // Closes the file
        saveFile.close();

        // Returns whether the file can be loaded or not
        return(canLoad);
    }

    // Updates the GUI
    public void update() {

        if (titleModel.once){
            this.mPlayer.music();
            titleModel.once = false;
        }

        if(gameModel.leftGame){
            try {
                this.mPlayer.stop(mPlayer.gameClip);
            } catch (Exception e) {}
        }

        // Updates the GUI based on which button the user selects
        buttonsPanel.setVisible(true);

        if (this.titleModel.userSelection.equals("new")) {

            // Displays buttons to select game mode
            startNewGame.setVisible(false);
            howToPlay.setVisible(false);
            gameModePanel.setVisible(true);

            // Creates the game if the user has selected a game difficulty
            if (this.titleModel.gameDifficulty.equals("Easy")) {
                this.gameModel.gameOver = false;
                this.gameModel.once = true;
                gameModePanel.setVisible(false);
                this.gameModel.setInfo("EASY", 0, 100, 3, 100);
                this.gameModel.update();
                this.mPlayer.stop(mPlayer.clip);
                this.mPlayer.gameMusic();
                gameModel.leftGame = false;
            } else if (this.titleModel.gameDifficulty.equals("Medium")) {
                this.gameModel.gameOver = false;
                this.gameModel.once = true;
                gameModePanel.setVisible(false);
                this.gameModel.setInfo("MEDIUM", 0, 100, 2, 100);
                this.gameModel.update();
                this.mPlayer.stop(mPlayer.clip);
                this.mPlayer.gameMusic();
                gameModel.leftGame = false;
            } else if (this.titleModel.gameDifficulty.equals("Hard")) {
                this.gameModel.gameOver = false;
                this.gameModel.once = true;
                gameModePanel.setVisible(false);
                this.gameModel.setInfo("HARD", 0, 100, 1, 100);
                this.gameModel.update();
                this.mPlayer.stop(mPlayer.clip);
                this.mPlayer.gameMusic();
                gameModel.leftGame = false;
            } else if (this.titleModel.gameDifficulty.equals("Demo")) {
                this.gameModel.gameOver = false;
                this.gameModel.once = true;
                gameModePanel.setVisible(false);
                this.gameModel.setInfo("DEMO", 0, 100, 5, 100);
                this.gameModel.update();
                this.mPlayer.stop(mPlayer.clip);
                this.mPlayer.gameMusic();
                gameModel.leftGame = false;
            }
            
        } else if (this.titleModel.userSelection.equals("load")) {
            // Checks to see if the file can be loaded and starts game if the user selected load
            canFileLoad();

        } else if (this.titleModel.userSelection.equals("info")) {

            // Displays the how to play message
            startNewGame.setVisible(false);
            gameModePanel.setVisible(false);
            howToPlay.setVisible(true);
        
        } else if (this.titleModel.userSelection.equals("settings")) {

            // Makes settings visible
            optionsPanel.setVisible(true);
            title.setVisible(false);
            gameModePanel.setVisible(false);
            buttonsPanel.setVisible(false);
            startNewGame.setVisible(false);
            howToPlay.setVisible(false);


        } else if (this.titleModel.userSelection.equals("exit")) {
            
            // Closes the program
            this.mPlayer.stop(mPlayer.clip);
            this.mPlayer.stop(mPlayer.gameClip);
            Window win = SwingUtilities.getWindowAncestor(this);
            win.dispose();
            System.exit(0);

        } else if(this.titleModel.userSelection.equals("Quit")){

            optionsPanel.setVisible(false);
            title.setVisible(true);
            buttonsPanel.setVisible(true);
            startNewGame.setVisible(false);
            
        }

        cFowardKeybind.setText(gameModel.forwardKeyBind);
        cBackwardsKeybind.setText(gameModel.backwardsKeyBind);
        cLeftKeybind.setText(gameModel.leftKeyBind);
        cRightKeybind.setText(gameModel.rightKeyBind);
    }// end of update
    
} // Closes class