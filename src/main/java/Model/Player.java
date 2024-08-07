package Model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "firstName", "lastName", "position", "height", "draftYear",
        "draftRound", "draftPick", "team", "conference", "ppg",
        "rpg", "apg", "bpg", "spg", "mpg", "fgp", "ftp", "fg3p"
})
public class Player {

  /** Player's first name. */
  private String first_name;
  /** Player's last name. */
  private String last_name;
  /** Player's position. */
  private String position;
  /** Player's height. */
  private String height;
  /** Player's draft year. */
  private int draftYear;
  /** Player's draft round. */
  private int draftRound;
  /** Player's draft pick number. */
  private int draftPick;
  /** The team the player plays for. */
  private String team;
  /** The conference of the team that the player is on. */
  private String conference;
  /** Player's points per game. */
  private double ppg;
  /** Player's rebounds per game. */
  private double rpg;
  /** Player's assists per game. */
  private double apg;
  /** Player's blocks per game. */
  private double bpg;
  /** Player's steals per game. */
  private double spg;
  /** Player's minutes per game. */
  private String mpg;
  /** Player's field goal percentage. */
  private double fgp;
  /** Player's free throw percentage. */
  private double ftp;
  /** Player's three point shooting percentage. */
  private double fg3p;

  /** Default constructor. */
  public Player() {}

  /**
   * Overloaded constructor that takes in the player background and season averages information.
   * @param first_name
   * @param last_name
   * @param position
   * @param height
   * @param draftYear
   * @param draftRound
   * @param draftPick
   * @param team
   * @param conference
   * @param ppg
   * @param rpg
   * @param apg
   * @param bpg
   * @param spg
   * @param mpg
   * @param fgp
   * @param ftp
   * @param fg3p
   */
  public Player(String first_name, String last_name, String position, String height, int draftYear, int draftRound, int draftPick,
                String team, String conference, double ppg, double rpg, double apg, double bpg, double spg, String mpg, double fgp, double ftp, double fg3p) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.position = position;
    this.height = height;
    this.draftYear = draftYear;
    this.draftRound = draftRound;
    this.draftPick = draftPick;
    this.team = team;
    this.conference = conference;
    this.ppg = ppg;
    this.rpg = rpg;
    this.apg = apg;
    this.bpg = bpg;
    this.spg = spg;
    this.mpg = mpg;
    this.fgp = fgp;
    this.ftp = ftp;
    this.fg3p = fg3p;
  }

  /**
   * Gets first name of player.
   * @return String - player's first name.
   */
  public String getFirstName() {
    return first_name;
  }

  /**
   * Gets last name of player.
   * @return String - player's last name.
   */
  public String getLastName() {
    return last_name;
  }

  /**
   * Gets player's position.
   * @return String - Player's position.
   */
  public String getPosition() {
    return position;
  }

  /**
   * Gets player's height.
   * @return String - Player's height.
   */
  public String getHeight() {
    return height;
  }

  /**
   * Gets player's draft year.
   * @return int - player's draft year.
   */
  public int getDraftYear() {
    return draftYear;
  }

  /**
   * Gets player's draft round.
   * @return int - player's draft round.
   */
  public int getDraftRound() {
    return draftRound;
  }

  /**
   * Gets player's draft pick number.
   * @return int - The overall numbered pick that the player was drafted.
   */
  public int getDraftPick() {
    return draftPick;
  }

  /**
   * Gets the team the player is currently on.
   * @return String - Team name.
   */
  public String getTeam() {
    return team;
  }

  /**
   * Gets the conference that the player's team plays in.
   * @return String - name of the conference the team plays in.
   */
  public String getConference() {
    return conference;
  }

  /**
   * Gets the player's points per game.
   * @return double - points per game.
   */
  public double getPpg() {
    return ppg;
  }

  /**
   * Gets the player's rebounds per game.
   * @return double - rebounds per game.
   */
  public double getRpg() {
    return rpg;
  }

  /**
   * Gets the player's assists per game.
   * @return double - assists per game.
   */
  public double getApg() {
    return apg;
  }

  /**
   * Gets the player's blocks per game.
   * @return double - blocks per game.
   */
  public double getBpg() {
    return bpg;
  }

  /**
   * Gets the player's steals per game.
   * @return double - steals per game.
   */
  public double getSpg() {
    return spg;
  }

  /**
   * Gets the player's minutes played per game.
   * @return double - minutes played per game.
   */
  public String getMpg() {
    return mpg;
  }

  /**
   * Gets the player's Field goal percentage per game.
   * @return double - field goal percentage per game.
   */
  public double getFgp() {
    return fgp;
  }

  /**
   * Gets the player's free throw percentage per game.
   * @return double - free throw percentage per game.
   */
  public double getFtp() {
    return ftp;
  }

  /**
   * Gets the player's three point shooting percentage per game.
   * @return double - three point shooting percentage per game.
   */
  public double getFg3p() {
    return fg3p;
  }

  /**
   * Takes the player's information and creates a string representation of the player in pretty format.
   * @return String - a player object represented in a string.
   */
  @Override
  public String toString() {
    return String.format(
            "First_Name: %s\nLast_Name: %s\nPosition: %s\nHeight: %s\nDraft Year: %d\nDraft Round: %d\nDraft Pick: %d\n" +
                    "Team: %s\nConference: %s\nPoints per game: %.2f\nRebounds per game: %.2f\nAssists per game: %.2f\n" +
                    "Blocks per game: %.2f\nSteals per game: %.2f\nMinutes per game: %s\n" +
                    "Field goal percentage: %.2f\nFree throw percentage: %.2f\nThree-point percentage: %.2f",
            first_name, last_name, position, height, draftYear, draftRound, draftPick, team, conference, ppg, rpg, apg, bpg, spg, mpg, fgp, ftp, fg3p);
  }
}
