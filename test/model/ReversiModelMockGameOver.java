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

}
