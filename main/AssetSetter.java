package main;

import entity.NPC_kelmy;

//import object.OBJ_Key;
//import object.OBJ_Chest;
//import object.OBJ_Door;
//import object.OBJ_Boots;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;

    }

    public void setObject() {


    }
    public void setNPC() {

        gp.npc[0] = new NPC_kelmy(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;

    }

}
