package model;

import cs3500.reversi.provider.utils.HexCoords;

public class AdaptHexCoordsToHexagon {
  private final HexCoords hexCoords;

  public AdaptHexCoordsToHexagon(HexCoords hexCoords) {
    this.hexCoords = hexCoords;
  }
  public Hexagon convertHexCoordsToHexagon() {
    return new Hexagon(hexCoords.q, hexCoords.r, -hexCoords.q - hexCoords.r);
  }
}
