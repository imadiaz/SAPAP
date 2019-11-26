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
var storageRef = storageFirebase.ref();




function getListaCursos(){
    var table = $('#dataTable').DataTable();
    var rol = document.getElementById('rolUsuario').value;
    console.log("El rol es: ",rol);

    var tipoModificar='';
    if(rol =='AP'){
        tipoModificar="buscarCursoModificar";
    }else if(rol=='COD'){
        tipoModificar="buscarCursoModificarCOD";
    }else if(rol=='RAPE'){
        tipoModificar="buscarCursoModificarRAPE";
    }else if(rol=='RD'){
        tipoModificar="buscarCursoModificarRD";
    }else if(rol=='RH'){
        tipoModificar="buscarCursoModificarRH";
    }
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
                        "<a href="+arrayCursos[i].evidencia+" target='_blank' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a><br/>" +
                        "<button value="+arrayCursos[i].idCurso+","+arrayCursos[i].referencia+" onclick='abrirModal(this);' class='btn btn-sm btn-icon-split btn-warning'><span class='icon text-white-50'><i class='fas fa-exclamation-triangle'></i></span><span class='text'>Modificar</span></button>",
                        "<button onclick='eliminarCurso(this);' value="+arrayCursos[i].idCurso+" class='btn btn-sm btn-icon-split btn-danger'><span class='icon text-white-50'><i class='fas fa-trash'></i></span><span class='text'>Eliminar</span></button><br/>" +
                        "<form method='post' action="+tipoModificar+"><button name='idCursoModificar' type='submit' value="+arrayCursos[i].idCurso+" class='btn btn-sm btn-icon-split btn-warning'><span class='icon text-white-50'><i class='fas fa-exclamation-triangle'></i></span><span class='text'>Modificar</span></button></form>"
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
              //  table.clear().draw();
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
        evidencia:generarURL(file.name),
        referencia:file.name
    };

    $.ajax({
        type: 'POST',
        url: "http://localhost:8080/SAPAP/registrarCurso",
        data:{
          params:JSON.stringify(objetoCurso)
        },
        success: function (respuesta) {
          //  console.log("registrado");
           // console.log(respuesta.response);
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
                    icon: 'error',
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

function modificarCurso(){
    var nombre = document.getElementById('nombre').value;
    var fecha = document.getElementById('fecha').value;
    var descripcion = document.getElementById('descripcion').value;
    var tipoCurso = document.getElementById('tipo').value;
    var idCurso = document.getElementById('idCurso').value;
    //console.log(nombre,fecha,descripcion,tipoCurso,idCurso);

    var objetoCurso = {
        nombre:nombre,
        fecha:fecha,
        descripcion:descripcion,
        tipoCurso:tipoCurso,
        idCurso:idCurso
    };

    $.ajax({
        type: 'POST',
        url: "http://localhost:8080/SAPAP/actualizarCurso",
        data:{
            params:JSON.stringify(objetoCurso)
        },
        success: function (respuesta) {
           // console.log(respuesta.response);
            if(respuesta.response.status){
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Actualizado correctamente',
                    showConfirmButton: false,
                    timer: 1500
                });
            }else{
                Swal.fire({
                    position: 'top-end',
                    icon: 'error',
                    title: 'Error, intente nuevamente',
                    showConfirmButton: false,
                    timer: 1500
                });
            }
           //location.href = "../../vista/AP/CursosAP.jsp";

        },
        error: function (error) {
            console.log("al error");
            console.log(error);
        }
    });
}

function eliminarCurso(button){
    //console.log("ID",button);
    //$(button).closest('tr').remove();
    $.ajax({
        type: 'POST',
        url: "http://localhost:8080/SAPAP/eliminarCurso",
        data:{
            params:button.value
        },
        success: function (respuesta) {
            console.log(respuesta.response);
            if(respuesta.response.status){
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: 'Eliminado correctamente',
                    showConfirmButton: false,
                    timer: 1500
                });
            }else{
                Swal.fire({
                    position: 'top-end',
                    icon: 'error',
                    title: 'Error, intente nuevamente',
                    showConfirmButton: false,
                    timer: 1500
                });
            }
            $('#dataTable').DataTable().clear().draw();
            getListaCursos();
        },
        error: function (error) {
            console.log("al error");
            console.log(error);
        }
    });
}
var nuevoFile=null;
function abrirModal(button){
   // console.log("abrir modal");
    //console.log(button.value);
    var values = button.value.split(",",2);

    var nuevoArchivo = document.getElementById('evidenciaNuevoArchivo');
    document.getElementById('evidenciaNuevoArchivo').value='';
    nuevoFile=null;
    nuevoArchivo.addEventListener('change',function (ev) {
        nuevoFile = ev.target.files[0];
    });
    document.getElementById("identificadorCurso").value=values[0];
    document.getElementById("nombreArchivo").value=values[1];
    $('#modalFile').modal('show');
}

function guardarNuevoArchivo(){
    //console.log(storageRef);
    //console.log(nuevoFile);
    // Create a reference to the file to delete

// // Delete the file
//     desertRef.delete().then(function() {
//         // File deleted successfully
//     }).catch(function(error) {
//         // Uh-oh, an error occurred!
//     });
    if(nuevoFile){
        deleteFile = document.getElementById("nombreArchivo").value;
        deleteFile = "evidencias/"+deleteFile;
        //console.log(storageRef);
        var desertRef = storageFirebase.ref().child(deleteFile);
       // console.log(desertRef);
        desertRef.delete();

        var storageRef = storageFirebase.ref('evidencias/' + nuevoFile.name);
        storageRef.put(nuevoFile);
            var objetoArchivo = {
                ref:nuevoFile.name,
                url:generarURL(nuevoFile.name),
                cursoID:document.getElementById("identificadorCurso").value
            };

            $.ajax({
                type: 'POST',
                url: "http://localhost:8080/SAPAP/actualizarArchivo",
                data:{
                    params:JSON.stringify(objetoArchivo)
                },
                success: function (respuesta) {
                    console.log(respuesta.response);
                    if(respuesta.response.status){
                        Swal.fire({
                            position: 'top-end',
                            icon: 'success',
                            title: 'Modificado correctamente',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }else{
                        Swal.fire({
                            position: 'top-end',
                            icon: 'error',
                            title: 'Error, intente nuevamente',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }
                    $("#modalFile").modal('hide');
                    $('#dataTable').DataTable().clear().draw();
                    getListaCursos();
                },
                error: function (error) {
                    console.log("al error");
                    console.log(error);
                }
            });

    }else{
        $("#modalFile").modal('hide');
    }
}


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


