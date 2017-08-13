package jp.ksgwr.parser.csv;

import java.io.Reader;

import jp.ksgwr.parser.Parser;

/**
 * csv escaped parser
 * @author ksgwr
 *
 */
public class CSVEscapedParser implements Parser<String> {

	/** csv char parser */
	private static final CSVTextDataParser TEXT_DATA = new CSVTextDataParser();

	/**
	 * constructor
	 */
	public CSVEscapedParser() {

	}

	@Override
	public String parse(Reader in) throws Exception {
		int c;
		String ret = null;
		in.mark(1);
		c = in.read();
		if (c == '"') {
			StringBuilder sb = new StringBuilder();
			in.mark(1);
			while ((c = in.read()) != -1) {
				if (c == ',' || c == '\r' || c == '\n') {
					sb.append((char)c);
				} else if (c == '"') {
					in.mark(1);
					c = in.read();
					if (c == '"') {
						sb.append((char)c);
					} else {
						// TODO: 最後まで読み進んで駄目だった時にロールバックができない
						// mark(buffer limit)を設定しないと駄目, markは複数付けれない
						in.reset();
						break;
					}
				} else {
					in.reset();
					Character tmpc = TEXT_DATA.parse(in);
					if (tmpc != null) {
						sb.append(tmpc);
					}
				}
				in.mark(1);
			}
			ret = sb.toString();
		} else {
			in.reset();
		}

		return ret;
	}

}
