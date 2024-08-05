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
      IModel.PlayerAverages avg = NetUtils.fetchSeasonAverages("3");


    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
