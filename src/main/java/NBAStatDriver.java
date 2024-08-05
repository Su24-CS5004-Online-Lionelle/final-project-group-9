import Model.Format.DataFormatter;
import Model.Format.Format;
import Model.IModel;
import Model.Net.NetUtils;
import View.IView;
import View.JFrameView;
import Model.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NBAStatDriver {

  public static void main(String[] args) {
    // IView view =  new JFrameView("NBA Stat Tracker");

    // view.start()
    try {
      IModel model = new Model();
      File file = new File("data/database.json");

      IModel.PlayerAverages avg1 = NetUtils.fetchSeasonAverages("15");
      IModel.PlayerBackground bg1 = NetUtils.getAPlayer("giannis", "antetokounmpo");
      Player newPlayer1 = model.createPlayer(bg1, avg1);

      IModel.PlayerAverages avg2 = NetUtils.fetchSeasonAverages("18");
      IModel.PlayerBackground bg2 = NetUtils.getAPlayer("og", "anunoby");
      Player newPlayer2 = model.createPlayer(bg2, avg2);

      IModel.PlayerAverages avg3 = NetUtils.fetchSeasonAverages("4");
      IModel.PlayerBackground bg3 = NetUtils.getAPlayer("bam", "adebayo");
      Player newPlayer3 = model.createPlayer(bg3, avg3);

      IModel.PlayerAverages avg4 = NetUtils.fetchSeasonAverages("8");
      IModel.PlayerBackground bg4 = NetUtils.getAPlayer("grayson", "allen");
      Player newPlayer4 = model.createPlayer(bg4, avg4);

      IModel.PlayerAverages avg5 = NetUtils.fetchSeasonAverages("9");
      IModel.PlayerBackground bg5 = NetUtils.getAPlayer("jarrett", "allen");
      Player newPlayer5 = model.createPlayer(bg5, avg5);

      IModel.PlayerAverages avg6 = NetUtils.fetchSeasonAverages("12");
      IModel.PlayerBackground bg6 = NetUtils.getAPlayer("kyle", "anderson");
      Player newPlayer6 = model.createPlayer(bg6, avg6);

      List<Player> testList = new ArrayList<>();
      testList.add(newPlayer1);
      testList.add(newPlayer2);
      testList.add(newPlayer3);
      testList.add(newPlayer4);
      testList.add(newPlayer5);
      testList.add(newPlayer6);
      // System.out.println(newPlayer.toString());
      DataFormatter.write(testList, Format.JSON, new FileOutputStream(file));


    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
