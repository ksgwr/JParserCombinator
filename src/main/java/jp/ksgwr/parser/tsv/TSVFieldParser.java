package jp.ksgwr.parser.tsv;

import java.io.Reader;

import jp.ksgwr.parser.Parser;
import jp.ksgwr.parser.flatten.TextFieldParser;

/**
 * TSV Field Parser
 * @author ksgwr
 *
 */
public class TSVFieldParser implements Parser<String> {

	/** tab field parser */
	private static final TextFieldParser FIELD_PARSER = new TextFieldParser(new TSVTextDataParser());

	/**
	 * constructor
	 */
	public TSVFieldParser() {

	}

	@Override
	public String parse(Reader in) throws Exception {
		return FIELD_PARSER.parse(in);
	}

}
