package cs3500.reversi.provider.utils;

import java.util.Objects;

/**
 * Represents the coordinate system for our game.
 * Refer to the AxialCoords.jpeg to see a visual of this system.
 */
public final class HexCoords {
  public final int q;
  public final int r;

  /**
   * Default constructor for a HexCoord.
   * @param q the q axis of the axial coordinate system
   * @param r the r axis of the axial coordinate system
   */
  public HexCoords(int q, int r) {
    this.r = r;
    this.q = q;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof HexCoords) {
      HexCoords object = (HexCoords) o;
      return object.q == this.q && object.r == r;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(q, r);
  }
}
