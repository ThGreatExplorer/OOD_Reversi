package model;

import java.util.ArrayList;
import java.util.List;
import adapter.AdaptTokenStatusToColor;
import controller.ModelObserverFeatures;
import cs3500.reversi.provider.controller.ModelFeatures;

public abstract class AReversiModel<T extends BoardTile> implements ReversiModel<T> {

  protected final APlayingBoard<T> board;
  protected Color currentPlayer;
  protected boolean flagPass;
  protected boolean isGameOver;
  protected final List<ModelObserverFeatures> featuresListeners = new ArrayList<>(); // the list of
  //observer features that are subscribed to this model

  protected final List<ModelFeatures> providerListeners = new ArrayList<>(); // the list of the
  //provider's listeners that are subscribed to this model

  protected abstract APlayingBoard<T> initPLayingBoard(int boardSize);
  protected abstract APlayingBoard<T> initPLayingBoard(APlayingBoard<T> board);

  public AReversiModel(int boardSize) {
    this.board = this.initPLayingBoard(boardSize);
    this.currentPlayer = Color.WHITE;
    this.flagPass = false;
    this.isGameOver = false;
  }


  public AReversiModel(APlayingBoard<T> board) {
    this.board = this.initPLayingBoard(board);
    this.currentPlayer = Color.WHITE;
  }


  public AReversiModel(ReadOnlyReversiModel<T> model) {
    this.board = this.initPLayingBoard(model.getCurrentBoardState());
    this.currentPlayer = model.getCurrentPlayer();
    this.flagPass = model.getFlagPass();
    this.isGameOver = model.isGameOver();
  }

  @Override
  public int getScore(Color color) {
    int countScore = 0;

    //go through the map of all the occupied hexagons and count how many are of the same color
    for (Color discColor : board.getOccupiedTiles().values()) {
      if (discColor.equals(color)) {
        countScore++;
      }
    }

    return countScore;
  }

  @Override
  public Color getWinner() {
    if (!this.isGameOver) {
      throw new IllegalArgumentException("No winner, game is not over!");
    }
    if (this.getScore(this.currentPlayer) > this.getScore(this.currentPlayer.getNextColor())) {
      return this.currentPlayer;
    } else if (this.getScore(this.currentPlayer) ==
            this.getScore(this.currentPlayer.getNextColor())) {
      return null;
    } else {
      return this.currentPlayer.getNextColor();
    }
  }

  @Override
  public boolean isGameOver() throws IllegalArgumentException {
    //check if the game has ended already (either by passing twice or for each player being unable
    //to move or the board is filled
    if (this.isGameOver) {
      return true;
    }

    //check if both players must pass
    if (!this.canMakeAnyMove(this.currentPlayer)
            && !this.canMakeAnyMove(this.getCurrentPlayer().getNextColor())) {
      this.isGameOver = true;
      return true;
    }

    return false;
  }

  @Override
  public int getSize() {
    return this.board.getSize();
  }

  @Override
  public Color getCurrentPlayer() {
    return this.currentPlayer;
  }

  @Override
  public APlayingBoard<T> getCurrentBoardState() {
    return this.initPLayingBoard(this.board);
  }

  @Override
  public boolean getFlagPass() {
    return this.flagPass;
  }

  @Override
  public void addModelFeatures(ModelObserverFeatures modelFeatures) {
    featuresListeners.add(modelFeatures);
  }

  @Override
  public void addMoveFeatures(ModelFeatures providerFeatures) {
    this.providerListeners.add(providerFeatures);
  }

  @Override
  public void notifyMoveMade() {
    for (ModelObserverFeatures listener : featuresListeners) {
      listener.update();
    }
    this.notifyProviderListeners();
  }

  @Override
  public void startGame() {
    this.notifyMoveMade();
  }

  @Override
  public void pass() {
    if (this.isGameOver) {
      throw new IllegalArgumentException("Game is already over!");
    }
    if (this.flagPass) {
      this.isGameOver = true;
      this.switchPlayer();
    } else {
      this.flagPass = true;
      this.switchPlayer();
    }
    this.notifyMoveMade();
  }

  /**
   * Switches the current Color to the next Player in the ENUM Color by ordinal number. Calls
   * the next method in Color.
   */
  protected void switchPlayer() {
    this.currentPlayer = this.currentPlayer.getNextColor();
  }

  private void notifyProviderListeners() {
    for (ModelFeatures features : providerListeners) {
      features.refreshAll();
      System.out.println(features.getColor());
      if (new AdaptTokenStatusToColor(features.getColor()).convertTokenStatusToColor() ==
              this.getCurrentPlayer()) {
        features.yourTurn();
      }
    }
  }


}
