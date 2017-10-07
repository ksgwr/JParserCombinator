package jp.ksgwr.parser.query;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import jp.ksgwr.io.MultiMarkableStringReader;

import org.junit.Test;

public class ClauseTermParserTest {

	@Test
	public void parseTest() throws IOException {
		MultiMarkableStringReader in = new MultiMarkableStringReader("(a b)");
		ClauseTermParser parser = new ClauseTermParser();

		List<Term> terms = parser.parse(in);

		Term term;
		assertEquals(2, terms.size());
		term = terms.get(0);
		assertEquals("a", term.content);
		assertEquals("a", term.surface);
		assertFalse(term.not);
		assertFalse(term.phrase);
		assertEquals(0, term.position);
		term = terms.get(1);
		assertEquals("b", term.content);
		assertEquals("b", term.surface);
		assertFalse(term.not);
		assertFalse(term.phrase);
		assertEquals(1, term.position);
	}

	@Test
	public void parseTest2() throws IOException {
		MultiMarkableStringReader in = new MultiMarkableStringReader("(a b");
		ClauseTermParser parser = new ClauseTermParser();

		List<Term> terms = parser.parse(in);

		assertNull(terms);

	}
}
