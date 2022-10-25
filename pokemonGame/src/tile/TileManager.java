package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {

  public GamePanel gp;
  public Tile[] tile;
  public int[][] mapTileNum;

  public TileManager(GamePanel gp) {
    this.gp = gp;

    tile = new Tile[20];
    mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
    getTileImage();
    loadMap("/mapsRes/world01.txt");
  }

  public void getTileImage() {
    try {
      tile[0] = new Tile();
      tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tilesRes/grass.png"));

      tile[1] = new Tile();
      tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tilesRes/wall.png"));
      tile[1].collision = CollisionType.SOLID;

      tile[2] = new Tile();
      tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tilesRes/water.png"));
      tile[2].collision = CollisionType.ICE;

      tile[3] = new Tile();
      tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tilesRes/earth.png"));
      tile[3].collision = CollisionType.MUD;

      tile[4] = new Tile();
      tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tilesRes/tree.png"));
      tile[4].collision = CollisionType.SOLID;


      tile[5] = new Tile();
      tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tilesRes/sand.png"));

      tile[6] = new Tile();
      tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tilesRes/TallGrass.png"));
      tile[6].collision = CollisionType.TALL_GRASS;

      tile[10] = new Tile();
      tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tilesRes/pokecentre1.png"));
      tile[10].collision = CollisionType.SOLID;

      tile[11] = new Tile();
      tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tilesRes/pokecentre2.png"));
      tile[11].collision = CollisionType.SOLID;

      tile[12] = new Tile();
      tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tilesRes/pokecentre3.png"));
      tile[12].collision = CollisionType.SOLID;

      tile[13] = new Tile();
      tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tilesRes/pokecentre4.png"));
      tile[13].collision = CollisionType.SOLID;

      tile[14] = new Tile();
      tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tilesRes/pokecentre5.png"));
      tile[14].collision = CollisionType.SOLID;

      tile[15] = new Tile();
      tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tilesRes/pokecentre6.png"));
      tile[15].collision = CollisionType.SOLID;



    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadMap(String map) {
    try {
      InputStream is = getClass().getResourceAsStream(map);
      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      int col = 0;
      int row = 0;

      while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
        String line = br.readLine();
        String[] numbers = line.split(" ");
        while (col < gp.maxWorldCol) {

          int num = Integer.parseInt(numbers[col]);

          mapTileNum[col][row] = num;
          col++;
        }
        row++;
        col = row == gp.maxWorldRow ? col : 0;
      }
      br.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void draw(Graphics2D g2) {
    int worldCol = 0;
    int worldRow = 0;


    while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

      int tileNum = mapTileNum[worldCol][worldRow];

      int worldX = worldCol * gp.tileSize;
      int worldY = worldRow * gp.tileSize;
      int screenX = worldX - gp.player.worldX + gp.player.screenX;
      int screenY = worldY - gp.player.worldY + gp.player.screenY;
      if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
          worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
          worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
          worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
        g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
      }
      worldCol++;
      if (worldCol == gp.maxWorldCol) {
        worldRow++;
        worldCol = 0;
      }
    }
  }


}
