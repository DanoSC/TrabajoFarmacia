function doGet() {
  var http = new XMLHttpRequest();
  let mail = document.getElementById("mail").value;
  let pass = document.getElementById("pass").value;
  http.open("GET","http://localhost:8080/PracticaFinalEntornos/Login?mail="+mail+"&pass="+pass,true);

  http.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      let respuesta = this.responseText;

      if (respuesta != "0") {
        sessionStorage.setItem("mail", mail);
        sessionStorage.setItem("session", respuesta);
        location.href = "Gestio.html";
      } else {
        alert("Los datos que has introducido no son correctos introduce los datos de nuevo");
      }
    }
  };
  http.send();
}





