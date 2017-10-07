package jp.ksgwr.parser.query;

import static org.junit.Assert.*;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableStringReader;

import org.junit.Test;

public class NotPrefixParserTest {

	@Test
	public void parseTrueTest() throws IOException {
		MultiMarkableStringReader in = new MultiMarkableStringReader("-a");
		NotPrefixParser parser = new NotPrefixParser();

		Boolean actual = parser.parse(in);

		assertTrue(actual);

		char next = (char)in.read();

		assertEquals('a', next);
	}

	@Test
	public void parseFalseTest() throws IOException {
		MultiMarkableStringReader in = new MultiMarkableStringReader("a");
		NotPrefixParser parser = new NotPrefixParser();

		Boolean actual = parser.parse(in);

		assertFalse(actual);

		char next = (char)in.read();

		assertEquals('a', next);
	}
}
