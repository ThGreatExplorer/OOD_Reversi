package adapter;

import cs3500.reversi.provider.controller.ModelFeatures;
import cs3500.reversi.provider.model.ReversiModel;
import cs3500.reversi.provider.utils.HexCoords;
import model.Hexagon;

/**
 * Represents an adapter for the model features to the providers ReversiModel interface.
 */
public class ModelAdapter extends ReadOnlyModelAdapter implements ReversiModel {

  private final model.ReversiModel model;

  /**
   * Constructs a new ModelAdapter.
   *
   * @param model the model to be adapted
   */
  public ModelAdapter(model.ReversiModel model) {
    super(model);
    this.model = model;
  }

  @Override
  public void placeToken(HexCoords coord) throws IllegalStateException, IllegalArgumentException {
    Hexagon hexagon = new AdaptHexCoordsToHexagon(coord).convertHexCoordsToHexagon();
    this.model.move(this.model.getCurrentPlayer(), hexagon.getQ(), hexagon.getR(), hexagon.getS());
  }

  @Override
  public void pass() throws IllegalStateException {
    this.model.pass();
  }

  @Override
  public void startGame() throws IllegalStateException {
    this.model.startGame();
  }

  @Override
  public void addMoveListener(ModelFeatures listener) {
    this.model.addMoveFeatures(listener);
  }


  /*
  private void notifyProviderListeners() {
    for (ModelFeatures features : providerListeners) {
      features.refreshAll();
      System.out.println(features.getColor());
      if (new AdaptTokenStatusToColor(features.getColor()).convertTokenStatusToColor() ==
              this.model.getCurrentPlayer()) {
        features.yourTurn();
      }
    }
  }
   */

}
