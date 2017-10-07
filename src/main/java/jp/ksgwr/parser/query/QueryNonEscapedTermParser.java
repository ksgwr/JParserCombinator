package jp.ksgwr.parser.query;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.parser.Parser;

/**
 * QueryNonEscapedTermParser ^
 *
 * @author ksgwr
 *
 */
public class QueryNonEscapedTermParser implements Parser<String> {

	/**
	 * constructor
	 */
	public QueryNonEscapedTermParser() {

	}

	@Override
	public String parse(MultiMarkableReader in) throws IOException {
		StringBuilder sb = new StringBuilder();
		int c;
		while ((c = this.markAndRead(in)) != -1) {
			if (c == ' ') {
				in.reset();
				break;
			} else {
				sb.append((char)c);
			}
		}
		return sb.toString();
	}

}
