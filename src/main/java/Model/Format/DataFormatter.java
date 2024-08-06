package Model.Format;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import Model.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

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
   * Pretty print the data in a human readable format.
   *
   * @param records the records to print
   * @param out the output stream to write to
   */
  private static void prettyPrint(Collection<Player> records, OutputStream out) {
    PrintStream pout = new PrintStream(out);  // so i can use println
    for (Player record : records) {
      prettySingle(record, pout);
      pout.println();
    }
    pout.flush(); // flush all contents to stream
  }

  /**
   * Pretty print a single record.
   *
   * Let this as an example, so you didn't have to worry about spacing.
   *
   * @param record the record to print
   * @param out the output stream to write to
   */
  private static void prettySingle(@Nonnull Player record, @Nonnull PrintStream out) {
    out.println(record.toString());
  }

  /**
   * Write the data as XML.
   *
   * @param records the records to write
   * @param out the output stream to write to
   */
  private static void writeXmlData(Collection<Player> records, OutputStream out) {

    // Use DomainXmlWrapper in try catch statement
    try {
      ObjectMapper mapper = new XmlMapper();
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      PlayerXMLWrapper wrapper = new PlayerXMLWrapper(records);
      mapper.writeValue(out, wrapper);
    } catch (Exception e) {
      throw new RuntimeException("Writing to XML failed.");
    }
  }



  /**
   * Write the data as CSV.
   *
   * @param records the records to write
   * @param out the output stream to write to
   */
  private static <T> void writeCSVData(Collection<Player> records, OutputStream out) {
    // Initialize object mapper that will connect format to record
    CsvMapper mapper = new CsvMapper();

    // Configure CsvSchema to define header structure for CSV file
    // .withHeader() includes header in file
    CsvSchema schema = mapper.schemaFor(records.getClass()).withHeader();

    // Write data in try-catch
    try {
      mapper.writer(schema).writeValue(out, records);
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
  public static void write(@Nonnull Collection<Player> records, @Nonnull Format format,
                           @Nonnull OutputStream out) {
    switch (format) {
      case XML:
        writeXmlData(records, out);
        break;
      case JSON:
        writeJsonData(records, out);
        break;
      case CSV:
        writeCSVData(records, out);
        break;
      default:
        prettyPrint(records, out);
    }
  }
}
