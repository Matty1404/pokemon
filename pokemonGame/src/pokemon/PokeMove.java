package pokemon;

import java.util.HashSet;

public class PokeMove {
  public String name;
  public TypeClass damagetTpe;
  public MoveType moveStatus;
  public double standardDamage;
  public double defChange;
  // multiplier of either 0.75 or 1.25
  public double attackChange;
  public double critChance;

  public PokeMove(String name, MoveType moveStatus, TypeClass damagetTpe, double standardDamage, double critChance) {
    this.name = name;
    this.moveStatus = moveStatus;
    this.damagetTpe = damagetTpe;
    this.standardDamage = standardDamage;
    this.critChance = critChance;
  }

  public PokeMove(String name, MoveType moveType, double multiplier) {
    this.name = name;
    this.moveStatus = moveType;
    switch (moveType) {
      case ATTACK_BOOST, ATTACK_REDUCE -> attackChange = multiplier;
      case HEAL, DEFENCE_BOOST, DEFENCE_REDUCE -> defChange = multiplier;
      default -> throw new IllegalArgumentException();
    }
  }

  public Effect findEffect(Pokemon target) {
    switch (damagetTpe) {
      case FIRE:
        if (target.pokeType == TypeClass.GRASS) {
          return Effect.SUPER;
        } else if (target.pokeType == TypeClass.WATER) {
          return Effect.WEAK;
        } else {
          return Effect.NORMAL;
        }
      case WATER:
        if (target.pokeType == TypeClass.FIRE) {
          return Effect.SUPER;
        } else if (target.pokeType == TypeClass.GRASS) {
          return Effect.WEAK;
        } else {
          return Effect.NORMAL;
        }
      case GRASS:
        if (target.pokeType == TypeClass.WATER) {
          return Effect.SUPER;
        } else if (target.pokeType == TypeClass.FIRE) {
          return Effect.WEAK;
        } else {
          return Effect.NORMAL;
        }
      default:
        return Effect.NORMAL;
    }
  }
}
