// Program Name: TitleView
// Last Modified:
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
    private MusicPlayer mPlayer;
    private VolumeController volumeModel;

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
    
    // Difficulty settings
    private JButton easy = new JButton("Easy"); 
    private JButton medium = new JButton("Medium");
    private JButton hard = new JButton("Hard");
    private JPanel gameModePanel = new JPanel(); // Stores buttons for difficulty

    private JSlider volume = new JSlider(JSlider.HORIZONTAL,-20, 6,0);
    private JTextField cFowardKeybind = new JTextField();
    private JTextField cBackwardsKeybind = new JTextField();
    private JTextField cRightKeybind = new JTextField();
    private JTextField cLeftKeybind = new JTextField();

    private JLabel fowardKeybind = new JLabel("Foward Keybind");
    private JLabel backwardsKeybind = new JLabel("Backward Keybing");
    private JLabel rightKeybind = new JLabel("Right Keybind");
    private JLabel leftKeybind = new JLabel("Left Keybind"); 
    private JLabel volumeLable = new JLabel("Volume"); 

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
    }

    // Creates the initial layout of the GUI
    private void layoutView() {
        // Sets the background image
        int width = (int)this.screenSize.getWidth();
        int height = (int)this.screenSize.getHeight();

        loadingScreenImage.setBounds(0, 0, width, height);
        // loadingScreenImage.setIcon(new ImageIcon(directory + "\\src\\res\\Safeimagekit-resized-img.png"));

        try {
            // setting image and scaling image to fit the icon
            BufferedImage bufferedImage = ImageIO.read(new File(directory + "\\src\\res\\Safeimagekit-resized-img.png")); 
            Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            loadingScreenImage.setIcon(new ImageIcon(image));
        } catch (IOException e1) {}

        // Formatting for title
        title.setBounds((int)Math.round(width*0.234),(int)Math.round(height*0.231),(int)Math.round(width*0.625),(int)Math.round(height*0.092)); // setting scaling for title
        // title.setBounds(450,250,1200,100);
        title.setForeground(new Color(139, 0, 0));
        title.setBorder(null);

        File fontFile = new File(directory + "\\src\\res\\HelpMe.ttf");
        try {
            // setting font and scaling the font
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font sizedFont = font.deriveFont(width*0.052f); // original size 100
            title.setFont(sizedFont);
        } 
        catch (Exception e) {}

        // Adds the buttons to the buttons panel
        buttonsPanel.setBounds((int)Math.round(width*0.416),(int)Math.round(height*0.462),(int)Math.round(width*0.156),(int)Math.round(height*0.509));
        // buttonsPanel.setBounds(800,500,300,550);
        buttonsPanel.setLayout(new GridLayout(5, 1));
        buttonsPanel.add(newGame);
        buttonsPanel.add(loadGame);
        buttonsPanel.add(howTo);
        buttonsPanel.add(settings);
        buttonsPanel.add(quit);


        startNewGame.setVisible(false);
        startNewGame.setForeground(new Color(255, 255, 255));
        startNewGame.setBounds((int)Math.round(width*0.625),(int)Math.round(height*0.531),(int)Math.round(width*0.364),(int)Math.round(height*0.185));

        //
        gameModePanel.setBounds((int)Math.round(width*0.625),(int)Math.round(height*0.462),(int)Math.round(width*0.104),(int)Math.round(height*0.185));
        // gameModePanel.setBounds(1200,500,200,200);
        gameModePanel.setLayout(new GridLayout(3,1));

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

            Font font2 = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font volumeFont = font2.deriveFont(width*0.007f);

            volume.setFont(volumeFont);
        } 
        catch (Exception e) {}

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

        volumeLable.setBounds((int)Math.round(width*0.463), (int) Math.round(height*0.629), (int)Math.round(width*0.208), (int) Math.round(height*0.046));
        volumeLable.setForeground(new Color(139, 0, 0));
        volume.setBounds((int)Math.round(width*0.260), (int) Math.round(height*0.722), (int)Math.round(width*0.468), (int) Math.round(height*0.046));
        volume.setMajorTickSpacing(2);
        volume.setMinorTickSpacing(1);
        volume.setPaintTicks(true);
        volume.setPaintLabels(true);
        volume.setForeground(new Color(139, 0, 0));

        exitOptions.setBounds(10,10,150,90);
        exitOptions.setForeground(new Color(139, 0, 0));

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

        optionsPanel.setVisible(false);

        gameModePanel.add(easy);
        gameModePanel.add(medium);
        gameModePanel.add(hard);
        gameModePanel.setVisible(false);

        this.setLayout(null);

        this.add(optionsPanel);
        this.add(title);
        this.add(gameModePanel);
        this.add(buttonsPanel);
        this.add(startNewGame);
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
        this.exitOptions.addActionListener(controller);

        volumeModel = new VolumeController(mPlayer, volume);
        volume.addChangeListener(volumeModel);
    }

    // This checks if the save file can be used for game information
    public boolean canFileLoad() {
        File sFile = new File(directory + "\\src\\SaveFile\\SaveFile.txt"); // Directory of file
        Scanner saveFile = null;
        String gameMode = ""; // Information for game
        int numOfKeys = 0;
        int health = 100;
        int flashbangs = 0;
        int counter = 0; // Counter to help transfer information from file to variables
        boolean canLoad = false;

        // Accesses the save file
        try {
            saveFile = new Scanner(sFile);
        } catch (FileNotFoundException ex) {
            System.out.println("Error.");
        }

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
                    health = Integer.parseInt(saveFile.next());
                } else if (counter == 3) {
                    flashbangs = Integer.parseInt(saveFile.next());
                }

                counter++;
            }

            // Passes the information to the GameModel and sets canLoad to true
            this.gameModel.SetInfo(gameMode, numOfKeys, health, flashbangs);
            buttonsPanel.setVisible(false);
            this.mPlayer.stop();
            this.gameModel.update();
            canLoad = true;
        }

        // Closes the file
        saveFile.close();

        // Returns whether the file can be loaded or not
        if (canLoad) {
            return(true);
        } else {
            return(false);
        }
    }
    

    // Updates the GUI with the answer
    public void update() {
        // Updates the GUI based on which button the user selects
        buttonsPanel.setVisible(true);

        if (this.titleModel.userSelection.equals("new")) {

            // Displays buttons to select game mode
            startNewGame.setVisible(false);
            gameModePanel.setVisible(true);

            // Creates the game if the user has selected a game difficulty
            if (this.titleModel.gameDifficulty.equals("Easy")) {
                gameModePanel.setVisible(false);
                this.gameModel.SetInfo("Easy", 0, 100, 3);
                this.gameModel.update();
                this.mPlayer.stop();
            } else if (this.titleModel.gameDifficulty.equals("Medium")) {
                gameModePanel.setVisible(false);
                this.gameModel.SetInfo("Medium", 0, 100, 2);
                this.gameModel.update();
                this.mPlayer.stop();
            } else if (this.titleModel.gameDifficulty.equals("Hard")) {
                gameModePanel.setVisible(false);
                this.gameModel.SetInfo("Hard", 0, 100, 1);
                this.gameModel.update();
                this.mPlayer.stop();
            }
            
        } else if (this.titleModel.userSelection.equals("load")) {

            // Checks to see if the file can be loaded
            canFileLoad();

        } else if (this.titleModel.userSelection.equals("settings")) {

            // setting settings to visible
            optionsPanel.setVisible(true);
            title.setVisible(false);
            gameModePanel.setVisible(false);
            buttonsPanel.setVisible(false);
            startNewGame.setVisible(false);


        } else if (this.titleModel.userSelection.equals("exit")) {
            
            // Closes the program
            this.mPlayer.stop();
            Window win = SwingUtilities.getWindowAncestor(this);
            win.dispose();
        } else if(this.titleModel.userSelection.equals("Quit")){
            optionsPanel.setVisible(false);
            title.setVisible(true);
            buttonsPanel.setVisible(true);
            startNewGame.setVisible(false);
        }
    }
    
} // Closes class