// Program Name: ButtonController
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: This is the controller for the buttons on the title screen

// Imports
import java.awt.event.*;
// import javax.swing.*;

public class TitleController implements ActionListener {

    // Creates the model and the buttons
    private TitleModel titleModel;
    // private JButton button;

    // Constructor
    public TitleController(TitleModel titleModel) {
        this.titleModel = titleModel;
        // this.button = button;
    }

    // Checks to see if a button is pressed
    public void actionPerformed(ActionEvent e) {

        // if (((JButton)this.button).toString().contains("text=New Game")) {
        //     this.titleModel.getSelected("New Game");
        // }
        
        this.titleModel.getSelected(e.getActionCommand());
        
        // Add a check here to make send the button information to the right place
        // if (this.titleModel.gameModeFlag == 0) {
        //     this.titleModel.getSelected(e.getActionCommand());
        // } else if (this.titleModel.gameModeFlag == 1) {
        //     this.titleModel.getDifficulty(e.getActionCommand());
        // } 
    }

} // Closes class