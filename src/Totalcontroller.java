import java.awt.event.*;
public class Totalcontroller implements ActionListener {

    private TotalModel titleModel;
    // private JButton button;

    // Constructor
    public Totalcontroller(TotalModel titleModel) {
        this.titleModel = titleModel;
        // this.button = button;
    }

    // Checks to see if a button is pressed
    public void actionPerformed(ActionEvent e) {

        titleModel.update();

    }

    
}
