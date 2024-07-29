import View.IView;
import View.JFrameView;

public class NBAStatDriver {

    public static void main(String[] args) {
        IView view =  new JFrameView("NBA Stat Tracker");

        view.start();
    }


}
