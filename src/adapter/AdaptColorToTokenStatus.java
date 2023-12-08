package adapter;

import cs3500.reversi.provider.utils.TokenStatus;
import model.Color;

public class AdaptColorToTokenStatus {
  private final Color color;

  public AdaptColorToTokenStatus(Color color) {
    this.color = color;
  }

  /**
   * Converts a color to a token status.
   *
   * @return the token status of the color
   * @throws IllegalArgumentException if the color is empty
   */
  public TokenStatus convertColorToTokenStatus() throws IllegalArgumentException {
    if (color == Color.BLACK) {
      return TokenStatus.BLACK;
    } else if (color == Color.WHITE) {
      return TokenStatus.WHITE;
    } else if (color == null) {
      return TokenStatus.EMPTY;
    } else {
      throw new IllegalArgumentException("Can't convert " + color.toString() + " to token status");
    }
  }
}
