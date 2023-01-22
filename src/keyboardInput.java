// Program Name: KeyboardInput
// Last Modified: January 22, 2023
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Gets keyboard input

// Imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {
    GameModel gModel;

    // Constructor
    public KeyboardInput(GameModel gModel){
        this.gModel = gModel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        gModel.update();
        
    }

    // Gets the key pressed and either sets the direction or opens options menu
    @Override
    public void keyPressed(KeyEvent e) {
        if (gModel.forwardKeyBind.equals(KeyEvent.getKeyText(e.getKeyCode()))){
            gModel.setUserDirection(gModel.forwardKeyBind);
        }
        else if (gModel.backwardsKeyBind.equals(KeyEvent.getKeyText(e.getKeyCode()))){
            gModel.setUserDirection(gModel.backwardsKeyBind);
        }
        else if (gModel.rightKeyBind.equals(KeyEvent.getKeyText(e.getKeyCode()))){
            gModel.setUserDirection(gModel.rightKeyBind);
        }
        else if (gModel.leftKeyBind.equals(KeyEvent.getKeyText(e.getKeyCode()))){
            gModel.setUserDirection(gModel.leftKeyBind);
        }
        else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Escape")){
            if (gModel.canEscape) {
                gModel.displayOptionsPanel = true;
            }
        }

        // Updates the model
        gModel.update();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        gModel.update();
        
    }
    
} // Closes class