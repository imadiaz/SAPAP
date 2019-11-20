var raiz = window.location.origin + '/SAPAP/';
var lista = [];
var lista2 = [];
var lista3 = [];

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
var nuevoFile=null;
//var $ = jQuery.noConflict();
var storageRef = storageFirebase.ref();


function consultarJustificantesPendientes() {
    $.ajax({
            type: 'POST',
            url: raiz + 'consultarJustificantesPendientes',
            success: function (respuesta) {
                var lista = respuesta.respuestas.listaJPendientes;

                $('#tableBody').html('');
                for (var i = 0; i < lista.length; i++) {
                    $('#tableBody').append('<tr><td>' + lista[i].fechaElaboracion + '</td>' +
                        '<td> AP Estático</td>' +
                        '<td>' + lista[i].justifica.nombre + '</td>' +
                        '<td>' + lista[i].proyecto.nombre + '</td>' +
                        '<td>' + lista[i].motivoJustifica + '</td>' +
                        '<td>' +
                        '<a href="'+lista[i].evidencia+'" target="_blank"><button class="btn btn-info""><i class="fas fa-info-circle">Ver</i></button></a>' +
                        '<button id="btnModi" class="btn btn-info" data-nombreArchivo="' + lista[i].referencia + '" data-idjustificante="' + lista[i].idJustificante + '" data-toggle="modal" data-target="#modalActualizarArchivo"><i class="fas fa-info-circle">Modificar</i></button>' +
                        '</td>' +
                        '<td>' +
                        '<a href="ModificarJustificante.jsp?prodId=' + lista[i].idJustificante + '"><button class="btn btn-warning"><i class="fas fa-pen">Modificar</i></button></a>' +
                        '<button id="btnEli" data-justificante="' + lista[i].idJustificante + '" class="btn btn-danger" data-toggle="modal" data-target="#modalEliminarJustificante"><i class="fas fa-trash">Eliminar</i></button>' +
                        '</td>' +
                        '</tr>')
                }
            }
        }
    );
};

function consultarHistorialJustificantes() {
    $.ajax({
            type: 'POST',
            url: raiz + 'consultarHistorialJustificantes',
            success: function (respuesta) {
                var lista = respuesta.respuestas.listaJPendientes;
                var motivo;
                $('#tableBody').html('');
                for (var i = 0; i < lista.length; i++) {
                    if(lista[i].justifica.motivoRechazo == null){
                        motivo ='Este justificante fue aprobado';
                    } else {
                        motivo = lista[i].justifica.motivoRechazo;
                    }
                    $('#tableBody').append('<tr><td>' + lista[i].fechaElaboracion + '</td>' +
                        '<td> AP Estático</td>' +
                        '<td>' + lista[i].justifica.nombre + '</td>' +
                        '<td>' + lista[i].proyecto.nombre + '</td>' +
                        '<td>' +motivo+'</td>'+
                        '<td>' +
                        '<a href="'+lista[i].evidencia+'" target="_blank"><button class="btn btn-info""><i class="fas fa-info-circle">Ver</i></button></a>' +
                        '</td>' +
                        '</tr>')
                }
            }
        }
    );
};


function consultarSelectAgregar(id) {
    $.ajax({
            type: 'POST',
            url: raiz + 'consultarSelectAgregar',
            data: {
                parametro: id
            },
            success: function (respuesta) {
                lista = respuesta.respuestas.listaRape;
                lista2 = respuesta.respuestas.listaProyecto;
                document.getElementById('rape').innerHTML = '';
                document.getElementById('proyecto').innerHTML = '';

                for (var i = 0; i < lista.length; i++) {
                    $('#rape').append('<option value="' + lista[i].idPersona + "-" + lista[i].nombre + "-" + lista[i].primerApellido + "-" + lista[i].segundoApellido + '">'
                        + lista[i].nombre + "  " + lista[i].primerApellido + "  " + lista[i].segundoApellido
                        + '</option>');
                }
                for (var i = 0; i < lista2.length; i++) {
                    $('#proyecto').append('<option value="' + lista2[i].nombre + '">'
                        + lista2[i].nombre
                        + '</option>');
                }
            }
        }
    );
};

function agregarJustificante() {

    //justificantes/ es el nombre de la carpeta en firebase
    var storageRef = storageFirebase.ref('justificantes/' + file.name);
    //el metodo put es ell que sube la imagen
    storageRef.put(file);

    var justificante = {
        rape: document.getElementById('rape').value,
        proyecto: document.getElementById('proyecto').value,
        motivo: document.getElementById('motivo').value,
        id: 1,
        evidencia:generarURL(file.name),
        referencia:file.name
    };
    $.ajax({
        type: 'POST',
        url: raiz + 'agregarJustificante',
        data: {
            parametro: JSON.stringify(justificante)
        },
        success: function (respuesta) {
            alert(respuesta.respuestas.mensaje);
            consultarJustificantesPendientes();
            window.open("http://localhost:8080/SAPAP/vista/AP/InicioAP.jsp", "_self")
        }
    });
}

function consultarJustificantePendienteEspecifico(idJustificante) {
    $.ajax({
        type: 'POST',
        url: raiz + 'consultarJustificantePendienteEspecifico',
        data: {
            parametro: idJustificante
        },
        success: function (respuesta) {

            lista = respuesta.respuestas.listaRape;
            lista2 = respuesta.respuestas.listaProyecto;
            lista3 = respuesta.respuestas.listaJustificante;

            $('#rape').append('<option selected value="' + lista3.justifica.idPersona + "-" + lista3.justifica.nombre + '">'
                + lista3.justifica.nombre
                + '</option>');
            $('#proyecto').append('<option selected value="' + lista3.proyecto.nombre + '">'
                + lista3.proyecto.nombre
                + '</option>');
            for (var i = 0; i < lista.length; i++) {
                $('#rape').append('<option value="' + lista[i].idPersona + "-" + lista[i].nombre + " " + lista[i].primerApellido + " " + lista[i].segundoApellido + '">'
                    + lista[i].nombre + "  " + lista[i].primerApellido + "  " + lista[i].segundoApellido
                    + '</option>');
            }
            for (var i = 0; i < lista2.length; i++) {
                $('#proyecto').append('<option value="' + lista2[i].nombre + '">'
                    + lista2[i].nombre
                    + '</option>');
            }

            document.getElementById('motivo').value = lista3.motivoJustifica;
            document.getElementById('idJustificante').value = lista3.idJustificante;
        }
    });
};

function modificarJustificante() {
    var justificante = {
        rape: document.getElementById('rape').value,
        proyecto: document.getElementById('proyecto').value,
        motivo: document.getElementById('motivo').value,
        id: document.getElementById('idJustificante').value,
    };
    $.ajax({
        type: 'POST',
        url: raiz + 'modificarJustificante',
        data: {
            parametro: JSON.stringify(justificante)
        },
        success: function (respuesta) {
            alert(respuesta.respuestas.mensaje);
            consultarJustificantesPendientes();
            window.open("http://localhost:8080/SAPAP/vista/AP/InicioAP.jsp", "_self")
        }
    });
}

function abrirModal(button){
    console.log("al ejecutar 1");
    var nuevoArchivo = document.getElementById('evidenciaNuevoArchivo');
    document.getElementById('evidenciaNuevoArchivo').value='';
    nuevoFile=null;
    nuevoArchivo.addEventListener('change',function (ev) {
        nuevoFile = ev.target.files[0];
    });
    // $('#modalFile').modal('show');
}

function guardarNuevoArchivo(){
    if(nuevoFile){
        alert('entre');

        deleteFile = document.getElementById("nombreArchivo").value;
        deleteFile = "justificantes/"+deleteFile;
        var desertRef = storageFirebase.ref().child(deleteFile);
        desertRef.delete();

        var storageRef = storageFirebase.ref('justificantes/' + nuevoFile.name);
        storageRef.put(nuevoFile);
        var objetoArchivo = {
            ref: nuevoFile.name,
            evidencia:generarURL(nuevoFile.name),
            idJustificante:document.getElementById("identificadorJustificante").value
        };
        $.ajax({
            type: 'POST',
            url: raiz + 'actualizarArchivo',
            data:{
                parametro:JSON.stringify(objetoArchivo)
            },
            success: function (respuesta) {
                    Swal.fire({
                        position: 'top-end',
                        icon: 'success',
                        title: respuesta.response.mensaje,
                        showConfirmButton: false,
                        timer: 1500
                    });
                $("#modalActualizarArchivo").modal('hide');
                // $('#dataTable').DataTable().clear().draw();
                consultarJustificantesPendientes();
            },
            error: function (error) {
                console.log("al error");
                console.log(error);
            }
        });

    }else{
        $("#modalActualizarArchivo").modal('hide');
    }
}

function eliminarJustificantePendiente() {
    var idJustificante = document.getElementById('botonEli').value;
    $.ajax({
        type: 'POST',
        url: raiz + 'eliminarJustificante',
        data: {
            parametro: idJustificante,
        },
        success: function (respuesta) {
            alert(respuesta.respuestas.mensaje);
            consultarJustificantesPendientes();
        }
    });
};

function ejecutarEvento(){
    console.log("al ejecutar");
    var archivo = document.getElementById('imagen');
    archivo.addEventListener('change',function (ev) {
        file = ev.target.files[0];
    });
}

function generarURL(name) {
    var HOST ="https://firebasestorage.googleapis.com/v0/b/sapap-1c0e0.appspot.com/o/justificantes%2F";
    var TOKEN = "?alt=media&token=1e17563a-798d-4fbd-ad31-ddbd61853280";
    return url_id=HOST+name+TOKEN;
}

//RAPE

function consultarJustificantesPendientesRAPE() {
    $.ajax({
            type: 'POST',
            url: raiz + 'consultarJustificantesPendientesRAPE',
            success: function (respuesta) {
                var lista = respuesta.respuestas.listaJPendientes;

                $('#tableBody').html('');
                for (var i = 0; i < lista.length; i++) {
                    $('#tableBody').append('<tr><td>' + lista[i].fechaElaboracion + '</td>' +
                        '<td> AP Estático</td>' +
                        '<td>' + lista[i].estudiante.nombre + " " + lista[i].estudiante.primerApellido +  " " + lista[i].estudiante.segundoApellido + '</td>' +
                        '<td>' + lista[i].proyecto.nombre + '</td>' +
                        '<td>' + lista[i].motivoJustifica + '</td>' +
                        '<td>' +
                        '<a href="'+lista[i].evidencia+'" target="_blank"><button class="btn btn-info""><i class="fas fa-info-circle">Ver</i></button></a>' +
                        '<input id="idJustificante" type="hidden" value="'+ lista[i].idJustificante + '" ></input>' +
                        '</td>' +
                        '<td>' +
                        '<button id="btnAprobar" data-justificante="1" class="btn btn-success"data-toggle="modal" data-target="#modalAprobarJustificante"><i class="fas fa-check">Aprobar</i></button>' +
                        '<button id="btnRechazar" data-justificante="0" class="btn btn-danger" data-toggle="modal" data-target="#modalAprobarJustificante"><i class="fas fa-times-circle">Rechazar</i></button>' +
                        '</td>' +
                        '</tr>')
                }
            }
        }
    );
};

function validarContrasena(){
    var accion = document.getElementById('aprobar').value;
    var objeto = {
        aprobar: document.getElementById('aprobar').value,
        contrasena:document.getElementById('contrasena').value,
        idJus: document.getElementById("idJustificante").value,
        idUs: 3
    };
    $.ajax({
        type: 'POST',
        url: raiz + 'validarContrasena',
        data: {
            parametro:JSON.stringify(objeto)
        },
        success: function (respuesta) {
            if(respuesta.respuestas.mensaje === 'yes' && accion == 0){
                $("#modalMotivo").modal('show');
            } else if (respuesta.respuestas.mensaje === 'yes' && accion == 1){
                accionJustificante();
            } else {
                alert(respuesta.respuestas.mensaje);
            }

        }
    });
}

function accionJustificante() {
    var objeto = {
        aprobar: document.getElementById('aprobar').value,
        motivo: document.getElementById('motivo').value,
        idJus: document.getElementById("idJustificante").value,
    };
    $.ajax({
        type: 'POST',
        url: raiz + 'accionJustificante',
        data: {
            parametro:JSON.stringify(objeto)
        },
        success: function (respuesta) {
            $('#motivo').html('');
            alert(respuesta.respuestas.mensaje);
            consultarJustificantesPendientesRAPE();
        }
    });
}

//COD

function consultarJustificantesPendientesCOD() {
    $.ajax({
            type: 'POST',
            url: raiz + 'consultarJustificantesPendientesCOD',
            success: function (respuesta) {
                var lista = respuesta.respuestas.listaJPendientes;

                $('#tableBody').html('');
                for (var i = 0; i < lista.length; i++) {
                    $('#tableBody').append('<tr><td>' + lista[i].fechaElaboracion + '</td>' +
                        '<td> AP Estático</td>' +
                        '<td>' + lista[i].estudiante.nombre + " " + lista[i].estudiante.primerApellido +  " " + lista[i].estudiante.segundoApellido + '</td>' +
                        '<td>' + lista[i].proyecto.nombre + '</td>' +
                        '<td>' + lista[i].motivoJustifica + '</td>' +
                        '<td>' +
                        '<a href="'+lista[i].evidencia+'" target="_blank"><button class="btn btn-info""><i class="fas fa-info-circle">Ver</i></button></a>' +
                        '<input id="idJustificante" type="hidden" value="'+ lista[i].idJustificante + '" ></input>' +
                        '</td>' +
                        '<td>' +
                        '<button id="btnAprobar" data-justificante="1" class="btn btn-success"data-toggle="modal" data-target="#modalAprobarJustificante"><i class="fas fa-check">Aprobar</i></button>' +
                        '<button id="btnRechazar" data-justificante="0" class="btn btn-danger" data-toggle="modal" data-target="#modalAprobarJustificante"><i class="fas fa-times-circle">Rechazar</i></button>' +
                        '</td>' +
                        '</tr>')
                }
            }
        }
    );
};

function validarContrasenaCOD(){
    var accion = document.getElementById('aprobar').value;
    var objeto = {
        aprobar: document.getElementById('aprobar').value,
        contrasena:document.getElementById('contrasena').value,
        idJus: document.getElementById("idJustificante").value,
        idUs: 3
    };
    $.ajax({
        type: 'POST',
        url: raiz + 'validarContrasena',
        data: {
            parametro:JSON.stringify(objeto)
        },
        success: function (respuesta) {
            if(respuesta.respuestas.mensaje === 'yes' && accion == 0){
                $("#modalMotivo").modal('show');
            } else if (respuesta.respuestas.mensaje === 'yes' && accion == 1){
                accionJustificanteCOD();
            } else {
                alert(respuesta.respuestas.mensaje);
            }

        }
    });
}

function accionJustificanteCOD() {
    var objeto = {
        aprobar: document.getElementById('aprobar').value,
        motivo: document.getElementById('motivo').value,
        idJus: document.getElementById("idJustificante").value,
    };
    $.ajax({
        type: 'POST',
        url: raiz + 'accionJustificanteCOD',
        data: {
            parametro:JSON.stringify(objeto)
        },
        success: function (respuesta) {
            $('#motivo').html('');
            alert(respuesta.respuestas.mensaje);
            consultarJustificantesPendientesCOD();
        }
    });
}

//RH

function consultarJustificantesPendientesRH() {
    $.ajax({
            type: 'POST',
            url: raiz + 'consultarJustificantesPendientesRH',
            success: function (respuesta) {
                var lista = respuesta.respuestas.listaJPendientes;

                $('#tableBody').html('');
                for (var i = 0; i < lista.length; i++) {
                    $('#tableBody').append('<tr><td>' + lista[i].fechaElaboracion + '</td>' +
                        '<td> AP Estático</td>' +
                        '<td>' + lista[i].estudiante.nombre + " " + lista[i].estudiante.primerApellido +  " " + lista[i].estudiante.segundoApellido + '</td>' +
                        '<td>' + lista[i].proyecto.nombre + '</td>' +
                        '<td>' + lista[i].motivoJustifica + '</td>' +
                        '<td>' +
                        '<a href="'+lista[i].evidencia+'" target="_blank"><button class="btn btn-info""><i class="fas fa-info-circle">Ver</i></button></a>' +
                        '<input id="idJustificante" type="hidden" value="'+ lista[i].idJustificante + '" ></input>' +
                        '</td>' +
                        '<td>' +
                        '<button id="btnAprobar" data-justificante="1" class="btn btn-success"data-toggle="modal" data-target="#modalAprobarJustificante"><i class="fas fa-check">Aprobar</i></button>' +
                        '<button id="btnRechazar" data-justificante="0" class="btn btn-danger" data-toggle="modal" data-target="#modalAprobarJustificante"><i class="fas fa-times-circle">Rechazar</i></button>' +
                        '</td>' +
                        '</tr>')
                }
            }
        }
    );
};

function validarContrasenaRH(){
    var accion = document.getElementById('aprobar').value;
    var objeto = {
        aprobar: document.getElementById('aprobar').value,
        contrasena:document.getElementById('contrasena').value,
        idJus: document.getElementById("idJustificante").value,
        idUs: 3
    };
    $.ajax({
        type: 'POST',
        url: raiz + 'validarContrasena',
        data: {
            parametro:JSON.stringify(objeto)
        },
        success: function (respuesta) {
            if(respuesta.respuestas.mensaje === 'yes' && accion == 0){
                $("#modalMotivo").modal('show');
            } else if (respuesta.respuestas.mensaje === 'yes' && accion == 1){
                accionJustificanteRH();
            } else {
                alert(respuesta.respuestas.mensaje);
            }

        }
    });
}

function accionJustificanteRH() {
    var objeto = {
        aprobar: document.getElementById('aprobar').value,
        motivo: document.getElementById('motivo').value,
        idJus: document.getElementById("idJustificante").value,
    };
    $.ajax({
        type: 'POST',
        url: raiz + 'accionJustificanteRH',
        data: {
            parametro:JSON.stringify(objeto)
        },
        success: function (respuesta) {
            $('#motivo').html('');
            alert(respuesta.respuestas.mensaje);
            consultarJustificantesPendientesCOD();
        }
    });
}
