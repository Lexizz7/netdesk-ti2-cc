package netdesk;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.text.SimpleDateFormat;

import netdesk.Usuario;
import spark.*;

@SuppressWarnings("unchecked")
public class Service {

    public static String getAllAnuncios() {
        DAO dao = new DAO();
        dao.conectar();

        Anuncio[] anuncios = dao.getAllAnuncios();
        JSONArray anuncioListJSON = new JSONArray();

        if (anuncios.length > 0) {
            for (Anuncio anuncio : anuncios) {
                JSONObject anuncioDetails = new JSONObject();
                anuncioDetails.put("id", anuncio.getID());
                anuncioDetails.put("cpf", anuncio.getCpf());
                anuncioDetails.put("titulo", anuncio.getTitulo());
                anuncioDetails.put("descricao", anuncio.getDescricao());
                anuncioDetails.put("valor", anuncio.getValor());
                anuncioDetails.put("cpu", anuncio.getCpu());
                anuncioDetails.put("ram", anuncio.getRam());
                anuncioDetails.put("gpu", anuncio.getGpu());
                anuncioDetails.put("so", anuncio.getSo());
                anuncioDetails.put("armazenamento", anuncio.getArmazenamento());
                anuncioDetails.put("pais", anuncio.getPais());
                anuncioDetails.put("cidade", anuncio.getCidade());
                anuncioDetails.put("estado", anuncio.getEstado());
                anuncioDetails.put("situacao", anuncio.getSituacao());

                anuncioListJSON.add(anuncioDetails);
            }
        }
        dao.close();

        return anuncioListJSON.toJSONString();
    }

    public static String getAnuncioById(Request request, Response response) {
        DAO dao = new DAO();
        dao.conectar();

        int id = Integer.parseInt(request.params(":id"));

        Anuncio anuncio = dao.getAnuncio(id);

        JSONObject anuncioDetails = new JSONObject();
        anuncioDetails.put("id", anuncio.getID());
        anuncioDetails.put("cpf", anuncio.getCpf());
        anuncioDetails.put("titulo", anuncio.getTitulo());
        anuncioDetails.put("descricao", anuncio.getDescricao());
        anuncioDetails.put("valor", anuncio.getValor());
        anuncioDetails.put("cpu", anuncio.getCpu());
        anuncioDetails.put("ram", anuncio.getRam());
        anuncioDetails.put("gpu", anuncio.getGpu());
        anuncioDetails.put("so", anuncio.getSo());
        anuncioDetails.put("armazenamento", anuncio.getArmazenamento());
        anuncioDetails.put("pais", anuncio.getPais());
        anuncioDetails.put("cidade", anuncio.getCidade());
        anuncioDetails.put("estado", anuncio.getEstado());
        anuncioDetails.put("situacao", anuncio.getSituacao());

        dao.close();

        return anuncioDetails.toJSONString();
    }

    public static String register(Request request, Response response) {
        DAO dao = new DAO();
        dao.conectar();

        String bodyJSON = request.body();

        try {
            JSONObject body = (JSONObject) new JSONParser().parse(bodyJSON);

            String cpf = (String) body.get("cpf");
            String nome = (String) body.get("nome");
            String dataNascString = (String) body.get("dataNascimento");
            String username = (String) body.get("username");
            String senha = (String) body.get("senha");
            String email = (String) body.get("email");
            String cidade = (String) body.get("cidade");
            String estado = (String) body.get("estado");
            String pais = (String) body.get("pais");
            int avaliacao = 0;

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNasc = sdf.parse(dataNascString);

            Usuario usuario = new Usuario(cpf, nome, dataNasc, username, senha, email, cidade, estado, pais, avaliacao);

            dao.inserirUsuario(usuario);

        } catch (Exception e) {
            e.printStackTrace();
        }

        dao.close();

        return "Usuario cadastrado com sucesso!";
    }

    public static String login(Request request, Response response) {
        DAO dao = new DAO();
        dao.conectar();

        String bodyJSON = request.body();

        try {
            JSONObject body = (JSONObject) new JSONParser().parse(bodyJSON);

            String username = (String) body.get("username");
            String senha = (String) body.get("senha");

            Usuario usuario = dao.loginUsuario(username, senha);

            // parse date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataNascString = sdf.format(usuario.getDataNasc());

            if (usuario != null) {
                JSONObject usuarioDetails = new JSONObject();
                usuarioDetails.put("cpf", usuario.getCpf());
                usuarioDetails.put("nome", usuario.getNome());
                usuarioDetails.put("dataNascimento", dataNascString);
                usuarioDetails.put("username", usuario.getUsername());
                usuarioDetails.put("email", usuario.getEmail());
                usuarioDetails.put("cidade", usuario.getCidade());
                usuarioDetails.put("estado", usuario.getEstado());
                usuarioDetails.put("pais", usuario.getPais());
                usuarioDetails.put("avaliacao", usuario.getAvaliacao());

                dao.close();

                return usuarioDetails.toJSONString();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        dao.close();

        return "{\"msg\":\"Usuario não encontrado!\"}";
    }
}