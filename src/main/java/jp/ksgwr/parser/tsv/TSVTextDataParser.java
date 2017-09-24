package jp.ksgwr.parser.tsv;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.parser.Parser;

/**
 * TSV Character Parser
 * @author ksgwr
 *
 */
public class TSVTextDataParser implements Parser<Character> {

	/**
	 * constructor
	 */
	public TSVTextDataParser() {

	}

	@Override
	public Character parse(MultiMarkableReader in) throws IOException {
		int c;
		in.mark(1);
		c = in.read();
		switch (c) {
		case '\t':
		case '\r':
		case '\n':
			in.reset();
			return null;
		default:
			return (char) c;
		}
	}
}
