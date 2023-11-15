package model;

import java.util.HashMap;
import java.util.Map;

/**
 * A mock model for testing strategy moves by manipulating the model output. Here, the game is
 * over from the get go.
 */
public class ReversiModelMockGameOver extends StandardHexagonalReversiModel {

  StringBuilder log;

  /**
   * Creates a mock model with size of board.
   *
   * @param size of the game board.
   */
  ReversiModelMockGameOver(StringBuilder log, int size) {
    super(size);
    this.log = log;
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
    return true;
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
    log.append("asked for moves").append("\n");
    //returns a hashmap of all the tiles in the first ring with a score zero
    Map<Hexagon, Integer> moves = new HashMap<>();
    moves.put(new Hexagon(0, -1, +1), 0); //top left
    moves.put(new Hexagon(1, -1, 0), 0); // top right
    moves.put(new Hexagon(1, 0, -1), 0); // right
    moves.put(new Hexagon(0, 1, -1), 0); // bottom right
    moves.put(new Hexagon(-1, 1, 0), 0); // bottom left
    moves.put(new Hexagon(-1, 0, +1), 0); // left
    return moves;
  }

  @Override
  public void pass() {

  }

  @Override
  public void move(Color color, int q, int r, int s) throws IllegalArgumentException {

  }

}
