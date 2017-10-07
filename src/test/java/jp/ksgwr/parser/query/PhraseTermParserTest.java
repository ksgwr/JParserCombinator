package jp.ksgwr.parser.query;

import static org.junit.Assert.*;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableStringReader;

import org.junit.Test;

public class PhraseTermParserTest {

	@Test
	public void parseTest1() throws IOException {
		MultiMarkableStringReader in = new MultiMarkableStringReader("\"a a\"b");
		PhraseTermParser parser = new PhraseTermParser();

		String content = parser.parse(in);

		assertEquals("a a", content);
	}

	@Test
	public void parseTest2() throws IOException {
		MultiMarkableStringReader in = new MultiMarkableStringReader("\"ab");
		PhraseTermParser parser = new PhraseTermParser();

		String content = parser.parse(in);

		assertNull(content);
	}

	@Test
	public void parseTest3() throws IOException {
		MultiMarkableStringReader in = new MultiMarkableStringReader("ab");
		PhraseTermParser parser = new PhraseTermParser();

		String content = parser.parse(in);

		assertNull(content);
	}
}
