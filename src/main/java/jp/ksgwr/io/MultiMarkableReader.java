package jp.ksgwr.io;

import java.io.IOException;
import java.io.Reader;

/**
 * MultiMarkableReader Base Class
 *
 * @author ksgwr
 *
 */
public abstract class MultiMarkableReader extends Reader {

	/**
	 * constructor
	 * @param in reader
	 */
	protected MultiMarkableReader(Reader in) {
		super(in);
	}

	/**
	 * constructor
	 */
	protected MultiMarkableReader() {
		super();
	}

	/**
	 * addMultiMark (not readAheadLimit)
	 * @param hashCode uniq key
	 * @throws IOException error
	 */
	public abstract void addMultiMark(int hashCode) throws IOException;

	/**
	 * clearMultiMark
	 * @param hashCode uniq key
	 * @throws IOException error
	 */
	public abstract void clearMultiMark(int hashCode) throws IOException;

	/**
	 * resetMultiMark
	 * @param hashCode uniq key
	 * @throws IOException error
	 */
	public abstract void resetMultiMark(int hashCode) throws IOException;
}
