package model;

import java.util.HashMap;
import java.util.Map;

/**
 * A mock model for testing strategy moves by manipulating the model output. Here, one of the
 * possible moves has a higher score than the rest.
 */
public class ReversiModelMockOneHighScore extends StandardHexagonalReversiModel {


  /**
   * Creates a mock model with size of board.
   *
   * @param size of the game board.
   */
  ReversiModelMockOneHighScore(int size) {
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
    moves.put(new Hexagon(0, -1, +1), 0); //top left
    moves.put(new Hexagon(1, -1, 0), 2); // top right
    moves.put(new Hexagon(1, 0, -1), 0); // right
    moves.put(new Hexagon(0, 1, -1), 9); // bottom right
    moves.put(new Hexagon(-1, 1, 0), 0); // bottom left
    moves.put(new Hexagon(-1, 0, +1), 8); // left
    return moves;
  }

  @Override
  public void pass() {

  }

  @Override
  public void move(Color color, int q, int r, int s) throws IllegalArgumentException {

  }

}
