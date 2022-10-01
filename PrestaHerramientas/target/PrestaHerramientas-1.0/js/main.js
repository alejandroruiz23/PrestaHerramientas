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
                mostrarHerramienta(parsedResult);
            } else {
                console.log("Error recuperando los datos de las herramientas");
            }
        }
    });
}


function mostrarHerramienta(herramienta) {

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
                    '<td><button onclick="Alquilar(' + herramienta.id + ',' + precio + ');" class="btn btn-success" ';
            if (user.saldo < precio) {
                contenido += ' disabled ';
            }

            contenido += '>Reservar</button></td></tr>'

        }
    });
    $("#herramienta-tbody").html(contenido);
    
}

function ordenarHerramientas() {

    if ($("#icono-ordenar").hasClass("fa-sort")) {
        getHerramienta(true, "ASC");
        $("#icono-ordenar").removeClass("fa-sort");
        $("#icono-ordenar").addClass("fa-sort-down");
    } else if ($("#icono-ordenar").hasClass("fa-sort-down")) {
        getHerramienta(true, "DESC");
        $("#icono-ordenar").removeClass("fa-sort-down");
        $("#icono-ordenar").addClass("fa-sort-up");
    } else if ($("#icono-ordenar").hasClass("fa-sort-up")) {
        getHerramienta(false, "ASC");
        $("#icono-ordenar").removeClass("fa-sort-up");
        $("#icono-ordenar").addClass("fa-sort");
    }
}
function alquilarHerramienta(id, precio) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletHerramientaAlquilar",
        data: $.param({
            id: id,
            username: username

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                restarDinero(precio).then(function () {
                    location.reload();
                })
            } else {
                console.log("Error en la reserva de la herramienta");
            }
        }
    });
}


async function restarDinero(precio) {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioRestarDinero",
        data: $.param({
            username: username,
            saldo: parseFloat(user.saldo - precio)

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                console.log("Saldo actualizado");
            } else {
                console.log("Error en el proceso de pago");
            }
        }
    });
}