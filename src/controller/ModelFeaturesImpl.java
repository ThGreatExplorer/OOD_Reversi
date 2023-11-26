package controller;

import model.ReversiModel;
import player.Player;
import view.GUIView;

public class ModelFeaturesImpl implements ModelFeatures {

  private final ReversiModel model;
  private final GUIView view;
  private final Player player; //actual implementation is for only HumanPlayers, since the AIPlayer
  //does not need or communicate with a GUIView

  public ModelFeaturesImpl(ReversiModel model, GUIView view, Player player) {
    this.model = model;
    this.view = view;
    this.player = player;
  }

  @Override
  public void update() {
    this.view.update();
  }
}
