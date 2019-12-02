var raiz = window.location.origin + '/SAPAP/';
var lista = [];




function consulta() {
    var table = $('#dataTable').DataTable();
    table.clear().data;
    $.ajax({
            type: 'POST',
            url: raiz + 'consultaProyectos',
            success: function (respuesta) {
                var proyectos = respuesta.respuestas.proyectos;

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
                            aps=proyectos[i].aps[j].nombre+" "+proyectos[i].aps[j].primerApellido;
                        }else{
                            aps=aps+","+proyectos[i].aps[j].nombre+" "+proyectos[i].aps[j].primerApellido;
                        }
                    }
                    for (var k=0;k<proyectos[i].rds.length;k++){
                        if (k==0){
                            rd=proyectos[i].rds[k].nombre+" "+proyectos[i].rds[k].primerApellido;
                        }else{
                            rd=rd+","+proyectos[i].rds[k].nombre+" "+proyectos[i].rds[k].primerApellido;
                        }

                    }
                    for (var l=0;l<proyectos[i].rapes.length;l++){
                        if (l==0){
                            rape=proyectos[i].rapes[l].nombre+" "+proyectos[i].rapes[l].primerApellido;
                        }else{
                            rape=rape+","+proyectos[i].rapes[l].nombre+" "+proyectos[i].rapes[l].primerApellido;
                        }

                    }
                    table.row.add([
                        proyectos[i].identificador
                        ,proyectos[i].nombre
                        ,proyectos[i].nombreDelCliente
                        ,proyectos[i].correoDelCliente
                        ,proyectos[i].numTelefonico
                        ,proyectos[i].direccionCliente
                        ,proyectos[i].fechaInicio
                        ,proyectos[i].fechaFin
                        ,proyectos[i].descripcion
                        ,'$ '+proyectos[i].costo
                        ,rape
                        ,rd
                        ,aps
                        ,'<td><button onclick=\'eliminarProyecto(this);\' value="'+proyectos[i].idProyecto+'" class=\'btn btn-sm btn-icon-split btn-danger\'><span class=\'icon text-white-50\'><i class=\'fas fa-trash\'></i></span><span class=\'text\'>Eliminar</span></button><br/>' +
                        '<form method=\'post\' action=\'buscarProyecto.action\'><button name=\'params\' type=\'submit\' value="'+proyectos[i].idProyecto+'" class=\'btn btn-sm btn-icon-split btn-warning\' > ' +
                        '<span class=\'icon text-white-50\'><i class=\'fas fa-exclamation-triangle\'></i></span><span class=\'text\'>Modificar</span> <s:hidden name="params" value="tadeo"/></button>  </form></td> </tr>'
                    ]).draw(false);
                }
            }
        }
    );
};

function consultaIndividual() {
    var id=document.getElementById("idUsSesion").value;

    var table = $('#dataTable').DataTable();
    table.clear().data;
    var params={id:id}
    $.ajax({
            type: 'POST',
        data:{
            params:JSON.stringify(params)
        }, url: raiz + 'consultaProyectosInd',
            success: function (respuesta) {
                var proyectos = respuesta.respuestas.proyectos;

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
                            aps=proyectos[i].aps[j].nombre+" "+proyectos[i].aps[j].primerApellido;
                        }else{
                            aps=aps+","+proyectos[i].aps[j].nombre+" "+proyectos[i].aps[j].primerApellido;
                        }
                    }
                    for (var k=0;k<proyectos[i].rds.length;k++){
                        if (k==0){
                            rd=proyectos[i].rds[k].nombre+" "+proyectos[i].rds[k].primerApellido;
                        }else{
                            rd=rd+","+proyectos[i].rds[k].nombre+" "+proyectos[i].rds[k].primerApellido;
                        }

                    }
                    for (var l=0;l<proyectos[i].rapes.length;l++){
                        if (l==0){
                            rape=proyectos[i].rapes[l].nombre+" "+proyectos[i].rapes[l].primerApellido;
                        }else{
                            rape=rape+","+proyectos[i].rapes[l].nombre+" "+proyectos[i].rapes[l].primerApellido;
                        }

                    }
                    table.row.add([
                        proyectos[i].identificador
                        ,proyectos[i].nombre
                        ,proyectos[i].nombreDelCliente
                        ,proyectos[i].correoDelCliente
                        ,proyectos[i].numTelefonico
                        ,proyectos[i].direccionCliente
                        ,proyectos[i].fechaInicio
                        ,proyectos[i].fechaFin
                        ,proyectos[i].descripcion
                        ,'$ '+proyectos[i].costo
                        ,rape
                        ,rd
                        ,aps]).draw(false);
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
function consultaRecursos() {

    $.ajax({
        type: 'POST',
        url: raiz + 'consultarRecursos',
        data:{

        },
        success: function (respuesta) {

        },
        error: function (error) {
            console.log("al error");
            console.log(error);
        }
    });
}

function registrarProyecto() {
    var aps=$('#apSelect').val();
    var rds=$('#rdSelect').val();
    var rapes=$('#rapeSelect').val();
    var params={identificador:document.getElementById("identificador").value,
        nombreProyecto:document.getElementById("nombre").value,
        nombreCliente:document.getElementById("nombreCliente").value,
        correoE:document.getElementById("correoE").value,
        numeroTelefonico:document.getElementById("numeroTelefonico").value,
        direccionDelCliente:document.getElementById("direccionDelCliente").value,
        fechaInicio:document.getElementById("fechaInicio").value,
        fechaFin:document.getElementById("fechaFin").value,
        descripcion:document.getElementById("descripcion").value,
        costo:document.getElementById("costo").value,
        rds:rds,
        aps:aps,
        rapes:rapes}
    $.ajax({
        type: 'POST',
        url: raiz + 'registrarProyecto',
        data:{
            params:JSON.stringify(params)
        },
        success: function (respuesta) {
            Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Actualizado correctamente',
                showConfirmButton: false,
                timer: 1500
            });
            var form =  document.getElementById("formProyectos");
            form.reset();
        },
        error: function (error) {
            console.log("al error");
            console.log(error);
        }
    });
}


    function modificarProyecto() {
        var aps=$('#apSelect').val();
        var rds=$('#rdSelect').val();
        var rapes=$('#rapeSelect').val();
        var params={identificador:document.getElementById("identificador").value,
            nombreProyecto:document.getElementById("nombre").value,
            nombreCliente:document.getElementById("nombreCliente").value,
            correoE:document.getElementById("correoE").value,
            numeroTelefonico:document.getElementById("numeroTelefonico").value,
            direccionDelCliente:document.getElementById("direccionDelCliente").value,
            fechaInicio:document.getElementById("fechaInicio").value,
            fechaFin:document.getElementById("fechaFin").value,
            descripcion:document.getElementById("descripcion").value,
            costo:document.getElementById("costo").value
            ,idProyecto:document.getElementById("idProyecto").value,
            rds:rds,
            aps:aps,
            rapes:rapes}
        $.ajax({
            type: 'POST',
            url: raiz + 'modificarProyecto',
            data:{
            params:JSON.stringify(params)
            },
            success: function (respuesta) {
                if(respuesta.respuestas.response){
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Modificado correctamente',
                        showConfirmButton: false,
                        timer: 2500
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
                setTimeout(function () {

                    top.location.href = 'http://localhost:8080/SAPAP/vista/RH/ProyectosRH.jsp';
                },3500)
            },
            error: function (error) {
                console.log("al error");
                console.log(error);
            }
        });

}