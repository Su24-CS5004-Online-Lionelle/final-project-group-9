package Model.Net;

import Model.Format.DataFormatter;
import Model.Format.Format;
import Model.IModel;
import Model.IModel.PlayerBackground;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NetUtils {
  private static final String API_URL = "https://api.balldontlie.io/v1";
  private static final String API_KEY = "b339f77f-8c45-4cb4-bfec-bc26b584c358";

  /**
   * Prevent instantiation.
   */
  private NetUtils() {
    // Prevent instantiation
  }

  private static HttpURLConnection UrlConnection(String endpoint)
      throws IOException {
    URL url = new URL(API_URL + endpoint);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setRequestProperty("Authorization", API_KEY);
    connection.setRequestProperty("Accept", "application/json");
    return connection;
  }

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

//  /**
//   * Gets a player info data.
//   */
//  public static IModel.PlayerBackground getAPlayer(String endpoint) throws IOException {
//    // endpoint searches for player using first and or last name
//
//    // returns background data for a player
//    return null;
//  }

  /**
   * Gets a player averages info data.
   */
  public static IModel.PlayerAverages getAPlayerAverage(String endpoint)
      throws IOException {
    // endpoint searches for player using id

    // returns background data for a player
    return null;
  }

  /**
   * Returns players with either first or last name.
   * @param name first or last name
   * @return Player background
   * @throws IOException if file not found
   */
  public static PlayerBackground getAPlayer(String name)
      throws IOException {
    return getAPlayer(name, "");
  }

  /**
   * Gets a player info data using their first and last name.
   * @param name first or last name
   * @param lastName first or last name of player
   * @return a player background object
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
    

    // Write to database file
    File file = new File("data/getplayer.json");
    DataFormatter.write(playerList, Format.JSON,
        new FileOutputStream(file));

    return playerList.get(0);
  }

  /**
   * Get all player data string.
   *
   * @param endpoint
   * @return
   * @throws IOException
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
        endpoint = "/players?cursor=" + nextCursor.asText() + "&per_page=100";
        cursorLimit--;
      }
    }

    // Write to database file
    File file = new File("data/playerbackground2023.json");
    DataFormatter.write(masterPlayerList, Format.JSON,
        new FileOutputStream(file));

    return masterPlayerList;
  }

  /**
   * @return
   * @throws IOException
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
      List<IModel.PlayerAverages> currentPagePlayers =
          mapper.readValue(jsonResponse, mapper.getTypeFactory().constructCollectionType(List.class, IModel.PlayerAverages.class));

      // Add current page of players to master list
      masterPlayerAverage.addAll(currentPagePlayers);


      // Write to database file
      File file = new File("data/aplayeraverage.json");
      DataFormatter.write(masterPlayerAverage, Format.JSON,
          new FileOutputStream(file));

      return masterPlayerAverage.get(0);
    }
}
