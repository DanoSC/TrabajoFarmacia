function logout() {
    sessionStorage.clear();
    location.href = "login.html";
  }

  function tabla() {
    let http = new XMLHttpRequest();
    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");

    http.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        let respuesta = http.responseText;
        document.getElementById("tabla").innerHTML = respuesta;
      }
    };


  http.open("GET","http://localhost:8080/PracticaFinalEntornos/ServeXips?mail="+mail+"&session="+session,true);
  http.send();
}