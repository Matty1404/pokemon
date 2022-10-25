package pokemon;

import java.util.HashMap;

public class Squirtle extends Pokemon {

  public Squirtle(int level, HashMap<String, PokeMove> moves) {
    name = "Squirtle";
    pokeType = TypeClass.WATER;
    this.level = level;
    catchRate = 0.5;

    defenceLevelMultiply = level * 0.01;
    attackLevelMultiply = level * 0.01;
    healthLevelMultiply = level * 7.8;

    initialHealth = 60 + (int) healthLevelMultiply;
    health = initialHealth;

    beforeMatchStrength = 1.15 + level * 0.01;
    strength = beforeMatchStrength;

    beforeMatchDefence = 1.1 + level * 0.01;
    defence = beforeMatchDefence;

    movesCanLearn.add(moves.get("Tackle"));
    currMoves[0] = moves.get("Tackle");
    movesCanLearn.add(moves.get("Water Gun"));
    currMoves[1] = moves.get("Water Gun");
    movesCanLearn.add(moves.get("solar warmth"));
    //currMoves[2] = moves.get("solar warmth");
    movesCanLearn.add(moves.get("hardening"));
    movesCanLearn.add(moves.get("confusion"));
    movesCanLearn.add(moves.get("strength"));


    movesLearnt = 2;
  }
}
