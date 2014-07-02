var arrayScript = new Array();



function cargarJS(url) {
    var newscript = document.createElement('script');
    newscript.type = 'text/javascript';
    newscript.async = false;
    newscript.id = arrayScript.length + 1;
    newscript.src = url;
    ﻿(document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(newscript);
    arrayScript.push(arrayScript.length + 1);
}



/*Funcion que remueve las JS*/
function removeFilesLoad() {
    $.each(arrayScript, function(key, value) {
        $("#" + value).remove();
    });
    arrayScript = [];
}

/*Funcion que me permite cargar una pagina web*/
function cargarPaginaFunction(idDiv, href, fn) {
    $('body').addClass("loading");
    $("#" + idDiv).empty();

    //$("#loading_layer").css("display", "inline");
    $("#" + idDiv).load(href, function() {
        fn();
        $('body').removeClass("loading");
    });
}

/*Funcion que me permite cargar una pagina web*/
function cargarPagina(idDiv, href) {
    //map.removeLayer(layersNegocio);
    // idDiv
    $('body').addClass("loading");
    $("#" + idDiv).empty();

    //$("#loading_layer").css("display", "inline");
    $("#" + idDiv).load(href, function() {
        $('body').removeClass("loading");
        //fn();
    });
}




/*__________________________________EVENTOS DEL MENU___________________________________________________*/

/*______Cargando al Menu cliente__________*/
$("#MenuCliente").click(function(evento) {

    removeFilesLoad();
    cargarPagina('ContainerModal', urlApp + '/html/Cliente/ViewModalCliente.html');
    var funtionCallJS = function() {
        cargarJS(urlApp + '/js/App/Cliente/JSCliente.js');
    };
    cargarPaginaFunction('ContainerSidebar', urlApp + '/html/Cliente/ViewSideBarCliente.html', funtionCallJS);
    IndOperCreate = "Cliente";
    map.removeLayer(layersNegocio);
    $('.inf_sidebar').click();
    layerGroup.clearLayers();
    drawnItems.clearLayers();
    deleteAllInfoLeyends();
});


/*______Cargando al Menu Geometria__________*/
$("#MenuGeometria").click(function(evento) {
    removeFilesLoad();
    cargarPagina('ContainerModal', urlApp + '/html/Geometria/ViewModalGeometria.html');
    var funtionCallJS = function() {
        cargarJS(urlApp + '/js/App/Geometria/JSGeometria.js');
    };
    cargarPaginaFunction('ContainerSidebar', urlApp + '/html/Geometria/ViewSideBarGeometria.html', funtionCallJS);
    IndOperCreate = 'Geometria';
    $('.inf_sidebar').click();
    console.log(layerGroup);
    //layerGroup.clearLayers();
    //drawnItems.clearLayers();
});

$("#MenuVenta").click(function(evento) {

    removeFilesLoad();
    cargarPagina('ContainerModal', urlApp + '/html/Ventas/ViewModalVenta.html');

    var funtionCallJS = function() {
        cargarJS(urlApp + '/js/Libs/BootstrapDateRangePicker/moment.js');
        cargarJS(urlApp + '/js/Libs/BootstrapDateRangePicker/daterangepicker.js');
        cargarJS(urlApp + '/js/App/Venta/JSVenta.js');
    };
    cargarPaginaFunction('ContainerSidebar', urlApp + '/html/Ventas/ViewSideBarVenta.html', funtionCallJS);
    IndOperCreate = "Cliente";
    map.removeLayer(layersNegocio);
    $('.inf_sidebar').click();
    console.log(layerGroup);
    layerGroup.clearLayers();
    drawnItems.clearLayers();
    deleteAllInfoLeyends();

});

$("#MenuZona").click(function(evento) {

    $("#MenuGeometria").trigger('click');


    setTimeout(function() {
        listar_geometrias();
    }, 500);



});


/*fuin que me permite eliminar las leyendas y los infomaciones*/

function deleteAllInfoLeyends() {
    var info = '';
    var legend = '';
    console.log('Array Informaion:' + ArrayInformacion);
    console.log('Array leyendas:' + ArrayLeyendas);
    for (var i = 0; i < ArrayInformacion.length; i++) {

        info += ArrayInformacion[i] + '.removeFrom(map);'

    }
    for (var i = 0; i < ArrayLeyendas.length; i++) {

        legend += ArrayLeyendas[i] + '.removeFrom(map);'
    }
    console.log(info);
    console.log(legend);
    eval(info);
    eval(legend);
    ArrayInformacion.length = 0;
    ArrayLeyendas.length = 0;
}

