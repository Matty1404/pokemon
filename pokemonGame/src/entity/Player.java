package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import pokemon.Battle;
import pokemon.Bulbasour;
import pokemon.Charmander;
import pokemon.Pokemon;
import pokemon.Squirtle;
import pokemon.testPokemon;
import tile.CollisionType;

public class Player extends Entity {

  GamePanel gp;
  KeyHandler keyH;
  int hasKey = 0;
  private Random generator = new Random();
  public List<Pokemon> pokeStorage = new ArrayList<>();

  public final int screenX, screenY;

  public Player(GamePanel gp, KeyHandler keyH) {
    this.gp = gp;
    this.keyH = keyH;

    screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
    screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

    solidArea = new Rectangle(8, 16, 32, 32);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    setDefaultValues();
    getPlayerImage();

    currPokemon.add(new Squirtle(5, testPokemon.moves));
    currPokemon.add(new Charmander(5, testPokemon.moves));
    currPokemon.add(new Bulbasour(5, testPokemon.moves));
  }

  public void setDefaultValues() {
    worldX = gp.tileSize * 23;
    worldY = gp.tileSize * 21;
    speed = 4;
    defaultSpeed = speed;
    direction = Direction.DOWN;
  }

  public void getPlayerImage() {
    try {

      up1 = ImageIO.read(getClass().getResourceAsStream("/playerRes/boy_up_1.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("/playerRes/boy_up_2.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("/playerRes/boy_down_1.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("/playerRes/boy_down_2.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/playerRes/boy_right_1.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("/playerRes/boy_right_2.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/playerRes/boy_left_1.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("/playerRes/boy_left_2.png"));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void update() {
    //if current thing is ice, then just add until collision with solid
    int objectIndex = gp.cChecker.checkObject(this, true);

    int sprintValue = keyH.sprintPressed ? 2 : 1;
    int mudValue = gp.cChecker.currTileCheck(this) == CollisionType.MUD ? 4 : 1;
    speed = (speed * sprintValue) / mudValue;
    gp.cChecker.checkTile(this);
    if (gp.cChecker.currTileCheck(this) == CollisionType.ICE && !collisionOn) {
      switch (direction) {
        case UP -> worldY -= speed;
        case DOWN -> worldY += speed;
        case LEFT -> worldX -= speed;
        case RIGHT -> worldX += speed;
      }
    } else {
      collisionOn = false;

      if (keyH.downPressed || keyH.upPressed || keyH.leftPressed || keyH.rightPressed) {
        if (keyH.upPressed) {
          spriteCounter++;
          direction = Direction.UP;
        } else if (keyH.downPressed) {
          spriteCounter++;
          direction = Direction.DOWN;

        } else if (keyH.leftPressed) {
          spriteCounter++;
          direction = Direction.LEFT;

        } else {
          spriteCounter++;
          direction = Direction.RIGHT;
        }
        gp.cChecker.checkTile(this);
        objectIndex = gp.cChecker.checkObject(this, true);
        if (!collisionOn) {
          switch (direction) {
            case DOWN -> worldY += speed;
            case UP -> worldY -= speed;
            case LEFT -> worldX -= speed;
            case RIGHT -> worldX += speed;
          }
        }
        if (spriteCounter > 11) {
          spriteNum = spriteNum == 1 ? 2 : 1;
          spriteCounter = 0;
        }
        if (gp.cChecker.currTileCheck(this) == CollisionType.TALL_GRASS) {
          if (generator.nextDouble(0, 1) < 0.008) {
            Battle stimulateBattle = new Battle(this,
                new Charmander(10, testPokemon.moves));
          }
        }
      }
      pickUpObject(objectIndex);
    }
    speed = defaultSpeed;
  }

  public void pickUpObject(int index) {
    if (index != 9999) {
      String objectName = gp.objects[index].name;

      switch (objectName) {
        case "Key":
          // display option to pick up the key?
          if (keyH.useButton) {
            hasKey++;
            gp.objects[index] = null;
          }
          break;
        case "Door":
          if (hasKey > 0 && keyH.useButton) {
            gp.objects[index] = null;
            hasKey--;
          }
          break;
        case "PokeBall":
          if (keyH.useButton) {
            hasKey += 2;
            gp.objects[index] = null;
          }
        default:
          break;
      }

    }
  }

  public void draw(Graphics2D g2) {
    BufferedImage image = null;

    switch (direction) {
      case UP:
        image = spriteNum == 1 ? up1 : up2;
        break;
      case DOWN:
        image = spriteNum == 1 ? down1 : down2;
        break;
      case LEFT:
        image = spriteNum == 1 ? left1 : left2;
        break;
      case RIGHT:
        image = spriteNum == 1 ? right1 : right2;
        break;
    }

    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
  }
}
