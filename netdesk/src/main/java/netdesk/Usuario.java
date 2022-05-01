package netdesk;

import java.util.*;

class Usuario {
    private String CPF;
    private String Nome;
    private Date DataNasc;
    private String Username;
    private String Senha;
    private String Email;
    private String Cidade;
    private String Estado;
    private String Pais;
    private int Avaliacao;

    public Usuario() {
        CPF = "";
        Nome = "";
        DataNasc = null;
        Username = "";
        Senha = "";
        Email = "";
        Cidade = "";
        Estado = "";
        Pais = "";
        Avaliacao = 0;
    }

    public Usuario(String CPF, String Nome, Date DataNasc, String Username, String Senha, String Email, String Cidade,
            String Estado, String Pais, int Avaliacao) {
        this.CPF = CPF;
        this.Nome = Nome;
        this.DataNasc = DataNasc;
        this.Username = Username;
        this.Senha = Senha;
        this.Email = Email;
        this.Cidade = Cidade;
        this.Estado = Estado;
        this.Pais = Pais;
        this.Avaliacao = Avaliacao;
    }

    public void setCPF(String cPF) {
        if (CPF.length() > 11) {
            System.out.println("Erro: CPF não pode ter mais que 11 caracteres.");
        } else if (CPF.length() < 11) {
            System.out.println("Erro: CPF não pode ter menos que 11 caracteres.");
        } else {
            this.CPF = cPF;
        }
    }

    public String getCPF() {
        return CPF;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getNome() {
        return Nome;
    }

    public void setDataNasc(Date dataNasc) {
        DataNasc = dataNasc;
    }

    public Date getDataNasc() {
        return DataNasc;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUsername() {
        return Username;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getSenha() {
        return Senha;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEmail() {
        return Email;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getEstado() {
        return Estado;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getPais() {
        return Pais;
    }

    public void setAvaliacao(int avaliacao) {
        Avaliacao = avaliacao;
    }

    public int getAvaliacao() {
        return Avaliacao;
    }
}