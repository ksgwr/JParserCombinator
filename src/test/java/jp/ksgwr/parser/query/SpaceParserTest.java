package jp.ksgwr.parser.query;

import static org.junit.Assert.*;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableStringReader;

import org.junit.Test;

public class SpaceParserTest {

	@Test
	public void parseTest() throws IOException {
		MultiMarkableStringReader in = new MultiMarkableStringReader("   a");
		SpaceParser parser = new SpaceParser();

		String actual = parser.parse(in);

		assertEquals("   ", actual);

		actual = parser.parse(in);

		assertNull(actual);

		char next = (char)in.read();

		assertEquals('a', next);

	}
}
