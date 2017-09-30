package jp.ksgwr.io;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class MultiMarkableBufferedReaderTest {

	@Test
	public void readTest() throws IOException {
		StringReader internalReader = new StringReader("abcdef");
		MultiMarkableBufferedReader reader = new MultiMarkableBufferedReader(internalReader, 3);
		int c;

		c = reader.read();
		assertEquals('a', c);

		c = reader.read();
		assertEquals('b', c);

		c = reader.read();
		assertEquals('c', c);

		c = reader.read();
		assertEquals('d', c);

		c = reader.read();
		assertEquals('e', c);

		c = reader.read();
		assertEquals('f', c);

		c = reader.read();
		assertEquals(-1, c);

		reader.close();
	}

	@Test
	public void readTest2() throws IOException {
		StringReader internalReader = new StringReader("abcdef");
		MultiMarkableBufferedReader reader = new MultiMarkableBufferedReader(internalReader, 3);
		int c;

		reader.addMultiMark(0);

		c = reader.read();
		assertEquals('a', c);

		reader.addMultiMark(1);

		c = reader.read();
		assertEquals('b', c);

		c = reader.read();
		assertEquals('c', c);

		reader.mark(10);

		c = reader.read();
		assertEquals('d', c);

		c = reader.read();
		assertEquals('e', c);

		reader.resetMultiMark(0);

		c = reader.read();
		assertEquals('a', c);

		c = reader.read();
		assertEquals('b', c);

		c = reader.read();
		assertEquals('c', c);

		c = reader.read();
		assertEquals('d', c);

		c = reader.read();
		assertEquals('e', c);

		c = reader.read();
		assertEquals('f', c);

		c = reader.read();
		assertEquals(-1, c);

		reader.close();
	}

	@Test
	public void readTest3() throws IOException {
		StringReader internalReader = new StringReader("abcdef");
		MultiMarkableBufferedReader reader = new MultiMarkableBufferedReader(internalReader, 3);
		int c;

		reader.mark(2);

		c = reader.read();
		assertEquals('a', c);

		reader.reset();

		c = reader.read();
		assertEquals('a', c);

		c = reader.read();
		assertEquals('b', c);

		c = reader.read();
		assertEquals('c', c);

		c = reader.read();
		assertEquals('d', c);

		c = reader.read();
		assertEquals('e', c);

		c = reader.read();
		assertEquals('f', c);

		c = reader.read();
		assertEquals(-1, c);

		reader.close();
	}

	@Test
	public void readTest4() throws IOException {
		StringReader internalReader = new StringReader("abcdef");
		MultiMarkableBufferedReader reader = new MultiMarkableBufferedReader(internalReader, 3);
		int c;

		c = reader.read();
		assertEquals('a', c);

		c = reader.read();
		assertEquals('b', c);

		c = reader.read();
		assertEquals('c', c);

		reader.mark(1);

		c = reader.read();
		assertEquals('d', c);

		reader.reset();

		c = reader.read();
		assertEquals('d', c);

		c = reader.read();
		assertEquals('e', c);

		c = reader.read();
		assertEquals('f', c);

		c = reader.read();
		assertEquals(-1, c);

		reader.close();
	}

	@Test
	public void readTest5() throws IOException {
		StringReader internalReader = new StringReader("abcde");
		MultiMarkableBufferedReader reader = new MultiMarkableBufferedReader(internalReader, 2);
		int c;

		reader.addMultiMark(0);

		c = reader.read();
		assertEquals('a', c);

		reader.mark(1);

		c = reader.read();
		assertEquals('b', c);

		reader.mark(1);

		c = reader.read();
		assertEquals('c', c);

		reader.mark(1);

		c = reader.read();
		assertEquals('d', c);

		reader.reset();

		reader.mark(1);

		c = reader.read();
		assertEquals('d', c);

		reader.mark(1);

		c = reader.read();
		assertEquals('e', c);


		reader.clearMultiMark(0);

		reader.close();
	}
}
