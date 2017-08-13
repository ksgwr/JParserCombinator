package jp.ksgwr.parser.tsv;

import java.io.Reader;

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
	public Character parse(Reader in) throws Exception {
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
