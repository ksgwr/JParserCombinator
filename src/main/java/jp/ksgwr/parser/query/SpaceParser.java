package jp.ksgwr.parser.query;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.parser.Parser;

/**
 * SpaceParser
 *
 * @author ksgwr
 *
 */
public class SpaceParser implements Parser<String> {

	@Override
	public String parse(MultiMarkableReader in) throws IOException {
		StringBuilder sb = null;
		int c;
		while ((c = this.markAndRead(in)) != -1) {
			if (c == ' ') {
				if (sb == null) {
					sb = new StringBuilder();
				}
				sb.append((char)c);
			} else {
				in.reset();
				break;
			}
		}
		if (sb == null) {
			return null;
		} else {
			return sb.toString();
		}
	}

}
