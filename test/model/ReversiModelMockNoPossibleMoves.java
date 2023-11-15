package model;

import java.util.HashMap;
import java.util.Map;

/**
 * A mock model for testing strategy moves by manipulating the model output. Here, there are no
 * possible moves.
 */
public class ReversiModelMockNoPossibleMoves extends AbstractModelMock {


  protected ReversiModelMockNoPossibleMoves(StringBuilder log) {
    super(log);
  }


  @Override
  public Map<Hexagon, Integer> getValidMoveScores(Color color) {
    this.log.append("asked for moves").append("\n");
    return new HashMap<>();
  }


}
