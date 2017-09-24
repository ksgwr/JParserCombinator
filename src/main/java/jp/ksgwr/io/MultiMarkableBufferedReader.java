package jp.ksgwr.io;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Map.Entry;

/**
 * MultiMarkableBufferedReader
 *
 * @author ksgwr
 *
 */
public class MultiMarkableBufferedReader extends MultiMarkableStringReader {

	/** reader */
	private Reader in;

	/** charcter buffer */
	private char cb[];

	/** mark read ahead limit */
	private int readAheadLimit = 0;

	/** mark invalidated */
	private static final int INVALIDATED = -2;

	/** mark unmarked */
    private static final int UNMARKED = -1;

    /** default buffer size */
	private static final int DEFAULT_CHAR_BUFFER_SIZE = 8192;

	/**
	 * constructor
	 */
	public MultiMarkableBufferedReader(Reader in) {
		this(in, DEFAULT_CHAR_BUFFER_SIZE);
	}

	/**
	 * constructor
	 * @param in reader
	 * @param sz buffer size
	 */
	public MultiMarkableBufferedReader(Reader in, int sz) {
		super(in);
		this.in = in;
		this.cb = new char[sz];
		super.str = "";
	}

    /** Checks to make sure that the stream has not been closed */
    private void ensureOpen() throws IOException {
        if (in == null)
            throw new IOException("Stream closed");
    }

    /**
     * fill method based BufferedReader
     * @throws IOException error
     */
    private void fill() throws IOException {
    	int markDelta;
    	if (super.mark > UNMARKED) {
    		// check mark
    		markDelta = super.next - super.mark;
    		if (markDelta >= readAheadLimit) {
    			/* Gone past read-ahead limit: Invalidate mark */
    			super.mark = INVALIDATED;
    			readAheadLimit = 0;
    		}
    	}

    	Integer minMark = null;
    	if (super.marks.size() > 0) {
    		minMark = Collections.min(super.marks.values());
    		if (super.mark > UNMARKED && super.mark < minMark) {
    			minMark = super.mark;
    		}
    	} else if (super.mark > UNMARKED) {
    		minMark = super.mark;
    	}

    	int n;
    	if (minMark != null) {
    		markDelta = super.next - minMark;

    		StringBuilder sb = new StringBuilder(markDelta + cb.length);

    		if (cb.length == super.next) {
    			if (minMark < cb.length) {
    				sb.append(cb, minMark, cb.length);
    			}
    			if (super.mark > UNMARKED) {
    				super.mark = super.mark - minMark;
    				readAheadLimit = readAheadLimit - minMark;
    			}
    			if (super.marks.size() > 0) {
    				for(Entry<Integer, Integer> entry:super.marks.entrySet()) {
    					super.marks.put(entry.getKey(), entry.getValue() - minMark);
    				}
    			}
    		}

            do {
                n = in.read(cb, 0, cb.length);
            } while (n == 0);
            if (n > 0) {
            	sb.append(cb, 0, n);
            	super.str = sb.toString();
            	super.length = super.str.length();
            	super.next = markDelta;
            }

    	} else {
            do {
                n = in.read(cb, 0, cb.length);
            } while (n == 0);
            if (n > 0) {
                super.str = new String(cb, 0, n);
                super.length = super.str.length();
                super.next = 0;
            }
    	}

    	if (n < 0) {
        	super.next = super.length;
    	}
    }

    /**
     * Reads a single character.
     *
     * @return The character read, as an integer in the range
     *         0 to 65535 (<tt>0x00-0xffff</tt>), or -1 if the
     *         end of the stream has been reached
     * @exception  IOException  If an I/O error occurs
     */
    public int read() throws IOException {
        synchronized (lock) {
            ensureOpen();
            if (super.next >= super.length) {
            	fill();
            	if (super.next >= super.length) {
            		return -1;
            	}
            }
            return super.read();
        }
    }

    @Override
    public void mark(int readAheadLimit) throws IOException {
        if (readAheadLimit < 0) {
            throw new IllegalArgumentException("Read-ahead limit < 0");
        }
        synchronized (lock) {
            ensureOpen();
            this.readAheadLimit = readAheadLimit;
            super.mark(readAheadLimit);
        }
    }

    @Override
    public void reset() throws IOException {
    	 synchronized (lock) {
             ensureOpen();
             if (super.mark <= UNMARKED)
                 throw new IOException((mark == INVALIDATED)
                                       ? "Mark invalid"
                                       : "Stream not marked");
             super.reset();
    	 }
    }

    @Override
    public void close() throws IOException {
        synchronized (lock) {
            if (in == null)
                return;
            try {
                in.close();
                super.close();
            } finally {
                in = null;
                cb = null;
            }
        }
    }

}
