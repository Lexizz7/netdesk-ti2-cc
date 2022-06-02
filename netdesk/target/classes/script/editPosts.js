let i=0;

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


function excluir(){

}

function editar(){

}

function change(e) {
    let checkbox = document.getElementById(`${e.classList[0]}`);
    
    let el = document.querySelectorAll('.threePoints');
    let checkboxFor = document.querySelectorAll('.deleteMenu');

    if(checkbox.checked == false){

        for(let j=0; j<el.length; j++){
            el[j].classList.remove('CDO');
        }
        for(let j=0; j<checkboxFor.length; j++){
            if(j != e.classList[0]){
                checkboxFor[j].checked = false;
            }
        }

        el[e.classList[0]].classList.add('CDO');

    }else{
        el[e.classList[0]].classList.remove('CDO');
    }
}