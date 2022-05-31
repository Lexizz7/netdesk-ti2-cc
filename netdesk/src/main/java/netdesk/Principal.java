package netdesk;

import java.util.*;
import static spark.Spark.*;

import spark.Spark;
import spark.Filter;
import spark.Request;
import spark.Response;

public class Principal {
	
private static final HashMap<String, String> corsHeaders = new HashMap<String, String>();
    
    static {
        corsHeaders.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        corsHeaders.put("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
        corsHeaders.put("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
        corsHeaders.put("Access-Control-Allow-Credentials", "true");
    }

    public final static void apply() {
        Filter filter = new Filter() {
            @Override
            public void handle(Request request, Response response) throws Exception {
                corsHeaders.forEach((key, value) -> {
                    response.header(key, value);
                });
            }
        };
        Spark.after(filter);
    }
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();
		
		port(3001);
		
		Principal.apply();
		
		get("/getAllAnuncios", (request, response) -> Service.getAllAnuncios());

		get("/getAnuncioById/:id", (request, response) -> Service.getAnuncioById(request, response));
		
		get("pesquisarAnuncio/:title/:valor", (request, response) -> Service.searchAnuncio(request, response));

		post("/login/", (request, response) -> Service.login(request, response));

		post("/cadastrar/", (request, response) -> Service.register(request, response));
		
		dao.close();
	}
}