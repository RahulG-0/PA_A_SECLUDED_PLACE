import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyboardInput implements KeyListener{
    GameModel gModel;

    public keyboardInput(GameModel gModel){
        this.gModel = gModel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        gModel.update();
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gModel.forwardKeyBind.equals(KeyEvent.getKeyText(e.getKeyCode()))){
            gModel.setUserDirection(gModel.forwardKeyBind);
            // gModel.hasGivenDirection = true;
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
                gModel.displayOptionsPannel = true;
            }

            // OPTIONAL ADD ERROR SOUND

        }

        gModel.update();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        gModel.update();
        
    }    
}
