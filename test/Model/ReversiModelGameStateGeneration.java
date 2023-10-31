package Model;

import java.util.ArrayList;
import java.util.List;

public class ReversiModelGameStateGeneration {

  /**
   * Generate a game that is already over with white tiles filling rest of space.
   *
   * @return a Board State with the game already over and white winning.
   */
  static StandardHexagonalBoard generate3RingsWhiteFilled() {
    StandardHexagonalBoard board = new StandardHexagonalBoard(2);
    List<Hexagon> occupied = new ArrayList<>(board.getOccupiedTiles().keySet());
    for (Hexagon hex : occupied) {
      for (int[] direction : Hexagon.CUBE_DIRECTION_VECTORS) {
        Hexagon tmp = hex.generateFromVector(direction);
        if (!board.isOccupiedTile(tmp)) {
          board.occupyTile(tmp.getQ(), tmp.getR(), tmp.getS(), Color.WHITE);
        }
      }
    }
    return board;
  }

  static StandardHexagonalBoard generate3RingsNoCenter() {
    StandardHexagonalBoard board = new StandardHexagonalBoard(2);
    List<Hexagon> occupied = new ArrayList<>(board.getOccupiedTiles().keySet());
    for (Hexagon hex : occupied) {
      for (int[] direction : Hexagon.CUBE_DIRECTION_VECTORS) {
        Hexagon tmp = hex.generateFromVector(direction);
        if (tmp.getDistance() != 0) { //not the origin
          if (!board.isOccupiedTile(tmp)) {
            board.occupyTile(tmp.getQ(), tmp.getR(), tmp.getS(), Color.WHITE);
          }
        }
      }
    }
    return board;
  }

  static StandardHexagonalBoard generate3RingsBlackAndWhiteCantMove() {
    StandardHexagonalBoard board = new StandardHexagonalBoard(2);
    board.occupyTile(-1,-1,2, Color.BLACK);
    board.occupyTile(1,-2,1, Color.BLACK);
    board.occupyTile(2,-1,-1,Color.BLACK);
    board.occupyTile(1,1,-2,Color.BLACK);
    board.occupyTile(-1,2,-1,Color.BLACK);
    board.occupyTile(-2,1,1,Color.BLACK);
    return board;
  }
}
