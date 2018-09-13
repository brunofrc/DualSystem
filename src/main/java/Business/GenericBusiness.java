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

public abstract class GenericBusiness<E, DAO extends GenericDAO> {

	@Inject
	private DAO dao;

	public GenericBusiness() {
	}

	/**
	 * Metodo responsavel por recuperar o atributo dao
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public DAO getDAO() throws BusinessException {
		return dao;
	}

	public E persist(E entity) throws BusinessException {
		try {
			return (E) getDAO().persist(entity);
		} catch (DAOException ex) {
			throw new BusinessException(ex);
		}
	}

	@Transactional
	public E edit(E entity) throws BusinessException {
		try {
			return (E) getDAO().edit(entity);
		} catch (DAOException ex) {
			throw new BusinessException(ex);
		}
	}

	public void remove(E entity) throws BusinessException {
		try {
			getDAO().remove(entity);
		} catch (DAOException ex) {
			throw new BusinessException(ex);
		}
	}

	public List<E> findAll() throws BusinessException {
		try {
			return getDAO().findAll();
		} catch (DAOException ex) {
			throw new BusinessException(ex);
		}
	}
}
