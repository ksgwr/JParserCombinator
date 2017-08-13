package jp.ksgwr.parser.csv;

import java.io.Reader;

import jp.ksgwr.parser.Parser;

/**
 * csv field parser
 * @author ksgwr
 *
 */
public class CSVFieldParser implements Parser<String> {

	/** csv escaped field parser */
	private static final CSVEscapedParser ESCAPED = new CSVEscapedParser();

	/** csv non escaped field parser */
	private static final CSVNonEscapedParser NON_ESCAPED = new CSVNonEscapedParser();

	@Override
	public String parse(Reader in) throws Exception {
		return ESCAPED.or(NON_ESCAPED).parse(in);
	}

}
