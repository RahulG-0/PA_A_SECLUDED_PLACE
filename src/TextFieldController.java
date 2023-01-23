// Program Name: TextFieldController
// Last Modified: January 22, 2023
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: This is the controller for the text fields on the options menu

// Imports
import java.awt.event.*;
import javax.swing.*;

public class TextFieldController implements ActionListener {

    // Creates an instance of TitleModel
    private GameModel gameModel;
    private JTextField forwardKeyBind;
    private JTextField leftKeyBind;
    private JTextField rightKeyBind;
    private JTextField backKeyBind;

    // Constructor
    public TextFieldController(GameModel gameModel, JTextField forwardKeyBind, JTextField backwardsKeyBind, JTextField leftKeyBind, JTextField rightKeyBind) {
        this.gameModel = gameModel;
        this.forwardKeyBind = forwardKeyBind;
        this.leftKeyBind = leftKeyBind;
        this.backKeyBind = backwardsKeyBind;
        this.rightKeyBind = rightKeyBind;
    }

    // Checks to see if a button is pressed on the title page and passes it on to TitleModel
    public void actionPerformed(ActionEvent e) {
        char forward;
        char left;
        char right;
        char back;

        // These make sure that the user does not enter the same key bind multiple times
        if (gameModel.forwardKeyBind == gameModel.backwardsKeyBind || gameModel.forwardKeyBind == gameModel.rightKeyBind || gameModel.forwardKeyBind == gameModel.leftKeyBind) {
            forwardKeyBind.removeAll();
        } else {
            forward = this.forwardKeyBind.getText().charAt(0);
            this.gameModel.setKeyBind("FORWARD", forward);
        }

        if (gameModel.backwardsKeyBind == gameModel.forwardKeyBind || gameModel.backwardsKeyBind == gameModel.rightKeyBind || gameModel.backwardsKeyBind == gameModel.leftKeyBind) {
            backKeyBind.removeAll();
        } else {
            back = this.backKeyBind.getText().charAt(0);
            this.gameModel.setKeyBind("BACKWARDS", back);
        }

        if (gameModel.rightKeyBind == gameModel.forwardKeyBind || gameModel.rightKeyBind == gameModel.backwardsKeyBind || gameModel.rightKeyBind == gameModel.leftKeyBind) {
            rightKeyBind.removeAll();
        } else {
            right = this.rightKeyBind.getText().charAt(0);
            this.gameModel.setKeyBind("RIGHT", right);
        }

        if (gameModel.leftKeyBind == gameModel.forwardKeyBind || gameModel.leftKeyBind == gameModel.rightKeyBind || gameModel.leftKeyBind == gameModel.backwardsKeyBind) {
            leftKeyBind.removeAll();
        } else {
            left = this.leftKeyBind.getText().charAt(0);
            this.gameModel.setKeyBind("LEFT", left);
        }

    } // Closes actionPerformed

} // Closes class