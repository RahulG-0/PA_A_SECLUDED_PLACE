// Program Name: ButtonGameController
// Last Modified: January 22, 2023
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Controller for the buttons used in the actual game

// Imports
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class ButtonGameController implements ActionListener {

    // Instance variables
    private GameModel gameModel;
    TitleModel titleModel;
    TitleView titleView;
    private MusicPlayer mPlayer;
    private JButton aButton;

    // Constructor
    public ButtonGameController(GameModel gameModel,TitleModel titleModel, JButton aButton) {
        this.gameModel = gameModel;
        this.mPlayer = new MusicPlayer();
        this.titleModel = titleModel;
        this.aButton = aButton;
    }

    // Checks through all buttons used in the game
    public void actionPerformed(ActionEvent e) {

        // Checks defend button
        if (aButton.getText().equals("Defend")) {
            this.gameModel.defendButton = true;
            this.gameModel.displayDefendButton = false;
            gameModel.update();
        }

        // Checks exit button for the options panel
        if (aButton.getText().equals("Exit")) {
            gameModel.displayOptionsPanel = false;
            gameModel.update();
        }

        // Checks quit game button on options panel
        if (aButton.getText().equals("Quit Game")) {
            // Sets variables so quitting the game does not cause errors
            gameModel.displayOptionsPanel = false;
            titleModel.startGame = false;
            titleModel.gameDifficulty = "nonbe";
            titleModel.userSelection = "";
            gameModel.leftGame = true;
            titleModel.once = true;

            // Output to save file
            // File saveFile = new File(gameModel.directory + "\\src\\TextFiles\\SaveFile.txt"); // Version for VS
            File saveFile = new File(gameModel.directory + "\\TextFiles\\SaveFile.txt");
            PrintWriter output = gameModel.getPrintWriter(saveFile);
            output.println(gameModel.gameMode + "\n" + gameModel.numOfKeys + "\n" + gameModel.health + "\n" + gameModel.smokeBombs + "\n" + gameModel.monsterHealth);
            output.close();

            // Calls update on both models so it can switch
            gameModel.gameOver = true;
            titleModel.update();
            gameModel.update();
            
        }

        // Checks use button for the smoke bomb
        if (aButton.getText().equals("Use")) {
            gameModel.useSmokeBomb = true;
            gameModel.update();
        }

        // Checks the quit to title button on the game over screen
        if (aButton.getText().equals("Quit to Title Screen")) {
            // Quit to the title screen
            titleModel.startGame = false;
            titleModel.gameDifficulty = "nonbe";
            titleModel.userSelection = "";
            gameModel.leftGame = true;
            titleModel.once = true;
            gameModel.displayOptionsPanel = false;
            gameModel.gameOver = true;

            // Clears the text file
            // File saveFile = new File(gameModel.directory + "\\src\\TextFiles\\SaveFile.txt"); // Version for VS
            File saveFile = new File(gameModel.directory + "\\TextFiles\\SaveFile.txt");
            PrintWriter output = gameModel.getPrintWriter(saveFile);
            output.flush();
            output.close();
            
            titleModel.update();
            gameModel.update();
        }

        // Checks the randomly generated buttons and sets them to false
        for(int i = 0; i<18;i++){
            if(aButton.getText().equals(Integer.toString(i))){
                aButton.setVisible(false);
                gameModel.amountClicked++;
                gameModel.update();
            }
        }
        
        // Plays the defend button noise
        this.mPlayer.defendButtonClick();

    } // Closes actionPerformed method
} // Closes class
