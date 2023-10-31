package Model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a pointy top hexagon of Cube coordinates (i.e. q, r, s). Can be thought of as a
 * vector with 3 components: q,r, and s.
 */
public class Hexagon {

  /*
  Invariant: Any hexagon no matter what game state or position, must always satisfy the constraint
  q + r + s = 0.
   */

  //negative q is at bottom left, positive q is top right
  private final int q;

  //negative r is up, positive r is down
  private final int r;

  //negative s is bottom right, positive s is top left
  private final int s;

  /**
   * Represents the direction vectors to get the 6 nearest neighbors of this hexagon, representing
   * the + and - directions of q,r,s.
   */
  public static final int[][] CUBE_DIRECTION_VECTORS = {{-1, 0, +1}, {0, -1, +1}, {+1, -1, 0},
          {+1, 0, -1}, {0, +1, -1}, {-1, +1, 0}}; //from clockwise direction starting from left

  /**
   * Constructs a Model.Hexagon with Cube coordinate system for hexagons (i.e. q, r, s).
   *
   * @param q the q coordinate
   * @param r the r coordinate
   * @param s the s coordinate
   */
  Hexagon(int q, int r, int s) {
    if (q+r+s != 0) {
      throw new IllegalArgumentException("Invalid coordinates! Must satisfy q+r+s = 0 " + q + " "
              + r + " " + s);
    }
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
   * Checks if this hexagon lines on the same q,r,s lines as another hexagon (i.e. it's a valid
   * direction).
   *
   * @param other the hexagon to compare against
   * @return true or false based on if they are on the same line
   */
  public boolean sameLine(Hexagon other) {
    return this.getQ() == other.getQ() || this.getR() == other.getR()
            || this.getS() == other.getS();
  }

  /**
   * Gets the distance vector from this Hexagon to a given hexagon, preserving magnitude AND
   * direction.
   *
   * @param other the incoming Hexagon
   * @return the distance vector of size 3
   */
  public int[] distanceVector(Hexagon other) {
    int qDist = other.getQ() - this.getQ();
    int rDist = other.getR() - this.getR();
    int sDist = other.getS() - this.getS();
    return new int[]{qDist, rDist, sDist};
  }

  /**
   * Normalizes the distance vector between this hexagon and a given hexagon where a normalized
   * vector representing one step in a direction, must return a <b>CUBE_DIRECTION_VECTORS</b>.
   *
   * @param other the other hexagon
   * @return the integer vector representing the normalized magnitude to direction
   * @throws IllegalArgumentException if the two hexagons are not on the same line, since there is
   *     way to normalize
   */
  public int[] normalizedDistanceVector(Hexagon other) throws IllegalArgumentException {
    //they have to be on the same line to normalize
    if (!this.sameLine(other)) {
      throw new IllegalArgumentException("To normalize the distance the vectors have to be on " +
              "the same line " + this.getQ() + " " + this.getR() + " " + this.getS() + " | "
              + other.getQ() + " " + other.getR() + " " + other.getS());
    }
    int[] vector = distanceVector(other);
    return normalizeVector(vector);
  }

  private int[] normalizeVector(int[] vector) {
    // Get the maximum absolute value among the vector coordinates
    int maxAbsValue = Math.abs(vector[0]);
    for (int i = 1; i < vector.length; i++) {
      if (Math.abs(vector[i]) > maxAbsValue) {
        maxAbsValue = Math.abs(vector[i]);
      }
    }

    // If the maximum absolute value is 0, return the vector as is
    if (maxAbsValue == 0) return vector;

    // Normalize each coordinate of the vector
    for (int i = 0; i < vector.length; i++) {
      vector[i] = vector[i] / maxAbsValue;
    }

    return vector;
  }

  /**
   * Gets the distance of this hexagon from the origin. Conceptually can be though of as returning
   * the ring that this hexagon lies on.
   *
   * @return the int distance.
   */
  public int getDistance() {
    return Math.max(Math.abs(this.q), Math.max(Math.abs(this.r), Math.abs(this.s)));
  }

  /**
   * Returns a Hexagon relative to this hexagon's position and a given vector.
   *
   * @param vector the relative distance vector to this vector
   * @return the new Hexagon at the new position.
   */
  public Hexagon generateFromVector(int[] vector) {
    if (vector.length != 3) {
      throw new IllegalArgumentException(
              "Invalid generation parameters for incoming vector\n");
    }
    if (Arrays.stream(vector).sum() != 0) {
      throw new IllegalArgumentException("Illegal generation, q+r+s must be preserved "
              + this.getQ() + " " + this.getR() + " " + this.getS() + " | "
              + Arrays.toString(vector));
    }
    return new Hexagon(this.getQ() + vector[0],
            this.getR() + vector[1], this.getS() + vector[2]);
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
