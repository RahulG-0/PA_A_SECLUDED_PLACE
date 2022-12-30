// Imports
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class TotalView extends JPanel{

    private TitleModel titleModel;
    private GameModel gameModel;

    private TotalModel totalModel;

    private TitleView title;
    private GameView game;

    public TotalView(TotalModel totalModel) {
        this.titleModel = new  TitleModel();
        this.gameModel = new GameModel();

        this.title = new TitleView(titleModel);
        this.game = new GameView(gameModel);

        this.totalModel = totalModel;
        totalModel.setGUI(this);

        this.layoutView();
        this.update();
    }

    private void layoutView(){

        this.setLayout(null);
        title.setBounds(0,0,1920,1080);
        game.setBounds(0,0,1920,1080);
        this.add(game);
        game.setVisible(false);
        this.add(title);

        
    }

    public void update(){

        if(!titleModel.getStartGame()){
            this.remove(game);
            this.add(title);
        }
        else {
            this.remove(title);
            this.add(game);
            game.setVisible(true);
        }
    }

    
}
