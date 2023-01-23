// Program Name: TotalModel
// Last Modified: January 22, 2023
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Model for the entire game which helps switch from titleView to gameView

public class TotalModel {

    // Creates instance of TotalView
    private TotalView totalView;

    // Constructor
    public TotalModel(){
        super();
    }

    // Sets GUI
    public void setGUI(TotalView totalView){
        this.totalView = totalView;
    }

    // Calls update in TotalView
    public void update(){
        totalView.update();
    }
}
