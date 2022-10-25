package pokemon;

import java.util.HashMap;
import java.util.HashSet;

public class Charmander extends Pokemon {

  public Charmander(int level, HashMap<String, PokeMove> moves) {
    name = "Charmander";
    pokeType = TypeClass.FIRE;
    this.level = level;
    catchRate = 0.5;

    healthLevelMultiply = level * 8.8;
    initialHealth = 70 + (int) (healthLevelMultiply);
    health = initialHealth;

    defenceLevelMultiply = level * 0.01;
    attackLevelMultiply = level * 0.01;
    beforeMatchStrength = 1.2 + level * 0.01;
    strength = beforeMatchStrength;

    beforeMatchDefence = 1.1 + level * 0.01;
    defence = beforeMatchDefence;

    movesCanLearn.add(moves.get("Tackle"));
    currMoves[0] = moves.get("Tackle");
    movesCanLearn.add(moves.get("Flame Thrower"));
    currMoves[1] = moves.get("Flame Thrower");
    movesCanLearn.add(moves.get("confusion"));
    currMoves[2] = moves.get("confusion");
    movesCanLearn.add(moves.get("strength"));
    currMoves[3] = moves.get("strength");

    movesLearnt = 4;

    nextMoveConstant = 70 / (movesCanLearn.size() - 2);
    nextLevelMove = nextMoveConstant;

  }

}
