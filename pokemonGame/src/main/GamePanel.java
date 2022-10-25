package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

  //SCREEN SETTINGS
  final int originalTileSize = 16; //16 by 16 tile
  final int scale = 3;

  public final int tileSize = originalTileSize * scale;
  public final int maxScreenCol = 16;
  public final int maxScreenRow = 12;
  public final int screenWidth = tileSize * maxScreenCol;
  public final int screenHeight = tileSize * maxScreenRow;

  //WORLD SETTING
  public final int maxWorldCol = 50;
  public final int maxWorldRow = 50;
  public final int worldWidth = tileSize * maxWorldCol;
  public final int worldHeight = tileSize * maxWorldRow;

  private final KeyHandler keyH = new KeyHandler();
  private Thread gameThread;
  public final Player player = new Player(this, keyH);
  public final TileManager tilesM = new TileManager(this);
  public final CollisionChecker cChecker = new CollisionChecker(this);
  public AssetSetter aSetter = new AssetSetter(this);
  public SuperObject objects[] = new SuperObject[20];

  static double FPS = 60;

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.BLACK);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

  public void setupGame() {
    aSetter.setObject();
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() {

    double drawInterval = 1000000000 / FPS;
    double nextDrawTime = System.nanoTime() + drawInterval;

    while (gameThread != null) {

      //update position of character
      update();
      //draw the screen
      repaint();
      try {
        double remainingTime = nextDrawTime - System.nanoTime();
        remainingTime /= 1000000;

        if (remainingTime < 0) {
          remainingTime = 0;
        }

        Thread.sleep((long) remainingTime);

        nextDrawTime += drawInterval;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void update() {
    player.update();
  }

  @Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    tilesM.draw(g2);

    List<SuperObject> foreGround = new ArrayList<>();

    for (SuperObject obj : objects) {
      if (obj != null) {
        if (!obj.foreground) {
          obj.draw(g2, this);
        } else {
          foreGround.add(obj);
        }
      }
    }

    player.draw(g2);

    for (SuperObject obj : foreGround) {
      obj.draw(g2, this);
    }

    //do for loop again with all objects that are foreground objects so player appears behind them

    g2.dispose();
  }
}
