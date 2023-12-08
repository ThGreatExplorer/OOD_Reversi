package cs3500.reversi.provider.player;

import cs3500.reversi.provider.controller.PlayerFeatures;
import cs3500.reversi.provider.utils.HexCoords;

/**
 * Represents a player for the game of Reversi. A player can either be a human or AI.
 */
//INTERFACE INVARIANT:
// A player cannot make a move unless it has been notified by the model that it is this
//person's turn.
//Additionally, an IPlayer can then make one placeToken move OR pass.
//then it may not make another move until it is notified by the model again.
public interface IPlayer {

  /**
   * Assigns a controller to a given player.
   * @param feat a features interface
   */
  void assignController(PlayerFeatures feat);

  /**
   * Places the current player's token at a given cell.
   * @param coord a cell given in HexCoords
   */
  void placeToken(HexCoords coord);

  /**
   * Passes the current players turn.
   */
  void pass();

  /**
   * Notifies the controller that the current player can now play their turn.
   */
  void yourTurn();
}