import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import Model.IModel;
import Model.IModel.PlayerBackground;
import Model.Net.NetUtils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class TestNetUtils {

  /**
   * Tests the getUrlContents method.
   */
  @Test
  public void testGetUrlContents() {
    try {
      String endpoint = "/players?search=Stephen";
      String jsonResponse = NetUtils.getPlayerDataString(endpoint);

      // Check if the response contains expected data...Same as get a play name?
      assertEquals(true, jsonResponse.contains("Stephen"));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests the getAPlayer method with only first name.
   */
  @Test
  public void testGetAPlayerByName() {
    try {
      PlayerBackground player = NetUtils.getAPlayer("Stephen");

      // Check if the player returned has the expected first name
      assertEquals("Stephen", player.first_name());
      assertEquals("Curry", player.last_name());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * Tests the getAPlayer method with first and last name.
   */
  @Test
  public void testGetAPlayerByFullName() {
    try {
      PlayerBackground player = NetUtils.getAPlayer("Stephen", "Curry");

      // Ensure the player object is not null
      assertNotNull(player, "Player object should not be null");

      assertEquals("Stephen", player.first_name(), "First name should match");
      assertEquals("Curry", player.last_name(), "Last name should match");
      assertEquals("G", player.position(), "Position should match");
      assertEquals("6-2", player.height(), "Height should match");
      assertEquals(2009, player.draft_year(), "Draft year should match");
      assertEquals(1, player.draft_round(), "Draft round should match");
      assertEquals(7, player.draft_number(), "Draft pick should match");
      assertEquals("Golden State Warriors", player.team().full_name(), "Team name should match");
      assertEquals("West", player.team().conference(), "Conference should match");

    } catch (IOException e) {
      e.printStackTrace();
      fail("Exception thrown while fetching player data: " + e.getMessage());
    }
  }



  /**
   * Tests the fetchPlayers method.
   */
  @Test
  public void testFetchPlayers() {
    try {
      List<PlayerBackground> players = NetUtils.fetchPlayers();

      // Check if the players list is not empty
      assertEquals(true, players.size() > 0);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Tests the fetchSeasonAverages method.
   */
  @Test
  public void testFetchSeasonAverages() {
    try {
      String playerId = "115";  // Stephen Curry's ID
      IModel.PlayerAverages playerAverages = NetUtils.fetchSeasonAverages(playerId);

      // Ensure the player averages object is not null
      assertNotNull(playerAverages, "Player averages should not be null");

      assertEquals(26.373, playerAverages.pts(), "Points per game should match");
      assertEquals(4.44, playerAverages.reb(), "Rebounds per game should match");
      assertEquals(5.093, playerAverages.ast(), "Assists per game should match");
      assertEquals(0.387, playerAverages.blk(), "Blocks per game should match");
      assertEquals(0.747, playerAverages.stl(), "Steals per game should match");
      assertEquals(0.45, playerAverages.fg_pct(), "Field goal percentage should match");
      assertEquals(0.924, playerAverages.ft_pct(), "Free throw percentage should match");
      assertEquals(0.408, playerAverages.fg3_pct(), "Three-point percentage should match");

    } catch (IOException e) {
      e.printStackTrace();
      fail("Exception thrown while fetching season averages: " + e.getMessage());
    }
  }


  /**
   * Tests the getAPlayer method with an invalid name.
   */
  @Test
  public void testGetAPlayerInvalidName() {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      NetUtils.getAPlayer("InvalidName", "InvalidLastName");
    });
    assertThrows(IndexOutOfBoundsException.class, () -> {
      NetUtils.getAPlayer("Stephennn", "Curry");
    });
  }
}
