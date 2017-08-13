package jp.ksgwr.parser.csv;

import static org.junit.Assert.*;

import java.io.StringReader;

import jp.ksgwr.parser.Parser;

import org.junit.Test;

public class CSVEscapedParserTest {

	@Test
	public void parseTest() throws Exception {
		Parser<String> parser = new CSVEscapedParser();

		StringReader text;
		String actual;

		text = new StringReader("\"a\"\"a\"");

		actual = parser.parse(text);
		assertEquals("a\"a", actual.toString());

		text = new StringReader("bb");

		actual = parser.parse(text);
		assertNull(actual);

	}
}
