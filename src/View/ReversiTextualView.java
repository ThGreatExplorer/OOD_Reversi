package View;

import java.util.ArrayList;
import java.util.Arrays;

import Model.PlayingBoard;
import Model.ReversiModel;

public class ReversiTextualView extends HexagonRepresentation implements TextualView {
  ReversiModel model;

  public ReversiTextualView(ReversiModel model) {
    this.model = model;
  }

  public String toString() {
    int[][] boardRepresentation = boardByNumber(model.getCurrentBoardState());
    int height = boardRepresentation.length;
    int boardSize = model.getCurrentBoardState().getSize();

    StringBuilder boardString = new StringBuilder();

    for (int rowNum = 0; rowNum < height; rowNum++) {
      int[] boardRow = boardRepresentation[rowNum];
      int boardStartIndex = (height + 1 - boardRow.length) / 2;
      int boardEndIndex = boardStartIndex + boardRow.length - 1;

      if (rowNum % 2 == boardSize % 2) {
        boardString.append(" ");
      }

      for (int col = 0; col < height; col++) {
        if (col >= boardStartIndex && col <= boardEndIndex) {
          int disc = boardRow[col - boardStartIndex];
          //TODO coupled to have 2 players, so make that invarient
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
          }
        } else {
          boardString.append("  ");
        }
      }

      boardString.append("\n");

    }

    boardString.delete(boardString.length() - 1, boardString.length());
    return boardString.toString();
  }
}
