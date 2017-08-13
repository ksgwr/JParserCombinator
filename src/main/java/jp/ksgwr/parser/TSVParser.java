package jp.ksgwr.parser;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import jp.ksgwr.parser.tsv.TSVRecordParser;

/**
 * TSV Parser
 *
 * tsv = 1 * (record CRLF)
 * record = field * (TAB field)
 * field = *TEXTDATA
 *
 * @author ksgwr
 *
 */
public class TSVParser implements Parser<Iterator<List<String>>>{

	/** TSV Record Parser */
	private static final TSVRecordParser RECORD = new TSVRecordParser();

	@Override
	public Iterator<List<String>> parse(Reader in) throws Exception {
		return new LineIterator<>(in, RECORD);
	}

}
