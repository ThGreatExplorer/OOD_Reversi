package player;

/**
 * An interface that represents the actions a hypothetical Player could make. In practice, this
 * is only for moves that an AI can make since all the human player moves will be coming from the
 * GUI view when the human user clicks on the board.
 */
public interface PlayerActions {

  /**
   * Makes a move for a Player based on a pre-determined strategy. Returns null if no legal moves.
   * In practice, will only have its return values used for AI PLayers. If human players call this
   * method, an IllegalArgumentException will be thrown.
   *
   * @throws IllegalArgumentException if the method is called by a human player.
   */
  int[] makeMove();
}
