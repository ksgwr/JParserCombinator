package jp.ksgwr.parser.query;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.parser.Parser;

/**
 * PhraseTermParser PHRASE:"
 *
 * @author ksgwr
 *
 */
public class PhraseTermParser implements Parser<String> {

	/** object hashCode */
	private int hashCode;

	/**
	 * constructor
	 */
	public PhraseTermParser() {
		this.hashCode = this.hashCode();
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
			while ((c = in.read()) != -1) {
				if (c == '"') {
					isSuccess = true;
					break;
				} else {
					sb.append((char)c);
				}
			}
			if (isSuccess) {
				ret = sb.toString();
				in.clearMultiMark(hashCode);
			} else {
				in.resetMultiMark(hashCode);
			}
		} else {
			in.resetMultiMark(hashCode);
		}

		return ret;
	}

}
