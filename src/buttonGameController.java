import java.awt.event.*;

public class buttonGameController implements ActionListener {
    private GameModel gameModel;
    private MusicPlayer mPlayer;

    // Constructor
    public buttonGameController(GameModel gameModel) {
        this.gameModel = gameModel;
        this.mPlayer = new MusicPlayer();
    }

    // Checks to see if a button is pressed on the title page and passes it on to TitleModel
    public void actionPerformed(ActionEvent e) {
        this.gameModel.defendButton = true;
        this.mPlayer.buttonSound();
        gameModel.update();
    }
}
