package Model;

public interface RulesKeeper {

  //TODO refactor this to have BoardTile instead of Hexagon
  boolean isValidMove(PlayingBoard board, Hexagon toMove, Color player);
}
