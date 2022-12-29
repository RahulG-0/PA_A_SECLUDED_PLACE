package PA;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        
        // Creates the model and view
        Model model = new Model();
        TitleView view = new TitleView(model);
        

        JFrame frame = new JFrame("A Secluded Place");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Sets JFrame to be fullscreen by default
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.setVisible(true);

    } // Closes main method
    
} // Closes class