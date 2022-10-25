package pokemon;

import java.util.HashMap;
import java.util.HashSet;

public class Pikatchu extends Pokemon{
  public Pikatchu(int level, HashMap<String, PokeMove> moves) {
    name = "Pikatchu";
    pokeType = TypeClass.DEFAULT;
    this.level = level;
    catchRate = 0.3;
    initialHealth = 100 + (int) (level * 6.5);
    health = initialHealth;
    strength = 1.1;
    movesCanLearn.add(moves.get("Tackle"));
    currMoves[0] = moves.get("Tackle");
  }
}
