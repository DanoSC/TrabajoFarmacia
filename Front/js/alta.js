function enviar() {
    let http = new XMLHttpRequest();
    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");
    let XipId = document.getElementById("XipId").value;
    let medicine = document.getElementById("idMed").value;
    let patient = document.getElementById("pMail").value;
    let date = document.getElementById("Date").value;
  
    http.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        let resposta = http.responseText;
        if (resposta == "insertado") {
          document.getElementById("XipId").value = "";
          document.getElementById("idMed").value = "";
          document.getElementById("pMail").value = "";
          alert("Se ha insertado el xip correctamente");
        } else {
          alert("No se ha podido insertar el xip prueba de otra manera");
        }
      }
    };

    let datos = "mail="+mail+"&session="+session+"&XipId="+XipId+"&medicine="+medicine+"&patient="+patient+"&date="+date;
    http.open("POST", "http://localhost:8080/PracticaFinalEntornos/Release?"+datos,true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send();
  }

function getMedicines(){
        let http = new XMLHttpRequest();
        let mail = sessionStorage.getItem("mail");
        let session = sessionStorage.getItem("session");

        http.onreadystatechange = function () {
          if (this.readyState == 4 && this.status == 200) {

            let respuesta = JSON.parse(http.responseText);
            let selectElement = document.getElementById("idMed");

            for (let i = 0; i < respuesta.length; i++) {
              let option = document.createElement("option");
              option.value = respuesta[i].id;
              option.textContent = respuesta[i].name;
              selectElement.appendChild(option);
            }
      
          
        }
      };
        http.open("GET","http://localhost:8080/PracticaFinalEntornos/ServeMedicines?mail="+mail+"&session="+session,true);
        http.send();
      
}

function getPatients() {
    let http = new XMLHttpRequest();
    let mail = sessionStorage.getItem("mail");
    let session = sessionStorage.getItem("session");

    http.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {

        let respuesta = JSON.parse(http.responseText);
        let selectElement = document.getElementById("pMail");

        for (let i = 0; i < respuesta.length; i++) {
          let option = document.createElement("option");
          option.value = respuesta[i];
          option.textContent = respuesta[i];
          selectElement.appendChild(option);
        }
      }
    };
  
    http.open(
        "GET","http://localhost:8080/PracticaFinalEntornos/ServePatients?mail="+mail+"&session="+session,true);
      http.send();
  }

