package Model;

import Player.Players;

public interface ReversiModel {

  Players getCurrentPlayer();

  PlayingBoard getCurrentBoardState();
}
