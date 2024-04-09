// Função para fazer uma solicitação HTTP GET
function httpGet(url, callback) {
  var xhr = new XMLHttpRequest();
  xhr.open("GET", url, true);
  xhr.onreadystatechange = function () {
      if (xhr.readyState == 4) {
          if (xhr.status == 200) {
              callback(xhr.responseText);
          } else {
              console.error("Erro na solicitação: " + xhr.status);
          }
      }
  };
  xhr.send();
}

// Função para preencher as informações na página HTML
function preencherInformacoes() {
  var reserveId = sessionStorage.getItem("reservaId");
  var ticketId = sessionStorage.getItem("id"); 

  function normalizeCardNumber(cardNumber) {
    // Remover todos os caracteres não numéricos
    return cardNumber.replace(/\D/g, '');
}

// Solicitar informações da reserva
httpGet("http://localhost:8080/reserve/" + reserveId, function (response) {
    var reserve = JSON.parse(response);
    document.getElementById("name").innerText = reserve.nome; 
    document.getElementById("email").innerText = reserve.email;
    
    // Obter o número do cartão e normalizá-lo
    var cardNumber = normalizeCardNumber(reserve.numeroCartao);
    
    // Aplicar a máscara ao número do cartão normalizado
    var groups = cardNumber.match(/.{1,4}/g) || []; // Dividir em grupos de 4 caracteres
    var maskedGroups = groups.map(function(group, index) {
        return index < groups.length - 1 ? 'X'.repeat(group.length) : group;
    });
    var maskedCardNumber = maskedGroups.join(' ');
    
    // Exibir o número do cartão mascarado
    document.getElementById("ccardnumber").innerText = maskedCardNumber;
});

  // Solicitar informações do ticket
  httpGet("http://localhost:8080/tickets/" + ticketId, function (response) {
      var ticket = JSON.parse(response);
      document.getElementById("origin").innerText = ticket.origem + " » " + ticket.partida; 
      document.getElementById("destination").innerText = ticket.destino + " » " + ticket.chegada; 
      document.getElementById("company").innerText = ticket.empresa; 
      document.getElementById("price").innerText = ticket.preco.toFixed(2) + " EUR";
  });
}


function changeCurrency() {
  var currencyDropdown = document.getElementById('currencyDropdown');
  var selectedCurrency = currencyDropdown.value;
  
  // Fazer solicitação AJAX para obter as taxas de câmbio
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "https://open.er-api.com/v6/latest/" + selectedCurrency);
  xhr.onreadystatechange = function() {
      if (xhr.readyState === XMLHttpRequest.DONE) {
          if (xhr.status === 200) {
              var exchangeRates = JSON.parse(xhr.responseText);
              
              var priceElements = document.querySelectorAll('#price');
              priceElements.forEach(function(priceElement) {
                  var priceandcoin = priceElement.innerText.split(' ');
                  var originalPrice = parseFloat(priceandcoin[0]);
                  var previousCurrency = priceandcoin[1];
                  var newPrice = (originalPrice / exchangeRates.rates[previousCurrency]).toFixed(2);
                  priceElement.innerText = newPrice + ' ' + selectedCurrency;
              });
          } else {
              console.error("Erro ao obter as taxas de câmbio");
          }
      }
  };
  xhr.send();
}

window.onload = preencherInformacoes;