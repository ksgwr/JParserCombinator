package jp.ksgwr.parser.csv;

import static org.junit.Assert.*;

import java.io.StringReader;

import jp.ksgwr.parser.Parser;

import org.junit.Test;

public class CSVNonEscapedParserTest {

	@Test
	public void parseTest() throws Exception {
		Parser<String> parser = new CSVNonEscapedParser();

		StringReader text;
		String actual;

		text = new StringReader("aa,");

		actual = parser.parse(text);
		assertEquals("aa", actual);

		actual = parser.parse(text);
		assertEquals("", actual);
	}

}
