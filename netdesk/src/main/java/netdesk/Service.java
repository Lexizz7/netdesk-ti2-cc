package netdesk;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import netdesk.Usuario;
import spark.*;
@SuppressWarnings("unchecked")
public class Service {


	public static String getAllAnuncios() {
		DAO dao = new DAO();
		dao.conectar();
		
		Anuncio[] anuncios = dao.getAllAnuncios();
		JSONArray anuncioListJSON = new JSONArray();
		
        for (Anuncio anuncio : anuncios) {
        	JSONObject anuncioDetails = new JSONObject();
        	anuncioDetails.put("id", anuncio.getID());
            anuncioDetails.put("cpf", anuncio.getCPF());
            anuncioDetails.put("titulo", anuncio.getTitulo());
            anuncioDetails.put("descricao", anuncio.getDescricao());
            anuncioDetails.put("valor", anuncio.getValor());
            anuncioDetails.put("cpu", anuncio.getCPU());
            anuncioDetails.put("ram", anuncio.getRAM());
            anuncioDetails.put("gpu", anuncio.getGPU());
            anuncioDetails.put("so", anuncio.getSO());
            anuncioDetails.put("armazenamento", anuncio.getArmazenamento());
            anuncioDetails.put("pais", anuncio.getPais());
            anuncioDetails.put("cidade", anuncio.getCidade());
            anuncioDetails.put("estado", anuncio.getEstado());
            anuncioDetails.put("situacao", anuncio.getSituacao());

            anuncioListJSON.add(anuncioDetails);
           }
		
		dao.close();
		
		return anuncioListJSON.toJSONString();
	}
	
	// public static String searchUsuario(Request request) {
	// 	DAO dao = new DAO();
	// 	dao.conectar();
	// 	String cpf = request.params(":cpf");
		
	// 	Usuario usuario = dao.procurarUsuario(cpf);
		
	// 	JSONArray usuariosListJSON = new JSONArray();
	// 	if(Integer.parseInt(usuario.getCPF()) != 0) {
    //     JSONObject alunoDetails = new JSONObject();
    //     usuarioDetails.put("matricula", usuario.getMatricula());
    //     usuarioDetails.put("nome", usuario.getNome());
    //     usuarioDetails.put("idade", usuario.getIdade());
    //     usuarioDetails.put("sexo", "" + usuario.getSexo() + "");
    //     usuarioDetails.put("curso", usuario.getCurso());
    //     usuarioDetails.put("periodo", usuario.getPeriodo());
        	
    //     usuariosListJSON.add(usuarioDetails);
	// }
	// 	dao.close();
		
	// 	return alunosListJSON.toJSONString();
	// }
}