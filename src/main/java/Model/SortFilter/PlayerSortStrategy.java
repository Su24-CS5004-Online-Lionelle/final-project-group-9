package Model.SortFilter;


import java.util.Comparator;
import Model.Player;

/**
 * PlayerSortStrategy holds customized comparator that sorts each attribute in ascending or descending order.
 */
public class PlayerSortStrategy {
  private static final int ZERO_INT = 0;

  /**
   * Private constructor.
   */
  private PlayerSortStrategy() {

  }


  /**
   * Sorting comparator method for each attribute.
   * @param sortType column data enum of player attribute
   * @return player comparator
   */
  public static Comparator<Player> getSort(ColumnData sortType) {
    return getSort(sortType, true);
  }

  /**
   * Sorting comparator method for each attribute.
   * @param sortType column data enum of player attribute
   * @param direction boolean direction of ascending or descending order
   * @return player comparator
   */
  public static Comparator<Player> getSort(ColumnData sortType,
                                           boolean direction) {
    switch (sortType) {
      case FIRST_NAME:
          return direction ? new FirstNameAscending() : new FirstNameDescending();
      case LAST_NAME:
        return direction ? new LastNameAscending() : new LastNameDescending();
        case POSITION:
          return direction? new PositionAscending() : new PositionDescending();
        case HEIGHT:
          return direction ? new HeightAscending() : new HeightDescending();
        case DRAFTYEAR:
          return direction ? new DraftYearAscending() : new DraftYearDescending();
        case DRAFTROUND:
          return direction ? new DraftRoundAscending() : new DraftRoundDescending();
        case DRAFTPICK:
          return direction ? new DraftPickAscending() : new DraftPickDescending();
        case TEAM:
          return direction ? new TeamAscending() : new TeamDescending();
        case CONFERENCE:
          return direction ? new ConferenceAscending() : new ConferenceDescending();
        case PPG:
          return direction ? new PpgAscending() : new PpgDescending();
        case RPG:
          return direction ? new RpgAscending() : new RpgDescending();
        case APG:
          return direction ? new ApgAscending() : new ApgDescending();
        case BPG:
          return direction ? new BpgAscending() : new BpgDescending();
        case SPG:
          return direction ? new SpgAscending() : new SpgDescending();
        case MPG:
          return direction ? new MpgAscending() : new MpgDescending();
        case FGP:
          return direction ? new FgpAscending() : new FgpDescending();
        case FTP:
          return direction ? new FtpAscending() : new FtpDescending();
        case FP3P:
          return direction ? new Fg3pAscending() : new Fg3pDescending();
        default:
          return null;
      }
}

  public static class FirstNameAscending implements Comparator<Player> {

      /**
       * Compares its two arguments for order.  Returns a negative integer,
       * zero, or a positive integer as the first argument is less than, equal
       * to, or greater than the second.<p>
       */
      @Override
      public int compare(Player o1, Player o2) {
        return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
      }
    }

  public static class FirstNameDescending implements Comparator<Player> {

      /**
       * Compares its two arguments for order.  Returns a negative integer,
       * zero, or a positive integer as the first argument is less than, equal
       * to, or greater than the second.<p>
       */
      @Override
      public int compare(Player o1, Player o2) {
        return o2.getFirstName().compareToIgnoreCase(o1.getFirstName());
      }
    }

  public static class LastNameAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o1.getLastName().compareToIgnoreCase(o2.getLastName());
    }
  }

  public static class LastNameDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o2.getLastName().compareToIgnoreCase(o1.getLastName());
    }
  }

  public static class PositionAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o1.getPosition().compareToIgnoreCase(o2.getPosition());
    }
  }

  public static class PositionDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o2.getPosition().compareToIgnoreCase(o1.getPosition());
    }
  }

  public static class HeightAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return getHeightInInches(o1) - getHeightInInches(o2);
    }
  }

  public static class HeightDescending implements Comparator<Player> {
    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return getHeightInInches(o2) - getHeightInInches(o1);
    }
  }

  /**
   * Converts height string from player.getHeight() to inches as an int
   * @param player object
   * @return integer of height as inches
   */
  private static int getHeightInInches(Player player) {
    // Convert height string to decimal
    String[] partsOne = player.getHeight().split("-");

    int playerOneFeet = ZERO_INT;
    if (!partsOne[0].isEmpty()) {
      playerOneFeet = Integer.valueOf(partsOne[0]) * 12;
    }
    int playerOneInches = ZERO_INT;
    if (!partsOne[1].isEmpty()) {
      playerOneInches = (Integer.valueOf(partsOne[1]));
    }

    // Compare feet
    try {
      return playerOneFeet + playerOneInches;
    } catch (IllegalArgumentException e){
      throw new IllegalArgumentException("Height could not be converted to double.");
    }
  }


  public static class DraftYearAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o1.getDraftYear() - (o2.getDraftYear());
    }
  }

  public static class DraftYearDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o2.getDraftYear() - (o1.getDraftYear());
    }
  }

  public static class DraftRoundAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o1.getDraftRound() - (o2.getDraftRound());
    }
  }

  public static class DraftRoundDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o2.getDraftRound() - (o1.getDraftRound());
    }
  }

  public static class DraftPickAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o1.getDraftPick() - (o2.getDraftPick());
    }
  }

  public static class DraftPickDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o2.getDraftPick() - (o1.getDraftPick());
    }
  }

  public static class TeamAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o1.getTeam().compareToIgnoreCase(o2.getTeam());
    }
  }

  public static class TeamDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o2.getTeam().compareToIgnoreCase(o1.getTeam());
    }
  }

  public static class ConferenceAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o1.getConference().compareToIgnoreCase(o2.getConference());
    }
  }

  public static class ConferenceDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o2.getConference().compareToIgnoreCase(o1.getConference());
    }
  }

  public static class PpgAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return  Double.compare(o1.getPpg(), o2.getPpg());
  }
  }

  public static class PpgDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return Double.compare(o2.getPpg(), o1.getPpg());
    }
  }

  public static class RpgAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return Double.compare(o1.getRpg(), o2.getRpg());
    }
  }

  public static class RpgDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return Double.compare(o2.getRpg(), o1.getRpg());
    }
  }

  public static class ApgAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return Double.compare(o1.getApg(), o2.getApg());
    }
  }

  public static class ApgDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return Double.compare(o2.getApg(), o1.getApg());
    }
  }

  public static class BpgAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return  Double.compare(o1.getBpg(), o2.getBpg());
    }
  }

  public static class BpgDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return  Double.compare(o2.getBpg(), o1.getBpg());
    }
  }

  public static class SpgAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return  Double.compare(o1.getSpg(), o2.getSpg());
    }
  }

  public static class SpgDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return  Double.compare(o2.getSpg(), o1.getSpg());
    }
  }

  public static class MpgAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o1.getMpg().compareToIgnoreCase(o2.getMpg());
    }
  }

  public static class MpgDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return o2.getMpg().compareToIgnoreCase(o1.getMpg());
    }
  }

  public static class FgpAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return Double.compare(o1.getFgp(), o2.getFgp());
    }
  }

  public static class FgpDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return Double.compare(o2.getFgp(), o1.getFgp());
    }
  }

  public static class FtpAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return Double.compare(o1.getFtp(), o2.getFtp());
    }
  }

  public static class FtpDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return Double.compare(o2.getFtp(), o1.getFtp());
    }
  }

  public static class Fg3pAscending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return Double.compare(o1.getFg3p(), o2.getFg3p());
    }
  }

  public static class Fg3pDescending implements Comparator<Player> {

    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     */
    @Override
    public int compare(Player o1, Player o2) {
      return Double.compare(o2.getFg3p(), o1.getFg3p());
    }
  }
  }

