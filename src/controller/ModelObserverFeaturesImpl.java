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
    //Update the view
    this.view.update(player);

    //TODO, slow AI player down
    //TODO, what to do when the game is over!
    /*
    try {
      // Pause for 1000 milliseconds (1 second)
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // Handle the interruption
      e.printStackTrace();
    }
     */

    try {
      //If an AI Player, make a move.
      int[] moveCoords = player.makeMove();

      if (moveCoords == null) {
        this.model.pass();
      }
      else {
        this.model.move(playerColor, moveCoords[0], moveCoords[1], moveCoords[2]);
      }
    } catch (IllegalArgumentException ignored) {
      //If not an AI Player do nothing
    }
  }

  @Override
  public Color getcurrentPlayerColor() {
    return player.getColor();
  }

}
