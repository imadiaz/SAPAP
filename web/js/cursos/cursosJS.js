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
//var $ = jQuery.noConflict();


function getListaCursos(){
    var table = $('#dataTable').DataTable();
    $.ajax({
            type: 'POST',
            url: "http://localhost:8080/SAPAP/listaCursos",
            success: function (respuesta) {
                //console.log("al success");
               // console.log(respuesta.response);
                var rows = "";
                var arrayCursos =respuesta.response.listaCursos;
                for (var i = 0; i<arrayCursos.length;i++){
                    table.row.add([
                        arrayCursos[i].nombre,
                        arrayCursos[i].fecha,
                        arrayCursos[i].descripcion,
                        arrayCursos[i].tipoCurso,
                        "<a href="+arrayCursos[i].evidencia+" target='_blank' class='btn btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>",
                        "<button class='btn btn-icon-split btn-danger'><span class='icon text-white-50'><i class='fas fa-trash'></i></span><span class='text'>Eliminar</span></button>"
                        ]).draw(false);
                }

                //document.getElementById('bodyTable').innerHTML=rows;
                // console.log("Nombre:",arrayCursos[i].nombre);
                // rows+="<tr class='odd'><td>"+(i+1)+"</td>" +
                //     "<td>"+arrayCursos[i].nombre+"</td>" +
                //     "<td>"+arrayCursos[i].fecha+"</td>" +
                //     "<td>"+arrayCursos[i].descripcion+"</td>" +
                //     "<td>"+arrayCursos[i].tipoCurso+"</td>" +
                //     "<td><a href="+arrayCursos[i].evidencia+" target='_blank' class='btn btn-sm btn-info'>Visualizar</a></td>" +
                //     "<td><button class='btn btn-danger btn-sm'>Eliminar</button></td></tr>";
            },
            error: function (error) {
                console.log("al error");
                console.log(error);
            }
        }
    );
}

function guardarCurso(){
    var nombre = document.getElementById('nombre').value;
    var fecha = document.getElementById('fecha').value;
    var descripcion = document.getElementById('descripcion').value;
    var tipoCurso = document.getElementById('tipo').value;

    var storageRef = storageFirebase.ref('evidencias/' + file.name);
    storageRef.put(file);

    var objetoCurso = {
        nombre:nombre,
        fecha:fecha,
        descripcion:descripcion,
        tipoCurso:tipoCurso,
        evidencia:generarURL(file.name)
    };

    $.ajax({
        type: 'POST',
        url: "http://localhost:8080/SAPAP/registrarCurso",
        data:{
          params:JSON.stringify(objetoCurso)
        },
        success: function (respuesta) {
            console.log("registrado");
            console.log(respuesta.response);
            if(respuesta.response.status){
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Registrado correctamente',
                    showConfirmButton: false,
                    timer: 1500
                });
               var form =  document.getElementById("formCurso");
                form.reset();
            }else{
                Swal.fire({
                    position: 'top-end',
                    icon: 'danger',
                    title: 'Error, intente nuevamente',
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        },
        error: function (error) {
            console.log("al error");
            console.log(error);
        }
    });

    //dom.submit();


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


