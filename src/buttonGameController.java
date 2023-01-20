import java.awt.event.*;

import javax.swing.JButton;

public class buttonGameController implements ActionListener {
    private GameModel gameModel;
    TitleModel titleModel;
    private MusicPlayer mPlayer;
    private JButton aButton;

    // Constructor
    public buttonGameController(GameModel gameModel,TitleModel titleModel, JButton aButton) {
        this.gameModel = gameModel;
        this.mPlayer = new MusicPlayer();
        this.titleModel = titleModel;
        this.aButton = aButton;
    }

    // Checks to see if a button is pressed on the title page and passes it on to TitleModel
    public void actionPerformed(ActionEvent e) {
        if(((JButton)aButton).toString().contains("text=Defend")){
            this.gameModel.defendButton = true;
            this.gameModel.displayDefendButton = false;

        }

        if(((JButton)aButton).toString().contains("text=Exit")){
            gameModel.displayOptionsPannel = false;

        }

        if(((JButton)aButton).toString().contains("text=Quit_Game")){

            gameModel.displayOptionsPannel = false;
            titleModel.startGame = false;
            titleModel.gameDifficulty = "";

        }

        for(int i = 0; i<18;i++){
            if(((JButton)aButton).toString().contains("text="+(i))){
                gameModel.buttonVisible[i] = false;
                gameModel.amountClicked++;
            }
        }
        
        this.mPlayer.buttonSound();
        gameModel.update();

    }
}
