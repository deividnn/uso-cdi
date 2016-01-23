package respository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Fabricante;
import util.FacesUtil;

public class Fabricantes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Fabricante> listarTodosFabricantes() {
		return manager.createQuery("from Fabricante order by codigo desc").getResultList();
	}

	public boolean salvar(Fabricante fabricante) {
		EntityTransaction tx = null;
		try {
			tx = manager.getTransaction();
			tx.begin();
			if (fabricante.getCodigo() == null) {
				manager.persist(fabricante);
				FacesUtil.addSuccessMessage("salvo");
			} else {
				manager.merge(fabricante);
				FacesUtil.addSuccessMessage("alterado");
			}
			tx.commit();
			return true;
		} catch (Exception e) {
			FacesUtil.addErrorMessage("erro " + e);
			if (tx != null)
				tx.rollback();
			return false;
		}

	}

	public boolean excluir(Fabricante fabricante) {
		EntityTransaction tx = null;
		try {
			tx = manager.getTransaction();
			tx.begin();

			manager.remove(manager.getReference(Fabricante.class,fabricante.getCodigo()));
			FacesUtil.addSuccessMessage("excluido");

			tx.commit();
			return true;
		} catch (Exception e) {
			FacesUtil.addErrorMessage("erro " + e);
			if (tx != null)
				tx.rollback();
			return false;
		}

	}

}
