package adapter;

import cs3500.reversi.provider.controller.ModelFeatures;
import cs3500.reversi.provider.model.ReversiModel;
import cs3500.reversi.provider.player.IPlayer;
import cs3500.reversi.provider.utils.TokenStatus;
import cs3500.reversi.provider.view.RevGUI;

/**
 * A provider model features implementation for the provider model features.
 */
public class ProviderModelFeaturesImpl implements ModelFeatures {

  RevGUI view;
  ReversiModel providerModel;
  TokenStatus tokenColor;
  IPlayer player;

  /**
   * Constructs a provider model features implementation.
   *
   * @param view the view
   * @param providerModel the provider model
   * @param player the player
   * @param status the status
   */
  public ProviderModelFeaturesImpl(RevGUI view, ReversiModel providerModel, IPlayer player,
                                   TokenStatus status) {
    this.view = view;
    this.providerModel = providerModel;
    this.player = player;
    assignColor(status);
  }


  @Override
  public void gameOver() {
    view.removeFocus();
    view.showMessage("End of Game!");
  }

  @Override
  public void yourTurn() {
    if (providerModel.isGameOver()) {
      this.gameOver();
    }
    System.out.println(tokenColor + "'s Turn");
    this.view.setFocus();
    player.yourTurn();
  }

  @Override
  public void refreshAll() {
    this.view.render();
  }

  @Override
  public TokenStatus getColor() {
    return this.tokenColor;
  }

  @Override
  public void assignColor(TokenStatus color) throws IllegalArgumentException,
          IllegalStateException {
    this.tokenColor = color;
  }
}
