package entity;



import java.util.Random;

import main.GamePanel;


public class NPC_kelmy extends Entity{

    public NPC_kelmy(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        
        setDialogue();

    }

    public void getImage() {
    
        up1 = setup("/res/resources/npc/kelmy_up_1");
        up2 = setup("/res/resources/npc/kelmy_up_2");
        down1 = setup("/res/resources/npc/kelmy_down_1");
        down2 = setup("/res/resources/npc/kelmy_down_2");
        left1 = setup("/res/resources/npc/kelmy_left_1");
        left2 = setup("/res/resources/npc/kelmy_left_2");
        right1 = setup("/res/resources/npc/kelmy_right_1");
        right2 = setup("/res/resources/npc/kelmy_right_2");
    }
    public void setDialogue() {
        dialogues[0] = "Hello, my love!";
        dialogues[1] = "Welcome to your own little personal \ngame!";
        dialogues[2] = "Where you're the main charater, \njust like in real life!";
        dialogues[3] = "I hope you like it pookie, \nI love you!";
    }

    public void setAction() {

        actionLockCounter ++;

        if(actionLockCounter == 120) {

       
            Random random = new Random();
            int i = random.nextInt(100)+1 ;

            if(i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }

    }
    public void speak() {

        //Do this character specific stuff
       super.speak();
    }
 

}
