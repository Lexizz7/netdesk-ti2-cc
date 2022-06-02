function loadPageNumbers() {}

function newPage(e) {
    window.location.href = `index.html#${e.id}`;
}

window.onload = loadPageNumbers;

// Listar anúncios na página inicial
const loadAnuncios = () => {
    fetch("http://localhost:3001/getAllAnuncios")
        .then((response) => response.json())
        .then((data) => {
            const anunciosDiv = document.querySelector(".anunciosDiv");
            anunciosDiv.innerHTML = "";
            console.log(data);
            data.forEach((anuncio) => {
                const link = document.createElement("a");
                link.setAttribute("href", `anuncioUnico.html#${anuncio.id}`);
                let img = "";
                switch (anuncio.so) {
                    case "Windows":
                        img = "imgs/logo/windows.png";
                        break;
                    case "Linux":
                        img = "imgs/logo/linux.svg";
                        break;
                    case "Ubuntu":
                        img = "imgs/logo/ubuntu.png";
                        break;
                    default:
                        img = "imgs/NetdeskLogo.png";
                        break;
                }

                link.innerHTML = `
                <div class="anuncio">
                    <div class="componentsLogo">
                        <img src="${img}" alt="" />
                    </div>
                    <div class="textAnuncio">
                        <h5>${anuncio.titulo}</h5>
                        <p>Descrição: ${anuncio.descricao}</p>
                        <p>Valor: R$${anuncio.valor}</p>
                    </div>
                </div>
            `;

                anunciosDiv.appendChild(link);
            });
        });
};
if (window.location.href.indexOf("index.html") > -1) {
    loadAnuncios();
}

const pesquisar = () => {
    const pesquisa = document.querySelector("#pesquisaInput").value;
    const anunciosDiv = document.querySelector(".anunciosDiv");
    anunciosDiv.innerHTML = "Carregando...";
    if (pesquisa === "") {
        return loadAnuncios();
    }
    fetch(`http://localhost:3001/pesquisarAnuncio/${pesquisa}/0`)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            anunciosDiv.innerHTML = "";
            if (data.length === 0) {
                anunciosDiv.innerHTML = "Nenhum resultado encontrado";
                return;
            }
            data.forEach((anuncio) => {
                const link = document.createElement("a");
                link.setAttribute("href", `anuncioUnico.html#${anuncio.id}`);

                link.innerHTML = `
                    <div class="anuncio">
                        <div class="componentsLogo">
                            <img src="imgs/NetdeskLogo.png" alt="" />
                        </div>
                        <div class="textAnuncio">
                            <h5>${anuncio.titulo}</h5>
                            <p>Descrição: ${anuncio.descricao}</p>
                            <p>Valor: R$${anuncio.valor}</p>
                        </div>
                    </div>
                `;

                anunciosDiv.appendChild(link);
            });
        });
};

const isLogged = () => {
    const user = localStorage.getItem("user");
    if (user === null) {
        return false;
    }
    return true;
};

const Logout = () => {
    localStorage.removeItem("user");
    window.location.href = "index.html";
};

const updateHeader = () => {
    const header = document.querySelector(".nav");
    const user = JSON.parse(localStorage.getItem("user"));
    if (isLogged()) {
        header.innerHTML = `
            <div>
                <img src="imgs/NetdeskLogo.png" alt="" class="logo" />
            </div>
            <div class="mainMenu" style="width: 40%">
                <ul class="menuUl">
                    <a href="index.html">Página Inicial</a>
                    <a href="anunciar.html">Anunciar</a>
                    <a href="perfil.html">${user.username}</a>
                </ul>
            </div>
        `;
    } else {
        header.innerHTML = `
            <div>
                <img src="imgs/NetdeskLogo.png" alt="" class="logo" />
            </div>
            <div class="mainMenu" style="width: 40%">
                <ul class="menuUl">
                    <a href="index.html">Página Inicial</a>
                    <a href="signUp.html">Criar Conta</a>
                    <a href="login.html">Login</a>
                </ul>
            </div>
        `;
    }
};
updateHeader();
