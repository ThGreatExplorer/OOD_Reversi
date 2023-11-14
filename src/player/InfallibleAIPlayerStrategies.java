package player;


import model.Color;
import model.ReadOnlyReversiModel;

/**
 * An interface for an AI Player in a game of Reversi describing strategies whose return value
 * cannot fail: they will always return a non-null Coord, or else will throw an exception if
 * they're called on a game without a move.
 */
public interface InfallibleAIPlayerStrategies {

  /**
   * Takes in a model and a player and chooses the optimal move based on that strategy. Must return
   * non-null or throw exception.
   *
   * @param model the Reversi Game Model to make a move for.
   * @param player the Color of the AI player to make the move for.
   * @return the int array of q,r,s coordinates that is the most optimal move (in that order)
   *
   * @throws IllegalStateException if there are no valid moves.
   */
  int[] chooseMove(ReadOnlyReversiModel model, Color player) throws IllegalStateException;

}
