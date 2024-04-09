function checkBuy() {
    var ticketId = sessionStorage.getItem("id");
    console.log(ticketId);
    var nome = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var numeroCartao = document.getElementById("ccardnumber").value;
    var validade = document.getElementById("ccardyear").value;
    var cvv = document.getElementById("ccardcvv").value;
    var nomeCartao = document.getElementById("ccardname").value;

    if (nome && email && numeroCartao && validade && cvv && nomeCartao) {
        // Crie um objeto com os dados do formulário
        var reservaData = {
            idticket: ticketId, 
            nome: nome,
            email: email,
            numeroCartao: numeroCartao,
            validade: validade,
            cvv: cvv,
            nomeCartao: nomeCartao
        };

        // Faça uma solicitação AJAX para o endpoint newreserve
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8080/newreserve", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 201) {
                    var response = JSON.parse(xhr.responseText);
                    var reservaId = response.id;
                    sessionStorage.setItem("reservaId", reservaId);
                    window.location.href = "confirmation.html";
                } else {
                    console.error("Erro ao fazer reserva");
                }
            }
        };
        xhr.send(JSON.stringify(reservaData));
    } else {
        document.getElementById("alertMessage").style.display = "block";
    }
}
