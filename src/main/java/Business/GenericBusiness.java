package Business;

import java.util.List;

/**
 * Generic class where generic methods are implemented by the business layer, 
 * for object manipulation and validations.
 *
 * @author bruno
 */

import javax.inject.Inject;
import javax.transaction.Transactional;
import DAO.DAOException;
import DAO.GenericDAO;

/**
 * Abstract generic business class, where specific methods are implemented to
 * manipulation and validation of objects of generic type.
 * 
 * @author bruno
 *
 * @param <E>
 * @param <DAO>
 */
public abstract class GenericBusiness<E, DAO extends GenericDAO> {

	@Inject
	private DAO dao;

	public GenericBusiness() {
	}

	/**
	 * Method responsible for retrieving dao property
	 * 
	 * @return Returns the dao property.
	 */
	public DAO getDAO() throws BusinessException {
		return dao;
	}

	/**
	 * Method to call DAO layer to persist a generic entity
	 * 
	 * @param entity
	 * @return
	 */
	public E persist(E entity) throws BusinessException {
		try {
			return (E) getDAO().persist(entity);
		} catch (DAOException ex) {
			throw new BusinessException(ex);
		}
	}

	/**
	 * Method to call DAO layer to edit a generic entity
	 * 
	 * @param entity
	 * @return
	 */
	@Transactional
	public E edit(E entity) throws BusinessException {
		try {
			return (E) getDAO().edit(entity);
		} catch (DAOException ex) {
			throw new BusinessException(ex);
		}
	}

	/**
	 * Method to call DAO layer delete a generic entity
	 * 
	 * @param entity
	 * @return
	 */
	public void remove(E entity) throws BusinessException {
		try {
			getDAO().remove(entity);
		} catch (DAOException ex) {
			throw new BusinessException(ex);
		}
	}

	/**
	 * Method to call DAO layer to return all elements of a generic entity
	 * 
	 * @return
	 */
	public List<E> findAll() throws BusinessException {
		try {
			return getDAO().findAll();
		} catch (DAOException ex) {
			throw new BusinessException(ex);
		}
	}
}
