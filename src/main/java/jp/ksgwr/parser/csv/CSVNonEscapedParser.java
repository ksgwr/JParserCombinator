package jp.ksgwr.parser.csv;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.parser.Parser;

/**
 * csv non escaped parser
 * @author ksgwr
 *
 */
public class CSVNonEscapedParser implements Parser<String>  {

	/** csv field parser */
	private static final CSVTextDataParser TEXT_DATA = new CSVTextDataParser();

	@Override
	public String parse(MultiMarkableReader in) throws IOException {
		StringBuilder sb = new StringBuilder();
		while (this.peek(in) != -1) {
			Character tmpc = TEXT_DATA.parse(in);
			if (tmpc == null) {
				break;
			} else {
				sb.append(tmpc);
			}
		}
		return sb.toString();
	}

}
