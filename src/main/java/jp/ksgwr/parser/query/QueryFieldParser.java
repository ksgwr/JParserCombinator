package jp.ksgwr.parser.query;

import java.io.IOException;
import java.util.List;

import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.parser.Parser;

/**
 * QueryFieldParser
 *
 * @author ksgwr
 *
 */
public class QueryFieldParser implements Parser<Term> {

	/** SPACE parser */
	private static final SpaceParser SPACE = new SpaceParser();

	/** NOT PREFIX parser */
	private static final NotPrefixParser NOT_PREFIX = new NotPrefixParser();

	/** PHRASE parser */
	private static final PhraseTermParser PHRASE_TERM = new PhraseTermParser();

	/** CLAUSE parser */
	private static final ClauseTermParser CLAUSE_TERM = new ClauseTermParser();

	/** NORMAL TERM parser */
	private static final QueryNonEscapedTermParser NON_ESCAPED_TERM = new QueryNonEscapedTermParser();

	/** normal term parser */
	private Parser<String> normalTermParser;

	/** end check code */
	private int end;

	/** object hashCode */
	private int hashCode;

	/**
	 * constructor
	 */
	public QueryFieldParser() {
		this(NON_ESCAPED_TERM, -1);
	}

	/**
	 * constructor
	 * @param normalTermParser normal parser
	 * @param end end check code
	 */
	public QueryFieldParser(Parser<String> normalTermParser, int end) {
		this.hashCode = this.hashCode();
		this.normalTermParser = normalTermParser;
		this.end = end;
	}

	@Override
	public Term parse(MultiMarkableReader in) throws IOException {

		SPACE.parse(in);

		if (this.peek(in) == end) {
			return null;
		}

		in.addMultiMark(hashCode);
		Term term = new Term();
		term.not = NOT_PREFIX.parse(in);

		String content = PHRASE_TERM.parse(in);
		term.phrase = content != null;
		if (term.phrase) {
			term.content = content;
		} else {
			List<Term> attr = CLAUSE_TERM.parse(in);
			if (attr == null) {
				term.content = normalTermParser.parse(in);
			} else {
				term.attr = attr;
			}
		}

		if ("".equals(term.content)) {
			term = null;
		} else {
			term.surface = in.readToMultiMark(hashCode);
		}

		in.clearMultiMark(hashCode);

		SPACE.parse(in);

		return term;
	}

}
