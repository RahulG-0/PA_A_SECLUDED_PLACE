import java.io.*;
import javax.sound.sampled.*;


public class MusicPlayer {

    // Initializes clips
    Clip clipButton;
    Clip clip;
    Clip gameClip;
    Clip monstPrepClip;
    Clip monstClip;
    Clip walkClip;
    Clip defendClip;

    private String directory = System.getProperty("user.dir");

    // Sets volume
    public void setVolume(float volume){
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume); // Reduce volume or increase volume
    }

    // Sound for button clicks
    public void buttonSound(){
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\ButtonClick.wav"));
            this.clipButton = AudioSystem.getClip();
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
            // gameClip.start();
            gameClip.loop(5);
        } catch (Exception e) {}
    }

    // Plays sound effect for which direction the monster is coming from
    public void monstAttackPrepSounds(String dir) {
        AudioInputStream inputStream = null; /////////////////// MIGHT CREATE A NULL POINTER EXCEPTION
        
        try {
            if (dir.equals("FORWARD")) {
                inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\WarningFront.wav"));
            } else if (dir.equals("LEFT")) {
                inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\WarningLeft.wav"));
            } else if (dir.equals("RIGHT")) {
                inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\WarningRight.wav"));
            } else if (dir.equals("BACKWARD")) {
                inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\WarningBack.wav"));
            }

            this.monstPrepClip = AudioSystem.getClip();
            monstPrepClip.open(inputStream);
            monstPrepClip.start();
        } catch (Exception e) {}
    }

    // Monster death sound effect
    public void monsterDeath() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\MonsterDeath.wav"));
            this.monstClip = AudioSystem.getClip();
            monstClip.open(inputStream);
            monstClip.start();
        } catch (Exception e) {}
    }

    // Sound effect for walking
    public void walkingSounds() {
        // MAYBE HAVE A RANDOM NUMBER GENERATOR IN HERE
        // AND CYCLE THROUGH A FEW DIFFERENT TYPES OF WALKING NOISES
        int randNum = (int)(Math.round(((Math.random() * 2) + 1)));
        
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\Walking" + randNum + ".wav"));
            this.walkClip = AudioSystem.getClip();
            walkClip.open(inputStream);
            walkClip.start();
        } catch (Exception e) {
            
        }
    }

    public void defendButtonClick() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\DefendingButtons.wav"));
            this.defendClip = AudioSystem.getClip();
            defendClip.open(inputStream);
            defendClip.start();
        } catch (Exception e) {}
    }

    // Stops whichever audio clip is required to stop
    public void stop(Clip stopClip){
        // this.clip.stop(); Initially there was no parameter
        // Changes in TitleView on lines: 285, 318, 323, 328, 349
        stopClip.stop();
    }

}