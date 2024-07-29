package Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.File;
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
                        bean.getSpg(), bean.getTpg(), bean.getMpg(), bean.getFgp(), bean.getFtp(), bean.getFg3p());
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
        return null;
    }

    /**
     * Gets file path in string.
     *
     * @return String
     */
    @Override
    public String getFilePath() {
        return "";
    }

    /**
     * Sets file path.
     *
     * @param filePath
     */
    @Override
    public void setFilePath(String filePath) {

    }

    /**
     * Returns a string containing the player record passed in, in the format passed in.
     *
     * @param player
     * @param format
     * @return
     */
    @Override
    public String toString(Player player, Formats format) {
        return "";
    }

    /**
     * Creates a new record Player object.
     *
     * @param playerName
     * @return player
     */
    @Override
    public Player createPlayer(String playerName) {
        return null;
    }
}
