// Program Name: TotalView
// Last Modified: January 22, 2023
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Main view for the game

// Imports
import javax.swing.*;
public class TotalView extends JPanel{

    // Instance variables
    private TitleModel titleModel;
    private GameModel gameModel;

    private TotalModel totalModel;

    private TitleView title;
    private GameView game;

    // Constructor
    public TotalView() {
        this.titleModel = new TitleModel();
        this.gameModel = new GameModel();

        this.title = new TitleView(titleModel, gameModel);
        this.game = new GameView(gameModel,titleModel);

        this.totalModel = new TotalModel();
        this.layoutView();
        totalModel.setGUI(this);

        this.gameModel.addTotalModel(totalModel);

        this.registerControllers();

        this.update();
    }

    // Creates the initial view with the game and title view
    private void layoutView() {
        this.setLayout(null);
        title.setBounds(0,0,1920,1080);
        game.setBounds(0,0,1920,1080);
        this.add(game);
        this.add(title);
    }

    // Registers the mouse controller
    public void registerControllers(){
        MouseController mc = new MouseController(this.totalModel);

        game.addMouseListener(mc);
        title.addMouseListener(mc);
        this.addMouseListener(new MouseController(this.totalModel));
    }

    // Updates the TotalView by switching which view is displayed
    public void update(){
        title.setVisible(!titleModel.startGame);
        game.setVisible(titleModel.startGame);

        if(titleModel.startGame){
            game.setFocusable(true);
            game.isFocusable();
            game.requestFocus();
            game.requestFocusInWindow();
        }
    }   
} // Closes class