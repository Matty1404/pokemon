package pokemon;

import entity.Player;
import java.util.Random;
import java.util.Scanner;
import object.PokeBall;

public class Battle {

  private int turn = 1;
  private boolean trainerBattle;
  private Player p1;
  private Player p2;
  private Pokemon poke1;
  private Pokemon poke2;
  private Scanner scanner = new Scanner(System.in);
  private int p1Remaining = 0;
  private int p2Remaining = 0;
  Random generator = new Random();


  public Battle(Pokemon poke1, Pokemon poke2) { //not to use
    this.poke1 = poke1;
    this.poke2 = poke2;
    stimulateBattle(poke1, poke2);
  }

  public Battle(Player p1, Pokemon poke2) { //wild pokemon
    this.p1 = p1;
    for (Pokemon poke1 : p1.currPokemon) {
      if (poke1.alive) {
        p1Remaining++;
        if (this.poke1 == null) {
          this.poke1 = poke1;
        }
      }
    }
    this.poke2 = poke2;
    trainerBattle = false;
    while (p1Remaining > 0) {
      stimulateBattle(poke1, poke2);
      if (poke1.alive) {
        break;
      } else {
        if (p1Remaining > 0) {
          System.out.println("pick next pokemon to send out");
          for (int i = 0; i < 6; i++) {
            if (p1.currPokemon.size() >= i + 1 && p1.currPokemon.get(i).alive) {
              System.out.println(
                  "send out " + p1.currPokemon.get(i).name + " by typing " + (i + 1));
            }
          }
          //todo fill in changing pokemon
          boolean chosen = false;
          while (!chosen) {
            chosen = true;
            String nextPoke = scanner.nextLine();
            switch (nextPoke) {
              case "1":
                if (p1.currPokemon.size() > 0 && p1.currPokemon.get(0).alive) {
                  poke1 = p1.currPokemon.get(0);
                } else {
                  chosen = false;
                  System.out.println("pick an specified number");
                }
                break;
              case "2":
                if (p1.currPokemon.size() > 1 && p1.currPokemon.get(1).alive) {
                  poke1 = p1.currPokemon.get(1);
                } else {
                  chosen = false;
                  System.out.println("pick an specified number");
                }
                break;
              case "3":
                if (p1.currPokemon.size() > 2 && p1.currPokemon.get(2).alive) {
                  poke1 = p1.currPokemon.get(2);
                } else {
                  chosen = false;
                  System.out.println("pick an specified number");
                }
                break;
              case "4":
                if (p1.currPokemon.size() > 3 && p1.currPokemon.get(3).alive) {
                  poke1 = p1.currPokemon.get(3);
                } else {
                  chosen = false;
                  System.out.println("pick an specified number");
                }
                break;
              case "5":
                if (p1.currPokemon.size() > 4 && p1.currPokemon.get(4).alive) {
                  poke1 = p1.currPokemon.get(4);
                } else {
                  chosen = false;
                  System.out.println("pick an specified number");
                }
                break;
              case "6":
                if (p1.currPokemon.size() > 5 && p1.currPokemon.get(5).alive) {
                  poke1 = p1.currPokemon.get(5);
                } else {
                  chosen = false;
                  System.out.println("pick an specified number");
                }
                break;
              default:
                chosen = false;
                System.out.println("please choose again the move to forget with right input");
            }
          }

        }
      }
    }

    if (p1Remaining == 0) {
      System.out.println("go back to pokemon centre to heal pokemon");
    }

  }

  public Battle(Player p1, Player p2) {
    this.p1 = p1;
    this.p2 = p2;
    trainerBattle = true;
    this.poke1 = p1.currPokemon.get(0);
    this.poke2 = p2.currPokemon.get(0);
    stimulateBattle(poke1, poke2);
  }


  private void stimulateBattle(Pokemon poke1, Pokemon poke2) {
    boolean endBattle = false;
    while (poke1.alive && poke2.alive) {
      Pokemon currPokemon = turn == 1 ? poke1 : poke2;
      Pokemon target = turn == 2 ? poke1 : poke2;

      System.out.println("to run, type 9");
      System.out.println("to catch pokemon, type 8");
      System.out.println(currPokemon.name + "'s turn to attack. Choose what move to make: ");

      //display moves
      for (int i = 0; i < 4; i++) {
        if (currPokemon.currMoves[i] != null) {
          System.out.println("Use " + currPokemon.currMoves[i].name + " by typing " + (i + 1));
        }
      }

      PokeMove chosenMove = null;
      boolean chosen = false;
      while (!chosen) {
        chosen = true;
        String move = scanner.nextLine();
        switch (move) {
          case "1":
            chosenMove = currPokemon.currMoves[0];
            break;
          case "2":
            chosenMove = currPokemon.currMoves[1];
            break;
          case "3":
            if (currPokemon.movesLearnt > 2) {
              chosenMove = currPokemon.currMoves[2];
            } else {
              chosen = false;
              System.out.println("please choose again the move to forget with right input");
            }
            break;
          case "4":
            if (currPokemon.movesLearnt > 3) {
              chosenMove = currPokemon.currMoves[3];
            } else {
              chosen = false;
              System.out.println("please choose again the move to forget with right input");
            }
            break;
          case "9":
            if (trainerBattle) {
              System.out.println("cannot run from trainer battles");
            } else {
              endBattle = true;
            }
            break;
          case "8":
            if (trainerBattle) {
              System.out.println("cannot catch a trainer pokemon!");
            } else {
              if (poke2.attemptCapture(new PokeBall())) {
                System.out.println("You caught the pokemon!");
                if (p1.currPokemon.size() >= 6) {
                  System.out.println("too many pokemon on the team, storing in player Storage");
                  p1.pokeStorage.add(poke2);
                } else {
                  p1.currPokemon.add(poke2);
                }
                endBattle = true;
              } else {
                System.out.println("you did not manage to catch the pokemon");
              }

            }
            break;
          default:
            chosen = false;
            System.out.println("please choose again the move to forget with right input");
        }
      }
      if (endBattle) {
        break;
      }
      makeMove(chosenMove, currPokemon, target, false);

      if (!poke2.alive) {
        break;
      }

      turn = turn == 1 ? 2 : 1;

      //AI MOVE
      //pick random move


      chosenMove = target.currMoves[generator.nextInt(0, poke2.movesLearnt - 1)];
      makeMove(chosenMove, target, currPokemon, true);
      turn = turn == 1 ? 2 : 1;

    }
  }

  private void makeMove(PokeMove chosenMove, Pokemon currPokemon, Pokemon target, boolean ai) {
    if (chosenMove != null) {
      switch (chosenMove.moveStatus) {
        case ATTACK_BOOST, DEFENCE_BOOST, HEAL -> currPokemon.useMove(chosenMove, currPokemon,
            false);
        default -> currPokemon.useMove(chosenMove, target, ai);
      }
    }
  }
}
