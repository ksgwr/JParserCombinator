package jp.ksgwr.io;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class MultiMarkableStringReaderTest {

	@Test
	public void multiMarkTest() throws IOException {
		MultiMarkableStringReader reader = new MultiMarkableStringReader("abcd");
		int c;

		reader.addMultiMark(0);
		c = reader.read();
		assertEquals('a', c);

		reader.addMultiMark(1);
		c = reader.read();
		assertEquals('b', c);

		reader.addMultiMark(2);
		c = reader.read();
		assertEquals('c', c);

		reader.resetMultiMark(0);
		c = reader.read();
		assertEquals('a', c);

		c = reader.read();
		assertEquals('b', c);

		reader.clearMultiMark(2);
		try {
			reader.resetMultiMark(2);
			fail();
		} catch (NullPointerException e) {

		}

		reader.resetMultiMark(1);
		c = reader.read();
		assertEquals('b', c);

		c = reader.read();
		assertEquals('c', c);

		c = reader.read();
		assertEquals('d', c);

		c = reader.read();
		assertEquals(-1, c);

		reader.close();
	}
}
