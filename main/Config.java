package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));


            //FULL SCREEN
            if(gp.FullScreenOn == true) {
                bw.write("On");
            }
            if(gp.FullScreenOn == false) {
                bw.write("Off");
            }
            bw.newLine();

            //MUSIC VOLUME
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            //SE VOLUME
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            String s = br.readLine();

            //Full Screen
            if(s.equals("On")) {
                gp.FullScreenOn = true;
            }
            if(s.equals("Off")) {
                gp.FullScreenOn = false;
            }

            //Music Volume
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);
            
            //SE Volume
            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
