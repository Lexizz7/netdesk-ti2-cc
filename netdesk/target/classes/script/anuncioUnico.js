const body = document.body;
const html = document.documentElement;
const height = Math.max(body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight);

function removeFixedFooter() {
    if (height > window.innerHeight) {
        document.getElementById("footer").style.position = "initial";
    }
    console.log(height);
    console.log(window.innerHeight);
}

window.onload = removeFixedFooter;

// Popular anúncio com dados reais
const loadAnuncio = () => {
    const id = window.location.hash.substr(1);
    fetch(`http://localhost:3001/getAnuncioById/${id}`)
        .then((response) => response.json())
        .then((data) => {
            const anuncio = data;
            const mainClass = document.querySelector(".mainClass");
            mainClass.innerHTML = "";
            const isMyAnuncio = anuncio.cpf === JSON.parse(localStorage.getItem("user")).cpf;
            fetch(`http://localhost:3001/getUsuarioByCpf/${anuncio.cpf}`)
                .then((response) => response.json())
                .then((data) => {
                    const usuario = data;
                    mainClass.innerHTML = `
                <div class="chosenAnuncioDiv">
                    <h1>${anuncio.titulo}</h1>
                    <div class="chosenAnuncioBorder">
                        <p>Autor: ${usuario.nome}</p>
                        <p>Descrição: ${anuncio.descricao}</p>
                        <p>Valor: R$${anuncio.valor} por hora.</p>
                        <h4>Informações do sistema</h4>
                        <p>Sistema operacional: ${anuncio.so}</p>
                        <p>Processador: ${anuncio.cpu}</p>
                        <p>Memória: ${anuncio.ram} GB</p>
                        <p>Placa de vídeo: ${anuncio.gpu}</p>
                        <p>Armazenamento: ${anuncio.armazenamento} GB</p>
                        <h4>Localização</h4>
                        <p>Cidade: ${anuncio.cidade}</p>
                        <p>Estado: ${anuncio.estado}</p>
                        <p>País: ${anuncio.pais}</p>
                    </div>
                    <div>
                        <button id="btn" class="btn" onclick="alugar()">Alugar</button>
                    </div>
                    ${
                        isMyAnuncio
                            ? `<div>
                                <button id="btn" class="btn" onclick="excluir()">Excluir</button>
                            </div>`
                            : ""
                    }
                </div>
            `;
                });
        });
};
loadAnuncio();

const excluir = () => {
    const id = window.location.hash.substr(1);
    const cpf = JSON.parse(localStorage.getItem("user")).cpf;
    fetch(`http://localhost:3001/excluirAnuncio/${id}/${cpf}`)
        .then((response) => response.json())
        .then((data) => {
            alert(data);
            window.location.href = "index.html";
        });
};
