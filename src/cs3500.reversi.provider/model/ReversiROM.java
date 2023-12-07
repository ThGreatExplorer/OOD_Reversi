package cs3500.reversi.provider.model;

import java.util.List;

import cs3500.reversi.provider.board.HexReversiBoard;
import cs3500.reversi.provider.controller.ModelFeatures;
import cs3500.reversi.provider.utils.TokenStatus;
import cs3500.reversi.provider.utils.HexCoords;

/**
 * A read only model of Hexigonal Reversi that doesn't support mutating the board.
 */
public interface ReversiROM {

  /**
   * @return a mutable copy of this read-only model.
   */
  ReversiModel copy();

  /**
   * Returns a copy of the token at the coordinates.
   * @param c the coordinates you wish to get the token from.
   * @return the copy.
   * @throws IllegalArgumentException if the coordinates are null or off the board.
   */
  TokenStatus getTokenAt(HexCoords c) throws IllegalArgumentException;

  /**
   * Gets the side length of the game board. This is the number of cells wide/tall the hexagon
   * board is.
   * @return side length
   */
  int getSideLength() throws IllegalStateException;

  /**
   * Gets the current score for black.
   * @return black's score
   * @throws IllegalStateException if the game has not been started.
   */
  int getScoreBlack();

  /**
   * Gets the current score for white.
   * @return white's score
   * @throws IllegalStateException if the game has not been started.
   */
  int getScoreWhite();

  /**
   * Determines if the game is over. A game of Reversi ends when two players pass back to back or
   * the board is full.
   * @return if the game is over
   */
  boolean isGameOver();

  /**
   * Gets a COPY of the board that this game is being played on.
   * @return the copied board.
   */
  HexReversiBoard getBoard();

  /**
   * States whether a move by the current player at these hexCoords is legal.
   * @param hc the coordinates you wish to play at.
   * @return boolean stating whether the move was true.
   * @throws IllegalStateException if the game has not been started.
   * @throws IllegalArgumentException if HexCoords are null.
   */
  boolean isMoveLegal(HexCoords hc) throws IllegalStateException, IllegalArgumentException;

  /**
   * Lists all possible moves in order from top left to bottom right.
   * if the returned list is empty, there are no possible moves for the player whose turn it is.
   * @return the wished for list.
   * @throws IllegalStateException if the game has not been started.
   */
  List<HexCoords> getPossibleMoves();

  /**
   * States the value of a given move.
   * @param hc the coordinates you wish to play at.
   * @return the value of the move.
   * @throws IllegalStateException if the game has not been started.
   * @throws IllegalArgumentException if the move is not legal or HexCoords are null.
   */
  int valueOfMove(HexCoords hc) throws IllegalStateException, IllegalArgumentException;

  /**
   * Shows what color's turn it is currently.
   * @return the color of whose turn it is.
   * @throws IllegalStateException if the game has not been started.
   */
  TokenStatus whoseTurn() throws IllegalStateException;

  /**
   * Adds the give modelFeaturesInterface as a listener to this model's actions.
   * @param listener the ModelFeatures Interface you wish to listen.
   */
  void addMoveListener(ModelFeatures listener);
}
