var raiz = window.location.origin + '/SAPAP/';
var lista = [];
function consultarJustificantesPendientes() {
    $.ajax({
            type: 'POST',
            url: raiz + 'consultarJustificantesPendientes',
            success: function (respuesta) {
                var lista = respuesta.respuestas.prueba;
                alert(lista[0].fechaElaboracion);
            }
        }
    );
};


