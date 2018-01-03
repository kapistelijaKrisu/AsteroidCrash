package assets;

import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundAssets {
	public static Clip test = loadSound("test.wav");
	
	public static Clip loadSound(String musicName) {
		try {
            URL url = SoundAssets.class.getResource("/" + musicName);            
            Clip music = AudioSystem.getClip();
            music.open(AudioSystem.getAudioInputStream(url));
            
            return music;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
}
