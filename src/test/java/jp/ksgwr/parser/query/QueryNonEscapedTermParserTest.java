package jp.ksgwr.parser.query;

import static org.junit.Assert.*;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableStringReader;

import org.junit.Test;

public class QueryNonEscapedTermParserTest {

	@Test
	public void parseTest() throws IOException {
		MultiMarkableStringReader in = new MultiMarkableStringReader("ab cd");
		QueryNonEscapedTermParser parser = new QueryNonEscapedTermParser();

		String content = parser.parse(in);

		assertEquals("ab", content);

		char next = (char) in.read();

		assertEquals(' ', next);
	}
}
