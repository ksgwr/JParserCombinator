package jp.ksgwr.parser.tsv;

import static org.junit.Assert.*;
import jp.ksgwr.io.MultiMarkableStringReader;
import jp.ksgwr.parser.Parser;

import org.junit.Test;

public class TSVTextDataParserTest {

	@Test
	public void parseTest() throws Exception {
		Parser<Character> parser = new TSVTextDataParser();

		MultiMarkableStringReader text = new MultiMarkableStringReader("a\tb");
		Character actual;

		actual = parser.parse(text);
		assertEquals("a", actual.toString());

		actual = parser.parse(text);
		assertNull(actual);
		text.read();

		actual = parser.parse(text);
		assertEquals("b", actual.toString());
	}
}
