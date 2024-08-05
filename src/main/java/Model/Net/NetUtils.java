package Model.Net;

import Model.IModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Net.PlayerRecord;
import Model.Net.PlayerAverages;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class NetUtils {
  private static final String API_URL = "https://api.balldontlie.io/v1";
  private static final String API_KEY = "b339f77f-8c45-4cb4-bfec-bc26b584c358";

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

  public static List<IModel.PlayerBackground> fetchPlayers() throws IOException {
    String endpoint = "/players";
    HttpURLConnection connection = UrlConnection(endpoint);

    try {
      System.out.println("Fetching URL in fetchPlayers" + API_URL + endpoint);
      String jsonResponse = getUrlContents(connection);
      System.out.println("API responded in fetchPlayers" + jsonResponse);
      ObjectMapper mapper = new ObjectMapper();

      List<IModel.PlayerBackground> bg = mapper.readValue(jsonResponse, new TypeReference<List<IModel.PlayerBackground>>() { });
      return bg;
      // CURRENTLY: Having issues with deserializing next page of players. Getting first 25 records successfully. STOPPED HERE.
     //  return mapper.readValue(jsonResponse, mapper.getTypeFactory().constructCollectionType(List.class, IModel.PlayerBackground.class));
    } finally {
      connection.disconnect();
    }
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

