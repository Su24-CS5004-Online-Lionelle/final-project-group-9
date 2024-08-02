package Model;

import Model.Net.PlayerAverages;
import Model.Net.PlayerRecord;

public class Player {

  private String first_name;
  private String last_name;
  private int age;
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

  public Player(String first_name, String last_name, int age, String position, String height, int draftYear, int draftRound, int draftPick,
                String team, String conference, double ppg, double rpg, double apg, double bpg, double spg, double mpg, double fgp, double ftp, double fg3p) {
    this.first_name = first_name;
    this.last_name = last_name;
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

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public int getDraftYear() {
    return draftYear;
  }

  public void setDraftYear(int draftYear) {
    this.draftYear = draftYear;
  }

  public int getDraftRound() {
    return draftRound;
  }

  public void setDraftRound(int draftRound) {
    this.draftRound = draftRound;
  }

  public int getDraftPick() {
    return draftPick;
  }

  public void setDraftPick(int draftPick) {
    this.draftPick = draftPick;
  }

  public String getTeam() {
    return team;
  }

  public void setTeam(String team) {
    this.team = team;
  }

  public String getConference() {
    return conference;
  }

  public void setConference(String conference) {
    this.conference = conference;
  }

  public double getPpg() {
    return ppg;
  }

  public void setPpg(double ppg) {
    this.ppg = ppg;
  }

  public double getRpg() {
    return rpg;
  }

  public void setRpg(double rpg) {
    this.rpg = rpg;
  }

  public double getApg() {
    return apg;
  }

  public void setApg(double apg) {
    this.apg = apg;
  }

  public double getBpg() {
    return bpg;
  }

  public void setBpg(double bpg) {
    this.bpg = bpg;
  }

  public double getSpg() {
    return spg;
  }

  public void setSpg(double spg) {
    this.spg = spg;
  }

  public double getMpg() {
    return mpg;
  }

  public void setMpg(double mpg) {
    this.mpg = mpg;
  }

  public double getFgp() {
    return fgp;
  }

  public void setFgp(double fgp) {
    this.fgp = fgp;
  }

  public double getFtp() {
    return ftp;
  }

  public void setFtp(double ftp) {
    this.ftp = ftp;
  }

  public double getFg3p() {
    return fg3p;
  }

  public void setFg3p(double fg3p) {
    this.fg3p = fg3p;
  }

  // toString method
  @Override
  public String toString() {
    return String.format(
            "First_Name: %s\nLast_Name: %s\nAge: %d\nPosition: %s\nHeight: %s\nDraft Year: %d\nDraft Round: %d\nDraft Pick: %d\n" +
                    "Team: %s\nConference: %s\nPoints per game: %.2f\nRebounds per game: %.2f\nAssists per game: %.2f\n" +
                    "Blocks per game: %.2f\nSteals per game: %.2f\nMinutes per game: %.2f\n" +
                    "Field goal percentage: %.2f\nFree throw percentage: %.2f\nThree-point percentage: %.2f",
            first_name, last_name, age, position, height, draftYear, draftRound, draftPick, team, conference, ppg, rpg, apg, bpg, spg, mpg, fgp, ftp, fg3p);
  }


}
