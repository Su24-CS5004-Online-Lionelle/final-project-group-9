package Model.Net;

import Model.IModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Net.PlayerRecord;
import Model.Net.PlayerAverages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class NetUtils {
  private static final String API_URL = "https://api.balldontlie.io/v1";
  private static final String API_KEY = "d8754d2a-af6d-42ab-a674-d3ab4d504ad9";

  private static HttpURLConnection UrlConnection(String endpoint) throws IOException {
    URL url = new URL(API_URL + endpoint);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
    connection.setRequestProperty("Accept", "application/json");
    return connection;
  }

  private static String getUrlContents(HttpURLConnection connection) throws IOException {
    if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
      throw new IOException("HTTP error");
    }
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
      return reader.lines().collect(Collectors.joining("\n"));
    }
  }

  public static List<IModel.PlayerBackground> fetchPlayers() throws IOException {
    String endpoint = "/players";
    HttpURLConnection connection = UrlConnection(endpoint);

    try {
      String jsonResponse = getUrlContents(connection);
      ObjectMapper mapper = new ObjectMapper();

      return mapper.readValue(jsonResponse, mapper.getTypeFactory().constructCollectionType(List.class, PlayerRecord.class));
    } finally {
      connection.disconnect();
    }
  }

  public static List<IModel.PlayerAverages> fetchSeasonAverages() throws IOException {
    String endpoint = "/season_averages";
    HttpURLConnection connection = UrlConnection(endpoint);

    try {
      String jsonResponse = getUrlContents(connection);
      ObjectMapper mapper = new ObjectMapper();

      return mapper.readValue(jsonResponse, mapper.getTypeFactory().constructCollectionType(List.class, PlayerAverages.class));
    } finally {
      connection.disconnect();
    }
  }
}

