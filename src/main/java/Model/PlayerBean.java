package Model;

public class PlayerBean {
    /** Empty bean constructor. */
    public PlayerBean () {}

    /**
     * Overloaded PlayerBean constructor containing all data for the Player objects.
     * @param firstName - String: first name of Player object
     * @param lastName - String: last name of Player object
     * @param position - String: position that Player plays
     * @param height - String: height of Player object
     * @param draftYear - int: year that player was drafted
     * @param draftRound - int: round that player was drafted
     * @param draftPick - int: overall pick that player was selected in draft
     * @param team - String: the team the player is currently on
     * @param conference - String: the conference that the team of the player
     * @param ppg - double: the average points per game of the player
     * @param rpg - double: the average rebounds per game of the player
     * @param apg - double: the average assists per game of the player
     * @param bpg - double: the average blocks per game of the player
     * @param spg - double: the average steals per game of the player
     * @param mpg - String: the average minutes per game of the player
     * @param fgp - double: the average field goal percentage per game of the player
     * @param ftp - double: the average free throw percentage per game of the player
     * @param fg3p - double: the average three point percentage per game of player
     */
    public PlayerBean(String firstName, String lastName, String position, String height, int draftYear, int draftRound,
                      int draftPick, String team, String conference, double ppg, double rpg, double apg, double
                      bpg, double spg, String mpg, double fgp, double ftp, double fg3p) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    /** First name of player. */
    private String firstName;
    /** Last name of player. */
    private String lastName;
    /** Position of player. */
    private String position;
    /** Height of player. */
    private String height;
    /** Draft year of player. */
    private int draftYear;
    /** Draft round of player. */
    private int draftRound;
    /** Draft pick of player. */
    private int draftPick;
    /** Team of player. */
    private String team;
    /** Conference of player. */
    private String conference;
    /** Points per game of player. */
    private double ppg;
    /** Rebounds per game of player. */
    private double rpg;
    /** Assists per game of player. */
    private double apg;
    /** Blocks per game of player. */
    private double bpg;
    /** Steals per game of player. */
    private double spg;
    /** Minutes per game of player. */
    private String mpg;
    /** Field goal percentage of player. */
    private double fgp;
    /** Free throw percentage of player. */
    private double ftp;
    /** Three point field goal percentage of player. */
    private double fg3p;

    /**
     * Gets player's first name.
     * @return String - player's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets player's position.
     * @return String - player's position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Gets player's height.
     * @return String - player's height
     */
    public String getHeight() {
        return height;
    }

    /**
     * Gets player's draft year.
     * @return int - player's draft year
     */
    public int getDraftYear() {
        return draftYear;
    }

    /**
     * Gets player's draft round.
     * @return int - player's draft round
     */
    public int getDraftRound() {
        return draftRound;
    }

    /**
     * Gets player's draft pick.
     * @return
     */
    public int getDraftPick() {
        return draftPick;
    }

    /**
     * Gets player's team.
     * @return String
     */
    public String getTeam() {
        return team;
    }

    /**
     * Gets player's team's conference.
     * @return
     */
    public String getConference() {
        return conference;
    }

    /**
     * Gets player's points per game.
     * @return double
     */
    public double getPpg() {
        return ppg;
    }

    /**
     * Gets player's rebounds per game.
     * @return double
     */
    public double getRpg() {
        return rpg;
    }

    /**
     * Gets player's assists per game.
     * @return double
     */
    public double getApg() {
        return apg;
    }

    /**
     * Gets player's blocks per game.
     * @return double
     */
    public double getBpg() {
        return bpg;
    }

    /**
     * Gets player's steals per game.
     * @return double
     */
    public double getSpg() {
        return spg;
    }

    /**
     * Gets player's minutes per game.
     * @return double
     */
    public String getMpg() {
        return mpg;
    }

    /**
     * Gets player's field goal percentage.
     * @return double
     */
    public double getFgp() {
        return fgp;
    }

    /**
     * Gets player's free throw percentage.
     * @return double
     */
    public double getFtp() {
        return ftp;
    }

    /**
     * Gets player's three point shooting percentage.
     * @return double
     */
    public double getFg3p() {
        return fg3p;
    }

    /**
     * Returns a string containing all the PlayerBean attributes.
     * @return String.
     */
    public String toString() {
        return String.format(
                """
                First name: %s\n
                Last name: %s\n
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
                Minutes per game: %s\n 
                Field goal percentage per game: %.3f\n 
                Free throw percentage per game: %.3f\n 
                Three point percentage per game: %.3f""",
                getFirstName(), getLastName(), getPosition(), getHeight(), getDraftYear(), getDraftRound(), getDraftPick(),
                getTeam(), getConference(), getPpg(), getRpg(), getApg(), getBpg(), getSpg(), getMpg(),
                getFgp(), getFtp(), getFg3p());
    }

    /**
     * Gets player's last name.
     * @return String player's last name.
     */
    public String getLastName() {
        return lastName;
    }
}
