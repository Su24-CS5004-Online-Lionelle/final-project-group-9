package Model;

import Model.SortFilter.ColumnData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;
import java.util.Set;

public interface IModel {
    /** Default database file. */
    String DATABASE = "data/database.json"; // need to figure out what to do about default file to read.

    /**
     * Gets the players as a list.
     *
     * @return the list of players.
     */
    Set<Player> getRoster();

    /**
     * Gets the set containing ALL current NBA players.
     *
     * @return Set containing Player objects.
     */
    Set<Player> getAllPlayers();

    /**
     * Gets file path in string.
     * @return String
     */
    String getFilePath();

    /**
     * Sets file path.
     * @param filePath
     */
    void setFilePath(String filePath);

    /**
     * Returns a string containing the player record passed in, in the format passed in.
     * @param player
     * @return
     */
    String toString(Player player);

    /**
     * Creates the master dates for all filtering, sorting, adding, and removing.
     */
    Set<Player> createNBARoster();

    /**
     * Takes a list of PlayerBean objects and converts it into a set of Player objects.
     * @param beanList
     * @return
     */
    Set<Player> beanToPlayer(List<PlayerBean> beanList);

    /**
     * Assumes results are sorted in ascending order, and by player name.
     * @param filter The filter to apply on the set of all players.
     * @return A set of players that match the filter.
     * @see #filterSortNBARoster(String, ColumnData, boolean)
     */
    Set<Player> filterSortNBARoster(String filter);

    /**
     * Filters players by the passed in text filter. Assumes results are sorted in ascending order.
     *
     * @param filter The filter to apply on the set of all players.
     * @param sortOn The charateristics/statistics of the players to sort on.
     * @return A set of players that match the filter.
     * @see #filterSortNBARoster(String, ColumnData, boolean)
     */
    Set<Player> filterSortNBARoster(String filter, ColumnData sortOn);


    /**
     * Builds a set by filtering/sorting the set that contains all current NBA players.
     *
     * A String filter can be the following options:
     *
     * > : greater than
     *
     * < : less than
     *
     * >= : greater than or equal to
     *
     * <= : less than or equal to
     *
     * == : equal to
     *
     * != : not equal to
     *
     * ~= : contains the text
     *
     * The left side of the filter describes the column to filter on.
     *
     * The right side of the filter describes the value to filter on.
     *
     * For example:
     *
     * >16.9
     *
     * would filter the Players to only players that average greater than 16.9 on the selected ColumnData in GUI.
     *
     *
     * it is possible to filter on the same data multiple times. For example:
     *
     * >2.1,<=11.6
     *
     * This would filter the players to those only who average greater than 2.1, and less than or equal to 11.6.
     *
     * Spaces are ignored, can be added for readability. For example:
     *
     * < 12
     *
     * is the same as
     *
     * <12
     *
     * If filtering on a player name, filter is case-insensitive, and accounts spaces. For example:
     *
     * ~= jayson tatum OR ~= JAYSON TATUM
     *
     * would filter the set to only players with jayson tatum in their name.
     *
     * NOTE: id/player_id is a special column that is not used for filtering or sorting.
     *
     * If the filter is empty (an empty string), then the set returned will contain ALL players currently
     * in the NBA in the defined direction, sorted based on the sortOn column.
     *
     *
     *
     * @param filter    The filter to apply on the players/teams.
     * @param sortOn    The statistic/characteristic to sort the results on.
     * @param ascending Whether to sort the results in ascending or descending order.
     * @return A set of players that match the filter.
     */
    Set<Player> filterSortNBARoster(String filter, ColumnData sortOn, boolean ascending);


    /**
     * Method takes in the filtered/sorted set and a name/index/index range and add to roster.
     *
     * Format for number range goes from smallest index to largest index. For example:
     *
     * 1-5
     *
     * NOTE: if string passed in is "all", all players will be added to the user's roster.
     *
     * @param filterSortedSet the filtered and sorted set that contains all players that fit the mold.
     * @param nameOrRange the name of the player, or the index number/range.
     */
    void buildRoster(Set<Player> filterSortedSet, String nameOrRange);

    /**
     * Removes players from user's roster. Built to handle names, indexes/index ranges, and keyword "all".
     *
     * NOTE: if keyword "all" is given, roster will be emptied.
     * @param nameOrRange
     */
     void removeFromRoster(String nameOrRange);

    /**
     * Creates a player object from 2 records.
     * @param background - the player background record.
     * @param seasonAverages - the player season averages record.
     * @return Player object.
     */
    Player createPlayer(PlayerBackground background, PlayerAverages seasonAverages);


    /**
     * Record to pass season averages to objects. Immutable, and uses Json annotations to serialize data.
     * @param pts
     * @param ast
     * @param turnover
     * @param pf
     * @param reb
     * @param stl
     * @param blk
     * @param fg_pct
     * @param fg3_pct
     * @param ft_pct
     * @param min
     * @param games_played
     * @param player_id
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonRootName("data")
    @JsonPropertyOrder({"pts", "ast", "turnover", "pf", "reb", "stl", "blk", "fg_pct", "fg3_pct",
            "ft_pct", "min", "games_played", "player_id"})
    record PlayerAverages(double pts, double ast, double turnover, double pf, double reb, double stl, double blk,
                  double fg_pct, double fg3_pct, double ft_pct, String min, double games_played, int player_id) {
    }
    record Team(int id, String conference, String division, String city, String name, String full_name,
                String abbreviation) {}

    /**
     * Record to pass player background to objects. Immutable, and uses Json annotations to serialize data.
     * @param id
     * @param first_name
     * @param last_name
     * @param position
     * @param height
     * @param draft_year
     * @param draft_round
     * @param draft_number
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonRootName("data")
    @JsonPropertyOrder({"id", "first_name", "last_name", "position",
        "height", "draft_year", "draft_round", "draft_number", "team"})
    record PlayerBackground(int id, String first_name, String last_name, String position, String height,
                            int draft_year, int draft_round, int draft_number, Team team) {

    }
}
