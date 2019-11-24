var raiz = window.location.origin + '/SAPAP/';
var lista = [];
var $ = jQuery.noConflict();



function eliminarPersona(button) {

    $.ajax({
            type: 'POST',
            url: raiz + 'eliminarPersona',
        data:{
            params:button.value
        },
        success: function (respuesta) {
            var table = $('#dataTable').DataTable();
            if(respuesta.respuestas.response){
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'Eliminado correctamente',
                    showConfirmButton: false,
                    timer: 1500
                });
            }else{
                Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: 'Error, intente nuevamente',
                    showConfirmButton: false,
                    timer: 1500
                });
            }

            $('#dataTable').DataTable().clear().draw();
           consultaPersonitas();
        },
        error: function (error) {
            console.log("al error");
            console.log(error);
        }
    });
}
function esEstudiante() {

    var button=$('#gridCheck');
    if($("#gridCheck").is(':checked')) {

        $('#estudiante').append('' +
            '<div class="col-sm-4 mb-3 mb-sm-0">\n' +
            '                            <label for="cuatrimestreActual">Cuatrimestre Actual</label>\n' +
            '                            <select id="cuatrimestreActual" class="form-control form-control-file">\n' +
            '                                <option value="1°">1°</option>\n' +
            '                                <option value="2°">2°</option>\n' +
            '                                <option value="3°">3°</option>\n' +
            '                                <option value="4°">4°</option>\n' +
            '                                <option value="5°">5°</option>\n' +
            '                                <option value="6°">6°</option>\n' +
            '                                <option value="7°">7°</option>\n' +
            '                                <option value="8°">8°</option>\n' +
            '                                <option value="9°">9°</option>\n' +
            '                                <option value="10°">10°</option>\n' +
            '                                <option value="11°">11°</option>\n' +
            '                            </select>\n' +
            '                        </div>\n' +
            '                        <div class="col-sm-4">\n' +
            '                            <label for="cuatrimestreDeIngreso" >Cuatrimestre de Ingreso</label>\n' +
            '                            <select id="cuatrimestreDeIngreso" class="form-control form-control-file">\n' +
            '                                <option value="1°">1°</option>\n' +
            '                                <option value="2°">2°</option>\n' +
            '                                <option value="3°">3°</option>\n' +
            '                                <option value="4°">4°</option>\n' +
            '                                <option value="5°">5°</option>\n' +
            '                                <option value="6°">6°</option>\n' +
            '                                <option value="7°">7°</option>\n' +
            '                                <option value="8°">8°</option>\n' +
            '                                <option value="9°">9°</option>\n' +
            '                                <option value="10°">10°</option>\n' +
            '                                <option value="11°">11°</option>\n' +
            '                            </select>\n' +
            '                        </div>\n' +
            '                        <div class="col-sm-4">\n' +
            '                            <label for="grupoActual">Grupo Actual</label>\n' +
            '                            <div class="input-group mb-2">\n' +
            '\n' +
            '                                <input type="text"   class="form-control form-control-user" id="grupoActual" placeholder="A">\n' +
            '\n' +
            '                            </div>\n' +
            '                        </div>');
        $('#becarioDiv').append('  <div class="col-sm-4 mb-3 mb-sm-0">\n' +
            '                            <input class="form-check-input" type="checkbox" id="gridBecario" onchange="esBecario()">\n' +
            '                            <label class="form-check-label" for="gridCheck">\n' +
            '                                ¿Es Becario?\n' +
            '                            </label>\n' +
            '                        </div>' );
    } else {
        $('#estudiante').html('');
        $('#becarioDiv').html('');
    }
}

function esBecario() {

    if($("#gridBecario").is(':checked')) {

        $('#becarioDivDiv').append('<label for="beca">Beca</label>\n' +
            '                            <div class="input-group mb-2">\n' +
            '\n' +
            '                                <input type="Number"   class="form-control form-control-user" id="beca" placeholder="$2000">\n' +
            '\n' +
            '                            </div> ');
    } else {

        $('#becarioDivDiv').html('');
    }
}


function consultaRegistro() {

    $.ajax({
            type: 'POST',
            url: raiz + 'consultarRegistro',
            success: function (respuesta) {
                var horarios=respuesta.respuestas.horario;
                var roles=respuesta.respuestas.roles;

                for (var i=0;i<roles.length;i++){

                    $('#tbody').append('<tr><td> '+roles[i].tipo+'</td><td><input type="checkbox" name="rolesCheck" value="'+roles[i].idRol+'"></td></tr>');

                }

                for (var i=0;i<horarios.length;i++){

                    $('#select').append('<option value="'+horarios[i].idHorario+'">'+horarios[i].horario+'</option>');
                }

            },
            error: function (error) {
                console.log("al error");
                console.log(error);
            }
        }
    );
}
function registroPersona() {
    var roles=[];
    var grupoActual="";
    var cuatrimestreDeIngreso="";
    var cuatrimestreActual="";
    var beca="";
    var becario=false;
    $.each($("input[name='rolesCheck']:checked"), function(){
        roles.push($(this).val());
    });
    var estudiante;
    var estudiante=document.getElementById("gridCheck").checked;

    if (estudiante){
        var grupoActual=document.getElementById("grupoActual").value;
        var becario=document.getElementById("gridBecario").checked;
        var cuatrimestreDeIngreso=document.getElementById("cuatrimestreDeIngreso").value;
        var cuatrimestreActual=document.getElementById("cuatrimestreActual").value;
        if (becario){

            beca=document.getElementById("beca").value;
        }
    }



     var nombre=document.getElementById("nombre").value;
     var primerApellido=document.getElementById("primerApellido").value;
     var segundoApellido=" ";
     var segundoApellido=document.getElementById("segundoApellido").value;
     var matricula=document.getElementById("matricula").value;
     var correoP=document.getElementById("correoPersonal").value;
     var correoInstitucional=document.getElementById("correoInstitucional").value;
     var fechaNac=document.getElementById("fechaDeNacimiento").value;
     var numeroTel=document.getElementById("numeroTelefonico").value;
     var numeroPersonal=document.getElementById("numeroPersonal").value;
     var carreraDeEgreso=document.getElementById("carreraDeEgreso").value;
     var universidadDeEgreso=document.getElementById("universidadDeEgreso").value;
     var fechaDeIngreso=document.getElementById("fechaDeIngreso").value;
     var horario=document.getElementById("select").value;
     var contrasena=document.getElementById("contrasena").value;
     var direccion=document.getElementById("direccion").value;

  var  params={nombre:nombre,estudiante:estudiante,becario:becario,beca:beca,grupoActual:grupoActual,cuatrimestreDeIngreso:cuatrimestreDeIngreso,cuatrimestreActual:cuatrimestreActual
          ,beca:beca,primerApellido:primerApellido,segundoApellido:segundoApellido,
      matricula:matricula,correoP:correoP,correoInstitucional:correoInstitucional,fechaNac:fechaNac,
      numeroTel:numeroTel,numeroPersonal:numeroPersonal,carreraDeEgreso:carreraDeEgreso,universidadDeEgreso:universidadDeEgreso,fechaDeIngreso:fechaDeIngreso,
      horario:horario,contrasena:contrasena,roles:roles,direccion:direccion}

    $.ajax({
        type: 'POST',
        url: raiz + 'registrarPersona',
        data:{
            params:JSON.stringify(params)
           },
        success: function (respuesta) {

            alert('redireccionando')
            window.location.href = "http://localhost:8080/SAPAP/vista/RH/EmpleadosRH.jsp";

        },
        error: function (error) {
            console.log("al error");
            console.log(error);
        }
    });
}
function modificarPersona() {
    var roles=[];
    var grupoActual="";
    var cuatrimestreDeIngreso="";
    var cuatrimestreActual="";
    var beca="";
    var becario=false;
    $.each($("input[name='rolesCheck']:checked"), function(){
        roles.push($(this).val());
    });
    var estudiante;
    var estudiante=document.getElementById("gridCheck").checked;

    if (estudiante){
        var grupoActual=document.getElementById("grupoActual").value;
        var becario=document.getElementById("gridBecario").checked;
        var cuatrimestreDeIngreso=document.getElementById("cuatrimestreDeIngreso").value;
        var cuatrimestreActual=document.getElementById("cuatrimestreActual").value;
        if (becario){

            beca=document.getElementById("beca").value;
        }
    }



    var nombre=document.getElementById("nombre").value;
    var primerApellido=document.getElementById("primerApellido").value;
    var segundoApellido=" ";
    var segundoApellido=document.getElementById("segundoApellido").value;
    var matricula=document.getElementById("matricula").value;
    var correoP=document.getElementById("correoPersonal").value;
    var correoInstitucional=document.getElementById("correoInstitucional").value;
    var fechaNac=document.getElementById("fechaDeNacimiento").value;
    var numeroTel=document.getElementById("numeroTelefonico").value;
    var numeroPersonal=document.getElementById("numeroPersonal").value;
    var carreraDeEgreso=document.getElementById("carreraDeEgreso").value;
    var universidadDeEgreso=document.getElementById("universidadDeEgreso").value;
    var fechaDeIngreso=document.getElementById("fechaDeIngreso").value;
    var horario=document.getElementById("select").value;
    var contrasena=document.getElementById("contrasena").value;
    var direccion=document.getElementById("direccion").value;
    var idPersona=document.getElementById("idPersona").value;

    var  params={nombre:nombre,estudiante:estudiante,becario:becario,beca:beca,grupoActual:grupoActual,cuatrimestreDeIngreso:cuatrimestreDeIngreso,cuatrimestreActual:cuatrimestreActual
        ,beca:beca,primerApellido:primerApellido,segundoApellido:segundoApellido,
        matricula:matricula,correoP:correoP,correoInstitucional:correoInstitucional,fechaNac:fechaNac,
        numeroTel:numeroTel,numeroPersonal:numeroPersonal,carreraDeEgreso:carreraDeEgreso,universidadDeEgreso:universidadDeEgreso,fechaDeIngreso:fechaDeIngreso,
        horario:horario,contrasena:contrasena,roles:roles,idPersona:idPersona,direccion:direccion}

    $.ajax({
        type: 'POST',
        url: raiz + 'modificarPersona',
        data:{
            params:JSON.stringify(params)
        },
        success: function (respuesta) {


        },
        error: function (error) {
            console.log("al error");
            console.log(error);
        }
    });
}

function consultaPersonitas() {
    $.ajax({
            type: 'POST',
            url: raiz + 'consultaEmpleados',

            success: function (respuesta) {
                var personas=respuesta.respuestas.personas;
                var table = $('#dataTable').DataTable();
                for (var i=0;i<personas.length;i++){
                    table.row.add([personas[i].nombre+' '+personas[i].primerApellido+ ' '+personas[i].segundoApellido
                        ,personas[i].matricula
                        ,personas[i].numeroTelefonico
                        ,personas[i].numeroCasa
                        ,personas[i].fechaDeNacimiento
                        ,personas[i].correoInstitucional
                        ,personas[i].correoPersonal
                        ,personas[i].fechaDeIngreso
                        ,personas[i].horario.horario
                        , '<td><button onclick=\'eliminarPersona(this);\' value="'+personas[i].idPersona+'" class=\'btn btn-sm btn-icon-split btn-danger\'><span class=\'icon text-white-50\'><i class=\'fas fa-trash\'></i></span><span class=\'text\'>Eliminar</span></button><br/>' +
                        '<form method=\'post\' action=\'buscarPersona.action\'><button name=\'params\' type=\'submit\' value="'+personas[i].idPersona+'" class=\'btn btn-sm btn-icon-split btn-warning\'><span class=\'icon text-white-50\'><i class=\'fas fa-exclamation-triangle\'></i></span><span class=\'text\'>Modificar</span></button></form></td> </tr>'  ]).draw(false);
                }

            },
            error: function (error) {
                console.log("al error");
                console.log(error);
            }
        }
    );
}
