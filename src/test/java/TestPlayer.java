import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.Player;

/**
 * The type Test player.
 */
class TestPlayer {

  private Player player;

  /**
   * Sets up player object makes sure that all values have correct string, double or int.
   */
  @BeforeEach
  void setUp() {
    // Initialize the Player object for Stephen Curry
    player = new Player("Stephen", "Curry", "G", "6-2", 2009, 1, 7,
            "Golden State Warriors", "West", 26.373, 4.44, 5.093, 0.387, 0.747, "35.5", 45.0, 92.4, 40.8);
  }

  /**
   * Test first name.
   */
  @Test
  void testFirstName() {
    assertEquals("Stephen", player.getFirstName());
  }

  /**
   * Test last name.
   */
  @Test
  void testLastName() {
    assertEquals("Curry", player.getLastName());
  }

  /**
   * Test position.
   */
  @Test
  void testPosition() {
    assertEquals("G", player.getPosition());
  }

  /**
   * Test height.
   */
  @Test
  void testHeight() {
    assertEquals("6-2", player.getHeight());
  }

  /**
   * Test draft year.
   */
  @Test
  void testDraftYear() {
    assertEquals(2009, player.getDraftYear());
  }

  /**
   * Test draft round.
   */
  @Test
  void testDraftRound() {
    assertEquals(1, player.getDraftRound());
  }

  /**
   * Test draft pick.
   */
  @Test
  void testDraftPick() {
    assertEquals(7, player.getDraftPick());
  }

  /**
   * Test team.
   */
  @Test
  void testTeam() {
    assertEquals("Golden State Warriors", player.getTeam());
  }

  /**
   * Test conference.
   */
  @Test
  void testConference() {
    assertEquals("West", player.getConference());
  }

  /**
   * Test ppg.
   */
  @Test
  void testPpg() {
    assertEquals(26.373, player.getPpg());
  }

  /**
   * Test rpg.
   */
  @Test
  void testRpg() {
    assertEquals(4.44, player.getRpg());
  }

  /**
   * Test apg.
   */
  @Test
  void testApg() {
    assertEquals(5.093, player.getApg());
  }

  /**
   * Test bpg.
   */
  @Test
  void testBpg() {
    assertEquals(0.387, player.getBpg());
  }

  /**
   * Test spg.
   */
  @Test
  void testSpg() {
    assertEquals(0.747, player.getSpg());
  }

  /**
   * Test mpg.
   */
  @Test
  void testMpg() {
      assertEquals("35.5", player.getMpg());
    }


  /**
   * Test fgp.
   */
  @Test
  void testFgp() {
    assertEquals(45.0, player.getFgp());
  }

  /**
   * Test ftp.
   */
  @Test
  void testFtp() {
    assertEquals(92.4, player.getFtp());
  }

  /**
   * Test fg 3 p.
   */
  @Test
  void testFg3p() {
    assertEquals(40.8, player.getFg3p());
  }
}
