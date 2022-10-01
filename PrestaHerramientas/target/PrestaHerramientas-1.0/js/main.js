var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    getUsuario().then(function () {
        
        $("#mi-perfil-btn").attr("href","profile.html?username=" + username);
        
        $("#user-saldo").html(user.saldo.toFixed(2) + "$");

        getHerramienta(false, "ASC");

        $("#ordenar-marca").click(ordenarHerramientas);
    });
});


async function getUsuario() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            username: username
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;
            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });

}

function getHerramienta(ordenar, orden) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletHerramientasListar",
        data: $.param({
            ordenar: ordenar,
            orden: orden
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                mostrarherramientas(parsedResult);
            } else {
                console.log("Error recuperando los datos de las herramientas");
            }
        }
    });
}


function mostrarherramientas(herramienta) {

    let contenido = "";

    $.each(herramienta, function (index, herramienta) {

        herramienta = JSON.parse(herramienta);
        let precio;

        if (herramienta.nDisponible > 0) {

            if (user.frecuente) {

                if (herramienta.disponible) {
                    precio = (100000 - (100000 * 0.1));
                } else {
                    precio = (50000 - (50000 * 0.1));
                }
            } else {
                if (herramienta.disponible) {
                    precio = 200000;
                } else {
                    precio = 100000;
                }
            }

            contenido += '<tr><th scope="row">' + herramienta.id + '</th>' +
                    '<td>' + herramienta.nombre_herra + '</td>' +
                    '<td>' + herramienta.marca + '</td>' +
                    '<td>' + herramienta.tipoTrabajo + '</td>' +
                    '<td>' + herramienta.nDisponible + '</td>' +
                    '<td><input type="checkbox" name="disponible" id="disponible' + herramienta.id + '" disabled ';
            if (herramienta.disponible) {
                contenido += 'checked';
            }
            contenido += '></td>' +
                    '<td>' + precio + '</td>' +
                    '<td><button onclick="alquilarHerramienta(' + herramienta.id + ',' + precio + ');" class="btn btn-success" ';
            if (user.saldo < precio) {
                contenido += ' disabled ';
            }

            contenido += '>Alquilar</button></td></tr>'

        }
    });
    $("#herramienta-tbody").html(contenido);
}