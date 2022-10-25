package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import tile.CollisionType;

public class ObjChest extends SuperObject {

  public ObjChest() {
    name = "Chest";
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/objectRes/chest (OLD).png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
