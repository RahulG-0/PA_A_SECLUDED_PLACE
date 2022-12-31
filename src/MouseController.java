// Imports
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.event.*;



import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class MouseController extends MouseInputAdapter {
    public TotalModel totalModel;


    public MouseController(TotalModel totalModel){

        this.totalModel = totalModel;
        
    }

    @Override
    public void mouseClicked(MouseEvent e){ 
        totalModel.update();
        System.out.println("hello");
    }

    public void mousePressed(MouseEvent me) { 
        totalModel.update();
        System.out.println("hello");
    }
    @Override
    public void mouseReleased(MouseEvent me) { 
        totalModel.update();
        System.out.println("hello");
    }
    @Override
    public void mouseEntered(MouseEvent me) { 
        totalModel.update();
        System.out.println("goodbye");
    }
    @Override
    public void mouseExited(MouseEvent me) { }

//Also, you will need to include the other mouselistener implemented methods, just 
//leave them empty
}