package Model.Format;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import Model.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class DataFormatter {

  /**
   * Write the data as JSON.
   *
   * @param records the records to write
   * @param out the output stream to write to
   */
  private static <T> void writeJsonData(Collection<T> records, OutputStream out) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);

    try {
      mapper.writeValue(out, records);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Writes collection to output stream helper method.
   * @param mapper ObjectMapper
   * @param out OutputStream
   * @param records Collection of DNRecords
   */
  private static <T> void mapperWrtier(ObjectMapper mapper, OutputStream out, Collection<T> records) {
    try {
      mapper.writeValue(out, records);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Write the data in the specified format.
   *
   * @param records generic collections for the records to write
   * @param format the format to write the records in
   * @param out the output stream to write to
   */
  public static <T> void write(@Nonnull Collection<T> records, @Nonnull Format format,
                               @Nonnull OutputStream out) {

    writeJsonData(records, out);
  }


}
