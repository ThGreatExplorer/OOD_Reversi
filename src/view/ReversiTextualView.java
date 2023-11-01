package view;

import model.ReversiModel;

/**
 * A class for rendering a textual representation of the flat-top hexagonal Reversi board with a
 * black and white player.
 */
public class ReversiTextualView extends HexagonRepresentation implements TextualView {
  ReversiModel model;

  /**
   * Constructs a new ReversiTextualView for rendering a Reversi board.
   *
   * @param model The model for the game for which to create the view of the board.
   */
  public ReversiTextualView(ReversiModel model) {
    this.model = model;
  }

  /**
   * @implNote In this render, "_" represents an empty space, "X" is a black disc, and "O" is a
   * white disc.
   */
  @Override
  public String render() throws IllegalStateException {
    //get 0, 1, 2 representation of the board and details of the set-up
    int[][] boardRepresentation = boardByNumber(model.getCurrentBoardState());
    int height = boardRepresentation.length;
    int boardSize = model.getCurrentBoardState().getSize();

    StringBuilder boardString = new StringBuilder();

    //iterate through each row of the board representation
    for (int rowNum = 0; rowNum < height; rowNum++) {
      int[] boardRow = boardRepresentation[rowNum];
      int boardStartIndex = (height + 1 - boardRow.length) / 2;
      int boardEndIndex = boardStartIndex + boardRow.length - 1;

      //offset every other line to mimic the hexagon pattern
      if (rowNum % 2 == boardSize % 2) {
        boardString.append(" ");
      }

      //piece together each row one by one
      for (int col = 0; col < height; col++) {
        //if the current space is on the board
        if (col >= boardStartIndex && col <= boardEndIndex) {
          //add the correct disc from the board to the string
          int disc = boardRow[col - boardStartIndex];
          switch (disc) {
            case 0:
              boardString.append("_ ");
              break;
            case 1:
              boardString.append("O ");
              break;
            case 2:
              boardString.append("X ");
              break;
            default:
              throw new IllegalStateException("Board spaces should only be black, white or empty.");
          }
        } else {
          //if current space is not on the board, add a space
          boardString.append("  ");
        }
      }
      //next row
      boardString.append("\n");
    }
    //remove the last "\n"
    boardString.delete(boardString.length() - 1, boardString.length());
    return boardString.toString();
  }
}
