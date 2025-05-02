package Main;
import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Sound {
    Clip clip ;
    URL soundURL[] = new URL[30];
    public Sound(){
        try{
        soundURL[0] = new File("src/res/sound/BlueBoyAdvanture.wav").toURI().toURL();
        soundURL[1] = new File("src/res/sound/coin.wav").toURI().toURL();
        soundURL[2] = new File("src/res/sound/powerup.wav").toURI().toURL();
        soundURL[3] = new File("src/res/sound/unlock.wav").toURI().toURL();
        soundURL[4] = new File("src/res/sound/fanfare.wav").toURI().toURL();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public void setFile(int i){
        try {
            
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
           
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
