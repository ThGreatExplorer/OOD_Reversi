package model;

import java.util.ArrayList;
import java.util.List;


/**
 * Playing board for standard hexagonal board. Represents a hexagonal board using cube coordinates
 * with q, r, s coordinates meaning the following constraint must hold (q + r + s = ). Hexagon must
 * be a regular hexagon with same size side length.
 * <p>
 *   tiles- A list of all the tiles on the board. In order of concentric ring distance from center
 *   of hexagonal board and then going from -1, 0, +1 and going clockwise.
 *   <p></p>
 *   boardSize - number of concentric "rings" of tiles from center of board. A size of 0 means
 *   1 hexagon. A size of 2 means a center hexagon, and two rings around it.
 * </p>
 */
public final class StandardHexagonalBoard extends APlayingBoard<Hexagon> {


  /**
   * Constructs a StandardHexagonalBoard i.e. a regular hexagon with the given board size
   * representing the distance from the origin.
   *
   * @param boardSize the distance from the origin (number of rings)
   */
  public StandardHexagonalBoard(int boardSize) {
    super(boardSize);

    //size of 0 means there are no discs, size 1 means the game immediately ends,
    // need at least size 2
    if (boardSize < 2) {
      throw new IllegalArgumentException("Can't have a board with size of less than 2!");
    }

    //generate center hexagon
    this.tiles.add(new Hexagon(0, 0, 0));

    //creates all the tiles in the board and adds them to the tiles list
    while (boardSize > 0) {
      List<Hexagon> toAdd = new ArrayList<>();
      for (Hexagon currHex : tiles) {
        for (int[] relativeCoordinates : Hexagon.CUBE_DIRECTION_VECTORS) {
          Hexagon newHex = generateFromVector(currHex, relativeCoordinates);
          if (!this.tiles.contains(newHex) && !toAdd.contains(newHex)) {
            toAdd.add(newHex);
          }
        }
        //System.out.println(toAdd.size());
      }
      this.tiles.addAll(toAdd);
      boardSize--;
    }

    //instantiates occupied Tiles with the tiles in the 1st ring,
    // i.e distance of 1 from the center to white, black
    //alternating order
    List<Hexagon> firstRing = this.tiles.subList(1, 7);
    for (int i = 0; i < firstRing.size(); i++) {
      if (i % 2 == 0) {
        this.occupiedTiles.put(firstRing.get(i), Color.WHITE);
      } else {
        this.occupiedTiles.put(firstRing.get(i), Color.BLACK);
      }
    }
  }

  /**
   * Constructs a copy of a given board state.
   *
   * @param board the board to make a copy of.
   */
  public StandardHexagonalBoard(APlayingBoard<Hexagon> board) {
    super(board);
  }

  @Override
  void occupyTile(int q, int r, int s, Color color) throws IndexOutOfBoundsException {
    //creates new hexagon at coordinates q, r, s
    Hexagon sampleHex = new Hexagon(q, r, s);

    if (!this.tiles.contains(sampleHex)) {
      throw new IndexOutOfBoundsException("Incoming hexagon is not on the board!");
    }

    //adds or updates a tile to have that color
    occupiedTiles.put(sampleHex, color);
  }

  @Override
  void occupyTile(int row, int col, Color color) {
    //do Nothing
  }

  /**
   * Used to generate a new hexagon relative to a given one.
   *
   * @param hexagon     the hexagon to generate new tiles around
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

}
