package adapter;

import cs3500.reversi.provider.controller.PlayerFeatures;
import cs3500.reversi.provider.player.IPlayer;
import cs3500.reversi.provider.strategy.PlaceStrategy;
import cs3500.reversi.provider.utils.HexCoords;

//TODO
public class HumanPlayerAdapter implements IPlayer {

  PlayerFeatures features;

  public HumanPlayerAdapter(){}

  @Override
  public void assignController(PlayerFeatures feat) {
    this.features = feat;
  }

  @Override
  public void placeToken(HexCoords coord) {
  }

  @Override
  public void pass() {
  }

  @Override
  public void yourTurn() {
  }
}
