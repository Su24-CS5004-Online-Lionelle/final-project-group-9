package Model.Format;

import java.util.Collection;
import Model.Player;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * This wrapper helps when using Jackson to serialize a list of domain records to xml. Without this,
 * it tries to use <ArrayList> and <item> tags instead of <domainList> and <domain> tags.
 *
 * Suggested use (note you need try/catch with this)
 *
 * <pre>
 * XmlMapper mapper = new XmlMapper();
 * mapper.enable(SerializationFeature.INDENT_OUTPUT);
 * DomainXmlWrapper wrapper = new DomainXmlWrapper(records);
 * mapper.writeValue(out, wrapper);
 * </pre>
 */
@JacksonXmlRootElement(localName = "data") // specifies that root element in xml will be <domainList>
public final class PlayerXMLWrapper {

  /** List of the records. */
  @JacksonXmlElementWrapper(useWrapping = false)
  private Collection<Player> domain;

  /**
   * Constructor.
   *
   * @param records the records to wrap
   */
  public PlayerXMLWrapper(Collection<Player> records) {
    this.domain = records;

  }
}


