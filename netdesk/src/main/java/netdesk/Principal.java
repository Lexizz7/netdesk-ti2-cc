package netdesk;

import static spark.Spark.*;

import spark.Spark;
import spark.Filter;
import spark.Request;
import spark.Response;

public class Principal {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();

		port(3001);
		
		after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST");
        });
		
		get("/getAllAnuncios", (request, response) -> Service.getAllAnuncios());

		get("/getAnuncioById/:id", (request, response) -> Service.getAnuncioById(request, response));

		post("/login", (request, response) -> Service.login(request, response));

		post("/cadastrar", (request, response) -> Service.register(request, response));
		
		dao.close();
	}
}