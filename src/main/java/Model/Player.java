package Model;

import Model.Net.PlayerAverages;
import Model.Net.PlayerRecord;

public class Player {

  private String first_name;
  private String last_name;
  private String position;
  private String height;
  private int draftYear;
  private int draftRound;
  private int draftPick;
  private String team;
  private String conference;
  private double ppg;
  private double rpg;
  private double apg;
  private double bpg;
  private double spg;
  private double mpg;
  private double fgp;
  private double ftp;
  private double fg3p;

  public Player() {}

  public Player(String first_name, String last_name, String position, String height, int draftYear, int draftRound, int draftPick,
                String team, String conference, double ppg, double rpg, double apg, double bpg, double spg, double mpg, double fgp, double ftp, double fg3p) {
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

  public String getFirstName() {
    return first_name;
  }

  public String getLastName() {
    return last_name;
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

  // toString method
  @Override
  public String toString() {
    return String.format(
            "First_Name: %s\nLast_Name: %s\nPosition: %s\nHeight: %s\nDraft Year: %d\nDraft Round: %d\nDraft Pick: %d\n" +
                    "Team: %s\nConference: %s\nPoints per game: %.2f\nRebounds per game: %.2f\nAssists per game: %.2f\n" +
                    "Blocks per game: %.2f\nSteals per game: %.2f\nMinutes per game: %.2f\n" +
                    "Field goal percentage: %.2f\nFree throw percentage: %.2f\nThree-point percentage: %.2f",
            first_name, last_name, position, height, draftYear, draftRound, draftPick, team, conference, ppg, rpg, apg, bpg, spg, mpg, fgp, ftp, fg3p);
  }


}
