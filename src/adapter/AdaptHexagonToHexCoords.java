package adapter;

import cs3500.reversi.provider.utils.HexCoords;
import model.Hexagon;

public class AdaptHexagonToHexCoords {
  private final Hexagon hexagon;

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
