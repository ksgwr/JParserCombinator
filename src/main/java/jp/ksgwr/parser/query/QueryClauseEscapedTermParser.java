package jp.ksgwr.parser.query;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.parser.Parser;

/**
 * QueryClauseEscapedTermParser ^)
 *
 * @author ksgwr
 *
 */
public class QueryClauseEscapedTermParser implements Parser<String> {

	/**
	 * constructor
	 */
	public QueryClauseEscapedTermParser() {

	}

	@Override
	public String parse(MultiMarkableReader in) throws IOException {
		StringBuilder sb = new StringBuilder();
		int c;
		while ((c = this.markAndRead(in)) != -1) {
			if (c == ' ' || c == ')') {
				in.reset();
				break;
			} else {
				sb.append((char)c);
			}
		}
		return sb.toString();
	}

}
