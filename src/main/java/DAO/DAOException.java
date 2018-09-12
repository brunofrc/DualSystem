package DAO;

/**
 * Exception to be thrown whenever an error occurs in the DAO layer
 * 
 * @author bruno
 *
 */
public class DAOException extends Exception {
	/**
	 * Standard Builder
	 */
	public DAOException() {

		super();
	}

	/**
	 * Exception with three parameters and a Throwable
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Throwable
	 * @param arg2
	 *            boolean
	 * @param arg3
	 *            boolean
	 */
	public DAOException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {

		super(arg0, arg1, arg2, arg3);
	}

	/**
	 * Exception with a parameters and a Throwable
	 * 
	 * @param arg0
	 *            String
	 * @param arg1
	 *            Throwable
	 */
	public DAOException(String arg0, Throwable arg1) {

		super(arg0, arg1);
	}

	/**
	 * Exception with a parameters
	 * 
	 * @param arg0
	 *            String
	 */
	public DAOException(String arg0) {

		super(arg0);
	}

	/**
	 * Exception with a Throwable
	 * 
	 * @param arg0
	 *            Throwable
	 */
	public DAOException(Throwable arg0) {

		super(arg0);
	}
}
