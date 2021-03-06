package jp.ksgwr.parser;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class TSVParserTest {

	@Test
	public void parseTest() throws Exception {
		Parser<Iterator<List<String>>> parser = new TSVParser();

		String text = "abc\tdef\tghi\r\n\"aa\"\tbb\tcc";
		Iterator<List<String>> iterator = parser.parse(text);

		assertTrue(iterator.hasNext());
		assertThat(iterator.next(), contains("abc", "def", "ghi"));
		assertThat(iterator.next(), contains("\"aa\"", "bb", "cc"));
		assertFalse(iterator.hasNext());

		// TODO: 異常値テスト
	}
}
