package cs3500.reversi.provider.utils;

/**
 * Vectors representing the change in r & q values that are required to traverse one unit
 * in each of the six allowable directions.
 */
public enum HexVectors {
  UPLEFT(0, -1),
  LEFT(-1, 0),
  DOWNLEFT(-1, 1),
  DOWNRIGHT(0, 1),
  RIGHT(1, 0),
  UPRIGHT(1, -1);

  public final int qChange;
  public final int rChange;

  /**
   * Default constructor for a HexVector.
   * @param qChange the change in the q axis
   * @param rChange the change in the r axis
   */
  HexVectors(int qChange, int rChange) {
    this.qChange = qChange;
    this.rChange = rChange;
  }
}