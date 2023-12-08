import adapter.AIPlayerAdapter;
import adapter.ModelAdapter;
import controller.Controller;
import cs3500.reversi.provider.strategy.GetHighestScore;
import cs3500.reversi.provider.utils.TokenStatus;
import cs3500.reversi.provider.view.RevGUI;
import model.Color;
import model.ReversiModel;
import model.StandardHexagonalReversiModel;
import player.HumanPlayer;
import player.Player;
import view.GUIView;
import view.ReversiGraphicsView;

/**
 * This class is used to test the Reversi game using a preset configuration of our implementation
 * as color white and human and their implementation as color black and AI with the highest score
 * strategy.
 */
public class ReversiTester {

  public static void main(String[] args) {
    ReversiModel model = new StandardHexagonalReversiModel(6);

    //their provider setup
    //their model
    cs3500.reversi.provider.model.ReversiModel providerModel = new ModelAdapter(model);
    //their view
    RevGUI view = new cs3500.reversi.provider.view.ReversiGraphicsView(1000, 1000,
        providerModel);
    //their AIPlayer
    AIPlayerAdapter provider1 = new AIPlayerAdapter(new GetHighestScore());

    //OurView and human player
    GUIView ourView = new ReversiGraphicsView(model);
    Player humanPLayer = new HumanPlayer(Color.WHITE);

    Controller ourController = new Controller(model, ourView, humanPLayer);

    Controller providerController1 = new Controller(TokenStatus.BLACK, providerModel, view,
        provider1);

    ourView.setVisible();
    providerModel.startGame();
  }
}
