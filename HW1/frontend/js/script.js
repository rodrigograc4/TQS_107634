function openLink(evt, linkName) {
  var i, x, tablinks;
  x = document.getElementsByClassName("myLink");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablink");
  for (i = 0; i < x.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
  }
  document.getElementById(linkName).style.display = "block";
  evt.currentTarget.className += " w3-red";
}
  
// Click on the first tablink on load
document.getElementsByClassName("tablink")[0].click();


function checkAndRedirect() {
  var pickup = document.getElementById("pickup").value;
  var destination = document.getElementById("destination").value;
  var alertMessage = document.getElementById("alertMessage");
  if (pickup !== "" && destination !== "") {
    window.location.href = "trips.html";
  } else {
    alertMessage.style.display = "block";
    console.log("Please select both Pick-Up and Destination");
  }
}

function submitForm(formId) {
  window.location.href = "purchase.html";
}