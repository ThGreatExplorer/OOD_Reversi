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
  private final ReversiModel model;
  private final GUIView view;
  private final PlayerActionFeatures playerActionFeatures; //delegate to the viewFeatures
  private final ModelObserverFeatures modelObserverFeatures; //delegate to the modelFeatures
  private final Player player;

  /**
   * Constructs a controller for a game of Reversi, given a model, view, and player.
   * Initializes the constructor as a listener for the ViewFeatures, ModelFeatures,
   * and PlayerActions.
   *
   * @param model the model of the game
   * @param view the view of the game
   * @param player one of the players of the game
   */
  public Controller(ReversiModel model, GUIView view, Player player) {
    this.model = model;
    this.view = view;
    this.player = player;
    this.playerActionFeatures = new PlayerActionFeaturesImpl(model, view, player);
    this.modelObserverFeatures = new ModelObserverFeaturesImpl(model, view, player);
    this.view.addPlayerActionFeatures(this.playerActionFeatures);
    this.model.addModelFeatures(this.modelObserverFeatures);
  }

}
