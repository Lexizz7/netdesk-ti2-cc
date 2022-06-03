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
        setCpf(cpf);
        setNome(nome);
        setDataNasc(dataNasc);
        setUsername(Username);
        setSenha(senha);
        setEmail(email);
        setCidade(cidade);
        setEstado(estado);
        setPais(pais);
        setAvaliacao(avaliacao);
    }

    public boolean validateCpf(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public void setCpf(String cPF) {
        if (validateCpf(cPF))
            cpf = cPF;
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
        if (Senha.length() < 6) {
            System.out.println("Erro: Senha não pode ter menos que 6 caracteres.");
        } else {
            this.senha = Senha;
        }
    }

    public String getSenha() {
        return senha;
    }

    public boolean validateEmail(String Email) {
        // email pattern
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        // Compila o pattern
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(emailPattern);

        // Verifica o email
        java.util.regex.Matcher matcher = pattern.matcher(Email);
        return matcher.matches();
    }

    public void setEmail(String Email) {
        if (validateEmail(Email))
            email = Email;
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
        if (avaliacao > 5) {
            System.out.println("Erro: Avaliação não pode ser maior que 5.");
        } else if (avaliacao < 0) {
            System.out.println("Erro: Avaliação não pode ser menor que 0.");
        } else {
            this.avaliacao = avaliacao;
        }
    }

    public int getAvaliacao() {
        return avaliacao;
    }
}