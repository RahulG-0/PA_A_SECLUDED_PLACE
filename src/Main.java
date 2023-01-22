// Program Name: Main
// Last Modified: January 22, 2023
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Main class for "A Secluded Place"

// Imports
import javax.swing.*; 

public class Main {

    public static void main(String[] args) {
        
        // Creates the model and view   
        TotalView view = new TotalView();
        JFrame frame = new JFrame("A Secluded Place");
        
        // Sets up the JFrame
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Sets JFrame to be fullscreen by default
        frame.setUndecorated(true); // Gets rid of the top bar of the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.setVisible(true); 

    } // Closes main method
    
} // Closes class