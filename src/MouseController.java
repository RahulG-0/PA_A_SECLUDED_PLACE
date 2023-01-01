// Program Name: MouseController
// Last Modified:
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Tracks the mouse

// Imports
import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;

public class MouseController extends MouseInputAdapter {

    // Creates instance of TotalModel
    public TotalModel totalModel;

    // Constructor
    public MouseController(TotalModel totalModel){
        this.totalModel = totalModel;
    }

    // Tracks if the mouse is clicked, pressed, released or has entered/exited a button
    // and updates the TotalModel
    @Override
    public void mouseClicked(MouseEvent e){ 
        totalModel.update();
    }

    public void mousePressed(MouseEvent me) { 
        totalModel.update();
    }

    @Override
    public void mouseReleased(MouseEvent me) { 
        totalModel.update();
    }

    @Override
    public void mouseEntered(MouseEvent me) { 
        totalModel.update();
    }
    
    @Override
    public void mouseExited(MouseEvent me) { }

//Also, you will need to include the other mouselistener implemented methods, just 
//leave them empty
}