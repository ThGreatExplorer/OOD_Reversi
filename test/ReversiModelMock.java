import java.util.HashMap;

import model.Color;
import model.Hexagon;
import model.PlayingBoard;
import model.ReversiModel;
import model.StandardHexagonalBoard;

/**
 * A mock model for testing strategy moves by logging the history of moves into an appendable.
 */
public class ReversiModelMock implements ReversiModel {

  StringBuilder ap;
  PlayingBoard board;

  /**
   * Creates a mock model using a StringBuilder to track the history of moves.
   *
   * @param ap the StringBuilder.
   */
  ReversiModelMock(StringBuilder ap, PlayingBoard board) {
    this.ap = ap;
    this.board = new StandardHexagonalBoard(board);
  }

  @Override
  public int getScore(Color color) {
    return 0;
  }

  @Override
  public Color getWinner() {
    return null;
  }

  @Override
  public boolean isGameOver() throws IllegalArgumentException {
    return false;
  }

  @Override
  public int getSize() {
    return 0;
  }

  @Override
  public Color getCurrentPlayer() {
    return null;
  }

  @Override
  public PlayingBoard getCurrentBoardState() {
    return null;
  }

  @Override
  public Color getColorAt(int q, int r, int s) throws IllegalArgumentException {
    return null;
  }

  @Override
  public boolean canMakeAnyMove(Color color) {
    return false;
  }

  @Override
  public boolean isValidMove(Color color, int q, int r, int s) {
    return false;
  }

  @Override
  public HashMap<Hexagon, Integer> getValidMoveScores(Color color) {
    return null;
  }

  @Override
  public void pass() {
    this.ap.append("Pass").append("\n");
  }

  @Override
  public void move(Color color, int q, int r, int s) throws IllegalArgumentException {
    this.ap.append("Color: ").append(color).append("Move to Q:").append(q).append(" R:").
            append(r).append(" S:").append(s).append("\n");
  }

  /**
   * For testing the move method.
   */
}
