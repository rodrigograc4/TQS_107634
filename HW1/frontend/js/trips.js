document.addEventListener("DOMContentLoaded", function() {
    var pickup = sessionStorage.getItem('pickup');
    var destination = sessionStorage.getItem('destination');

    // Exibir os valores de origem e destino na página
    document.getElementById('pickup').innerText = pickup;
    document.getElementById('destination').innerText = destination;

    // Fazer solicitação AJAX para obter as viagens disponíveis
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/tickets");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                var tickets = JSON.parse(xhr.responseText);
                var filteredTrips = filterTripsByOriginAndDestination(tickets, pickup, destination);
                if(filteredTrips.length === 0) {
                    displayNoTripsMessage();
                } else {
                    displayTrips(filteredTrips);
                }
            } else {
                console.error("Erro ao carregar as viagens");
            }
        }
    };
    xhr.send();
});

function filterTripsByOriginAndDestination(tickets, pickup, destination) {
    // Filtra as viagens com base no local de partida e destino
    return tickets.filter(function(ticket) {
        return ticket.origem === pickup && ticket.destino === destination;
    });
}

function displayTrips(tickets) {
    var pickup = sessionStorage.getItem('pickup');
    var destination = sessionStorage.getItem('destination');
    
    // Exibe as viagens filtradas na página
    var tripTableContainer = document.getElementById('tripTableContainer');
    var tripTable = document.createElement('table');
    tripTable.className = 'table';
    tripTable.style.width = '100%';
    tripTable.style.marginTop = '20px';

    // Cria o cabeçalho da tabela
    var thead = document.createElement('thead');
    thead.innerHTML = `<tr><td>Choose</td><td>Company</td><td>Origin: ${pickup}</td><td>Destination: ${destination}</td><td>Price</td></tr>`;
    tripTable.appendChild(thead);

    // Adiciona cada viagem à tabela
    var tbody = document.createElement('tbody');
    tickets.forEach(function(trip) {
        var row = document.createElement('tr');
        row.innerHTML = `<form id="${trip.id}" method="post" action="purchase.html">
                            <td><input type="submit" class="w3-button w3-black w3-hover-black" value="Reserve Bus Ticket" onclick="redirectToPurchase('${trip.id}')"></td>
                            <td>${trip.empresa}</td>
                            <td>${trip.partida}</td>
                            <td>${trip.chegada}</td>
                            <td>${trip.preco.toFixed(2)} EUR</td>
                            <input type="hidden" value="${trip.preco}" name="price">
                            <input type="hidden" value="${trip.empresa}" name="empresa">
                            <input type="hidden" name="fromPort" value="${trip.partida}">
                            <input type="hidden" name="toPort" value="${trip.chegada}">
                        </form>`;
        tbody.appendChild(row);
    });
    tripTable.appendChild(tbody);

    tripTableContainer.appendChild(tripTable);
}

function displayNoTripsMessage() {
    var tripTableContainer = document.getElementById('tripTableContainer');
    var noTripsMessage = document.createElement('p');
    noTripsMessage.innerText = 'Não há viagens disponíveis.';
    tripTableContainer.appendChild(noTripsMessage);
}

function redirectToPurchase(id) {
    sessionStorage.setItem('id', id);
    window.location.href = 'purchase.html';
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
                
                var priceElements = document.querySelectorAll('#tripTableContainer tbody tr td:nth-child(6)');
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


