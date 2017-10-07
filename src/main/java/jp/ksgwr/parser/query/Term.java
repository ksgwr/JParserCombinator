package jp.ksgwr.parser.query;

import java.util.List;

/**
 * Query Term
 *
 * @author ksgwr
 *
 */
public class Term {

	/** surface */
	public String surface;

	/** content */
	public String content;

	/** position */
	public int position;

	/** not */
	public boolean not;

	/** phrase */
	public boolean phrase;

	/** attr */
	public List<Term> attr;

	/**
	 * constructor
	 */
	public Term() {

	}
}
