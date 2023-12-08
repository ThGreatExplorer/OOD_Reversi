package adapter;

import cs3500.reversi.provider.utils.HexCoords;
import model.Hexagon;

public class AdaptHexCoordsToHexagon {
  private final HexCoords hexCoords;

  public AdaptHexCoordsToHexagon(HexCoords hexCoords) {
    this.hexCoords = hexCoords;
  }
  public Hexagon convertHexCoordsToHexagon() {
    return new Hexagon(hexCoords.q, hexCoords.r, -hexCoords.q - hexCoords.r);
  }
}
