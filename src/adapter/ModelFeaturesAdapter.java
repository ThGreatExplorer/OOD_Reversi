package adapter;

import controller.ModelObserverFeatures;
import cs3500.reversi.provider.controller.ModelFeatures;
import cs3500.reversi.provider.utils.TokenStatus;

public class ModelFeaturesAdapter implements ModelFeatures, ModelObserverFeatures {
  private final ModelObserverFeatures modelFeatures;

  public ModelFeaturesAdapter(ModelObserverFeatures modelFeatures) {
    this.modelFeatures = modelFeatures;
  }

  @Override
  public void gameOver() {
    this.modelFeatures.;
  }

  @Override
  public void yourTurn() {

  }

  @Override
  public void refreshAll() {

  }

  @Override
  public TokenStatus getColor() {
    return null;
  }

  @Override
  public void assignColor(TokenStatus color) throws IllegalArgumentException,
          IllegalStateException {

  }

  //------------------ our implemenation ----------------------------------------
  @Override
  public void update() {
    this.modelFeatures.update();
  }
}
