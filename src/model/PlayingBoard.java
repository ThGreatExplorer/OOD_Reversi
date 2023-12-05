package model;

import java.util.List;
import java.util.Map;

/**
 * This abstract class represents the game state of the board of a hexagonal board game.
 */
public abstract class PlayingBoard {

  /**
   * Returns a copy of the tiles inside this PlayingBoard.
   *
   * @return a List of the elements
   */
  public abstract List<Hexagon> getBoard();

  /**
   * Returns the size of the board by distance from center.
   */
  public abstract int getSize();

  /**
   * Returns a copy of the Hashmap containing Hexagon and the Player occupying that tile.
   *
   * @return the map containing the key value pair
   */
  public abstract Map<Hexagon, Color> getOccupiedTiles();

  /**
   * Checks if this tile is occupied, returns false if not.
   *
   * @param hex the incoming Hexagon
   * @return true or false based on if the tile is occupied
   */
  public abstract boolean isOccupiedTile(Hexagon hex);

  /**
   * If the tile is occupied, returns the associated Player. Otherwise, throws an exception.
   *
   * @param hex the incoming Hexagon
   * @return the associated Player
   * @throws IllegalArgumentException if the tile is not occupied.
   */
  public abstract Color whoOccupiesThisTile(Hexagon hex);

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

}
