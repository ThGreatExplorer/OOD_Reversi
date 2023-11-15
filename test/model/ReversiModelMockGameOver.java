package model;

import java.util.HashMap;
import java.util.Map;

/**
 * A mock model for testing strategy moves by manipulating the model output. Here, the game is
 * over from the get go.
 */
public class ReversiModelMockGameOver extends AbstractModelMock {


  protected ReversiModelMockGameOver(StringBuilder log) {
    super(log);
  }

  @Override
  public boolean isGameOver() throws IllegalArgumentException {
    return true;
  }


  @Override
  public Map<Hexagon, Integer> getValidMoveScores(Color color) {
    this.log.append("asked for moves").append("\n");
    return new HashMap<>();
  }

}
