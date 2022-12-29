package PA;
import javax.swing.*;
public class Model extends Object {

    public String whichDiffioculty = null;
    private boolean newGameYorN = false;

    
    
    public Model(){

        super();
    }

    public void whatButton(String Button){

        if (Button.equals("New game")){
            newGameYorN = true;
        }
        
    }

    public boolean newGameYOrN(){
        return newGameYorN;
    }



}
