package adapter;

import cs3500.reversi.provider.utils.HexCoords;
import model.Hexagon;

/**
 * Represents an adapter for the hexagon to the providers hex coordinates.
 */
public class AdaptHexagonToHexCoords {
  private final Hexagon hexagon;

  /**
   * Constructs an adapter for the hexagon to the providers hex coordinates.
   *
   * @param hexagon the hexagon to be adapted
   */
  public AdaptHexagonToHexCoords(Hexagon hexagon) {
    this.hexagon = hexagon;
  }

  /**
   * Converts a hexagon to hex coordinates.
   *
   * @return the hex coordinates of the hexagon
   */
  public HexCoords convertHexagonToHexCoords() {
    return new HexCoords(hexagon.getQ(), hexagon.getR());
  }
}
