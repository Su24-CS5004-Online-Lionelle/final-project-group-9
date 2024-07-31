package Model;

public class PlayerBean {
    /** Empty bean constructor. */
    public PlayerBean () {}

    /**
     * Overloaded PlayerBean constructor containing all data for the Player objects.
     * @param name
     * @param age
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
    public PlayerBean(String name, int age, String position, String height, int draftYear, int draftRound,
                      int draftPick, String team, String conference, double ppg, double rpg, double apg, double
                      bpg, double spg, double mpg, double fgp, double ftp, double fg3p) {
        this.name = name;
        this.age = age;
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

    /** Name of player. */
    private String name;
    /** Age of player. */
    private int age;
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
    private double mpg;
    /** Field goal percentage of player. */
    private double fgp;
    /** Free throw percentage of player. */
    private double ftp;
    /** Three point field goal percentage of player. */
    private double fg3p;

    /**
     * Gets player's name.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets player's age.
     * @return int
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets player's position.
     * @return String
     */
    public String getPosition() {
        return position;
    }

    /**
     * Gets player's height.
     * @return String
     */
    public String getHeight() {
        return height;
    }

    /**
     * Gets player's draft year.
     * @return int
     */
    public int getDraftYear() {
        return draftYear;
    }

    /**
     * Gets player's draft round.
     * @return int
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
    public double getMpg() {
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
                getName(), getAge(), getPosition(), getHeight(), getDraftYear(), getDraftRound(), getDraftPick(),
                getTeam(), getConference(), getPpg(), getRpg(), getApg(), getBpg(), getSpg(), getMpg(),
                getFgp(), getFtp(), getFg3p());
    }
}
