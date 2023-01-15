import java.io.*;
import javax.sound.sampled.*;


public class MusicPlayer {

    Clip clip;
    Clip gameClip;

    private String directory = System.getProperty("user.dir");

    public void setVolume(float volume){
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume); // Reduce volume or increace volume
    }

    // Sound for button clicks
    public void buttonSound(){
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\ButtonClick.wav"));
            Clip clipButton = AudioSystem.getClip();
            clipButton.open(inputStream);
            clipButton.start();
        } catch (Exception e) {}
    }

    // Ambiance for title screen
    public void music(){
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\Opening.wav"));
            this.clip = AudioSystem.getClip();
            clip.open(inputStream);

            clip.loop(100);
        } 
        catch (Exception e) {}
    }

    // Ambiance for game
    public void gameMusic() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\Ambiance.wav"));
            this.gameClip = AudioSystem.getClip();
            gameClip.open(inputStream);

            gameClip.loop(5);
        } catch (Exception e) {}
    }

    // Sound effects for monster
    public void monsterSounds() {
        //
    }

    public void stop(){
        this.clip.stop();
    }

}