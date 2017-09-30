package jp.ksgwr.parser.csv;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.List;

import jp.ksgwr.parser.Parser;

import org.junit.Test;

public class CSVRecordParserTest {

	@Test
	public void parseTest() throws Exception {
		Parser<List<String>> parser = new CSVRecordParser();

		StringReader text;
		List<String> actual;

		text = new StringReader("aa,\"b\"\"b\",cc");
		actual = parser.parse(text);

		assertThat(actual, contains("aa", "b\"b", "cc"));
	}

	@Test
	public void parseTest2() throws Exception {
		Parser<List<String>> parser = new CSVRecordParser();

		StringReader text = new StringReader("\"a\",\"b\"");
		List<String> actual = parser.parse(text, 3);

		assertThat(actual, contains("a", "b"));
	}
}
