package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound() {

        soundURL[0] = getClass().getResource("/res/resources/sound/that fya shit.wav");
        soundURL[1] = getClass().getResource("/res/resources/sound/coin.wav");
        soundURL[2] = getClass().getResource("/res/resources/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/res/resources/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/res/resources/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/res/resources/sound/menu.wav");
        soundURL[6] = getClass().getResource("/res/resources/sound/hitmonster.wav");
        soundURL[7] = getClass().getResource("/res/resources/sound/receivedamage.wav");
        soundURL[8] = getClass().getResource("/res/resources/sound/swingweapon.wav");
        soundURL[9] = getClass().getResource("/res/resources/sound/levelup.wav");
        soundURL[10] = getClass().getResource("/res/resources/sound/cursor.wav");
        soundURL[11] = getClass().getResource("/res/resources/sound/burning.wav");
        soundURL[12] = getClass().getResource("/res/resources/sound/cuttree.wav");
        soundURL[13] = getClass().getResource("/res/resources/sound/gameover.wav");


    }

    public void setFile(int i) {

        try{

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();


        }catch(Exception e) {

        }

    }
    public void play() {

        clip.start();

    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void stop() {
        clip.stop();

    }
    public void checkVolume() {

        switch(volumeScale) {
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }

}
