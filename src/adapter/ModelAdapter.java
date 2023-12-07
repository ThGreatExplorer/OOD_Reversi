package adapter;

import cs3500.reversi.provider.controller.ModelFeatures;
import cs3500.reversi.provider.model.ReversiModel;
import cs3500.reversi.provider.utils.HexCoords;
import model.AdaptHexCoordsToHexagon;
import model.Hexagon;

public class ModelAdapter extends ReadOnlyModelAdapter implements ReversiModel {

  private final model.ReversiModel model;

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
    ModelFeatures listener = new ModelFeaturesAdapter(listener);
    this.model.addModelFeatures();
  }

}
