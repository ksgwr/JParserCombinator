package jp.ksgwr.parser.csv;

import java.io.Reader;
import java.util.List;

import jp.ksgwr.parser.Parser;
import jp.ksgwr.parser.flatten.DelimitedRecordParser;

/**
 * csv record parser
 * @author ksgwr
 *
 */
public class CSVRecordParser implements Parser<List<String>> {

	/** csv record parser */
	private static final DelimitedRecordParser RECORD_PARSER = new DelimitedRecordParser(',', new CSVFieldParser());

	@Override
	public List<String> parse(Reader in) throws Exception {
		return RECORD_PARSER.parse(in);
	}

}

