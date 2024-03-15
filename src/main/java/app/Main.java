package app;

import static spark.Spark.*;
import service.ElementoService;

public class Main {
	
	private static ElementoService elementoService = new ElementoService();
	
	public static void main(String args[]) {
		
		post("/elemento", (req,res) -> elementoService.create(req, res));
		get("/elemento", (req, res) -> elementoService.readAll(req, res));
		get("/elemento:id", (req, res) -> elementoService.read(req, res));
		get("/elemento:id", (req, res) -> elementoService.update(req, res));
		get("/elemento:id", (req, res) -> elementoService.delete(req, res));
	}
}
