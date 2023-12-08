package adapter;

import java.util.ArrayList;
import java.util.List;

import controller.ModelObserverFeatures;
import cs3500.reversi.provider.controller.ModelFeatures;
import cs3500.reversi.provider.model.ReversiModel;
import cs3500.reversi.provider.utils.HexCoords;
import model.AdaptHexCoordsToHexagon;
import model.AdaptTokenStatusToColor;
import model.Hexagon;

public class ModelAdapter extends ReadOnlyModelAdapter implements ReversiModel {

  private final model.ReversiModel model;
  //private final List<ModelFeatures> providerListeners;

  public ModelAdapter(model.ReversiModel model) {
    super(model);
    this.model = model;
    //this.providerListeners = new ArrayList<>();
  }

  @Override
  public void placeToken(HexCoords coord) throws IllegalStateException, IllegalArgumentException {
    Hexagon hexagon = new AdaptHexCoordsToHexagon(coord).convertHexCoordsToHexagon();
    this.model.move(this.model.getCurrentPlayer(), hexagon.getQ(), hexagon.getR(), hexagon.getS());
    //this.notifyProviderListeners();
  }

  @Override
  public void pass() throws IllegalStateException {
    this.model.pass();
    //this.notifyProviderListeners();
  }

  @Override
  public void startGame() throws IllegalStateException {
    this.model.startGame();

    //this.notifyProviderListeners();
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
