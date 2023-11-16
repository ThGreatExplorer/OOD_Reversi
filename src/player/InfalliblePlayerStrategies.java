package player;


import model.Color;
import model.ReadOnlyReversiModel;

/**
 * An interface describing strategies in a game of Reversi whose return value cannot fail: they
 * will always return a next placement for a game piece, or else will throw an exception if
 * they're called on a game without a move.
 */
public interface InfalliblePlayerStrategies {

  /**
   * Takes in a model and a player and chooses the optimal move based on that strategy. Must return
   * non-null array or throw exception.
   *
   * @param model    the Reversi Game Model to make a move for.
   * @param player   the Color of the AI player to make the move for.
   * @return the int array of q,r,s coordinates (the cubic coordinates for a Hexagon) that is the
   *         most optimal move (in that order)
   * @throws IllegalStateException if there are no valid moves or the game is over.
   */
  int[] chooseMove(ReadOnlyReversiModel model, Color player) throws IllegalStateException;

}
