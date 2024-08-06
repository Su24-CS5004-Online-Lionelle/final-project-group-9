import Model.Format.DataFormatter;
import Model.Format.Format;
import Model.IModel;
import Model.Net.NetUtils;
import View.IView;
import View.JFrameView;
import Model.*;
import Controller.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class NBAStatDriver {

  public static void main(String[] args) {

    IModel model = new Model();

    IView view =  new JFrameView("NBA Stat Tracker");

    Controller controller = new Controller(view, model);

    view.start();

  }
}
