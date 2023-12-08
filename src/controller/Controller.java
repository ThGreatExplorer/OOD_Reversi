package controller;

import adapter.ProviderModelFeaturesImpl;
import adapter.ProviderPlayerFeaturesImpl;
import cs3500.reversi.provider.controller.ModelFeatures;
import cs3500.reversi.provider.controller.PlayerFeatures;
import cs3500.reversi.provider.player.IPlayer;
import cs3500.reversi.provider.utils.TokenStatus;
import cs3500.reversi.provider.view.RevGUI;
import model.ReversiModel;
import player.Player;
import view.GUIView;

/**
 * Represents a controller a game of Reversi, responsible for communicating between the View and
 * Model. Each controller object handles the interactions of one player, managing which player
 * is moving and solely communicating to that player at every point in the Reversi game.
 */
public class Controller {

  /**
   * Constructs a controller for a game of Reversi, given a model, view, and player.
   * Initializes the constructor as a listener for the ViewFeatures, ModelFeatures,
   * and PlayerActions.
   *
   * @param model  the model of the game
   * @param view   the view of the game
   * @param player one of the players of the game
   */
  public Controller(ReversiModel model, GUIView view, Player player) {
    //delegate to the viewFeatures
    PlayerActionFeatures playerActionFeatures = new PlayerActionFeaturesImpl(model, view, player);
    //delegate to the modelFeatures
    ModelObserverFeatures modelObserverFeatures =
        new ModelObserverFeaturesImpl(model, view, player);
    view.addPlayerActionFeatures(playerActionFeatures);
    model.addModelFeatures(modelObserverFeatures);
  }


  public Controller(TokenStatus status, cs3500.reversi.provider.model.ReversiModel model,
                    RevGUI view, IPlayer player) {
    PlayerFeatures playerFeatures = new ProviderPlayerFeaturesImpl(view, model, status);
    ModelFeatures modelFeatures = new ProviderModelFeaturesImpl(view, model, player, status);

    player.assignController(playerFeatures);
    model.addMoveListener(modelFeatures);
    view.setCommandListener(playerFeatures);
  }

}
