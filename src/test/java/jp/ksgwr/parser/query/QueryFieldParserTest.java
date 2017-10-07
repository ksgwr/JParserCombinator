package jp.ksgwr.parser.query;

import static org.junit.Assert.*;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableStringReader;

import org.junit.Test;

public class QueryFieldParserTest {

	@Test
	public void parseTest() throws IOException {
		MultiMarkableStringReader in = new MultiMarkableStringReader(" -ab ");
		QueryFieldParser parser = new QueryFieldParser();

		Term term = parser.parse(in);

		assertNotNull(term);
		assertEquals("ab", term.content);
		assertEquals("-ab", term.surface);
		assertTrue(term.not);
		assertFalse(term.phrase);
	}
}
