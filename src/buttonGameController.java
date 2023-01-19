import java.awt.event.*;

import javax.swing.JButton;

public class buttonGameController implements ActionListener {
    private GameModel gameModel;
    private MusicPlayer mPlayer;
    private JButton aButton;

    // Constructor
    public buttonGameController(GameModel gameModel, JButton aButton) {
        this.gameModel = gameModel;
        this.mPlayer = new MusicPlayer();
        this.aButton = aButton;
    }

    // Checks to see if a button is pressed on the title page and passes it on to TitleModel
    public void actionPerformed(ActionEvent e) {
        if(((JButton)aButton).toString().contains("text=Defend")){
            this.gameModel.defendButton = true;
            this.gameModel.displayDefendButton = false;

        }
        for(int i = 0; i<18;i++){
            if(((JButton)aButton).toString().contains("text="+(i))){
                gameModel.buttonVisible[i] = false;
                System.out.println("bob");
                gameModel.amountClicked++;
            }
        }
        
        this.mPlayer.buttonSound();
        gameModel.update();
    }
}
