package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import adapter.AdaptColorToTokenStatus;
import adapter.AdaptHexCoordsToHexagon;
import adapter.AdaptHexagonToHexCoords;
import adapter.AdaptTokenStatusToColor;
import cs3500.reversi.provider.board.HexReversiBoard;
import cs3500.reversi.provider.utils.HexCoords;
import cs3500.reversi.provider.utils.TokenStatus;

/**
 * Adapts our PlayingBoard to the provider's HexReversiBoard interface.
 */
public class BoardAdapter implements HexReversiBoard {

  // delegate to our Playing Board implementation
  private final PlayingBoard board;

  public BoardAdapter(PlayingBoard board) {
    this.board = board;
  }

  /**
   * Gets the color of a piece at the given HexCoords on the Board.
   *
   * @param hc the axial coordinate of the piece you would like to get.
   * @return the color or TokenStatus.EMPTY if the space is empty.
   * @throws IndexOutOfBoundsException if the coordinate is off the board.
   */

  @Override
  public TokenStatus getPieceAt(HexCoords hc) throws IndexOutOfBoundsException {
    Hexagon hexagon = new AdaptHexCoordsToHexagon(hc).convertHexCoordsToHexagon();
    if (this.board.isOccupiedTile(hexagon)) {
      Color color = this.board.whoOccupiesThisTile(hexagon);
      return new AdaptColorToTokenStatus(color).convertColorToTokenStatus();
    } else {
      return TokenStatus.EMPTY;
    }
  }

  @Override
  public int getSideLength() {
    return this.board.getSize() - 1;
  }

  @Override
  public void addToBoard(HexCoords hc, TokenStatus gc) throws IndexOutOfBoundsException {
    Color color = new AdaptTokenStatusToColor(gc).convertTokenStatusToColor();
    this.board.occupyTile(hc.q, hc.r, -hc.q - hc.r, color);
  }

  @Override
  public HexReversiBoard copyBoard() {
    return new BoardAdapter(this.board);
  }

  @Override
  public Set<HexCoords> getValidCoords() {
    Set<HexCoords> hexCoordsSet = new HashSet<>();
    List<Hexagon> hexagonList = this.board.getBoard();

    for (Hexagon hexagon : hexagonList) {
      hexCoordsSet.add(new AdaptHexagonToHexCoords(hexagon).convertHexagonToHexCoords());
    }
    return hexCoordsSet;
  }
}

