import javax.sound.sampled.*;
import java.io.File;

public class SoundManager {
    private static Clip bgmClip; // stored so we can stop
    private static Clip susClip; // stored so we can stop

    public static void playBGM() { //play loop forever
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File("sound/bg_sound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            bgmClip = clip; // store it so it can be stop
        } catch (Exception e) {System.out.println("Error: " + e.getMessage());}
    }
    public static void stopBGM() { //to stop bg sound
        if(bgmClip != null) bgmClip.stop();
    }
    public static void playHeart(){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File("sound/heart_sound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {System.out.println("Error: " + e.getMessage());}
    }
    public static void playJump(){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File("sound/Scream_sound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {System.out.println("Error: " + e.getMessage());}
    }
    public static void playGameOver(){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File("sound/gameover.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {System.out.println("Error: " + e.getMessage());}
    }
    public static void playSus(){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File("sound/ghost_show.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            susClip = clip; // store it so it can be stop
        } catch (Exception e) {System.out.println("Error: " + e.getMessage());}
    }
    public static void stopSus() { //to stop bg sound
        if(susClip != null) susClip.stop();
    }
    public static void playWhisper() {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File("sound/whisper_sound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {System.out.println("Error: " + e.getMessage());}
    }
    public static void playIseeyou () {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File("sound/iseeyou_sound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {System.out.println("Error: " + e.getMessage());}
    }
}