package netdesk;

public class Anuncio {
    private int ID;
    private String CPF;
    private String Titulo;
    private String Descricao;
    private String Valor;
    private String CPU;
    private String RAM;
    private String GPU;
    private String SO;
    private String Armazenamento;
    private String Pais;
    private String Cidade;
    private String Estado;
    private String Situacao;
    
    public Anuncio(){
        ID = -1;
        CPF = "";
        Titulo = "";
        Descricao = "";
        Valor = "";
        CPU = "";
        RAM = "";
        GPU = "";
        SO = "";
        Armazenamento = "";
        Pais = "";
        Cidade = "";
        Estado = "";
        Situacao = "";
    }

    public Anuncio(int ID, String CPF, String Titulo, String Descricao, String Valor, String CPU, String RAM, String GPU, String SO, String Armazenamento, String Pais, String Cidade, String Estado, String Situacao){
        this.ID = ID;
        this.CPF = CPF;
        this.Titulo = Titulo;
        this.Descricao = Descricao;
        this.Valor = Valor;
        this.CPU = CPU;
        this.RAM = RAM;
        this.GPU = GPU;
        this.SO = SO;
        this.Armazenamento = Armazenamento;
        this.Pais = Pais;
        this.Cidade = Cidade;
        this.Estado = Estado;
        this.Situacao = Situacao;
    }
    
    
    public void setID(int iD) {
        ID = iD;
    }
    public int getID() {
        return ID;
    }
    
    public void setCPF(String cPF) {
        CPF = cPF;
    }
    public String getCPF() {
        return CPF;
    }

public void setTitulo(String titulo) {
    Titulo = titulo;
}
public String getTitulo() {
    return Titulo;
}

public void setDescricao(String descricao) {
    Descricao = descricao;
}
public String getDescricao() {
    return Descricao;
}

public void setValor(String valor) {
    Valor = valor;
}
public String getValor() {
    return Valor;
}

public void setCPU(String cPU) {
    CPU = cPU;
}
public String getCPU() {
    return CPU;
}

public void setRAM(String rAM) {
    RAM = rAM;
}
public String getRAM() {
    return RAM;
}

public void setGPU(String gPU) {
    GPU = gPU;
}
public String getGPU() {
    return GPU;
}

public void setSO(String sO) {
    SO = sO;
}
public String getSO() {
    return SO;
}

public void setArmazenamento(String armazenamento) {
    Armazenamento = armazenamento;
}
public String getArmazenamento() {
    return Armazenamento;
}

public void setPais(String pais) {
    Pais = pais;
}
public String getPais() {
    return Pais;
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

public void setSituacao(String situacao) {
    Situacao = situacao;
}
public String getSituacao() {
    return Situacao;
}

}