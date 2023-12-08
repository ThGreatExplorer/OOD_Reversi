package adapter;

import cs3500.reversi.provider.controller.PlayerFeatures;
import cs3500.reversi.provider.model.ReversiModel;
import cs3500.reversi.provider.model.ReversiROM;
import cs3500.reversi.provider.utils.HexCoords;
import cs3500.reversi.provider.utils.TokenStatus;
import cs3500.reversi.provider.view.RevGUI;

/**
 * Represents a Player Features implementation for the providers interfaces.
 */
public class ProviderPlayerFeaturesImpl implements PlayerFeatures {

  private final RevGUI view;
  private final ReversiModel providerModel;
  private final TokenStatus tokenColor;

  /**
   * Constructs a provider player features implementation.
   *
   * @param view the view
   * @param providerModel the provider model
   * @param tokenColor the token color
   */
  public ProviderPlayerFeaturesImpl(RevGUI view, ReversiModel providerModel,
                                    TokenStatus tokenColor) {
    this.view = view;
    this.providerModel = providerModel;
    this.tokenColor = tokenColor;
  }

  @Override
  public void pass() {
    this.providerModel.pass();
    this.view.removeFocus();
  }

  @Override
  public void placeToken(HexCoords hc) {
    try {
      this.providerModel.placeToken(hc);
      this.view.removeFocus();
    } catch (IllegalArgumentException e) {
      this.view.showMessage(e.getMessage());
    }
  }

  @Override
  public ReversiROM getROM() {
    return this.providerModel.copy();
  }
}
