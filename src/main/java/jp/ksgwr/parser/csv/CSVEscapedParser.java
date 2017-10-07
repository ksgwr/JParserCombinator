package jp.ksgwr.parser.csv;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.parser.Parser;

/**
 * csv escaped parser
 * @author ksgwr
 *
 */
public class CSVEscapedParser implements Parser<String> {

	/** csv char parser */
	private static final CSVTextDataParser TEXT_DATA = new CSVTextDataParser();

	/** object hashCode */
	private int hashCode;

	/**
	 * constructor
	 */
	public CSVEscapedParser() {
		this.hashCode = hashCode();
	}

	@Override
	public String parse(MultiMarkableReader in) throws IOException {
		int c;
		String ret = null;
		in.addMultiMark(hashCode);
		c = in.read();
		if (c == '"') {
			boolean isSuccess = false;
			StringBuilder sb = new StringBuilder();
			while ((c = this.markAndRead(in)) != -1) {
				if (c == ',' || c == '\r' || c == '\n') {
					sb.append((char)c);
				} else if (c == '"') {
					c = this.markAndRead(in);
					if (c == '"') {
						sb.append((char)c);
					} else {
						in.reset();
						isSuccess = true;
						break;
					}
				} else {
					in.reset();
					Character tmpc = TEXT_DATA.parse(in);
					if (tmpc != null) {
						sb.append(tmpc);
					}
				}
			}
			if (isSuccess) {
				ret = sb.toString();
			} else {
				in.resetMultiMark(hashCode);
			}
		} else {
			in.resetMultiMark(hashCode);
		}
		in.clearMultiMark(hashCode);
		return ret;
	}

}
