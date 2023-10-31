package Model;

/**
 * The interface representing a model that functions as both a rules keeper and the board state
 * for a game of Reversi, facilitating communications from the board state to the rules keeper.
 */
public interface ReversiModel {

  /**
   * Returns a copy of the current Player's color
   *
   * @return the color of the player
   */
  Color getCurrentPlayer();

  /**
   * Returns a copy of the board state, returning <code>PlayingBoard</code>.
   *
   * @return a copy of the board state.
   */
  PlayingBoard getCurrentBoardState();

  /**
   * Checks if there's any possible moves that can be made by the given player.
   *
   * @param color the Color to check possible moves for
   * @return whether there are any moves that can be made
   */
  boolean canMakeMove(Color color);

  /**
   * Player makes a move to pass out of their turn. Switches to the next Player in the ENUM Players
   * by ordinal number.
   */
  void pass();

  /**
   * Performs a move if valid, where a valid move is defined as for a given Player A,
   * if the disc being played is adjacent (in at least one direction) to a straight line of
   * the opponent player B’s discs, at the far end of which is another player A disc.
   * <p></p>
   * Flips all the discs of Player B between the two ends of A.
   * <p></p>
   * Switches the Player.
   *
   * @throws IllegalArgumentException no legal moves or move is not valid
   */
  void move(int q, int r, int s) throws IllegalArgumentException;

  /**
   * Checks if the game is over. A game can be over if there are no more possible moves to be made
   * for either player (and thus must both pass), or if all the tiles are occupied.
   *
   * @return true or false depending on if the game is over.
   */
  boolean isGameOver();

  /**
   * Returns the score of the Player associated with a particular color. Counts all the hexagons
   * occupied by that Player's color.
   *
   * @param color the color of the Player to check for.
   * @return the score of that Player
   */
  int getScore(Color color);
}
