package jp.ksgwr.parser.csv;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;

import jp.ksgwr.io.MultiMarkableStringReader;
import jp.ksgwr.parser.Parser;

import org.junit.Test;

public class CSVTextDataParserTest {

	@Test
	public void parseTest() throws Exception {
		Parser<Character> parser = new CSVTextDataParser();

		MultiMarkableStringReader text = new MultiMarkableStringReader("a,b");
		Character actual;

		actual = parser.parse(text);
		assertEquals("a", actual.toString());

		actual = parser.parse(text);
		assertNull(actual);
		text.read();

		actual = parser.parse(text);
		assertEquals("b", actual.toString());
	}

	@Test
	public void parseTest2() throws Exception {
		Parser<Character> parser = new CSVTextDataParser();

		Reader input = new BufferedReader(new StringReader("a,b"), 5);
		Character actual;

		actual = parser.parse(input);
		assertEquals("a", actual.toString());
	}
}
