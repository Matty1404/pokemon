package pokemon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import object.PokeBall;

public class Pokemon {

  public String name;
  public TypeClass pokeType;
  public int initialHealth;

  //change with level
  public double healthLevelMultiply;
  public double attackLevelMultiply;
  public double defenceLevelMultiply;
  public int health;
  public int level;
  public int experience = 0;
  protected int levelEvolveAt;

  //multiplier, change with level
  public double beforeMatchStrength;
  public double strength;
  public double beforeMatchDefence;
  public double defence = beforeMatchDefence;
  public List<PokeMove> movesCanLearn = new ArrayList<>();
  protected int nextMoveConstant;
  public int nextLevelMove;
  public PokeMove[] currMoves = new PokeMove[4];

  //larger the number is harder to catch, max is 1, multiplier, change with level
  public double catchRate;

  private Random generator = new Random();
  private Scanner scanner = new Scanner(System.in);
  public int movesLearnt;
  public boolean alive = true;


  public void useMove(PokeMove move, Pokemon target, boolean ai) {
    System.out.println(name + " used " + move.name + "!");
    switch (move.moveStatus) {
      case ATTACK -> attack(move, target, ai);
      case DEFENCE_BOOST -> defend(move, this);
      case DEFENCE_REDUCE -> defend(move, target);
      case HEAL -> heal(move);
      case ATTACK_BOOST -> attackStatEffect(move, this);
      case ATTACK_REDUCE -> attackStatEffect(move, target);
      default -> throw new NoSuchElementException();
    }
  }

  private void defend(PokeMove move, Pokemon target) {
    System.out.println(
        target.name + "'s defence " + (move.moveStatus == MoveType.DEFENCE_BOOST ? "increased"
            : "decreased") + "!");
    target.defence *= move.defChange;
  }

  private void attackStatEffect(PokeMove move, Pokemon target) {
    System.out.println(
        target.name + "'s attack " + (move.moveStatus == MoveType.ATTACK_BOOST ? "increased"
            : "decreased") + "!");
    target.strength *= move.attackChange;
  }

  private void heal(PokeMove move) {
    health += move.defChange * initialHealth;
    if (health > initialHealth) {
      health = initialHealth;
    }
    System.out.println(
        name + " healed " + (int) (move.defChange * initialHealth) + " health points!");
  }

  private void attack(PokeMove move, Pokemon target, boolean ai) {

    double crit = generator.nextDouble(0, 1) < move.critChance ? 1.5 : 1;
    double effect;
    switch (move.findEffect(target)) {
      case SUPER -> effect = 1.5;
      case NORMAL -> effect = 1;
      case WEAK -> effect = 0.5;
      default -> effect = 0;
    }
    double damageDealt = strength * move.standardDamage * crit * effect / target.defence;
    target.health -= damageDealt;
    if (crit == 1.5) {
      System.out.println("A critical hit!");
    }
    System.out.println(move.findEffect(target));

    System.out.println(
        name + " dealt " + (int) damageDealt + " amount of damage" + ", and " + target.name
            + "'s new health is " + target.health);
    if (target.health <= 0) {
      target.health = 0;
      target.alive = false;
      System.out.println(target.name + " fainted");

      //if player move
      if (!ai) {
        System.out.println(
            name + " gained " + (int) (Math.pow(target.level, 1.25) * 50) + " experience!");

        //level up if need be
        experienceHandler((int) (Math.pow(target.level, 1.25) * 50));
      }

    }
    System.out.println("\n");
  }

  private void updateStats() {
    strength += attackLevelMultiply;
    defence += defenceLevelMultiply;
    initialHealth += healthLevelMultiply;
    if (level > nextLevelMove) {
      nextLevelMove += nextMoveConstant;
      movesLearnt++;
      if (movesLearnt > 4) {
        if (movesCanLearn.size() > movesLearnt) {
          PokeMove toLearn = movesCanLearn.get(movesLearnt - 1);
          System.out.println(name + " wants to learn the move " + toLearn.name
              + " but cannot learn any more moves. Should he forget a move or not learn it? type y to forget, otherwise type n");

          String forget = scanner.nextLine();
          if (forget.equals("y")) {
            System.out.println("you did not learn the new move");
          } else {
            System.out.println("Choose which move to forget: ");
            System.out.println("type '1' to forget " + currMoves[0].name);
            System.out.println("type '2' to forget " + currMoves[1].name);
            System.out.println("type '3' to forget " + currMoves[2].name);
            System.out.println("type '4' to forget " + currMoves[3].name);
            boolean forgotten = false;
            while (!forgotten) {
              forgotten = true;
              forget = scanner.nextLine();
              switch (forget) {
                case "1" -> currMoves[0] = toLearn;
                case "2" -> currMoves[1] = toLearn;
                case "3" -> currMoves[2] = toLearn;
                case "4" -> currMoves[3] = toLearn;
                default -> {
                  forgotten = false;
                  System.out.println("please choose again the move to forget with right input");
                }
              }
            }
          }
        }
      } else {
        currMoves[movesLearnt - 1] = movesCanLearn.get(movesLearnt - 1);
        System.out.println(name + " learnt the move " + currMoves[movesLearnt - 1].name);
      }

    }
  }

  private void experienceHandler(int gainedExp) {
    int nextLevelExp = level * 50;
    int remainingExp = gainedExp;
    while (experience + remainingExp > nextLevelExp) {
      System.out.println(name + " levelled up to level " + ++level + "!");
      remainingExp -= (nextLevelExp - experience);
      experience = 0;
      nextLevelExp = level * 50;
      updateStats();
      //checkEvolve();
    }
    experience += remainingExp;
  }

  public boolean attemptCapture(PokeBall ball) {
    double value = generator.nextDouble(0, 1);
    double catchProb =
        (((double) level + 20) / 100) * ball.catchValue * catchRate - (0.4 * ((double) health
            / initialHealth)) + 0.4;
    System.out.println(catchProb);
    return value < catchProb;
  }

//  public void checkEvolve() {
//    if (level >= levelEvolveAt) {
//      this = new Ivysaur(level, movesCanLearn);
//    }
//  }

}
