package pokemon;

import java.util.HashMap;
import java.util.HashSet;

public class testPokemon {

  public static HashMap<String, PokeMove> moves = genMoves();

  public static void main(String[] args) {

    //create move list

    //create all pokemon list

    Pokemon pikatchu = new Pikatchu(5, moves);

    Pokemon charmander = new Charmander(10, moves);

    Pokemon bulbasour = new Bulbasour(5, moves);

    Pokemon squirtle = new Squirtle(5, moves);

//    charmander.useMove(charmander.currMoves[0], bulbasour);
//    bulbasour.useMove(bulbasour.currMoves[1], charmander);
//    squirtle.useMove(squirtle.currMoves[1], charmander);
//    charmander.useMove(charmander.currMoves[1], bulbasour);


    //charmander.useMove(charmander.currMoves[2], bulbasour);
    //charmander.useMove(charmander.currMoves[3], bulbasour);
//    charmander.useMove(charmander.currMoves[1], bulbasour);
//    bulbasour.useMove(bulbasour.currMoves[2], bulbasour);

    new Battle(charmander, bulbasour);
    new Battle(squirtle, bulbasour);



  }

  private static HashMap<String, PokeMove> genMoves() {
    HashMap<String, PokeMove> moves = new HashMap<>();
    moves.put("Tackle", new PokeMove("Tackle", MoveType.ATTACK, TypeClass.DEFAULT, 50, 0.2));
    moves.put("Leaf Blade", new PokeMove("Leaf Blade", MoveType.ATTACK, TypeClass.GRASS, 70, 0.15));
    moves.put("Flame Thrower", new PokeMove("Flame Thrower", MoveType.ATTACK, TypeClass.FIRE, 80, 0.1));
    moves.put("Water Gun", new PokeMove("Water Gun", MoveType.ATTACK, TypeClass.WATER, 65, 0.3));
    moves.put("solar warmth", new PokeMove("Solar Warmth", MoveType.HEAL, 0.2));
    moves.put("hardening", new PokeMove("hardening", MoveType.DEFENCE_BOOST, 1.2));
    moves.put("leer", new PokeMove("leer", MoveType.ATTACK_REDUCE, 0.8));
    moves.put("strength", new PokeMove("strength", MoveType.ATTACK_BOOST, 1.2));
    moves.put("confusion", new PokeMove("confusion", MoveType.DEFENCE_REDUCE, 0.8));
    return moves;
  }
}
