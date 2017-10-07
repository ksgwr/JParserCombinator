package jp.ksgwr.parser;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import jp.ksgwr.io.MultiMarkableStringReader;
import jp.ksgwr.parser.query.Term;

import org.junit.Test;

public class QueryParserTest {

	@Test
	public void parseTest() throws IOException {
		MultiMarkableStringReader in = new MultiMarkableStringReader("a \"b\" -c (d e)");
		QueryParser parser = new QueryParser();

		List<Term> terms = parser.parse(in);

		Term term;
		assertEquals(4, terms.size());
		term = terms.get(0);
		assertEquals("a", term.content);
		assertEquals("a", term.surface);
		assertFalse(term.not);
		assertFalse(term.phrase);
		assertEquals(0, term.position);
		term = terms.get(1);
		assertEquals("b", term.content);
		assertEquals("\"b\"", term.surface);
		assertFalse(term.not);
		assertTrue(term.phrase);
		assertEquals(1, term.position);
		term = terms.get(2);
		assertEquals("c", term.content);
		assertEquals("-c", term.surface);
		assertTrue(term.not);
		assertFalse(term.phrase);
		assertEquals(2, term.position);
		term = terms.get(3);
		assertNull(term.content);
		assertEquals("(d e)", term.surface);
		assertFalse(term.not);
		assertFalse(term.phrase);
		assertEquals(3, term.position);
	}
}
