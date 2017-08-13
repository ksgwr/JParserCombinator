package jp.ksgwr.parser.csv;

import static org.junit.Assert.*;

import java.io.StringReader;

import jp.ksgwr.parser.Parser;

import org.junit.Test;

public class CSVTextDataParserTest {

	@Test
	public void parseTest() throws Exception {
		Parser<Character> parser = new CSVTextDataParser();

		StringReader text = new StringReader("a,b");
		Character actual;

		actual = parser.parse(text);
		assertEquals("a", actual.toString());

		actual = parser.parse(text);
		assertNull(actual);
		text.read();

		actual = parser.parse(text);
		assertEquals("b", actual.toString());
	}
}
