package player;

import java.util.Optional;
import model.Color;
import model.ReadOnlyReversiModel;

/**
 * An interface describing incomplete or partial strategies, that might successfully choose a move
 * and might not for a game of Reversi.
 */
public interface FallibleAIPlayerStrategies {

  /**
   * Takes in a model and a player and chooses the optimal move based on that strategy. Could return
   * null.
   *
   * @param model the Reversi Game Model to make a move for.
   * @param player the Color of the AI player to make the move for.
   * @return an optional int array of q,r,s coordinates that is the most optimal move
   *     (in that order)
   */
  Optional<int[]> chooseMove(ReadOnlyReversiModel model, Color player) throws IllegalStateException;

}
