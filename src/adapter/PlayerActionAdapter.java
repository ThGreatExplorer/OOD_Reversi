package adapter;

import controller.PlayerActionFeaturesImpl;
import cs3500.reversi.provider.controller.PlayerFeatures;
import cs3500.reversi.provider.model.ReversiROM;
import cs3500.reversi.provider.utils.HexCoords;
import model.AdaptHexCoordsToHexagon;
import model.Hexagon;
import adapter.ReadOnlyModelAdapter;

/**
 * Represents an adapter for the player action features to the providers Player interface.
 */
public class PlayerActionAdapter implements PlayerFeatures {

  private final PlayerActionFeaturesImpl playerActionFeatures;

  public PlayerActionAdapter(PlayerActionFeaturesImpl playerActionFeatures) {
    this.playerActionFeatures = playerActionFeatures;
  }

  @Override
  public void pass() {
    this.playerActionFeatures.passMove();
  }

  @Override
  public void placeToken(HexCoords hc) {
    Hexagon hexagon = new AdaptHexCoordsToHexagon(hc).convertHexCoordsToHexagon();
    this.playerActionFeatures.playMove(hexagon.getQ(), hexagon.getR(), hexagon.getS());
  }

  @Override
  public ReversiROM getROM() {
    return new ReadOnlyModelAdapter(this.playerActionFeatures.getROM());
  }
}
