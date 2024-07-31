package Model;
import Model.Format.Format;
import Model.SortFilter.ColumnData;
import Model.SortFilter.Operations;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import Model.SortFilter.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static Model.SortFilter.Filters.getFilter;
import static Model.SortFilter.PlayerSort.sortPlayers;

public class Model implements IModel {
    /**
     * String containing the file path to read from.
     */
    private String filePath;

    /**
     * Set containing player objects picked by the user.
     */
    private Set<Player> roster;

    /**
     * Set containing ALL player objects in the 2023-2024 NBA season.
     */
    private Set<Player> NBAROSTER = new LinkedHashSet<Player>();

    /**
     * Constructor with default filepath.
     * Means that user didn't have a previously built roster, so make a new HashSet.
     */
    public Model() {
        this.filePath = DATABASE;
        this.roster = new LinkedHashSet<Player>();
        this.NBAROSTER = getAllPlayers();
    }

    /**
     * Overloaded constructor with different filepath.
     * Means that the user passed in a file containing a roster of players they want to start with.
     */
    public Model(String filePath) {
        this.filePath = filePath;
        this.NBAROSTER = getAllPlayers();

        // set roster to a set of players found in the data file passed in by user.
        // start by creating xml mapper to serialize data into roster.
        ObjectMapper mapper = new XmlMapper();
        try {
            List<PlayerBean>  beanList = mapper.readValue(new File(filePath), new TypeReference<List<PlayerBean>>() { });
            this.roster = new LinkedHashSet<Player>(beanToPlayer(beanList));
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets a set of Player objects.
     *
     * @return the list of players.
     */
    @Override
    public Set<Player> getRoster() {
        return roster;
    }

    /**
     * Gets the set containing all current NBA players.
     *
     * @return Set containing Player objects.
     */
    @Override
    public Set<Player> getAllPlayers() {
        ObjectMapper mapper = new XmlMapper();
        try {
            List<PlayerBean> beanList = mapper.readValue(new File(DATABASE), new TypeReference<List<PlayerBean>>() { });
            NBAROSTER = beanToPlayer(beanList);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return NBAROSTER;
    }

    /**
     * Looks up to see if player is in database. If player in database, return player object.
     * If player is not in the list, serialize player info via BALLDONTLIE api, add to list, and return new player.
     *
     * @param playerName
     * @return player
     */
    @Override
    public Player getPlayer(String playerName) {
        try {
            Player player = null;
            boolean found = false;
            for (Player exisitingPlayer : roster) {
                if (exisitingPlayer.getName().equalsIgnoreCase(playerName)) {
                    found = true;
                    player = exisitingPlayer;
                    return player;
                }
            }
            // if player record doesn't exist, need to get info and build the record, then return it.
            if (found = false) {
                player = createPlayer(playerName);
                roster.add(player);
                write(roster, Format.XML, new FileOutputStream(filePath)); // need to implement and import write from dataformatter
            }
            return player;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets file path in string.
     *
     * @return String
     */
    @Override
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Sets file path.
     *
     * @param filePath
     */
    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a string containing the player record passed in.
     *
     * @param player
     * @return
     */
    @Override
    public String toString(Player player) {
        return String.format(
                """
                        Name: %s\n
                        Age: %d\n
                        Position: %s\n 
                        Height: %s\n 
                        Draft Year: %d\n 
                        Draft Round: %d\n 
                        Draft Pick: %d\n
                        Team: %s\n
                        Conference: %s\n 
                        Points per game: %.3f\n 
                        Rebounds per game: %.3f\n 
                        Assists per game: %.3f\n 
                        Blocks per game: %.3f\n 
                        Steals per game: %.3f\n 
                        Minutes per game: %.3f\n 
                        Field goal percentage per game: %.3f\n 
                        Free throw percentage per game: %.3f\n 
                        Three point percentage per game: %.3f""",
                player.getName(), player.getAge(), player.getPosition(), player.getHeight(), player.getDraftYear(),
                player.getDraftRound(), player.getDraftPick(), player.getTeam(), player.getConference(),
                player.getPpg(), player.getRpg(), player.getApg(), player.getBpg(), player.getSpg(), player.getMpg(),
                player.getFgp(), player.getFtp(), player.getFg3p());
    }

    /**
     * Creates a new record Player object, and adds it to roster.
     *
     * @param playerName
     * @return player
     */
    @Override
    public Player createPlayer(String playerName) {
        return null; // NEED NETUTILS FIRST.
    }

    public Set<Player> beanToPlayer(List<PlayerBean> beanList) {
        Set<Player> s = new LinkedHashSet<Player>();
        for (PlayerBean bean : beanList) {
            Player player = new Player(bean.getName(), bean.getAge(), bean.getPosition(), bean.getHeight(),
                    bean.getDraftYear(), bean.getDraftRound(), bean.getDraftPick(), bean.getTeam(),
                    bean.getConference(), bean.getPpg(), bean.getRpg(), bean.getApg(), bean.getBpg(),
                    bean.getSpg(), bean.getMpg(), bean.getFgp(), bean.getFtp(), bean.getFg3p());
            s.add(player);
        }
        return s;
    }

    /**
     * Assumes results are sorted in ascending order, and by player name.
     * @param filter The filter to apply on the set of all players.
     * @return A set of players that match the filter.
     * @see #filter(String, ColumnData, boolean)
     */
    public Set<Player> buildRoster(String filter) {
        return buildRoster(filter, ColumnData.NAME);
    }

    /**
     * Filters players by the passed in text filter. Assumes results are sorted in ascending order.
     *
     * @param filter The filter to apply on the set of all players.
     * @param sortOn The charateristics/statistics of the players to sort on.
     * @return A set of players that match the filter.
     * @see #filter(String, ColumnData, boolean)
     */
    public Set<Player> buildRoster(String filter, ColumnData sortOn) {
        return buildRoster(filter, sortOn, true);
    }

    /**
     * Builds roster by filtering/sorting the set that contains all current NBA players.
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
    public Set<Player> buildRoster(String filter, ColumnData sortOn, boolean ascending) {
        // remove spaces.
        filter = filter.replace(" ", "");

        // initialize a set to hold the results.
        Set<Player> result = new LinkedHashSet<Player>();

        // when user passes in empty string as filter.
        if (filter == "") {
            NBAROSTER = sortPlayers(NBAROSTER, sortOn, ascending); // follow up with yana on this method.
            return NBAROSTER;
        }

        // break down the filter String
        String[] separatedFilters = filter.split(",");

        // iterate through the elements of the string array, each element is one filter.
        for (int i = 0; i < separatedFilters.length; i++) {
            Operations operator = Operations.getOperatorFromStr(separatedFilters[i]);

            // save a string version of operator to split string
            String delimiter = operator.getOperator();
            String[] singleFilter = separatedFilters[i].split(delimiter);

            // define the value that is used for filtering.
            String value = singleFilter[1];

            // use filter methods to collect and save to result. STOPPED HERE.
            NBAROSTER.stream().getFilter(
                    Player -> getFilter(Player, sortOn, operator, value)).collect(Collectors.toSet());
        }
    }
}
