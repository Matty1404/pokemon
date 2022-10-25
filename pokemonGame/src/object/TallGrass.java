package object;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import pokemon.Pokemon;
import tile.CollisionType;

public class TallGrass extends SuperObject {

  int avgLevel;
  List<Pokemon> pokemonSpawn; //include in the constructor

  public TallGrass() {
    name = "Tall Grass";
    collision = CollisionType.WALKABLE;
    foreground = false;
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/objectRes/TallGrass.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
