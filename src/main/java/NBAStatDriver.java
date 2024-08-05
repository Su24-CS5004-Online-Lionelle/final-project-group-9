import Model.IModel;
import View.IView;
import View.JFrameView;
import Model.*;

import java.util.Set;

public class NBAStatDriver {

    public static void main(String[] args) {
        // IView view =  new JFrameView("NBA Stat Tracker");

        // view.start();
        IModel model = new Model();
        Set<Player> testSet = model.getAllPlayers();
        for (Player player : testSet) {
            String idk = player.getFirstName() + player.getLastName();
            System.out.println(idk + "\n");
        }
    }


}
