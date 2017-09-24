package jp.ksgwr.parser.flatten;

import java.io.IOException;

import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.parser.Parser;

/**
 * common text field parser
 * @author ksgwr
 *
 */
public class TextFieldParser implements Parser<String> {

	/** charcter parser */
	private final Parser<Character> charParser;

	/**
	 * constructor
	 * @param charParser character parser
	 */
	public TextFieldParser(Parser<Character> charParser) {
		this.charParser = charParser;
	}

	@Override
	public String parse(MultiMarkableReader in) throws IOException {
		StringBuilder sb = new StringBuilder();
		while (this.peek(in) != -1) {
			Character tmpc = charParser.parse(in);
			if (tmpc == null) {
				break;
			} else {
				sb.append(tmpc);
			}
		}
		return sb.toString();
	}

}
