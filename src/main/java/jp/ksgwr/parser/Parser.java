package jp.ksgwr.parser;
import java.io.IOException;
import java.io.Reader;

import jp.ksgwr.io.MultiMarkableBufferedReader;
import jp.ksgwr.io.MultiMarkableReader;
import jp.ksgwr.io.MultiMarkableStringReader;

/**
 * Parser Interface
 *
 * @author ksgwr
 *
 * @param <T> parse result
 */
@FunctionalInterface
public interface Parser<T> {

	/**
     * parse stream
     * @param in input stream
     * @return parse result
     * @throws Exception parser exception
     */
    T parse(MultiMarkableReader in) throws IOException;

	/**
	 * parse string
	 * @param s string
	 * @return parse result
	 * @throws ParserException parser exception
	 */
    default T parse(String s) throws IOException {
    	return parse(new MultiMarkableStringReader(s));
    }

    default T parse(Reader in) throws IOException {
    	return parse(new MultiMarkableBufferedReader(in));
    }

    default T parse(Reader in, int bufferSize) throws IOException {
    	return parse(new MultiMarkableBufferedReader(in, bufferSize));
    }

    /**
     * parse combinator
     * @param p parser
     * @return combination parser
     */
    default Parser<T> or(Parser<T> p) {
        return s -> {
            T ret = parse(s);
            if (ret == null) {
            	ret = p.parse(s);
            }
            return ret;
        };
    }

    /**
     * peek charcater
     * @param in input stream
     * @return head character
     * @throws IOException read exception
     */
    default int peek(Reader in) throws IOException {
    	int c = markAndRead(in);
    	in.reset();
    	return c;
    }

    /**
     * mark And read character
     * @param in input stream
     * @return head character
     * @throws IOException read exception
     */
    default int markAndRead(Reader in) throws IOException {
    	in.mark(1);
    	return in.read();
    }
}