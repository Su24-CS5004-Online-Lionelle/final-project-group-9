package Model.Net;

public record PlayerRecord(int id, String first_name, String last_name, String position, String height,
                           String weight, int jersey_number, int draft_year, int draft_round, int draft_number,
                           String team) {

}
