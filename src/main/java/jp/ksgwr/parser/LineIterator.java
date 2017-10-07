package jp.ksgwr.parser;

import java.io.IOException;
import java.util.Iterator;

import jp.ksgwr.io.MultiMarkableReader;

/**
 * Line Iterator for Input Stream
 *
 * @author ksgwr
 *
 * @param <T> parse result
 */
public class LineIterator<T> implements Iterator<T> {

	/** input stream */
	private MultiMarkableReader in;

	/** line parser */
	private Parser<T> parser;

	/**
	 * constructor
	 * @param in input stream
	 * @param parser line parser
	 */
	public LineIterator(MultiMarkableReader in, Parser<T> parser) {
		this.in = in;
		this.parser = parser;
	}

	@Override
	public boolean hasNext() {
		try {
			if (parser.peek(in) != -1) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public T next() {
		try {
			T fields = parser.parse(in);
			in.mark(2);
			int c = in.read();
			if (c == '\r') {
				c = in.read();
				if (c == '\n') {

				} else {
					in.reset();
				}
			} else if(c == '\n') {

			}
			return fields;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
