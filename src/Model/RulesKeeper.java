package Model;

import Player.Players;

public interface RulesKeeper {

  //TODO refactor this to have BoardTile instead of Hexagon
  boolean isValidMove(PlayingBoard board, Hexagon toMove, Players player);
}
