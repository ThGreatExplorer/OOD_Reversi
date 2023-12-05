package model;

import cs3500.reversi.provider.utils.TokenStatus;

public class AdaptTokenStatusToColor {
  private final TokenStatus tokenStatus;

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
