package jp.ksgwr.parser.tsv;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.List;

import jp.ksgwr.parser.Parser;

import org.junit.Test;

public class TSVRecordParserTest {

	@Test
	public void parseTest() throws Exception {
		Parser<List<String>> parser = new TSVRecordParser();

		StringReader text;
		List<String> actual;

		text = new StringReader("aa\tbb\tcc");
		actual = parser.parse(text);

		assertThat(actual, contains("aa", "bb", "cc"));
	}
}
