package adapter;

import cs3500.reversi.provider.controller.PlayerFeatures;
import cs3500.reversi.provider.player.IPlayer;
import cs3500.reversi.provider.utils.HexCoords;

/**
 * Represents a Human Player Adapter for the providers Player interface.
 */
public class HumanPlayerAdapter implements IPlayer {

  PlayerFeatures features;

  /**
   * Constructs a Human Player Adapter for the providers Player interface.
   */
  public HumanPlayerAdapter() {
  }

  @Override
  public void assignController(PlayerFeatures feat) {
    this.features = feat;
  }

  @Override
  public void placeToken(HexCoords coord) {
    //does nothing
  }

  @Override
  public void pass() {
    //does nothing
  }

  @Override
  public void yourTurn() {
    //does nothing
  }
}
