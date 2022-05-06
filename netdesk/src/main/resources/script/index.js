function loadPageNumbers() {}

function newPage(e) {
    window.location.href = `index.html#${e.id}`;
}

window.onload = loadPageNumbers;

// Listar anúncios na página inicial
fetch("http://localhost:3001/getAllAnuncios")
    .then((response) => response.json())
    .then((data) => {
        const anunciosDiv = document.querySelector(".anunciosDiv");

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
