import javax.swing.*;

import javafx.scene.effect.ColorAdjust;

// import javafx.scene.paint.Color;

import java.awt.*;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class TitleView extends JPanel{

    public JLabel loadingScreenImage;
    public JFrame loadingScreen;

    public JLabel title;

    private TitleModel titleModel = new TitleModel();

    // Default buttons
    public JButton newGame= new JButton("New Game");
    public JButton loadGame = new JButton("Continue Game");
    public JButton settings = new JButton("Options");
    public JButton quit = new JButton("Quit");

    private JLabel startNewGame = new JLabel("You need to start a new game.");

    // Different game modes
    private JButton easy = new JButton("Easy");
    private JButton medium = new JButton("Medium");
    private JButton hard = new JButton("Hard");
    private JPanel gameModePanel = new JPanel();
    
    // Gets directory and screen size
    private String directory = System.getProperty("user.dir");
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public JPanel menu;

    public TitleView(TitleModel aGame)
    {
        super();
        LayoutView();
        this.registerControllers();
        this.update();
    }

    public void LayoutView(){

        loadingScreenImage = new JLabel();
        int width = (int)this.screenSize.getWidth();
        int height = (int)this.screenSize.getHeight();

        loadingScreenImage.setBounds(0, 0, width, height);
        loadingScreenImage.setIcon(new ImageIcon(directory + "\\Safeimagekit-resized-img.png"));
       

        title =  new JLabel();
        title.setText("A Secluded Place");
        title.setBounds(450,250,1200,100);
        // title.setEditable(false);
        title.setForeground(new Color(139, 0, 0));
        // title.setBackground(new Color(0,0,0,70));
        title.setBorder(null);

        File font_file = new File(directory + "\\HelpMe.ttf");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, font_file);
            Font sizedFont = font.deriveFont(100f);
            title.setFont(sizedFont);
        } catch (FontFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        menu = new JPanel();
        
        gameModePanel.add(easy);
        gameModePanel.add(medium);
        gameModePanel.add(hard);
        gameModePanel.setVisible(false);

        menu.add(newGame);
        menu.add(loadGame);
        menu.add(settings);
        menu.add(quit);
        menu.add(gameModePanel);

        menu.setBounds(800,500,300,550);


        this.setSize(1920,1080);
        this.setLayout(null);
        this.add(title);
        this.add(menu);
        this.add(loadingScreenImage);
        this.setVisible(true);

    }   

    // Registers the controller to the buttons
    private void registerControllers() {
        TitleController controller = new TitleController(this.titleModel, this.newGame);
        this.newGame.addActionListener(controller);
        this.loadGame.addActionListener(controller);
        this.settings.addActionListener(controller);
        this.quit.addActionListener(controller);
    }

    // This takes the player into another JFrame for the actual game
    public void goToGame(String gameMode, int numOfKeys, int health, ArrayList<String> inventory) {
        GameModel model = new GameModel(gameMode, numOfKeys, health, inventory);
        GameView view = new GameView(model);

        JFrame frame = new JFrame("A Secluded Place");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(view);
        frame.setVisible(true);
    }

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
                this.goToGame("Easy", 0, 100, null);
            } else if (this.titleModel.gameDifficulty.equals("Medium")) {
                this.goToGame("Medium", 0, 100, null);
            } else if (this.titleModel.gameDifficulty.equals("Hard")) {
                this.goToGame("Hard", 0, 100, null);
            }
            
        } else if (this.titleModel.userSelection.equals("load")) {

            ////////////////// CHANGE NEWDIR TO MAKE IT WORK WITH EVERY COMPUTER
            // This opens the save file
            String newDir = directory.replace("\\src", "");
            File sFile = new File(newDir + "\\SaveFile.txt");
            // File sFile = new File(directory + "\\SaveFile.txt"); THIS IS HOW IT WOULD WORK
            Scanner saveFile = null;
            String gameMode = "";
            int numOfKeys = 0;
            int health = 100;
            ArrayList<String> inventory = new ArrayList<String>();
            int counter = 0;

            // Tries to access the save file
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
                    } else if (counter >= 3) {
                        inventory.add(saveFile.next());
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
                    this.goToGame(gameMode, numOfKeys, health, inventory);
                }
            }

            saveFile.close();

        } else if (this.titleModel.userSelection.equals("settings")) {
            // TODO Create a settings menu
        } else if (this.titleModel.userSelection.equals("exit")) {
            System.exit(0);
        }
    }

}