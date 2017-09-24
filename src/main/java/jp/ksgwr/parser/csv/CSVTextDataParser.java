package jp.ksgwr.parser.csv;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.parser.Parser;

/**
 * CSV Character Parser
 * @author ksgwr
 *
 */
public class CSVTextDataParser implements Parser<Character> {

	/**
	 * constructor
	 */
	public CSVTextDataParser() {

	}

	@Override
	public Character parse(MultiMarkableReader in) throws IOException {
		int c;
		in.mark(1);
		c = in.read();
		switch (c) {
		case ',':
		case '\r':
		case '\n':
			in.reset();
			return null;
		default:
			return (char) c;
		}
	}

}
