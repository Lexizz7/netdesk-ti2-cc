package netdesk;

public class Anuncio {
    private int id;
    private String cpf;
    private String titulo;
    private String descricao;
    private String valor;
    private String cpu;
    private String ram;
    private String gpu;
    private String so;
    private String armazenamento;
    private String pais;
    private String cidade;
    private String estado;
    private String situacao;
    
    public Anuncio(){
        id = -1;
        cpf = "";
        titulo = "";
        descricao = "";
        valor = "";
        cpu = "";
        ram = "";
        gpu = "";
        so = "";
        armazenamento = "";
        pais = "";
        cidade = "";
        estado = "";
        situacao = "";
    }

    public Anuncio(int id, String cpf, String titulo, String descricao, String valor, String cpu, String ram, String gpu, String so, String armazenamento, String pais, String cidade, String estado, String situacao){
        this.id = id;
        this.cpf = cpf;
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        this.so = so;
        this.armazenamento = armazenamento;
        this.pais = pais;
        this.cidade = cidade;
        this.estado = estado;
        this.situacao = situacao;
    }
    
    
    public void setID(int iD) {
        this.id = iD;
    }
    public int getID() {
        return id;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCpf() {
        return cpf;
    }

public void setTitulo(String titulo) {
    this.titulo = titulo;
}
public String getTitulo() {
    return titulo;
}

public void setDescricao(String descricao) {
    this.descricao = descricao;
}
public String getDescricao() {
    return descricao;
}

public void setValor(String valor) {
    this.valor = valor;
}
public String getValor() {
    return valor;
}

public void setCpu(String cpu) {
    this.cpu = cpu;
}
public String getCpu() {
    return cpu;
}

public void setRam(String ram) {
    this.ram = ram;
}
public String getRam() {
    return ram;
}

public void setGpu(String gpu) {
    this.gpu = gpu;
}
public String getGpu() {
    return gpu;
}

public void setSo(String so) {
    this.so = so;
}
public String getSo() {
    return so;
}

public void setArmazenamento(String armazenamento) {
    this.armazenamento = armazenamento;
}
public String getArmazenamento() {
    return armazenamento;
}

public void setPais(String pais) {
    this.pais = pais;
}
public String getPais() {
    return pais;
}

public void setCidade(String cidade) {
    this.cidade = cidade;
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

public void setSituacao(String situacao) {
    this.situacao = situacao;
}
public String getSituacao() {
    return situacao;
}

}