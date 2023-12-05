package cs3500.reversi.provider.controller;

import cs3500.reversi.provider.model.ReversiROM;
import cs3500.reversi.provider.utils.HexCoords;

/**
 * Represents the interface for all the features a player has access to.
 */
public interface PlayerFeatures {

  /**
   * Passes the current player's turn.
   */
  void pass();

  /**
   * Places the current's player token color at the given hexcoord.
   * @param hc a cell's hex coordinate
   */
  void placeToken(HexCoords hc);

  /**
   * Gets the read only model so that the player can observe the model.
   * @return the read only model
   */
  ReversiROM getROM();
}
