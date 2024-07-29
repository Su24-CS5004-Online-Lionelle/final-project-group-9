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
     * @param tpg
     * @param mpg
     * @param fgp
     * @param ftp
     * @param fg3p
     */
    public PlayerBean(String name, int age, String position, String height, int draftYear, int draftRound,
                      int draftPick, String team, String conference, double ppg, double rpg, double apg, double
                      bpg, double spg, double tpg, double mpg, double fgp, double ftp, double fg3p) {
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
        this.tpg = tpg;
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
    /** Turnovers per game of player. */
    private double tpg;
    /** Minutes per game of player. */
    private double mpg;
    /** Field goal percentage of player. */
    private double fgp;
    /** Free throw percentage of player. */
    private double ftp;
    /** Three point field goal percentage of player. */
    private double fg3p;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public String getHeight() {
        return height;
    }

    public int getDraftYear() {
        return draftYear;
    }

    public int getDraftRound() {
        return draftRound;
    }

    public int getDraftPick() {
        return draftPick;
    }

    public String getTeam() {
        return team;
    }

    public String getConference() {
        return conference;
    }

    public double getPpg() {
        return ppg;
    }


    public double getRpg() {
        return rpg;
    }

    public double getApg() {
        return apg;
    }

    public double getBpg() {
        return bpg;
    }

    public double getSpg() {
        return spg;
    }

    public double getTpg() {
        return tpg;
    }

    public double getMpg() {
        return mpg;
    }

    public double getFgp() {
        return fgp;
    }

    public double getFtp() {
        return ftp;
    }

    public double getFg3p() {
        return fg3p;
    }

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
                Turnovers per game: %.3f\n 
                Minutes per game: %.3f\n 
                Field goal percentage per game: %.3f\n 
                Free throw percentage per game: %.3f\n 
                Three point percentage per game: %.3f""",
                getName(), getAge(), getPosition(), getHeight(), getDraftYear(), getDraftRound(), getDraftPick(),
                getTeam(), getConference(), getPpg(), getRpg(), getApg(), getBpg(), getSpg(), getTpg(), getMpg(),
                getFgp(), getFtp(), getFg3p());
    }
}
