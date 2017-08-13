package jp.ksgwr.parser;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

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
	 * parse string
	 * @param s string
	 * @return parse result
	 * @throws Exception parser exception
	 */
    default T parse(String s) throws Exception {
    	return parse(new StringReader(s));
    }

    /**
     * parse stream
     * @param in input stream
     * @return parse result
     * @throws Exception parser exception
     */
    T parse(Reader in) throws Exception;

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
    	in.mark(1);
    	int c = in.read();
    	in.reset();
    	return c;
    }
}