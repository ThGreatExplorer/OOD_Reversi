package player;

import model.Color;
import model.ReversiModel;

public class HumanPlayer implements Player {
  private final Color color;
  private final ReversiModel model;

  public HumanPlayer(Color color, ReversiModel model) {
    this.color = color;
    this.model = model;
  }

  @Override
  public Color getColor() {
    return this.color;
  }


  @Override
  public int[] makeMove() {
    //in practice this method will always be called by the ViewFeaturesImpl class
    return null;
  }
}
