package Model;

import java.util.Map;

import Player.Players;

/**
 * Reversi game model for standard hexagonal board following the normal rules of the game.
 */
public class StandardHexagonalReversi implements ReversiModel {
  private final PlayingBoard board;
  private Players currentPlayer;
  //private final RulesKeeper rulesKeeper;

  /**
   * Default constructor for a Standard Reversi game, starts with the player as white. Intializes
   * Board and Rules Keeper.
   *
   * @param boardSize the distance from center of board.
   */
  public StandardHexagonalReversi(int boardSize) {
    this.board = new StandardHexagonalBoard(boardSize);
    this.currentPlayer = Players.WHITE;
    //this.rulesKeeper = rulesKeeper;
  }

  //method to get current player
  @Override
  public Players getCurrentPlayer() {
    return this.currentPlayer;
  }

  //method to get the current Board()
  @Override
  public PlayingBoard getCurrentBoardState() {
    return new StandardHexagonalBoard(this.board);
  }

  //isGameOverMethod

  //Perform move that first calls isValidMove in RulesKeeper

  //helper method called switch player



}
