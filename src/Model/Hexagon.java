package Model;

import java.util.Objects;

/**
 * Represents a pointy top hexagon with Cube coordinates (i.e. q, r, s).
 */
public class Hexagon {

  //negative q is at bottom left, positive q is top right
  private final int q;

  //negative r is up, positive r is down
  private final int r;

  //negative s is bottom right, positive s is top left
  private final int s;

  /**
   * Constructs a Model.Hexagon with Cube coordinate system for hexagons (i.e. q, r, s).
   *
   * @param q the q coordinate
   * @param r the r coordinate
   * @param s the s coordinate
   */
  Hexagon(int q, int r, int s) {
    this.q = q;
    this.r = r;
    this.s = s;
  }

  /**
   * Returns the q coordinate of this Model.Hexagon.
   *
   * @return the q coordinate of this Model.Hexagon
   */
  public int getQ() {
    return q;
  }

  /**
   * @return the r coordinate of this Model.Hexagon.
   */
  public int getR() {
    return r;
  }

  /**
   * @return the s coordinate of this Model.Hexagon.
   */
  public int getS() {
    return s;
  }

  /**
   * Two hexagons are equal if they are in the same spot.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Hexagon)) {
      return false;
    }
    else {
      Hexagon other = (Hexagon) o;
      return (this.q == other.getQ() && this.r == other.getR() && this.s == other.getS());
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.q, this.r, this.s);
  }

}
