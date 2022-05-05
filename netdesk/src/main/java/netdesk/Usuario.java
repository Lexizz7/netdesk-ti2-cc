package netdesk;

import java.util.*;

class Usuario {
    private String cpf;
    private String nome;
    private Date dataNasc;
    private String username;
    private String senha;
    private String email;
    private String cidade;
    private String estado;
    private String pais;
    private int avaliacao;

    public Usuario() {
        cpf = "";
        nome = "";
        dataNasc = null;
        username = "";
        senha = "";
        email = "";
        cidade = "";
        estado = "";
        pais = "";
        avaliacao = 0;
    }

    public Usuario(String cpf, String nome, Date dataNasc, String Username, String senha, String email, String cidade,
            String estado, String pais, int avaliacao) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.username = Username;
        this.senha = senha;
        this.email = email;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.avaliacao = avaliacao;
    }

    public void setCpf(String cPF) {
        if (cpf.length() > 11) {
            System.out.println("Erro: CPF não pode ter mais que 11 caracteres.");
        } else if (cpf.length() < 11) {
            System.out.println("Erro: CPF não pode ter menos que 11 caracteres.");
        } else {
            this.cpf = cPF;
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String Nome) {
        this.nome = Nome;
    }

    public String getNome() {
        return nome;
    }

    public void setDataNasc(Date DataNasc) {
        this.dataNasc = DataNasc;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setUsername(String Username) {
        this.username = Username;
    }

    public String getUsername() {
        return username;
    }

    public void setSenha(String Senha) {
        this.senha = Senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getEmail() {
        return email;
    }

    public void setCidade(String Cidade) {
        this.cidade = Cidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPais() {
        return pais;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }
}