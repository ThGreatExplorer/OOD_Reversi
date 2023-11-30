package controller;

import model.ReversiModel;
import player.Player;
import view.GUIView;

/**
 * This is a mock of the controller for testing purposes.
 */
public class ControllerMock {
  PlayerActionFeatures playerActionFeatures;
  ModelObserverFeatures modelObserverFeatures;

  /**
   * Constructs a mock controller which delegates to a mock model observer and mock player action.
   *
   * @param model  the model of the game
   * @param view   the view of the game
   * @param player one of the players of the game
   */
  public ControllerMock(ReversiModel model, GUIView view, Player player, StringBuilder ap) {
    //delegate to the viewFeatures
    playerActionFeatures = new PlayerActionFeaturesMock(model, view, player,ap);
    //delegate to the modelFeatures
    modelObserverFeatures = new ModelObserverFeaturesMock(model, view, player, ap);
    view.addPlayerActionFeatures(playerActionFeatures);
    model.addModelFeatures(modelObserverFeatures);
  }

}
