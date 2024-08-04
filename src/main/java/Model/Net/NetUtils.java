package Model.Net;

import Model.Format.DataFormatter;
import Model.Format.Format;
import Model.IModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Net.PlayerRecord;
import Model.Net.PlayerAverages;
import com.fasterxml.jackson.databind.json.JsonMapper;

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

  private static HttpURLConnection UrlConnection(String endpoint) throws IOException {
    URL url = new URL(API_URL + endpoint);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setRequestProperty("Authorization", API_KEY);
    connection.setRequestProperty("Accept", "application/json");
    return connection;
  }

  private static String getUrlContents(HttpURLConnection connection) throws IOException {
    int responseCode = connection.getResponseCode();
    if (responseCode != HttpURLConnection.HTTP_OK) {
      String error;
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
        error = reader.lines().collect(Collectors.joining("\n"));
      }
      throw new IOException("HTTP error code: " + responseCode + "\n Error message: " + error);
    }
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
      return reader.lines().collect(Collectors.joining("\n"));
    }
  }

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


  public static List<IModel.PlayerBackground> fetchPlayers() throws IOException {
    // Initialize mega player list
    List<IModel.PlayerBackground> masterPlayerList = new ArrayList<>();

    // Get JSON String containing 100 player
    String endpoint = "/players?per_page=100";
    ObjectMapper mapper = new ObjectMapper();
    int cursorLimit = 2; // Sets to 2 pages worth aka 200 players

    while (endpoint != null) {

      // Call endpoints
      String jsonResponse = getPlayerDataString(endpoint);
      JsonNode rootNode = mapper.readTree(jsonResponse);
      JsonNode metaNode = rootNode.path("meta");
      JsonNode dataNode = rootNode.path("data");

      // Map string to object
      List<IModel.PlayerBackground> currentPagePlayers =
          mapper.convertValue(dataNode,
              new TypeReference<List<IModel.PlayerBackground>>() {
              });

      // Add current page of players to master list
      masterPlayerList.addAll(currentPagePlayers);

      System.out.println("\n\n ------ going to next page \n\n");

      // Go to next page
      JsonNode nextCursor = metaNode.path("next_cursor");
      if (nextCursor.isMissingNode() || nextCursor.isNull() || cursorLimit == 0) {
        endpoint = null;
      } else {
        endpoint = "/players?cursor=" + nextCursor.asText() + "&per_page=100";
        cursorLimit--;
      }
    }

    // Write to database file
    File file = new File("data/playerbackground.json");
    DataFormatter.write(masterPlayerList, Format.JSON, new FileOutputStream(file));

    return masterPlayerList;
  }

  public static List<IModel.PlayerAverages> fetchSeasonAverages() throws IOException {
    String endpoint = "/season_averages";
    HttpURLConnection connection = UrlConnection(endpoint);

    try {
      System.out.println("Fetching URL in fetchSeasonAverages");
      String jsonResponse = getUrlContents(connection);
      System.out.println("API responded in fetchSeasonAverages");

      ObjectMapper mapper = new JsonMapper();

      return mapper.readValue(jsonResponse, mapper.getTypeFactory().constructCollectionType(List.class, IModel.PlayerAverages.class));
    } finally {
      connection.disconnect();
    }
  }
}

