var raiz = window.location.origin + '/SAPAP/';
var lista = [];

function consulta() {
    $.ajax({
            type: 'POST',
            url: raiz + 'consultaProyectos',
            success: function (respuesta) {
                var proyectos = respuesta.respuestas.proyectos;
                var table = $('#dataTable').DataTable();
                table.clear().data;
                for (var i=0;i<proyectos.length;i++){
                    var aps="";
                    var rd="";
                    var rape="";
                    if (proyectos[i].aps.length==0){
                        aps="Pendiente de Asignar"
                    }
                    if (proyectos[i].rapes.length==0){
                        rape="Pendiente de Asignar"
                    }
                    if (proyectos[i].rds.length==0){
                        rd="Pendiente de Asignar"
                    }
                    for (var j=0;j<proyectos[i].aps.length;j++){
                        if (j==0){
                            aps=proyectos[i].aps[j].nombre;
                        }else{
                            aps=aps+","+proyectos[i].aps[j].nombre;
                        }
                    }
                    for (var k=0;k<proyectos[i].rds.length;k++){
                        if (k==0){
                            rd=proyectos[i].rds[k].nombre;
                        }else{
                            rd=rd+","+proyectos[i].rds[k].nombre;
                        }

                    }
                    for (var l=0;l<proyectos[i].rapes.length;l++){
                        if (l==0){
                            rape=proyectos[i].rapes[l].nombre;
                        }else{
                            rape=rape+","+proyectos[i].rapes[l].nombre;
                        }

                    }
                    table.row.add([proyectos[i].identificador
                        ,proyectos[i].nombre
                        ,proyectos[i].nombreDelCliente
                        ,proyectos[i].correoDelCliente
                        ,proyectos[i].numTelefonico
                        ,proyectos[i].direccionCliente
                        ,proyectos[i].fechaInicio
                        ,proyectos[i].fechaFin
                        ,proyectos[i].descripcion
                        ,'$ '+proyectos[i].costo
                        ,aps
                        ,rd
                        ,rape
                        ,'<td><button onclick=\'eliminarProyecto(this);\' value="'+proyectos[i].idProyecto+'" class=\'btn btn-sm btn-icon-split btn-danger\'><span class=\'icon text-white-50\'><i class=\'fas fa-trash\'></i></span><span class=\'text\'>Eliminar</span></button><br/>' +
                        '<form method=\'post\' action=\'buscarProyecto('+proyectos[i].idProyecto+')\'><button name=\'idCursoModificar\' type=\'submit\' value="'+proyectos[i].idProyecto+'" class=\'btn btn-sm btn-icon-split btn-warning\'><span class=\'icon text-white-50\'><i class=\'fas fa-exclamation-triangle\'></i></span><span class=\'text\'>Modificar</span></button></form></td> </tr>'  ]).draw(false);
                }
            }
        }
    );
};



function eliminarProyecto(button) {

    $.ajax({
        type: 'POST',
        url: raiz + 'eliminarProyecto',
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
            consulta();
        },
        error: function (error) {
            console.log("al error");
            console.log(error);
        }
    });
}