package model;

import java.util.Map;

/**
 * Implements only the observation methods of a ReversiModel game.
 */
public interface ReadOnlyReversiModel<T extends BoardTile> {

  /**
   * Checks if there's <b>Any</b> possible moves that can be made by the given player.
   * <p></p>
   * Calls getValidMoveScores.
   *
   * @param color the Color to check possible moves for
   * @return true if there are any moves that can be made
   */
  boolean canMakeAnyMove(Color color);

  /**
   * Checks if a move is valid. Calls <code>countDirectionValidSequence</code> to check if
   * any of the CUBE_DIRECTION_VECTORS contains valid sequences of moves.
   *
   * @param q coordinate of incoming BoardTile
   * @param r coordinate of incoming BoardTile
   * @param s coordinate of incoming BoardTile
   * @return true or false depending on if move is valid
   * @throws IllegalArgumentException if the move is invalid for whatever reason including the move
   *     is logically invalid, or if the move is out of bounds, or this player can't make a move!!!
   */
  boolean isValidMove(Color color, int q, int r, int s);

  /**
   * Checks if a move is valid. Calls <code>determineValidDirectionsForMove</code> that checks if
   * any of the six directions contains a valid move.
   *
   * @param row the row coordinate of incoming BoardTile
   * @param col the col coordinate of incoming BoardTile
   * @return true or false depending on if move is valid
   * @throws IllegalArgumentException if the move is invalid for whatever reason including the move
   *     is logically invalid, or if the move is out of bounds, or this player can't make a move!!!
   */
  boolean isValidMove(Color color, int row, int col);

  /**
   * Gets the list of all the valid moves with the associated scores for that move
   * which could be played on the board for a given color. Does not track any moves that are
   * invalid (i.e. flipping no tiles).
   *
   * @param color the color to get the moves for
   * @return the map of valid BoardTiles with their score that you could play on
   */
  Map<T, Integer> getValidMoveScores(Color color);

  /**
   * Returns the score of the Player associated with a particular color. Counts all the BoardTiles
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
  APlayingBoard<T> getCurrentBoardState();

  /**
   * Returns the color of the current Player at a coordinate or null if no player is there.
   *
   * @return Color or null
   */
  Color getColorAt(int q, int r, int s) throws IllegalArgumentException;

  /**
   * Returns the color of the current Player at a coordinate or null if no player is there.
   *
   * @return Color or null
   */
  Color getColorAt(int row, int col) throws IllegalArgumentException;

  boolean getFlagPass();
}
