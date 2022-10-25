package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import pokemon.Pokemon;
import tile.CollisionType;

public class Entity {

  public int worldX, worldY;
  public int speed;
  public int defaultSpeed;

  public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
  public Direction direction;

  public int spriteCounter = 0;
  public int spriteNum = 1;

  public Rectangle solidArea;
  public boolean collisionOn = false;
  public int solidAreaDefaultX, solidAreaDefaultY;

  public List<Pokemon> currPokemon = new ArrayList<>();

}
