//var sidebarCliente = L.control.sidebar('sidebar', {position: 'left'});//variable global a cambiar
/*________________________AREA INICIO ____________________*/
$(function() {
    cargarComboDistrito();
    cargarComboCategoriaCliente();
    cargarComboGiroNegocio();
    $("#blqDetalleCliente").hide();
    crearGrillaCliente();
    //agregarCapaLayers();
    $('.content_draggable').css({'left': $(window).width() - 200});
    autocompletarCliente();
    cargarLayerNegocioCliente();
});



/*________________________AREA DE EVENTOS ____________________*/
$(function() {
    /*Evento de carga boton busqueda de cliente para mostrar el modal de busqueda*/
    $("#btnBusquedaCliente").click(function() {
        busquedaCliente();
    });
    /*Evento de carga busqueda de cliente*/
    $("#btnBuscarCliente").click(function() {
        mostrarClientes();
    });

    $('#Licon').mouseover(function() {
        $('#Ldetail').show();
        $('#Licon').hide();
    });

    $('#Ldetail').click(function() {
        $('#Licon').show();
        $('#Ldetail').hide();
    });
    /*Evento que muestra con click los puntos en cluster*/
    $("#chkCluster").click(function() {
        if ($("#chkCluster").is(':checked')) {
            map.addLayer(markersClusterCliente);
        } else {
            map.removeLayer(markersClusterCliente);
        }
    });
    /*Evento que muestra con click los puntos en cluster*/
    $(".btnMostrarbqlBusqueda").click(function() {
        ocultarBloqueDetalleCliente();
    });
    /*Evento que muestra con click los puntos en el mapa de calor*/
    $("#chkHeat").click(function() {
        if ($("#chkHeat").is(':checked')) {
            //map.removeLayer(markersClusterCliente);
            if (heatCliente === null) {
                heatCliente = L.heatLayer(PuntosClientesGlobal).addTo(map);
            } else {
                map.addLayer(heatCliente);
            }

        } else {
            map.removeLayer(heatCliente);

        }
    });



    ///click para squares

    $("#chkSquare0005").click(function() {
        if ($("#chkSquare0005").is(':checked')) {
            optener_datos_square0005();

        } else {
            map.removeLayer(layerSquare0005);

        }
    });

    $("#chkSquare00025").click(function() {
        if ($("#chkSquare00025").is(':checked')) {
            optener_datos_square00025();

        } else {
            map.removeLayer(LayerSquare0025);

        }
    });

    $("#chkHexagono00025").click(function() {
        if ($("#chkHexagono00025").is(':checked')) {
            get_hexagono00025();

        } else {
            map.removeLayer(Layerhexagono00025);

        }
    });
   $("#chkHexagono0005").click(function() {
        if ($("#chkHexagono0005").is(':checked')) {
            get_hexagono0005();

        } else {
            map.removeLayer(Layerhexagono0005);

        }
    });




    /*Evento que muestra con click la capa de las rutas*/
    $("#chkLayerRutas").click(function() {
        if ($("#chkLayerRutas").is(':checked')) {
            loadLayerRutasCliente();
            ArrayInformacion.push('infoLayerRutas');
            ArrayLeyendas.push('legendLayerRutas');
            $(".info").draggable();


            //css();
        } else {
            map.removeLayer(LayerRutasCliente);
            infoLayerRutas.removeFrom(map);
            legendLayerRutas.removeFrom(map);
            eliminarElemArray(ArrayInformacion, 'infoLayerRutas');
            eliminarElemArray(ArrayLeyendas, 'legendLayerRutas');

        }
    });
    /*Evento que muestra con click la capa de las mesas*/
    $("#chkLayerMesas").click(function() {
        if ($("#chkLayerMesas").is(':checked')) {
            loadLayerMesasCliente();
            ArrayInformacion.push('infoLayerMesas');
            ArrayLeyendas.push('legendLayerMesas');
            $(".info").draggable();

            //  css();
        } else {
            map.removeLayer(LayerMesasCliente);
            infoLayerMesas.removeFrom(map);
            legendLayerMesas.removeFrom(map);
            eliminarElemArray(ArrayInformacion, 'infoLayerMesas');
            eliminarElemArray(ArrayLeyendas, 'legendLayerMesas');
        }


    });
    /*Evento que muestra con click la capa de las dias*/
    $("#chkLayerDias").click(function() {
        if ($("#chkLayerDias").is(':checked')) {
            loadLayerDiasCliente();
            ArrayInformacion.push('infoClienteLayer');
            ArrayLeyendas.push('legendCliente');
            $(".info").draggable();

            // css();
        } else {
            map.removeLayer(LayerDiasCliente);
            infoClienteLayer.removeFrom(map);
            legendCliente.removeFrom(map);
            eliminarElemArray(ArrayInformacion, 'infoClienteLayer');
            eliminarElemArray(ArrayLeyendas, 'legendCliente');
        }
    });

    /*INFORMACION DRAGABLE*/

});

/*Agregar a la capa de mapas los  checkbox capas rutas, mesas y dias*/
function  cargarLayerNegocioCliente() {
    //var nada = {};
    var overlays = {
        "": 'overlays'
    };
    CapaLayers.addOverlay(overlays);

    $('.leaflet-control-layers-overlays').html('');

    $('.leaflet-control-layers-overlays').append('<label>' +
            '<input id="chkLayerRutas" class="leaflet-control-layers-selector" type="checkbox">' +
            '<span> Rutas</span>' +
            '</label>'
            );
    $('.leaflet-control-layers-overlays').append('<label>' +
            '<input id="chkLayerMesas" class="leaflet-control-layers-selector" type="checkbox">' +
            '<span> Mesas</span>' +
            '</label>'
            );

    $('.leaflet-control-layers-overlays').append('<label>' +
            '<input id="chkLayerDias" class="leaflet-control-layers-selector" type="checkbox">' +
            '<span> Dias</span>' +
            '</label>'
            );
}

/*Funcion que permite generar el autocompletar de */
function autocompletarCliente() {
    $("#searchbox").AutocompleteGeoSolution("ClienteController/autocompletarCliente.htm", "#searchbox", 200,
            function(item) {
                return {
                    label: item.nombrecliente,
                    nombre: item.nombrecliente,
                    direccion: item.direccion,
                    distrito: item.distrito,
                    categoria: item.categoriacliente,
                    gironegocio: item.gironegocio,
                    x: item.coordenada_x,
                    y: item.coordenada_y
                }
            },
            function(event, ui) {

                $('#txtNombresClienteDetalle').val(ui.item.nombre);
                $('#txtDireccionClienteDetalle').val(ui.item.direccion);
                $('#txtDistritoClienteDetalle').val(ui.item.distrito);
                $('#txtCategoriaClienteDetalle').val(ui.item.categoria);
                $('#txtGiroClienteDetalle').val(ui.item.gironegocio);
                $('#txtLatitudClienteDetalle').val(ui.item.x);
                $('#txtLongitudClienteDetalle').val(ui.item.y);
                $('#blqDetalleCliente').show();
                $('#blqBusquedaCliente').hide();

                motrarAutocompleteCliente(ui.item.x, ui.item.y, ui.item.nombre);

            });
}



/*funcion que cargar los siderbar*/
function agregarCapaLayers() {
    layersNegocio.addTo(map);
}

/*funcion que carga la barra siderbar*/
function loadSiderbar() {
    map.addControl(sidebarCliente);
}

/* funcion que leventa la busqueda de un cliente */
function busquedaCliente() {
    $('#ModalCliente').modal('show');
    return false;
}

/*_______________AREA DE LAS FUNCIONES QUE VA UTILIZAR CLIENTE ____________________*/

/*Funcion que realiza la busqueda de los cliente de acuerdo a los filtros*/
function mostrarClientes() {
    $('body').addClass("loading");

    var cliente = {
        IndOpSp: 1,
        nombrecliente: $('#txtNombresCliente').val(),
        direccion: $('#txtDireccionCliente').val(),
        distrito: $('#drpDistritoCliente').val(),
        categoriacliente: $('#drpCategoriaCliente').val(),
        gironegocio: $('#drpGiroNegocioCliente').val()
    };

    var filtros = [];
    ($('#txtNombresCliente').val().length > 0) ? filtros.push('Nombres Apellidos') : console.log('');
    ($('#txtDireccionCliente').val().length > 0) ? filtros.push('Direccion') : console.log('');
    ($('#drpDistritoCliente').val() != 'null') ? filtros.push('Distrito') : console.log('');
    ($('#drpCategoriaCliente').val() != 'null') ? filtros.push('Categoria cliente') : console.log('');
    ($('#drpGiroNegocioCliente').val() != 'null') ? filtros.push('Giro Negocio') : console.log('');

    ViewFilters('#pnlFilter', cliente, 'cliente', ['ninguno']);

    setTimeout(function() {

        $.ajaxGeoSolution('ClienteController/consultarClienteObject.htm', {poClienteBE: cliente}, false, function(response) {
            cargarGrillaCliente(response);
        });

        $.ajaxGeoSolution('ClienteController/mostrarPuntosCliente.htm', {poClienteBE: cliente}, false, function(response) {
            var tipoPuntos = $('input[name=optFormaPuntos]:radio:checked').val();
            (tipoPuntos == 'Cluster') ? tipoPuntos = 'Cluster' : tipoPuntos = 'Heat';
            clientesBusqueda = response;

            $.ajaxGeoSolution('ClienteController/listaLayers.htm', {ind: 1, poClienteBE: cliente}, false, function(response) {
                ArrayClientesDias = response;
            });

            $.ajaxGeoSolution('ClienteController/listaLayers.htm', {ind: 2, poClienteBE: cliente}, false, function(response) {
                ArrayClientesMesas = response;
            });

            $.ajaxGeoSolution('ClienteController/listaLayers.htm', {ind: 3, poClienteBE: cliente}, false, function(response) {
                ArrayClientesRutas = response;
            });

            $('body').removeClass("loading");
            pintarPuntosClientes(tipoPuntos, response);

        });
    }, 100);

}

/*Mostrar el detalle del cliente buscado*/
function mostrarBloqueDetalleCliente() {
    $("#blqDetalleCliente").show();
    $("#blqBusquedaCliente").hide();
}

/*Ocultar el detalle del cliente buscado*/
function ocultarBloqueDetalleCliente() {

    $("#blqDetalleCliente").hide();
    $("#blqBusquedaCliente").show();

}


var leafletView = new PruneClusterForLeaflet();//variable para la utilizacion del prune clustered
/*Funcion que me permite pintar los puntos en marker clustered*/
function pintarPuntosClientes(tipoPuntos, PuntosClientes) {


    var addressPoints = [];
    var len = PuntosClientes.length;

    map.removeLayer(markersClusterCliente);
    var markerList = [];

    for (var i = 0; i < len; i++) {

        var a = PuntosClientes[i];
        var point = [Number(a[6]), Number(a[7]), a[1] + '<br> ' + a[2] + '<br> ' + a[3]];
        var marker = L.marker(L.latLng(a[6], a[7]), {title: a[1] + '<br> ' + a[2] + '<br> ' + a[3]});
        marker.bindPopup(a[1] + '<br> ' + a[2] + '<br> ' + a[3]);
        markerList.push(marker);
        addressPoints.push(point);

    }

    PuntosClientesGlobal = addressPoints;
    markersClusterCliente = L.markerClusterGroup();
    markersClusterCliente.addLayers(markerList);
    map.addLayer(markersClusterCliente);
    //map.addLayer(leafletView);

}

/*Funcion para mostrar los filtros de un app*/
function ViewFilters(div, Object, objName, ArrayItemsEliminar) {
    var filters = [];
    for (i in Object) {
        filters.push(objName + "." + i + " = " + Object[i]);
    }

    //filters = eliminarArraytoArray(filters, ArrayItemsEliminar);
    $(div).html('');
    var htmlFilters = '';
    var lenFilter = filters.length;
    for (var i = 1; i < lenFilter; i++) {
        htmlFilters += '<span class="label label-success">' + filters[i] + '</span><br>';
    }
    $(div).append(htmlFilters);
}


/*==================================Combos Clientes================================*/
/*funcion para cargas combo distrito*/
function cargarComboDistrito() {
    var cliente = {
        IndOpSp: 1
    };
    $.CargarComboGeoSolution('ClienteController/cargarCombos.htm', {poClienteBE: cliente}, '#drpDistritoCliente');
}
/*funcion para cargas combo categoria cliente*/
function cargarComboCategoriaCliente() {
    var cliente = {
        IndOpSp: 2
    };
    $.CargarComboGeoSolution('ClienteController/cargarCombos.htm', {poClienteBE: cliente}, '#drpCategoriaCliente');
}
/*funcion para cargas combo giro negocio*/
function cargarComboGiroNegocio() {
    var cliente = {
        IndOpSp: 3
    };
    $.CargarComboGeoSolution('ClienteController/cargarCombos.htm', {poClienteBE: cliente}, '#drpGiroNegocioCliente');
}
/*==============================combos clientes========================*/
/*Funcion que me permite crear la grilla del resultado de la busqueda de clientes*/
function crearGrillaCliente() {
    $("#gridCliente").jqGrid("clearGridData", true).trigger("reloadGrid");
    jQuery("#gridCliente").jqGrid({
        datatype: "local",
        //datatype: "local",
        //data:data,
        height: 180,
        width: 385,
        colNames: [
            'IndOpSp',
            'Cod. Cliente',
            'Nomb. Cliente',
            'Direcci&oacute;n',
            'Distrito',
            'Categoria',
            'Giro Neg.',
            'Coord. Y',
            'Coord. X',
            'visual'],
        colModel: [
            {name: 'IndOpSp', index: 'IndOpSp', width: 55, hidden: true},
            {name: 'codigocliente', index: 'codigocliente', width: 90, hidden: false},
            {name: 'nombrecliente', index: 'nombrecliente', width: 150},
            {name: 'direccion', index: 'direccion', width: 150},
            {name: 'distrito', index: 'distrito', width: 120, align: "right", hidden: true},
            {name: 'categoriacliente', index: 'categoriacliente', width: 120, align: "right", hidden: true},
            {name: 'gironegocio', index: 'gironegocio', width: 120, align: "right"},
            {name: 'coordenada_y', index: 'coordenada_y', width: 150, hidden: true},
            {name: 'coordenada_x', index: 'coordenada_x', width: 150, hidden: true},
            {name: 'visual', index: 'visual', width: 150, hidden: true},
        ],
        rowNum: 10,
        pager: '#paperCliente',
        ondblClickRow: mostrarDetalleCliente,
        viewrecords: true,
        sortable: true,
        shrinkToFit: false,
        sortorder: "desc",
        caption: "Lista de Clientes Filtrados"

    }).navGrid("#paperCliente", {edit: false, add: false, del: false});
    jQuery("#gridCliente").jqGrid('filterToolbar', {stringResult: true, searchOnEnter: false});
}

/*funcion que me permite cargar la grilla de cliente a partir de un busqueda de clientes */
function cargarGrillaCliente(response) {
    $('#gridCliente').jqGrid('clearGridData');
    jQuery("#gridCliente").jqGrid('setGridParam', {data: response}).trigger('reloadGrid');
    // $("#gridCliente")[0].addJSONData(response);
}

/*Funcion que me permite mostrar el detalle de un cliente seleccionado de una grilla*/
function mostrarDetalleCliente(id) {

    if (circleBusqCliente == null) {
    }
    else {
        map.removeLayer(circleBusqCliente);
    }
    var rowData = jQuery('#gridCliente').jqGrid('getRowData', id);
    console.log(rowData);

    $('#txtNombresClienteDetalle').val(rowData.nombrecliente);
    $('#txtDireccionClienteDetalle').val(rowData.direccion);
    $('#txtDistritoClienteDetalle').val(rowData.distrito);
    $('#txtCategoriaClienteDetalle').val(rowData.categoriacliente);
    $('#txtGiroClienteDetalle').val(rowData.gironegocio);
    $('#txtLatitudClienteDetalle').val(rowData.coordenada_y);
    $('#txtLongitudClienteDetalle').val(rowData.coordenada_x);

    circleBusqCliente = L.circle([rowData.coordenada_y, rowData.coordenada_x], 50, {
        color: '#428BCA',
        fillColor: '#428BCA',
        fillOpacity: 0.5
    }).addTo(map);

    map.setView([rowData.coordenada_y, rowData.coordenada_x], 18);
    if (marker == null) {
    }
    else {
        map.removeLayer(marker);
    }
    //var lat = posicion.coords.latitude; //obtengo la latitud
    //var lon = posicion.coords.longitude; //obtengo la longitud
    //var error = posicion.coords.accuracy;
    marker = L.marker([rowData.coordenada_y, rowData.coordenada_x]).addTo(map);
    marker.bindPopup("" + rowData.nombrecliente).openPopup();
    mostrarBloqueDetalleCliente();
}

/*funcion que me permite mostrar el resultado de la busqueda de un autocompletado*/
function motrarAutocompleteCliente(x, y, nombre) {
    if (circleBusqCliente == null) {
    }
    else {
        map.removeLayer(circleBusqCliente);
    }
    circleBusqCliente = L.circle([y, x], 50, {
        color: '#428BCA',
        fillColor: '#428BCA',
        fillOpacity: 0.5
    }).addTo(map);
    map.setView([y, x], 18);

    if (marker == null) {
    }
    else {
        map.removeLayer(marker);
    }
    //var lat = posicion.coords.latitude; //obtengo la latitud
    //var lon = posicion.coords.longitude; //obtengo la longitud
    //var error = posicion.coords.accuracy;
    marker = L.marker([y, x]).addTo(map);
    marker.bindPopup("" + nombre).openPopup();
}

/*funcion que me permite filtrar los puntos dentro de un polygon que utiliza POINT IN POLYGON*/
function filtrarPuntosClientes(Polygono, PuntosClientes) {
    var PuntosFiltrados = [];
    PuntosFiltrados = FiltroPuntosPolygon(Polygono, PuntosClientes);
    var lenclientesBusqueda = clientesBusqueda.length;
    var lenPuntosFiltrados = PuntosFiltrados.length;
    var ClientesFiltradosMostrar = [];

    for (var i = 0; i < lenclientesBusqueda; i++) {
        for (var j = 0; j < lenPuntosFiltrados; j++) {
            if (clientesBusqueda[i][6] == PuntosFiltrados[j][0] && clientesBusqueda[i][7] == PuntosFiltrados[j][1]) {
                ClientesFiltradosMostrar.push({
                    IndOpSp: 1,
                    codigocliente: clientesBusqueda[i][0],
                    nombrecliente: clientesBusqueda[i][1],
                    direccion: clientesBusqueda[i][2],
                    distrito: clientesBusqueda[i][3],
                    categoriacliente: clientesBusqueda[i][4],
                    gironegocio: clientesBusqueda[i][5],
                    coordenada_y: clientesBusqueda[i][6],
                    coordenada_x: clientesBusqueda[i][7],
                    visual: clientesBusqueda[i][8]
                });
            }
        }
    }
    cargarGrillaCliente(ClientesFiltradosMostrar);
}


/*==================que muestra los clientes filtrdos====================*/
/*____________________________COROPLEJICO__________________________________*/
/*cargar layer Mesas*/
function loadLayerMesasCliente() {
    LayerMesasCliente = L.geoJson(LayerMesas, {style: pintarMesas, onEachFeature: onEachFeatureMesas}).addTo(map);
    layerGroup.addLayer(LayerMesasCliente).addTo(map);
    cargarDetalleCapaMesas();
    cargarLeyendaMesas();
}
/*cargar layer rutas*/
function loadLayerRutasCliente() {
    LayerRutasCliente = L.geoJson(LayerRutas, {style: pintarRutas, onEachFeature: onEachFeatureRutas}).addTo(map);
    layerGroup.addLayer(LayerRutasCliente).addTo(map);
    cargarDetalleCapaRutas();
    cargarLeyendaRutas();
}
/*cargar layer Dias*/
function loadLayerDiasCliente() {
    LayerDiasCliente = L.geoJson(LayerDias, {style: pintarDias, onEachFeature: onEachFeature}).addTo(map);
    layerGroup.addLayer(LayerDiasCliente).addTo(map);
    cargarDetalleCapaDias();
    cargarLeyendaCliente();
}

/*?=====================================LAYER DIAS ============================================================*/

/*=====================LEGENDA==============================*/
//Leyenda de cliente Dias
legendCliente.onAdd = function(map) {

//    var CantPuntSlecc = PuntosClientesGlobal.length;
//    var nroCapas = LayerDias.features.length;
//    var rango = generarRango(CantPuntSlecc, nroCapas, '#FDFD2F', 10);

    //debugger;
    var div = L.DomUtil.create('div', 'info legend'),
            grades = [0, 10, 20, 50, 100, 200, 500, 1000],
            labels = [],
            from, to;

    labels.push(
            '<i style="background:#FFF "></i> 0'
            );

    for (var i = 0; i < rangoDias.length; i++) {
        from = (rangoDias[i][0] == 0) ? rangoDias[i][0] + 1 : rangoDias[i][0];
        to = rangoDias[i][1];
        labels.push('<i style="background:' + rangoDias[i][2] + '"></i> ' + rangoDias[i][3]);
    }

    div.innerHTML = ' <input id="verLegendDias" type="checkbox" value="" onclick="ocultarLeyendaDias();" checked > Mostrar Legend Dias<div id="infoLegendDias">' + labels.join('<br>') + '</div>';
    return div;
};
/*Funcion que me permite ocultar la leyenda de los dias*/
function ocultarLeyendaDias() {
    if ($("#verLegendDias").is(':checked')) {
        $('#infoLegendDias').show();
    } else {
        $('#infoLegendDias').hide();
    }
}

/*funcion que carga  la leyenda del capa de dias al mapa*/
function cargarLeyendaCliente() {
    legendCliente.addTo(map);
}


/*=====================INFORMACION==============================*/
//Funcion agregar al mapa de la variable dia que muestra la informacion de un cliente al pasar por la capa dias
infoClienteLayer.onAdd = function(map) {
    this._div = L.DomUtil.create('div', 'info');
    this.update();
    return this._div;
};
//Funcion modificar al mapa de la variable Informacion de dia que muestra la informacion de un cliente al pasar por la capa dias
infoClienteLayer.update = function(layer) {
    if (layer == null) {
        this._div.innerHTML = 'Pase el mouse sobre una Dia.';
    } else {
        var props = layer.feature.properties;

        //var puntosFiltrados = FiltroPuntosPolygonCoroplejico(layer.feature, PuntosClientesGlobal);
        var codDia = layer.feature.properties.cod;
        var lengh = 0;
        for (var i = 0; i < ArrayClientesDias.length; i++) {
            if (ArrayClientesDias[i][0] === codDia) {
                lengh = ArrayClientesDias[i][1];
            }
        }
        this._div.innerHTML = '<h4>Capa de Dias</h4>' + (props ?
                '<b>Capa: ' + props.Dia + '</b><br /> Cantidad clientes: ' + lengh + ''
                : 'Pase el mouse sobre una Zona');
    }
};

/*carga la capa de los detalles de un dia referente a la capa dias*/
function cargarDetalleCapaDias() {
    infoClienteLayer.addTo(map);
}

/*Funcion que se dispara al pasar el mouse por un dia de la capa dias */
function highlightFeature(e) {

    console.log(e);
    console.log(layer);
    var layer = e.target;

    layer.setStyle({
        weight: 5,
        color: '#666',
        dashArray: '',
        fillOpacity: 0.1
    });

    if (!L.Browser.ie && !L.Browser.opera) {
        layer.bringToFront();
    }

    infoClienteLayer.update(layer);
    bringToFront_draw();
}


/*Funion que se dispara al dejar de apuntar a un capa dia*/
function resetHighlight(e) {
    LayerDiasCliente.resetStyle(e.target);
    infoClienteLayer.update();
    bringToFront_draw();
}
/*Funion que se dispara  al acercar a un capa dias*/
function zoomToFeature(e) {
    map.fitBounds(e.target.getBounds());
    bringToFront_draw();
}

/*Funcion que genera los eventos de la capa DIA al crearse*/
function onEachFeature(feature, layer) {
    layer.on({
        mouseover: highlightFeature,
        mouseout: resetHighlight,
        click: zoomToFeature
    });
}



/*Funcion que pinta un la capa coroplejico de dias*/
function pintarDias(feature) {
    //var nomDia = feature.properties.Dia;

    var codDia = feature.properties.cod;
    var pintura = {
        "clickable": true,
        "fillColor": '#FFF',
        "weight": 1.0,
        "opacity": 1,
        "fillOpacity": 0.1,
        color: "red"
    };

    var colorcapa = "";

    for (var i = 0; i < ArrayClientesDias.length; i++) {
        if (ArrayClientesDias[i][0] === codDia) {
            for (var j = 0; j < rangoDias.length; j++) {
                //console.log('[' + rango[i][0] + '-' + rango[i][1] + '] ->' + longitudPunstosFiltrados);
                if (ArrayClientesDias[i][1] > 0 && ArrayClientesDias[i][1] >= rangoDias[j][0] && ArrayClientesDias[i][1] <= rangoDias[j][1] + 1) {
                    colorcapa = rangoDias[j][2];
                    console.log('Clientes Arrays' + i);
                    console.log('Rango Arrays' + j);
                    console.log(colorcapa);
                    break;
                }
                else {
                    colorcapa = '#FFF';
                    console.log(colorcapa);
                }
            }
        }
    }
    pintura.fillColor = colorcapa;

    return  pintura;
}

/*Funcion en desuso*/
function pintarCoroplejicoDias(feature) {

//    var CantPuntSlecc = PuntosClientesGlobal.length; //puntos que fueron filtrados
//    var nroCapas = LayerDias.features.length; //layer geojson
//    var rango = generarRango(CantPuntSlecc, nroCapas, '#FDFD2F', 10);
//    console.log(rango);
//    console.log(feature.properties.Dia);
    debugger;

    /*cuanto puntos pertenecen a ese poligono*/
    var puntosFiltrados = FiltroPuntosPolygonCoroplejico(feature, PuntosClientesGlobal);
    var longitudPunstosFiltrados = puntosFiltrados.length;

    var colorcapa = "";
    for (var i = 0; i < rangoDias.length; i++) {
        //console.log('[' + rango[i][0] + '-' + rango[i][1] + '] ->' + longitudPunstosFiltrados);
        if (longitudPunstosFiltrados > 0 && longitudPunstosFiltrados >= rangoDias[i][0] && longitudPunstosFiltrados <= rangoDias[i][1] + 1) {

            colorcapa = rangoDias[i][2];
            break;
        }
        else {
            colorcapa = '#FFF';
        }
    }
    return {"clickable": true,
        "fillColor": colorcapa,
        "weight": 1.0,
        "opacity": 1,
        "fillOpacity": 0.1,
        color: "blue"};


}

/*==================FIN ================================*/

/*?=====================================LAYER Rutas ============================================================*/

/*=====================LEGENDA==============================*/

//Leyenda de cliente Dias
legendLayerRutas.onAdd = function(map) {

    //var CantPuntSlecc = PuntosClientesGlobal.length;
    //var nroCapas = LayerRutas.features.length;
    //var rango = generarRango(CantPuntSlecc, redondeo(nroCapas / 10, 0), '#FF0000', 10);


    var div = L.DomUtil.create('div', 'info legend'),
            grades = [0, 10, 20, 50, 100, 200, 500, 1000],
            labels = [],
            from, to;

    labels.push(
            '<i style="background:#FFE1E1 "></i> 0'
            );

    for (var i = 0; i < rangoRutas.length; i++) {
        from = (rangoRutas[i][0] == 0) ? rangoRutas[i][0] + 1 : rangoRutas[i][0];
        to = rangoRutas[i][1];

        labels.push(
                '<i style="background:' + rangoRutas[i][2] + '"></i> ' + rangoRutas[i][3]);
    }

    div.innerHTML = ' <input id="verLegendRutas" type="checkbox" value="" onclick="ocultarLeyendaRutas();" checked > Mostrar Legend Rutas<div id="infoLegendRutas">' + labels.join('<br>') + '</div>';
    return div;
};
/*Funcion que me permite ocultar la leyenda de los Rutas*/
function ocultarLeyendaRutas() {
    if ($("#verLegendRutas").is(':checked')) {
        $('#infoLegendRutas').show();
    } else {
        $('#infoLegendRutas').hide();
    }
}

/*funcion que carga  la leyenda del capa rutas al mapa*/
function cargarLeyendaRutas() {
    legendLayerRutas.addTo(map);
}



/*=====================INFORMACION==============================*/

//Funcion agregar al mapa de la variable dia que muestra la informacion a detalle de un cliente al pasar por la capa Rutas
infoLayerRutas.onAdd = function(map) {
    this._div = L.DomUtil.create('div', 'info');
    this.update();
    return this._div;
};
//Funcion modificar al mapa de la variable Informacion de rutas que muestra la informacion de un cliente al pasar por la capa Rutas
infoLayerRutas.update = function(layer) {
    if (layer == null) {
        this._div.innerHTML = 'Pase el mouse sobre una Rutas';
    } else {

        //var puntosFiltrados = FiltroPuntosPolygonCoroplejico(layer.feature, PuntosClientesGlobal);
        var codDia = layer.feature.properties.CodZona;
        console.log(codDia);
        var lengh = 0;
        for (var i = 0; i < ArrayClientesRutas.length; i++) {
            if (ArrayClientesRutas[i][0] === codDia) {
                lengh = ArrayClientesRutas[i][1];
            }
        }

        var props = layer.feature.properties;
        var puntosFiltrados = FiltroPuntosPolygonCoroplejico(layer.feature, PuntosClientesGlobal);
        this._div.innerHTML = '<h4>Capa de Rutas</h4>' + (props ?
                '   <b>Mesa: ' + props.MESA + '</b><br />\n\
                    <b>Zona: ' + props.CodZona + '</b><br /> \n\
                    <b>Vendedor: ' + props.CodVend + '</b><br />\n\
                    <b>Ruta: ' + props.Ruta + '</b><br />\n\
                    <b>Dia: ' + props.Dia + '</b><br />\n\
                    <b>Cantidad clientes: ' + lengh + '</b>'
                : 'Pase el mouse sobre una Zona');
    }
};
/*carga la capa de los detalles de un Ruta referente a la capa Rutas*/
function cargarDetalleCapaRutas() {
    infoLayerRutas.addTo(map);
}

/*Funcion que genera los eventos de la capa RUTA al crearse*/
function onEachFeatureRutas(feature, layer) {
    layer.on({
        mouseover: highlightFeatureRutas,
        mouseout: resetHighlightRutas,
        click: zoomToFeatureRutas
    });
}
/*Funcion que se dispara al pasar el mouse por un dia de la capa Rutas */
function highlightFeatureRutas(e) {

    var layer = e.target;

    layer.setStyle({
        weight: 5,
        color: '#666',
        dashArray: '',
        fillOpacity: 0.1
    });

    if (!L.Browser.ie && !L.Browser.opera) {
        layer.bringToFront();
    }

    infoLayerRutas.update(layer);
    bringToFront_draw();
}


/*Funion que se dispara al dejar de apuntar a un capa Ruta*/
function resetHighlightRutas(e) {
    LayerRutasCliente.resetStyle(e.target);
    infoLayerRutas.update();
    bringToFront_draw();
}

/*Funion que se dispara  al acercar a un capa Rutas*/
function zoomToFeatureRutas(e) {
    map.fitBounds(e.target.getBounds());
    bringToFront_draw();
}

/*Funcion que pinta un la capa coroplejico de capa Rutas*/
function pintarRutas(feature) {

    var CodZona = feature.properties.CodZona;

    var pintura = {
        "clickable": true,
        "fillColor": '#FFF',
        "weight": 1.0,
        "opacity": 1,
        "fillOpacity": 0.1,
        color: "blue"
    };

    var colorcapa = "";

    for (var i = 0; i < ArrayClientesRutas.length; i++) {
        if (ArrayClientesRutas[i][0] == CodZona) {
            console.log('Ruta:' + CodZona);
            for (var j = 0; j < rangoRutas.length; j++) {
                console.log('cantidad:' + ArrayClientesRutas[i][1]);
                console.log('rango Ini:' + rangoRutas[j][0]);
                console.log('rango Fin:' + rangoRutas[j][1]);
                if (ArrayClientesRutas[i][1] > 0 && ArrayClientesRutas[i][1] >= rangoRutas[j][0] && ArrayClientesRutas[i][1] <= rangoRutas[j][1] + 1) {
                    colorcapa = rangoRutas[j][2];
                    break;
                }
                else {
                    colorcapa = '#FFF';
                    //console.log(colorcapa);
                }


            }
        }
    }
    console.log(colorcapa);
    pintura.fillColor = colorcapa;
    return  pintura;

}

/*Funcion en desuso que pinta un la capa coroplejico de dias*/
function pintarCoroplejicoRutas(feature) {

    var CantPuntSlecc = PuntosClientesGlobal.length; //puntos global filtrados
    var nroCapas = LayerRutas.features.length; //layer geojson
    console.log('capas' + redondeo(nroCapas / 10, 0));
    var rango = generarRango(CantPuntSlecc, redondeo(nroCapas / 10, 0), '#FF0000', 10);
    //console.log(rango);
    /*cuanto puntos pertenecen a ese poligono*/
    var puntosFiltrados = FiltroPuntosPolygonCoroplejico(feature, PuntosClientesGlobal);
    var longitudPunstosFiltrados = puntosFiltrados.length;

    var colorcapa = "";
    for (var i = 0; i < rango.length; i++) {
        //console.log('[' + rango[i][0] + '-' + rango[i][1] + '] ->' + longitudPunstosFiltrados);
        if (longitudPunstosFiltrados > 0 && longitudPunstosFiltrados >= rango[i][0] && longitudPunstosFiltrados <= rango[i][1] + 1) {
            colorcapa = rango[i][2];
            break;
        }
        else {
            colorcapa = '#FFE1E1';
        }
    }
    return {"clickable": true,
        "fillColor": colorcapa,
        "weight": 0.7,
        "opacity": 1,
        "fillOpacity": 0.9,
        color: "#B300B3"};


}

/*==================FIN ================================*/


/*?=====================================LAYER Mesas ============================================================*/

/*=====================LEGENDA==============================*/

//Leyenda de cliente Dias
legendLayerMesas.onAdd = function(map) {

//    var CantPuntSlecc = PuntosClientesGlobal.length;
//    var nroCapas = LayerMesas.features.length;
//    var rango = generarRango(CantPuntSlecc, redondeo(nroCapas / 2, 0), '#FF972F', 10);


    var div = L.DomUtil.create('div', 'info legend'),
            grades = [0, 10, 20, 50, 100, 200, 500, 1000],
            labels = [],
            from, to;

    labels.push(
            '<i style="background:#fff "></i> 0'
            );

    for (var i = 0; i < rangoMesas.length; i++) {
        from = (rangoMesas[i][0] == 0) ? rangoMesas[i][0] + 1 : rangoMesas[i][0];
        to = rangoMesas[i][1];

        labels.push('<i style="background:' + rangoMesas[i][2] + '"></i> ' + rangoMesas[i][3]);
    }

    div.innerHTML = ' <input id="verLegendMesas" type="checkbox" value="" onclick="ocultarLeyendaMesas();" checked > Mostrar Legend Mesas<div id="infoLegendMesas">' + labels.join('<br>') + '</div>';
    return div;
};
/*Funcion que me permite ocultar la leyenda de los Mesas*/
function ocultarLeyendaMesas() {
    if ($("#verLegendMesas").is(':checked')) {
        $('#infoLegendMesas').show();
    } else {
        $('#infoLegendMesas').hide();
    }
}
/*funcion que carga  la leyenda del capa de Mesas al mapa*/
function cargarLeyendaMesas() {
    legendLayerMesas.addTo(map);
}



/*=====================INFORMACION==============================*/

/*funcion de la variable informacion de las mesas que se ejecuta al agregar la capa mesas*/
infoLayerMesas.onAdd = function(map) {
    this._div = L.DomUtil.create('div', 'info');
    this.update();
    return this._div;
};
/*funcion de la variable informacion de las mesas que se ejecuta al pasar el mouse sobre una mesa*/
infoLayerMesas.update = function(layer) {
    if (layer == null) {
        this._div.innerHTML = 'Pase el mouse sobre una Zona';
    } else {
        var props = layer.feature.properties;
        //var puntosFiltrados = FiltroPuntosPolygonCoroplejico(layer.feature, PuntosClientesGlobal);
        var codDia = layer.feature.properties.codigomesa;
        var lengh = 0;
        for (var i = 0; i < ArrayClientesMesas.length; i++) {
            if (ArrayClientesMesas[i][0] === codDia) {
                lengh = ArrayClientesMesas[i][1];
            }
        }


        //var puntosFiltrados = FiltroPuntosPolygonCoroplejico(layer.feature, PuntosClientesGlobal);
        console.log(props);
        this._div.innerHTML = '<h4>Capa de Mesas</h4>' + (props ?
                '   <b>Mesa: ' + props.dia + ' ' + props.mesa + '</b><br />\n\
                    <b>Cantidad clientes: ' + lengh + '</b>'
                : 'Pase el mouse sobre una Zona');
    }
};
/*funcion que me permite agregar eel detalle de una mesa */
function cargarDetalleCapaMesas() {
    infoLayerMesas.addTo(map);
}

/*funcion que permite la carga de la capa mesas */
function onEachFeatureMesas(feature, layer) {
    layer.on({
        mouseover: highlightFeatureMesas,
        mouseout: resetHighlightMesas,
        click: zoomToFeatureMesas
    });


    //LayerMesasCliente
}
/*funcion que muestrar la informacion al pasar el mouse por encima de una capa de Mesas */
function highlightFeatureMesas(e) {

    var layer = e.target;

    layer.setStyle({
        weight: 5,
        color: '#666',
        dashArray: '',
        fillOpacity: 0.1
    });

    if (!L.Browser.ie && !L.Browser.opera) {
        layer.bringToFront();


    }

    infoLayerMesas.update(layer);
    bringToFront_draw();
}


/*funcion que quitar la informacion al sacar el mouse por encima de una capa de Mesas */
function resetHighlightMesas(e) {
    LayerMesasCliente.resetStyle(e.target);
    infoLayerRutas.update();
    bringToFront_draw();
}

/*funcion que se ejecuta al hacer un zoom sobre la capa Mesas */
function zoomToFeatureMesas(e) {
    map.fitBounds(e.target.getBounds());
    bringToFront_draw();

}

/*funcion que se ejecuta para pintar las mesas de acuerdo a los rangos de busqueda*/
function pintarMesas(feature) {
    var codRuta = feature.properties.codigomesa;
    var pintura = {
        "clickable": true,
        "fillColor": '#FFF',
        "weight": 1.0,
        "opacity": 1,
        "fillOpacity": 0.1,
        color: "blue"
    };
    var colorcapa = "";
    for (var i = 0; i < ArrayClientesMesas.length; i++) {
        if (ArrayClientesMesas[i][0] === codRuta) {
            for (var j = 0; j < rangoMesas.length; j++) {
                //console.log('[' + rango[i][0] + '-' + rango[i][1] + '] ->' + longitudPunstosFiltrados);
                if (ArrayClientesMesas[i][1] > 0 && ArrayClientesMesas[i][1] >= rangoMesas[j][0] && ArrayClientesMesas[i][1] <= rangoMesas[j][1] + 1) {
                    colorcapa = rangoMesas[j][2];
                    console.log('Clientes Arrays' + i);
                    console.log('Rango Arrays' + j);
                    console.log(colorcapa);
                    break;
                }
                else {
                    colorcapa = '#FFF';
                    console.log(colorcapa);
                }
            }
        }
    }
    pintura.fillColor = colorcapa;

    return  pintura;


}

/*Funcion que pinta un la capa coroplejico de dias*/
function pintarCoroplejicoMesas(feature) {

    //var CantPuntSlecc = PuntosClientesGlobal.length; //puntos global filtrados
    //var nroCapas = LayerMesas.features.length; //layer geojson
    //console.log('capas' + redondeo(nroCapas / 10, 0));
    //var rango = generarRango(CantPuntSlecc, redondeo(nroCapas / 2, 0), '#FF972F', 10);
    //console.log(rango);
    /*cuanto puntos pertenecen a ese poligono*/
    var puntosFiltrados = FiltroPuntosPolygonCoroplejico(feature, PuntosClientesGlobal);
    var longitudPunstosFiltrados = puntosFiltrados.length;

    var colorcapa = "";
    for (var i = 0; i < rangoMesas.length; i++) {
        //console.log('[' + rango[i][0] + '-' + rango[i][1] + '] ->' + longitudPunstosFiltrados);
        if (longitudPunstosFiltrados > 0 && longitudPunstosFiltrados >= rangoMesas[i][0] && longitudPunstosFiltrados <= rangoMesas[i][1] + 1) {
            colorcapa = rangoMesas[i][2];
            break;
        }
        else {
            colorcapa = '#FFDFBF';
        }
    }
    return {"clickable": true,
        "fillColor": colorcapa,
        "weight": 0.7,
        "opacity": 1,
        "fillOpacity": 0.9,
        color: "#003322"};
}




//superponer capa draw sobre otros
function bringToFront_draw() {
    setTimeout(function() {
        drawnItems.bringToFront();
    }, 100);
}