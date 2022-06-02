const validateCPF = (cpf) => {
    let sum = 0;
    let rest = 0;
    let i = 0;
    let digit = 0;
    let cpfNumber = cpf.replace(/[^\d]+/g, "");

    if (cpfNumber.length !== 11) {
        return false;
    }

    for (i = 1; i <= 9; i++) {
        sum = sum + parseInt(cpfNumber.substring(i - 1, i)) * (11 - i);
    }

    rest = (sum * 10) % 11;
    if (rest === 10 || rest === 11) {
        rest = 0;
    }

    if (rest !== parseInt(cpfNumber.substring(9, 10))) {
        return false;
    }
    sum = 0;
    for (i = 1; i <= 10; i++) {
        sum = sum + parseInt(cpfNumber.substring(i - 1, i)) * (12 - i);
    }
    rest = (sum * 10) % 11;
    if (rest === 10 || rest === 11) {
        rest = 0;
    }

    if (rest !== parseInt(cpfNumber.substring(10, 11))) {
        return false;
    }
    return true;
};

const validatePassword = (password, confirmPassword) => {
    if (password !== confirmPassword || password.length < 6 || password.length > 20) {
        return false;
    }
    return true;
};

const validateEmail = (email) => {
    const re =
        /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
};

const createAccount = () => {
    const name = document.getElementById("nomeInput").value;
    const email = document.getElementById("emailInput").value;
    const cpf = document.getElementById("CPFInput").value;

    const date = document.getElementById("dateInput").value;
    const dateSplit = date.split("-");
    const dateFormat = dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0];

    const password = document.getElementById("passwordInput").value;
    const confirmPassword = document.getElementById("repeatPasswordInput").value;

    if (name === "" || email === "" || cpf === "" || date === "" || password === "" || confirmPassword === "") {
        alert("Preencha todos os campos!");
        return;
    }
    if (!validateCPF(cpf)) {
        alert("CPF inválido!");
        return;
    }
    if (!validateEmail(email)) {
        alert("Email inválido!");
        return;
    }
    if (!validatePassword(password, confirmPassword)) {
        alert("Senhas inválidas!");
        return;
    }

    const user = {
        nome: name,
        email: email,
        cpf: cpf,
        dataNascimento: dateFormat,
        senha: password,
        username: name,
    };

    fetch("http://localhost:3001/cadastrar/", {
        method: "POST",
        body: JSON.stringify(user),
    }).then((response) => {
        if (response.status === 200) {
            alert("Cadastro realizado com sucesso!");
            window.location.href = "./login.html";
        } else {
            alert("Erro ao cadastrar!");
        }
    });
};

const login = () => {
    const name = document.getElementById("nomeInput").value;
    const password = document.getElementById("passwordInput").value;

    if (name === "" || password === "") {
        alert("Preencha todos os campos!");
        return;
    }
    if (validateCPF(name)) {
        const user = {
            login: name,
            senha: password,
            tipo: "cpf",
        };

        fetch("http://localhost:3001/login/", {
            method: "POST",
            body: JSON.stringify(user),
        })
            .then((response) => response.json())
            .then((data) => {
                if (data.msg) {
                    alert(data.msg);
                    return;
                }
                localStorage.setItem("user", JSON.stringify(data));
                window.location.href = "./index.html";
            });
    } else if (validateEmail(name)) {
        const user = {
            login: name,
            senha: password,
            tipo: "email",
        };

        fetch("http://localhost:3001/login/", {
            method: "POST",
            body: JSON.stringify(user),
        })
            .then((response) => response.json())
            .then((data) => {
                if (data.msg) {
                    alert(data.msg);
                    return;
                }
                localStorage.setItem("user", JSON.stringify(data));
                window.location.href = "./index.html";
            });
    } else {
        const user = {
            login: name,
            senha: password,
            tipo: "usuario",
        };

        fetch("http://localhost:3001/login/", {
            method: "POST",
            body: JSON.stringify(user),
        })
            .then((response) => response.json())
            .then((data) => {
                if (data.msg) {
                    alert(data.msg);
                    return;
                }
                localStorage.setItem("user", JSON.stringify(data));
                window.location.href = "./index.html";
            });
    }
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
