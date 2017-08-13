package jp.ksgwr.parser.tsv;

import java.io.Reader;
import java.util.List;

import jp.ksgwr.parser.Parser;
import jp.ksgwr.parser.flatten.DelimitedRecordParser;

/**
 * TSV Fields Parser
 * @author ksgwr
 *
 */
public class TSVRecordParser implements Parser<List<String>> {

	/** tab delimited record parser */
	private static final DelimitedRecordParser RECORD_PARSER = new DelimitedRecordParser('\t', new TSVFieldParser());

	/**
	 * constructor
	 */
	public TSVRecordParser() {
	}

	@Override
	public List<String> parse(Reader in) throws Exception {
		return RECORD_PARSER.parse(in);
	}

}
