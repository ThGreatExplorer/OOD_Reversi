package adapter;

import cs3500.reversi.provider.utils.TokenStatus;
import model.Color;

/**
 * Represents an adapter for the color to the providers TokenStatus enum.
 */
public class AdaptColorToTokenStatus {
  private final Color color;

  /**
   * Constructs an adapter for the color to the providers TokenStatus enum.
   *
   * @param color the color to be adapted
   */
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
    }
    else if (color == Color.WHITE) {
      return TokenStatus.WHITE;
    }
    else if (color == null) {
      return TokenStatus.EMPTY;
    }
    else {
      throw new IllegalArgumentException("Can't convert " + color.toString() + " to token status");
    }
  }
}
