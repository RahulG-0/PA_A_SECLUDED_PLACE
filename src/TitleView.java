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

    private JLabel loadingScreenImage = new JLabel(); // Background image
    private JLabel title = new JLabel("A Secluded Place"); // Title

    private JButton newGame = new JButton("New Game"); // Buttons on GUI
    private JButton loadGame = new JButton("Continue Game");
    private JButton settings = new JButton("Options");
    private JButton quit = new JButton("Quit");
    private JPanel buttonsPanel = new JPanel(); // JPanel to store buttons

    // Error message for if the save file has not been started or is completed
    private JLabel startNewGame = new JLabel("No game in session. Please start a new game.");
    
    // Difficulty settings
    private JButton easy = new JButton("Easy"); 
    private JButton medium = new JButton("Medium");
    private JButton hard = new JButton("Hard");
    private JPanel gameModePanel = new JPanel(); // Stores buttons for difficulty

    private GameModel gameModel; // Instance of GameModel

    // Gets directory and screen size
    private String directory = System.getProperty("user.dir");
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Constructor
    public TitleView(TitleModel titleModel, GameModel gameModel) {
        this.titleModel = titleModel;
        this.gameModel = gameModel;
        this.titleModel.setGUI(this);
        this.layoutView();
        this.registerControllers();
        this.update();
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
        catch (FontFormatException e) {} 
        catch (IOException e){}

        // Adds the buttons to the buttons panel
        buttonsPanel.setBounds((int)Math.round(width*0.416),(int)Math.round(height*0.462),(int)Math.round(width*0.156),(int)Math.round(height*0.509));
        // buttonsPanel.setBounds(800,500,300,550);
        buttonsPanel.setLayout(new GridLayout(4, 1));
        buttonsPanel.add(newGame);
        buttonsPanel.add(loadGame);
        buttonsPanel.add(settings);
        buttonsPanel.add(quit);


        startNewGame.setVisible(false);

        //
        gameModePanel.setBounds((int)Math.round(width*0.625),(int)Math.round(height*0.462),(int)Math.round(width*0.104),(int)Math.round(height*0.185));
        // gameModePanel.setBounds(1200,500,200,200);
        gameModePanel.setLayout(new GridLayout(3,1));

        gameModePanel.add(easy);
        gameModePanel.add(medium);
        gameModePanel.add(hard);
        gameModePanel.setVisible(false);

        this.setLayout(null);

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

        this.easy.addActionListener(controller);
        this.medium.addActionListener(controller);
        this.hard.addActionListener(controller);
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
        if (this.titleModel.userSelection.equals("new")) {

            // Displays buttons to select game mode
            startNewGame.setVisible(false);
            gameModePanel.setVisible(true);

            // Creates the game if the user has selected a game difficulty
            if (this.titleModel.gameDifficulty.equals("Easy")) {
                gameModePanel.setVisible(false);
                this.gameModel.SetInfo("Easy", 0, 100, 3);
                this.gameModel.update();
            } else if (this.titleModel.gameDifficulty.equals("Medium")) {
                gameModePanel.setVisible(false);
                this.gameModel.SetInfo("Medium", 0, 100, 2);
                this.gameModel.update();
            } else if (this.titleModel.gameDifficulty.equals("Hard")) {
                gameModePanel.setVisible(false);
                this.gameModel.SetInfo("Hard", 0, 100, 1);
                this.gameModel.update();
            }
            
        } else if (this.titleModel.userSelection.equals("load")) {

            // Checks to see if the file can be loaded
            gameModePanel.setVisible(false);
            canFileLoad();

        } else if (this.titleModel.userSelection.equals("settings")) {
            // TODO Create a settings menu
        } else if (this.titleModel.userSelection.equals("exit")) {
            
            // Closes the program
            Window win = SwingUtilities.getWindowAncestor(this);
            win.dispose();

        }
    }
    
} // Closes class