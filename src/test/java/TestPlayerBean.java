import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Model.*;

class TestPlayerBean {
  private PlayerBean testPlayerBean1 = new PlayerBean("Seth", "Curry", "G", "6-1", 0, 0, 0,
          "Charlotte Hornets", "East", 5.136, 1.545, 1.0, 0.136, 0.5,
          "14:01", 0.392, 0.903, 0.352);

  private PlayerBean testPlayerBean2 = new PlayerBean("Luka", "Doncic", "F-G", "6-7", 2018,
          1, 3, "Dallas Mavericks", "West", 33.857, 9.243, 9.786,
          0.543, 1.4, "37:26", 0.487, 0.786, 0.382);

  private PlayerBean testPlayerBean3 = new PlayerBean("Shai", "Gilgeous-Alexander", "G", "6-6",
          2018, 1, 11, "Oklahoma City Thunder", "West",
          30.053, 5.52, 6.213, 0.893, 1.987, "34:08", 0.535, 0.874, 0.353);

  private PlayerBean testPlayerBean4 = new PlayerBean("Giannis", "Antetokounmpo", "F", "6-11", 2013,
          1, 15, "Milwaukee Bucks", "East",
          30.438, 11.493, 6.521, 1.082, 1.164, "35:12", 0.611, 0.657, 0.274);

  private PlayerBean testPlayerBean5 = new PlayerBean("Jalen", "Brunson", "G", "6-2", 2018,
          2, 33, "New York Knicks", "East",
          28.727, 3.597, 6.753, 0.169, 0.922, "35:25", 0.479, 0.847, 0.401);

  private PlayerBean testPlayerBean6 = new PlayerBean("LeBron", "James", "F", "6-9", 2003,
          1, 1, "Los Angeles Lakers", "West",
          25.603, 7.37, 8.247, 0.548, 1.288, "35:21", 0.535, 0.757, 0.407);

  @Test
  void testGetFirstName() {
    String testFirstName1 = "LeBron";
    String testFirstName2 = "Jalen";
    String testFirstName3 = "Luka";

    assertEquals(testFirstName1, testPlayerBean6.getFirstName());
    assertEquals(testFirstName2, testPlayerBean5.getFirstName());
    assertEquals(testFirstName3, testPlayerBean2.getFirstName());
  }

  @Test
  void testGetLastName() {
    String testLastName1 = "Curry";
    String testLastName2 = "Gilgeous-Alexander";
    String testLastName3 = "James";

    assertEquals(testLastName1, testPlayerBean1.getLastName());
    assertEquals(testLastName2, testPlayerBean3.getLastName());
    assertEquals(testLastName3, testPlayerBean6.getLastName());

  }

  @Test
  void testGetPosition() {
    String guard = "G";
    String forward = "F";
    String guardForward = "F-G";
    assertEquals(guard, testPlayerBean1.getPosition());
    assertEquals(forward, testPlayerBean6.getPosition());
    assertEquals(guardForward, testPlayerBean2.getPosition());
  }

  @Test
  void testGetHeight() {
    String seth = "6-1";
    String brunson = "6-2";
    String giannis = "6-11";
    assertEquals(seth, testPlayerBean1.getHeight());
    assertEquals(brunson, testPlayerBean5.getHeight());
    assertEquals(giannis, testPlayerBean4.getHeight());
  }

  @Test
  void testGetDraftYear() {
    assertEquals(2003, testPlayerBean6.getDraftYear());
    assertEquals(2013, testPlayerBean4.getDraftYear());
    assertEquals(2018, testPlayerBean5.getDraftYear());

    // testing that if player was undrafted, draft year is 0.
    assertEquals(0, testPlayerBean1.getDraftYear());
  }

  @Test
  void testGetDraftRound() {
    // testing that if player was undrafted, draft round would be 0
    assertEquals(0, testPlayerBean1.getDraftRound());

    assertEquals(2, testPlayerBean5.getDraftRound());
    assertEquals(1, testPlayerBean4.getDraftRound());
  }

  @Test
  void testGetDraftPick() {
    // testing that if player was undrafted, draft pick will be 0.
    assertEquals(0, testPlayerBean1.getDraftPick());

    assertEquals(1, testPlayerBean6.getDraftPick());
    assertEquals(15, testPlayerBean4.getDraftPick());
    assertEquals(33, testPlayerBean5.getDraftPick());
  }

  @Test
  void testGetTeam() {
    assertEquals("Charlotte Hornets", testPlayerBean1.getTeam());
    assertEquals("Dallas Mavericks", testPlayerBean2.getTeam());
    assertEquals("Los Angeles Lakers", testPlayerBean6.getTeam());
  }

  @Test
  void testGetConference() {
    assertEquals("West", testPlayerBean3.getConference());
    assertEquals("East", testPlayerBean4.getConference());
    assertEquals("East", testPlayerBean5.getConference());
  }

  @Test
  void testGetPpg() {
    assertEquals(33.857, testPlayerBean2.getPpg());
    assertEquals(5.136, testPlayerBean1.getPpg());
    assertEquals(25.603, testPlayerBean6.getPpg());
  }

  @Test
  void testGetRpg() {
    assertEquals(5.52, testPlayerBean3.getRpg());
    assertEquals(11.493, testPlayerBean4.getRpg());
    assertEquals(3.597, testPlayerBean5.getRpg());
  }

  @Test
  void testGetApg() {
    assertEquals(1.0, testPlayerBean1.getApg());
    assertEquals(8.247, testPlayerBean6.getApg());
    assertEquals(9.786, testPlayerBean2.getApg());
  }

  @Test
  void testGetBpg() {
    assertEquals(0.136, testPlayerBean1.getBpg());
    assertEquals(1.082, testPlayerBean4.getBpg());
    assertEquals(0.169, testPlayerBean5.getBpg());
  }

  @Test
  void testGetSpg() {
    assertEquals(1.987, testPlayerBean3.getSpg());
    assertEquals(1.4, testPlayerBean2.getSpg());
    assertEquals(1.288, testPlayerBean6.getSpg());
  }

  @Test
  void testGetMpg() {
    assertEquals("35:25", testPlayerBean5.getMpg());
    assertEquals("14:01", testPlayerBean1.getMpg());
    assertEquals("37:26", testPlayerBean2.getMpg());
  }

  @Test
  void testGetFgp() {
    assertEquals(0.535, testPlayerBean6.getFgp());
    assertEquals(0.611, testPlayerBean4.getFgp());
    assertEquals(0.479, testPlayerBean5.getFgp());
  }

  @Test
  void testGetFtp() {
    assertEquals(0.657, testPlayerBean4.getFtp());
    assertEquals(0.903, testPlayerBean1.getFtp());
    assertEquals(0.786, testPlayerBean2.getFtp());
  }

  @Test
  void testGetFg3p() {
    assertEquals(0.274, testPlayerBean4.getFg3p());
    assertEquals(0.407, testPlayerBean6.getFg3p());
    assertEquals(0.382, testPlayerBean2.getFg3p());
  }

  @Test
  void testToString() {
    String testToStringLeBron = """
            First name: LeBron
                        
            Last name: James
                        
            Position: F
                        
            Height: 6-9
                        
            Draft Year: 2003
                        
            Draft Round: 1
                        
            Draft Pick: 1
                        
            Team: Los Angeles Lakers
                        
            Conference: West
                        
            Points per game: 25.603
                        
            Rebounds per game: 7.370
                        
            Assists per game: 8.247
                        
            Blocks per game: 0.548
                        
            Steals per game: 1.288
                        
            Minutes per game: 35:21
                        
            Field goal percentage per game: 0.535
                        
            Free throw percentage per game: 0.757
                        
            Three point percentage per game: 0.407""";
    String testToStringShai = """
            First name: Shai
                        
            Last name: Gilgeous-Alexander
                        
            Position: G
                        
            Height: 6-6
                        
            Draft Year: 2018
                        
            Draft Round: 1
                        
            Draft Pick: 11
                        
            Team: Oklahoma City Thunder
                        
            Conference: West
                        
            Points per game: 30.053
                        
            Rebounds per game: 5.520
                        
            Assists per game: 6.213
                        
            Blocks per game: 0.893
                        
            Steals per game: 1.987
                        
            Minutes per game: 34:08
                        
            Field goal percentage per game: 0.535
                        
            Free throw percentage per game: 0.874
                        
            Three point percentage per game: 0.353""";
    assertEquals(testToStringShai, testPlayerBean3.toString());
    assertEquals(testToStringLeBron, testPlayerBean6.toString());

  }
}