package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class just for generating mock game states (logically valid and invalid) for purposes of
 * testing.
 */
final class ReversiModelGameStateGeneration {

  /**
   * Generate a game that is already over with white tiles filling rest of space.
   *
   *                        O O O
   *                       O X O O
   *                      O O O X O
   *                       O X O O
   *                        O O O
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

  /**
   * Generates a game of size 2 with 3 rings, that has the center empty.
   *
   *                        O O O
   *                       O X O O
   *                      O O _ X O
   *                       O X O O
   *                        O O O
   *
   * @return a board state with the rest of the board filled by white and center empty.
   */
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

  /**
   * Generates a game of 3 rings with a non-filled board but black restricting white such that
   * black and white both can't move.
   *
   *                        _ X _
   *                       X X O X
   *                      _ O _ X _
   *                       X X O X
   *                        _ X _
   *
   * @return the associated board state.
   */
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