package jp.ksgwr.parser.flatten;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import jp.ksgwr.parser.Parser;

/**
 * common delimited fields parser
 * @author ksgwr
 *
 */
public class DelimitedRecordParser implements Parser<List<String>> {

	/** field delimiter */
	private final char delimiter;

	/** field parser */
	private final Parser<String> fieldParser;

	/**
	 * constructor
	 * @param delimiter field delimiter
	 * @param fieldParser field parser
	 */
	public DelimitedRecordParser(char delimiter, Parser<String> fieldParser) {
		this.delimiter = delimiter;
		this.fieldParser = fieldParser;
	}

	@Override
	public List<String> parse(Reader in) throws Exception {
		List<String> fields = new ArrayList<String>();
		int c;
		fields.add(fieldParser.parse(in));
		in.mark(1);
		while ((c = in.read()) != -1) {
			if (c == delimiter) {
				String s = fieldParser.parse(in);
				if (s == null) {
					break;
				} else {
					fields.add(s);
				}
				in.mark(1);
			} else {
				in.reset();
				break;
			}
		}
		return fields;
	}

}
