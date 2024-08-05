import Model.IModel;
import Model.Net.NetUtils;
import View.IView;
import View.JFrameView;
import Model.*;

import java.io.IOException;
import java.util.Set;

public class NBAStatDriver {

  public static void main(String[] args) {
    // IView view =  new JFrameView("NBA Stat Tracker");

    // view.start()
    try {
      IModel model = new Model();
      IModel.PlayerAverages avg = NetUtils.fetchSeasonAverages("15");
      IModel.PlayerBackground bg = NetUtils.getAPlayer("giannis", "antetokounmpo");
      Player newPlayer = model.createPlayer(bg, avg);
      System.out.println(newPlayer.toString());


    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
