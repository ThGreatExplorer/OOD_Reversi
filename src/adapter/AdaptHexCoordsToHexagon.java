package adapter;

import cs3500.reversi.provider.utils.HexCoords;
import model.Hexagon;

/**
 * Represents an adapter for the providers hex coordinates to our hexagon.
 */
public class AdaptHexCoordsToHexagon {
  private final HexCoords hexCoords;

  /**
   * Constructs an adapter for the providers hex coordinates to our hexagon.
   *
   * @param hexCoords the hex coordinates to be adapted
   */
  public AdaptHexCoordsToHexagon(HexCoords hexCoords) {
    this.hexCoords = hexCoords;
  }
  public Hexagon convertHexCoordsToHexagon() {
    return new Hexagon(hexCoords.q, hexCoords.r, -hexCoords.q - hexCoords.r);
  }
}
