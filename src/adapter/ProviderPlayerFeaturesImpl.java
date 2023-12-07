package adapter;

import controller.PlayerActionFeaturesImpl;
import cs3500.reversi.provider.controller.PlayerFeatures;
import cs3500.reversi.provider.model.ReversiModel;
import cs3500.reversi.provider.model.ReversiROM;
import cs3500.reversi.provider.utils.HexCoords;
import cs3500.reversi.provider.utils.TokenStatus;
import cs3500.reversi.provider.view.RevGUI;
import model.AdaptHexCoordsToHexagon;
import model.Hexagon;
import adapter.ReadOnlyModelAdapter;

/**
 * Represents an adapter for the player action features to the providers Player interface.
 */
public class ProviderPlayerFeaturesImpl implements PlayerFeatures {

  RevGUI view;
  ReversiModel providerModel;
  TokenStatus tokenColor;

  public ProviderPlayerFeaturesImpl(RevGUI view, ReversiModel providerModel, TokenStatus tokenColor) {
    this.view = view;
    this.providerModel = providerModel;
    this.tokenColor = tokenColor;
  }

  @Override
  public void pass() {
    this.providerModel.pass();
  }

  @Override
  public void placeToken(HexCoords hc) {
    this.providerModel.placeToken(hc);
  }

  @Override
  public ReversiROM getROM() {
    return this.providerModel.copy();
  }
}
