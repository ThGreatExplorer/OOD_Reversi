package model;

import java.util.HashMap;
import java.util.Map;

/**
 * A mock model for testing strategy moves by manipulating the model output. Here, there are no
 * possible moves.
 */
public class ReversiModelMockNoPossibleMoves extends StandardHexagonalReversiModel {


  /**
   * Creates a mock model with size of board.
   *
   * @param size of the game board.
   */
  ReversiModelMockNoPossibleMoves(int size) {
    super(size);
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
    return true;
  }

  @Override
  public Map<Hexagon, Integer> getValidMoveScores(Color color) {
    Map<Hexagon, Integer> moves = new HashMap<>();
    return moves;
  }

  @Override
  public void pass() {

  }

  @Override
  public void move(Color color, int q, int r, int s) throws IllegalArgumentException {

  }

}
