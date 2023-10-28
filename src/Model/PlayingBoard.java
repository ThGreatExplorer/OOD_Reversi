package Model;

import java.util.List;
import java.util.Map;

import Player.Players;

public interface PlayingBoard {

  /**
   * Returns a copy of the tiles inside this playboard.
   *
   * @return a List of the elements
   */
  //TODO make this generic?
  List<Hexagon> getBoard();

  /**
   * Returns the size of the board by distance from center.
   */
  int getSize();

  /**
   * Returns a copy of the Hashmap containing Hexagon and the Player occupying that tile.
   *
   * @Return the map containing the key value pair
   */
  Map<Hexagon, Players> getOccupiedTiles();

  /**
   * Checks if this tile is occupied, returns false if not.
   *
   * @param hex the incoming Hexagon
   * @return true or false based on if the tile is occupied
   */
  boolean isOccupiedTile(Hexagon hex);

  /**
   * If the tile is occupied, returns the associated Player. Otherwise, throws an exception.
   *
   * @param hex the incoming Hexagon
   * @return the associated Player
   * @throws IllegalArgumentException if the tile is not occupied.
   */
  Players whoOccupiesThisTile(Hexagon hex);
}
