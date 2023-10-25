package org.apache.commons.csv;
import org.junit.Test;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Stream;
import org.junit.Assert;

public class CSVPrinterTest {
    @Test
    public void testCRComment() throws IOException {
        final StringWriter sw = new StringWriter();
        final Object value = "abc";
        try (final CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT.withCommentMarker('#'))) {
            printer.print(value);
            printer.printComment("This is a comment\r\non multiple lines\rthis is next comment\r");

        }
    }
    @Test
    public void testCSV259() throws IOException {
        final StringWriter sw = new StringWriter();
        try (final Reader reader = new FileReader("src/test/resources/org/apache/commons/csv/CSV-259/sample.txt");
             final CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT.withEscape('!').withQuote(null))) {
            printer.print(reader);

        }
    }
    @Test
    public void testDelimeterQuoted() throws IOException {
        final StringWriter sw = new StringWriter();
        try (final CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT.withQuote('\''))) {
            printer.print("a,b,c");
            printer.print("xyz");

        }
    }
    @Test
    public void testCarriageReturnEndings() throws IOException {
        final String code = "foo\rbaar,\rhello,world\r,kanu";
        try (final CSVParser parser = CSVParser.parse(code, CSVFormat.DEFAULT)) {
            final List<CSVRecord> records = parser.getRecords();
            Assert.assertEquals(4, records.size());
        }
    }




}
