// Imports
import javax.swing.event.MouseInputAdapter;
import java.awt.event.*;

public class MouseController extends MouseInputAdapter {
    public TotalModel totalModel;


    public MouseController(TotalModel totalModel){

        this.totalModel = totalModel;
        
    }

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