package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Fabricante;
import respository.Fabricantes;
import util.FacesUtil;

@Named
@ViewScoped
public class FabricanteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Fabricante> fabricantes;
	@Inject
	private Fabricantes repository;
	private Fabricante fabricante;

	@PostConstruct
	public void init() {
		fabricante = new Fabricante();
		listar();
	}

	public void salvar() {
		if (repository.salvar(fabricante)) {
			listar();
		}
		fabricante = new Fabricante();
	}
	
	public void excluir(Fabricante f) {
		if (repository.excluir(f)) {
			listar();
		}
	}

	public void novo() {
		fabricante = new Fabricante();
		FacesUtil.resetarForm("form");
	}

	public void listar() {
		fabricantes = repository.listarTodosFabricantes();
	}

	public void editarFabricante(Fabricante f) {
		fabricante = f;
		FacesUtil.resetarForm("form");
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

}
