package adapter;

import java.util.List;
import cs3500.reversi.provider.controller.PlayerFeatures;
import cs3500.reversi.provider.model.ReversiROM;
import cs3500.reversi.provider.player.IPlayer;
import cs3500.reversi.provider.strategy.PlaceStrategy;
import cs3500.reversi.provider.utils.HexCoords;

/**
 * Represents an adapter for the AI Players to the providers Player interface.
 */
public class AIPlayerAdapter implements IPlayer {

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
    System.out.println("Your Turn: " + this);
    System.out.println(features);
    ReversiROM model = features.getROM();
    List<HexCoords> coordsList = this.strategy.getValidMoves(model, model.getPossibleMoves());
    System.out.println(coordsList);
    if (coordsList == null || coordsList.isEmpty()) {
      System.out.println(this + "passed");
      this.pass();
    } else {
      this.placeToken(coordsList.get(0));
      System.out.println(this + "moved to" + coordsList.get(0));
    }
  }
}
