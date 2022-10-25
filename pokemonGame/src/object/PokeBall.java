package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class PokeBall extends SuperObject{
  public double catchValue = 0.8;

  public PokeBall() {
    name = "PokeBall";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/objectRes/PokeBall.png"));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
