package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

public interface IModel {
    /** Default database file. */
    String DATABASE = ""; // need to figure out what to do about default file to read.

    /**
     * Gets the players as a list.
     * @return the list of players.
     */
    List<Player> getPlayers();

    /**
     * Looks up to see if player is in database. If player in database, return player object.
     * If player is not in the list, serialize player info via BALLDONTLIE api, add to list, and return new player.
     * @param playerName
     * @return player
     */
    Player getPlayer(String playerName);

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
     * Creates a new record Player object.
     * @param playerName
     * @return player
     */
    Player createPlayer(String playerName);

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
    @JacksonXmlRootElement(localName = "data")
    @JsonPropertyOrder({"pts", "ast", "turnover", "pf", "reb", "stl", "blk", "fg_pct", "fg3_pct",
            "ft_pct", "min", "games_played", "player_id"})
    record PlayerAverages(double pts, double ast, double turnover, double pf, double reb, double stl, double blk,
                  double fg_pct, double fg3_pct, double ft_pct, double min, double games_played, int player_id) {
    }

    /**
     * Record to pass player background to objects. Immutable, and uses Json annotations to serialize data.
     * @param id
     * @param first_name
     * @param last_name
     * @param position
     * @param height
     * @param weight
     * @param jersey_number
     * @param draft_year
     * @param draft_round
     * @param draft_number
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JacksonXmlRootElement(localName = "data")
    @JsonPropertyOrder({"id", "first_name", "last_name", "position", "height", "weight", "jersey_number",
            "draft_year", "draft_number", "team"})
    record PlayerBackground(int id, String first_name, String last_name, String position, String height,
                            String weight, int jersey_number, int draft_year, int draft_round, int draft_number,
                            String team) {

    }
}
