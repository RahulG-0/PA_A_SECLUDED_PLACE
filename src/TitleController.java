// Program Name: TitleController
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: This is the controller for the buttons on the title screen

// Imports
import java.awt.event.*;

public class TitleController implements ActionListener {

    // Creates an instance of TitleModel
    private TitleModel titleModel;

    // Constructor
    public TitleController(TitleModel titleModel) {
        this.titleModel = titleModel;
    }

    // Checks to see if a button is pressed on the title page and passes it on to TitleModel
    public void actionPerformed(ActionEvent e) {
        this.titleModel.getSelected(e.getActionCommand());
    }

} // Closes class