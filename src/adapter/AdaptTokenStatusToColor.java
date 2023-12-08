package adapter;

import cs3500.reversi.provider.utils.TokenStatus;
import model.Color;

/**
 * Represents an adapter for the providers token status to our color.
 */
public class AdaptTokenStatusToColor {
  private final TokenStatus tokenStatus;

  /**
   * Constructs an adapter for the providers token status to our color.
   *
   * @param tokenStatus the token status to be adapted
   */
  public AdaptTokenStatusToColor(TokenStatus tokenStatus) {
    this.tokenStatus = tokenStatus;
  }

  /**
   * Converts a token status to a color.
   *
   * @return the color of the token status
   * @throws IllegalArgumentException if the token status is empty
   */
  public Color convertTokenStatusToColor() throws IllegalArgumentException {
    if (tokenStatus == TokenStatus.BLACK) {
      return Color.BLACK;
    }
    else if (tokenStatus == TokenStatus.WHITE) {
      return Color.WHITE;
    }
    else {
      throw new IllegalArgumentException("Can't convert empty token status to color");
    }
  }

}
