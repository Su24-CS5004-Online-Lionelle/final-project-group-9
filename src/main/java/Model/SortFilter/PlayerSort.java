package Model.SortFilter;

import java.util.Set;
import Model.PlayerBean;

/**
 * PlayerSort class that generates set of sorted PlayerBeans based on sort type.
 */
public class PlayerSort {

  /**
   * Private constructor.
   */
  private PlayerSort() {

  }

/**
 * Sorts board games into a set of games in ascending order by default based on sort type of board game attribute.
 */
  public static Set<PlayerBean> sortPlayers(Set<PlayerBean> players, String sortType) {
    return null;
  }

  /**
   * Sorts board games into a set of games in specified order based on boolean and sort type of board game attribute.
   */
  public static Set<PlayerBean> sortBoardGames(Set<PlayerBean> games, String sortType, boolean direction) {
    return null;
  }
}
