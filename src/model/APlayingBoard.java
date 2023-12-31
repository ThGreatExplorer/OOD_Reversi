package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This abstract class represents the game state of the board of a hexagonal board game.
 */
public abstract class APlayingBoard<T extends BoardTile> {

  protected final Map<T, Color> occupiedTiles;

  /**
   * A list of all the hexagons on the board.
   * In order of concentric ring distance from center of hexagonal board and then
   * going from -1, 0, +1 and going clockwise.
   */
  protected final List<T> tiles;
  protected final int boardSize;


  /**
   * Constructs a copy of a given board state.
   *
   * @param board the board to make a copy of.
   */
  public APlayingBoard(APlayingBoard<T> board) {
    this.occupiedTiles = board.getOccupiedTiles();
    this.tiles = board.getBoard();
    this.boardSize = board.getSize();
  }

  public APlayingBoard(int boardSize) {
    this.boardSize = boardSize;
    this.occupiedTiles = new HashMap<>();
    this.tiles = new ArrayList<>();
  }


  /**
   * Returns a copy of the tiles inside this PlayingBoard.
   *
   * @return a List of the elements
   */
  public List<T> getBoard() {
    return new ArrayList<>(this.tiles);
  }

  /**
   * Returns the size of the board by distance from center.
   */
  public int getSize() {
    return this.boardSize;
  }

  /**
   * Returns a copy of the Hashmap containing Hexagon and the Player occupying that tile.
   *
   * @return the map containing the key value pair
   */
  public Map<T, Color> getOccupiedTiles() {
    return new HashMap<>(this.occupiedTiles);
  }

  /**
   * Checks if this tile is occupied, returns false if not.
   *
   * @param tile the incoming BoardTile
   * @return true or false based on if the tile is occupied
   */
  public boolean isOccupiedTile(T tile) {
    return this.occupiedTiles.containsKey(tile);
  }

  /**
   * If the tile is occupied, returns the associated Player. Otherwise, throws an exception.
   *
   * @param tile the incoming BoardTile
   * @return the associated Player
   * @throws IllegalArgumentException if the tile is not occupied.
   */
  public Color whoOccupiesThisTile(T tile) {
    if (this.isOccupiedTile(tile)) {
      return this.occupiedTiles.get(tile);
    } else {
      throw new IllegalArgumentException("Incoming tile " + tile +
              " does not have associated Player!");
    }
  }

  /**
   * Adds a new disc or flips a disc at the given coordinate to the new color.
   *
   * @param q     the q cubic coordinate for the disc to be changed
   * @param r     the r cubic coordinate for the disc to be changed
   * @param s     the s cubic coordinate for the disc to be changed
   * @param color the color of the new disc
   * @throws IndexOutOfBoundsException if the incoming coordinates are invalid
   */
  abstract void occupyTile(int q, int r, int s, Color color);

  /**
   * Adds a new disc or flips a disc at the given coordinate to the new color.
   *
   * @param row   the row coordinate for the square to be changed
   * @param col   the col coordinate for the square to be changed
   * @param color the color of the new disc
   * @throws IndexOutOfBoundsException if the incoming coordinates are invalid
   */
  abstract void occupyTile(int row, int col, Color color);

}
