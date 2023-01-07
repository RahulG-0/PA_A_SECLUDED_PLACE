import java.io.*;
import javax.sound.sampled.*;


public class MusicPlayer {

    Clip clip;

    private String directory = System.getProperty("user.dir");

    public void setVolume(float volume){

        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume); // Reduce volume or increace volume
    }

    public void buttonSound(){
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\ButtonClick.wav"));
            Clip clipButton = AudioSystem.getClip();
            clipButton.open(inputStream);
            clipButton.start();
        } catch (Exception e) {}
    }

    public void music(){

        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\Opening.wav"));
            this.clip = AudioSystem.getClip();
            clip.open(inputStream);

            clip.start();
        } 
        catch (Exception e) {}
    }
    


    public void stop(){
        this.clip.stop();
    }

}