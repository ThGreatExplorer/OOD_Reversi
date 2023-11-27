package controller;

import model.Color;
import model.ReversiModel;
import player.Player;
import view.GUIView;

public class ModelObserverFeaturesImpl implements ModelObserverFeatures {

  private final ReversiModel model;
  private final GUIView view;
  private final Player player;
  private final Color playerColor;

  public ModelObserverFeaturesImpl(ReversiModel model, GUIView view, Player player) {
    this.model = model;
    this.view = view;
    this.player = player;
    this.playerColor = this.player.getColor();
  }

  @Override
  public void update() {
    this.view.update(player);
  }

  @Override
  public Color getcurrentPlayerColor() {
    return player.getColor();
  }

  @Override
  public void makeMove() {
    int[] moveCoords = player.makeMove();

    if (moveCoords == null) {
      this.model.pass();
    }
    else {
      this.model.move(playerColor, moveCoords[0], moveCoords[1], moveCoords[2]);
    }
  }
}
