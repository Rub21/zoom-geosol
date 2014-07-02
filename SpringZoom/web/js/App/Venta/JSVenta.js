/*funcion que me permite crear el calendario con rango personalizado*/
function cargarFechaRango() {

    var cb = function(start, end, label) {
        console.log(start.toISOString(), end.toISOString(), label);
        $('#reportrange span').html(start.format('YYYY-MM-DD') + ' hasta ' + end.format('YYYY-MM-DD'));
        //alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
    }

    var optionSet1 = {
        startDate: moment().subtract('days', 29),
        endDate: moment(),
        minDate: '2012-01-01',
        maxDate: '2014-12-31',
        dateLimit: {days: 60},
        showDropdowns: true,
        showWeekNumbers: true,
        timePicker: false,
        timePickerIncrement: 1,
        timePicker12Hour: true,
        ranges: {
            'Hoy dia': [moment(), moment()],
            'Ayer': [moment().subtract('days', 1), moment().subtract('days', 1)],
            'Ultimos 7 Dias': [moment().subtract('days', 6), moment()],
            'Ultimos 30 Dias': [moment().subtract('days', 29), moment()],
            'Este mes': [moment().startOf('month'), moment().endOf('month')],
            'Mes anterior': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')],
            'Este año': [moment().startOf('year'), moment().endOf('year')],
            'Año anterior': [moment().subtract('year', 1).startOf('year'), moment().subtract('year', 1).endOf('year')]
        },
        opens: 'right',
        buttonClasses: ['btn btn-default'],
        applyClass: 'btn-small btn-primary',
        cancelClass: 'btn-small',
        format: 'YYYY-MM-DD',
        separator: ' to ',
        locale: {
            applyLabel: 'Enviar',
            cancelLabel: 'Limpiar',
            fromLabel: 'Desde',
            toLabel: 'Hasta',
            customRangeLabel: 'Personalizar',
            daysOfWeek: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'],
            monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'],
            firstDay: 1
        }
    };



    $('#reportrange span').html(moment().subtract('days', 29).format('YYYY-MM-DD') + ' hasta ' + moment().format('YYYY-MM-DD'));

    $('#reportrange').daterangepicker(optionSet1, cb);



    $('#reportrange').on('apply.daterangepicker', function(ev, picker) {
        console.log("apply event fired, start/end dates are "
                + picker.startDate.format('MMMM D, YYYY')
                + " to "
                + picker.endDate.format('MMMM D, YYYY')
                );
    });

    $('#reportrange').on('cancel.daterangepicker', function(ev, picker) {
        console.log("cancel event fired");
    });

    $('#destroy').click(function() {
        $('#reportrange').data('daterangepicker').remove();
    });
}
/*________________________VARIABLES GLOBALES____________________*/
var ArrayDias = []; //Datos de los dias al culminar la busqueda
var ArrayMesas = []; //Datos de los Mesas al culminar la busqueda
var ArrayRutas = []; //Datos de los Rutas al culminar la busqueda
var ind = 0;


/*________________________AREA INICIO ____________________*/
$(function() {

    // cargarComboAnio();
    $('.content_draggable').css({'left': $(window).width() - 200});
    $('#fecha').show();
    $('#rango').hide();

    //$('#txtFechaInicio').datepicker({"dateFormat": 'yy-mm-dd', changeMonth: true, changeYear: true});
    //$('#txtFechaFin').datepicker({"dateFormat": 'yy-mm-dd', changeMonth: true, changeYear: true});
    cargarFechaRango();
    cargarLayerNegocioCliente();

});

/*________________________AREA DE EVENTOS ____________________*/
$(function() {

    $("#drpMes").change(function() {
        //console.log($('#drpAnio').val());
        //console.log($('#drpMes').val());
        cargarComboDias($('#drpAnio').val(), $('#drpMes').val());
    });
    /*enevento que controla el boton buscar ventas al realiza la busqueda de las ventas */
    $('#btnbusquedaVentas').click(function() {
        mostrarVentas();
    });

    /*Evento que click que muestra la capa de los dias*/
    $("#chkLayerDias").click(function() {
        if ($("#chkLayerDias").is(':checked')) {
            loadLayerDiasVenta();
            ArrayInformacion.push('infoVentasDias');
            ArrayLeyendas.push('legendVentasDias');
            $(".info").draggable();
        } else {
            map.removeLayer(LayerDiasVenta);
            infoVentasDias.removeFrom(map);
            legendVentasDias.removeFrom(map);
            eliminarElemArray(ArrayInformacion, 'infoVentasDias');
            eliminarElemArray(ArrayLeyendas, 'legendVentasDias');


        }
    });
    /*Evento que click que muestra la capa de los Mesas*/
    $("#chkLayerMesas").click(function() {
        if ($("#chkLayerMesas").is(':checked')) {
            loadLayerMesasVenta();
            ArrayInformacion.push('infoVentasMesas');
            ArrayLeyendas.push('legendVentasMesas');
            $(".info").draggable();
        } else {
            map.removeLayer(LayerMesasVenta);
            infoVentasMesas.removeFrom(map);
            legendVentasMesas.removeFrom(map);
            eliminarElemArray(ArrayInformacion, 'infoVentasMesas');
            eliminarElemArray(ArrayLeyendas, 'legendVentasMesas');
        }
    });
    /*Evento que click que muestra la capa de los Rutas*/
    $("#chkLayerRutas").click(function() {
        if ($("#chkLayerRutas").is(':checked')) {
            loadLayerRutasVenta();
            ArrayInformacion.push('infoVentasRutas');
            ArrayLeyendas.push('legendVentasRutas');
            $(".info").draggable();
        } else {
            map.removeLayer(LayerRutasVenta);
            infoVentasRutas.removeFrom(map);
            legendVentasRutas.removeFrom(map);
            eliminarElemArray(ArrayInformacion, 'infoVentasRutas');
            eliminarElemArray(ArrayLeyendas, 'legendVentasRutas');
        }
    });


});

/*funcion que permite eliminar */



/*________________________AREA DE FUNCIONES ____________________*/

/*Agregando los Dias, Mesas, Rutas a los capas bases de los mapas*/
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


/*____________________________CARGAR INFORMACION__________________________________*/
/*funcion que me permite buscar las ventas mediante fechas y rango de fechas*/
function  mostrarVentas() {
    $('body').addClass("loading");
    var ind = 2;
    var ventas = {
        IndOpSp: 1,
        anio: $('#drpAnio').val(),
        mes: $('#drpMes').val(),
        dia: $('#drpDia').val(),
        fechaInicio: $('#reportrange span').text().substring(0, 10),
        fechaFin: $('#reportrange span').text().substring(17, 27)
    };
    setTimeout(function() {
        if (ind == 1) {
            $.ajaxGeoSolution('VentasController/ListarVentas.htm', {ind: 1, poVentasBE: ventas}, false, function(response) {
                ArrayDias = response;
            });
            $.ajaxGeoSolution('VentasController/ListarVentas.htm', {ind: 2, poVentasBE: ventas}, false, function(response) {
                ArrayMesas = response;
            });
            $.ajaxGeoSolution('VentasController/ListarVentas.htm', {ind: 3, poVentasBE: ventas}, false, function(response) {
                ArrayRutas = response;
                $('body').removeClass("loading");
                $('#txtResultadoBusqueda').text('Busqueda realizada');
            });
        }
        if (ind == 2) {

            $.ajaxGeoSolution('VentasController/ListarVentas.htm', {ind: 4, poVentasBE: ventas}, false, function(response) {
                ArrayDias = response;
            });
            $.ajaxGeoSolution('VentasController/ListarVentas.htm', {ind: 5, poVentasBE: ventas}, false, function(response) {
                ArrayMesas = response;
            });
            $.ajaxGeoSolution('VentasController/ListarVentas.htm', {ind: 6, poVentasBE: ventas}, false, function(response) {
                ArrayRutas = response;
                $('body').removeClass("loading");
                $('#txtResultadoBusqueda').text('Busqueda realizada');
            });
        }
    }, 100);

}



var LayerDiasVenta = null;
/*____________________________COROPLEJICO__________________________________*/
//COROPLEJICO DIAS
function loadLayerDiasVenta() {
    LayerDiasVenta = L.geoJson(LayerDias, {style: pintarDias, onEachFeature: onEachFeatureDiasVentas}).addTo(map);
    layerGroup.addLayer(LayerDiasVenta).addTo(map);
    cargarDetalleCapaVentaDias();
    cargarLeyendaDiasVenta();
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
    for (var i = 0; i < ArrayDias.length; i++) {
        if (ArrayDias[i][0] === codDia) {
            for (var j = 0; j < rangoDiasVenta.length; j++) {
                //console.log('[' + rango[i][0] + '-' + rango[i][1] + '] ->' + longitudPunstosFiltrados);
                if (ArrayDias[i][4] > 0 && ArrayDias[i][4] >= rangoDiasVenta[j][0] && ArrayDias[i][4] <= rangoDiasVenta[j][1] + 1) {
                    colorcapa = rangoDiasVenta[j][2];
                    break;
                }
                else {
                    colorcapa = '#FFF';
                    //console.log(colorcapa);
                }
            }
        }
    }
    pintura.fillColor = colorcapa;
    return  pintura;
}

/*?=====================================LAYER DIAS ============================================================*/
function onEachFeatureDiasVentas(feature, layer) {
    layer.on({
        mouseover: highlightFeatureDiasVentas,
        mouseout: resetHighlightDiasVentas,
        click: zoomToFeatureDiasVentas
    });
}

/*=====================LEGENDA==============================*/
var legendVentasDias = L.control({position: 'bottomleft'});
legendVentasDias.onAdd = function(map) {
    //layerGroup.addLayer(legendVentasDias).addTo(map);
    var div = L.DomUtil.create('div', 'info legend'),
            grades = [0, 10, 20, 50, 100, 200, 500, 1000],
            labels = [],
            from, to;
    labels.push(
            '<i style="background:#FFF "></i> 0'
            );
    for (var i = 0; i < rangoDiasVenta.length; i++) {
        from = (rangoDiasVenta[i][0] == 0) ? rangoDiasVenta[i][0] + 1 : rangoDiasVenta[i][0];
        to = rangoDiasVenta[i][1];
        labels.push(
                '<i style="background:' + rangoDiasVenta[i][2] + '"></i> ' + rangoDiasVenta[i][3]);
    }
    div.innerHTML = ' <input id="verLegendDias" type="checkbox" value="" onclick="ocultarLeyendaDias();" checked > Mostrar Legend Dias<div id="infoLegendDias">' + labels.join('<br>') + '</div>';
    return div;
};

function ocultarLeyendaDias() {
    if ($("#verLegendDias").is(':checked')) {
        $('#infoLegendDias').show();
    } else {
        $('#infoLegendDias').hide();
    }

}

/*=====================INFORMACION==============================*/
var infoVentasDias = L.control({position: 'topleft'});
infoVentasDias.onAdd = function(map) {
    //layerGroup.addLayer(infoVentasDias).addTo(map);
    this._div = L.DomUtil.create('div', 'info');
    this.update();
    return this._div;
};

infoVentasDias.update = function(layer) {
    if (layer == null) {
        this._div.innerHTML = 'Pase el mouse sobre una Dia';
    } else {
        var props = layer.feature.properties;

        //var puntosFiltrados = FiltroPuntosPolygonCoroplejico(layer.feature, PuntosClientesGlobal);
        var codDia = layer.feature.properties.cod;

        var cantidadunitaria = 0;
        var itemcostoventa = 0;
        var itemvalorventa = 0;
        var totalventa = 0;

        for (var i = 0; i < ArrayDias.length; i++) {
            if (ArrayDias[i][0] === codDia) {
                cantidadunitaria = redondeo(ArrayDias[i][1]);
                itemcostoventa = redondeo(ArrayDias[i][2]);
                itemvalorventa = redondeo(ArrayDias[i][3]);
                totalventa = redondeo(ArrayDias[i][4]);

            }
        }

        this._div.innerHTML = '<h4>Capa de Dias</h4>' + (props ?
                'DIA: ' + props.Dia + ''
                + '<br/>U VENDIDAS&nbsp:<strong>' + cantidadunitaria + '</strong>'
                + '<br/>COSTO VENTA:<strong>' + itemcostoventa + '</strong>'
                + '<br/>VAL. VENTA:<strong>' + itemvalorventa + '</strong>'
                + '<br/>TOTAL VENTA:<strong>' + totalventa + '</strong>'

                : 'Pase el mouse sobre una Dia');
    }
};

function cargarDetalleCapaVentaDias() {
    infoVentasDias.addTo(map);
    //layerGroup.addLayer(infoVentasDias).addTo(map);
}
/*fin informacion*/


function cargarLeyendaDiasVenta() {
    legendVentasDias.addTo(map);
    //layerGroup.addLayer(legendVentasDias).addTo(map);

}


function highlightFeatureDiasVentas(e) {
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
    infoVentasDias.update(layer);
}

function resetHighlightDiasVentas(e) {
    LayerDiasVenta.resetStyle(e.target);
    infoVentasDias.update();
}

function zoomToFeatureDiasVentas(e) {
    map.fitBounds(e.target.getBounds());
}




var LayerMesasVenta = null;
/*____________________________COROPLEJICO__________________________________*/
//COROPLEJICO MESAS
function loadLayerMesasVenta() {


    LayerMesasVenta = L.geoJson(LayerMesas, {style: pintarMesas, onEachFeature: onEachFeatureMesasVentas}).addTo(map);
    layerGroup.addLayer(LayerMesasVenta).addTo(map);


    cargarDetalleCapaMesas();
    cargarLeyendaVentaMesas();
}

/*Funcion que pinta un la capa coroplejico de dias*/
function pintarMesas(feature) {
    //var nomDia = feature.properties.Dia;

    var codigomesa = feature.properties.codigomesa;
    var pintura = {
        "clickable": true,
        "fillColor": '#FFF',
        "weight": 1.0,
        "opacity": 1,
        "fillOpacity": 0.1,
        color: "red"
    };
    var colorcapa = "";
    for (var i = 0; i < ArrayMesas.length; i++) {
        if (ArrayMesas[i][0] === codigomesa) {
            for (var j = 0; j < rangoMesasVenta.length; j++) {
                //console.log('[' + rango[i][0] + '-' + rango[i][1] + '] ->' + longitudPunstosFiltrados);
                if (ArrayMesas[i][4] > 0 && ArrayMesas[i][4] >= rangoMesasVenta[j][0] && ArrayMesas[i][4] <= rangoMesasVenta[j][1] + 1) {
                    colorcapa = rangoMesasVenta[j][2];
                    break;
                }
                else {
                    colorcapa = '#FFF';
                }
            }
        }
    }
    pintura.fillColor = colorcapa;
    return  pintura;
}

/*?=====================================LAYER MESAS ============================================================*/
/*funcion que me permite manejar los envento para la capa de mesas*/
function onEachFeatureMesasVentas(feature, layer) {

    layer.on({
        mouseover: highlightFeatureMesasVentas,
        mouseout: resetHighlightMesasVentas,
        click: zoomToFeatureMesasVentas
    });
}

/*=====================LEGENDA==============================*/
/*variable de leyenda para la capa de mesas*/
var legendVentasMesas = L.control({position: 'bottomleft'});

/*funcion que se dispara al agregar la leyenda de las mesas al mapa*/
legendVentasMesas.onAdd = function(map) {

    var div = L.DomUtil.create('div', 'info legend'),
            grades = [0, 10, 20, 50, 100, 200, 500, 1000],
            labels = [],
            from, to;
    labels.push(
            '<i style="background:#FFF "></i> 0'
            );
    for (var i = 0; i < rangoMesasVenta.length; i++) {
        from = (rangoMesasVenta[i][0] == 0) ? rangoMesasVenta[i][0] + 1 : rangoMesasVenta[i][0];
        to = rangoMesasVenta[i][1];
        labels.push('<i style="background:' + rangoMesasVenta[i][2] + '"></i> ' + rangoMesasVenta[i][3]);
    }
    div.innerHTML = ' <input id="verLegendMesas" type="checkbox" value="" onclick="ocultarLeyendaMesas();" checked > Mostrar Legend Mesas<div id="infoLegendMesas">' + labels.join('<br>') + '</div>';
    return div;
};

/*funcion que permite ocualtar la leyenda de los capa mesas*/
function ocultarLeyendaMesas() {
    if ($("#verLegendMesas").is(':checked')) {
        $('#infoLegendMesas').show();
    } else {
        $('#infoLegendMesas').hide();
    }
}
/*=====================INFORMACION==============================*/
/*variable que para la informacion de la capa mesas*/
var infoVentasMesas = L.control({position: 'topleft'});
infoVentasMesas.onAdd = function(map) {
    this._div = L.DomUtil.create('div', 'info');
    this.update();
    return this._div;
};
/*permite mostrar la informacion de la capa de mesas*/
infoVentasMesas.update = function(layer) {
    if (layer == null) {
        this._div.innerHTML = 'Pase el mouse sobre una Mesa';
    } else {

        var props = layer.feature.properties;
        //var puntosFiltrados = FiltroPuntosPolygonCoroplejico(layer.feature, PuntosClientesGlobal);
        var codigomesa = layer.feature.properties.codigomesa;
        var cantidadunitaria = 0;
        var itemcostoventa = 0;
        var itemvalorventa = 0;
        var totalventa = 0;

        for (var i = 0; i < ArrayMesas.length; i++) {
            if (ArrayMesas[i][0] === codigomesa) {
                cantidadunitaria = redondeo(ArrayMesas[i][1]);
                itemcostoventa = redondeo(ArrayMesas[i][2]);
                itemvalorventa = redondeo(ArrayMesas[i][3]);
                totalventa = redondeo(ArrayMesas[i][4]);

            }
        }
        ;

        this._div.innerHTML = '<h4>Capa de Mesas</h4>' + (props ?
                'MESA: ' + props.dia + ' - ' + props.mesa + ' '
                + '<br/>U VENDIDAS&nbsp:<strong>' + cantidadunitaria + '</strong>'
                + '<br/>COSTO VENTA:<strong>' + itemcostoventa + '</strong>'
                + '<br/>VAL. VENTA:<strong>' + itemvalorventa + '</strong>'
                + '<br/>TOTAL VENTA:<strong>' + totalventa + '</strong>'
                : 'Pase el mouse sobre una Mesa');

        //console.log(this._div.innerHTML);
    }
};

/*funcion que permite cargar la capa de mesas*/
function cargarDetalleCapaMesas() {
    infoVentasMesas.addTo(map);
    //layerGroup.addLayer(infoVentasMesas).addTo(map);

}
/*FUncion que permite cargar la leyenda de las mesas al mapa*/
function cargarLeyendaVentaMesas() {
    legendVentasMesas.addTo(map);
    //layerGroup.addLayer(legendVentasMesas).addTo(map);
}


function highlightFeatureMesasVentas(e) {
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
    infoVentasMesas.update(layer);
}

function resetHighlightMesasVentas(e) {

    LayerMesasVenta.resetStyle(e.target);
    infoVentasMesas.update();

}

function zoomToFeatureMesasVentas(e) {
    map.fitBounds(e.target.getBounds());
}






var LayerRutasVenta = null;
/*____________________________COROPLEJICO__________________________________*/
//COROPLEJICO MESAS
function loadLayerRutasVenta() {
    LayerRutasVenta = L.geoJson(LayerRutas, {style: pintarRutas, onEachFeature: onEachFeatureRutasVentas}).addTo(map);
    layerGroup.addLayer(LayerRutasVenta).addTo(map);
    cargarDetalleCapaRutas();
    cargarLeyendaVentaRutas();
}

/*Funcion que pinta un la capa coroplejico de dias*/
function pintarRutas(feature) {
    //var nomDia = feature.properties.Dia;

    var Ruta = feature.properties.CodZona;
    var pintura = {
        "clickable": true,
        "fillColor": '#FFF',
        "weight": 1.0,
        "opacity": 1,
        "fillOpacity": 0.1,
        color: "red"
    };

    var colorcapa = "";
    for (var i = 0; i < ArrayRutas.length; i++) {
        if (ArrayRutas[i][0] === Ruta) {
            for (var j = 0; j < rangoRutasVenta.length; j++) {
                //console.log('[' + rango[i][0] + '-' + rango[i][1] + '] ->' + longitudPunstosFiltrados);
                if (ArrayRutas[i][4] > 0 && ArrayRutas[i][4] >= rangoRutasVenta[j][0] && ArrayRutas[i][4] <= rangoRutasVenta[j][1] + 1) {
                    colorcapa = rangoRutasVenta[j][2];
                    break;
                }
                else {
                    colorcapa = '#FFF';

                }
            }
        }
    }
    pintura.fillColor = colorcapa;
    return  pintura;
}

/*?=====================================LAYER MESAS ============================================================*/
function onEachFeatureRutasVentas(feature, layer) {
    layer.on({
        mouseover: highlightFeatureRutasVentas,
        mouseout: resetHighlightRutasVentas,
        click: zoomToFeatureRutasVentas
    });
}

/*=====================LEGENDA==============================*/
var legendVentasRutas = L.control({position: 'bottomleft'});

legendVentasRutas.onAdd = function(map) {

    var div = L.DomUtil.create('div', 'info legend'),
            grades = [0, 10, 20, 50, 100, 200, 500, 1000],
            labels = [],
            from, to;
    labels.push(
            '<i style="background:#FFF "></i> 0'
            );
    for (var i = 0; i < rangoRutasVenta.length; i++) {
        from = (rangoRutasVenta[i][0] == 0) ? rangoRutasVenta[i][0] + 1 : rangoRutasVenta[i][0];
        to = rangoRutasVenta[i][1];
        labels.push(
                '<i style="background:' + rangoRutasVenta[i][2] + '"></i> ' + rangoRutasVenta[i][3]);
    }
    div.innerHTML = ' <input id="verLegendRutas" type="checkbox" value="" onclick="ocultarLeyendaRutas();" checked > Mostrar Legend Rutas<div id="infoLegendRutas">' + labels.join('<br>') + '</div>';
    return div;
};

function ocultarLeyendaRutas() {
    if ($("#verLegendRutas").is(':checked')) {
        $('#infoLegendRutas').show();
    } else {
        $('#infoLegendRutas').hide();
    }
}

/*=====================INFORMACION==============================*/
var infoVentasRutas = L.control({position: 'topleft'});
infoVentasRutas.onAdd = function(map) {
    this._div = L.DomUtil.create('div', 'info');
    this.update();
    return this._div;
};

infoVentasRutas.update = function(layer) {
    if (layer == null) {

        this._div.innerHTML = 'Pase el mouse sobre una Ruta.';
    } else {
        var props = layer.feature.properties;

        //var puntosFiltrados = FiltroPuntosPolygonCoroplejico(layer.feature, PuntosClientesGlobal);
        var Ruta = layer.feature.properties.CodZona;

        var cantidadunitaria = 0;
        var itemcostoventa = 0;
        var itemvalorventa = 0;
        var totalventa = 0;

        for (var i = 0; i < ArrayRutas.length; i++) {
            if (ArrayRutas[i][0] === Ruta) {
                cantidadunitaria = redondeo(ArrayRutas[i][1]);
                itemcostoventa = redondeo(ArrayRutas[i][2]);
                itemvalorventa = redondeo(ArrayRutas[i][3]);
                totalventa = redondeo(ArrayRutas[i][4]);

            }
        }


        this._div.innerHTML = '<h4>Capa de Rutas</h4>' + (props ?
                'RUTA: ' + props.Ruta + ''
                + '<br/>U VENDIDAS&nbsp:<strong>' + cantidadunitaria + '</strong>'
                + '<br/>COSTO VENTA:<strong>' + itemcostoventa + '</strong>'
                + '<br/>VAL. VENTA:<strong>' + itemvalorventa + '</strong>'
                + '<br/>TOTAL VENTA:<strong>' + totalventa + '</strong>'

                : 'Pase el mouse sobre una Dia');

        //console.log(this._div.innerHTML);
    }
};

function cargarDetalleCapaRutas() {
    infoVentasRutas.addTo(map);
    //layerGroup.addLayer(infoVentasDias).addTo(map);

}
/*fin informacion*/


function cargarLeyendaVentaRutas() {
    legendVentasRutas.addTo(map);
    //layerGroup.addLayer(legendVentasRutas).addTo(map);
}


function highlightFeatureRutasVentas(e) {
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
    //debugger;
    infoVentasRutas.update(layer);
}

function resetHighlightRutasVentas(e) {
    LayerRutasVenta.resetStyle(e.target);
    //debugger;
    infoVentasRutas.update();
}

function zoomToFeatureRutasVentas(e) {
    map.fitBounds(e.target.getBounds());
}








