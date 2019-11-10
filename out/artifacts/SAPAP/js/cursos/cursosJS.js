// Your web app's Firebase configuration
var firebaseConfig = {
    apiKey: "AIzaSyBCHbixrhvWLQcn9ygTf864Bd_9ca3YgVs",
    authDomain: "sapap-1c0e0.firebaseapp.com",
    databaseURL: "https://sapap-1c0e0.firebaseio.com",
    projectId: "sapap-1c0e0",
    storageBucket: "sapap-1c0e0.appspot.com",
    messagingSenderId: "562881741479",
    appId: "1:562881741479:web:32416284c88fa78385d182",
    measurementId: "G-WEELC7869N"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);
firebase.analytics();

var storageFirebase = firebase.storage();
var file;
var $ = jQuery.noConflict();

function getListaCursos(){
    console.log("Entro al curso js al getListacursos");
}

function guardarCurso(){
    var nombre = document.getElementById('nombre').value;
    var fecha = document.getElementById('fecha').value;
    var descripcion = document.getElementById('descripcion').value;
    var tipoCurso = document.getElementById('tipo').value;


    var dom = document.getElementById('formAgregarCurso');
    //dom.submit();
    console.log(dom);

   // var storageRef = storageFirebase.ref('evidencias/' + file.name);
   // var task = storageRef.put(file);
   // //console.log(task);
   //  console.log(generarURL(file.name));
   //  $.ajax({
   //      type: 'POST',
   //      url: 'test.action',
   //
   //      success: function (response) {
   //          console.log(response.name);
   //
   //      },
   //      error: function (error) {
   //          console.log(error);
   //      }
   //  });


}
ejecutarEvento();

function ejecutarEvento(){
    console.log("al ejecutar");
    var archivo = document.getElementById('evidencia');
    archivo.addEventListener('change',function (ev) {
         file = ev.target.files[0];
    });
}

function  generarURL(name) {
    var HOST ="https://firebasestorage.googleapis.com/v0/b/sapap-1c0e0.appspot.com/o/evidencias%2F";
    var TOKEN = "?alt=media&token=1e17563a-798d-4fbd-ad31-ddbd61853280";

    return url_id=HOST+name+TOKEN;
}


