package controller;

import model.Color;
import model.ReversiModel;
import player.Player;
import view.GUIView;

public class ModelFeaturesImpl implements ModelFeatures {

  private final ReversiModel model;
  private final GUIView view;
  private final Player player; //actual implementation is for only HumanPlayers, since the AIPlayer
  //does not need or communicate with a GUIView
  private final Color playerColor;

  public ModelFeaturesImpl(ReversiModel model, GUIView view, Player player) {
    this.model = model;
    this.view = view;
    this.player = player;
    this.playerColor = this.player.getColor();
  }

  @Override
  public void update() {
    this.view.update();
  }

  @Override
  public Color getcurrentPlayerColor() {
    return player.getColor();
  }

  @Override
  public void makeMove() {
    Color currentPlayer = model.getCurrentPlayer();
    if (playerColor != currentPlayer) {
      view.showErrorMessage("It is not your turn!");
      return;
    }

    int[] moveCoords;
    moveCoords = player.makeMove();

    if (moveCoords == null) {
      this.model.pass();
    }
    else {
      this.model.move(playerColor, moveCoords[0], moveCoords[1], moveCoords[2]);
    }
  }
}
