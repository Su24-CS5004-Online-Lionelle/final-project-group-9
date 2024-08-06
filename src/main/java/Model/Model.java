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
import java.util.*;
import java.util.stream.Collectors;

import static Model.Net.NetUtils.fetchPlayers;
import static Model.Net.NetUtils.fetchSeasonAverages;
import static Model.Net.NetUtils.getAPlayer;
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

        // intialize NBAROSTER with createNBARoster.
        NBAROSTER = createNBARoster();
        this.NBAROSTER = NBAROSTER;
        // createNBARoster();
    }

    /**
     * Overloaded constructor with different filepath.
     * Means that the user passed in a file containing a roster of players they want to start with.
     */
    public Model(String filePath) {
        this.filePath = DATABASE;
        NBAROSTER = createNBARoster();
        this.NBAROSTER = NBAROSTER;

        // createNBARoster();

        // set roster to a set of players found in the data file passed in by user.
        // start by creating xml mapper to serialize data into roster.
        ObjectMapper mapper = new ObjectMapper();
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
        return NBAROSTER;
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
                        First Name: %s
                        
                        Last Name: %s
                        
                        Position: %s
                         
                        Height: %s
                         
                        Draft Year: %d
                         
                        Draft Round: %d
                         
                        Draft Pick: %d
                        
                        Team: %s
                        
                        Conference: %s
                         
                        Points per game: %.3f
                         
                        Rebounds per game: %.3f
                         
                        Assists per game: %.3f
                         
                        Blocks per game: %.3f
                         
                        Steals per game: %.3f
                         
                        Minutes per game: %.3f
                         
                        Field goal percentage per game: %.3f
                         
                        Free throw percentage per game: %.3f
                         
                        Three point percentage per game: %.3f""",
                player.getFirstName(), player.getLastName(), player.getPosition(), player.getHeight(), player.getDraftYear(),
                player.getDraftRound(), player.getDraftPick(), player.getTeam(), player.getConference(),
                player.getPpg(), player.getRpg(), player.getApg(), player.getBpg(), player.getSpg(), player.getMpg(),
                player.getFgp(), player.getFtp(), player.getFg3p());
    }

    /**
     * Creates the master database for all filtering, sorting, adding, and removing.
     */
    @Override
    public Set<Player> createNBARoster() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<PlayerBean>  beanList = mapper.readValue(new File(filePath), new TypeReference<List<PlayerBean>>() { });
            return new LinkedHashSet<Player>(beanToPlayer(beanList));
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Takes a list of PlayerBean objects and converts it into a set of Player objects.
     * @param beanList
     * @return
     */
    @Override
    public Set<Player> beanToPlayer(List<PlayerBean> beanList) {
        Set<Player> s = new LinkedHashSet<Player>();
        for (PlayerBean bean : beanList) {
            Player player = new Player(bean.getFirstName(), bean.getLastName(), bean.getPosition(), bean.getHeight(),
                    bean.getDraftYear(), bean.getDraftRound(), bean.getDraftPick(), bean.getTeam(),
                    bean.getConference(), bean.getPpg(), bean.getRpg(), bean.getApg(), bean.getBpg(),
                    bean.getSpg(), bean.getMpg(), bean.getFgp(), bean.getFtp(), bean.getFg3p());
            s.add(player);
        }
        return s;
    }

    /**
     * Assumes results are sorted in ascending order, and by player first name.
     * @param filter The filter to apply on the set of all players.
     * @return A set of players that match the filter.
     * @see #filterSortNBARoster(String, ColumnData, boolean)
     */
    @Override
    public Set<Player> filterSortNBARoster(String filter) {
        return filterSortNBARoster(filter, ColumnData.FIRST_NAME);
    }

    /**
     * Filters players by the passed in text filter. Assumes results are sorted in ascending order.
     *
     * @param filter The filter to apply on the set of all players.
     * @param sortOn The charateristics/statistics of the players to sort on.
     * @return A set of players that match the filter.
     * @see #filterSortNBARoster(String, ColumnData, boolean)
     */
    @Override
    public Set<Player> filterSortNBARoster(String filter, ColumnData sortOn) {
        return filterSortNBARoster(filter, sortOn, true);
    }

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
     * If filtering on a player first or last name, filter is case-insensitive, and ignores spaces. For example:
     *
     * ~= jayson OR ~= JAYSON
     *
     * would filter the set to only players with jayson in their first name if user searches by first name.
     *
     * == tatum OR == TATUM
     *
     * would filter the set to only players with tatum in their last name if user searches by last name.
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
    @Override
    public Set<Player> filterSortNBARoster(String filter, ColumnData sortOn, boolean ascending) {
        // remove spaces.
        filter = filter.replace(" ", "");

        // initialize a set to hold the results.
        Set<Player> result = new LinkedHashSet<Player>();

        // when user passes in empty string or all as filter.
        if (filter == "" || filter.equalsIgnoreCase("all")) {
            result = sortPlayers(NBAROSTER, sortOn.toString(), ascending);
            return result;
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

            // use filter methods to collect and save to result.
            result = NBAROSTER.stream().filter(
                    Player -> Filters.getFilter(Player, sortOn, operator, value)).collect(Collectors.toSet());

            // after filtering, sort the set based on user's input, then return the roster.
            result = sortPlayers(result, sortOn.toString(), ascending);
        }
        return result;
    }

    /**
     * Sets the user roster.
     * @param roster a set of players.
     */
    public void setRoster(Set<Player> roster) {
        this.roster = roster;
    }

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
    @Override
    public void buildRoster(Set<Player> filterSortedSet, String nameOrRange) {
        // start by checking for keyword all.
        if (nameOrRange.equalsIgnoreCase("all")) {
            setRoster(filterSortedSet);
            return; // end method.
        }
        // checking if string is a range, full name, or singular index by parsing.
        String[] range = nameOrRange.split("-");

        // if range's length is 2, then we know it's a range.
        if (range.length == 2) {
            try {
                int start = Integer.parseInt(range[0]) - 1; // -1 because index starts at 0.
                System.out.println(start);
                int end = Integer.parseInt(range[1]) - 1;
                System.out.println(end);
                System.out.println(filterSortedSet.size());

                // check to make sure format is correct.
                if (start < 0 || end > filterSortedSet.size() || start > end) {
                    throw new IndexOutOfBoundsException("Index range out of bounds");
                }

                // add the desired range of players into the roster.
                roster.addAll(filterSortedSet.stream().toList().subList(start, end + 1));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid index range input");
            }
            // we have now eliminated every possibility except a singular number.
        } else {
            try {
                // initialize boolean to check if player is in database.
                boolean found = false;
                // split string by space to check if user passed in a full name.
                range = nameOrRange.split(" ");
                if (range.length == 2) { // meaning user passed in a full name.
                    String firstName = range[0];
                    String lastName = range[1];
                    // iterate through filterSortedSet to find the player object.
                    for (Player player : filterSortedSet) {
                        if (player.getFirstName().equalsIgnoreCase(firstName) &&
                                player.getLastName().equalsIgnoreCase(lastName)) {
                            roster.add(player);
                            found = true;
                            return; // end method here.
                        }
                    }
                    if (found == false) {
                        // get the records from net utils.
                        PlayerBackground bg = getAPlayer(firstName, lastName);
                        String id = String.valueOf(bg.id());
                        PlayerAverages avg = fetchSeasonAverages(id);
                        Player newPlayer = createPlayer(bg, avg);
                        roster.add(newPlayer);
                        return;
                    }
                } else { // meaning the string passed in must be a singular index.
                    int index = Integer.parseInt(nameOrRange) - 1;

                    // protect logic by ensuring index is within range.
                    if (index >= 0 && index < filterSortedSet.size()) {
                        roster.add(filterSortedSet.stream().toList().get(index));
                    } else {
                        throw new IndexOutOfBoundsException("Index out of bounds");
                    }
                }
            } catch(NumberFormatException e){
                    throw new IllegalArgumentException("Invalid index input");
                } catch (IOException e) {
              throw new RuntimeException(e);
            }
        }
        }

    /**
     * Removes players from user's roster. Built to handle names, indexes/index ranges, and keyword "all".
     *
     * NOTE: if keyword "all" is given, roster will be emptied.
     * @param nameOrRange
     */
    @Override
    public void removeFromRoster(String nameOrRange) {
        // start by checking for keyword "all"
        if (nameOrRange.equalsIgnoreCase("all")) {
            setRoster(new LinkedHashSet<Player>()); // emptying the roster
            return; // end method.
        }
        // checking if string is a range, name, or singular number by parsing.
        String[] range = nameOrRange.split("-");

        // if range's length is 2, then we know it's a range.
        if (range.length == 2) {
            try {
                int start = Integer.parseInt(range[0]) - 1; // -1 because index starts at 0.
                int end = Integer.parseInt(range[1]);

                // check to make sure format is correct.
                if (start <= 0 || end > roster.size() || start > end) {
                    throw new IndexOutOfBoundsException("Index range out of bounds");
                }

                // remove the desired range of players into the roster.
                roster.removeAll(roster.stream().toList().subList(start, end));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid index range input");
            }
            // we have now eliminated every possibility except a singular number.
        } else {
            try {
                range = nameOrRange.split(" ");
                if (range.length == 2) { // meaning user passed in a full name.
                    String firstName = range[0];
                    String lastName = range[1];

                    // iterate through roster to find corresponding name
                    for (Player player : roster) {
                        if (player.getFirstName().equalsIgnoreCase(firstName) &&
                                player.getLastName().equalsIgnoreCase(lastName)) {
                            roster.remove(player);
                            return; // end method.
                        }
                    }
                } else { // meaning it must be a singular index.
                    int index = Integer.parseInt(nameOrRange) - 1;

                    // protect logic by ensuring index is within range.
                    if (index >= 0 && index < roster.size()) {
                        roster.remove(roster.stream().toList().get(index));
                    } else {
                        throw new IndexOutOfBoundsException("Index out of bounds");
                    }
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid index input");
            }
        }
    }

    /**
     * Creates a player object from 2 records, and saves it to the NBA Database.
     * @param background - the player background record.
     * @param seasonAverages - the player season averages record.
     * @return Player object.
     */
    @Override
    public Player createPlayer(PlayerBackground background,
                               PlayerAverages seasonAverages) {
        Player newPlayer = new Player(background.first_name(),
            background.last_name(), background.position(),
            background.height(), background.draft_year(),
            background.draft_round(), background.draft_number(),
            background.team().full_name(), background.team().conference(),
            seasonAverages.pts(), seasonAverages.reb(), seasonAverages.ast(),
            seasonAverages.blk(), seasonAverages.stl(), seasonAverages.min(),
            seasonAverages.fg_pct(), seasonAverages.ft_pct(),
            seasonAverages.fg3_pct());

        NBAROSTER.add(newPlayer);
        return newPlayer;
    }






}
