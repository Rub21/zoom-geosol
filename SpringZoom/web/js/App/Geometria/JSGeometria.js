/***********************************************MOTODOS DE GRILLAS ********************************/
var i = 1;

//crea grilla para geometrias
function crearGrillaGeometria() {
    $("#gridgeometria").jqGrid({
        /*data: mydata,*/
        datatype: "local",
        height: 120,
        width: 372,
        caption: "Geometrias dibujadas",
        colNames: ["Id", "Nombre", "Tipo", "Valor", "Color", "Descripcion", "Opacity"],
        colModel: [{
                name: 'idgeometria',
                index: 'idgeometria',
                editable: false
            }, {
                name: 'nombre',
                index: 'nombre',
                editable: true
            }, {
                name: 'tipo',
                index: 'tipo',
                editable: false
            }, {
                name: 'valor',
                index: 'valor',
                editable: false,
                hidden: true
            }, {
                name: 'color',
                index: 'color',
                editable: false,
                hidden: true
            },
            {
                name: 'descripcion',
                index: 'descripcion',
                editable: false,
                hidden: true
            },
            {
                type: "number",
                name: 'opacity',
                index: 'opacity',
                editable: false,
                hidden: true
            }
        ],
        pager: '#pagergeometria',
        onSelectRow: centrar_mostrar_geometria,
        viewrecords: true,
        shrinkToFit: false,
        multiselect: true
    });
    //$("#gridgeometria").jqGrid('filtertolbar',{searchOnEnter: false, enableClear: true, stringResult: true});
    jQuery("#gridgeometria").jqGrid('filterToolbar', {stringResult: true, searchOnEnter: false});
}

function centrar_mostrar_geometria(id) {
    var rowData = jQuery('#gridgeometria').jqGrid('getRowData', id);
    console.log(rowData);
    geom = jQuery.parseJSON(rowData.valor);


    var lat = 0;
    var lng = 0;

    if (rowData.tipo === 'rectangle' || rowData.tipo === 'polygon' || rowData.tipo === 'circle') {

        for (i = 0; i < geom.coordinates[0].length; i++) {
            lat = lat + geom.coordinates[0][i][1];
            lng = lng + geom.coordinates[0][i][0];
        }
        lat = lat / geom.coordinates[0].length;
        lng = lng / geom.coordinates[0].length;
        console.log(lat);
        console.log(lng);
    }

    filtrar_geometria();
    map.setView(new L.LatLng(lat, lng), 17);

}


function filtrar_geometria() {
    var array_ids = jQuery("#gridgeometria").getGridParam('selarrrow');
    var data = [];
    for (var i = 0; i < array_ids.length; i++) {
        var rowData = jQuery('#gridgeometria').jqGrid('getRowData', array_ids[i]);
        data.push(rowData);
    }

    show_in_map(data);


}


function crearGrillaClienteGeometria() {
    // $("#gridClienteGeometria").jqGrid("clearGridData", true);
    $("#gridClienteGeometria").jqGrid({
        datatype: "local",
        caption: "Lista de Clientes Filtrados",
        height: 200,
        width: 372,
        colNames: [
            'codigocliente',
            'Nomb. Cliente',
            'Direccion',
            'distrito',
            'categoriacliente',
            'Giro Neg.',
            'coordenada_y',
            'coordenada_x',
            'visual'],
        colModel: [
            {name: 'codigocliente', index: 'codigocliente', width: 55, hidden: false},
            {name: 'nombrecliente', index: 'nombrecliente', width: 90},
            {name: 'direccion', index: 'direccion', width: 100},
            {name: 'distrito', index: 'distrito', width: 80, align: "right", hidden: true},
            {name: 'categoriacliente', index: 'categoriacliente', width: 80, align: "right", hidden: true},
            {name: 'gironegocio', index: 'gironegocio', width: 80, align: "right"},
            {name: 'coordenada_y', index: 'coordenada_y', width: 150, hidden: true},
            {name: 'coordenada_x', index: 'coordenada_x', width: 150, hidden: true},
            {name: 'visual', index: 'visual', width: 150, hidden: true},
        ],
        rowNum: 10,
        shrinkToFit: false,
        pager: '#pagerClienteGeometria',
        ondblClickRow: mostrarDetalleCliente,
        viewrecords: true
    });
}


function mostrarDetalleCliente(id) {
    var rowData = jQuery('#gridClienteGeometria').jqGrid('getRowData', id);
    $('#txtNombresClienteDetalle').val(rowData.nombrecliente);
    $('#txtDireccionClienteDetalle').val(rowData.direccion);
    $('#txtDistritoClienteDetalle').val(rowData.distrito);
    $('#txtCategoriaClienteDetalle').val(rowData.categoriacliente);
    $('#txtGiroClienteDetalle').val(rowData.gironegocio);
    $('#txtLatitudClienteDetalle').val(rowData.coordenada_y);
    $('#txtLongitudClienteDetalle').val(rowData.coordenada_x);
    motrarAutocompleteCliente(rowData.coordenada_x, rowData.coordenada_y, rowData.nombrecliente);
    mostrarBloqueDetalleCliente();
}



function motrarAutocompleteCliente(x, y, nombre) {
    if (circleBusqCliente === null) {
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

    if (marker_geometria === null) {
    }
    else {
        map.removeLayer(marker_geometria);
    }
    marker_geometria = L.marker([y, x]).addTo(map);
    marker_geometria.bindPopup("" + nombre).openPopup();
}



/*Mostrar el detalle del cliente buscado*/
function mostrarBloqueDetalleCliente() {
    $("#blqDetalleCliente").show();
    $("#blqGeometrias").hide();
}
/*Ocultar el detalle del cliente buscado*/
function ocultarBloqueDetalleCliente() {

    $("#blqDetalleCliente").hide();
    $("#blqGeometrias").show();

}


/***********************************************METODOS DE CONSULTA A BASE DE DATOS ********************************/
/*
 function guardargeometria(type, layer) {
 
 layer.options['id'] = 'geo' + i;//va a trabajar como un id del objeto creado
 layer.options['type'] = type;//asignanos el valor de tipo
 
 //declaramos valores del objeto
 var valor;
 var nombre;
 var idgeometria = "geo" + i;
 
 if (type === 'rectangle') {
 valor = get_valor(type, layer);
 nombre = "Rectangulo-" + i;
 layer.bindPopup('<h5>' + nombre + '</h5>');
 }
 else if (type === 'circle') {
 valor = get_valor(type, layer);
 nombre = "Circulo-" + i;
 layer.bindPopup('<h5>' + nombre + '</h5>');
 }
 else if (type === 'polygon') {
 valor = get_valor(type, layer);
 nombre = "Poligono-" + i;
 layer.bindPopup('<h5>' + nombre + '</h5>');
 }
 
 var oGeometria = {
 idgeometria: idgeometria,
 nombre: nombre,
 tipo: type,
 valor: valor
 };
 jQuery("#gridgeometria").jqGrid('addRowData', i, [oGeometria]);
 i++;
 seleccionarcliente();
 
 }
 */

/***********************************************METODOS PARA MAPAS ********************************/

/*
 function open_popup(type, layer) {
 layer.options['id'] = 'geo' + i;//va a trabajar como un id del objeto creado
 layer.options['type'] = type;//asignanos el valor de tipo
 
 var lastPopupLayer;
 $("#map").on("keyup", ".input-title", function(e) {
 lastPopupLayer.title = $(this).val();
 });
 $("#map").on("keyup", ".input-description", function(e) {
 lastPopupLayer.description = $(this).val();
 });
 $("#map").on("change", ".input-color", function(e) {
 lastPopupLayer.setStyle({color: $(this).val()});
 });
 
 $("#map").on("change", ".input-opacity", function(e) {
 lastPopupLayer.setStyle({opacity: $(this).val() / 100,
 fillOpacity: $(this).val() / 100});
 });
 
 var color = "";
 if (type === 'rectangle') {
 color = "#C615A7";
 }
 else if (type === 'circle') {
 color = "#005288";
 }
 else if (type === 'polygon') {
 color = "#B00004";
 }
 var popupBase = '<div class="form">'
 + '<div class="form-group"><label>Title</label><input type="text" class="form-control input-title" name="nombre" id=\'id_mobre_geom' + i + '\'  placeholder=\'' + get_nombre_tipo(type) + '-' + i + '\' ></div>'
 + '<div class="form-group"><label>Description</label><textarea   id=\'id_descripcion_geom' + i + '\' class="form-control input-description"></textarea></div>'
 + '<div class="form-group form-group-color"><label>Color</label><input type="color"  id=\'id_color_geom' + i + '\' class="form-control input-color" value="' + color + '"></div>'
 + '<div class="form-group form-group-range"><label>Opacity</label><input type="range"   id=\'id_opacity_geom' + i + '\'class="input-opacity" min="0" max="100"></div>'
 var boton_guardar = '<button type="button" id=\'id_boton_geom' + i + '\'  class="btn btn-mini btn-primary"  onclick="guardar_geometria_popup(\'' + i + '\',  \'' + type + '\',\'' + get_valor(type, layer) + '\')">Guardar</button>';
 var boton_consultar = '<button type="button" class="btn btn-mini btn-success"   onclick="consultar_geometria_popup(\'' + type + '\',\'' + get_valor(type, layer) + '\')">Consultar</button></form>';
 
 
 //  layer.bindPopup(nombre + boton_guardar + boton_consultar);
 layer.bindPopup(popupBase + boton_guardar + boton_consultar);
 layer.on("popupopen", function(e) {
 lastPopupLayer = e.target;
 populatePopup(e.target, 'polygon');
 });
 i++;
 }
 
 */

//colores de poligono


/*
 function guardar_geometria_popup(id, type, geom, layer) {
 var nombre = $('#id_mobre_geom' + id).val();
 if (nombre === '') {
 nombre = get_nombre_tipo(type) + '-' + id;
 }
 var descripcion = $('#id_descripcion_geom' + id).val();
 var color = $('#id_color_geom' + id).val();
 var opcity = $('#id_opacity_geom' + id).val();
 var oGeometria = {
 idgeometria: 'geom' + id,
 nombre: nombre,
 tipo: type,
 valor: geom,
 descripcion: descripcion,
 color: color,
 opcity: opcity
 };
 $.ajaxGeoSolution('GeometriaController/guardarGeometria.htm', {arraygeometrias: [oGeometria]}, false, function(response) {
 alert(response);
 });
 drawnItems.clearLayers();
 $("#btListarGeometria").trigger("click");
 }
 
 */

/*
 function consultar_geometria_popup(type, geom) {
 var oGeometria = {
 IndOpSp: 2, //2=consulta por geomtria sin registraar
 idgeometria: '',
 nombre: '',
 tipo: type,
 valor: geom
 };
 
 $.ajaxGeoSolution('GeometriaController/listarClienteGeometria.htm', {oGeometria: oGeometria}, false, function(response) {
 $('#gridClienteGeometria').jqGrid('clearGridData');
 jQuery("#gridClienteGeometria").jqGrid('setGridParam', {data: response}).trigger('reloadGrid');
 });
 }
 
 *//*
  
  function modificargeometria(layer) {
  console.log('Objeto Modificado:' + layer.options.id);
  console.log('Objeto tipo:' + layer.options.type);
  var idgeometria = layer.options.id;
  var rowIds = $('#gridgeometria').jqGrid('getDataIDs');
  for (i = 0; i < rowIds.length; i++) {
  console.log(rowIds[i]);
  var rowData = $('#gridgeometria').jqGrid("getRowData", rowIds[i]);
  if (rowData.idgeometria === idgeometria) {
  console.log(rowData.valor);
  var newValor = get_valor(layer.options.type, layer);
  console.log(newValor);
  $('#gridgeometria').jqGrid('setCell', rowIds[i], 'valor', newValor);
  }
  }
  }
  
  */

function eliminargeometria(layer) {
    var idgeometria = layer.options.id;

    var rowIds = $('#gridgeometria').jqGrid('getDataIDs');

    for (i = 0; i < rowIds.length; i++) {
        var rowData = $('#gridgeometria').jqGrid("getRowData", rowIds[i]);


        if (rowData.idgeometria === idgeometria) {
            console.log(rowIds[i]);
            console.log(rowData);
            // $('#gridgeometria').jqGrid('delGridRow', rowIds[i]);

            jQuery('#gridgeometria').delRowData(rowIds[i]);
            //  break;
            //$('#gridgeometria').jqGrid('setCell', rowIds[i], 'valor', newValor);
        }
    }

}


ocultarBloqueDetalleCliente();
crearGrillaGeometria();
crearGrillaClienteGeometria();



$(function() {

    $('#btListarGeometria').click(function() {
        drawnItems.clearLayers();
        $("#gridgeometria").jqGrid("clearGridData", true);
        /*$.ajaxGeoSolution('GeometriaController/listarGeometriaObject.htm', {parametro: "null"}, false, function(data) {
         console.log(data);
         layerGroup.clearLayers();
         // show_in_map(data);
         $("#gridgeometria").jqGrid("clearGridData", true);
         jQuery("#gridgeometria").jqGrid('addRowData', 0, data);
         
         });*/
        listar_geometrias();
    });

    $(".btnMostrarbqlBusqueda").click(function() {
        ocultarBloqueDetalleCliente();
    });



    $("#btMostrarGeometria").click(function() {
        filtrar_geometria();
    });


    $("#btEliminarGeometria").click(function() {
        var array_ids = jQuery("#gridgeometria").getGridParam('selarrrow');
        for (var i = 0; i < array_ids.length; i++) {
            var rowData = jQuery('#gridgeometria').jqGrid('getRowData', array_ids[i]);
            // console.log(rowData.idgeometria);
            eliminar_geometria_popup_id(rowData.idgeometria, 'grilla');
        }

        $("#btListarGeometria").trigger("click");
    });


});


var poligonos = null;

function  show_in_map(data) {
    console.log(data);
    layerGroup.clearLayers();
    var gejson = {
        "type": "FeatureCollection",
        "features": [
        ]
    };
    for (i = 0; i < data.length; i++) {
        features = {
            "type": "Feature",
            "properties": {},
            "geometry": {}
        };
        features.properties.idgeometria = data[i].idgeometria;
        features.properties.tipo = data[i].tipo;
        features.properties.nombre = data[i].nombre;
        features.properties.descripcion = data[i].descripcion;
        features.properties.color = data[i].color;
        features.properties.opacity = data[i].opacity;
        features.geometry = jQuery.parseJSON(data[i].valor);



        /*   
         if(data[i].tipo==='rectangle'){
         
         console.log(features.geometry) 
         
         }
         */



        gejson.features.push(features);
    }

    // 
    poligonos = L.geoJson(gejson, {
        onEachFeature: onEachFeature,
        style: function(feature) {
            return {"color": feature.properties.color,
                opacity: 1,
                fillOpacity: feature.properties.opacity / 100
            };
        }
    });
    layerGroup.addLayer(poligonos).addTo(map);
}


var list_layer_modif = [];
function onEachFeature(feature, layer) {
    list_layer_modif = [];
    list_layer_modif.push(layer);
    layer.on('contextmenu', function(e) {
        $('#id_geom_lista').val(feature.properties.idgeometria);
        layer.options.id = feature.properties.idgeometria;
        FadeOut_lista(e, layer);
    });
}


//funcion que abre y cierra el menucontext

function FadeOut_lista(e, layer) {
    $('#context_lista').show();
    $('#context_lista').css('left', e.originalEvent.clientX);
    $('#context_lista').css('top', e.originalEvent.clientY - 50);

    $('#context_lista').hover(function() {
        $('#context_lista').show();
    });

    $('#context_lista').mouseleave(function() {
        $('#context_lista').hide();
    });
    setTimeout(function() {
        if ($('#context_lista').is(':hover') === true) {
            $('#context_lista').show();
        } else {
            $('#context_lista').hide();
        }
    }, 2000);

    $("#consultar_data_lista").click(function(event) {
        consultar_geometria_ctx($('#id_geom_lista').val());
        e.stopPropagation();
    });

    $("#eliminar_geometria_lista").click(function(event) {
        eliminar_geometria_ctx($('#id_geom_lista').val());
        e.stopPropagation();
    });

    $("#modificar_geometria_lista").click(function(event) {
        modificar_geometria_ctx($('#id_geom_lista').val());
        e.stopPropagation();
    });

    $("#actualizar_geometria_lista").click(function(event) {
        actualizar_geometria_ctx($('#id_geom_lista').val(), layer);
        e.stopPropagation();
    });

    $("#ctx_consultar_data_filtrada").click(function(event) {
        alert(1);
        // actualizar_geometria_ctx($('#id_geom_lista').val(), layer);
        // e.stopPropagation();
    });

}



function consultar_geometria_ctx(id) {
    var oGeometria = {
        IndOpSp: 1, //1=consulta por ids
        idgeometria: id,
        nombre: '',
        tipo: 'type',
        valor: ''
    };

    $.ajaxGeoSolution('GeometriaController/listarClienteGeometria.htm', {oGeometria: oGeometria}, false, function(response) {
        $('#gridClienteGeometria').jqGrid('clearGridData');
        jQuery("#gridClienteGeometria").jqGrid('setGridParam', {data: response}).trigger('reloadGrid');
    });
}


function  eliminar_geometria_ctx(id) {

    var oGeometria = {
        IndOpSp: 1, //1=consulta por ids
        idgeometria: id,
        nombre: '',
        tipo: 'type',
        valor: ''
    };

    $.ajaxGeoSolution('GeometriaController/eliminarGeometria.htm', {oGeometria: oGeometria}, false, function(response) {
        layerGroup.clearLayers();

    });

    // if (de === 'popup') {
    $("#btListarGeometria").trigger("click");
    //}
}


function modificar_geometria_ctx(id) {
    $.each(list_layer_modif, function(key, value) {
        // console.log(value);
        if (id === value.options.id) {

            console.log(get_valor('rectangle', value));
            value.editing.enable();
            $('#li_actualizar_geometria').css("display", "block");
        }



    });
}


function actualizar_geometria_ctx(id, layer) {
    $.each(list_layer_modif, function(key, value) {
        //console.log(value);
        if (id === value.options.id) {
            value.editing.disable();
            console.log("---------------------------------")
            console.log(value.feature)
            $('#li_actualizar_geometria').css("display", "none");
            var oGeometria = {
                IndOpSp: 1, //1=consulta por ids
                idgeometria: value.options.id,
                nombre: '',
                tipo: value.feature.properties.tipo,
                valor: get_valor('rectangle', value)
            };

            $.ajaxGeoSolution('GeometriaController/actualizarGeometria.htm', {oGeometria: oGeometria}, false, function(response) {
                layerGroup.clearLayers();

            });


            $("#btListarGeometria").trigger("click");

        }



    });
}