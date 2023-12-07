package adapter;

import cs3500.reversi.provider.controller.PlayerFeatures;
import cs3500.reversi.provider.player.IPlayer;
import cs3500.reversi.provider.strategy.PlaceStrategy;
import cs3500.reversi.provider.utils.HexCoords;

public class AIPlayerAdapter implements IPlayer {

  boolean myTurn = false;
  PlayerFeatures features;
  PlaceStrategy strategy;

  public AIPlayerAdapter(PlaceStrategy strategy) {
    this.strategy = strategy;
  }

  @Override
  public void assignController(PlayerFeatures feat) {
    this.features = feat;
  }

  @Override
  public void placeToken(HexCoords coord) {
    features.placeToken(coord);
  }

  @Override
  public void pass() {
    features.pass();
  }

  @Override
  public void yourTurn() {
    this.myTurn = true;
  }
}
