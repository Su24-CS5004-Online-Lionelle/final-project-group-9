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
            return direction ? new AgeAscending() : new AgeDescending();
        case POSITION:
          return direction ? new NameAscending() : new NameDescending();
        case HEIGHT:
          return direction ? new NameAscending() : new NameDescending();
        case DRAFTYEAR:
          return direction ? new NameAscending() : new NameDescending();
        case DRAFTROUND:
          return direction ? new NameAscending() : new NameDescending();
        case DRAFTPICK:
          return direction ? new NameAscending() : new NameDescending();
        case TEAM:
          return direction ? new NameAscending() : new NameDescending();
        case CONFERENCE:
          return direction ? new NameAscending() : new NameDescending();
        case PPG:
          return direction ? new NameAscending() : new NameDescending();
        case RPG:
          return direction ? new NameAscending() : new NameDescending();
        case APG:
          return direction ? new NameAscending() : new NameDescending();
        case BPG:
          return direction ? new NameAscending() : new NameDescending();
        case SPG:
          return direction ? new NameAscending() : new NameDescending();
        case TPG:
          return direction ? new NameAscending() : new NameDescending();
        case MPG:
          return direction ? new NameAscending() : new NameDescending();
        case FGP:
          return direction ? new NameAscending() : new NameDescending();
        case FTP:
          return direction ? new NameAscending() : new NameDescending();
        case FP3P:
          return direction ? new NameAscending() : new NameDescending();
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

  public static class AgeAscending implements Comparator<PlayerBean> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(PlayerBean o1, PlayerBean o2) {
      return o1.getAge() - (o2.getAge());
    }
  }

  public static class AgeDescending implements Comparator<PlayerBean> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(PlayerBean o1, PlayerBean o2) {
      return o2.getAge() - (o1.getAge());
    }
  }

  public static class PositionAscending implements Comparator<PlayerBean> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(PlayerBean o1, PlayerBean o2) {
      return o1.getPosition().compareToIgnoreCase(o2.getPosition());
    }
  }

  public static class PositionDescending implements Comparator<PlayerBean> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(PlayerBean o1, PlayerBean o2) {
      return o2.getPosition().compareToIgnoreCase(o1.getPosition());
    }
  }

  public static class HeightAscending implements Comparator<PlayerBean> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(PlayerBean o1, PlayerBean o2) {
      return o1.getHeight().compareToIgnoreCase(o2.getHeight());
    }
  }

  public static class HeightDescending implements Comparator<PlayerBean> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(PlayerBean o1, PlayerBean o2) {
      return o2.getHeight().compareToIgnoreCase(o1.getHeight());
    }
  }
}
