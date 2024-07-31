package Model;
import Model.Format.Format;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model implements IModel {
    /** String containing the file path to read from. */
    String filePath;

    /** List containing player record objects. */
    List<Player> roster = new ArrayList<Player>();

    /** Constructor with default filepath. */
    public Model() {
        this.filePath = DATABASE;
    }

    /** Overloaded constructor with different filepath. */
    public Model(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets the players as a list.
     *
     * @return the list of players.
     */
    @Override
    public List<Player> getPlayers() {
        ObjectMapper mapper = new XmlMapper();
        try {
            List<PlayerBean> beanList = mapper.readValue(new File(filePath), new TypeReference<List<PlayerBean>>() {
            });
            // System.out.println(beanList.size());
            for (PlayerBean bean : beanList) {
                Player player = new Player(bean.getName(), bean.getAge(), bean.getPosition(), bean.getHeight(),
                        bean.getDraftYear(), bean.getDraftRound(), bean.getDraftPick(), bean.getTeam(),
                        bean.getConference(), bean.getPpg(), bean.getRpg(), bean.getApg(), bean.getBpg(),
                        bean.getSpg(), bean.getMpg(), bean.getFgp(), bean.getFtp(), bean.getFg3p());
                roster.add(player);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return roster;
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
                Turnovers per game: %.3f\n 
                Minutes per game: %.3f\n 
                Field goal percentage per game: %.3f\n 
                Free throw percentage per game: %.3f\n 
                Three point percentage per game: %.3f""",
                player.getName(), player.getAge(), player.getPosition(), player.getHeight(), player.getDraftYear(),
                player.getDraftRound(), player.getDraftPick(), player.getTeam(), player.getConference(),
                player.getPpg(), player.getRpg(), player.getApg(), player.getBpg(), player.getSpg(),
                player.getTpg(), player.getMpg(), player.getFgp(), player.getFtp(), player.getFg3p());
    }

    /**
     * Creates a new record Player object.
     *
     * @param playerName
     * @return player
     */
    @Override
    public Player createPlayer(String playerName) {
        return null; // NEED NETUTILS FIRST.
    }
}
