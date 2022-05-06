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
