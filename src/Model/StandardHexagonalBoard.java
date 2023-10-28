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
//TODO parameterize instead of hexagon but with any BoardTile Interface type.
public class StandardHexagonalBoard implements PlayingBoard {
  private Map<Hexagon, Color> occupiedTiles;
  private final List<Hexagon> hexagons; //in order of concentric ring distance from center of
  //hexagonal board starting from -1, 0, +1 and going clockwise
  private final int boardSize; //number of concentric "rings" of hexagons from center of board.
  //A size of 0 means 1 hexagon.
  public static final int[][] cube_direction_vectors = {{-1, 0, +1}, {0, -1, +1}, {+1, -1, 0},
          {+1, 0, -1}, {0, +1, -1}, {-1, +1, 0}};
  public StandardHexagonalBoard(int boardSize) {
    if (boardSize < 2) {
      throw new IllegalArgumentException("Can't have a board with size of less than 2!");
    }
    //generate 1st, 0th hexagon
    this.hexagons = new ArrayList<>(Arrays.asList(new Hexagon(0,0,0)));
    this.boardSize = boardSize;

    while (boardSize > 0 ) {
      List<Hexagon> toAdd = new ArrayList<>();
      for (int i = 0; i < hexagons.size(); i++) {
        for (int k = 0; k < cube_direction_vectors.length; k++) {
          Hexagon tmp = hexagons.get(i).generate(cube_direction_vectors[k]);
          if (!this.hexagons.contains(tmp) && !toAdd.contains(tmp)) {
            toAdd.add(tmp);
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
