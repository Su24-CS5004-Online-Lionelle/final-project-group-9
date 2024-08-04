package Model.Format;

import java.util.Collection;
import Model.IModel;
import Model.Player;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


public final class PlayerJSONWrapper {
  @JsonProperty("data")
  private Collection<IModel.PlayerBackground> data;

  /**
   * List of records
   */
  public PlayerJSONWrapper(Collection<IModel.PlayerBackground> players) {
    this.data = players;
  }
}


