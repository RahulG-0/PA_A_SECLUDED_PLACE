// Program Name: MusicPlayer
// Last Modified: January 22, 2023
// Name: Rahul Gurukiran & Anirudh Bharadwaj
// Description: Plays the various audio clips

// Imports
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

    // Gets directory
    private String directory = System.getProperty("user.dir");

    // Sets volume
    public void setVolume(float volume){
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume); // Reduce volume or increase volume
    }

    // Sound for button clicks
    public void buttonSound(){
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\ButtonClick.wav")); // Version for VS
            // AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\Music\\ButtonClick.wav"));
            this.clipButton = AudioSystem.getClip();
            clipButton.open(inputStream);
            clipButton.start();
        } catch (Exception e) {}
    }

    // Ambiance for title screen
    public void music(){
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\Opening.wav")); // Version for VS
            // AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\Music\\Opening.wav"));
            this.clip = AudioSystem.getClip();
            clip.open(inputStream);

            clip.loop(100);
        } 
        catch (Exception e) {}
    }

    // Ambiance for game
    public void gameMusic() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\Ambiance.wav")); // Version for VS
            // AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\Music\\Ambiance.wav"));
            this.gameClip = AudioSystem.getClip();
            gameClip.open(inputStream);
            // gameClip.start();
            gameClip.loop(100);
        } catch (Exception e) {}
    }

    // Plays sound effect for which direction the monster is coming from
    public void monstAttackPrepSounds(String dir) {
        AudioInputStream inputStream = null;
        
        try {
            if (dir.equals("FORWARD")) {
                inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\WarningFront.wav")); // Version for VS
                // inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\Music\\WarningFront.wav"));
            } else if (dir.equals("LEFT")) {
                inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\WarningLeft.wav")); // Version for VS
                // inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\Music\\WarningLeft.wav"));
            } else if (dir.equals("RIGHT")) {
                inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\WarningRight.wav")); // Version for VS
                // inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\Music\\WarningRight.wav"));
            } else if (dir.equals("BACKWARD")) {
                inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\WarningBack.wav")); // Version for VS
                // inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\Music\\WarningBack.wav"));
            }

            this.monstPrepClip = AudioSystem.getClip();
            monstPrepClip.open(inputStream);
            monstPrepClip.start();
        } catch (Exception e) {}
    }

    // Monster death sound effect
    public void monsterDeath() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\MonsterDeath.wav")); // Version for VS
            // AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\Music\\MonsterDeath.wav"));
            this.monstClip = AudioSystem.getClip();
            monstClip.open(inputStream);
            monstClip.start();
        } catch (Exception e) {}
    }

    // Sound effect for walking
    public void walkingSounds() {
        int randNum = (int)(Math.round(((Math.random() * 2) + 1)));
        
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\Walking" + randNum + ".wav")); // Version for VS
            // AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\Music\\Walking" + randNum + ".wav"));
            this.walkClip = AudioSystem.getClip();
            walkClip.open(inputStream);
            walkClip.start();
        } catch (Exception e) {
            
        }
    }

    // Sound for the defend button or the qte buttons being clicked
    public void defendButtonClick() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\src\\Music\\DefendingButtons.wav")); // Version for VS
            // AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(directory + "\\Music\\DefendingButtons.wav"));
            this.defendClip = AudioSystem.getClip();
            defendClip.open(inputStream);
            defendClip.start();
        } catch (Exception e) {}
    }

    // Stops whichever audio clip is required to stop
    public void stop(Clip stopClip){
        stopClip.stop();
    }

} // Closes class