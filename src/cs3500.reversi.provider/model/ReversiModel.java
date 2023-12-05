package cs3500.reversi.provider.model;

import cs3500.reversi.provider.utils.HexCoords;

/**
 * Represents the primary model interface for a game of reversi that all
 * implementations should follow. This implementation of Reversi uses hexagon shaped cells as an
 * individual
 */
public interface ReversiModel extends ReversiROM {


  /**
   * Placing a token on the board.
   * @param coord the coordinates you wish to place this token.
   * @throws IllegalArgumentException if the coords are off the board.
   * @throws IllegalStateException if the coordinates already have a token at that location, or if
   *     the game has not been started.
   */
  void placeToken(HexCoords coord) throws IllegalStateException, IllegalArgumentException;

  /**
   * Skips the current player's turn. When two players pass back to back, the game is over.
   * @throws IllegalStateException if the game has not been started.
   */
  void pass() throws IllegalStateException;

  /**
   * Starts the game to be played.
   * @throws IllegalStateException if the game has already been started or it has not been given
   *     two ModelFeatures listeners to play with.
   */
  void startGame() throws IllegalStateException;
}
