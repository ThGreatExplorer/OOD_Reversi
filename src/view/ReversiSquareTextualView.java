package view;

import model.APlayingBoard;
import model.Color;
import model.ReadOnlyReversiModel;
import model.Square;

public class ReversiSquareTextualView implements TextualView {

  ReadOnlyReversiModel<Square> board;

  public ReversiSquareTextualView(ReadOnlyReversiModel<Square> board) {
    this.board = board;
  }

  @Override
  public String render() throws IllegalStateException {
    APlayingBoard<Square> squareAPlayingBoard = this.board.getCurrentBoardState();
    int size = squareAPlayingBoard.getSize();

    System.out.println(squareAPlayingBoard.getBoard());
    System.out.println(squareAPlayingBoard.getOccupiedTiles());

    StringBuilder boardString = new StringBuilder();

    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        Color color = board.getColorAt(row, col);
        if (color == null) {
          boardString.append("_  ");
        } else {
          switch (color) {
            case WHITE:
              boardString.append("O  ");
              break;
            case BLACK:
              boardString.append("X  ");
              break;
          }
        }
      }
      boardString.append("\n");
    }

    return boardString.toString().strip();
  }
}
