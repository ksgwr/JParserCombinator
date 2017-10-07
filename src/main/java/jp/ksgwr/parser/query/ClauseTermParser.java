package jp.ksgwr.parser.query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.parser.Parser;

/**
 * Clause Term Paser LP:(, RP:)
 *
 * @author ksgwr
 *
 */
public class ClauseTermParser implements Parser<List<Term>> {

	/** QueryFieldParser */
	private static final QueryFieldParser QUERY_FIELD = new QueryFieldParser(new QueryClauseEscapedTermParser(), ')');

	/** object hashCode */
	private int hashCode;

	/**
	 * constructor
	 */
	public ClauseTermParser() {
		this.hashCode = this.hashCode();
	}

	@Override
	public List<Term> parse(MultiMarkableReader in) throws IOException {
		int c;
		List<Term> ret = null;
		in.addMultiMark(hashCode);
		c = in.read();
		if (c == '(') {
			ret = new ArrayList<Term>();
			Term term;
			int position = 0;
			while((term=QUERY_FIELD.parse(in)) != null) {
				term.position = position++;
				ret.add(term);
			}
			if ((c = in.read()) == ')') {
				in.clearMultiMark(hashCode);
			} else {
				ret = null;
				in.resetMultiMark(hashCode);
			}
		} else {
			in.resetMultiMark(hashCode);
		}
		return ret;
	}

}
