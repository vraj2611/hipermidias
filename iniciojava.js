function palindromo() {
    texto = prompt("Informe o texto para verificar palindromo:");
    var naoehpalindromo = 0;
    for (x = 0; x < texto.length / 2 ; x++ ){
        if (texto[x] != texto[texto.length - x - 1]){
            naoehpalindromo = 1;
            break;
        }
    }
    if (naoehpalindromo == 1){
        alert("Nao é palindromo");
    } else {
        alert("É palindromo");
    }
}

function calcfatorial(x){
    x = prompt("Informe o numero para calcular fatorial:");
    alert(fatorial(x));
}

function fatorial(x) {
    if (x < 2) {
        return 1;
    } else {
        return x * fatorial(x - 1);
    }
}

function validaremail() {
    email = prompt("Informe email para validar:");
    tamanho = email.length;
    arroba = email.indexOf('@');
    ponto = email.indexOf(".");
    arroba2 = email.lastIndexOf('@');
    if (tamanho > ponto + 2 && ponto > arroba + 1 && arroba > 0 && arroba == arroba2) {
        alert("É um e-mail");
    } else {
        alert("Não é um email");
    }
}
