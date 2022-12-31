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

    public TotalView() {
        this.titleModel = new TitleModel();
        this.gameModel = new GameModel();

        this.title = new TitleView(titleModel);
        this.game = new GameView(gameModel);

        this.totalModel = new TotalModel();
        this.layoutView();
        totalModel.setGUI(this);

        registerControllers();

        this.update();
    }

    private void layoutView(){

        this.setLayout(null);
        title.setBounds(0,0,1920,1080);
        game.setBounds(0,0,1920,1080);
        this.add(game);
        this.add(title);

    }

    public void registerControllers(){
        MouseController mc = new MouseController(this.totalModel);

        game.addMouseListener(mc);
        title.addMouseListener(mc);
        this.addMouseListener(new MouseController(this.totalModel) );

    }


    public void update(){
        
        title.setVisible(!titleModel.startGame);
        game.setVisible(titleModel.startGame);

    }

    
}
