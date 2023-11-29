package Controller;

import controller.ModelObserverFeatures;
import controller.PlayerActionFeatures;
import model.ReversiModel;
import player.Player;
import view.GUIView;

/**
 * Constructor for a ControllerMock object.
 */
public class ControllerMock {

  public ControllerMock(ReversiModel model, GUIView view, Player player, StringBuilder ap) {
    //delegate to the viewFeatures
    PlayerActionFeatures playerActionFeatures =
            new PlayerActionFeaturesMock(model, view, player,ap);
    //delegate to the modelFeatures
    ModelObserverFeatures modelObserverFeatures =
            new ModelObserverFeaturesMock(model, view, player, ap);
    view.addPlayerActionFeatures(playerActionFeatures);
    model.addModelFeatures(modelObserverFeatures);
  }

}
