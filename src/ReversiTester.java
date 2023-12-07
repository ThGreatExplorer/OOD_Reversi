import adapter.AIPlayerAdapter;
import adapter.ModelAdapter;
import controller.Controller;
import cs3500.reversi.provider.strategy.GetHighestScore;
import cs3500.reversi.provider.utils.TokenStatus;
import cs3500.reversi.provider.view.RevGUI;
import model.ReversiModel;
import model.StandardHexagonalReversiModel;

public class ReversiTester {

  public static void main(String[] args) {
    ReversiModel model = new StandardHexagonalReversiModel(2);

    cs3500.reversi.provider.model.ReversiModel providerModel = new ModelAdapter(model);

    RevGUI view = new cs3500.reversi.provider.view.ReversiGraphicsView(1000,1000,
            providerModel);

    AIPlayerAdapter providerAI = new AIPlayerAdapter(new GetHighestScore());

    Controller providerController = new Controller(TokenStatus.BLACK, providerModel, view,
            providerAI);
  }
}
