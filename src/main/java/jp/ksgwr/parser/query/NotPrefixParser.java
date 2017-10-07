package jp.ksgwr.parser.query;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.parser.Parser;

/**
 * Not Prefix Parser NOT:-
 *
 * @author ksgwr
 *
 */
public class NotPrefixParser implements Parser<Boolean> {

	/**
	 * constructor
	 */
	public NotPrefixParser() {

	}

	@Override
	public Boolean parse(MultiMarkableReader in) throws IOException {
		boolean isNot = false;
		in.mark(1);
		int c = in.read();
		if (c == '-') {
			isNot = true;
		} else {
			in.reset();
		}
		return isNot;
	}

}
