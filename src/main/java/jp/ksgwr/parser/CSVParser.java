package jp.ksgwr.parser;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.parser.csv.CSVRecordParser;

/**
 * CSV Parser
 *
 * BNF (RFC 4180)
 * csv = 1 * (record CRLF)
 * record = field * (COMMA field)
 * field = (escaped / non-escaped)
 * escaped = DQUOTE * (TEXTDATA / COMMA / CR / LF / 2DQUOTE) DQUOTE
 * non-escaped = *TEXTDATA
 *
 * @author ksgwr
 *
 */
public class CSVParser implements Parser<Iterator<List<String>>> {

	/** CSV Record parser */
	private static final CSVRecordParser RECORD = new CSVRecordParser();

	@Override
	public Iterator<List<String>> parse(MultiMarkableReader in) throws IOException {
		return new LineIterator<>(in, RECORD);
	}
}
