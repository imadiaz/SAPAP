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
var nuevoFile = null;
// var $ = jQuery.noConflict();
var storageRef = storageFirebase.ref();


function consultarJustificantesPendientes() {
    var table = $('#dataTable').DataTable();
    $.ajax({
            type: 'POST',
            url: raiz + 'consultarJustificantesPendientes',
            data: {
                parametro: document.getElementById('idUsSesion').value
            },
            success: function (respuesta) {
                var lista = respuesta.respuestas.listaJPendientes;
                var fila;
                for (var i = 0; i < lista.length; i++) {
                    if (lista[i].evidencia === '#') {
                        fila =
                            "<a style='cursor: not-allowed' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    } else {
                        fila =
                            "<a href=" + lista[i].evidencia + " target='_blank' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    }
                    table.row.add([
                        lista[i].fechaElaboracion,
                        "AP",
                        lista[i].justifica.nombre,
                        lista[i].proyecto.nombre,
                        lista[i].motivoJustifica,
                        fila +
                        "<button id='btnModi' class='btn btn-sm btn-icon-split btn-warning' data-nombreArchivo=" + lista[i].referencia + " data-idjustificante=" + lista[i].idJustificante + " " +
                        "data-toggle='modal' data-target='#modalActualizarArchivo'><span class='icon text-white-50'><i class='fas fa-exclamation-triangle'></i></span><span class='text'>Modificar</span></button>",
                        "<button id='btnEli' data-justificante=" + lista[i].idJustificante + " data-toggle='modal' data-target='#modalEliminarJustificante' class='btn btn-sm btn-icon-split btn-danger'><span class='icon text-white-50'><i class='fas fa-trash'></i></span><span class='text'>Eliminar</span></button><br/>" +
                        "<a href='ModificarJustificante.jsp?prodId=" + lista[i].idJustificante + "'> <button class='btn btn-sm btn-icon-split btn-warning'>" +
                        "<span class='icon text-white-50'><i class='fas fa-exclamation-triangle'></i></span><span class='text'>Modificar</span></button></a>"
                    ]).draw(false);
                }
            }
        }
    );
};

function consultarHistorialJustificantes() {
    var table = $('#dataTable').DataTable();
    $.ajax({
            type: 'POST',
            url: raiz + 'consultarHistorialJustificantes',
            data: {
                parametro: document.getElementById('idUsSesion').value
            },
            success: function (respuesta) {
                var lista = respuesta.respuestas.listaJPendientes;
                var motivo;
                var fila;
                for (var i = 0; i < lista.length; i++) {
                    if (lista[i].motivoRechazo == null) {
                        motivo = 'No aplica';
                    } else {
                        motivo = lista[i].motivoRechazo;
                    }
                    if (lista[i].evidencia === '#') {
                        fila =
                            "<a style='cursor: not-allowed' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    } else {
                        fila =
                            "<a href=" + lista[i].evidencia + " target='_blank' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    }
                    table.row.add([
                        lista[i].fechaElaboracion,
                        "AP",
                        lista[i].justifica.nombre,
                        lista[i].proyecto.nombre,
                        motivo,
                        fila
                    ]).draw(false);
                    // $('#tableBody').append('<tr><td>' + lista[i].fechaElaboracion + '</td>' +
                    //     '<td> AP Estático</td>' +
                    //     '<td>' + lista[i].justifica.nombre + '</td>' +
                    //     '<td>' + lista[i].proyecto.nombre + '</td>' +
                    //     '<td>' +motivo+'</td>'+
                    //     '<td>' +
                    //     '<a href="'+lista[i].evidencia+'" target="_blank"><button class="btn btn-info""><i class="fas fa-info-circle">Ver</i></button></a>' +
                    //     '</td>' +
                    //     '</tr>')
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
                // lista = respuesta.respuestas.listaRape;
                lista2 = respuesta.respuestas.listaProyecto;
                // document.getElementById('rape').innerHTML = '';
                document.getElementById('proyecto').innerHTML = '';

                // for (var i = 0; i < lista.length; i++) {
                //     $('#rape').append('<option value="' + lista[i].idPersona + "-" + lista[i].nombre + "-" + lista[i].primerApellido + "-" + lista[i].segundoApellido + '">'
                //         + lista[i].nombre + "  " + lista[i].primerApellido + "  " + lista[i].segundoApellido
                //         + '</option>');
                // }
                for (var i = 0; i < lista2.length; i++) {
                    if (i===0){
                        buscarRAPE(lista2[i].nombre)
                    }
                    $('#proyecto').append('<option value="' + lista2[i].nombre + '">'
                        + lista2[i].nombre
                        + '</option>');
                }
            }
        }
    );
};

function agregarJustificante() {
    var justificante;
    if (typeof file === "undefined") {
        justificante = {
            rape: document.getElementById('rape').value,
            proyecto: document.getElementById('proyecto').value,
            motivo: document.getElementById('motivo').value,
            id: document.getElementById('idUsSesion').value,
            evidencia: "#",
            referencia: "#"
        };
    } else {
        justificante = {
            rape: document.getElementById('rape').value,
            proyecto: document.getElementById('proyecto').value,
            motivo: document.getElementById('motivo').value,
            id: document.getElementById('idUsSesion').value,
            evidencia: generarURL(file.name),
            referencia: file.name
        };
        //justificantes/ es el nombre de la carpeta en firebase
        var storageRef = storageFirebase.ref('justificantes/' + file.name);
        //el metodo put es ell que sube la imagen
        storageRef.put(file);
    }
    $.ajax({
        type: 'POST',
        url: raiz + 'agregarJustificante',
        data: {
            parametro: JSON.stringify(justificante)
        },
        success: function (respuesta) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Justificante registrado con éxito',
                showConfirmButton: false,
                timer: 1500
            });
            // alert(respuesta.respuestas.mensaje);
            var form = document.getElementById("formJustificante");
            form.reset();
            // consultarJustificantesPendientes();
            // window.open("http://localhost:8080/SAPAP/vista/AP/InicioAP.jsp", "_self")
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
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Justificante modificado con éxito',
                showConfirmButton: false,
                timer: 1500
            });
            // alert(respuesta.respuestas.mensaje);
            // consultarJustificantesPendientes();
            // window.open("http://localhost:8080/SAPAP/vista/AP/InicioAP.jsp", "_self")
        }
    });
}

function abrirModal(button) {
    console.log("al ejecutar 1");
    var nuevoArchivo = document.getElementById('evidenciaNuevoArchivo');
    document.getElementById('evidenciaNuevoArchivo').value = '';
    nuevoFile = null;
    nuevoArchivo.addEventListener('change', function (ev) {
        nuevoFile = ev.target.files[0];
    });
    // $('#modalFile').modal('show');
}

function guardarNuevoArchivo() {
    if (nuevoFile) {
        deleteFile = document.getElementById("nombreArchivo").value;
        deleteFile = "justificantes/" + deleteFile;
        var desertRef = storageFirebase.ref().child(deleteFile);
        desertRef.delete();

        var storageRef = storageFirebase.ref('justificantes/' + nuevoFile.name);
        storageRef.put(nuevoFile);
        var objetoArchivo = {
            ref: nuevoFile.name,
            evidencia: generarURL(nuevoFile.name),
            idJustificante: document.getElementById("identificadorJustificante").value
        };
        $.ajax({
            type: 'POST',
            url: raiz + 'actualizarArchivo',
            data: {
                parametro: JSON.stringify(objetoArchivo)
            },
            success: function (respuesta) {
                // Swal.fire({
                //     position: 'top-end',
                //     icon: 'success',
                //     title: respuesta.response.mensaje,
                //     showConfirmButton: false,
                //     timer: 1500
                // });
                $("#modalActualizarArchivo").modal('hide');
                location.reload();
                // $('#dataTable').DataTable().clear().draw();
                // consultarJustificantesPendientes();
            },
            error: function (error) {
                console.log("al error");
                console.log(error);
            }
        });

    } else {
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
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Justificante eliminado con éxito',
                showConfirmButton: false,
                timer: 1500
            });
            // alert(respuesta.respuestas.mensaje);
            $('#dataTable').DataTable().clear().draw();
            consultarJustificantesPendientes();
        }
    });
};

function ejecutarEvento() {
    console.log("al ejecutar");
    var archivo = document.getElementById('imagen');
    archivo.addEventListener('change', function (ev) {
        file = ev.target.files[0];
    });
}

function generarURL(name) {
    var HOST = "https://firebasestorage.googleapis.com/v0/b/sapap-1c0e0.appspot.com/o/justificantes%2F";
    var TOKEN = "?alt=media&token=1e17563a-798d-4fbd-ad31-ddbd61853280";
    return url_id = HOST + name + TOKEN;
}

function buscarRAPE(nombreProyecto) {
    $.ajax({
        type: 'POST',
        url: raiz + 'buscarRAPE',
        data: {
            parametro: nombreProyecto
        },
        success: function (respuesta) {
            lista = respuesta.respuestas.listaRape;
            document.getElementById('rape').innerHTML = '';

            for (var i = 0; i < lista.length; i++) {
                $('#rape').append('<option value="' + lista[i].idPersona + "-" + lista[i].nombre + "-" + lista[i].primerApellido + "-" + lista[i].segundoApellido + '">'
                    + lista[i].nombre + "  " + lista[i].primerApellido + "  " + lista[i].segundoApellido
                    + '</option>');
            }
        }
    })
}


//RAPE

function consultarJustificantesPendientesRAPE() {
    var table = $('#dataTable').DataTable();
    $.ajax({
            type: 'POST',
            url: raiz + 'consultarJustificantesPendientesRAPE',
            data: {
                parametro: document.getElementById('idUsSesion').value
            },
            success: function (respuesta) {
                var lista = respuesta.respuestas.listaJPendientes;
                var fila;
                for (var i = 0; i < lista.length; i++) {
                    if (lista[i].evidencia === '#') {
                        fila =
                            "<a style='cursor: not-allowed' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    } else {
                        fila =
                            "<a href=" + lista[i].evidencia + " target='_blank' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    }
                    table.row.add([
                        lista[i].fechaElaboracion,
                        "AP",
                        lista[i].estudiante.nombre + " " + lista[i].estudiante.primerApellido + " " + lista[i].estudiante.segundoApellido,
                        lista[i].proyecto.nombre,
                        lista[i].motivoJustifica,
                        fila,
                        "<button id='btnAprobar' data-idJustificante=" + lista[i].idJustificante + " data-justificante='1'  class='btn btn-sm btn-icon-split btn-success' data-toggle='modal' data-target='#modalAprobarJustificante'><span class='icon text-white-50'><i class='fas fa-check'></i></span><span class='text'>Aprobar</span></button>" +
                        "<button id='btnRechazar' data-idJustificante=" + lista[i].idJustificante + " data-justificante='0' class='btn btn-sm btn-icon-split btn-danger'  data-toggle='modal' data-target='#modalAprobarJustificante'><span class='icon text-white-50'><i class='fas fa-check'></i></span><span class='text'>Rechazar</span></button>"
                    ]).draw(false);

                }
            }
        }
    );
};

function validarContrasena() {
    var accion = document.getElementById('aprobar').value;
    var objeto = {
        aprobar: document.getElementById('aprobar').value,
        contrasena: document.getElementById('contrasena').value,
        idJus: document.getElementById("idJustificante").value,
        idUs: document.getElementById("idUsSesion").value
    };
    $.ajax({
        type: 'POST',
        url: raiz + 'validarContrasena',
        data: {
            parametro: JSON.stringify(objeto)
        },
        success: function (respuesta) {
            if (respuesta.respuestas.mensaje === 'yes' && accion == 0) {
                $("#modalMotivo").modal('show');
            } else if (respuesta.respuestas.mensaje === 'yes' && accion == 1) {
                accionJustificante();
            } else {
                Swal.fire({
                    position: 'top-end',
                    icon: 'error',
                    title: 'La contraseña ingresada es incorrecta',
                    showConfirmButton: false,
                    timer: 1500
                });
                // alert(respuesta.respuestas.mensaje);
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
            parametro: JSON.stringify(objeto)
        },
        success: function (respuesta) {
            $('#motivo').html('');
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Justificante aprobado / rechazado con éxito',
                showConfirmButton: false,
                timer: 1500
            });
            // alert(respuesta.respuestas.mensaje);
            document.getElementById('motivo').value = "";
            $('#dataTable').DataTable().clear().draw();
            consultarJustificantesPendientesRAPE();
        }
    });
}

function consultarHistorialJustificantesRAPE() {
    var table = $('#dataTable').DataTable();
    $.ajax({
            type: 'POST',
            url: raiz + 'consultarHistorialJustificantesRAPE',
            data: {
                parametro: document.getElementById('idUsSesion').value
            },
            success: function (respuesta) {
                var lista = respuesta.respuestas.listaJPendientes;
                var motivo;
                var fila;
                for (var i = 0; i < lista.length; i++) {
                    if (lista[i].motivoRechazo == null) {
                        motivo = 'No aplica';
                    } else {
                        motivo = lista[i].motivoRechazo;
                    }
                    if (lista[i].evidencia === '#') {
                        fila =
                            "<a style='cursor: not-allowed' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    } else {
                        fila =
                            "<a href=" + lista[i].evidencia + " target='_blank' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    }
                    table.row.add([
                        lista[i].fechaElaboracion,
                        "AP",
                        lista[i].estudiante.nombre + " " + lista[i].estudiante.primerApellido + " " + lista[i].estudiante.segundoApellido,
                        lista[i].proyecto.nombre,
                        motivo,
                        fila
                    ]).draw(false);
                }
            }
        }
    );
};

//COD

function consultarJustificantesPendientesCOD() {
    var table = $('#dataTable').DataTable();
    $.ajax({
            type: 'POST',
            url: raiz + 'consultarJustificantesPendientesCOD',
            success: function (respuesta) {
                var lista = respuesta.respuestas.listaJPendientes;
                var fila;
                for (var i = 0; i < lista.length; i++) {
                    if (lista[i].evidencia === '#') {
                        fila =
                            "<a style='cursor: not-allowed' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    } else {
                        fila =
                            "<a href=" + lista[i].evidencia + " target='_blank' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    }
                    table.row.add([
                        lista[i].fechaElaboracion,
                        "AP",
                        lista[i].estudiante.nombre + " " + lista[i].estudiante.primerApellido + " " + lista[i].estudiante.segundoApellido,
                        lista[i].proyecto.nombre,
                        lista[i].motivoJustifica,
                        fila,
                        "<button id='btnAprobar' data-idJustificante=" + lista[i].idJustificante + " data-justificante='1'  class='btn btn-sm btn-icon-split btn-success' data-toggle='modal' data-target='#modalAprobarJustificante'><span class='icon text-white-50'><i class='fas fa-check'></i></span><span class='text'>Aprobar</span></button>" +
                        "<button id='btnRechazar' data-idJustificante=" + lista[i].idJustificante + " data-justificante='0' class='btn btn-sm btn-icon-split btn-danger'  data-toggle='modal' data-target='#modalAprobarJustificante'><span class='icon text-white-50'><i class='fas fa-check'></i></span><span class='text'>Rechazar</span></button>"
                    ]).draw(false);

                }
            }
        }
    );
};

function validarContrasenaCOD() {
    var accion = document.getElementById('aprobar').value;
    var objeto = {
        aprobar: document.getElementById('aprobar').value,
        contrasena: document.getElementById('contrasena').value,
        idJus: document.getElementById("idJustificante").value,
        idUs: document.getElementById("idUsSesion").value
    };
    $.ajax({
        type: 'POST',
        url: raiz + 'validarContrasena',
        data: {
            parametro: JSON.stringify(objeto)
        },
        success: function (respuesta) {
            if (respuesta.respuestas.mensaje === 'yes' && accion == 0) {
                $("#modalMotivo").modal('show');
            } else if (respuesta.respuestas.mensaje === 'yes' && accion == 1) {
                accionJustificanteCOD();
            } else {
                Swal.fire({
                    position: 'top-end',
                    title: 'La contraseña ingresada es incorrecta',
                    showConfirmButton: false,
                    timer: 1500
                });
                // alert(respuesta.respuestas.mensaje);
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
            parametro: JSON.stringify(objeto)
        },
        success: function (respuesta) {
            $('#motivo').html('');
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Justificante aprobado / rechazado con éxito',
                showConfirmButton: false,
                timer: 1500
            });
            // alert(respuesta.respuestas.mensaje);
            $('#dataTable').DataTable().clear().draw();
            consultarJustificantesPendientesCOD();
        }
    });
}

function consultarHistorialJustificantesCOD() {
    var table = $('#dataTable').DataTable();
    $.ajax({
            type: 'POST',
            url: raiz + 'consultarHistorialJustificantesCOD',
            success: function (respuesta) {
                var lista = respuesta.respuestas.listaJPendientes;
                var motivo;
                var fila;
                $('#tableBody').html('');
                for (var i = 0; i < lista.length; i++) {
                    if (lista[i].motivoRechazo == null) {
                        motivo = 'No aplica';
                    } else {
                        motivo = lista[i].motivoRechazo;
                    }
                    if (lista[i].evidencia === '#') {
                        fila =
                            "<a style='cursor: not-allowed' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    } else {
                        fila =
                            "<a href=" + lista[i].evidencia + " target='_blank' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    }
                    table.row.add([
                        lista[i].fechaElaboracion,
                        "AP",
                        lista[i].estudiante.nombre + " " + lista[i].estudiante.primerApellido + " " + lista[i].estudiante.segundoApellido,
                        lista[i].proyecto.nombre,
                        motivo,
                        fila
                    ]).draw(false);
                }
            }
        }
    );
};


//RH

function consultarJustificantesPendientesRH() {
    var table = $('#dataTable').DataTable();

    $.ajax({
            type: 'POST',
            url: raiz + 'consultarJustificantesPendientesRH',
            success: function (respuesta) {
                var lista = respuesta.respuestas.listaJPendientes;
                var fila;
                for (var i = 0; i < lista.length; i++) {
                    if (lista[i].evidencia === '#') {
                        fila =
                            "<a style='cursor: not-allowed' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    } else {
                        fila =
                            "<a href=" + lista[i].evidencia + " target='_blank' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    }
                    table.row.add([
                        lista[i].fechaElaboracion,
                        "AP",
                        lista[i].estudiante.nombre + " " + lista[i].estudiante.primerApellido + " " + lista[i].estudiante.segundoApellido,
                        lista[i].proyecto.nombre,
                        lista[i].motivoJustifica,
                        fila,
                        "<button id='btnAprobar' data-idJustificante=" + lista[i].idJustificante + " data-justificante='1'  class='btn btn-sm btn-icon-split btn-success' data-toggle='modal' data-target='#modalAprobarJustificante'><span class='icon text-white-50'><i class='fas fa-check'></i></span><span class='text'>Aprobar</span></button>" +
                        "<button id='btnRechazar' data-idJustificante=" + lista[i].idJustificante + " data-justificante='0' class='btn btn-sm btn-icon-split btn-danger'  data-toggle='modal' data-target='#modalAprobarJustificante'><span class='icon text-white-50'><i class='fas fa-check'></i></span><span class='text'>Rechazar</span></button>"
                    ]).draw(false);
                }
            }
        }
    );
};

function validarContrasenaRH() {
    var accion = document.getElementById('aprobar').value;
    var objeto = {
        aprobar: document.getElementById('aprobar').value,
        contrasena: document.getElementById('contrasena').value,
        idJus: document.getElementById("idJustificante").value,
        idUs: document.getElementById("idUsSesion").value
    };
    $.ajax({
        type: 'POST',
        url: raiz + 'validarContrasena',
        data: {
            parametro: JSON.stringify(objeto)
        },
        success: function (respuesta) {
            if (respuesta.respuestas.mensaje === 'yes' && accion == 0) {
                $("#modalMotivo").modal('show');
            } else if (respuesta.respuestas.mensaje === 'yes' && accion == 1) {
                accionJustificanteRH();
            } else {
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: '“La contraseña ingresada es incorrecta',
                    showConfirmButton: false,
                    timer: 1500
                });
                // alert(respuesta.respuestas.mensaje);
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
            parametro: JSON.stringify(objeto)
        },
        success: function (respuesta) {
            $('#motivo').html('');
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Justificante aprobado / rechazado con éxito',
                showConfirmButton: false,
                timer: 1500
            });
            // alert(respuesta.respuestas.mensaje);
            $('#dataTable').DataTable().clear().draw();
            consultarJustificantesPendientesRH();
        }
    });
}

function consultarHistorialJustificantesRH() {
    var table = $('#dataTable').DataTable();
    $.ajax({
            type: 'POST',
            url: raiz + 'consultarHistorialJustificantesRH',
            success: function (respuesta) {
                var lista = respuesta.respuestas.listaJPendientes;
                var motivo;
                var fila;
                for (var i = 0; i < lista.length; i++) {
                    if (lista[i].motivoRechazo == null) {
                        motivo = 'No aplica';
                    } else {
                        motivo = lista[i].motivoRechazo;
                    }
                    if (lista[i].evidencia === '#') {
                        fila =
                            "<a style='cursor: not-allowed' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    } else {
                        fila =
                            "<a href=" + lista[i].evidencia + " target='_blank' class='btn btn-sm btn-icon-split btn-info'><span class='icon text-white-50'><i class='fas fa-info-circle'></i></span><span class='text'>Ver</span></a>"
                    }
                    table.row.add([
                        lista[i].fechaElaboracion,
                        "AP",
                        lista[i].estudiante.nombre + " " + lista[i].estudiante.primerApellido + " " + lista[i].estudiante.segundoApellido,
                        lista[i].proyecto.nombre,
                        motivo,
                        fila
                    ]).draw(false);
                }
            }
        }
    );
};