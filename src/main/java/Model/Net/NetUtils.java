package Model.Net;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import Model.Format.Format;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.stream.Collectors;

import Model.Format.Format;
public final class NetUtils {

  private static final String SEASON_AVERAGES_URL = "https://api.balldontlie.io/v1/season_averages";
  private static final String PLAYERS_URL = "https://api.balldontlie.io/v1/players";
  private static final String API_KEY = "d8754d2a-af6d-42ab-a674-d3ab4d504ad9";


  private NetUtils() {
    // Prevent instantiation
  }

  /**
   * Fetches the season averages data from the API.
   *
   * @return the response data as a String
   * @throws Exception if there is an error during the request
   */
  public static String fetchSeasonAverages() throws Exception {
    return getUrlContents(SEASON_AVERAGES_URL);
  }

  /**
   * Fetches the players data from the API.
   *
   * @return the response data as a String
   * @throws Exception if there is an error during the request
   */
  public static String fetchPlayers() throws Exception {
    return getUrlContents(PLAYERS_URL, API_KEY);
  }

  /**
   * Gets the contents of a URL as a String.
   *
   * @param urlStr the URL to get the contents of
   * @return the contents of the URL as a String
   * @throws Exception if there is an error during the request
   */
  private static String getUrlContents(String urlStr) throws Exception {
    return getUrlContents(urlStr, null);
  }




  /**
   * Gets the contents of a URL as a String with optional API key.
   *
   * @param urlStr the URL to get the contents of
   * @param apiKey the API key to include in the request header (can be null)
   * @return the contents of the URL as a String
   * @throws Exception if there is an error during the request
   */
  private static String getUrlContents(String urlStr, String apiKey) throws Exception {
    URL url = new URL(urlStr);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    if (apiKey != null) {
      con.setRequestProperty("Authorization", "Bearer " + apiKey);
    }
    con.setConnectTimeout(5000);
    con.setReadTimeout(5000);
    con.setRequestProperty("User-Agent", "Mozilla/5.0");

    int status = con.getResponseCode();
    if (status == 200) {
      try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
        return in.lines().collect(Collectors.joining());
      }
    } else {
      throw new Exception("Request failed with response code: " + status);
    }
  }
}