package cs3500.reversi.provider.controller;


/**
 * The controller for a game of reversi. Deals with the distribution of information between the
 * view, model, and players.
 */
public interface IController extends ModelFeatures, PlayerFeatures {

  //Purposely left empty because no group should be able to specifically call the controller,
  //instead they should call the methods above.
}
