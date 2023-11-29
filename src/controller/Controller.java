package controller;

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

}
