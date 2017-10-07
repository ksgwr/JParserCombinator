package jp.ksgwr.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.parser.query.QueryFieldParser;
import jp.ksgwr.parser.query.Term;

/**
 * Query Parser
 *
 * query = field * (SPACE field)
 * field = (NOT) (clause / phrase / term)
 * clause = LP term +(SPACE term) RP
 * phrase = DQUOTE term * (SPACE term) DQUOTE
 * term = *TEXTDATA
 *
 * @author ksgwr
 *
 */
public class QueryParser implements Parser<List<Term>> {

	/** QueryFieldParser instance */
	private static final QueryFieldParser QUERY_FIELD = new QueryFieldParser();

	public QueryParser() {

	}

	@Override
	public List<Term> parse(MultiMarkableReader in) throws IOException {
		List<Term> terms = new ArrayList<Term>();
		Term term;
		int position = 0;
		while((term = QUERY_FIELD.parse(in)) != null) {
			term.position = position++;
			terms.add(term);
		}
		return terms;
	}

}
