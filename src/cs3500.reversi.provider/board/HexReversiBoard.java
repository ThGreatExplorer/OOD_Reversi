package cs3500.reversi.provider.board;

import java.util.Set;

import cs3500.reversi.provider.utils.TokenStatus;
import cs3500.reversi.provider.utils.HexCoords;

/**
 * The board for a hexigonal reversi game.
 */
public interface HexReversiBoard {

  /**
   * Gets the color of a piece at the given HexCoords on the Board.
   * @param hc the axial coordinate of the piece you would like to get.
   * @return the color or null if the space is empty.
   * @throws IndexOutOfBoundsException if the coordinate is off the board.
   */
  TokenStatus getPieceAt(HexCoords hc) throws IndexOutOfBoundsException;

  /**
   * Gets the side length of this hexagonal board.
   * @return the appropriate side length.
   */
  int getSideLength();

  /**
   /**
   * Adds a token of the given color to this board.
   * @param hc the coordinates you wish to add the colored token at.
   * @param gc the GameColor of the token.
   * @throws IndexOutOfBoundsException if the coordinates are off the board.
   */
  void addToBoard(HexCoords hc, TokenStatus gc) throws IndexOutOfBoundsException;

  /**
   * Copies this board.
   * @return a copy of this board.
   */
  HexReversiBoard copyBoard();

  /**
   * Gets all the valid coordinates of this board.
   * The purpose of this method is to allow for separation of board and view.
   * @return a set of all coordinates.
   */
  Set<HexCoords> getValidCoords();

  /**
   * Two boards are equal if they are the same size with the same pieces at the same
   * axial coordinates.
   *
   * @param o the object you wish to compare this with.
   * @return if the object is equivalent to this HexReversiBoard.
   */
  boolean equals(Object o);
}
