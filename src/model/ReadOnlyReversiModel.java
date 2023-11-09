package model;

/**
 * Implements only the observation methods of a ReversiModel game.
 */
public interface ReadOnlyReversiModel {

  /**
   * Returns the score of the Player associated with a particular color. Counts all the hexagons
   * occupied by that Player's color.
   *
   * @param color the color of the Player to check for.
   * @return the score of that Player
   */
  int getScore(Color color);

  /**
   * Returns the winner of the game if it is over.
   *
   * @return the winner or null if no winner.
   */
  Color getWinner();

  /**
   * Checks if the game is over. A game can be over if there are no more possible moves to be made
   * for either player (and thus must both pass), or if all the tiles are occupied.
   *
   * @return true or false depending on if the game is over.
   * @throws IllegalArgumentException if game is already over.
   */
  boolean isGameOver() throws IllegalArgumentException;

  /**
   * Returns the size of the board for this game of Reversi.
   *
   * @return int representing size
   */
  int getSize();

  /**
   * Returns a copy of the current Player's color.
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
   * Returns the color of the current Player at a coordinate or null if no player is there.
   *
   * @return Color or null
   */
  Color getColorAt(int q, int r, int s) throws IllegalArgumentException;

}
