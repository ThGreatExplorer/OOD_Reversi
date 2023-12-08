package adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs3500.reversi.provider.board.HexReversiBoard;
import cs3500.reversi.provider.controller.ModelFeatures;
import cs3500.reversi.provider.model.ReversiModel;
import cs3500.reversi.provider.model.ReversiROM;
import cs3500.reversi.provider.utils.HexCoords;
import cs3500.reversi.provider.utils.TokenStatus;
import model.BoardAdapter;
import model.Color;
import model.Hexagon;
import model.ReadOnlyReversiModel;
import model.StandardHexagonalReversiModel;

/**
 * Represents an adapter for the read only model to the providers ReversiModel interface.
 */
public class ReadOnlyModelAdapter implements ReversiROM {

  private final ReadOnlyReversiModel model;

  /**
   * Constructs a new ReadOnlyModelAdapter.
   *
   * @param model the model to be adapted
   */
  public ReadOnlyModelAdapter(ReadOnlyReversiModel model) {
    this.model = model;
  }

  @Override
  public ReversiModel copy() {
    return new ModelAdapter(new StandardHexagonalReversiModel(this.model));
  }

  @Override
  public TokenStatus getTokenAt(HexCoords c) throws IllegalArgumentException {
    Hexagon hexagon = new AdaptHexCoordsToHexagon(c).convertHexCoordsToHexagon();
    Color color = this.model.getColorAt(hexagon.getQ(), hexagon.getR(), hexagon.getS());
    return new AdaptColorToTokenStatus(color).convertColorToTokenStatus();
  }

  @Override
  public int getSideLength() throws IllegalStateException {
    return this.model.getSize() + 1;
  }

  @Override
  public int getScoreBlack() {
    return this.model.getScore(Color.BLACK);
  }

  @Override
  public int getScoreWhite() {
    return this.model.getScore(Color.WHITE);
  }

  @Override
  public boolean isGameOver() {
    return this.model.isGameOver();
  }

  @Override
  public HexReversiBoard getBoard() {
    return new BoardAdapter(this.model.getCurrentBoardState());
  }

  @Override
  public boolean isMoveLegal(HexCoords hc) throws IllegalStateException, IllegalArgumentException {
    Hexagon hexagon = new AdaptHexCoordsToHexagon(hc).convertHexCoordsToHexagon();
    return this.model.isValidMove(this.model.getCurrentPlayer(), hexagon.getQ(), hexagon.getR(),
            hexagon.getS());
  }

  @Override
  public List<HexCoords> getPossibleMoves() {
    Map<Hexagon, Integer> moveScores = this.model.getValidMoveScores(this.model.getCurrentPlayer());
    List<HexCoords> hexCoordsList = new ArrayList<>();
    for (Hexagon hexagon : moveScores.keySet()) {
      hexCoordsList.add(new AdaptHexagonToHexCoords(hexagon).convertHexagonToHexCoords());
    }
    return hexCoordsList;
  }

  @Override
  public int valueOfMove(HexCoords hc) throws IllegalStateException, IllegalArgumentException {
    Map<Hexagon, Integer> moveScores = this.model.getValidMoveScores(this.model.getCurrentPlayer());
    Hexagon hexagon = new AdaptHexCoordsToHexagon(hc).convertHexCoordsToHexagon();
    if (!moveScores.containsKey(hexagon)) {
      throw new IllegalArgumentException("Invalid move");
    }
    return moveScores.get(hexagon);
  }

  @Override
  public TokenStatus whoseTurn() throws IllegalStateException {
    return new AdaptColorToTokenStatus(this.model.getCurrentPlayer()).convertColorToTokenStatus();
  }


  @Override
  public void addMoveListener(ModelFeatures listener) {
    //does nothing
  }
}
