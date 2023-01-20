import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VolumeController implements ChangeListener {

    private MusicPlayer mPlayer;
    private JSlider slider;
    
    public VolumeController(MusicPlayer mPlayer, JSlider slider){
        this.mPlayer = mPlayer;
        this.slider = slider;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        
        if (!slider.getValueIsAdjusting()) {
            int Volume = (int)slider.getValue();
            this.mPlayer.setVolume(Volume);
        }


    }
}
