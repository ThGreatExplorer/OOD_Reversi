package model;

import java.util.HashMap;
import java.util.Map;

/**
 * A mock model for testing strategy moves by manipulating the model output. Here, one of the
 * possible moves has a higher score than the rest.
 */
public class ReversiModelMockOneHighScore extends AbstractModelMock {

  protected ReversiModelMockOneHighScore(StringBuilder log) {
    super(log);
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
}
