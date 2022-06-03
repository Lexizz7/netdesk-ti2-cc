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

const anunciosUsuario = () => {
    const user = JSON.parse(localStorage.getItem("user"));
    fetch(`http://localhost:3001/getAnunciosByUsuario/${user.cpf}`)
        .then((response) => response.json())
        .then((data) => {
            const anunciosDiv = document.querySelector("#anunciosProfileDiv");
            for (let i = 0; i < data.length; i++) {
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
anunciosUsuario();

const signOut = () => {
    localStorage.removeItem("user");
    window.location.href = "index.html";
};
document.querySelector(".signout").addEventListener("click", signOut);
