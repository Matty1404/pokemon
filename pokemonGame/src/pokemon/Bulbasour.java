package pokemon;

import java.util.HashMap;
import java.util.HashSet;

public class Bulbasour extends Pokemon {

  public Bulbasour(int level, HashMap<String, PokeMove> moves) {
    name = "Bulbasour";
    pokeType = TypeClass.GRASS;
    this.level = level;
    catchRate = 0.4;
    healthLevelMultiply = level * 8.5;

    levelEvolveAt = 16;

    initialHealth = 120 + (int) healthLevelMultiply;
    defenceLevelMultiply = level * 0.01;
    attackLevelMultiply = level * 0.01;
    health = initialHealth;
    beforeMatchStrength = 1.1 + level * 0.01;
    strength = beforeMatchStrength;

    beforeMatchDefence = 1.1 + level * 0.01;
    defence = beforeMatchDefence;

    movesCanLearn.add(moves.get("Tackle"));
    currMoves[0] = moves.get("Tackle");
    movesCanLearn.add(moves.get("Leaf Blade"));
    currMoves[1] = moves.get("Leaf Blade");
    movesCanLearn.add(moves.get("solar warmth"));
    //currMoves[2] = moves.get("solar warmth");
    movesCanLearn.add(moves.get("hardening"));

    movesLearnt = 2;

  }
}
