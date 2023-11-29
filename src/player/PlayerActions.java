package player;

/**
 * Interface for the actions a player can take.
 */
public interface PlayerActions {

  /**
   * Player will choose which move to make.
   *
   * @return An array of integers representing the q, r, and s cubic coordinates of where to place
   *         the next move.
   */
  int[] makeMove();
}
