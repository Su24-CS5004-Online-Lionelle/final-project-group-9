package Model.Net;

import Model.IModel;
import Model.IModel.PlayerBackground;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Net utils.
 */
public class NetUtils {
  private static final String API_URL = "https://api.balldontlie.io/v1";
  private static final String API_KEY =  "d8754d2a-af6d-42ab-a674-d3ab4d504ad9";

  /**
   * Prevent instantiation.
   */
  private NetUtils() {
    // Prevent instantiation
  }

  /**
   * Sets the connection with the API.
   *
   * @param endpoint the specific API endpoint to connect to.
   * @return connection to then send a get request to the specified endpoint.
   * @throws IOException if error is seen with URL or during connection.
   */
  private static HttpURLConnection UrlConnection(String endpoint)
          throws IOException {
    URL url = new URL(API_URL + endpoint);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setRequestProperty("Authorization", API_KEY);
    connection.setRequestProperty("Accept", "application/json");
    return connection;
  }

  /**
   * Gets the contents in the URL.
   *
   * @param connection the HTTP object in URLConnection that sends a Get request to the specified endpoint.
   * @return Gets the contents from the URL as a String.
   * @throws IOException if HTTP is not ok.
   */
  private static String getUrlContents(HttpURLConnection connection)
          throws IOException {
    int responseCode = connection.getResponseCode();
    if (responseCode != HttpURLConnection.HTTP_OK) {
      String error;
      try (BufferedReader reader = new BufferedReader(
              new InputStreamReader(connection.getErrorStream()))) {
        error = reader.lines().collect(Collectors.joining("\n"));
      }
      throw new IOException(
              "HTTP error code: " + responseCode + "\n Error message: " + error);
    }
    try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(connection.getInputStream()))) {
      return reader.lines().collect(Collectors.joining("\n"));
    }
  }

  /**
   * Retrieves player data based on First or Last name.
   *
   * @param name The first or last name of a player.
   * @return A player background object.
   * @throws IOException Occurs if retrieving player data gives error.
   */
  public static PlayerBackground getAPlayer(String name)
          throws IOException {
    return getAPlayer(name, "");
  }

  /**
   * Gets a player info data using their first and last name.
   * If a players last name is not given then it will search with only the first name.
   *
   * @param name The first name.
   * @param lastName The last name. If this is empty only the first name will be used for search.
   * @return A player background object.
   * @throws IOException If error occurs while retrieving player data.
   */
  public static PlayerBackground getAPlayer(String name, String lastName)
          throws IOException {
    // Initialize mega player list
    List<PlayerBackground> playerList = new ArrayList<>();
    String endpoint;

    // Get JSON String
    if (lastName.isEmpty()) {
      endpoint = "/players?search=" + name;
    } else {
      endpoint = "/players?first_name=" + name + "&last_name=" + lastName;
    }

    String jsonResponse = getPlayerDataString(endpoint);

    ObjectMapper mapper = new ObjectMapper();

    // Call endpoints
    JsonNode rootNode = mapper.readTree(jsonResponse);
    JsonNode dataNode = rootNode.path("data");

    // If data node is not empty, deserialize
    if (dataNode.isArray() && dataNode.size() > 0) {
      playerList =
              mapper.convertValue(dataNode,
                      new TypeReference<List<PlayerBackground>>() {
                      });
    }


    return playerList.get(0);
  }

  /**
   * Retrieves the Player Data as a Json String.
   *
   * @param endpoint the endpoint
   * @return player data retrieved as a Json formatted String.
   * @throws IOException Occurs if error pop up in connection.
   */
  public static String getPlayerDataString(String endpoint) throws IOException {
    // Get URL connection using endpoint
    HttpURLConnection connection = UrlConnection(endpoint);

    // Convert data to string
    System.out.println("Fetching URL in fetchPlayers" + API_URL + endpoint);
    String jsonResponse = getUrlContents(connection);
    System.out.println("API responded in fetchPlayers" + jsonResponse);

    // Disconnect from server
    connection.disconnect();

    return jsonResponse;
  }


  /**
   * Fetches a list of Active players from the API.
   *
   * @return the list of player background object representing the active players.
   * @throws IOException Occurs if JSON parsing or API interactions has an error.
   */
  public static List<PlayerBackground> fetchPlayers()
          throws IOException {
    // Initialize mega player list
    List<PlayerBackground> masterPlayerList = new ArrayList<>();

    // Get JSON String containing 100 player
    String endpoint = "/players/active?per_page=100";
    ObjectMapper mapper = new ObjectMapper();
    int cursorLimit = 25; // Sets to 2 pages worth aka 200 players

    while (endpoint != null) {

      // Call endpoints
      String jsonResponse = getPlayerDataString(endpoint);
      JsonNode rootNode = mapper.readTree(jsonResponse);
      JsonNode metaNode = rootNode.path("meta");
      JsonNode dataNode = rootNode.path("data");

      // Map string to object
      List<PlayerBackground> currentPagePlayers =
              mapper.convertValue(dataNode,
                      new TypeReference<List<PlayerBackground>>() {
                      });

      // Add current page of players to master list
      masterPlayerList.addAll(currentPagePlayers);

      System.out.println("\n\n ------ going to next page \n\n");

      // Go to next page
      JsonNode nextCursor = metaNode.path("next_cursor");
      if (nextCursor.isMissingNode() || nextCursor.isNull() ||
              cursorLimit == 0) {
        endpoint = null;
      } else {
        endpoint = "/players/active?cursor=" + nextCursor.asText() + "&per_page=100";
        cursorLimit--;
      }
    }

    return masterPlayerList;
  }


  /**
   * Fetch season averages by player ID.
   *
   * @param id The id of the player.
   * @return The PlayerAverages model object with the players season averages.
   * @throws IOException Thrown case of API, parsing, or request processing error.
   */
  public static IModel.PlayerAverages fetchSeasonAverages(String id)
          throws IOException {
    // Initialize mega player list
    List<IModel.PlayerAverages> masterPlayerAverage = new ArrayList<>();

    // Get string of players
    String endpoint = "/season_averages?season=2023&player_ids[]=" + id;
    ObjectMapper mapper = new ObjectMapper();

    // Call endpoints
    String jsonResponse = getPlayerDataString(endpoint);
    JsonNode rootNode = mapper.readTree(jsonResponse);
    JsonNode dataNode = rootNode.path("data");

    // Map string to object
    if (dataNode.isArray() && dataNode.size() > 0) {
      masterPlayerAverage =
              mapper.convertValue(dataNode,
                      new TypeReference<List<IModel.PlayerAverages>>() {
                      });
    }
      return masterPlayerAverage.get(0);
    }
  }

