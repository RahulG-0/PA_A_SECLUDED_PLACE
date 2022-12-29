package PA;

import javax.jws.WebParam.Mode;
import javax.swing.*;



import java.awt.event.*;

public class TitelController implements ActionListener{
    
    private Mode game;
    private JButton button;

    public TitelController(Mode aGame, JButton aButton) {

        this.game = aGame;
        this.button = aButton;

    }

    public void actionPerformed(ActionEvent e) {
        if (((JButton)this.button).toString().contains("text=x")){
        }
        System.out.println(((JButton)this.button).toString());

    }
}
