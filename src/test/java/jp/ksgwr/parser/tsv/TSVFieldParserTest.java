package jp.ksgwr.parser.tsv;

import static org.junit.Assert.*;

import java.io.StringReader;

import jp.ksgwr.parser.Parser;

import org.junit.Test;

public class TSVFieldParserTest {

	@Test
	public void parseTest() throws Exception {
		Parser<String> parser = new TSVFieldParser();

		StringReader text;
		String actual;

		text = new StringReader("aaa");
		actual = parser.parse(text);
		assertEquals("aaa", actual);

	}
}
