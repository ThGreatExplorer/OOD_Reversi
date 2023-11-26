package controller;

import model.Color;
import model.ReversiModel;
import player.Player;
import view.GUIView;

public class PlayerActionFeaturesImpl implements PlayerActionFeatures {
  private final ReversiModel model;
  private final GUIView view;
  private final Player player;
  private final Color playerColor;

  public PlayerActionFeaturesImpl(ReversiModel model, GUIView view, Player player) {
    this.model = model;
    this.view = view;
    this.player = player;
    this.playerColor = this.player.getColor();
  }

  @Override
  public void playMove(int q, int r, int s) {
    Color currentPlayer = model.getCurrentPlayer();
    if (playerColor != currentPlayer) {
      view.showErrorMessage("It is not your turn!");
      return;
    }

    try {
      model.move(currentPlayer, q, r, s);
      System.out.print("Move played: " + q + " " + r + " " + s + "\n");
    } catch (IllegalArgumentException e) {
      view.showErrorMessage(e.getMessage());
    }

  }

  @Override
  public void passMove() {
    Color currentPlayer = model.getCurrentPlayer();
    if (playerColor != currentPlayer) {
      view.showErrorMessage("It is not your turn!");
    }
    else {
      model.pass();
    }
  }
}
