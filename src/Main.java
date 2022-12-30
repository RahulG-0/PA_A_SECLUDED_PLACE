// Program Name: Main
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Main class for "A Secluded Place"

// Imports
import javax.swing.*;

public class Main {

    public static JFrame frame = new JFrame("A Secluded Place");

    public Main() {
        super();
    }

    public static void main(String[] args) {
        
        // Creates the model and view
        TotalModel TotalModel = new TotalModel();
        TotalView view = new TotalView(TotalModel);

        // JFrame frame = new JFrame("A Secluded Place");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Sets JFrame to be fullscreen by default
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.setVisible(true);

    } // Closes main method
    
} // Closes class