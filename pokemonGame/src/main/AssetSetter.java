package main;

import object.*;

public class AssetSetter {
  GamePanel gp;

  public AssetSetter(GamePanel gp) {
    this.gp = gp;
  }

  public void setGrassTiles() {

  }

  public void setObject() {
    gp.objects[0] = new ObjKey();
    gp.objects[0].worldX = 23 * gp.tileSize;
    gp.objects[0].worldY = 7 * gp.tileSize;

    gp.objects[1] = new ObjKey();
    gp.objects[1].worldX = 23 * gp.tileSize;
    gp.objects[1].worldY = 40 * gp.tileSize;

    gp.objects[2] = new ObjKey();
    gp.objects[2].worldX = 38 * gp.tileSize;
    gp.objects[2].worldY = 8 * gp.tileSize;

    gp.objects[3] = new ObjDoor();
    gp.objects[3].worldX = 10 * gp.tileSize;
    gp.objects[3].worldY = 11 * gp.tileSize;

    gp.objects[4] = new ObjDoor();
    gp.objects[4].worldX = 8 * gp.tileSize;
    gp.objects[4].worldY = 28 * gp.tileSize;

    gp.objects[5] = new ObjDoor();
    gp.objects[5].worldX = 12 * gp.tileSize;
    gp.objects[5].worldY = 22 * gp.tileSize;

    gp.objects[6] = new ObjChest();
    gp.objects[6].worldX = 10 * gp.tileSize;
    gp.objects[6].worldY = 7 * gp.tileSize;

    gp.objects[7] = new PokeBall();
    gp.objects[7].worldX = 20 * gp.tileSize;
    gp.objects[7].worldY = 2 * gp.tileSize;

    gp.objects[7] = new PokeBall();
    gp.objects[7].worldX = 20 * gp.tileSize;
    gp.objects[7].worldY = 2 * gp.tileSize;

    gp.objects[8] = new TallGrass();
    gp.objects[8].worldX = 24 * gp.tileSize;
    gp.objects[8].worldY = 7 * gp.tileSize;

    gp.objects[9] = new TallGrass();
    gp.objects[9].worldX = 24 * gp.tileSize;
    gp.objects[9].worldY = 8 * gp.tileSize;

    gp.objects[10] = new TallGrass();
    gp.objects[10].worldX = 25 * gp.tileSize;
    gp.objects[10].worldY = 7 * gp.tileSize;

    gp.objects[11] = new TallGrass();
    gp.objects[11].worldX = 25 * gp.tileSize;
    gp.objects[11].worldY = 8 * gp.tileSize;

  }
}
