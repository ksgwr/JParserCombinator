package jp.ksgwr.parser.csv;

import static org.junit.Assert.*;

import java.io.StringReader;

import jp.ksgwr.parser.Parser;

import org.junit.Test;

public class CSVFieldParserTest {

	@Test
	public void parseTest() throws Exception {
		Parser<String> parser = new CSVFieldParser();

		StringReader text;
		String actual;

		text = new StringReader("aaa");
		actual = parser.parse(text);
		assertEquals("aaa", actual);

		text = new StringReader("\"b\"\"c");
		actual = parser.parse(text);
		assertEquals("\"b\"\"c", actual);

	}
}
