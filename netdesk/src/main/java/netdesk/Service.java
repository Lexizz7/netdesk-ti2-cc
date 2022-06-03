package netdesk;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;

import netdesk.Usuario;
import spark.*;

@SuppressWarnings("unchecked")
public class Service {

    public static String getAllAnuncios(Request request, Response response) {
        DAO dao = new DAO();
        dao.conectar();
        String orderBy = request.params(":orderBy");
        String order = request.params(":order");
        Anuncio[] anuncios = dao.getAllAnuncios(orderBy, order);
        JSONArray anuncioListJSON = new JSONArray();

        if (anuncios != null && anuncios.length > 0) {
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
                anuncioDetails.put("numero", anuncio.getNumero());
                anuncioDetails.put("link", anuncio.getLink());

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
        anuncioDetails.put("numero", anuncio.getNumero());
        anuncioDetails.put("link", anuncio.getLink());

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
            int avaliacao = 0;

            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(senha.getBytes(), 0, senha.length());
            senha = new BigInteger(1, m.digest()).toString(16);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNasc = sdf.parse(dataNascString);

            Usuario usuario = new Usuario(cpf, nome, dataNasc, username, senha, email, "", "", "", avaliacao);

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

            String login = (String) body.get("login");
            String senha = (String) body.get("senha");
            String tipo = (String) body.get("tipo");

            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(senha.getBytes(), 0, senha.length());
            senha = new BigInteger(1, m.digest()).toString(16);

            Usuario usuario;
            switch (tipo) {
                case "email":
                    usuario = dao.loginUsuarioEmail(login, senha);
                    break;
                case "cpf":
                    usuario = dao.loginUsuarioCPF(login, senha);
                    break;
                case "usuario":
                    usuario = dao.loginUsuario(login, senha);
                    break;
                default:
                    usuario = dao.loginUsuario(login, senha);
                    break;
            }

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

    public static String searchAnuncio(Request request, Response response) {
        DAO dao = new DAO();
        dao.conectar();

        String titulo = request.params(":title");
        int valor = Integer.parseInt(request.params(":valor"));
        String orderBy = request.params(":orderBy");
        String order = request.params(":order");

        Anuncio[] anuncios = dao.pesquisarAnuncio(titulo, valor, orderBy, order);
        JSONArray anuncioListJSON = new JSONArray();

        if (anuncios != null && anuncios.length > 0) {
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
                anuncioDetails.put("numero", anuncio.getNumero());
                anuncioDetails.put("link", anuncio.getLink());

                anuncioListJSON.add(anuncioDetails);
            }
        }
        dao.close();

        return anuncioListJSON.toJSONString();
    }

    // register anuncio
    public static String registerAnuncio(Request request, Response response) {
        DAO dao = new DAO();
        dao.conectar();

        String bodyJSON = request.body();

        try {
            JSONObject body = (JSONObject) new JSONParser().parse(bodyJSON);

            String cpf = (String) body.get("cpf");
            String titulo = (String) body.get("titulo");
            String descricao = (String) body.get("descricao");
            double valor = Double.parseDouble((String) body.get("valor"));
            String cpu = (String) body.get("cpu");
            String ram = (String) body.get("ram");
            String gpu = (String) body.get("gpu");
            String so = (String) body.get("so");
            String armazenamento = (String) body.get("armazenamento");
            String pais = (String) body.get("pais");
            String cidade = (String) body.get("cidade");
            String estado = (String) body.get("estado");
            String situacao = (String) body.get("situacao");
            String numero = (String) body.get("numero");
            String link = (String) body.get("link");

            Anuncio anuncio = new Anuncio(dao.getNextID(), cpf, titulo, descricao, valor, cpu, ram, gpu, so,
                    armazenamento, pais, cidade,
                    estado, situacao, numero, link);

            dao.inserirAnuncio(anuncio);

        } catch (Exception e) {
            e.printStackTrace();
        }

        dao.close();

        return "{\"msg\":\"Anuncio cadastrado com sucesso!\"}";
    }

    // getUsuarioByCpf
    public static String getUsuarioByCpf(Request request, Response response) {
        DAO dao = new DAO();
        dao.conectar();

        String cpf = request.params(":cpf");

        Usuario usuario = dao.getUsuario(cpf);

        // parse date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataNascString = sdf.format(usuario.getDataNasc());

        if (usuario.getNome() != "") {
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

        dao.close();

        return "{\"msg\":\"Usuario não encontrado!\"}";
    }

    // excluir anuncio
    public static String excluirAnuncio(Request request, Response response) {
        DAO dao = new DAO();
        dao.conectar();

        int id = Integer.parseInt(request.params(":id"));
        String cpf = request.params(":cpf");

        dao.excluirAnuncio(id, cpf);

        dao.close();

        return "{\"msg\":\"Anuncio excluido com sucesso!\"}";
    }

    public static String getAnunciosByUsuario(Request request, Response response) {
        DAO dao = new DAO();
        dao.conectar();

        String cpf = request.params(":cpf");

        Anuncio[] anuncios = dao.getAnunciosByUsuario(cpf);

        JSONArray anuncioListJSON = new JSONArray();

        if (anuncios != null && anuncios.length > 0) {
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
                anuncioDetails.put("numero", anuncio.getNumero());
                anuncioDetails.put("link", anuncio.getLink());

                anuncioListJSON.add(anuncioDetails);
            }
        }
        dao.close();

        return anuncioListJSON.toJSONString();
    }
}