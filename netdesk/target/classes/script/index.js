const anuncioPorPage = 10;

// Listar anúncios na página inicial
const loadAnuncios = () => {
    let orderBy = document.querySelector("#orderBySelect").value;
    let order = document.querySelector("#orderSelect").value;

    if (orderBy === "") orderBy = "id";
    else if (orderBy === "data") orderBy = "id";
    else if (orderBy === "nome") orderBy = "titulo";
    else if (orderBy === "preco") orderBy = "valor";

    if (order === "") order = "ASC";
    else if (orderBy === "ASC") order = "ASC";
    else if (order === "DESC") order = "DESC";

    fetch(`http://localhost:3001/getAllAnuncios/${orderBy}/${order}`)
        .then((response) => response.json())
        .then((data) => {
            //pagination
            const activePage = Number(window.location.hash.split("#")[1]) || 1;
            const pageNumbersDiv = document.querySelector(".pageNumbersDiv");
            pageNumbersDiv.innerHTML = "";
            const pageNumbers = Math.ceil(data.length / anuncioPorPage);
            for (let i = 0; i < pageNumbers; i++) {
                const pageNumber = document.createElement("span");
                pageNumber.className = "pageNumbers";
                pageNumber.innerHTML = i + 1;
                pageNumber.setAttribute("onclick", `newPage(${i})`);
                pageNumbersDiv.appendChild(pageNumber);

                if (i === activePage - 1) {
                    pageNumber.classList.add("active");
                }
            }
            const anunciosDiv = document.querySelector(".anunciosDiv");
            anunciosDiv.innerHTML = "";
            for (let i = (activePage - 1) * anuncioPorPage; i < activePage * anuncioPorPage; i++) {
                if (data[i] === undefined) {
                    break;
                }
                const link = document.createElement("a");
                link.setAttribute("href", `anuncioUnico.html#${data[i].id}`);

                let img = "";
                switch (data[i].so) {
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
                        <h5>${data[i].titulo}</h5>
                        <p>Descrição: ${data[i].descricao}</p>
                        <p>Valor: R$${data[i].valor}</p>
                    </div>
                </div>
            `;

                anunciosDiv.appendChild(link);
            }
        });
};

const newPage = (page) => {
    window.location.hash = page + 1;
    pesquisar();
};

if (window.location.href.indexOf("index.html") > -1) {
    loadAnuncios();
}

const pesquisar = () => {
    let pesquisa = document.querySelector("#pesquisaInput").value;
    const valor = parseFloat(document.querySelector("#valorMaxInput").value) || 0;
    const anunciosDiv = document.querySelector(".anunciosDiv");
    anunciosDiv.innerHTML = "Carregando...";
    if (pesquisa === "") {
        pesquisa = "*";
    }
    if (pesquisa === "*" && valor === 0) {
        return loadAnuncios();
    }

    let orderBy = document.querySelector("#orderBySelect").value;
    let order = document.querySelector("#orderSelect").value;

    if (orderBy === "") orderBy = "id";
    else if (orderBy === "data") orderBy = "id";
    else if (orderBy === "nome") orderBy = "titulo";
    else if (orderBy === "preco") orderBy = "valor";

    if (order === "") order = "ASC";
    else if (orderBy === "ASC") order = "ASC";
    else if (order === "DESC") order = "DESC";

    fetch(`http://localhost:3001/pesquisarAnuncio/${pesquisa}/${valor}/${orderBy}/${order}`)
        .then((response) => response.json())
        .then((data) => {
            if (data.length === 0) {
                anunciosDiv.innerHTML = "Nenhum resultado encontrado";
                return;
            }

            //pagination
            const activePage = Number(window.location.hash.split("#")[1]) || 1;
            const pageNumbersDiv = document.querySelector(".pageNumbersDiv");
            pageNumbersDiv.innerHTML = "";
            const pageNumbers = Math.ceil(data.length / anuncioPorPage);
            for (let i = 0; i < pageNumbers; i++) {
                const pageNumber = document.createElement("span");
                pageNumber.className = "pageNumbers";
                pageNumber.innerHTML = i + 1;
                pageNumber.setAttribute("onclick", `newPage(${i})`);
                pageNumbersDiv.appendChild(pageNumber);

                if (i === activePage - 1) {
                    pageNumber.classList.add("active");
                }
            }
            anunciosDiv.innerHTML = "";
            for (let i = (activePage - 1) * anuncioPorPage; i < activePage * anuncioPorPage; i++) {
                if (data[i] === undefined) {
                    break;
                }
                const link = document.createElement("a");
                link.setAttribute("href", `anuncioUnico.html#${data[i].id}`);

                let img = "";
                switch (data[i].so) {
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
                        <h5>${data[i].titulo}</h5>
                        <p>Descrição: ${data[i].descricao}</p>
                        <p>Valor: R$${data[i].valor}</p>
                    </div>
                </div>
            `;

                anunciosDiv.appendChild(link);
            }
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
                    <a href="Profile.html">${user.username}</a>
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
