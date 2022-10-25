package main;

import entity.Entity;
import object.SuperObject;
import tile.CollisionType;

public class CollisionChecker {

  GamePanel gp;

  public CollisionChecker(GamePanel gp) {
    this.gp = gp;
  }

  public void checkTile(Entity entity) {
    int entityLeftWorldX = entity.worldX + entity.solidArea.x;
    int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
    int entityTopWorldY = entity.worldY + entity.solidArea.y;
    int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

    int entityLeftCol = entityLeftWorldX / gp.tileSize;
    int entityRightCol = entityRightWorldX / gp.tileSize;
    int entityTopRow = entityTopWorldY / gp.tileSize;
    int entityBottomRow = entityBottomWorldY / gp.tileSize;

    int tileNum1, tileNum2;

    switch (entity.direction) {
      case UP:
        entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
        tileNum1 = gp.tilesM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = gp.tilesM.mapTileNum[entityRightCol][entityTopRow];
        if (gp.tilesM.tile[tileNum1].collision == CollisionType.SOLID
            || gp.tilesM.tile[tileNum2].collision == CollisionType.SOLID) {
          entity.collisionOn = true;
        }
        break;
      case DOWN:
        entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
        tileNum1 = gp.tilesM.mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = gp.tilesM.mapTileNum[entityRightCol][entityBottomRow];
        if (gp.tilesM.tile[tileNum1].collision == CollisionType.SOLID
            || gp.tilesM.tile[tileNum2].collision == CollisionType.SOLID) {
          entity.collisionOn = true;
        }
        break;
      case LEFT:
        entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
        tileNum1 = gp.tilesM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = gp.tilesM.mapTileNum[entityLeftCol][entityBottomRow];
        if (gp.tilesM.tile[tileNum1].collision == CollisionType.SOLID
            || gp.tilesM.tile[tileNum2].collision == CollisionType.SOLID) {
          entity.collisionOn = true;
        }
        break;
      case RIGHT:
        entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
        tileNum1 = gp.tilesM.mapTileNum[entityRightCol][entityTopRow];
        tileNum2 = gp.tilesM.mapTileNum[entityRightCol][entityBottomRow];
        if (gp.tilesM.tile[tileNum1].collision == CollisionType.SOLID
            || gp.tilesM.tile[tileNum2].collision == CollisionType.SOLID) {
          entity.collisionOn = true;
        }
        break;
    }

  }

  public CollisionType currTileCheck(Entity entity) {
    int posX = entity.worldX + entity.solidArea.x + entity.solidArea.width / 2;
    int posY = entity.worldY + entity.solidArea.y + entity.solidArea.height / 2;

    int valueOnMapX = posX / gp.tileSize;
    int valueOnMapY = posY / gp.tileSize;

    return gp.tilesM.tile[gp.tilesM.mapTileNum[valueOnMapX][valueOnMapY]].collision;
  }

  public int checkObject(Entity entity, boolean player) {
    int index = 9999;

    for (int i = 0; i < gp.objects.length; i++) {
      if (gp.objects[i] != null) {
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gp.objects[i].solidArea.x = gp.objects[i].worldX + gp.objects[i].solidArea.x;
        gp.objects[i].solidArea.y = gp.objects[i].worldY + gp.objects[i].solidArea.y;

        switch (entity.direction) {
          case UP:
            entity.solidArea.y -= entity.speed;
            if (entity.solidArea.intersects(gp.objects[i].solidArea)) {
              if (gp.objects[i].collision == CollisionType.SOLID_OBJECT) {
                entity.collisionOn = true;
              }
              if (player) {
                index = i;
              }
            }
            break;
          case DOWN:
            entity.solidArea.y += entity.speed;
            if (entity.solidArea.intersects(gp.objects[i].solidArea)) {
              if (gp.objects[i].collision == CollisionType.SOLID_OBJECT) {
                entity.collisionOn = true;
              }
              if (player) {
                index = i;
              }
            }
            break;
          case LEFT:
            entity.solidArea.x -= entity.speed;
            if (entity.solidArea.intersects(gp.objects[i].solidArea)) {
              if (gp.objects[i].collision == CollisionType.SOLID_OBJECT) {
                entity.collisionOn = true;
              }
              if (player) {
                index = i;
              }
            }
            break;
          case RIGHT:
            entity.solidArea.x += entity.speed;
            if (entity.solidArea.intersects(gp.objects[i].solidArea)) {
              if (gp.objects[i].collision == CollisionType.SOLID_OBJECT) {
                entity.collisionOn = true;
              }
              if (player) {
                index = i;
              }
            }
            break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.objects[i].solidArea.x = gp.objects[i].solidAreaDefaultX;
        gp.objects[i].solidArea.y = gp.objects[i].solidAreaDefaultY;

      }
    }

    return index;
  }
}
