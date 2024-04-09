document.addEventListener("DOMContentLoaded", function() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/tickets");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                var tickets = JSON.parse(xhr.responseText);
                var origins = extractUniqueOrigins(tickets);
                var destinations = extractUniqueDestinations(tickets);
                populateSelect("pickup", origins);
                populateSelect("destination", destinations);
            } else {
                console.error("Erro ao carregar destinos");
            }
        }
    };
    xhr.send();
});

function extractUniqueOrigins(tickets) {
    var origins = [];
    tickets.forEach(function(ticket) {
        if (!origins.includes(ticket.origem)) {
            origins.push(ticket.origem);
        }
    });
    return origins;
}

function extractUniqueDestinations(tickets) {
    var destinations = [];
    tickets.forEach(function(ticket) {
        if (!destinations.includes(ticket.destino)) {
            destinations.push(ticket.destino);
        }
    });
    return destinations;
}

function populateSelect(selectId, options) {
    var select = document.getElementById(selectId);
    options.forEach(function(option) {
        var optionElement = document.createElement("option");
        optionElement.value = option;
        optionElement.text = option;
        select.appendChild(optionElement);
    });
}

function redirectToTrips() {
    var pickup = document.getElementById("pickup").value;
    var destination = document.getElementById("destination").value;

    if (pickup !== "" && destination !== "") {
        // Armazenar os valores de origem e destino temporariamente no sessionStorage
        sessionStorage.setItem('pickup', pickup);
        sessionStorage.setItem('destination', destination);
    
        // Redirecionar para a página de viagens
        window.location.href = "trips.html";

    } else {
        alertMessage.style.display = "block";
        console.log("Please select both Pick-Up and Destination");
    }
}
