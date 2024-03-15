package service;

import dao.ElementoDAO;
import model.Elemento;

import spark.Request;
import spark.Response;
import spark.Spark.*;


public class ElementoService {

	private ElementoDAO elementoDAO = new ElementoDAO();
	private Elemento elemento;
	
	public Object create(Request req, Response res) {
		
		elementoDAO.connect();
		
		int numeroAtomico = Integer.parseInt(req.queryParams("atomicNum"));
	    String nome = req.queryParams("elementName");
	    String simbolo = req.queryParams("symbol");
	    String familia = req.queryParams("elementName");
	    
	    elemento = new Elemento(numeroAtomico, nome, simbolo, familia);
	    elementoDAO.createElemento(elemento);
	    
	    res.status(201);
	    
	    return null;
	}
	
	
	public Object read(Request req, Response res) {
		
	    elementoDAO.connect();
	    int numeroAtomico = Integer.parseInt(req.params("atomicNum"));
	    
	    Elemento elemento = elementoDAO.readElemento(numeroAtomico);
	    
	    if (elemento != null) {

	        res.status(200);
	        return elemento;
	    } else {

	        res.status(404);
	        return "Elemento não encontrado";
	    }
	}

	
	public Object readAll(Request req, Response res) {
		
	    elementoDAO.connect();
	    Elemento[] elementos = elementoDAO.readElementos();
	    
	    res.status(200);
	    res.type("application/json");
	    return elementos;
	}

	
	public Object update(Request req, Response res) {
		
	    elementoDAO.connect();
	    int numeroAtomico = Integer.parseInt(req.params("atomicNum"));
	    
	    Elemento elemento = elementoDAO.readElemento(numeroAtomico);
	    
	    if (elemento != null) {

	        elemento.setNome(req.queryParams("elementName"));
	        elemento.setSimbolo(req.queryParams("symbol"));
	        elemento.setFamilia(req.queryParams("family"));
	        

	        elementoDAO.updateElemento(elemento);
	        
	        res.status(200);
	        return "Elemento atualizado com sucesso";
	    } else {
	        res.status(404);
	        return "Elemento não encontrado";
	    }
	}

	
	public Object delete(Request req, Response res) {
		
	    elementoDAO.connect();
	    int numeroAtomico = Integer.parseInt(req.params("atomicNum"));
	    
	    if (elemento != null) {
	        elementoDAO.deleteElemento(numeroAtomico);
	        
	        res.status(200);
	        return "Elemento deletado com sucesso";
	    } else {
	        res.status(404);
	        return "Elemento não encontrado";
	    }
	}

}
