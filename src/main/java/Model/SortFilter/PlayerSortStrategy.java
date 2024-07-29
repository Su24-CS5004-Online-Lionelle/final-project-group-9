package Model.SortFilter;


import java.util.Comparator;
import Model.PlayerBean;

/**
 * PlayerSortStrategy holds comparator that storts each attribute in ascending or descending order.
 */
public class PlayerSortStrategy {

  /**
   * Private constructor.
   */
  private PlayerSortStrategy() {

  }

  public static Comparator<PlayerBean> getSort(ColumnData sortType) {
    return getSort(sortType, true);
  }

  public static Comparator<PlayerBean> getSort(ColumnData sortType, boolean direction) {
    switch (sortType) {
      case NAME:
        return direction ? new NameAscending() : new NameDescending();
      case AGE:
        // return direction ? new AgeAscending() : new AgeDescending();

        // Continue for each attribute
      default:
        return null;
    }
}
  public static class NameAscending implements Comparator<PlayerBean> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(PlayerBean o1, PlayerBean o2) {
      return o1.getName().compareToIgnoreCase(o2.getName());
    }
  }

  public static class NameDescending implements Comparator<PlayerBean> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(PlayerBean o1, PlayerBean o2) {
      return o2.getName().compareToIgnoreCase(o1.getName());
    }
  }
}
