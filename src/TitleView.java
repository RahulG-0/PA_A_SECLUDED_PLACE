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

    private JLabel startNewGame = new JLabel("You need to start a new game."); // button that staarts the game
    // difficulty settings
    private JButton easy = new JButton("Easy"); 
    private JButton medium = new JButton("Medium");
    private JButton hard = new JButton("Hard");
    private JPanel gameModePanel = new JPanel();
    private GameModel gameModel; // pannel that holds the difficulkty setting butttons

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
        loadingScreenImage.setIcon(new ImageIcon(directory + "\\src\\res\\Safeimagekit-resized-img.png"));

        // Formatting for title
        title.setBounds(450,250,1200,100);
        // title.setBounds(width/2, height/6, 1200, 100); TODO Try to make it more responsive
        title.setForeground(new Color(139, 0, 0));
        title.setBorder(null);

        File fontFile = new File(directory + "\\src\\res\\HelpMe.ttf");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font sizedFont = font.deriveFont(100f);
            title.setFont(sizedFont);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Adds the buttons to the buttons panel
        buttonsPanel.setBounds(800,500,300,550);
        buttonsPanel.setLayout(new GridLayout(4, 1));
        buttonsPanel.add(newGame);
        buttonsPanel.add(loadGame);
        buttonsPanel.add(settings);
        buttonsPanel.add(quit);


        startNewGame.setVisible(false);

        //
        gameModePanel.setBounds(1200,500,200,200);
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
    
    // This takes the player into another JFrame for the actual game
    

    // Updates the GUI with the answer
    public void update() {
        // Updates the GUI based on which button the user selects
        if (this.titleModel.userSelection.equals("new")) {
            // TODO Display buttons to select game mode
            startNewGame.setVisible(false);
            gameModePanel.setVisible(true);
            this.titleModel.gameModeFlag = 1;

            // Creates the game if the user has selected a game difficulty
            if (this.titleModel.gameDifficulty.equals("Easy")) {
                this.gameModel.getInfo("Easy", 0, 100, 3);
            } else if (this.titleModel.gameDifficulty.equals("Medium")) {
                this.gameModel.getInfo("Medium", 0, 100, 2);
            } else if (this.titleModel.gameDifficulty.equals("Hard")) {
                this.gameModel.getInfo("Hard", 0, 100, 1);
            }
            
        } else if (this.titleModel.userSelection.equals("load")) {
            gameModePanel.setVisible(false);

            ////////////////// CHANGE NEWDIR TO MAKE IT WORK WITH EVERY COMPUTER
            // This opens the save file
            /*
            String newDir = directory.replace("\\src", "");
            File sFile = new File(newDir + "\\SaveFile.txt"); */
            File sFile = new File(directory + "\\src\\SaveFile\\SaveFile.txt");
            Scanner saveFile = null;
            String gameMode = "";
            int numOfKeys = 0;
            int health = 100;
            int flashbangs = 0;
            int counter = 0;

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

                // Checks to see if the save file is already completed
                if (gameMode.equals("Easy") && numOfKeys == 3) {
                    startNewGame.setVisible(true);
                } else if (gameMode.equals("Medium") && numOfKeys == 4) {
                    startNewGame.setVisible(true);
                } else if (gameMode.equals("Hard") && numOfKeys == 5) {
                    startNewGame.setVisible(true);
                } else {
                    this.gameModel.getInfo(gameMode, numOfKeys, health, flashbangs);
                }
            }

            saveFile.close();

        } else if (this.titleModel.userSelection.equals("settings")) {
            // TODO Create a settings menu
        } else if (this.titleModel.userSelection.equals("exit")) {
            Window win = SwingUtilities.getWindowAncestor(this);
            win.dispose();
        }
    }
    
} // Closes class