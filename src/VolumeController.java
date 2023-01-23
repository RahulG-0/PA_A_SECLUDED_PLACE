// Program Name: VolumeController
// Last Modified: January 22, 2023
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Controls the volume in the options menus

// Imports
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VolumeController implements ChangeListener {

    // Instance variables
    private MusicPlayer mPlayer;
    private JSlider slider;
    
    // Constructor
    public VolumeController(MusicPlayer mPlayer, JSlider slider){
        this.mPlayer = mPlayer;
        this.slider = slider;
    } 

    // Changes the volume based on where the JSlider is
    @Override
    public void stateChanged(ChangeEvent e) {
        
        if (!slider.getValueIsAdjusting()) {
            int Volume = (int)slider.getValue();
            this.mPlayer.setVolume(Volume);
        }


    }
}
