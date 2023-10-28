package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Playing board for standard hexagonal board. Represents a hexagonal board using cube coordinates
 * with q, r, s coordinates meaning the following constraint must hold (q + r + s = ). Hexagon must
 * be a regular hexagon with same size side length.
 */

public class StandardHexagonalBoard implements PlayingBoard {
  private Map<Hexagon, Color> occupiedTiles;

  /**
   * A list of all the hexagons on the board.
   * In order of concentric ring distance from center of hexagonal board and then
   * going from -1, 0, +1 and going clockwise.
   */
  private final List<Hexagon> hexagons;

  /**
   * Number of concentric "rings" of hexagons from center of board.
   * A size of 0 means 1 hexagon. A size of 2 means a center hexagon, and two rings around it.
   */
  private final int boardSize;
  private static final int[][] CUBE_DIRECTION_VECTORS = {{-1, 0, +1}, {0, -1, +1}, {+1, -1, 0},
          {+1, 0, -1}, {0, +1, -1}, {-1, +1, 0}};

  public StandardHexagonalBoard(int boardSize) {
    //size of 0 means there are no discs, size 1 means the game immediately ends,
    // need at least size 2
    if (boardSize < 2) {
      throw new IllegalArgumentException("Can't have a board with size of less than 2!");
    }

    //generate center hexagon
    this.hexagons = new ArrayList<>(Arrays.asList(new Hexagon(0,0,0)));
    this.boardSize = boardSize;

    //creates all the hexagons in the board and adds them to the hexagons list
    while (boardSize > 0 ) {
      List<Hexagon> toAdd = new ArrayList<>();
      for (Hexagon currHex : hexagons) {
        for (int[] relativeCoordinates: CUBE_DIRECTION_VECTORS) {
          Hexagon newHex = generateFromVector(currHex, relativeCoordinates);
          if (!this.hexagons.contains(newHex) && !toAdd.contains(newHex)) {
            toAdd.add(newHex);
          }
        }
        //System.out.println(toAdd.size());
      }
      this.hexagons.addAll(toAdd);
      boardSize--;
    }

    //instantiates occuppied Tiles with the hexagons in the 1st ring,
    // i.e distance of 1 from the center to white, black
    //alternating order
    this.occupiedTiles = new HashMap<>();
    List<Hexagon> firstRing = this.hexagons.subList(1,7);
    for (int i = 0; i < firstRing.size(); i++) {
      if (i % 2 == 0) {
        this.occupiedTiles.put(firstRing.get(i), Color.WHITE);
      } else {
        this.occupiedTiles.put(firstRing.get(i), Color.BLACK);
      }
    }
  }

  /**
   * Used to generate a new hexagon relative to a given one.
   * @param hexagon the hexagon to generate new hexagons around
   * @param coordinates the coordinates of the new hexagon relative to the current one
   * @return a new hexagon
   */
  private Hexagon generateFromVector(Hexagon hexagon, int[] coordinates) {
    if (coordinates.length != 3) {
      throw new IllegalArgumentException(
          "Invalid generation parameters for incoming coordinates\n");
    }
    return new Hexagon(hexagon.getQ() + coordinates[0],
        hexagon.getR() + coordinates[1], hexagon.getS() + coordinates[2]);
  }

  public StandardHexagonalBoard(PlayingBoard board) {
    this.occupiedTiles = board.getOccupiedTiles();
    this.hexagons = board.getBoard();
    this.boardSize = board.getSize();
  }

  @Override
  public int getSize() {
    return this.boardSize;
  }

  @Override
  public List<Hexagon> getBoard() {
    return new ArrayList<>(this.hexagons);
  }

  @Override
  public Map<Hexagon, Color> getOccupiedTiles() {
    return new HashMap<>(this.occupiedTiles);
  }

  @Override
  public boolean isOccupiedTile(Hexagon hex) {
    return this.occupiedTiles.containsKey(hex);
  }

  @Override
  public Color whoOccupiesThisTile(Hexagon hex) throws IllegalArgumentException {
    if (this.isOccupiedTile(hex)) {
      return this.occupiedTiles.get(hex);
    }
    else {
      throw new IllegalArgumentException("Incoming hexagon does not have associated Player!");
    }
  }

}
