package View;

import java.util.Map;

import Model.Color;
import Model.Hexagon;
import Model.PlayingBoard;

/**
 * Represents the current board as a jagged 2-d array.
 */
class HexagonRepresentation {

  /**
   * Represents the flat-top hexagon board as a 2-D array of integers in the shape of the board.
   * The first row will be the same as the board size plus 1, while the middle row will be the same
   * length as the widest part of the hexagon board.
   * The integers will represent the colors: 0 means not occupied, 1 white and 2 black.
   *
   * @param boardState The state of the current board.
   * @return A 2-D jagged array of integers.
   */
  static int[][] boardByNumber(PlayingBoard boardState) {
    int boardSize = boardState.getSize();
    int maxWidth = boardSize * 2 + 1;

    //create a list of zeros with the proper size
    int[][] board = new int[maxWidth][];
    int rowIndex = 0;
    //top half of the hexagon board, including the middle row
    for (int rowSize = boardSize + 1; rowSize <= maxWidth; rowSize++) {
      board[rowIndex] = new int[rowSize];
      rowIndex++;
    }
    //bottom half of the hexagon board, excluding the middle row
    for (int rowSize = maxWidth - 1; rowSize >= boardSize + 1; rowSize--) {
      board[rowIndex] = new int[rowSize];
      rowIndex++;
    }

    //go through the occupied list
    for (Map.Entry<Hexagon, Color> disc : boardState.getOccupiedTiles().entrySet()) {
      Hexagon tile = disc.getKey();
      Color color = disc.getValue();

      //get row (up and down)
      int row = tile.getR() + boardSize;

      //get col (left and right)
      int col;
      int q = tile.getQ();
      if (row >= boardSize) {
        col = q + boardSize;
      } else {
        col = q + row;
      }

      //change the zero if occupied
      switch (color) {
        case WHITE:
          board[row][col] = 1;
          break;
        case BLACK:
          board[row][col] = 2;
          break;
        default:
          throw new IllegalStateException("Colors on board need to be black or white");
      }
    }

    return board;
  }
}
