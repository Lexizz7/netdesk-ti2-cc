var editDataHappening = false;
var METH = -51;
var lastHeight = 46;
const body = document.body;
const html = document.documentElement;
const height = Math.max(body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight);

function removeFixedFooter() {
    if (height > window.innerHeight) {
        document.getElementById("footer").style.position = "initial";
    }
}

function auto_grow(element) {
    element.style.height = "50px";
    element.style.height = element.scrollHeight + "px";
    if (editDataHappening == true && lastHeight != element.scrollHeight) {
        document.querySelector(".modalEditText").style.bottom = METH + "px";
        METH -= 12;
    }
    lastHeight = element.scrollHeight;
    removeFixedFooter();
}

const postAnuncio = () => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (!user) {
        alert("Você precisa estar logado para anunciar!");
        return;
    }
    const titulo = document.querySelector("#tituloInput").value;
    const descricao = document.querySelector("#commentTextarea").value;
    const valor = document.querySelector("#valorInput").value;
    const cpu = document.querySelector("#cpuInput").value;
    const ram = document.querySelector("#ramInput").value;
    const os = document.querySelector("#osSelect").value;
    const gpu = document.querySelector("#gpuInput").value;
    const armazenamento = document.querySelector("#storageInput").value;
    const pais = document.querySelector("#paisInput").value;
    const estado = document.querySelector("#estadoInput").value;
    const cidade = document.querySelector("#cidadeInput").value;
    const numero = document.querySelector("#numeroInput").value;
    const link = document.querySelector("#linkInput").value;

    if (
        titulo == "" ||
        descricao == "" ||
        valor == "" ||
        cpu == "" ||
        ram == "" ||
        os == "" ||
        gpu == "" ||
        armazenamento == "" ||
        pais == "" ||
        estado == "" ||
        cidade == "" ||
        (numero == "" && link == "")
    ) {
        alert("Preencha todos os campos!");
        return;
    }
    const anuncio = {
        titulo: titulo,
        descricao: descricao,
        valor: valor,
        cpu: cpu,
        ram: ram,
        so: os,
        gpu: gpu,
        armazenamento: armazenamento,
        pais: pais,
        estado: estado,
        cidade: cidade,
        cpf: user.cpf,
        numero: numero,
        link: link,
    };

    const url = "http://localhost:3001/cadastrarAnuncio/";

    fetch(url, {
        method: "POST",
        body: JSON.stringify(anuncio),
    })
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            alert(data.msg);
            window.location.href = "index.html";
        });
};
document.querySelector(".anunciarbtn").addEventListener("click", postAnuncio);
