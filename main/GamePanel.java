package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import entity.Player;
import entity.Entity;
import object.SuperObject;
import tile.TileManager;

import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int originalTileSize = 16; // 16 by 16 title
    final int scale = 3; // 3 times the original size

    public final int tileSize = originalTileSize * scale; // 48 by 48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels  
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol; // 800 pixels
    public final int worldHeight = tileSize * maxWorldRow; // 800 pixels

    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();

    
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];

    //GAME STATE

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;





    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);


    }

    public void setupGame() {

        aSetter.setObject();
        aSetter.setNPC();

      
        gameState = titleState;
        playMusic(5);

    //    if (gameState == pauseState) {
     //       playMusic(5);
//}



    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; //0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        
        while(gameThread != null){
            
            // 1 UPDATE: update information such as character positions
            update(); 

            // 2 DRAW: draw the updated information on the screen
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // convert to milliseconds

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }

    }

    public void update(){

        if (gameState == playState) {
            player.update();

            //NPC
            for(int i = 0; i < npc.length;i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }       
        }
        if(gameState == pauseState) {
            
            //nothing 
        }
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 =  (Graphics2D) g;

        //DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }
        

        //TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);

        }
        //OTHERS
        else{
             //TILE
        tileM.draw(g2);

        //OBJECT
        for(int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        //NPC
        for(int i = 0; i < npc.length; i++) {
            if(npc[i] != null) {
                npc[i].draw(g2);
            }
        }

        //PLAYER
        player.draw(g2);

        //UI
        ui.draw(g2);

        }

        //DEBUG
        if(keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10 , 400);
            System.out.println("Draw Time: "+ passed); 
        }
        g2.dispose();
    }

    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(int i) {
         
        music.stop();
    }
    public void playSE(int i) {

        se.setFile(i);
        se.play();
    }


}
