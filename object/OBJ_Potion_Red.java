package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp;
    int value = 5;

    public OBJ_Potion_Red(GamePanel gp) {

        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Red Potion";
        down1 = setup("/res/resources/objects/potion_red", gp.tileSize, gp.tileSize);
        description = "[Red Potion]\nHealls your life by " + value + ".";
    }
    public void use(Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drank the " + name + "!\n" + "Your life has been restored by "+ value + ".";
        entity.life += value;

        if(gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);

    }
}
