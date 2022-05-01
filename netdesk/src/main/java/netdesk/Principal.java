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
            response.header("Access-Control-Allow-Methods", "GET");
        });
		
		get("/getAllAnuncios", (request, response) -> Service.getAllAnuncios());
		
		dao.close();
	}
}