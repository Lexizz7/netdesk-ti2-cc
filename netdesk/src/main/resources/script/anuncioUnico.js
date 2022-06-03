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
            let isMyAnuncio = false;
            if (JSON.parse(localStorage.getItem("user"))) {
                isMyAnuncio = anuncio.cpf === JSON.parse(localStorage.getItem("user")).cpf;
            }
            fetch(`http://localhost:3001/getUsuarioByCpf/${anuncio.cpf}`)
                .then((response) => response.json())
                .then((data) => {
                    const usuario = data;
                    mainClass.innerHTML = `
                <div class="chosenAnuncioDiv">
                    <h1>${anuncio.titulo}</h1>
                    <div class="chosenAnuncioBorder">
                        <h4>Informações Gerais</h4>
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

const alugar = () => {
    const id = window.location.hash.substr(1);
    const cpf = JSON.parse(localStorage.getItem("user")).cpf;
    fetch(`http://localhost:3001/getAnuncioById/${id}`)
        .then((response) => response.json())
        .then((data) => {
            const anuncio = data;
            console.log(anuncio);
            const cpf = anuncio.cpf;
            fetch(`http://localhost:3001/getUsuarioByCpf/${cpf}`)
                .then((response) => response.json())
                .then((data) => {
                    const usuario = data;
                    console.log(usuario);
                    //show popup with number and site link
                    const popup = document.createElement("div");
                    popup.classList.add("popup");
                    popup.innerHTML = `
                    <div class="popup-content">
                        <h1>${anuncio.titulo}</h1>
                        <div class="popup-border">
                            <h4>Contato</h4>
                            <p>Nome: ${usuario.nome}</p>
                            ${anuncio.numero ? `<p>Telefone: ${anuncio.numero}</p>` : "<p>Telefone: Não informou</p>"}
                            <p>Email: ${usuario.email}</p>
                            ${anuncio.link ? `<p>Link externo: <a href="${anuncio.link}">${anuncio.link}</a></p>` : "<p>Link externo: Não informou</p>"}
                        </div>
                        <div>
                            <button id="btn" class="btn" onclick="fechar()">Fechar</button>
                        </div>
                    </div>
                `;
                    //popup css
                    popup.style.position = "fixed";
                    popup.style.top = "50%";
                    popup.style.left = "50%";
                    popup.style.transform = "translate(-50%, -50%)";
                    popup.style.backgroundColor = "white";
                    popup.style.borderRadius = "10px";
                    popup.style.padding = "20px";
                    popup.style.zIndex = "1000";
                    popup.style.width = "500px";
                    popup.style.height = "500px";
                    popup.style.textAlign = "center";
                    //insert popup
                    document.body.appendChild(popup);
                });
        });
};

const fechar = () => {
    const popup = document.querySelector(".popup");
    popup.parentNode.removeChild(popup);
};
