package Model;

import java.util.Objects;

/**
 * Represents a pointy top hexagon with Cube coordinates (i.e. q, r, s).
 */
public class Hexagon {
  private final int q;
  private final int r;
  private final int s;

  /**
   * Constructs a Model.Hexagon with Cube coordinates (i.e. q, r, s).
   *
   * @param q the q coordinate
   * @param r the r coordinate
   * @param s the s coordinate
   */
  public Hexagon(int q, int r, int s) {
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

  public Hexagon generate(int[] coordinates) {
    if (coordinates.length != 3) {
      throw new IllegalArgumentException(
              "Invalid generation parameters for incoming coordinates\n");
    }
    return new Hexagon(this.q + coordinates[0],
            this.r + coordinates[1], this.s + coordinates[2]);
  }

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
