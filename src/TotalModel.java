public class TotalModel {

    private TotalView totalView;

    public TotalModel(){
        super();
    }

    public void setGUI(TotalView totalView){
        this.totalView = totalView;
    }

    public void update(){
        while(true) totalView.update();
    }
}
